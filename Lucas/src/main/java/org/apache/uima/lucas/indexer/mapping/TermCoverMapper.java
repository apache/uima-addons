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

import org.xml.sax.Attributes;

/**
 * An {@ink ElementMapper} for {@link TermCoverDescription}s
 *
 */
public class TermCoverMapper implements ElementMapper<TermCoverDescription> {

	private static final String COVER_DEFINITION_FILE = "coverDefinitionFile";

	private static final String GENERATE_FIELDNAME_METHOD = "generateFieldNameMethod";

	private static final String IGNORE_CASE_OF_SELECTED_TERMS = "ignoreCaseOfSelectedTerms";

	/**
	 * Indicates that the cover subset name is appended to the field name
	 * specified in mapping file to generate the field name for Lucene
	 * documents.
	 */
	public static final String USE_SUBSET_NAME_APPEND = "append";

	/**
	 * Indicates that the cover subset name is prepended to the field name
	 * specified in the mapping file to generate the field name Lucene
	 * documents.
	 */
	public static final String USE_SUBSET_NAME_PREPEND = "prepend";

	/**
	 * Indicates that the field name in the mapping file is replaced by the
	 * cover subset name to generate the field name for Lucene documents.
	 */
	public static final String USE_SUBSET_NAME_REPLACE = "replace";

	private static final String TRUE = "true";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.uima.lucas.indexer.mapping.ElementMapper#mapElement(org.xml
	 * .sax.Attributes)
	 */
	public TermCoverDescription mapElement(Attributes attributes) {
		TermCoverDescription termCoverDescription = new TermCoverDescription();
		for (int i = 0; i < attributes.getLength(); i++) {
			String name = attributes.getQName(i);
			String value = attributes.getValue(i);

			if (name.equals(COVER_DEFINITION_FILE)) {
				termCoverDescription.setCoverDefinitionFile(value);
			} else if (name.equals(GENERATE_FIELDNAME_METHOD)) {
				if (!value.equals(USE_SUBSET_NAME_APPEND)
						&& !value.equals(USE_SUBSET_NAME_PREPEND)
						&& !value.equals(USE_SUBSET_NAME_REPLACE))
					throw new IllegalArgumentException(
							"The method to combine the original field name" +
							" with a cover subset name to generate the final Lucene field name must be one of \""
									+ USE_SUBSET_NAME_APPEND
									+ "\", \""
									+ USE_SUBSET_NAME_PREPEND
									+ "\" or \""
									+ USE_SUBSET_NAME_REPLACE
									+ "\" but was: \"" + value);
				termCoverDescription.setGenerateFieldNameMethod(value);
			} else if (name.equals(IGNORE_CASE_OF_SELECTED_TERMS)) {
				if (value != null && value.equals(TRUE))
					termCoverDescription.setIgnoreCaseOfSelectedTerms(true);
			}
		}
		return termCoverDescription;
	}
}
