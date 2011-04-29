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

package org.apache.uima.conceptMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.uima.analysis_engine.ResultSpecification;
import org.apache.uima.analysis_engine.annotator.AnnotatorConfigurationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorContext;
import org.apache.uima.analysis_engine.annotator.AnnotatorInitializationException;
import org.apache.uima.analysis_engine.annotator.AnnotatorProcessException;
import org.apache.uima.analysis_engine.annotator.Annotator_ImplBase;
import org.apache.uima.analysis_engine.annotator.TextAnnotator;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.cas.CAS;
import org.apache.uima.conceptMapper.support.dictionaryResource.DictionaryResource;
import org.apache.uima.conceptMapper.support.dictionaryResource.EntryProperties;
import org.apache.uima.conceptMapper.support.dictionaryResource.DictionaryResource.DictEntry;
import org.apache.uima.conceptMapper.support.tokens.TokenFilter;
import org.apache.uima.conceptMapper.support.tokens.TokenNormalizer;
import org.apache.uima.conceptMapper.support.tokens.UnknownTypeException;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

public class ConceptMapper extends Annotator_ImplBase implements TextAnnotator {

  /** Configuration parameter key/label for the dictionary file to load */
  public static final String PARAM_DICT_FILE = "DictionaryFile";

  /**
   * Configuration parameter for name of token class feature of token annotations, to distinguish
   * classes of tokens to skip during lookups. Token class features are Strings.
   */
  public static final String PARAM_TOKENCLASSFEATURENAME = "TokenClassFeatureName";

  private String tokenClassFeatureName;

  /**
   * Configuration parameter for name of token type feature of token annotations, to distinguish
   * types of tokens to skip during lookups. Token type features are Integers
   */
  public static final String PARAM_TOKENTYPEFEATURENAME = "TokenTypeFeatureName";

  private String tokenTypeFeatureName;

  /** Configuration parameter key/label for the annotation name */
  public static final String PARAM_ANNOTATION_NAME = "ResultingAnnotationName";

  /**
   * Configuration parameter key/label for the name of the feature that contains the resulting
   * term's span, i.e. sentence
   */
  public static final String PARAM_ENCLOSINGSPAN = "ResultingEnclosingSpanName";

  private String resultEnclosingSpanName;

  private Feature resultEnclosingSpan;

  /**
   * Configuration parameter feature in resulting annotation to store text matched in successful
   * dict lookup
   */
  public static final String PARAM_MATCHEDFEATURE = "ResultingAnnotationMatchedTextFeature";

  private String resultMatchedTextFeatureName;

  private Feature resultMatchedTextFeature;

  /** Configuration parameter key/label for the attribute list */
  public static final String PARAM_ATTRIBUTE_LIST = "AttributeList";

  /** Configuration parameter key/label for the feature list */
  public static final String PARAM_FEATURE_LIST = "FeatureList";

  /** Configuration parameter giving type of tokens */
  public static final String PARAM_TOKENANNOTATION = "TokenAnnotation";

  private String tokenAnnotationName;

  /**
   * Configuration parameter specifying name of token's feature containing text. If not specified,
   * the token annotation's covered text is used
   */
  public static final String PARAM_TOKENTEXTFEATURENAME = "TokenTextFeatureName";

  private String tokenTextFeatureName;

  private Feature tokenTextFeature;

  /**
   * array of features of the token annotation which should be written back to the token from the
   * resulting entry. For example, if a Part of Speech is specified as part of a dictionary entry,
   * it could be written back to the token so that a subsequent POS tagger would be able to use it
   * as a preannotated item.
   */
  public static final String PARAM_TOKENCLASSWRITEBACKFEATURENAMES = "TokenClassWriteBackFeatureNames";

  private String[] tokenClassWriteBackFeatureNames;

  private Feature[] tokenClassWriteBackFeatures;

  /**
   * Configuration parameter for name of feature in result annotations to contain list of matched
   * tokens
   */
  public static final String PARAM_MATCHEDTOKENSFEATURENAME = "MatchedTokensFeatureName";

  private String matchedTokensFeatureName;

  private Feature matchedTokensFeature;

  /**
   * Configuration parameter key/label to indicate if order-independent lookup is to be performed.
   * If true, words in a phrase are sorted alphabetically before lookup. This implies that a phrase
   * "C D A" would be considered equivalent to "A C D" and "D A C", etc.
   */
  public static final String PARAM_ORDERINDEPENDENTLOOKUP = "OrderIndependentLookup";

  private boolean sortElements;

  private final static int ContiguousMatch = 1;

  public static final String PARAMVALUE_CONTIGUOUSMATCH = "ContiguousMatch";

  private final static int SkipAnyMatch = 2;

  public static final String PARAMVALUE_SKIPANYMATCH = "SkipAnyMatch";

  private static final int SkipAnyMatchAllowOverlap = 3;

  public static final String PARAMVALUE_SKIPANYMATCHALLOWOVERLAP = "SkipAnyMatchAllowOverlap";

