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

import java.io.Serializable;


// Helper class

public class NGram implements Serializable{

  private static final long serialVersionUID = 1L;
  public String tag1;
  public String tag2;
  public String tag3;
  
 
  public NGram(){
    this(null,null,null);
  }
  public NGram(String tag1){
    this(tag1, null,null);
  }
  
  public NGram(String tag1, String tag2){
    this(tag1, tag2, null);
  }
  
  public NGram(String tag1, String tag2, String tag3 ){
    this.tag1 = tag1;
    this.tag2 = tag2;
    this.tag3 = tag3;
  }
  
 
 
  @Override
  public boolean equals(Object o) {
    if (o instanceof NGram) {
      NGram n = (NGram) o;
      // case: trigram
      if (this.tag2!=null && this.tag3!=null){
        return (this.tag1.equals(n.tag1)) && (this.tag2.equals(n.tag2)) && (this.tag3.equals(n.tag3));
      }
      // case: bigram
      else if (this.tag2!=null && this.tag3 == null){
        return (this.tag1.equals(n.tag1)) && (this.tag2.equals(n.tag2));
      } 
      // case onegram
      else {
        return (this.tag1.equals(n.tag1));
      }
     } else{
    return false;
     }
  }
  
  
  
  
  public int hashCode(){
    int hash = 7;
    hash = 31 * hash + this.tag1.hashCode();
    if (this.tag2!=null){
      hash = 31 * hash + this.tag2.hashCode();
    }
    if (this.tag3!=null){
     hash = 31 * hash + this.tag3.hashCode();
    }
    return hash;
  }

  
  /* in case we would use char[] instead of Strings.. didn't help to reduce the model:(
  public boolean equals(Object o) {
    if (o instanceof NGram) {
      NGram n = (NGram) o;
      // case: trigram
      if (this.tag2!=null && this.tag3!=null){
        return (Arrays.equals(this.tag1, n.tag1)) && (Arrays.equals(this.tag2, n.tag2)) && (Arrays.equals(this.tag3, n.tag3));
      }
      // case: bigram
      else if (this.tag2!=null && this.tag3 == null){
        return (Arrays.equals(this.tag1, n.tag1)) && (Arrays.equals(this.tag2, n.tag2));
      } 
      // case onegram
      else {
        return (Arrays.equals(this.tag1, n.tag1));
      }
     } else{
    return false;
     }
  }
  
  public int hashCode(){
    int hash = 7;
    hash = 31 * hash + Arrays.hashCode(this.tag1);
    if (this.tag2!=null){
      hash = 31 * hash + Arrays.hashCode(this.tag1);
    }
    if (this.tag3!=null){
     hash = 31 * hash + Arrays.hashCode(this.tag1);
    }
    return hash;
  }
  
  */
  
}


