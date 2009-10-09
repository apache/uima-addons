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
package org.apache.uima.conceptMapper.support.tokens;

import java.util.HashSet;
import java.util.Set;

import org.apache.uima.analysis_engine.annotator.AnnotatorConfigurationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorContext;
import org.apache.uima.analysis_engine.annotator.AnnotatorContextException;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.conceptMapper.Logger;
import org.apache.uima.conceptMapper.support.dictionaryResource.DictionaryToken;

public class TokenFilter {

  private String tokenClassFeatureName;

  private Feature tokenClassFeature = null;

  private String tokenTypeFeatureName;

  private Feature tokenTypeFeature = null;

  private Logger logger;

  /** Configuration parameter for list of token classes to include in lookups */
  public static final String PARAM_INCLUDEDTOKENCLASSES = "IncludedTokenClasses";

  // J5.0 protected Hashtable<String, Boolean> includedTokenClasses;
  protected HashSet<String> includedTokenClasses;

  /** Configuration parameter for list of token classes to include in lookups */
  public static final String PARAM_EXCLUDEDTOKENCLASSES = "ExcludedTokenClasses";

  // J5.0 protected Hashtable<String, Boolean> excludedTokenClasses;
  protected HashSet<String> excludedTokenClasses;

  /** Configuration parameter for list of token classes to include in lookups */
  public static final String PARAM_INCLUDEDTOKENTYPES = "IncludedTokenTypes";

  // J5.0 protected Hashtable<Integer, Boolean> includedTokenTypes;
  protected HashSet<Integer> includedTokenTypes;

  /** Configuration parameter for list of token classes to include in lookups */
  public static final String PARAM_EXCLUDEDTOKENTYPES = "ExcludedTokenTypes";

  // J5.0 protected Hashtable<Integer, Boolean> excludedTokenTypes;
  protected HashSet<Integer> excludedTokenTypes;

  public static final String PARAM_STOPWORDS = "StopWords";

  private Set<String> stopWords = null;

  /** Configuration parameter giving type of tokens */
  public static final String PARAM_TOKENANNOTATION = "TokenAnnotation";

  String tokenAnnotationName = null;

  public TokenFilter(String tokenAnnotationName, String tokenTypeFeatureName,
          String tokenClassFeatureName, Logger logger) {
    super();
    this.tokenAnnotationName = tokenAnnotationName;
    this.tokenTypeFeatureName = tokenTypeFeatureName;
    this.tokenClassFeatureName = tokenClassFeatureName;
    this.logger = logger;
  }

  public String getTokenClassFeatureName() {
    return tokenClassFeatureName;
  }

  public void setTokenClassFeatureName(String tokenClassFeatureName) {
    this.tokenClassFeatureName = tokenClassFeatureName;
  }

  public Feature getTokenClassFeature() {
    return tokenClassFeature;
  }

  public void setTokenClassFeature(Feature tokenClassFeature) {
    this.tokenClassFeature = tokenClassFeature;
  }

  public String getTokenTypeFeatureName() {
    return tokenTypeFeatureName;
  }

  public void setTokenTypeFeatureName(String tokenTypeFeatureName) {
    this.tokenTypeFeatureName = tokenTypeFeatureName;
  }

  public Feature getTokenTypeFeature() {
    return tokenTypeFeature;
  }

  public void setTokenTypeFeature(Feature tokenTypeFeature) {
    this.tokenTypeFeature = tokenTypeFeature;
  }

  public String getTokenAnnotationName() {
    return tokenAnnotationName;
  }

  public void setTokenAnnotationName(String tokenAnnotationName) {
    this.tokenAnnotationName = tokenAnnotationName;
  }