  private final static int DefaultSearchStrategy = ContiguousMatch;

  // private final static int DefaultSearchStrategy = SkipAnyMatch;

  /**
   * Configuration parameter to indicate search strategy, either: LongestMatch: longest match of
   * contiguous tokens within enclosing span(taking into account included/excluded items). DEFAULT
   * strategy SkipAnyMatch: longest match of noncontiguous tokens enclosing span (taking into
   * account included/excluded items). IMPLIES order-independent lookup
   */
  public static final String PARAM_SEARCHSTRATEGY = "SearchStrategy";

  private int searchStrategy = DefaultSearchStrategy;

  public static final String PARAM_FINDALLMATCHES = "FindAllMatches";

  private boolean findAllMatches;

  /** object used to stem/case normalize text */
  private TokenNormalizer tokenNormalizer;

  private TokenFilter tokenFilter;

  /** The name of the annotation type posted to the CAS by this TAE */
  protected String resultAnnotationName;

  /** The type of annotation posted to the CAS by this TAE */
  protected Type resultAnnotationType;

  /** The type of token annotations to consider */
  protected Type tokenType;

  /**
   * Array of Feature objects associated with {link #annotationType annotationType}
   */
  protected Feature features[];

  /** Array of feature names, obtained as a configuration parameter. */
  protected String featureNames[];

  /**
   * Array of attribute names for the XML dictionary token element, obtained as a configuration
   * parameter.
   */
  protected String attributeNames[];

  /** The dictionary */
  private DictionaryResource dict;

  /**
   * type of annotation that defines a block for processing, e.g. a sentence
   */
  private static final String PARAM_DATA_BLOCK_FS = "SpanFeatureStructure";

  private String spanFeatureStructureName;

  private Type spanFeatureStructureType;

  private Logger logger;

  private JCas jcas;

  private static final String PARAM_TOKENIZERDESCRIPTOR = "TokenizerDescriptorPath";

  private static final String UNKNOWN_VALUE = "unknown";

  // private FileWriter tokenDebugFile;
  // private FileWriter potentialMatchDebugFile;
  // private FileWriter findMatchDebugFile;

  // private void debugWrite (FileWriter file, String text)
  // {
  // try
  // {
  // file.write(text + "\n");
  // }
  // catch (Exception e)
  // {
  // }
  // }

  /**
   * Initialize the annotator, which includes compilation of regular expressions, fetching
   * configuration parameters from XML descriptor file, and loading of the dictionary file.
   */
  public void initialize(AnnotatorContext annotatorContext) throws AnnotatorConfigurationException,
          AnnotatorInitializationException {
    super.initialize(annotatorContext);

    // Process configration parameters
    try {
      // logger = new Logger (annotatorContext.getLogger ());
      logger = new Logger("ConceptMapper", annotatorContext.getLogger());
      // tokenDebugFile = new FileWriter("/tmp/cm/tokens."+
      // Calendar.getInstance ().getTimeInMillis () + ".txt");
      // potentialMatchDebugFile = new FileWriter("/tmp/cm/pm."+
      // Calendar.getInstance ().getTimeInMillis () + ".txt");
      // findMatchDebugFile = new FileWriter("/tmp/cm/fm."+
      // Calendar.getInstance ().getTimeInMillis () + ".txt");
      // FileWriter dictDebugFile = new FileWriter("/tmp/cm/dict."+
      // Calendar.getInstance ().getTimeInMillis () + ".txt");

      tokenAnnotationName = (String) annotatorContext
              .getConfigParameterValue(PARAM_TOKENANNOTATION);
      String tokenizerDescriptor = (String) annotatorContext
              .getConfigParameterValue(PARAM_TOKENIZERDESCRIPTOR);

      tokenClassFeatureName = (String) annotatorContext
              .getConfigParameterValue(PARAM_TOKENCLASSFEATURENAME);

      tokenTypeFeatureName = (String) annotatorContext
              .getConfigParameterValue(PARAM_TOKENTYPEFEATURENAME);

      resultAnnotationName = (String) annotatorContext
              .getConfigParameterValue(PARAM_ANNOTATION_NAME);
      resultEnclosingSpanName = (String) annotatorContext
              .getConfigParameterValue(PARAM_ENCLOSINGSPAN);

      resultMatchedTextFeatureName = (String) annotatorContext
              .getConfigParameterValue(PARAM_MATCHEDFEATURE);

      featureNames = (String[]) annotatorContext.getConfigParameterValue(PARAM_FEATURE_LIST);
      attributeNames = (String[]) annotatorContext.getConfigParameterValue(PARAM_ATTRIBUTE_LIST);

      spanFeatureStructureName = (String) annotatorContext
              .getConfigParameterValue(PARAM_DATA_BLOCK_FS);

      tokenTextFeatureName = (String) annotatorContext
              .getConfigParameterValue(PARAM_TOKENTEXTFEATURENAME);
      tokenClassWriteBackFeatureNames = (String[]) annotatorContext
              .getConfigParameterValue(PARAM_TOKENCLASSWRITEBACKFEATURENAMES);

      tokenAnnotationName = (String) annotatorContext
              .getConfigParameterValue(PARAM_TOKENANNOTATION);

      matchedTokensFeatureName = (String) annotatorContext
              .getConfigParameterValue(PARAM_MATCHEDTOKENSFEATURENAME);

      Boolean sortElementsParam = (Boolean) annotatorContext
              .getConfigParameterValue(PARAM_ORDERINDEPENDENTLOOKUP);
      sortElements = (sortElementsParam == null) ? false : sortElementsParam.booleanValue();

      searchStrategy = detectSearchStrategy((String) annotatorContext
              .getConfigParameterValue(PARAM_SEARCHSTRATEGY));
      // System.err.println("SEARCH STRATEGY = " + searchStrategy);

      Boolean findAllMatchesParam = (Boolean) annotatorContext
              .getConfigParameterValue(PARAM_FINDALLMATCHES);
      findAllMatches = (findAllMatchesParam == null) ? false : findAllMatchesParam.booleanValue();

      // always do order-independent lookup if performing "SkipAnyMatch"
      // lookups
      if (searchStrategy == SkipAnyMatch) {
        sortElements = true;
      }

      if (featureNames.length != attributeNames.length) {
        throw new Exception("AttributeList and FeatureList are inconsistent");
      }
      // for (int i = 0; i < featureNames.length; i++ )
      // {
      // logger.logInfo ("Attribute \"" + attributeNames [i] + "\" mapped
      // to feature \"" + featureNames [i] + "\"");
      // }

      tokenNormalizer = new TokenNormalizer(annotatorContext, logger);
      tokenFilter = new TokenFilter(tokenAnnotationName, tokenTypeFeatureName,
              tokenClassFeatureName, logger);
      tokenFilter.initConfig(annotatorContext);

      dict = (DictionaryResource) annotatorContext.getResourceObject(PARAM_DICT_FILE);
      if (!dict.isLoaded()) {
        // logger.logInfo("dictionary not yet loaded");
        dict.loadDictionaryContents(annotatorContext, logger, tokenAnnotationName,
                tokenTypeFeatureName, tokenClassFeatureName, tokenizerDescriptor);
        // logger.logInfo( "now is loaded: "+dict.toString() );
        // System.err.println ("NEW DICTIONARY:\n" + dict.toString());
        // debugWrite (dictDebugFile, dict.toString());
      }

    } catch (Exception e) {
      throw new AnnotatorConfigurationException(e);
    }
  }

