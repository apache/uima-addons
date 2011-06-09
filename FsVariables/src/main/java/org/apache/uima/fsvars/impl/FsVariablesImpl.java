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

package org.apache.uima.fsvars.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.fsvars.FsVariables;
import org.apache.uima.fsvars.FsVariablesException;

/**
 * TODO: Create type commment.
 */
public class FsVariablesImpl implements FsVariables {

  private static final String messageCatalog = "org.apache.uima.fsvars.messages";

  private CAS cas;

  private FSIndex index;

  private Type fsVariableType;

  private Feature nameFeature;

  private Feature typeFeature;

  private Feature valueFeature;

  public FsVariablesImpl() {
    super();
  }

  public void declareFsVariable(String name, Type type) {
    // If a fsvar of that name already exists, throw an exception.
    if (isFsVariable(name)) {
      throw new FsVariablesException(messageCatalog,
          FsVariablesException.fsvars_variable_already_defined, new Object[] { name });
    }
    // Create the FS.
    FeatureStructure fs = this.cas.createFS(this.fsVariableType);
    // Set name and type features.
    fs.setStringValue(this.nameFeature, name);
    fs.setStringValue(this.typeFeature, type.getName());
    // Add to the index.
    this.cas.addFsToIndexes(fs);
  }

  public Type getFsVariableType(String name) {
    // Try to retrieve the fsvar of "name". Will throw exception if fsvar doesn't exist.
    FeatureStructure fs = getFsVarForName(name);
    // Get the type name, and corresponding type.
    String typeName = fs.getStringValue(this.typeFeature);
    Type type = this.cas.getTypeSystem().getType(typeName);
    // Check that the type name is valid.
    if (type == null) {
      throw new FsVariablesException(messageCatalog,
          FsVariablesException.bad_typename_in_fsvar, new Object[] { typeName, name });
    }
    return type;
  }

  public FeatureStructure getVariableValue(String name) {
    // Try to retrieve the fsvar of "name".
    FeatureStructure fsvar = getFsVarForName(name);
    return fsvar.getFeatureValue(this.valueFeature);
  }

  public boolean isFsVariable(String name) {
    // Get candidate FS.
    FeatureStructure fs = getFsVarNoExc(name);
    // Check that candidate is valid.
    return ((fs != null) && fs.getStringValue(this.nameFeature).equals(name));
  }

  public List listFsVariables() {
    List list = new ArrayList();
    // Iterate over fsvars and add names to list.
    for (FSIterator it = this.index.iterator(); it.isValid(); it.moveToNext()) {
      list.add(it.get().getStringValue(this.nameFeature));
    }
    return list;
  }

  public List listFsVariables(Type type) {
    List list = new ArrayList();
    TypeSystem ts = this.cas.getTypeSystem();
    // Iterate over fsvars and add names to list.
    for (FSIterator it = this.index.iterator(); it.isValid(); it.moveToNext()) {
      String typeName = it.get().getStringValue(this.typeFeature);
      if (ts.subsumes(type, ts.getType(typeName))) {
        list.add(it.get().getStringValue(this.nameFeature));
      }
    }
    return list;
  }

  public void setVariable(String name, FeatureStructure fs) {
    // Get fsvar (must exist).
    FeatureStructure fsvar = getFsVarForName(name);
    // Get type of fsvar.
    Type type = this.cas.getTypeSystem().getType(fsvar.getStringValue(this.typeFeature));
    if (type == null) {
      throw new FsVariablesException(messageCatalog,
          FsVariablesException.bad_typename_in_fsvar, new Object[] {
              fsvar.getStringValue(this.nameFeature), name });
    }
    if (this.cas.getTypeSystem().subsumes(type, fs.getType())) {
      // Set variable value if type of var subsumes type of FS.
      fsvar.setFeatureValue(this.valueFeature, fs);
    } else {
      // If var type does not subsume value type, throw an exception.
      throw new FsVariablesException(messageCatalog,
          FsVariablesException.illegal_fsvar_value, new Object[] { fs.getType().getName(), name,
              type.getName() });
    }
  }

