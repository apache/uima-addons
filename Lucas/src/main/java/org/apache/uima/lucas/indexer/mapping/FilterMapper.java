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

import java.util.Properties;

import org.xml.sax.Attributes;

public class FilterMapper implements ElementMapper<FilterDescription> {
	private static final String TRUE = "true";
	private final String FILTER_CLASS_NAME = "className"; // filter attribute
	// for class name of
	// a
	// {@link
	// org.apache.lucene.analysis.TokenFilter}

	private final String FILTER_FACTORY_CLASS_NAME = "factoryClassName"; // filter
	// attribute
	// for
	// factory class name of a
	// {@link
	// org.apache.lucene.analysis.TokenFilter}

	private final String FILTER_NAME = "name"; // filter attribute for factory
	// id, needed
	// for factory reuse

	private final String FILTER_REUSE_FACTORY = "reuseFactory"; // filter

	// attribute for
	// factory id,

	public FilterDescription mapElement(Attributes attributes) {
		FilterDescription filterDescription = new FilterDescription();
		for (int i = 0; i < attributes.getLength(); i++) {
			String name = attributes.getQName(i);
			String value = attributes.getValue(i);

			if (name.equals(FILTER_NAME))
				filterDescription.setName(value);
			else if (name.equals(FILTER_CLASS_NAME))
				filterDescription.setClassName(value);
			else if (name.equals(FILTER_FACTORY_CLASS_NAME))
				filterDescription.setFactoryClassName(value);
			else if (name.equals(FILTER_REUSE_FACTORY)) {
				if (value != null && value.equals(TRUE))
					filterDescription.setReuseFactory(true);
			} else {
				Properties properties = filterDescription.getProperties();
				properties.setProperty(name, value);
			}
		}
		
		return filterDescription;
	}

}
