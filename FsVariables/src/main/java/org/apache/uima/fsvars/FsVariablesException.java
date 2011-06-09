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


package org.apache.uima.fsvars;

import org.apache.uima.UIMARuntimeException;

/**
 * Exceptions for fsvars package.
 */
public class FsVariablesException extends UIMARuntimeException {
  
  public static final String fsvars_index_doesnt_exist = "fsvars_index_doesnt_exist";
  
  public static final String fsvars_type_doesnt_exist = "fsvars_type_doesnt_exist";
  
  public static final String fsvars_feature_doesnt_exist = "fsvars_feature_doesnt_exist";
  
  public static final String fsvars_variable_already_defined = "fsvars_variable_already_defined";
  
  public static final String no_such_fsvar = "no_such_fsvar";
  
  public static final String bad_typename_in_fsvar = "bad_typename_in_fsvar";
  
  public static final String illegal_fsvar_value = "illegal_fsvar_value";

  public FsVariablesException() {
    super();
  }

  public FsVariablesException(Throwable arg0) {
    super(arg0);
  }

  public FsVariablesException(String arg0, Object[] arg1) {
    super(arg0, arg1);
  }

  public FsVariablesException(String arg0, String arg1, Object[] arg2) {
    super(arg0, arg1, arg2);
  }

  public FsVariablesException(String arg0, Object[] arg1, Throwable arg2) {
    super(arg0, arg1, arg2);
  }

  public FsVariablesException(String arg0, String arg1, Object[] arg2, Throwable arg3) {
    super(arg0, arg1, arg2, arg3);
  }

}
