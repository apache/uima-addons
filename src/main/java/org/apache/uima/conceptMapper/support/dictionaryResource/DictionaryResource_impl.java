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

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.Vector;
import java.util.regex.Pattern;

import org.apache.uima.analysis_engine.annotator.AnnotatorContext;
import org.apache.uima.conceptMapper.Logger;
import org.apache.uima.conceptMapper.support.dictionaryResource.annotatorAdaptor.AnnotatorAdaptor;
import org.apache.uima.conceptMapper.support.tokens.TokenFilter;
import org.apache.uima.conceptMapper.support.tokens.TokenNormalizer;
import org.apache.uima.resource.DataResource;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.SharedResourceObject;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/*
 * @version $Revision: 1.5 $
 * 
 * @author Michael Tanenblatt <mtan@us.ibm.com> @author (c) Copyright 2005 IBM @author All Rights
 * Reserved
 */
/**
 * Implementation of a UIMA DictionaryResource
 */

public class DictionaryResource_impl implements DictionaryResource, SharedResourceObject {
  /** Dictionary file loader. Uses an XML parser. */
  protected DictLoader dictLoader;

  /**
   * Hashtable of first words. Contains a DictEntries object keyed on word string for the first word
   * of every entry in the specified dictionary.
   */
  protected Hashtable<String, DictEntriesByLength> dictImpl;

  /** Initial size of <code>dict</code> */
  protected static final int NumOfInitialDictEntries = 500000;

  boolean loaded;

  /** Patterns to for matcher to replace SGML &amp;lt; entities */
  private static final Pattern ltPattern = Pattern.compile("&lt;");

  /** Patterns to for matcher to replace SGML &amp;gt; entities */
  private static final Pattern gtPattern = Pattern.compile("&gt;");

  /** Patterns to for matcher to replace SGML &amp;apos; entities */
  private static final Pattern aposPattern = Pattern.compile("&apos;");

  /** Patterns to for matcher to replace SGML &amp;quot; entities */
  private static final Pattern quotPattern = Pattern.compile("&quot;");

  /** Patterns to for matcher to replace SGML &amp;amp; entities */
  private static final Pattern ampPattern = Pattern.compile("&amp;");

  /** Configuration parameter key/label for the order independent lookup indicator */
  public static final String PARAM_ORDERINDEPENDENTLOOKUP = "OrderIndependentLookup";

  private boolean sortElements = false;

  /** Configuration parameter key/label to indicate whether dictionary should be printed upon load */
  public static final String PARAM_DUMPDICT = "PrintDictionary";

  private boolean dumpDict = false;

  /** Configuration parameter key/label for the case matching string */
  public static final String PARAM_CASE_MATCH = "caseMatch";

  /** Configuration parameter key/label for the stemmer class spec. If left out, no stemmer is used */
  public static final String PARAM_STEMMER_CLASS = "Stemmer";

  /**
   * Configuration parameter key/label for the stemmer dictionary, passed into the stemmer's
   * initialization method
   */
  public static final String PARAM_STEMMER_DICT = "StemmerDictionary";

  private static final String PARAM_LANGID = "LanguageID";

  private static final String DEFAULT_LANGID = "en";

  /** Configuration parameter key/label for the attribute list */
  public static final String PARAM_ATTRIBUTE_LIST = "AttributeList";

  public static final String PARAM_XML_PARSER = "XMLParserName";
  private String XMLParserName = null;

  public int entryNum = 0;

  /**
   * @param NumOfInitialDictEntries
   * 
   */
  public DictionaryResource_impl() {
    super();
    dictImpl = new Hashtable<String, DictEntriesByLength>();
    loaded = false;
  }

  /**
   * @param NumOfInitialDictEntries
   * 
   */
  public DictionaryResource_impl(int initialDictEntries) {
    super();
    dictImpl = new Hashtable<String, DictEntriesByLength>(initialDictEntries);
    loaded = false;
  }

