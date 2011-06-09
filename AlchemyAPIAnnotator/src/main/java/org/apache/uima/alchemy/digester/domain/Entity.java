/**
 * 	Licensed to the Apache Software Foundation (ASF) under one
 * 	or more contributor license agreements.  See the NOTICE file
 * 	distributed with this work for additional information
 * 	regarding copyright ownership.  The ASF licenses this file
 * 	to you under the Apache License, Version 2.0 (the
 * 	"License"); you may not use this file except in compliance
 * 	with the License.  You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * 	Unless required by applicable law or agreed to in writing,
 * 	software distributed under the License is distributed on an
 * 	"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * 	KIND, either express or implied.  See the License for the
 * 	specific language governing permissions and limitations
 * 	under the License.
 */
package org.apache.uima.alchemy.digester.domain;

public class Entity {

  private String type;

  private String relevance;

  private String count;

  private String text;

  private Disambiguated disambiguated;

  private Quotations quotations;
  
  private DocumentSentiment sentiment;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getRelevance() {
    return relevance;
  }

  public void setRelevance(String relevance) {
    this.relevance = relevance;
  }

  public String getCount() {
    return count;
  }

  public void setCount(String count) {
    this.count = count;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public void setDisambiguated(Disambiguated disambiguated) {
    this.disambiguated = disambiguated;
  }

  public Disambiguated getDisambiguated() {
    return disambiguated;
  }

  public void setQuotations(Quotations quotations) {
    this.quotations = quotations;
  }

  public Quotations getQuotations() {
    return quotations;
  }

  public void setSentiment(DocumentSentiment sentiment) {
    this.sentiment = sentiment;
  }

  public DocumentSentiment getSentiment() {
    return sentiment;
  }
}
