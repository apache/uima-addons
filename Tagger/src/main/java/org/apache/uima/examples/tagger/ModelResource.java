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

package org.apache.uima.examples.tagger;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import org.apache.uima.examples.tagger.trainAndTest.ModelGeneration;
import org.apache.uima.resource.DataResource;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.SharedResourceObject;

/**
 * Implementation of the model resource.
 */
public class ModelResource implements IModelResource, SharedResourceObject {

  private ModelGeneration model = null;

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.examples.tagger.IModelResource#getModel()
   */
  public ModelGeneration getModel() {
    return this.model;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.resource.SharedResourceObject#load(org.apache.uima.resource.DataResource)
   */
  public void load(DataResource data) throws ResourceInitializationException {
    if (data != null) {
      try {
        InputStream inputStream = data.getInputStream();
        this.model = null;

        ObjectInputStream p = new ObjectInputStream(inputStream);
        this.model = (ModelGeneration) p.readObject();
        p.close();

      } catch (IOException e) {
        throw new ResourceInitializationException(e);
      } catch (ClassNotFoundException e) {
        throw new ResourceInitializationException(e);
      }
    }

  }

}
