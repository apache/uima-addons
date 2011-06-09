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

public class LanguageDetectionResults extends Results {

  private String iso6391;

  private String iso6392;

  private String iso6393;

  private String ethnologue;

  private String nativeSpeakers;

  private String wikipedia;

  public String getIso6391() {
    return iso6391;
  }

  public void setIso6391(String iso6391) {
    this.iso6391 = iso6391;
  }

  public String getIso6392() {
    return iso6392;
  }

  public void setIso6392(String iso6392) {
    this.iso6392 = iso6392;
  }

  public String getIso6393() {
    return iso6393;
  }

  public void setIso6393(String iso6393) {
    this.iso6393 = iso6393;
  }

  public String getEthnologue() {
    return ethnologue;
  }

  public void setEthnologue(String ethnologue) {
    this.ethnologue = ethnologue;
  }

  public String getNativeSpeakers() {
    return nativeSpeakers;
  }

  public void setNativeSpeakers(String nativeSpeakers) {
    this.nativeSpeakers = nativeSpeakers;
  }

  public String getWikipedia() {
    return wikipedia;
  }

  public void setWikipedia(String wikipedia) {
    this.wikipedia = wikipedia;
  }

}
