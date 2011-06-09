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

package org.apache.uima.lucas.indexer.analysis;

import java.io.IOException;
import java.text.Format;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.StringArray;
import org.apache.uima.jcas.tcas.Annotation;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

/**
 * 
 * AnnotationTokenStream represents a TokenStream which extracts tokens from feature values of
 * annotations of a given type from a JCas object. Each token has the start and end offset from the
 * annotation object. This class supports only the following UIMA JCas types of features:
 * <ol>
 * <li>String</li>
 * <li>StringArray</li>
 * <li>FSArray</li>
 * <li>Number types</li>
 * </ol>
 */
public class AnnotationTokenStream extends TokenStream {

  private JCas jCas;

  private String featurePath;

  private List<String> featureNames;

  private String delimiter;

  private Iterator<Annotation> annotationIterator; // iterates over annotations

  private Iterator<FeatureStructure> featureStructureIterator; // iterates over feature structures
                                                               // stored in feature arrays of an
                                                               // annotation

  private Iterator<String> featureValueIterator; // iterates over the features of a feature
                                                 // structure

  private Annotation currentAnnotation;

  private Type annotationType;

  private Map<String, Format> featureFormats; // a optional map of format object for each feature

  private static Logger logger = Logger.getLogger(AnnotationTokenStream.class);

  private class NotNullPredicate<T> implements Predicate<T> {

    public boolean apply(T object) {
      return object != null;
    }
  }

  /**
   * Creates a TokenStream which extracts all coveredText feature values of annotations of a given
   * type from a JCas object. Each token has the start and end offset of the annotation and takes
   * the covered text value as termText.
   * 
   * @param jCas
   *          the jCas
   * @param sofaName the name of the subject of analysis (sofa)
   * @param typeName
   *          the type of the annotation
   * @throws CASException
   */
  public AnnotationTokenStream(JCas jCas, String sofaName, String typeName) throws InvalidTokenSourceException {
    this(jCas, sofaName, typeName, null, Collections.<String>emptyList(), null, 
         Collections.<String, Format>emptyMap());
  }

  /**
   * Creates a TokenStream which extracts all feature values of a given feature name from
   * annotations with a given type from a given JCas object. Each token has the start and end offset
   * of the annotation and uses the feature value as term text.
   * 
   * @param jCas
   *          the JCas object
   * @param sofaName the name of the subject of analysis (sofa)
   * @param typeName
   *          the type of the annotation
   * @param featureName
   *          the name of the feature from which the token text is build
   * @param featureFormat
   *          optional format object to convert feature values to strings
   * @throws InvalidTokenSourceException
   */

  public AnnotationTokenStream(JCas jCas, String sofaName, String typeName, String featureName,
          Format featureFormat) throws InvalidTokenSourceException {
    this(jCas, sofaName, typeName, null, Lists.newArrayList(featureName), null, 
         featureFormat != null ? ImmutableBiMap.of(featureName, featureFormat): Collections.<String, Format>emptyMap());
  }

  /**
   * Creates a TokenStream which extracts all feature values of a given feature name list from
   * annotations with a given type from a given JCas object. Each token has the start and end offset
   * of the annotation and uses the concatenation of all the feature values as term text. Optionally
   * the different feature values of an annotation can be concatenated with a delimiter.
   * 
   * @param jCas
   *          the JCas object
   * @param sofaName the name of the Subject Of Analysis (sofa)
   * @param typeName
   *          the type of the annotation
   * @param featureNames
   *          the name of the feature from which the token text is build
   * @param delimiter
   *          a delimiter for concatenating the different feature values of an annotation object. If
   *          null a white space will be used.
   * @param featureFormats
   *          optional map of format objects to convert feature values to strings - the key must be
   *          the feature name
   * @throws InvalidTokenSourceException
   */
  public AnnotationTokenStream(JCas jCas, String sofaName, String typeName,
          List<String> featureNames, String delimiter, Map<String, Format> featureFormats)
          throws InvalidTokenSourceException {
    this(jCas, sofaName, typeName, null, featureNames, delimiter, featureFormats);
  }

