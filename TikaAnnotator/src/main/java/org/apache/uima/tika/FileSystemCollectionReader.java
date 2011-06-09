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

package org.apache.uima.tika;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tika.exception.TikaException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceConfigurationException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Level;
import org.apache.uima.util.Progress;
import org.apache.uima.util.ProgressImpl;

/**
 * A collection reader that reads documents from a directory in the
 * filesystem.
 * 
 * This resource is different from the one in UIMA example as it uses TIKA to
 * extract the text from binary documents and generates annotations to represent
 * the markup
 */
public class FileSystemCollectionReader extends CollectionReader_ImplBase {
	/**
	 * Name of configuration parameter that must be set to the path of a
	 * directory containing input files.
	 */
	public static final String PARAM_INPUTDIR = "InputDirectory";

	/**
	 * Name of optional configuration parameter that contains the language of
	 * the documents in the input directory. If specified this information will
	 * be added to the CAS.
	 */
	public static final String PARAM_LANGUAGE = "Language";

	private final static String PARAM_MIME = "MIME";
	
	private final static String tika_file_param = "tikaConfigFile";

	private String mLanguage;
	
	private String mMIME;
	
	private List<File> mFiles;
	
	private int mCurrentIndex;

	private TIKAWrapper tika;


	/**
	 * @see org.apache.uima.collection.CollectionReader#hasNext()
	 */
	public boolean hasNext() {
		return mCurrentIndex < mFiles.size();
	}

	/**
	 * @see org.apache.uima.collection.CollectionReader#getNext(org.apache.uima.cas.CAS)
	 */
	public void getNext(CAS aCAS) throws IOException, CollectionException {
		JCas jcas;
		try {
			jcas = aCAS.getJCas();
		} catch (CASException e) {
			throw new CollectionException(e);
		}

		// open input stream to file
		File file = (File) mFiles.get(mCurrentIndex++);

		// call Tika wrapper 
		try {
			tika.populateCASfromURL(aCAS, file.toURI().toURL(), this.mMIME, this.mLanguage);
		} catch (CASException e) {
		  String msg = String.format("Problem converting file: %s\t%s%n", file.toURI().toURL(), e.getMessage());
			getLogger().log(Level.WARNING, msg);
			throw new IOException(msg);
	    	//jcas.setDocumentText(" "); return;
		}
	}

	/**
	 * @see org.apache.uima.collection.base_cpm.BaseCollectionReader#close()
	 */
	public void close() throws IOException {
	}

	/**
	 * @see org.apache.uima.collection.base_cpm.BaseCollectionReader#getProgress()
	 */
	public Progress[] getProgress() {
		return new Progress[] { new ProgressImpl(mCurrentIndex, mFiles.size(),
				Progress.ENTITIES) };
	}

	/**
	 * Gets the total number of documents that will be returned by this
	 * collection reader. This is not part of the general collection reader
	 * interface.
	 * 
	 * @return the number of documents in the collection
	 */
	public int getNumberOfDocuments() {
		return mFiles.size();
	}

	@Override
	public void initialize() throws ResourceInitializationException {
		File directory = new File(
				((String) getConfigParameterValue(PARAM_INPUTDIR)).trim());
		mLanguage = (String) getConfigParameterValue(PARAM_LANGUAGE);
		mCurrentIndex = 0;

		mMIME = (String) getConfigParameterValue(FileSystemCollectionReader.PARAM_MIME);
		
		// initialise TIKA parser
		
		String tikaConfigURL = (String) getConfigParameterValue(tika_file_param);
		try {
			tika = new TIKAWrapper(tikaConfigURL);
		} catch (TikaException e) {
			throw new ResourceInitializationException(e);
		}
		
		// if input directory does not exist or is not a directory, throw
		// exception
		if (!directory.exists() || !directory.isDirectory()) {
			throw new ResourceInitializationException(
					ResourceConfigurationException.DIRECTORY_NOT_FOUND,
					new Object[] { PARAM_INPUTDIR,
							this.getMetaData().getName(), directory.getPath() });
		}

		// get list of files (not subdirectories) in the specified directory
		mFiles = new ArrayList<File>();
		File[] files = directory.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (!files[i].isDirectory()) {
				mFiles.add(files[i]);
			}
		}
	}

}
