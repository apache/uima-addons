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
package org.apache.uima.annotator.bsf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.bsf.BSFEngine;
import org.apache.bsf.BSFException;
import org.apache.bsf.BSFManager;
import org.apache.bsf.util.IOUtils;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Logger;

/**
 * This class enables the Java scripting of an Annotator using the Apache Bean
 * Scripting Framework. It has a mandatory <code>SourceFile</code> attribute
 * containing the script's source file to execute.
 * <p>
 */
public class BSFAnnotator extends JCasAnnotator_ImplBase {

	public static final String MESSAGE_DIGEST = "org.apache.uima.annotator.bsf.BSFAnnotatorMessages";

	public static final String PATH_SEPARATOR = System.getProperty("path.separator");

	public static final String SCRIPT_SOURCE_FILE = "SourceFile";

	private Logger logger;
	private BSFManager manager;
	private BSFEngine engine;
	String scriptLanguage;
	String scriptFileName;


    /**
     * Initializes the annotator by compiling the script.
     */
    public void initialize(UimaContext aContext)
        throws ResourceInitializationException {
        super.initialize(aContext);
		// Initialize a BSF manager and do some 'cooking' to adapt the class loader
		manager = new BSFManager();
		ClassLoader classLoader = this.getClass().getClassLoader();
		Thread.currentThread().setContextClassLoader(classLoader);
		manager.setClassLoader(classLoader);
		// Is a UIMAClassLoader
		if (classLoader instanceof URLClassLoader) {
			manager.setClassPath(classpathFromUrls(((URLClassLoader) classLoader).getURLs()));
		}
		Reader reader = null;
		scriptFileName = null;
		try {
			logger = aContext.getLogger();
			// get UIMA datapath and tokenize it into its elements
			StringTokenizer tokenizer = new StringTokenizer(aContext
					.getDataPath(), PATH_SEPARATOR);
			List<File> datapathElements = new ArrayList<File>();
			while (tokenizer.hasMoreTokens()) {
				// add datapath elements to the 'datapathElements' array list
				datapathElements.add(new File(tokenizer.nextToken()));
			}

			// Get config. parameter values
			scriptFileName = (String) aContext.getConfigParameterValue(SCRIPT_SOURCE_FILE);
			File scriptFile = new File(scriptFileName);
			if (!scriptFile.isAbsolute()) {
				// try to resolve the relative file name with classpath or
				// datapath
				scriptFile = resolveRelativeFilePath(scriptFileName, datapathElements);

				// if the current script file wasn't found, throw an exception
				if (scriptFile == null) {
					throw new BSFAnnotatorConfigurationException(
							"bsf_annotator_resource_not_found",
							new Object[] { scriptFileName });
				}
			}
			reader = new FileReader(scriptFile);
		} catch (FileNotFoundException fnfe) {
			throw new BSFAnnotatorConfigurationException(
					"bsf_annotator_resource_not_found",
					new Object[] { scriptFileName }, fnfe);
		}

		try {
			scriptLanguage = BSFManager.getLangFromFilename(scriptFileName);
			engine = manager.loadScriptingEngine(scriptLanguage);
		} catch (BSFException bsfe) {
			Throwable cause = bsfe.getTargetException();
			if (cause == null) cause = bsfe;
			throw new BSFAnnotatorConfigurationException(
					"bsf_annotator_language_not_supported",
					new Object[] { scriptLanguage }, cause);
		}

		// read and execute the script
		try {
			String script = IOUtils.getStringFromReader(reader);
			engine.exec(scriptFileName, 0, 0, script);
		} catch (IOException ioe) {
			throw new BSFAnnotatorInitializationException(
					"bsf_annotator_error_reading_script",
					new Object[] { scriptFileName }, ioe);
		} catch (BSFException bsfe) {
			Throwable cause = bsfe.getTargetException();
			if (cause == null) cause = bsfe;
			throw new BSFAnnotatorInitializationException(
					"bsf_annotator_error_executing_script",
					new Object[] { scriptFileName }, cause);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
				}
			}
		}
		// Call the initialize function implemented in the script
		String methodName = null;
		try {
			methodName = "initialize";
			engine.call(null, methodName, new Object[] { aContext });
		} catch (BSFException bsfe) {
			Throwable cause = bsfe.getTargetException();
			if (cause == null) cause = bsfe;
			throw new BSFAnnotatorInitializationException(
					"bsf_annotator_error_calling_method",
					new Object[] { methodName }, cause);
		}

	}

	/**
	 * Call the process function implemented in the script
	 */
    public void process(JCas jcas)
    throws AnalysisEngineProcessException {
	
		String methodName = null;
		try {
			methodName = "process";
			engine.call(null, methodName, new Object[] { jcas });
		} catch (BSFException bsfe) {
			Throwable cause = bsfe.getTargetException();
			if (cause == null) cause = bsfe;
			throw new BSFAnnotatorProcessException(
					"bsf_annotator_error_calling_method",
					new Object[] { methodName }, cause);
		}
	}
	/**
	 * @param fileName
	 * @param datapathElements
	 * @return
	 */
	private File resolveRelativeFilePath(String fileName,
			List<File> datapathElements) {
		URL url;
		// try to use the class loader to load the file resource
		if ((url = this.getClass().getClassLoader().getResource(fileName)) != null) {
		  URI uri;
		  try {
		    // handle urls with embedded blanks, coded as %20 
		    // https://issues.apache.org/jira/browse/UIMA-1748
		    // https://issues.apache.org/jira/browse/UIMA-2097
        uri = quote(url);
      } catch (URISyntaxException e) {
        uri = null;
      }
      if (uri != null) {
			  return new File(uri);
      }
		} 
		if (datapathElements == null || datapathElements.size() == 0) {
			return null;
		}
		// try to use the datapath to load the file resource
		for (File dataPathDir : datapathElements) {
			File testFile = new File(dataPathDir, fileName);
			if (testFile.exists()) {
				return testFile;
			}
		}
		return null;
	}
	/**
	 * @param urls
	 * @return
	 */
	private String classpathFromUrls(URL[] urls) {
		String classpath = null;
		for (int i = 0; i < urls.length; i++) {
			File filepath = new File(urls[i].getPath());
			if (i == 0)
				classpath = filepath.getPath();
			else
				classpath = classpath + File.pathSeparator + filepath.getPath();
		}
		return classpath;
	}

	// Maintainer note: remove these methods once base sdk 2.3.2 is released, and switch
	// to using these methods from there
  /**
   * Create a URI from a string, with proper quoting.
   * Already quoted things in the input string are not re-quoted.
   * There are several cases:
   *   String has no characters needing quoting
   *   String has chars needing quoting, but no chars are currently quoted (e.g. %20)
   *   String has quoted (e.g. %20) characters but no other chars needing quoting
   *   String has quoted (e.g. %20) characters and chars needing quoting, not currently quoted
   *     -- this case will throw an exception
   * @param s
   * @return URI with proper quoting
   * @throws URISyntaxException 
   */
  private static URI quote (String s) throws URISyntaxException {
    if (-1 == s.indexOf('%')) {
      // 3 argument constructor does any needed quoting of otherwise illegal chars
      // https://issues.apache.org/jira/browse/UIMA-2097
      return new URI(null, s, null);  
    }
    
    // assume s already has all otherwise illegal chars properly quoted
    return new URI(s);
  }

  /**
   * Create a URI from a URL, with proper quoting.
   * Already quoted things in the input string are not re-quoted.
   * @param u
   * @return URI with proper quoting
   * @throws URISyntaxException 
   */

  private static URI quote(URL u) throws URISyntaxException {
    return quote(u.toString());
  }

}
