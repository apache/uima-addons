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

package org.apache.uima.lucas.indexer;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.uima.lucas.indexer.analysis.TokenFilterFactory;
import org.apache.uima.lucas.indexer.mapping.FilterDescription;
import org.apache.uima.lucas.indexer.mapping.Locateable;

public class FilterBuilder {

  public static final Logger LOGGER = Logger.getLogger(FilterBuilder.class);

  private Map<String, TokenFilterFactory> cachedFactories;
  private Locateable currentLocateable;
  
  public FilterBuilder(Map<String, TokenFilterFactory> predefinedFactories) {
    cachedFactories = predefinedFactories;
  }

  public TokenStream filter(TokenStream tokenStream,
          Collection<FilterDescription> filterDescriptions) throws FilterBuildingException {

    TokenStream filteredTokenStream = tokenStream;
    for (FilterDescription filterDescription : filterDescriptions) {
      String filterName = filterDescription.getName();
      String factoryClassName = filterDescription.getFactoryClassName();
      String className = filterDescription.getClassName();
      currentLocateable = filterDescription;
      
      // use cached factory to create filter 
      if (filterName != null && factoryClassName == null) {
        TokenFilterFactory tokenFilterFactory = cachedFactories.get(filterName);
        if (tokenFilterFactory == null)
          throw createException("No factory registered for " + filterName
                  + ". Please provide a factory");
        
        filteredTokenStream = createTokenFilter(filteredTokenStream, filterDescription, tokenFilterFactory);
      }
      // use single argument constructor of a token filter
      else if (className != null)
      {
        filteredTokenStream = createTokenFilterWithClassName(filteredTokenStream, className);
      }
      // use factory for creating the filter and cache it if needed
      else if (factoryClassName != null) 
      {
        TokenFilterFactory tokenFilterFactory = createTokenFilterFactory(factoryClassName);
        filteredTokenStream = createTokenFilter(filteredTokenStream, filterDescription, tokenFilterFactory);
        if( filterDescription.isReuseFactory() ){
          if( filterName == null )
            throw createException("Provide a name for factory reuse ");
          cachedFactories.put(filterName, tokenFilterFactory);
        }
      }
    }

    return filteredTokenStream;
  }

  private TokenFilter createTokenFilter(TokenStream filteredTokenStream,
          FilterDescription filterDescription, TokenFilterFactory tokenFilterFactory)
          throws FilterBuildingException {
    try {
      return tokenFilterFactory.createTokenFilter(filteredTokenStream,
              filterDescription.getProperties());
    } catch (IOException e) {
      throw createException("Can't build filter with description", e);
    }
  }

  private TokenFilterFactory createTokenFilterFactory(String factoryClassName)
          throws FilterBuildingException {
    try {
      Class<?> clazz = Class.forName(factoryClassName);
      Constructor<?>[] constructors = clazz.getConstructors();
      for (Constructor<?> constructor : constructors) {
        Class<?>[] parameters = constructor.getParameterTypes();
        if (parameters.length == 0)
          return (TokenFilterFactory) constructor.newInstance();
      }
    } catch (ClassNotFoundException e) {
      throw createException("Can't instantiate TokenFilterFactory " + factoryClassName);
    } catch (IllegalArgumentException e) {
      throw createException("Can't instantiate TokenFilterFactory " + factoryClassName);
    } catch (InstantiationException e) {
      throw createException("Can't instantiate TokenFilterFactory " + factoryClassName);
    } catch (IllegalAccessException e) {
      throw createException("Can't instantiate TokenFilterFactory " + factoryClassName);
    } catch (InvocationTargetException e) {
      throw createException("Can't instantiate TokenFilterFactory " + factoryClassName);
    }
    throw new FilterBuildingException("Class " + factoryClassName + " has no public no argument constructor!");
  }

  private TokenFilter createTokenFilterWithClassName(TokenStream tokenStream, String className)
          throws FilterBuildingException {
    try {
      Class<?> clazz = Class.forName(className);
      Constructor<?>[] constructors = clazz.getConstructors();
      for (Constructor<?> constructor : constructors) {
        Class<?>[] parameters = constructor.getParameterTypes();
        if (parameters.length == 1 && parameters[0].equals(TokenStream.class))
          return (TokenFilter) constructor.newInstance(tokenStream);
      }
    } catch (ClassNotFoundException e) {
      throw new FilterBuildingException("Can't instantiate TokenFilter " + className, e);
    } catch (IllegalArgumentException e) {
      throw new FilterBuildingException("Can't instantiate TokenFilter " + className, e);
    } catch (InstantiationException e) {
      throw new FilterBuildingException("Can't instantiate TokenFilter " + className, e);
    } catch (IllegalAccessException e) {
      throw new FilterBuildingException("Can't instantiate TokenFilter " + className, e);
    } catch (InvocationTargetException e) {
      throw new FilterBuildingException("Can't instantiate TokenFilter " + className, e);
    }    
    
    throw createException("Class " + className + " has no public single argument constructor!");
  }

  private FilterBuildingException createException(String message, Throwable cause){
	 String extendedMessage = message + " at line " + currentLocateable.getLineNumber();
	 return new FilterBuildingException(extendedMessage, cause);
  }

private FilterBuildingException createException(String message){
	  String extendedMessage = message + " at line " + currentLocateable.getLineNumber();
	  return new FilterBuildingException(extendedMessage);
  }

Map<String, TokenFilterFactory> getCachedFactories() {
    return cachedFactories;
  }

}
