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

package org.apache.uima.lucas.indexer.mapping;

import org.xml.sax.Attributes;

public class FieldMapper implements ElementMapper<FieldDescription> {
	private static final String TRUE = "true";
	private final String FIELD_NAME = "name"; // defines the name of the field
	private final String FIELD_INDEX = "index"; // defines if the field should
												// be tokenized or not

	private final String FIELD_TERM_VECTOR = "termVector";

	private final String FIELD_STORED = "stored"; // yes if the field should be
													// stored (returnable);

	// no: false

	private final String FIELD_DELIMITER = "delimiter"; // use special
														// characters to limit
														// the tokens
	
	private final String FIELD_UNIQUE = "unique";

	// and build one string

	private final String FIELD_MERGE = "merge"; // use the tokenstream merger

	public FieldDescription mapElement(Attributes attributes) {
		FieldDescription fieldDescription = new FieldDescription();
		for (int i=0; i< attributes.getLength(); i++){
			String name = attributes.getQName(i);
			String value = attributes.getValue(i);
	
			if (name.equals(FIELD_INDEX))
				fieldDescription.setIndex(value);
			else if (name.equals(FIELD_MERGE)) {
				if (value != null && value.equals(TRUE))
					fieldDescription.setMerge(true);
			}
			else if (name.equals(FIELD_DELIMITER))
				fieldDescription.setDelimiter(value);
			else if (name.equals(FIELD_NAME))
				fieldDescription.setName(value);
			else if (name.equals(FIELD_STORED))
				fieldDescription.setStored(value);
			else if (name.equals(FIELD_TERM_VECTOR))
				fieldDescription.setTermVector(value);
			else if (name.equals(FIELD_UNIQUE)) {
				if (value != null && value.equals(TRUE))
					fieldDescription.setUnique(true);
			}
		}
		return fieldDescription;
	}

}