  private int detectSearchStrategy(String strategyString) throws AnnotatorConfigurationException {
    if ((strategyString == null) || (strategyString.equals(""))) {
      return DefaultSearchStrategy;
    } else if (strategyString.equals(PARAMVALUE_CONTIGUOUSMATCH)) {
      return ContiguousMatch;
    } else if (strategyString.equals(PARAMVALUE_SKIPANYMATCH)) {
      return SkipAnyMatch;
    } else if (strategyString.equals(PARAMVALUE_SKIPANYMATCHALLOWOVERLAP)) {
      return SkipAnyMatchAllowOverlap;
    } else {
      throw new AnnotatorConfigurationException();
    }
  }

  /**
   * Perform local type system initialization.
   * 
   * @param typeSystem
   *          the current type system.
   * @see org.apache.uima.analysis_engine.annotator.TextAnnotator#typeSystemInit(TypeSystem)
   */
  public void typeSystemInit(TypeSystem typeSystem) throws AnnotatorConfigurationException,
          AnnotatorInitializationException {

    tokenType = typeSystem.getType(tokenAnnotationName);
    if (tokenType == null) {
      logger.logError(PARAM_TOKENANNOTATION + " '" + tokenAnnotationName
              + "' specified, but does not exist");
      throw new AnnotatorInitializationException();
    }

    if ((tokenTextFeatureName == null) || (tokenTextFeatureName.equals(""))) {
      tokenTextFeature = null;
    } else {
      tokenTextFeature = tokenType.getFeatureByBaseName(tokenTextFeatureName);
      if (tokenTextFeature == null) {
        logger.logError(PARAM_TOKENTEXTFEATURENAME + " '" + tokenTextFeatureName
                + "' specified, but does not exist for type: " + tokenType.getName());
        throw new AnnotatorInitializationException();
      }
    }

    if ((tokenClassWriteBackFeatureNames != null) && (tokenClassWriteBackFeatureNames.length > 0)) {
      tokenClassWriteBackFeatures = new Feature[tokenClassWriteBackFeatureNames.length];
      for (int i = 0; i < tokenClassWriteBackFeatureNames.length; i++) {
        tokenClassWriteBackFeatures[i] = tokenType
                .getFeatureByBaseName(tokenClassWriteBackFeatureNames[i]);
        if (tokenClassWriteBackFeatures[i] == null) {
          logger.logError(PARAM_TOKENCLASSWRITEBACKFEATURENAMES + "[" + i + "] '"
                  + tokenClassWriteBackFeatureNames[i]
                  + "' specified, but does not exist for type: " + tokenType.getName());
          throw new AnnotatorInitializationException();
        }
      }
    } else {
      tokenClassWriteBackFeatures = null;
    }

    spanFeatureStructureType = typeSystem.getType(spanFeatureStructureName);
    if (spanFeatureStructureType == null) {
      logger.logError(PARAM_DATA_BLOCK_FS + " '" + spanFeatureStructureName
              + "' specified, but does not exist for type: " + tokenType.getName());
      throw new AnnotatorInitializationException();
    }

    resultAnnotationType = typeSystem.getType(resultAnnotationName);
    if (resultAnnotationType == null) {
      logger.logError(PARAM_ANNOTATION_NAME + " '" + resultAnnotationName
              + "' specified, but does not exist");
      throw new AnnotatorInitializationException();
    }

    if ((resultEnclosingSpanName == null) || (resultEnclosingSpanName.equals(""))) {
      resultEnclosingSpan = null;
    } else {
      resultEnclosingSpan = resultAnnotationType.getFeatureByBaseName(resultEnclosingSpanName);
      if (resultEnclosingSpan == null) {
        logger.logError(PARAM_ENCLOSINGSPAN + " '" + resultEnclosingSpanName
                + "' specified, but does not exist for type: " + resultAnnotationType.getName());
        throw new AnnotatorInitializationException();
      }
    }

    if ((resultMatchedTextFeatureName == null) || (resultMatchedTextFeatureName.equals(""))) {
      resultMatchedTextFeature = null;
    } else {
      resultMatchedTextFeature = resultAnnotationType
              .getFeatureByBaseName(resultMatchedTextFeatureName);
      if (resultMatchedTextFeature == null) {
        logger.logError(PARAM_MATCHEDFEATURE + " '" + resultMatchedTextFeatureName
                + "' specified, but does not exist for type: " + resultAnnotationType.getName());
        throw new AnnotatorInitializationException();
      }
    }

    if ((matchedTokensFeatureName == null) || (matchedTokensFeatureName.equals(""))) {
      matchedTokensFeature = null;
    } else {
      matchedTokensFeature = resultAnnotationType.getFeatureByBaseName(matchedTokensFeatureName);
      if (matchedTokensFeature == null) {
        logger.logError(PARAM_MATCHEDTOKENSFEATURENAME + " '" + matchedTokensFeatureName
                + "' specified, but does not exist for type: " + resultAnnotationType.getName());
        throw new AnnotatorInitializationException();
      }
    }

    int numFeatures = featureNames.length;
    features = new Feature[numFeatures];

    for (int i = 0; i < numFeatures; i++) {
      features[i] = resultAnnotationType.getFeatureByBaseName(featureNames[i]);
      if (features[i] == null) {
        logger.logError(PARAM_FEATURE_LIST + "[" + i + "] '" + featureNames[i]
                + "' specified, but does not exist for type: " + resultAnnotationType.getName());
        // System.err.println (PARAM_FEATURE_LIST + "[" + i + "] '" +
        // featureNames[i] + "' specified, but does not exist for type:
        // " + resultAnnotationType.getName());
        throw new AnnotatorInitializationException();
      }

    }

    try {
      tokenFilter.initTypes(typeSystem);
    } catch (UnknownTypeException e) {
      throw new AnnotatorInitializationException(e);
    }
  }

