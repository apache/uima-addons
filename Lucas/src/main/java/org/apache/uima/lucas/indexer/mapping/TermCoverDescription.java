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

/**
 * 
 */
package org.apache.uima.lucas.indexer.mapping;

/**
 * A description of term covering
 */
public class TermCoverDescription implements Locateable {

	private String coverDefinitionFile;

	private String generateFieldNameMethod;

	private boolean ignoreCaseOfSelectedTerms;

	private String fieldName;

	private int line;

	private int column;

	/**
	 * 
	 */
	public TermCoverDescription() {
		generateFieldNameMethod = TermCoverMapper.USE_SUBSET_NAME_APPEND;
		// Lets set the default value to true; this way, one can at least see
		// that there might be too many terms in a field. It's always hard so
		// see what's NOT there.
		ignoreCaseOfSelectedTerms = true;
	}

	/**
	 * @return the coverDefinitonFile
	 */
	public String getCoverDefinitionFile() {
		return coverDefinitionFile;
	}

	/**
	 * @param coverDefinitionFile
	 *            the coverDefinitionFile to set
	 */
	public void setCoverDefinitionFile(String coverDefinitionFile) {
		this.coverDefinitionFile = coverDefinitionFile;
	}

	/**
	 * @see #getGenerateFieldNameMethod()
	 * @param generateFieldNameMethod
	 */
	public void setGenerateFieldNameMethod(String generateFieldNameMethod) {
		this.generateFieldNameMethod = generateFieldNameMethod;

	}

	/**
	 * Returns a string identifying the method of dynamic field name generation.
	 * For valid values, please see below.
	 * 
	 * @see TermCoverMapper#USE_SUBSET_NAME_APPEND
	 * @see TermCoverMapper#USE_SUBSET_NAME_PREPEND
	 * @see TermCoverMapper#USE_SUBSET_NAME_REPLACE
	 * @return the method for combination of the cover subset and the field name
	 *         defined in the mapping file (prepending, appending, replacing)
	 */
	public String getGenerateFieldNameMethod() {
		return generateFieldNameMethod;
	}

	/**
	 * @param b
	 */
	public void setIgnoreCaseOfSelectedTerms(boolean ignoreCase) {
		this.ignoreCaseOfSelectedTerms = ignoreCase;

	}

	/**
	 * @return the ignoreCase
	 */
	public boolean getIgnoreCaseOfSelectedTerms() {
		return ignoreCaseOfSelectedTerms;
	}

	/**
	 * @return the fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * @param fieldName
	 *            the fieldName to set
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.uima.lucas.indexer.mapping.Locateable#setLineNumber(int)
	 */
	public void setLineNumber(int lineNumber) {
		line = lineNumber;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.uima.lucas.indexer.mapping.Locateable#getLineNumber()
	 */
	public int getLineNumber() {
		return line;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.uima.lucas.indexer.mapping.Locateable#setColumnNumber(int)
	 */
	public void setColumnNumber(int columnNumber) {
		column = columnNumber;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.uima.lucas.indexer.mapping.Locateable#getColumnNumber()
	 */
	public int getColumnNumber() {
		return column;
	}
}
