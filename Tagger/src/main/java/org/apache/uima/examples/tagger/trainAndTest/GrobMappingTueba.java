/*
 *Licensed to the Apache Software Foundation (ASF) under one
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
 * 
 */

package org.apache.uima.examples.tagger.trainAndTest;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.examples.tagger.trainAndTest.Token;

/**
 *
 */
public class GrobMappingTueba implements MappingInterface{

	    
      /**
       * Defines mapping for List<{@link Token}>
       * E.g. if we need to map tags, given a list of {@code Tokens}, we need to map the 
       * {@code pos} field of every {@code Token} to a different {@code pos}.  
       */
	    public List<Token> map_tags(List<Token> tokens){
		  
	    	
		//    for (int i=0; i<sentences.size(); i++){ // iterate over sentences
		      
		         List<Token> tokens2 = new ArrayList<Token>(tokens.size());
		         
		         for (int x=0; x<tokens.size(); x++){ // iterate over tokens of the sentence with their corresponding POS
		           Token current_token = (Token)tokens.get(x);
		          
		           if(current_token.pos.startsWith("N")){
		        	   current_token.pos="Noun";
		           } 
		           if(current_token.pos.startsWith("V")){
		        	   current_token.pos="Verb";
		           } 
		           if(current_token.pos.startsWith("ADJ")){
		        	   current_token.pos="Adjective";
		           } 
		           if(current_token.pos.startsWith("P")){
		        	   current_token.pos="Pronoun";
		           } 
		           if(current_token.pos.startsWith("KO")){
		        	   current_token.pos="Conjunction";
		           } 
		           if(current_token.pos.startsWith("AP")){
		        	   current_token.pos="Preposition";
		           } 
		           if(current_token.pos.startsWith("PTK")){
		        	   current_token.pos="PTK";
		           } 
		           if(current_token.pos.startsWith("ADV")){
		        	   current_token.pos="Adverb";
		           } 
		           
		           if(current_token.pos.startsWith("ART")){
		        	   current_token.pos="Article";
		           } 
		           
		           if(current_token.pos.startsWith("ITJ")){
		        	   current_token.pos="Interjection";
		           } 
		           
		           Token zwischen = new Token(current_token.word, current_token.pos);
		   
		           tokens2.add(zwischen);
		         }
            return tokens2;
            
		  }
	    
}