  /**
   * Perform the actual analysis. Iterate over the document content looking for any matching words
   * or phrases in the loaded dictionary and post an annotation for each match found.
   * 
   * @param tcas
   *          the current CAS to process.
   * @param aResultSpec
   *          a specification of the result annotation that should be created by this annotator
   * 
   * @see org.apache.uima.analysis_engine.annotator.TextAnnotator#process(CAS,ResultSpecification)
   */
  public void process(CAS tcas, ResultSpecification aResultSpec) throws AnnotatorProcessException {
    // System.err.println ("ConceptMapper.process() begin");

    AnnotationFS token;

    try {
      setJCas(tcas.getJCas()); // this is needed to get around an issue
      // where UIMA crashes if no JCas is
      // referenced
      // logger.setupDocument (getJCas ());

      FSIndex dbIndex = tcas.getAnnotationIndex(spanFeatureStructureType);
      FSIterator spanIterator = dbIndex.iterator();

      AnnotationIndex tokenIndex = (AnnotationIndex) tcas.getAnnotationIndex(tokenType);

      while (spanIterator.hasNext()) {
        ArrayList<AnnotationFS> tokens = new ArrayList<AnnotationFS>(2048);

        Annotation spanAnnotation = (Annotation) spanIterator.next();

        FSIterator tokenIter = tokenIndex.subiterator(spanAnnotation);

        // System.err.println ("Tokens:");

        // get all tokens for the specified block
        while (tokenIter.hasNext()) {
          token = (AnnotationFS) tokenIter.next();
          // System.err.print ("--> token: '" + token.getCoveredText()
          // + "' ");
          if (tokenFilter.isOK_Token(token, tokenNormalizer)) {
            // System.err.println("--> ADDING token: " +
            // token.getCoveredText());
            // debugWrite(tokenDebugFile, "--> ADDING token: " +
            // token.getCoveredText() + ", type: " +
            // token.getIntValue (tokenTypeFeature) + ", checkType:
            // " + checkTokenType (token));

            tokens.add(token);
          }
          // else
          // {
          // System.err.println("-->NOT! ADDING token: " +
          // token.getCoveredText());
          // debugWrite(tokenDebugFile, "-->NOT! ADDING token: " +
          // token.getCoveredText() + ", type: " + token.getIntValue
          // (tokenTypeFeature) + ", checkType: " + checkTokenType
          // (token));
          // }
        }
        // System.err.println ();
        // logger.logInfo("Number of tokens: " + tokens.size());

        switch (searchStrategy) {
          case SkipAnyMatch:
          case SkipAnyMatchAllowOverlap:
            processTokenListSkipAny(searchStrategy, findAllMatches, tcas, tokens, spanAnnotation);
            break;
          case ContiguousMatch:
            processTokenList(searchStrategy, findAllMatches, tcas, tokens, spanAnnotation);
            break;
          default:
            processTokenList(searchStrategy, findAllMatches, tcas, tokens, spanAnnotation);
            break;
        }

      }
      // logger.logFinest("Number of annotations in CAS: " +
      // (tcas.getAnnotationIndex().size() - 1));
      // System.out.println("Number of annotations in CAS: " +
      // (tcas.getAnnotationIndex().size() - 1));
    } catch (Exception e) {
      throw new AnnotatorProcessException(e);
    }
    // System.err.println ("ConceptMapper.process() end");
  }