  /**
   * Creates a TokenStream which extracts all feature values of a given feature name list from
   * annotations with a given type from a given JCas object. Each token has the start and end offset
   * of the annotation and uses the concatenation of all the feature values as term text.
   * 
   * @param jCas
   *          the JCas object
   * @param sofaName the name of the Subject Of Analysis (sofa)
   * @param typeName
   *          the type of the annotation
   * @param featureNames
   *          the name of the feature from which the token text is build
   * @param featureFormats
   *          optional map of format objects to convert feature values to strings - the key must be
   *          the feature name
   * @throws InvalidTokenSourceException
   */
  public AnnotationTokenStream(JCas jCas, String sofaName, String typeName,
          List<String> featureNames, Map<String, Format> featureFormats) throws InvalidTokenSourceException {
    this(jCas, sofaName, typeName, null, featureNames, null, featureFormats);
  }

  /**
   * Creates a TokenStream which extracts all feature values of a given feature name list from
   * annotations with a given type from a given JCas object. The addressed features are part of
   * direct or indirect feature structure value of a annotation. For example a annotation of type
   * person has a feature address which values are address feature structures with features for the
   * street, postal code and city . To create tokens with postal code and city of a persons address,
   * the featurePath must be &quot;address&quot; and the featureNames &quot;postalCode&quot; and
   * &quot;city&quot;. Each token has the start and end offset of the annotation and uses the
   * concatenation of all the feature values as term text.
   * 
   * @param jCas
   *          the JCas object
   * @param sofaName the name of the Subject of Analysis (sofa)
   * @param typeName
   *          the type of the annotation
   * @param featurePath
   *          the path to the feature structures which features should be used for tokens Path
   *          entries should be separated by &quot;.&quot;. Example:
   *          &quot;affiliation.address.country&quot;
   * @param featureNames
   *          the name of the feature from which the token text is build
   * @param featureFormats
   *          optional map of format objects to convert feature values to strings - the key must be
   *          the feature name
   * @throws InvalidTokenSourceException
   */
  public AnnotationTokenStream(JCas jCas, String sofaName, String typeName, String featurePath,
          List<String> featureNames, Map<String, Format> featureFormats) throws InvalidTokenSourceException {
    this(jCas, sofaName, typeName, featurePath, featureNames, null, featureFormats);
  }

  /**
   * Creates a TokenStream which extracts all feature values of a given feature name list from
   * annotations with a given type from a given JCas object. The addressed features are part of
   * direct or indirect feature structure value of a annotation. For example a annotation of type
   * person has a feature address which values are address feature structures with features for the
   * street, postal code and city . To create tokens with postal code and city of a persons address,
   * the featurePath must be &quot;address&quot; and the featureNames &quot;postalCode&quot; and
   * &quot;city&quot;. Each token has the start and end offset of the annotation and uses the
   * concatenation of all the feature values as term text. Optionally the different feature values
   * of an annotation can be concatenated with a delimiter.
   * 
   * @param jCas
   *          the JCas object
   * @param sofaName the name of the Subject of Analysis (sofa)
   * @param typeName
   *          the type of the annotation
   * @param featurePath
   *          the path to the feature structures which features should be used for tokens Path
   *          entries should be separated by &quot;.&quot;. Example:
   *          &quot;affiliation.address.country&quot;
   * @param featureNames
   *          the name of the feature from which the token text is build
   * @param delimiter
   *          a delimiter for concatenating the different feature values of an annotation object. If
   *          null a white space will be used.
   * @param featureFormats
   *          optional map of format objects to convert feature values to strings - the key must be
   *          the feature name
   * @throws InvalidTokenSourceException
   */
  public AnnotationTokenStream(JCas jCas, String sofaName, String typeName, String featurePath,
          List<String> featureNames, String delimiter, Map<String, Format> featureFormats)
          throws InvalidTokenSourceException {
    super();

    this.featurePath = featurePath;
    this.featureNames = featureNames;
    this.delimiter = delimiter;
    if (featureFormats == null)
      this.featureFormats = Collections.emptyMap();
    else
      this.featureFormats = featureFormats;
    
    getSofaCas(jCas, sofaName);
    getTypeForName(typeName);
    validate(annotationType, featureNames, featurePath);
    
    initializeIterators();

  }

