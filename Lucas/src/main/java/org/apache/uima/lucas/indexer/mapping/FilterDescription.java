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

public class FilterDescription implements Locateable{

	private String className;

	private Properties properties;

	private String factoryClassName;

	private String name;

	private boolean reuseFactory;
	
	private int line;

	private int column;
	
	public FilterDescription() {
		properties = new Properties();
	}

	public FilterDescription(String className, String factoryClassName,
			String factoryId, boolean reuseFactory, Properties properties) {
		super();
		this.className = className;
		this.factoryClassName = factoryClassName;
		this.name = factoryId;
		this.reuseFactory = reuseFactory;
		this.properties = properties;
	}

	public String getClassName() {
		return className;
	}

	public Properties getProperties() {
		return properties;
	}

	public String getFactoryClassName() {
		return factoryClassName;
	}

	public String getName() {
		return name;
	}

	public boolean isReuseFactory() {
		return reuseFactory;
	}

	protected void setClassName(String className) {
		this.className = className;
	}

	protected void setProperties(Properties properties) {
		this.properties = properties;
	}

	protected void setFactoryClassName(String factoryClassName) {
		this.factoryClassName = factoryClassName;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected void setReuseFactory(boolean reuseFactory) {
		this.reuseFactory = reuseFactory;
	}

	public int getColumnNumber() {
		return column;
	}

	public int getLineNumber() {
		return line;
	}

	public void setColumnNumber(int columnNumber) {
		this.column = columnNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.line = lineNumber;
	}

}
