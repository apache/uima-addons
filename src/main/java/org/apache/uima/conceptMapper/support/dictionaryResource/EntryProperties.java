package org.apache.uima.conceptMapper.support.dictionaryResource;

import java.io.Serializable;

public class EntryProperties implements Serializable{

	/**
     * 
     */
    private static final long serialVersionUID = 1L;
	private String [] properties;

	/**
	 * 
	 * @param factory
	 * @param maxNumberOfProperties
	 * @throws NullPointerException
	 * 
	 * should only be called by factory
	 */
	public EntryProperties (EntryPropertiesFactory factory, int maxNumberOfProperties) throws NullPointerException
	{
		if (factory == null)
		{
			throw new NullPointerException ("EntryProperties not initialized via EntryProperties.init()");
		}
		properties = new String [maxNumberOfProperties];
	}
	
	public EntryProperties (EntryProperties toCopyFrom)
	{
		properties = toCopyFrom.properties.clone();
	}
	
	
	public String getProperty(String propertyName) {
		return getProperty(propertyName, null);
	}
	
	public String getProperty(String propertyName, String defaultValue) {
		int propertyID = EntryPropertiesFactory.getPropertyID (propertyName);
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
		int propertyID = EntryPropertiesFactory.getPropertyID (propertyName);
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