  public void init(CAS cas) {
    // Need to keep the CAS around.
    this.cas = cas;
    if (this.cas == null) {
      throw new NullPointerException("CAS");
    }
    // Get the fsvars index.
    this.index = this.cas.getIndexRepository().getIndex(FsVariables.INDEX_NAME);
    if (this.index == null) {
      throw new FsVariablesException(messageCatalog,
          FsVariablesException.fsvars_index_doesnt_exist, new Object[] { FsVariables.INDEX_NAME });
    }
    // Get the fsvars type.
    this.fsVariableType = this.cas.getTypeSystem().getType(FsVariables.TYPE_NAME);
    if (this.fsVariableType == null) {
      throw new FsVariablesException(messageCatalog,
          FsVariablesException.fsvars_type_doesnt_exist, new Object[] { FsVariables.TYPE_NAME });
    }
    // Get the name, type, and value features.
    this.nameFeature = this.fsVariableType.getFeatureByBaseName(FsVariables.NAME_FEATURE_NAME);
    if (this.nameFeature == null) {
      throw new FsVariablesException(messageCatalog,
          FsVariablesException.fsvars_feature_doesnt_exist,
          new Object[] { FsVariables.NAME_FEATURE_NAME });
    }
    this.typeFeature = this.fsVariableType.getFeatureByBaseName(FsVariables.TYPE_FEATURE_NAME);
    if (this.typeFeature == null) {
      throw new FsVariablesException(messageCatalog,
          FsVariablesException.fsvars_feature_doesnt_exist,
          new Object[] { FsVariables.TYPE_FEATURE_NAME });
    }
    this.valueFeature = this.fsVariableType.getFeatureByBaseName(FsVariables.VALUE_FEATURE_NAME);
    if (this.valueFeature == null) {
      throw new FsVariablesException(messageCatalog,
          FsVariablesException.fsvars_feature_doesnt_exist,
          new Object[] { FsVariables.VALUE_FEATURE_NAME });
    }
  }

  public boolean checkCas(CAS cas) {
    FSIndex variableIndex = cas.getIndexRepository().getIndex(FsVariables.INDEX_NAME);
    if (variableIndex == null) {
      return false;
    }
    TypeSystem ts = cas.getTypeSystem();
    Type type = ts.getType(FsVariables.TYPE_NAME);
    if (type == null) {
      return false;
    }
    if (type.getFeatureByBaseName(FsVariables.NAME_FEATURE_NAME) == null) {
      return false;
    }
    if (type.getFeatureByBaseName(FsVariables.VALUE_FEATURE_NAME) == null) {
      return false;
    }
    return true;
  }

  private final FeatureStructure getFsVarForName(String name) {
    // Get candidate FS.
    FeatureStructure fs = getFsVarNoExc(name);
    // Check that the fsvar FS is valid (not null and correct name)
    if ((fs != null) && fs.getStringValue(this.nameFeature).equals(name)) {
      return fs;
    }
    // If fsvar with "name" exists, throw an exception.
    throw new FsVariablesException(messageCatalog,
        FsVariablesException.no_such_fsvar, new Object[] { name });
  }

  private final FeatureStructure getFsVarNoExc(String name) {
    // Get an iterator over the fsvar index.
    FSIterator it = this.index.iterator();
    // Create a temp FS with which to search for the variable. We do this every time we retrieve
    // a variable, so this is sort of a CAS memory leak as the storage is not reclaimed. The
    // assumption is that annotators won't do this a lot.
    FeatureStructure fs = this.cas.createFS(this.fsVariableType);
    // Set the name of the temp var to the name we're looking for.
    fs.setStringValue(this.nameFeature, name);
    // Move the iterator, hopefully to the already existing var of that name.
    it.moveTo(fs);
    // Get the fsvar.
    if (it.isValid()) {
      return it.get();
    }
    return null;
  }

}