  private void setJCas(JCas jcas) {
    this.jcas = jcas;
  }

  private JCas getJCas() {
    return this.jcas;
  }

  private void processTokenListSkipAny(int searchStrategy, boolean findAllMatches, CAS tcas,
          ArrayList<AnnotationFS> tokens, Annotation spanAnnotation) {
    AnnotationFS token;
    // iterate over vector of tokens

    ArrayList<String> normalizedTokens = new ArrayList<String>();

    // iterate through all tokens within span and collect dict entries for each unique one
    for (int whichToken = 0; whichToken < tokens.size(); whichToken++) {
      token = tokens.get(whichToken);
      String tokenText = getTokenText(token);

      String word = tokenNormalizer.normalize(tokenText);
      normalizedTokens.add(word);

      // logger.logInfo("ENTRY SEARCH/ORIGINAL: " + word + " / " +
      // tokenText);
      // System.err.println("ENTRY SEARCH/ORIGINAL: " + word + " / " +
      // tokenText);
    }

    // System.err.println ("processTokenListSkipAny finding matches for " +
    // normalizedTokens.toString ());

    findMatchesSkipAnyToken(searchStrategy, findAllMatches, tcas, tokens, normalizedTokens,
                            findPotentialEntries(normalizedTokens, dict), spanAnnotation);
  }

  private Map<String, Collection<DictEntry>> findPotentialEntries(
          ArrayList<String> normalizedTokens, DictionaryResource dict) {
    HashMap<String, Collection<DictEntry>> potentialEntries = new HashMap<String, Collection<DictEntry>>();

    Iterator<String> tokenIter = normalizedTokens.iterator();
    while (tokenIter.hasNext()) {
      String word = tokenIter.next();
      Collection<DictEntry> entries = potentialEntries.get(word);

      if (entries == null) {
        entries = new ArrayList<DictEntry>();
      }
      DictionaryResource.DictEntriesByLength entriesByLength = dict.getEntries(word);
      if (entriesByLength != null) {
        int shortest = entriesByLength.getShortest().intValue();
        int longest = entriesByLength.getLongest().intValue();
        for (int currentLength = longest; currentLength >= shortest; currentLength--) {
          DictionaryResource.DictEntries dictEntries = entriesByLength.getEntries(currentLength);
          if (dictEntries != null) {
            ArrayList<DictEntry> entryItems = dictEntries.getEntries();
            Iterator<DictEntry> entryIter = entryItems.iterator();
            while (entryIter.hasNext()) {
              DictionaryResource.DictEntry entry = (DictionaryResource.DictEntry) entryIter.next();
              // System.err.println("entryIter = " + entryIter +
              // ", Entry: " + entry.getText ());
              // debugWrite (potentialMatchDebugFile, "Entry: " +
              // entry.getText ());
              if ((containsAll (normalizedTokens, entry.getElements())) && (!entries.contains(entry))) {
                entries.add(entry);
                // System.err.println ("Added potential match: "
                // + entry);
                // debugWrite (potentialMatchDebugFile, "Added
                // potential match: " + entry);
              }
            }
          }
        }
      }

      potentialEntries.put(word, entries);

    }
    return potentialEntries;
  }

