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

package org.apache.uima.examples.tagger.trainAndTest;

import java.util.List;

/**
 * Defines mapping for a tagset. For example, one may wish to map a more detailed tagset to a less
 * distinctive one (i.e. tell a program to tag all verbs as just {@code VERB} instead of
 * differentiating between {@code verb infinitive}, {@code verb imperative}, etc ... Receives a
 * List of {@code Tokens} to transform and returns a List of {@code Tokens} with mapped parts of
 * speech
 */
public interface MappingInterface {

  public List<Token> map_tags(List<Token> tokens);

}
