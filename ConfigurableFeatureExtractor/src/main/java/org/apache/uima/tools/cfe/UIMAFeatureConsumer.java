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

package org.apache.uima.tools.cfe;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.uima.cas.CAS;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;
import org.apache.uima.tools.cfe.support.ComparableArray;

;

public class UIMAFeatureConsumer
    extends CasConsumer_ImplBase
{
    public static final String PARAM_OUTPUTDIRECTORY = "OutputDirectory";
    

    private String m_outdir;
    private UIMAFeatureMatcher m_uimaFM;


    public void initialize ()
    throws ResourceInitializationException
    {
        super.initialize();
        
        try {
            m_uimaFM = new UIMAFeatureMatcher(
                    ((Boolean)getConfigParameterValue(CommonFeatureMatcher.PARAM_INCLUDETARGETID)).booleanValue());
            
            String cfgFile = (String)getConfigParameterValue(CommonFeatureMatcher.PARAM_CONFIGURATIONFILE);
            if (null != cfgFile) {
                Boolean isXMLBeansarser = (Boolean)getConfigParameterValue(CommonFeatureMatcher.PARAM_XMLBEANSPARSER);
                m_uimaFM.initialize(cfgFile, null == isXMLBeansarser ? false : isXMLBeansarser.booleanValue());;
            }

            m_outdir = (String)getConfigParameterValue(PARAM_OUTPUTDIRECTORY);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new ResourceInitializationException(e);
        }
    }
    
    public void processCas (CAS cas)
    throws ResourceProcessException
    {
        try {

            JCas jcas = cas.getJCas();
            
            String docId = CommonFeatureMatcher.getDocumentId(jcas, null);
            System.out.println("Processing " + docId);
            
            m_uimaFM.processJCas(jcas);
            if (m_uimaFM.m_featureImages.isEmpty()) {
                return;
            }
            
            File f = new File(m_outdir);
            if (!f.exists()) {
                System.err.println("Directory " + m_outdir + " does not exist, creating");
                if (!f.mkdirs()) {
                    throw new IOException(
                            String.format("failed to create an output directory \"%s\"", m_outdir));
                }
            }
            
            FileOutputStream fos = new FileOutputStream(m_outdir + "/" + docId + ".fve");
            for (Iterator<ComparableArray> it = m_uimaFM.m_featureImages.keySet().iterator(); it.hasNext();) {
                fos.write((m_uimaFM.m_featureImages.get(it.next()) + "\n").getBytes());
            }
            fos.close();
        } 
        catch (Exception e) {
            throw new ResourceProcessException(e);
        }   
    }
}