  private boolean containsAll (List<String> container, String[] contained)
  {      
      for (String item : contained)
      {
          if (! container.contains (item))
          {
              return false;
          }
      }
      return true;
  }
  /**
   * @param searchStrategy
   * @param tcas
   * @param tokens
   *          list of token annotations
   * @param normalizedTokens
   *          list of token annotations as strings
   * @param potentialEntries
   *          list of possible matches from dictionary
   * @param spanAnnotation
   */
  private void findMatchesSkipAnyToken(int searchStrategy, boolean findAllMatches, CAS tcas,
          ArrayList<AnnotationFS> tokens, ArrayList<String> normalizedTokens,
          Map<String, Collection<DictEntry>> potentialEntries, Annotation spanAnnotation) {
    int whichToken = 0; // use index instead of iterator to simplify walking
    // through parallel arrays (tokens/normalizedTokens)

    while (whichToken < normalizedTokens.size()) {
      // System.err.println ("findMatchesSkipAnyToken(), whichToken = " +
      // whichToken + ", token: " + (String) normalizedTokens.get
      // (whichToken));
      Collection<DictEntry> entries = potentialEntries.get(normalizedTokens.get(whichToken));
      if (entries == null) {
        whichToken += 1;
      } else {
        Iterator<DictEntry> entryIter = entries.iterator();
        boolean foundMatch = false;
        while ((entryIter.hasNext() && (!foundMatch))) {
          DictionaryResource.DictEntry entry = entryIter.next();

          // System.err.println("entryIter = " + entryIter + ", Entry:
          // " + entry.getText ());
          // debugWrite (findMatchDebugFile, "Entry: " + entry.getText
          // ());
          // System.err.println("remainingTokens = " +
          // normalizedTokens.subList (whichToken,
          // normalizedTokens.size ()).toString ());
          // debugWrite (findMatchDebugFile, "remainingTokens = " +
          // normalizedTokens.subList (whichToken,
          // normalizedTokens.size ()).toString ());

          if (containsAll (normalizedTokens.subList(whichToken, normalizedTokens.size()),
                  entry.getElements())) {
            int lengthOfMatch = processMatch(tcas, tokens, normalizedTokens, spanAnnotation,
                    whichToken, entry);
            if (!findAllMatches) {
              foundMatch = true;
              if (searchStrategy == SkipAnyMatchAllowOverlap) {
                whichToken += 1;
              } else {
                whichToken += lengthOfMatch;
              }
              // System.err.println ("Processed match, whichToken
              // = " + whichToken);
              // debugWrite (findMatchDebugFile, "Processed match,
              // whichToken = " + whichToken);
            }
          }
        }
        if (!foundMatch) {
          whichToken += 1;
        }
      }
    }
  }

  /**
   * @param tcas
   * @param tokens
   *          list of token annotations
   * @param normalizedTokens
   *          list of token annotations as strings
   * @param spanAnnotation
   * @param whichToken
   *          current token index (for tokens/normalizedTokens)
   * @param entry
   *          matching dict entry
   * @return length of match (in tokens)
   */
  private int processMatch(CAS tcas, ArrayList<AnnotationFS> tokens,
          ArrayList<String> normalizedTokens, Annotation spanAnnotation, int whichToken,
          DictionaryResource.DictEntry entry) {
    int startingPoint = whichToken;
    TreeMap<String, Integer> entryOccurences = findEntryOccurences(entry.getElements(), whichToken);
    int begin = -1;
    int end = 0;
    StringBuilder matchedText = new StringBuilder();

    // while there are still items to match against
    ArrayList<AnnotationFS> matched = new ArrayList<AnnotationFS>();
    while ((!entryOccurences.isEmpty()) && (whichToken < normalizedTokens.size())) {
      String currentTokenText = normalizedTokens.get(whichToken);
      // System.err.println ("matchedText: '" + matchedText + "',
      // whichToken = " + whichToken + ", currentTokenText: " +
      // currentTokenText);

      // if the dict entry contains at least one more of the current
      // token, process it
      Integer count = entryOccurences.get(currentTokenText);
      if (count != null) {
        if (matchedText.length() != 0) {
          matchedText.append(' ');
        }
        matchedText.append(currentTokenText);
        // System.err.println ("matchedText: '" + matchedText + "'");

        AnnotationFS realToken = tokens.get(whichToken);
        // System.err.println ("realToken: '" + realToken.getCoveredText
        // () + ", count.intValue () = " + count.intValue ());

        begin = (begin == -1) ? realToken.getBegin() : Math.min(begin, realToken.getBegin());
        end = Math.max(end, realToken.getEnd());
        matched.add(realToken);
        // decrement count, or remove entry if none left
        if (count.intValue() == 1) {
          entryOccurences.remove(currentTokenText);
        } else {
          entryOccurences.put(currentTokenText, Integer.valueOf (count.intValue() - 1));
        }
      }

      whichToken += 1;
    }
    if (entryOccurences.isEmpty()) {
      // System.err.println ("makeAnnotation, text: " +
      // matchedText.toString ());
      makeAnnotation(tcas, begin, end, entry.getProperties(), spanAnnotation, matchedText
              .toString(), matched, logger);
    }
    // else
    // {
    // System.err.println ("whichToken = " + whichToken + ",
    // normalizedTokens.size = " + normalizedTokens.size ());
    // }

    return whichToken - startingPoint;
  }

