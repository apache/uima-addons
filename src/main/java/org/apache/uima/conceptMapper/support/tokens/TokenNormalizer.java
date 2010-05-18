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

import java.util.regex.Pattern;

import org.apache.uima.analysis_engine.annotator.AnnotatorContext;
import org.apache.uima.analysis_engine.annotator.AnnotatorContextException;
import org.apache.uima.conceptMapper.Logger;
import org.apache.uima.conceptMapper.support.stemmer.Stemmer;

public class TokenNormalizer {
  private static Pattern CapPat = Pattern.compile("^[A-Z][a-z]+$");

  private static Pattern HasDigit = Pattern.compile("[0-9]");

  /**
   * replace instances of "," with the token "and" defaults to false
   */
  private static final String PARAM_REPLACE_COMMA_WITH_AND = "ReplaceCommaWithAND";

  /** Configuration parameter key/label for the case matching string */
  public static final String PARAM_CASE_MATCH = "caseMatch";

  /** Configuration parameter key/label for the stemmer class spec. If left out, no stemmer is used */
  public static final String PARAM_STEMMER_CLASS = "Stemmer";

  /**
   * Configuration parameter key/label for the stemmer dictionary, passed into the stemmer's
   * initialization method
   */
  public static final String PARAM_STEMMER_DICT = "StemmerDictionary";

  private boolean caseFoldAll;

  private boolean caseFoldInitCap;

  private boolean caseFoldDigit;

  private String CASE_INSENSITIVE = "insensitive";

  private String CASE_FOLD_DIGITS = "digitfold";

  private String CASE_IGNORE = "ignoreall";

  /** The stemmer that will perform the stemming. */
  private Stemmer stemmer = null;

  private boolean replaceCommaWithAND;

  /**
   * @param annotatorContext
   * @param logger
   * @throws AnnotatorContextException
   */
  public TokenNormalizer(AnnotatorContext annotatorContext, Logger logger)
          throws AnnotatorContextException {
    super();
    Boolean replaceCommaWithANDObj = (Boolean) annotatorContext
            .getConfigParameterValue(PARAM_REPLACE_COMMA_WITH_AND);
    boolean replaceCommaWithAND = false;
    if (replaceCommaWithANDObj != null) {
      replaceCommaWithAND = replaceCommaWithANDObj.booleanValue();
    }
    String caseMatchParam = (String) annotatorContext.getConfigParameterValue(PARAM_CASE_MATCH);
    String stemmerParam = (String) annotatorContext.getConfigParameterValue(PARAM_STEMMER_CLASS);
    String stemmerDict = (String) annotatorContext.getConfigParameterValue(PARAM_STEMMER_DICT);

    this.replaceCommaWithAND = replaceCommaWithAND;
    this.setCaseFoldInitCap(false);
    this.setCaseFoldDigit(false);
    this.setCaseFoldAll(false);

    if (caseMatchParam != null) {
      if (caseMatchParam.equalsIgnoreCase(CASE_INSENSITIVE)) {
        this.setCaseFoldInitCap(true);
      } else if (caseMatchParam.equalsIgnoreCase(CASE_FOLD_DIGITS)) {
        this.setCaseFoldDigit(true);
      } else if (caseMatchParam.equalsIgnoreCase(CASE_IGNORE)) {
        this.setCaseFoldAll(true);
      }
    }

    if (stemmerParam != null) {
      try {
        Class<?> stemmerClass = Class.forName(stemmerParam);
        setStemmer((Stemmer) stemmerClass.newInstance());
        getStemmer().initialize(stemmerDict);
      } catch (Exception e) {
        logger.logError("Exception trying to instantiate stemmer class: '" + stemmerParam
                + "', original exception:" + e.getMessage());
        e.printStackTrace();
      }
    }
  }

  /**
   * @return Returns the stemmer.
   */
  public Stemmer getStemmer() {
    return stemmer;
  }

  /**
   * @param stemmer
   *          The stemmer to set.
   */
  public void setStemmer(Stemmer stemmer) {
    this.stemmer = stemmer;
  }

  public boolean shouldStem() {
    return (getStemmer() != null);
  }

  /**
   * @return Returns the caseFoldAll.
   */
  public boolean isCaseFoldAll() {
    return caseFoldAll;
  }

  /**
   * @param caseFoldAll
   *          The caseFoldAll to set.
   */
  public void setCaseFoldAll(boolean caseFoldAll) {
    this.caseFoldAll = caseFoldAll;
  }

  /**
   * @return Returns the caseFoldDigit.
   */
  public boolean isCaseFoldDigit() {
    return caseFoldDigit;
  }

  /**
   * @param caseFoldDigit
   *          The caseFoldDigit to set.
   */
  public void setCaseFoldDigit(boolean caseFoldDigit) {
    this.caseFoldDigit = caseFoldDigit;
  }

  /**
   * @return Returns the caseFoldInitCap.
   */
  public boolean isCaseFoldInitCap() {
    return caseFoldInitCap;
  }

  /**
   * @param caseFoldInitCap
   *          The caseFoldInitCap to set.
   */
  public void setCaseFoldInitCap(boolean caseFoldInitCap) {
    this.caseFoldInitCap = caseFoldInitCap;
  }

  public boolean shouldFoldCase(String token) {
    return (caseFoldAll || (caseFoldInitCap && CapPat.matcher(token).matches()) || (caseFoldDigit && HasDigit
            .matcher(token).find()));
  }

  /**
   * If one of the case folding flags is true and the input string matches the character pattern
   * corresponding to that flag, then convert all letters to lowercase.
   * 
   * @param token
   *          The string to case fold
   * 
   * @return The case folded string
   */
  public String foldCase(String token) {
    if (shouldFoldCase(token)) {
      return token.trim().toLowerCase();
    }
    return token;
  }

  /**
   * If the stemming flag is true, then return the stemmed form of the supplied word using the
   * Porter stemmer.
   * 
   * @param token
   *          the word to stem
   * @return the original word if the stemming flag is false, otherwise the stemmed form of the word
   */
  public String stem(String token) {
    if (shouldStem()) {
      return getStemmer().stem(token.trim());
    }
    return token;
  }

  public String normalize(String token) {
    if (replaceCommaWithAND && token.equals(",")) {
      return stem(foldCase("and"));
    }
    return stem(foldCase(token));
  }
}
