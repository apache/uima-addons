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

import java.util.Arrays;

public class EntryPropertiesFactory {

	private static String [] propertyNames = null;
	
	public static synchronized EntryPropertiesFactory create (String [] thePropertyNames)
	{
		if (propertyNames == null)
		{
			propertyNames = new String [thePropertyNames.length];
			int i = 0;
			for (String prop : thePropertyNames)
			{
				propertyNames[i] = prop;
				i += 1;
			}
			Arrays.sort(propertyNames);
		}
		return new EntryPropertiesFactory ();
	}

	public static String [] propertyNames ()
	{
		return propertyNames;
	}
	
	public static int getPropertyID(String propertyName) {
		return Arrays.binarySearch(propertyNames, propertyName);
	}


	public EntryProperties newEntryProperties () throws NullPointerException
	{
		if (propertyNames == null)
		{
			throw new NullPointerException ("EntryProperties not initialized via EntryProperties.init()");
		}
		return new EntryProperties (this, propertyNames.length);
	}
	

}