  // generate a map from tokens to number of occurences of that token
  private TreeMap<String, Integer> findEntryOccurences(String[] normalizedTokens,
          int whichToken) {
    TreeMap<String, Integer> result = new TreeMap<String, Integer>();

    for (String token : normalizedTokens) {
      Integer count = result.get(token);
      if (count == null) {
        count = Integer.valueOf (1);
      } else {
        count = Integer.valueOf (count.intValue() + 1);
      }
      result.put(token, count);

    }
    return result;
  }

  /**
   * @param searchStrategy
   * @param tcas
   * @param tokens
   * @param spanAnnotation
   */
  protected void processTokenList(int searchStrategy, boolean findAllMatches, CAS tcas,
          ArrayList<AnnotationFS> tokens, Annotation spanAnnotation) {
    AnnotationFS token;
    // iterate over vector of tokens

    int whichToken = 0;
    int entryLength = 0;

    while (whichToken < tokens.size()) {
      token = tokens.get(whichToken);
      String tokenText = getTokenText(token);
      entryLength = 0;

      String word = tokenNormalizer.normalize(tokenText);

      // logger.logInfo("ENTRY SEARCH/ORIGINAL: " + word + " / " +
      // tokenText);
      // System.err.println("ENTRY SEARCH/ORIGINAL: " + word + ", Token["
      // + whichToken + "]: " + tokenText);

      DictionaryResource.DictEntriesByLength entriesByLength = dict.getEntries(word);
      if (entriesByLength != null) {
        entryLength = Math.min(entriesByLength.getLongest().intValue(),
                (tokens.size() - whichToken));
        // logger.logInfo("ENTRY FOUND for: " + word + ", longest: " +
        // entryLength + ", shortest: " + minLength);
        // System.err.println("ENTRY FOUND for: " + word + ", longest: "
        // + entryLength + ", shortest: " + minLength);
        // System.err.println("ENTRY FOUND for: " + word + ", longest: "
        // + entryLength);

        entryLength = defaultMatcher(findAllMatches, tcas, tokens, spanAnnotation, whichToken,
                entryLength, token.getBegin(), entriesByLength, entriesByLength.getShortest()
                        .intValue());

      }
      whichToken += entryLength + 1;
    }
  }

  private int defaultMatcher(boolean findAllMatches, CAS tcas, ArrayList<AnnotationFS> tokens,
          Annotation spanAnnotation, int whichToken, int entryLength, int start,
          DictionaryResource.DictEntriesByLength lengthEntries, int minLength) {
    boolean entryFound = false;
    // search through all entry lengths, as necessary
    while ((!entryFound) && (entryLength >= minLength)) {
      String [] tokensToMatch = buildTokensToMatchArray(tokens, whichToken, entryLength, sortElements);
      //System.err.print(">>> tokensToMatch: '");
      //for (String token : tokensToMatch) {
      //    System.err.print(token + " ");
      //}
      //System.err.println("'");
      DictionaryResource.DictEntries entriesByLength = lengthEntries.getEntries(entryLength);
      // System.err.println(">>> entriesByLength = " + entriesByLength);
      if (entriesByLength != null) {
        ArrayList<DictionaryResource.DictEntry> entries = entriesByLength.getEntries();
        Collection <DictionaryResource.DictEntry> resultEntries = findMatchingEntry(entries, tokensToMatch);
        Iterator<DictionaryResource.DictEntry> resultEntriesIterator = resultEntries.iterator();
        AnnotationFS endToken = tokens.get(whichToken + entryLength - 1);

        while (resultEntriesIterator.hasNext()) {
          DictionaryResource.DictEntry dictEntry = resultEntriesIterator.next ();
          // System.err.println("===> MATCH: '" + tokensToMatch + "'");

          // System.err.println(">>>"+dictEntry.getUnsorted() );
          makeAnnotation(tcas, start, endToken.getEnd(), dictEntry.getProperties(), spanAnnotation,
                  dictEntry.getUnsorted(), tokens.subList(whichToken, whichToken + entryLength),
                  logger);

          updateTokenAnnotations(tokens, whichToken, entryLength, dictEntry);
          if (!findAllMatches) {
            entryFound = true;
          }
        }
      }
      entryLength--;
    }
    if (!entryFound) {
      entryLength = 0;
    }
    return entryLength;
  }

