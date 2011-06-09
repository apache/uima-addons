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
package org.apache.uima.conceptMapper.support.dictionaryResource;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.text.AnnotationFS;

public class DictionaryToken {

  private String text;
  private Integer type;
  private String tokenClass;

  private boolean tokenTypeFeatureDefined;
  private boolean tokenClassFeatureDefined;
  
  
  public DictionaryToken(AnnotationFS annotation, Feature tokenTypeFeature, Feature tokenClassFeature) {
    super();
    if (tokenTypeFeature == null)
    {
      this.setTokenTypeFeatureDefined(false);
    }
    else
    {
      this.setTokenTypeFeatureDefined(true);
      this.setType(annotation.getIntValue(tokenTypeFeature));
    }
    if (tokenClassFeature == null)
    {
      this.setTokenClassFeatureDefined(false);
    }
    else
    {
      this.setTokenClassFeatureDefined(true);
      this.setTokenClass(annotation.getStringValue(tokenClassFeature));
    }
    this.setText(annotation.getCoveredText());
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getTokenClass() {
    return tokenClass;
  }

  public void setTokenClass(String tokenClass) {
    this.tokenClass = tokenClass;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public boolean isTokenTypeFeatureDefined() {
    return tokenTypeFeatureDefined;
  }

  public void setTokenTypeFeatureDefined(boolean tokenTypeFeatureDefined) {
    this.tokenTypeFeatureDefined = tokenTypeFeatureDefined;
  }

  public boolean isTokenClassFeatureDefined() {
    return tokenClassFeatureDefined;
  }

  public void setTokenClassFeatureDefined(boolean tokenClassFeatureDefined) {
    this.tokenClassFeatureDefined = tokenClassFeatureDefined;
  }

}
