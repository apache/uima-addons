/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.uima.simpleserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.impl.LowLevelCAS;
import org.apache.uima.cas.impl.TypeSystemUtils;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.simpleserver.config.ConfigFactory;
import org.apache.uima.simpleserver.config.Filter;
import org.apache.uima.simpleserver.config.Output;
import org.apache.uima.simpleserver.config.ServerSpec;
import org.apache.uima.simpleserver.config.TypeMap;
import org.apache.uima.simpleserver.output.Result;
import org.apache.uima.simpleserver.output.ResultEntry;
import org.apache.uima.simpleserver.output.impl.ResultEntryImpl;
import org.apache.uima.simpleserver.output.impl.ResultImpl;

/**
 * This class contains static methods responsible for iterating over annotations, applying filters
 * and building output.
 */
public class ResultExtractor {

  private static class DefaultTypeMap {

    private final Map<Type, TypeMap> typeMap = new HashMap<Type, TypeMap>();

    @SuppressWarnings("unchecked")
    private void initialize(TypeSystem ts) {
      Type annotationType = ts.getType(CAS.TYPE_NAME_ANNOTATION);
      List annotationTypes = ts.getProperlySubsumedTypes(annotationType);
      annotationTypes.add(annotationType);
      Iterator it = annotationTypes.iterator();
      while (it.hasNext()) {
        Type type = (Type) it.next();
        createMapForType(type);
      }
    }

    private void createMapForType(Type type) {
      TypeMap tm = ConfigFactory.newTypeMap(type.getName(), null, type.getShortName(), true);
      Iterator<?> featureIt = type.getFeatures().iterator();
      while (featureIt.hasNext()) {
        Feature feat = (Feature) featureIt.next();
        if (feat.getRange().isPrimitive()) {
          ArrayList<String> path = new ArrayList<String>(1);
          path.add(feat.getShortName());
          Output out = ConfigFactory.newOutput(path, feat.getShortName(),
              "No description provided", "No description provided");
          tm.addOutput(out);
        }
      }
      this.typeMap.put(type, tm);
    }

    private TypeMap get(Type type) {
      return this.typeMap.get(type);
    }

  }

  private TypeSystem ts = null;

  private DefaultTypeMap defaultTypeMap = null;

  /**
   * Extract a set of results from a CAS and a result specification.
   * 
   * @param cas
   *                The CAS to extract results from.
   * @param resultSpec
   *                The result specification.
   */
  public Result getResult(CAS cas, ServerSpec resultSpec) {
    List<ResultEntry> resultEntries = new ArrayList<ResultEntry>();
    processTypes(cas, resultSpec, resultEntries);
    return new ResultImpl(resultEntries, cas.getDocumentText());
  }

  private void processTypes(CAS cas, ServerSpec rspec, List<ResultEntry> resultEntries) {
    if (rspec.getOutputAll()) {
      outputAll(cas, resultEntries);
      return;
    }
    Type annotationType = cas.getTypeSystem().getType(CAS.TYPE_NAME_ANNOTATION);
    for (TypeMap tspec : rspec.getTypeSpecs()) {
      TypeSystem typeSystem = cas.getTypeSystem();
      Type type = typeSystem.getType(tspec.getTypeName());
      // Check that type exists and is an annotation.
      if ((type == null) || !typeSystem.subsumes(annotationType, type)) {
        // If not, skip.
        continue;
      }
      // Iterate over FSs, filter and create output.
      FSIterator iterator = cas.getAnnotationIndex(type).iterator();
      for (; iterator.isValid(); iterator.moveToNext()) {
        AnnotationFS annotation = (AnnotationFS) iterator.get();
        Filter filter = tspec.getFilter();
        // Check that there either is no filter, or the FS passes the filter.
        if ((filter == null) || filter.match(annotation)) {
          // Create a new result entry, fill with attributes and add result set.
          ResultEntryImpl resultEntry = new ResultEntryImpl(tspec.getOutputTag(), annotation
              .getBegin(), annotation.getEnd());
          makeOutputs(resultEntry, annotation, tspec);
          resultEntries.add(resultEntry);
        }

      }
    }

  }

  // TODO: create test case for this
  private void outputAll(CAS cas, List<ResultEntry> resultEntries) {
    checkCreateDefaultMap(cas.getTypeSystem());
    // Output everything except the document annotation.
    Type docAnnotationType = cas.getTypeSystem().getType(CAS.TYPE_NAME_DOCUMENT_ANNOTATION);
    FSIterator it = cas.getAnnotationIndex().iterator();
    for (it.moveToFirst(); it.isValid(); it.moveToNext()) {
      AnnotationFS annot = (AnnotationFS) it.get();
      if (annot.getType().equals(docAnnotationType)) {
        // Skip the document annotation
        continue;
      }
      TypeMap typeMap = this.defaultTypeMap.get(annot.getType());
      // Create a new result entry, fill with attributes and add result set.
      ResultEntryImpl resultEntry = new ResultEntryImpl(typeMap.getOutputTag(), annot.getBegin(),
          annot.getEnd());
      makeOutputs(resultEntry, annot, typeMap);
      resultEntries.add(resultEntry);
    }
  }

  private final void checkCreateDefaultMap(TypeSystem typeSystem) {
    assert(typeSystem != null);
    if (typeSystem == this.ts) {
      return;
    }
    this.ts = typeSystem;
    DefaultTypeMap dtm = new DefaultTypeMap();
    dtm.initialize(typeSystem);
    this.defaultTypeMap = dtm;
  }

  public static void makeOutputs(ResultEntryImpl resultEntry, AnnotationFS annotation, TypeMap tspec) {
    if (tspec.isOutputCoveredText()) {
      resultEntry.setCoveredText(annotation.getCoveredText());
    }
    for (Output outSpec : tspec.getOutputs()) {
      Object value = evaluatePath(annotation, outSpec.getFeaturePath());
      if (value != null) {
        String stringValue = value.toString();
        resultEntry.setAttributeValue(outSpec.getAttribute(), stringValue);
      }
    }

  }

  private static final String evaluatePath(FeatureStructure fs, List<String> path) {
    for (int i = 0; i < path.size(); i++) {
      String f = path.get(i);
      Feature feat = fs.getType().getFeatureByBaseName(f);
      if (feat == null) {
        return null;
      }
      int typeClass = TypeSystemUtils.classifyType(feat.getRange());
      if (typeClass == LowLevelCAS.TYPE_CLASS_FS) {
        fs = fs.getFeatureValue(feat);
        if (fs == null) {
          return null;
        }
      } else {
        if (i == (path.size() - 1)) {
          return fs.getFeatureValueAsString(feat);
        }
        return null;
      }
    }
    return fs.toString();
  }

}