  /**
   * update token annotations with value stored in dictionary for feature provided by
   * tokenClassFeatureName
   * 
   * @param tokens
   * @param whichToken
   * @param entryLength
   * @param dictEntry
   */
  private void updateTokenAnnotations(ArrayList<AnnotationFS> tokens, int whichToken,
          int entryLength, DictEntry dictEntry) {
    if (tokenClassWriteBackFeatures != null) {
      for (int feature = 0; feature < tokenClassWriteBackFeatures.length; feature++) {
        if (tokenClassWriteBackFeatures[feature] != null) {
          String propVal = dictEntry.getProperties().getProperty(
                  tokenClassWriteBackFeatureNames[feature], UNKNOWN_VALUE);
          // System.err.println ("propVal: " + ": " + propVal);
          for (int i = whichToken; i < whichToken + entryLength; i++) {
            AnnotationFS tokenToUpdate = tokens.get(i);
            // System.err.println ("Token: " + tokenToUpdate.getText
            // ());
            tokenToUpdate.setStringValue(tokenClassWriteBackFeatures[feature], propVal);
          }
        }
      }
    }
  }

  /**
   * @param start
   * @param end
   * @param properties
   * @param matched
   */
  protected void makeAnnotation(CAS tcas, int start, int end, EntryProperties properties,
          Annotation spanAnnotation, String matchedText, Collection<AnnotationFS> matched,
          Logger log) {
    AnnotationFS annotation = tcas.createAnnotation(resultAnnotationType, start, end);
    if (resultEnclosingSpan != null) {
      annotation.setFeatureValue(resultEnclosingSpan, spanAnnotation);
    }

    if (resultMatchedTextFeature != null) {
      annotation.setStringValue(resultMatchedTextFeature, matchedText);
    }

    if (matchedTokensFeature != null) {
      FSArray matchedTokens = new FSArray(getJCas(), matched.size());
      FeatureStructure[] featureStructArray = new FeatureStructure[matched.size()];
      matched.toArray(featureStructArray);
      matchedTokens.copyFromArray(featureStructArray, 0, 0, featureStructArray.length);
      annotation.setFeatureValue(matchedTokensFeature, matchedTokens);
      /*
       * FSArray tmp = (FSArray) annotation.getFeatureValue (matchedTokensFeature); FeatureStructure []
       * tmpfs = tmp.toArray (); System.err.println ("FSArray: begin"); for (int i = 0; i <
       * tmpfs.length; i++) { System.err.println (((Annotation) tmpfs[i]).getCoveredText ()); }
       * System.err.println ("FSArray: done");
       */
    }

    for (int featIndex = 0; featIndex < features.length; featIndex++) {
      if (features[featIndex] != null) {
        annotation.setStringValue(features[featIndex], properties.getProperty(
                attributeNames[featIndex], UNKNOWN_VALUE));
      } else {

        // String message = "Feature '" + features[featIndex].getName() + "' not found in type '" +
        // resultAnnotationName + "'";

        String message = "Feature '" + featIndex + "' not found in type '" + resultAnnotationName
                + "'";
        // System.err.println(message);

        log.logWarning(message);
      }
    }

    tcas.getIndexRepository().addFS(annotation);
  }

  /**
   * @param entries
   * @param tokensToMatch
   * @return
   */
  private Collection<DictEntry> findMatchingEntry(ArrayList<DictionaryResource.DictEntry> entries,
          										  String [] tokensToMatch) {
    //System.err.print("Searching for: '");
    //for (String token : tokensToMatch) {
    //    System.err.print(token + " ");
    //}
    //System.err.println("'");


	Collection<DictEntry> result = new ArrayList<DictEntry> ();
	
    for (int i = 0; i < entries.size(); i++) {
      DictionaryResource.DictEntry dictEntry = entries.get(i);
      String[] entryText = dictEntry.getElements();

      // System.err.println("--> trying: '" + entryText.toString() + "'");

      if (entryText.length == tokensToMatch.length)
      {
    	  boolean match = true;
    	  int item = 0;
          for (String entryTextItem : entryText)
    	  {
    		  if (! entryTextItem.equals(tokensToMatch[item]))
    		  {
    			  match = false;
    			  break;
    		  }
    		  item += 1;
    	  }
    	  if (match) {
    		  result.add (dictEntry);
    	  }
      }
    }
    return result;
  }

  /**
   * @param tokens
   * @param length
   * @return
   */
  private String[] buildTokensToMatchArray(ArrayList<AnnotationFS> tokens, int startIndex, int length,
          boolean sortElements) {
    String[] elements = new String[length];
    for (int i = startIndex; i < length + startIndex; i++) {
      AnnotationFS token = tokens.get(i);
      elements[i - startIndex] = tokenNormalizer.normalize(getTokenText(token));
    }

    if (sortElements) {
      Arrays.sort(elements);
    }

    return elements;
  }

  private String getTokenText(AnnotationFS token) {
    if (tokenTextFeature == null) {
      return token.getCoveredText();
    } else {
      return token.getStringValue(tokenTextFeature);
    }
  }
}