  /**
   * @return Returns the dictLoader.
   */
  public DictLoader getDictLoader() {
    return dictLoader;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.conceptMapper.conceptMapper.support.DictionaryResource#getEntry(java.lang.String)
   */
  public DictEntriesByLength getEntries(String key) {
    return dictImpl.get(key);
  }

  /**
   * Create a new dictionary entry.
   * 
   * @param key
   *          the key to index on
   * @param elements
   *          the individual elements to be entered in the dictionary
   * @param unsorted
   * 		  an unsorted string representation of the entry, if the contents of 'elements' has been sorted
   * @param length
   *          the number of words in the phrase (>=1)
   * @param props
   *          the EntryProperties object for the dictionary entry
   */
  public void putEntry(String key, String[] elements, String unsorted,
          int length, EntryProperties props) {
    DictEntriesByLength entry = getEntries(key);

    if (entry == null) {
      entry = new DictEntriesByLength_impl();
      // System.err.println ("DICT adding: " + key + ", " + text);
      dictImpl.put(key, entry);
    }
    // Iterator<String> elemIter = elements.iterator ();
    // while (elemIter.hasNext ())
    // {
    // System.err.println("ENTRY ELEMENT: " + elemIter.next ());
    // }
    entry.putEntry(length, elements, unsorted, props);
    // System.err.println("ENTRY: '" + text + "', PROPS: ");
    // Enumeration propKeys = props.keys();
    // while (propKeys.hasMoreElements())
    // {
    // String propKey = (String) propKeys.nextElement();
    // System.err.println ("\t" + propKey + ": " + props.getProperty(propKey));
    // }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.resource.SharedResourceObject#load(org.apache.uima.resource.DataResource)
   */
  public void load(DataResource data) throws ResourceInitializationException {
    // InputStream dictStream = null;
    try {
      Logger logger = new Logger("Dictionary Resource Loader", data.getLogger());
      dictLoader = new DictLoader(logger, this, data);

      /*
       * At least for now, while info from the AnnotatorContext is not available here, must delay
       * loading until able to access this info
       * 
       * //open input stream to data dictStream = data.getInputStream ();
       * 
       * dictLoader = new DictLoader (data.getLogger (), this); dictLoader.setDictionary
       * (dictStream, NumOfInitialDictEntries);
       */
    } catch (Exception e) {
      throw new ResourceInitializationException(e);
    }
    /*
     * finally { if (dictStream != null) { try { dictStream.close (); } catch (IOException e) { } } }
     */
  }

  public void loadDictionaryContents(AnnotatorContext aContext, Logger logger,
          String tokenAnnotationName, String tokenTypeFeatureName, String tokenClassFeatureName,
          String tokenizerDescriptor) throws ResourceInitializationException {

    InputStream dictStream = null;
    try {
      dictLoader.setLogger(logger);
      Boolean sortElementsParam = new Boolean(false);

      sortElementsParam = (Boolean) aContext.getConfigParameterValue(PARAM_ORDERINDEPENDENTLOOKUP);
      if (sortElementsParam == null) {
        sortElements = false;
      } else {
        sortElements = sortElementsParam.booleanValue();
      }

      Boolean dumpDictParam = (Boolean) aContext.getConfigParameterValue(PARAM_DUMPDICT);
      if (dumpDictParam == null) {
        dumpDict = false;
      } else {
        dumpDict = dumpDictParam.booleanValue();
      }

      TokenNormalizer tokenNormalizer = new TokenNormalizer(aContext, logger);

      // open input stream to data
      dictStream = dictLoader.getInputStream();

      TokenFilter tokenFilter = new TokenFilter(tokenAnnotationName, tokenTypeFeatureName,
              tokenClassFeatureName, logger);
      tokenFilter.initConfig(aContext);

      String langID = (String) aContext.getConfigParameterValue(PARAM_LANGID);
      if ((langID == null) || (langID == ""))
      {
        langID = DEFAULT_LANGID;
      }
      
      XMLParserName = (String) aContext.getConfigParameterValue(PARAM_XML_PARSER);
      
      String [] entryPropertyNames = (String []) aContext.getConfigParameterValue(PARAM_ATTRIBUTE_LIST);
      // System.out.print ("Loading Dictionary: '" + dictLoader.dataResource.getUri().toString() +
      // "'...");
      // System.out.print ("Loading Dictionary...");
      logger.logInfo("Loading Dictionary...");
      dictLoader.setDictionary(dictStream, NumOfInitialDictEntries, tokenAnnotationName,
              tokenTypeFeatureName, tokenClassFeatureName, tokenizerDescriptor, tokenFilter,
              tokenNormalizer, langID, entryPropertyNames);
      logger.logInfo("...done");
      // System.out.println ("done");
      // System.err.println("NEW DICT:\n" + toString());
      setLoaded(true);
      if (dumpDict)
      {
        System.err.println("BEGIN DUMPING DICTIONARY:");
        System.err.println(toString());
        System.err.println("DONE DUMPING DICTIONARY! ");
      }

    } catch (Exception e) {
      throw new ResourceInitializationException(e);
    } finally {
      if (dictStream != null) {
        try {
          dictStream.close();
        } catch (IOException e) {
        }
      }
    }

  }

  /**
   * @return Returns the loaded.
   */
  public boolean isLoaded() {
    return loaded;
  }

  /**
   * @param loaded
   *          The loaded to set.
   */
  public void setLoaded(boolean loaded) {
    this.loaded = loaded;
  }

  private static class DictEntriesByLength_impl implements DictEntriesByLength {
    private static final long serialVersionUID = -8150386021246495622L;

    private class ReverseOrderIntegerComparator implements Comparator<Integer>, Serializable {

      /**
       * 
       */
      private static final long serialVersionUID = -805437355806223406L;

      /*
       * (non-Javadoc)
       * 
       * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
       */
      public int compare(Integer left, Integer right) {
        // reverse the order of parameters, to reverse the sorting order
        return (right.compareTo(left));
      }

    }

    TreeMap<Integer, DictEntries> entries;

    public DictEntriesByLength_impl() {
      super();
      entries = new TreeMap<Integer, DictEntries>(new ReverseOrderIntegerComparator());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.uima.conceptMapper.support.dictionaryResource.DictionaryResource.DictEntriesByLength#getEntries(int)
     */
    public DictEntries getEntries(int length) {
      return entries.get(new Integer(length));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.uima.conceptMapper.support.dictionaryResource.DictionaryResource.DictEntriesByLength#putEntry(int,
     *      java.lang.String, java.util.Properties)
     */
    public void putEntry(int length, String[] elements, String unsorted,
            EntryProperties props) {
      DictEntries entry = getEntries(length);
      if (entry == null) {
        entry = new DictEntriesImpl();
        entries.put(new Integer(length), entry);
      }
      entry.putEntry(elements, unsorted, props);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.uima.conceptMapper.support.dictionaryResource.DictionaryResource.DictEntriesByLength#getLongest()
     */
    public Integer getLongest() {
      return entries.firstKey();
    }

    public Integer getShortest() {
      return entries.lastKey();
    }

    public String toString() {
      StringBuffer result = new StringBuffer();

      int i = getLongest().intValue();
      int last = getShortest().intValue();

      while (i >= last) {
        DictEntriesImpl entries = (DictEntriesImpl) getEntries(i);
        if (entries != null) {
          result.append("<DictEntriesByLength length='" + i + "'>\n");
          result.append(entries.toString());
          result.append("</DictEntriesByLength>\n");
        }
        i--;
      }
      return result.toString();
    }
  }

  /**
   * Private class for storing first words in the dict hashtable.
   */
  public static class DictEntriesImpl extends ArrayList<DictEntry> implements DictEntries {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Create a new dictionary entry.
     */
    public DictEntriesImpl() {
    }

    /**
     * Add a new phrase to an existing dictionary entry.
     * 
     * @param elements
     *          the text to be entered in the dictionary
     * @param props
     *          the properties object for the phrase
     */
    public void putEntry(String[] elements, String unsorted, EntryProperties props) {
      add(new DictEntryImpl(elements, unsorted, props));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.uima.conceptMapper.support.dictionaryResource.DictionaryResource.DictEntries#getEntry(java.lang.String)
     */
    public ArrayList<DictEntry> getEntries() {
      return this;
    }

    public String toString() {
      StringBuffer result = new StringBuffer("<DictEntries>");
      for (int i = 0; i < size(); i++) {
        result.append(((DictEntryImpl) get(i)).toString());
      }
      result.append("</DictEntries>");
      return result.toString();
    }

  }

  public static class DictEntryImpl implements DictEntry {
    private static final long serialVersionUID = -7723934333674544157L;

    String[] elements;

    String unsorted;

    EntryProperties properties;

    /**
     * @param text
     * @param elements
     * @param unsorted
     * @param properties
     */
    public DictEntryImpl(String[] elements, String unsorted,
            EntryProperties properties) {
      super();
      this.properties = properties;
      this.unsorted = unsorted;
      this.elements = elements;
    }

    /**
     * @param properties
     *          The properties to set.
     */
    public void setProperties(EntryProperties properties) {
      this.properties = properties;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.uima.conceptMapper.support.dictionaryResource.DictionaryResource.DictEntry#getProperties()
     */
    public EntryProperties getProperties() {
      return properties;
    }

    public String toString() {
      StringBuffer result = new StringBuffer("<DictEntry Text ='" + getElements().toString() + "'>");
      
      for (String propertyName : EntryPropertiesFactory.propertyNames())
      {
          result.append("<property name='" + propertyName.toString() + "'>");
          String item = getProperties().getProperty(propertyName);
          result.append(item);
          result.append("</property>\n");
      }
      result.append("</DictEntry>\n");
      return result.toString();

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.uima.conceptMapper.support.dictionaryResource.DictionaryResource.DictEntry#getUnsorted()
     */
    public String getUnsorted() {
      return unsorted;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.uima.conceptMapper.support.dictionaryResource.DictionaryResource.DictEntry#setUnsorted(java.lang.String)
     */
    public void setUnsorted(String unsorted) {
      this.unsorted = unsorted;
    }

    public String[] getElements() {
      return elements;
    }

    public void setElements(String[] elements) {
      this.elements = elements;
    }

  }

  /**
   * Convert character entities in a string to the corresponding character. The set of entities
   * handled includes:
   * <ul>
   * <li>&amp;amp;
   * <li>&amp;lt;
   * <li>&amp;gt;
   * <li>&amp;apos;
   * <li>&amp;quot;
   * </ul>
   * 
   * @param input
   *          the string to process.
   * 
   * @return the string with converted entities
   */
  protected String convertEntities(String input) {
    String result = ltPattern.matcher(input).replaceAll("<");
    result = gtPattern.matcher(result).replaceAll(">");
    result = aposPattern.matcher(result).replaceAll("'");
    result = quotPattern.matcher(result).replaceAll("\"");
    result = ampPattern.matcher(result).replaceAll("&");

    return (result);
  }

  /**
   * Private class to load the dictionary file. Extends the org.xml.sax.helpers.DefaultHandler for
   * XML parsing.
   */
  private class DictLoader extends DefaultHandler {
    /** Default parser name. */
    //protected static final String DEFAULT_PARSER_NAME = "org.apache.xerces.parsers.SAXParser";

    /** Default name of element that contains dictionary records. */
    protected static final String DEFAULT_TOKEN_ELEM = "token";

    /** Default name of element that contains variant form */
    protected static final String DEFAULT_VARIANT_ELEM = "variant";

    /**
     * Default name of attribute in the token element that contains the key for the entry (i.e., the
     * surface or variant form that will be found in the document).
     */
    protected static final String DEFAULT_KEY_ATTR = "base";

    /** Default name of element that contains part of speech */
    // protected static final String DEFAULT_POS_ATTR = "POS";
    /** The XML parser that parses the dictionary file. */
    private XMLReader parser = null;

    /** The name of the element that contains the dictionary records. */
    private String token_elem = DEFAULT_TOKEN_ELEM;

    /**
     * The name of the element that contains a variant form for the current entry
     */
    private String variant_elem = DEFAULT_VARIANT_ELEM;

    /** The name of the attribute that contains the key. */
    private String key_attribute = DEFAULT_KEY_ATTR;

    /** The name of the attribute that contains the part of speech. */
    // private String pos_attribute = DEFAULT_POS_ATTR;
    /** Count of number of dictionary entries loaded. */
    private int term_cnt = 0;

    /** Reference to UIMA logger. */
    private Logger logger;

    /** The hashtable built while parsing the dictionary file. */
    private DictionaryResource dict;

    /** Properties for current canonical form */
    private EntryProperties props;

    private AnnotatorAdaptor adaptor;

    private String tokenAnnotationName;

    private String tokenizerDescriptor;

    private TokenFilter tokenFilter;

    private TokenNormalizer tokenNormalizer;
    
    private EntryPropertiesFactory entryPropertiesFactory;

    /**
     * needed to access input stream, since cannot load external dict resource until TAE config
     * params are available to set up tokenizer correctly
     */
    DataResource dataResource;

    private Vector<DictionaryToken> result;

    /**
     * Create a dictionary loader.
     * 
     * @param The
     *          UIMA logger.
     * 
     * @throws Exception
     *           if XML parser cannot be created.
     */
    public DictLoader(Logger log, DictionaryResource dict, DataResource data) throws Exception {
      this.setLogger(log);
      this.dict = dict;
      this.dataResource = data;

      // create parser
      try {
  	    if (XMLParserName != null) {
          parser = XMLReaderFactory.createXMLReader(XMLParserName);
  	    } else {
          parser = XMLReaderFactory.createXMLReader();
  	    }
      } catch (Exception e) {
        log.logError("Unable to instantiate dictionary parser (" + ((XMLParserName==null) ? "default XML parser" : XMLParserName) + ")");
        throw (e);
      }
      parser.setContentHandler(this);
      parser.setErrorHandler(this);

    }

    protected String getTokenAnnotationName() {
      return tokenAnnotationName;
    }

    protected void setTokenAnnotationName(String tokenAnnotationName) {
      this.tokenAnnotationName = tokenAnnotationName;
    }

    protected String getTokenizerDescriptor() {
      return tokenizerDescriptor;
    }

    protected void setTokenizerDescriptor(String tokenizerDescriptor) {
      this.tokenizerDescriptor = tokenizerDescriptor;
    }

    protected void setTokenFilter(TokenFilter tokenFilter) {
      this.tokenFilter = tokenFilter;
    }

    protected TokenFilter getTokenFilter() {
      return tokenFilter;
    }

    protected void setTokenNormalizer(TokenNormalizer tokenNormalizer) {
      this.tokenNormalizer = tokenNormalizer;
    }

    protected void setPropertiesFactory (EntryPropertiesFactory factory)
    {
    	this.entryPropertiesFactory = factory;
    }

    protected EntryPropertiesFactory getPropertiesFactory ()
    {
    	return entryPropertiesFactory;
    }
    
    protected TokenNormalizer getTokenNormalizer() {
      return tokenNormalizer;
    }

    /**
     * @return Returns the log.
     */
    public Logger getLogger() {
      return logger;
    }

    /**
     * @param log
     *          The log to set.
     */
    public void setLogger(Logger logger) {
      this.logger = logger;
    }

    /**
     * Start element. This method does most of the work of building the hashtable.
     * 
     */
    public void startElement(String uri, String local, String raw, Attributes attrs)
            throws SAXException {

      String key = null;
      DictionaryToken token = null;
      int length = 0;

      if (raw.equals(token_elem)) { // starting new token entry
        if (attrs != null) {

        	props = getPropertiesFactory().newEntryProperties();
			int attrCount = attrs.getLength();
			for (int i = 0; i < attrCount; i++) {
				props.setProperty(attrs.getQName(i), convertEntities(attrs.getValue(i)));
			}

        }
      } else if (raw.equals(variant_elem)) { // variant for current token
        if (attrs != null) {
          int attrCount = attrs.getLength();
          ArrayList<String> tokens = new ArrayList<String>();

          // if this variant contains its own POS info, save token level POS info and set props to
          // contain variant's
          EntryProperties variantProperties = new EntryProperties(props);
          // logger.logInfo("" + entryNum++);
          //
          // System.err.println("" + entryNum++);
          for (int i = 0; i < attrCount; i++) {
            if (attrs.getQName(i).equals(key_attribute)) { // key attribute?

              adaptor.runCPM(convertEntities(attrs.getValue(i)));

              //if (dumpDict)
              //{
              //  System.err.println ("Adaptor done, result size: " + result.size() + "\nresult:\n");
              //  for (DictionaryToken resultItem : result)
              //  {
              //    System.err.println ("  " + resultItem.getText()); 
              //  }
              //}
              
              Iterator<DictionaryToken> tokenIter = result.iterator();
              token = null;

              while (tokenIter.hasNext()) {
                token = (DictionaryToken) tokenIter.next();
                //if (dumpDict)
                //{
                //  System.err.println ("TOKEN CLASS: '" + token.getTokenClass() + "', TOKEN TYPE: '" + token.getType() + "'");
                //}
                if (tokenFilter.isOK_Token(token, tokenNormalizer)) {
                  break;
                }
              }

              if (token == null) {
                return;
              }

              key = new String(tokenNormalizer.normalize(token.getText()));
              //if (dumpDict)
              //{
              //  System.err.println ("variant token key:" + key);
              //}

              tokens.add(key);
              length = 1;
              while (tokenIter.hasNext()) {
                token = (DictionaryToken) tokenIter.next();
                String tokenText = tokenNormalizer.normalize(token.getText());

                if (tokenFilter.isOK_Token(token, tokenNormalizer)) {
                  key += " " + tokenText;
                  tokens.add(tokenText);
                  length++;
                  
                  //if (dumpDict)
                  //{
                  //  System.err.println (" variant token aux:" + tokenText);
                  //}
                }
                //else
                //{
                //  if (dumpDict)
                //  {
                //    System.err.println (" SKIPPING: variant token aux:" + tokenText);
                //  }
                //}
              }
            } else {
           		variantProperties.setProperty(attrs.getQName(i), convertEntities(attrs.getValue(i)));
            }

          }

          String[] elements = (String[]) tokens.toArray(new String[tokens.size()]);

          String unsorted = null;

          if (sortElements) {
            unsorted = stringTogetherTokens(elements);
            Arrays.sort(elements);
          }

          //String tokenString = stringTogetherTokens(elements);
          //if (dumpDict)
          //{
          //  System.err.println ("token string: " + tokenString);
          //}


          // add to dictionary
          if (sortElements) {
            for (int i = 0; i < tokens.size(); i++) {
              dict.putEntry((String) tokens.get(i), elements, unsorted, elements.length,
                      variantProperties);
              // System.err.println ("adding props for:" + tokenString);
              // Enumeration propKeys = variantProperties.keys();
              // while (propKeys.hasMoreElements())
              // {
              // String propKey = (String) propKeys.nextElement();
              // System.err.println ("\t" + propKey + ": " +
              // variantProperties.getProperty(propKey));
              // }

            }
          } else {
            dict.putEntry((String) tokens.get(0), elements, unsorted, elements.length,
                    variantProperties);
          }
          term_cnt++;
          if ((term_cnt % 10000) == 0) {
            getLogger().logInfo("processed " + term_cnt + " entries");
            //System.err.println("processed " + term_cnt + " entries");
          }
        }
      }
    }
     
    
    //
    // ErrorHandler methods
    //
    /** Warning. */
    public void warning(SAXParseException ex) throws SAXException {
      getLogger().logWarning(errorString("Warning", ex));
    } // warning(SAXParseException)

    /**
     * Error. public void error (SAXParseException ex) throws SAXException { getLogger ().log
     * (Level.SEVERE, errorString ("Error", ex)); } // error(SAXParseException)
     */
    /** Fatal error. */
    public void fatalError(SAXParseException ex) throws SAXException {
      getLogger().logError(errorString("Fatal Error", ex));
      throw ex;
    } // fatalError(SAXParseException)

    /** Prints the error message. */
    protected String errorString(String type, SAXParseException ex) {
      String errorMsg = "[" + type + "]";
      if (ex == null) {
        return errorMsg + "!!!";
      }
      String systemId = ex.getSystemId();
      if (systemId != null) {
        int index = systemId.lastIndexOf('/');
        if (index != -1)
          systemId = systemId.substring(index + 1);
        errorMsg += systemId;
      }
      errorMsg += ":" + ex.getLineNumber() + ":" + ex.getColumnNumber() + ": " + ex.getMessage();
      return errorMsg;
    }

    /**
     * Use the tokenizer specified in 'tokenizerDescriptor' to load the specified dicitonary file.
     * The dictonary file must be in the format specified above. A new <code>dict</code> hashtable
     * is created with a <code>DictEntries</code> object for each unique first word in the base
     * fields in the dictionary file. The <code>dict</code> hashtable is keyed off of the first
     * word. The <code>DictEntries</code> for a first word contains a hashtable of
     * <code>DictEntry</code> objects for every phrase in the base fields of the dictionary file
     * started by the first word. The phrase hashtable is keyed off of the entire phrase.
     * 
     * @param tokenizerDescriptor
     * @param tokenAnnotationName
     * @param tokenFilter
     * 
     * @param dictFile
     *          the fully specified filename of the dictionary file to load.
     * 
     * @param NumOfInitialDictEntries
     *          initial size of hashtable to create
     * 
     * @exception java.io.IOException
     *              if dictionary file cannot be loaded or some other initialization error occurs.
     */
    public void setDictionary(InputStream dictStream, int initialDictEntries,
            String tokenAnnotationName, String tokenTypeFeatureName, String tokenClassFeatureName,
            String tokenizerDescriptor, TokenFilter tokenFilter, TokenNormalizer tokenNormalizer, String langID, String [] entryPropertyNames)
            throws DictionaryLoaderException {
      term_cnt = 0;
      setTokenAnnotationName(tokenAnnotationName);
      setTokenizerDescriptor(tokenizerDescriptor);
      setTokenFilter(tokenFilter);
      setTokenNormalizer(tokenNormalizer);
      result = new Vector<DictionaryToken>();

      setPropertiesFactory (EntryPropertiesFactory.create (entryPropertyNames));
      
      getLogger().logInfo("Loading dictionary");
      try {
        adaptor = new AnnotatorAdaptor(getTokenizerDescriptor(), result, tokenAnnotationName,
                tokenFilter, langID, getLogger());
        adaptor.initCPM();

        parser.parse(new InputSource(dictStream));
      } catch (SAXException e) {
        getLogger().logError("Parse error occurred - " + e.getMessage());
        throw new DictionaryLoaderException(e);
      } catch (IOException e) {
        throw new DictionaryLoaderException(e);
      }
      getLogger().logInfo("Finished loading " + term_cnt + " entries");
    }

    public InputStream getInputStream() throws IOException {
      return dataResource.getInputStream();
    }

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.conceptMapper.support.DictionaryResource#NewDictionaryResource(int)
   */
  public DictionaryResource NewDictionaryResource(int initialDictEntries) {
    return new DictionaryResource_impl(initialDictEntries);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.conceptMapper.support.DictionaryResource#keys()
   */
  public Enumeration<String> keys() {
    return dictImpl.keys();
  }


  public  static String stringTogetherTokens(String[] elements) {
    StringBuffer tokenString = new StringBuffer();

    for (int i = 0; i < elements.length; i++) {
      if (i > 0) {
        tokenString.append(" ");
      }
      tokenString.append(elements[i]);
    }
    return tokenString.toString();
  }

  
  public String toString() {
    StringBuffer result = new StringBuffer();

    Enumeration<String> e = keys();

    while (e.hasMoreElements()) {
      String key = e.nextElement();
      result.append("<DictionaryItem key='" + key + "'>\n");
      DictEntriesByLength_impl item = (DictEntriesByLength_impl) getEntries(key);
      result.append(item.toString());
      result.append("</DictionaryItem>\n");
    }
    return result.toString();
  }

  public void serializeEntries(FileOutputStream output) throws IOException {
    ObjectOutputStream oos = new ObjectOutputStream(output);
    oos.writeObject(this.dictImpl);
    oos.close();
  }

}