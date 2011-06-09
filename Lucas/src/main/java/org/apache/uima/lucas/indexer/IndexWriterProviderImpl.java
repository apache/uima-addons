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
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.uima.resource.DataResource;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.SharedResourceObject;

public class IndexWriterProviderImpl implements IndexWriterProvider, SharedResourceObject{

  public static final String USE_COMPOUND_FILE_FORMAT_PROPERTY = "useCompoundFileFormat";
  public static final String RAMBUFFER_SIZE_PROPERTY = "RAMBufferSize";
  public static final String INDEX_PATH_PROPERTY = "indexPath";
  public static final String MAX_FIELD_LENGTH_PROPERTY = "maxFieldLength";
  private static final String UNIQUE_INDEX_PROPERTY = "uniqueIndex";

  private static Set<Integer> randomNumbers = new HashSet<Integer>();

  public IndexWriter indexWriter;

  public IndexWriter getIndexWriter() {
    return indexWriter;
  }

  public void load(DataResource dataResource) throws ResourceInitializationException {
    Properties properties = loadProperties(dataResource);
    
    String indexPath = getIndexPath(properties);
    if (getUniqueIndexOrDefault(properties))
      indexPath = createUniqueIndexPath(indexPath);

    MaxFieldLength maxFieldLength = getMaxFieldLengthOrDefault(properties);
    createIndexWriter(indexPath, maxFieldLength);
    
    Double ramBufferSize = getRAMBufferSizeOrDefault(properties);    
    Boolean useCompoundFileFormat = getUseCompoundFormatOrDefault(properties);
    configureIndexWriter(ramBufferSize, useCompoundFileFormat);
  }

  private Properties loadProperties(DataResource dataResource)
          throws ResourceInitializationException {
  
    Properties properties = new Properties();
    try {
      properties.load(dataResource.getInputStream());
    } catch (IOException e) {
        throw new ResourceInitializationException(e);
    }
    
    return properties;
  }

  private Boolean getUseCompoundFormatOrDefault(Properties properties) {
    String useCompoundFileFormatAsString = properties.getProperty(USE_COMPOUND_FILE_FORMAT_PROPERTY);
    Boolean useCompoundFileFormat = true;
    if( useCompoundFileFormatAsString != null )
      useCompoundFileFormat = new Boolean(useCompoundFileFormatAsString);
    
    return useCompoundFileFormat;
  }

  private String getIndexPath(Properties properties){
    return properties.getProperty(INDEX_PATH_PROPERTY);
  }
  

  private synchronized int createRandom() {
          Random randomGenerator = new Random();
          int randomInt = randomGenerator.nextInt(1000000);
          while (randomNumbers.contains(randomInt)) {
              randomInt = randomGenerator.nextInt(1000000);
          }
          randomNumbers.add(randomInt);
          return randomInt;
      }

  private String createUniqueIndexPath(String indexPath){
    String uniqueIndexPath = indexPath + "-" + getHostName() + "-" + getPID() + 
                             "-" + createRandom();
    return uniqueIndexPath;
  }

  private Boolean getUniqueIndexOrDefault(Properties properties) {
    String uniqueIndexAsString = properties.getProperty(UNIQUE_INDEX_PROPERTY);
    Boolean uniqueIndex = false;
    if( uniqueIndexAsString != null )
      uniqueIndex = new Boolean(uniqueIndexAsString);
    
    return uniqueIndex;
  }

  private Double getRAMBufferSizeOrDefault(Properties properties) {
    String ramBufferSizeAsString = properties.getProperty(RAMBUFFER_SIZE_PROPERTY);
    Double ramBufferSize = IndexWriter.DEFAULT_RAM_BUFFER_SIZE_MB;
    if( ramBufferSizeAsString != null )
      ramBufferSize = new Double(ramBufferSizeAsString);
    return ramBufferSize;
  }
  
  private MaxFieldLength getMaxFieldLengthOrDefault(Properties properties) {
    String maxFieldLengthAsString = properties.getProperty(MAX_FIELD_LENGTH_PROPERTY);
    if( maxFieldLengthAsString == null )
      return IndexWriter.MaxFieldLength.UNLIMITED; 
    else{
      Integer maxFieldLength = new Integer(maxFieldLengthAsString);
      return new MaxFieldLength(maxFieldLength);
    }        
  }

  private void createIndexWriter(String indexPath, MaxFieldLength maxFieldLength) throws ResourceInitializationException {
    try {
      indexWriter = new IndexWriter(indexPath, new StandardAnalyzer(), true, maxFieldLength);
    } catch (CorruptIndexException e) {
      throw new ResourceInitializationException(e);
    } catch (LockObtainFailedException e) {
      throw new ResourceInitializationException(e);    
    } catch (IOException e) {
      throw new ResourceInitializationException(e);    }
  }

  private void configureIndexWriter(Double ramBufferSize, Boolean useCompoundFileFormat) {
    indexWriter.setRAMBufferSizeMB(ramBufferSize);
    indexWriter.setUseCompoundFile(true);
  }
  
  private String getPID() {
    String id = ManagementFactory.getRuntimeMXBean().getName();
    return id.substring(0, id.indexOf("@"));
  }

  private String getHostName() {
    InetAddress address;
    String hostName;
    try {
      address = InetAddress.getLocalHost();
      hostName = address.getHostName();
    } catch (UnknownHostException e) {
      throw new IllegalStateException(e);
    }
    return hostName;
  }

}
