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


// Java dependencies
import java.util.ArrayList;
// UIMA dependencies
import org.apache.uima.UIMAFramework;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.examples.tagger.trainAndTest.ModelGeneration;
import org.apache.uima.examples.tagger.trainAndTest.Token;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Level;

/**
 * This analysis engine trains an N-gram model for the HMM tagger. It uses
 * a training corpus as reference. This corpus must contain annotations on 
 * words with an attribute corresponding to the POS value to be learned.
 * 
 * The configuration of this analysis engine is done through several parameters:
 * <ul>
 * <li>View: - the view from which the tokens will be extracted</li>
 * <li>ModelExportFile: - the path where the model will be written</li>
 * <li>FeaturePathPOS: - feature path to the value of the POS to be learned.
 * The annotation should exactly cover a "word".</li>
 * </ul>
 * 
 * <b>BEWARE: this analysis engine does not allow multiple deployment !</b>
 * 
 * NB. At the moment: both bi and trigram statistics are saved in one model file. 
 */
public class HMMModelTrainer extends JCasAnnotator_ImplBase {
	
	/** Name of the parameter for the view */
	public static String PARAM_VIEW  = "View";
	/** Name of the parameter for the model export path */
	public static String PARAM_FILE  = "ModelExportFile";
	/** Name of the parameter for the feature path to the POS */
	public static String PARAM_POSFP = "FeaturePathPOS";
	
	/** The view from which the tokens will be extracted */
	private String theView;
	/** The path to the file where the model will be written */
	private String fileOutput;
	/** The type from which we will extract the tags to learn */
	private String theTokenTypeName;
	/** The name of the attribute where the POS to learn is stored */
	private String thePOSAttribute;
	
	/** The list of collected tokens */
	private ArrayList<Token> theLearnedTokens;

	/**
	 * Initialization of the component
	 */
	public void initialize(UimaContext aContext)
			throws ResourceInitializationException {
		super.initialize(aContext);
		// Configure the component
		theView = 
			(String) aContext.getConfigParameterValue(PARAM_VIEW);
		fileOutput = 
				(String) aContext.getConfigParameterValue(PARAM_FILE);
		// Compute the type and the attribute name
		String fpPOS = 
			(String) aContext.getConfigParameterValue(PARAM_POSFP);
		Integer idx = fpPOS.lastIndexOf(":");
		if (idx >= 0) {
			theTokenTypeName = fpPOS.substring(0, idx);
			thePOSAttribute  = fpPOS.substring(idx+1);
		} else {
			throw new ResourceInitializationException("The feature path passed " +
					"in parameter ('"+fpPOS+"') is not valid. " +
					"It should be like : 'type.name:attribute'", null);
		}	
		// Prepare the list of tokens
		theLearnedTokens = new ArrayList<Token>();
	}
	
	/**
	 * Processing.
	 * Browse the annotations of the type theTokenTypeName that must inherit 
	 * from the type tcas.Annotation and build the list of tokens that will be 
	 * learned by the HMMTagger. 
	 */
	@Override
	public void process(JCas cas) throws AnalysisEngineProcessException {
		try {
			// Select the view we will work on
			JCas workingView = cas.getView(theView);
			// Iterate over the type we will learn from
			Type tokenType = 
				workingView.getTypeSystem().getType(theTokenTypeName);
			if (tokenType != null) {
				// Compute the feature for the POS value
				Feature featPOS = 
					tokenType.getFeatureByBaseName(thePOSAttribute);
				// Browse these annotation and create the tokens to be learned
				FSIterator<Annotation> itPOS = 
					workingView.getAnnotationIndex(tokenType).iterator();
				Integer c = 0;
				Integer i = 0;
				while ( itPOS.hasNext() ) {
					Annotation token = itPOS.next();
					// Create a new token to be learned and add it to the list,
					// if the POS value is relevant (not null)
					Token tokenTmp = new Token();
					tokenTmp.word  = token.getCoveredText();
					tokenTmp.pos   = token.getStringValue(featPOS);
					if (tokenTmp.pos != null) {
						c++;
						theLearnedTokens.add(tokenTmp);
					} else {
						UIMAFramework.getLogger().log(Level.WARNING,
								"Ignoring token "+tokenTmp.word+" because its " +
								"POS value is null");
						i++;
					}
				}
				// Log the number of tokens collected
				UIMAFramework.getLogger().log(Level.INFO,
						c + " tokens to be learned added, " + i +
						" tokens ignored.");
			} else {
				throw new AnalysisEngineProcessException(
					"The type '"+theTokenTypeName+"', passed as token type " +
					"for the training is not in the type system.", null);
			}
		} catch (CASException e) {
			throw new AnalysisEngineProcessException(e);
		}
	}

	/**
	 * Called at the end of the processing.
	 * When the whole collection has been processed, we create the model
	 * from the elements we collected.
	 */
	@Override
	public void collectionProcessComplete() throws AnalysisEngineProcessException {
		try {
			UIMAFramework.getLogger().log(Level.INFO, 
					"Generation of model '"+fileOutput+"' with " + 
					theLearnedTokens.size()+" tokens to be learned.");
			ModelGeneration md = 
				new ModelGeneration(theLearnedTokens,fileOutput);
			md.init();
			UIMAFramework.getLogger().log(Level.INFO, "Model generated: " +
			md.suffix_tree.size() + " leaves suffix tree, " +
			md.transition_probs.size() + " transitions probabilities, " +
			md.word_probs.size() + " tag probabilities.");
		} catch (Exception e) {
			// Because UIMA filter all exceptions... we try to catch those
			UIMAFramework.getLogger().log(Level.SEVERE,
					"Something happened : " + e.getMessage());
			e.printStackTrace();
			throw new AnalysisEngineProcessException(e);
		}
	}
}

