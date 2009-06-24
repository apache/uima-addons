package org.apache.uima.conceptMapper.support.dictionaryResource;

import java.util.Arrays;

public class EntryPropertiesFactory {

	private static String [] propertyNames = null;
	
	public static EntryPropertiesFactory create (String [] thePropertyNames)
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