  private void getTypeForName(String typeName) throws InvalidTokenSourceException{
    annotationType = jCas.getTypeSystem().getType(typeName);
    if( annotationType == null )
      throw new InvalidTokenSourceException("Type " + typeName + " not found!");
  }

  private void getSofaCas(JCas cas, String sofaName) throws InvalidTokenSourceException {
    try {
      jCas = cas.getView(sofaName);
    } catch (CASException e) {
      throw new InvalidTokenSourceException(e);
    }
  }

  void validate(Type type, Collection<String> featureNames, String featurePath) throws InvalidTokenSourceException{
    Type typeToValidate = findTypeWithPath(type, featurePath);
    
    for( String featureName: featureNames ){
      Feature feature = typeToValidate.getFeatureByBaseName(featureName);
      if( feature == null )
        throw new InvalidTokenSourceException("Type " + typeToValidate.getName() + " has no feature " + featureName + ". featurePath: " + featurePath);
    }
  }
  
  private Type findTypeWithPath(Type type, String featurePath) throws InvalidTokenSourceException{
    if( featurePath == null )
      return type;
    
    String[] featurePathElements = featurePath.split("\\.");
    Type currentType = type;
    
    for( String featurePathElement: featurePathElements ){
      Feature feature = currentType.getFeatureByBaseName(featurePathElement);
      if (feature == null)
        throw new InvalidTokenSourceException("Type " + currentType.getName() + " has no feature " + featurePathElement);
      
      currentType = feature.getRange();
      if (currentType.isArray())
        currentType = currentType.getComponentType();
    }
    
    return currentType;
  }