  public void initConfig(AnnotatorContext annotatorContext) throws AnnotatorConfigurationException {

    String[] stopWordList = null;
    String[] includedTokenClassStrings = null;
    String[] excludedTokenClassStrings = null;
    Integer[] includedTokenTypeInts = null;
    Integer[] excludedTokenTypeInts = null;

    try {
      stopWordList = (String[]) annotatorContext.getConfigParameterValue(PARAM_STOPWORDS);
      includedTokenClassStrings = (String[]) annotatorContext
              .getConfigParameterValue(PARAM_INCLUDEDTOKENCLASSES);
      excludedTokenClassStrings = (String[]) annotatorContext
              .getConfigParameterValue(PARAM_EXCLUDEDTOKENCLASSES);
      includedTokenTypeInts = (Integer[]) annotatorContext
              .getConfigParameterValue(PARAM_INCLUDEDTOKENTYPES);
      excludedTokenTypeInts = (Integer[]) annotatorContext
              .getConfigParameterValue(PARAM_EXCLUDEDTOKENTYPES);

      if ((includedTokenClassStrings == null) || (includedTokenClassStrings.length == 0)) {
        includedTokenClasses = null;
      } else {
        includedTokenClasses = new HashSet<String>();
        for (int j = 0; j < includedTokenClassStrings.length; j++) {
          includedTokenClasses.add(includedTokenClassStrings[j]);
        }
      }
      if ((excludedTokenClassStrings == null) || (excludedTokenClassStrings.length == 0)) {
        excludedTokenClasses = null;
      } else {
        excludedTokenClasses = new HashSet<String>();
        for (int j = 0; j < excludedTokenClassStrings.length; j++) {
          excludedTokenClasses.add(excludedTokenClassStrings[j]);
        }
      }

      if ((includedTokenTypeInts == null) || (includedTokenTypeInts.length == 0)) {
        includedTokenTypes = null;
      } else {
        includedTokenTypes = new HashSet<Integer>();
        for (int j = 0; j < includedTokenTypeInts.length; j++) {
          includedTokenTypes.add(includedTokenTypeInts[j]);
        }
      }
      if ((excludedTokenTypeInts == null) || (excludedTokenTypeInts.length == 0)) {
        excludedTokenTypes = null;
      } else {
        excludedTokenTypes = new HashSet<Integer>();
        for (int j = 0; j < excludedTokenTypeInts.length; j++) {
          excludedTokenTypes.add(excludedTokenTypeInts[j]);
        }
      }

      if ((includedTokenClasses != null) || (excludedTokenClasses != null)) {
        if (tokenClassFeatureName == null) {
          throw new AnnotatorConfigurationException();
        }
      }

      if ((includedTokenTypes != null) || (excludedTokenTypes != null)) {
        if (tokenTypeFeatureName == null) {
          throw new AnnotatorConfigurationException();
        }
      }
      stopWords = initializeStopWordList(stopWordList);
    } catch (AnnotatorContextException ie) {
      throw new AnnotatorConfigurationException(ie);
    }

  }

  static public Set<String> initializeStopWordList(String[] stopWordsStrings)
          throws AnnotatorContextException {
    Set<String> retVal = new HashSet<String>();
    if (stopWordsStrings != null) {
      for (int i = 0; i < stopWordsStrings.length; i++) {
        // System.err.println("Adding stopword: '" + stopWordsStrings[i].toLowerCase ());
        retVal.add(stopWordsStrings[i].toLowerCase());
      }
    }
    return retVal;
  }

  /**
   * @param tokenClass
   *          tokenClass to look up
   * @return true if in includedTokenClasses or if both includedTokenClasses and
   *         excludedTokenClasses are unset, of if excludedTokenClasses does not contain an entry
   *         for tokenClass parameter
   */
  public boolean checkTokenClass(AnnotationFS token) {
    boolean returnValue = true;

    if (tokenClassFeature != null) {
      String tokenClass = token.getStringValue(tokenClassFeature);

      if (tokenClass != null) {
        returnValue = isOK_TokenClass(tokenClass);
      }
    }
    // System.err.println ("checkTokenClass, token = " + token.getCoveredText() + ", returnValue: "
    // + returnValue);
    return returnValue;
  }
  
  public boolean checkTokenClass(DictionaryToken token) {
    boolean returnValue = true;

    if (token.isTokenClassFeatureDefined()) {
      String tokenClass = token.getTokenClass();

      if (tokenClass != null) {
        returnValue = isOK_TokenClass(tokenClass);
      }
    }
    // System.err.println ("checkTokenClass, token = " + token.getCoveredText() + ", returnValue: "
    // + returnValue);
    return returnValue;
  }
  
  
  private boolean isOK_TokenClass(String tokenClass) {
    boolean returnValue = true;

    if ((includedTokenClasses != null) && (excludedTokenClasses == null)) {
      returnValue = (includedTokenClasses.contains(tokenClass));
    } else if (excludedTokenClasses != null) {
      returnValue = (!excludedTokenClasses.contains(tokenClass));
    }
    return returnValue;
  }

