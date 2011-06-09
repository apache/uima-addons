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

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.FSDirectory;
import org.apache.uima.resource.DataResource;
import org.apache.uima.resource.ResourceInitializationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class IndexWriterProviderImplTest {

  private static final String fileSep = System.getProperty("file.separator");

  private static final String TEST_INDEX = "src" + fileSep + "test" + fileSep + "resources"
      + fileSep + "test-index";

  private static final String RESOURCES_TEST_INDEX_PROPERTIES = "src" + fileSep + "test" + fileSep
      + "resources" + fileSep + "IndexWriter.properties";

  private IndexWriterProviderImpl indexWriterProviderImpl;

  private DataResource dataResource;

  private InputStream propertiesInputStream;

  @Before
  public void setUp() throws IOException {
    indexWriterProviderImpl = new IndexWriterProviderImpl();
    dataResource = createMock(DataResource.class);
    FileInputStream fileInputStream = new FileInputStream(RESOURCES_TEST_INDEX_PROPERTIES);
    propertiesInputStream = new BufferedInputStream(fileInputStream);
  }

  @After
  public void tearDown() throws Exception {

    FSDirectory directory = (FSDirectory) indexWriterProviderImpl.getIndexWriter().getDirectory();
    File directoryFile = directory.getFile();

    directory = FSDirectory.getDirectory(directoryFile);
    IndexWriter.unlock(directory);

    for (String file : directory.list())
      directory.deleteFile(file);

    directory.getFile().delete();
  }

  @Test
  public void testLoadData() throws IOException, ResourceInitializationException {

    expect(dataResource.getInputStream()).andReturn(propertiesInputStream);
    replay(dataResource);

    indexWriterProviderImpl.load(dataResource);
    IndexWriter indexWriter = indexWriterProviderImpl.getIndexWriter();
    FSDirectory fsDirectory = (FSDirectory) indexWriter.getDirectory();

    String hostname = getHostName();
    String pid = getPID();

    String writerPath = fsDirectory.getFile().getAbsolutePath();
    assertTrue(writerPath.contains(
        TEST_INDEX + "-" + hostname + "-" + pid));
    assertEquals(513, indexWriter.getRAMBufferSizeMB(), 0.5);
    assertEquals(9999, indexWriter.getMaxFieldLength(), 0.5);
    
    String random = writerPath.substring(writerPath.lastIndexOf("-")+1);
    assertTrue(random.matches("\\d+"));
  }

  protected String getPID() {
    String id = ManagementFactory.getRuntimeMXBean().getName();
    return id.substring(0, id.indexOf("@"));
  }

  public String getHostName() {
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