  @Override
  public Token next(Token token) throws IOException {
    while (!featureValueIterator.hasNext()) {
      while (!featureStructureIterator.hasNext()) {
        if (!annotationIterator.hasNext())
          return null;
        currentAnnotation = (Annotation) annotationIterator.next();
        featureStructureIterator = createFeatureStructureIterator(currentAnnotation, featurePath);
      }

      featureValueIterator = createFeatureValueIterator(featureStructureIterator.next(),
              featureNames);
    }

    token.setStartOffset(currentAnnotation.getBegin());
    token.setEndOffset(currentAnnotation.getEnd());

    char[] value = featureValueIterator.next().toCharArray();
    token.setTermBuffer(value, 0, value.length);
    return token;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.lucene.analysis.TokenStream#next()
   */
  @Override
  public Token next() throws IOException {
    return next(new Token());
  }

  protected void initializeIterators() {
    annotationIterator = Iterators.filter(jCas.getAnnotationIndex(annotationType).iterator(),
            new NotNullPredicate<Annotation>());

    if (!annotationIterator.hasNext()) {
      featureStructureIterator = Iterators.emptyIterator();
      featureValueIterator = Iterators.emptyIterator();
      return;
    }

    currentAnnotation = (Annotation) annotationIterator.next();
    featureStructureIterator = createFeatureStructureIterator(currentAnnotation, featurePath);
    if (!featureStructureIterator.hasNext()) {
      featureValueIterator = Iterators.emptyIterator();
      return;
    }

    FeatureStructure featureStructure = featureStructureIterator.next();
    featureValueIterator = createFeatureValueIterator(featureStructure, featureNames);
  }

  protected Iterator<FeatureStructure> createFeatureStructureIterator(Annotation annotation,
          String featurePath) {
    Collection<FeatureStructure> featureStructures = new LinkedList<FeatureStructure>();
    Collection<FeatureStructure> childs = new LinkedList<FeatureStructure>();

    if (featurePath == null) {
      featureStructures.add(annotation);
      return featureStructures.iterator();
    }

    Type currentType = annotation.getType();
    if (currentType.isArray())
      currentType = currentType.getComponentType();

    String[] pathEntries = featurePath.split("\\.");
    featureStructures.add(annotation);

    for (String pathEntry : pathEntries) {
      Feature feature = currentType.getFeatureByBaseName(pathEntry);
      childs.clear();

      if (feature.getRange().isArray()) {
        for (FeatureStructure featureStructureItem : featureStructures) {
          FSArray fsArray = (FSArray) featureStructureItem.getFeatureValue(feature);
          if (fsArray == null)
            continue;

          for (int i = 0; i < fsArray.size(); i++)
            childs.add(fsArray.get(i));
        }
      } else
        for (FeatureStructure featureStructureItem : featureStructures)
          childs.add(featureStructureItem.getFeatureValue(feature));

      currentType = feature.getRange();
      if (currentType.isArray())
        currentType = currentType.getComponentType();

      featureStructures.clear();
      featureStructures.addAll(childs);
    }

    return Iterators.filter(featureStructures.iterator(), new NotNullPredicate<FeatureStructure>());
  }

  protected Iterator<String> createFeatureValueIterator(FeatureStructure srcFeatureStructure,
          Collection<String> featureNames) {
    List<String> values = new LinkedList<String>();
    Type featureType = srcFeatureStructure.getType();

    if (featureNames.size() == 0)
      values.add(currentAnnotation.getCoveredText());

    for (String featureName : featureNames) {
      Feature feature = featureType.getFeatureByBaseName(featureName);
      if (feature.getRange().isArray()) {
        StringArray fsArray = (StringArray) srcFeatureStructure.getFeatureValue(feature);
        if (featureNames.size() == 1) {
          for (int i = 0; i < fsArray.size(); i++)
            values.add(fsArray.get(i).toString());
        } else {
          String value = "";
          for (int i = 0; i < fsArray.size(); i++) {
            value = value.concat(fsArray.get(i).toString());
            if (i < fsArray.size() - 1)
              value = value.concat(delimiter);
          }
          values.add(value);
        }
      } else
        values.add(getValueForFeature(srcFeatureStructure, feature, featureFormats.get(feature
                .getShortName())));
    }
    String value = "";
    if (delimiter != null) {
      for (int i = 0; i < values.size(); i++) {
        if (values.get(i) == null)
          continue;

        value = value.concat(values.get(i));
        if (i < values.size() - 1)
          value = value.concat(delimiter);
      }
      values.clear();
      values.add(value);
    }

    return Iterators.filter(values.iterator(), new NotNullPredicate<String>());
  }

  public String getValueForFeature(FeatureStructure featureStructure, Feature feature, Format format) {
    if (format == null)
      return featureStructure.getFeatureValueAsString(feature);
    else {
      Object value = null;
      if (feature.getRange().getName().equals(CAS.TYPE_NAME_DOUBLE))
        value = featureStructure.getDoubleValue(feature);
      else if (feature.getRange().getName().equals(CAS.TYPE_NAME_FLOAT))
        value = featureStructure.getFloatValue(feature);
      else if (feature.getRange().getName().equals(CAS.TYPE_NAME_LONG))
        value = featureStructure.getLongValue(feature);
      else if (feature.getRange().getName().equals(CAS.TYPE_NAME_INTEGER))
        value = featureStructure.getIntValue(feature);
      else if (feature.getRange().getName().equals(CAS.TYPE_NAME_SHORT))
        value = featureStructure.getShortValue(feature);

      return format.format(value);
    }
  }

  public void reset() {
    featureStructureIterator = null;
    currentAnnotation = null;
    featureFormats = Collections.emptyMap();
    initializeIterators();
  }

  public Map<String, Format> getFeatureFormats() {
    return featureFormats;
  }

  public JCas getJCas() {
    return jCas;
  }

  public String getFeaturePath() {
    return featurePath;
  }

  public List<String> getFeatureNames() {
    return featureNames;
  }

  public String getDelimiter() {
    return delimiter;
  }

  public Type getAnnotationType() {
    return annotationType;
  }

}