  static public boolean isStopWord(Set<String> stopWords, String tokenText) {
    // System.err.println("isStopWord, token = '" + tokenText + "', returns = " + ((stopWords !=
    // null) && stopWords.contains (tokenText.toLowerCase ())));
    return ((stopWords != null) && stopWords.contains(tokenText.toLowerCase()));
  }

  public boolean isStopWord(String tokenText) {
    // System.err.println("isStopWord, token = '" + tokenText + "', returns = " + ((stopWords !=
    // null) && stopWords.contains (tokenText.toLowerCase ())));
    return ((stopWords != null) && stopWords.contains(tokenText.toLowerCase()));
  }

  /**
   * @param intValue
   * @return
   */
  public boolean checkTokenType(AnnotationFS token) {
    boolean returnValue = true;

    if (tokenTypeFeature != null) {
      Integer tokenType = Integer.valueOf (token.getIntValue(tokenTypeFeature));

      if (tokenType != null) {
        returnValue = isOK_TokenType(tokenType);
      }
    }
    // System.err.println ("checkTokenType, token = " + token.getCoveredText() + ", returnValue: " +
    // returnValue);
    return returnValue;
  }
  public boolean checkTokenType(DictionaryToken token) {
    boolean returnValue = true;

    if (token.isTokenTypeFeatureDefined()) {
      Integer tokenType = token.getType();

      if (tokenType != null) {
        returnValue = isOK_TokenType(tokenType);
      }
    }
    // System.err.println ("checkTokenType, token = " + token.getCoveredText() + ", returnValue: " +
    // returnValue);
    return returnValue;
  }

  private boolean isOK_TokenType(Integer tokenType) {
    boolean returnValue = true;

    if ((includedTokenTypes != null) && (excludedTokenTypes == null)) {
      returnValue = (includedTokenTypes.contains(tokenType));
    } else if (excludedTokenTypes != null) {
      returnValue = (!excludedTokenTypes.contains(tokenType));
    }
    return returnValue;
  }

  public void initTypes(TypeSystem typeSystem) throws UnknownTypeException {
    initTypes(typeSystem, true);
  }

  /**
   * @param typeSystem
   * @param requireFeatureExistence -
   *          if true, if the tokenType and/or tokenClass features of the tokenAnnotation are
   *          specified, they must exist. This is to allow for the situation where these features
   *          might not exist during dictionary loading, but are needed at annotator runtime
   * @throws UnknownTypeException
   */
  public void initTypes(TypeSystem typeSystem, boolean requireFeatureExistence)
          throws UnknownTypeException {

    Type tokenType = typeSystem.getType(tokenAnnotationName);

    if (tokenType == null) {
      String message = "TokenFilter.initTypes(), Could not find type: " + tokenAnnotationName;
      System.err.println(message);
      throw new UnknownTypeException(message);
    }
    if ((tokenClassFeatureName == null) || (tokenClassFeatureName.equals(""))) {
      tokenClassFeature = null;
    } else {
      tokenClassFeature = tokenType.getFeatureByBaseName(tokenClassFeatureName);
      if ((tokenClassFeature == null) && (requireFeatureExistence)) {
        String message = "Token class feature name '" + tokenClassFeatureName
                + "' specified, but does not exist for type: " + tokenType.getName();
        logger.logError(message);
        throw new UnknownTypeException(message);
      }
    }

    if ((tokenTypeFeatureName == null) || (tokenTypeFeatureName.equals(""))) {
      tokenTypeFeature = null;
    } else {
      tokenTypeFeature = tokenType.getFeatureByBaseName(tokenTypeFeatureName);
      if ((tokenTypeFeature == null) && (requireFeatureExistence)) {
        String message = "Token type feature name '" + tokenTypeFeatureName
                + "' specified, but does not exist for type: " + tokenType.getName();
        logger.logError(message);
        throw new UnknownTypeException(message);
      }
    }
  }

  public boolean isOK_Token(AnnotationFS token, TokenNormalizer tokenNormalizer) {
    if (checkTokenClass(token) && checkTokenType(token)
            && (!isStopWord(tokenNormalizer.normalize(token.getCoveredText())))) {
      return true;
    }
    return false;
  }

  public boolean isOK_Token(DictionaryToken token, TokenNormalizer tokenNormalizer) {
    if (checkTokenClass(token) && checkTokenType(token)
            && (!isStopWord(tokenNormalizer.normalize(token.getText())))) {
      // System.err.println ("isOK_Token = true");
      return true;
    }
    // System.err.println ("isOK_Token = false");
    return false;
  }

}
