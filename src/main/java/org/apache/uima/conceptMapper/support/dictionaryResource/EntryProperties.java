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

import java.io.Serializable;

public class EntryProperties implements Serializable{

	/**
     * 
     */
    private static final long serialVersionUID = 1L;
	private String [] properties;
	private EntryPropertiesRoot entryPropertiesRoot;

	/**
	 * 
	 * @param factory
	 * @param maxNumberOfProperties
	 * @throws NullPointerException
	 * 
	 * should only be called by factory
	 */
	public EntryProperties (EntryPropertiesRoot root, int maxNumberOfProperties) throws NullPointerException
	{
		if (root == null)
		{
			throw new NullPointerException ("EntryPropertiesRoot cannot be null");
		}
		entryPropertiesRoot = root;
		properties = new String [maxNumberOfProperties];
	}
	
	public EntryProperties (EntryProperties toCopyFrom)
	{
		entryPropertiesRoot = toCopyFrom.entryPropertiesRoot;
		properties = toCopyFrom.properties.clone();
	}
	
	
	public String getProperty(String propertyName) {
		return getProperty(propertyName, null);
	}
	
	public String getProperty(String propertyName, String defaultValue) {
		int propertyID = entryPropertiesRoot.getPropertyID (propertyName);
		if (propertyID < 0)
		{
			return defaultValue;
		}
		else
		{
			return properties[propertyID];
		}
	}

	public void setProperty(String propertyName, String propertyValue) {
		int propertyID = entryPropertiesRoot.getPropertyID (propertyName);
		if (propertyID < 0)
		{
			// do nothing
		}
		else
		{
			properties[propertyID] = propertyValue;
		}
	}

}
