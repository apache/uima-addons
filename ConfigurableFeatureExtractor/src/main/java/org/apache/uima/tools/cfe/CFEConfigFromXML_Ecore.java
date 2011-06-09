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

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.uima.tools.cfe.config.BitsetFeatureValuesXML;
import org.apache.uima.tools.cfe.config.CFEDescriptorXML;
import org.apache.uima.tools.cfe.config.DocumentRoot;
import org.apache.uima.tools.cfe.config.EnumFeatureValuesXML;
import org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML;
import org.apache.uima.tools.cfe.config.GroupFeatureMatcherXML;
import org.apache.uima.tools.cfe.config.ObjectPathFeatureValuesXML;
import org.apache.uima.tools.cfe.config.PartialObjectMatcherXML;
import org.apache.uima.tools.cfe.config.PatternFeatureValuesXML;
import org.apache.uima.tools.cfe.config.RangeFeatureValuesXML;
import org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML;
import org.apache.uima.tools.cfe.config.TargetAnnotationXML;
import org.apache.uima.tools.cfe.config.impl.ConfigFactoryImpl;
import org.apache.uima.tools.cfe.config.impl.ConfigPackageImpl;
import org.apache.uima.tools.cfe.config.util.ConfigResourceFactoryImpl;
import org.apache.uima.tools.cfe.config.util.ConfigResourceImpl;
import org.apache.uima.tools.cfe.config.util.ConfigResourceUtil;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

public class CFEConfigFromXML_Ecore
{
    
    private CFEDescriptorXML m_CFEDescriptor;

    public CFEConfigFromXML_Ecore (String path) throws IOException
    {
        ConfigResourceUtil cru = ConfigResourceUtil.getInstance();
        DocumentRoot r = cru.load("file://" + path);
        m_CFEDescriptor = r.getCFEConfig();
    }


    private static boolean m_init = false;
    
    private DocumentRoot load(String filename) throws IOException
    {
        if (!m_init) {
            Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xml", new ConfigResourceFactoryImpl());
            ConfigPackageImpl.init();
            ConfigFactoryImpl.init();
            m_init = true;
        }
      
      ConfigResourceImpl resource = (ConfigResourceImpl)(new ConfigResourceFactoryImpl()).createResource(URI.createURI("file://" + filename));
      resource.load(null);
      DocumentRoot documentRoot = (DocumentRoot)resource.getContents().get(0);
      return documentRoot;
    }

    public CFEConfigFromXML_Ecore (String path, boolean manual) throws IOException
    {
        DocumentRoot r = load(path);
        m_CFEDescriptor = r.getCFEConfig();
    }

    EnumFeatureValues getEnumFeatureValues(EnumFeatureValuesXML efvs_xml)
    throws IOException, URISyntaxException
    {
        List<String> vals = efvs_xml.getValues();

        if ((1 == vals.size()) && (((String)vals.get(0))).startsWith("file://")) {
            return new EnumFeatureValues(new java.net.URI((String)vals.get(0)).getPath(), efvs_xml.isCaseSensitive());
        }
        return new EnumFeatureValues(vals.toArray(), efvs_xml.isCaseSensitive());
    }
    
    RangeFeatureValues getRangeFeatureValues(RangeFeatureValuesXML rfvs_xml)
    {
        String lb = ((String)rfvs_xml.getLowerBoundary()).trim();
        String ub = ((String)rfvs_xml.getUpperBoundary()).trim();
        
        return new RangeFeatureValues(Double.parseDouble(lb),
                                      rfvs_xml.isLowerBoundaryInclusive(),
                                      Double.parseDouble(ub),
                                      rfvs_xml.isUpperBoundaryInclusive());
    }

    BitsetFeatureValues getBitsetFeatureValues(BitsetFeatureValuesXML bfvs_xml)
    {
        return new BitsetFeatureValues(Integer.parseInt(bfvs_xml.getBitmask(), 16),
                                       bfvs_xml.isExactMatch());
    }
    
    PatternFeatureValues getPatternFeatureValues(PatternFeatureValuesXML pattern_xml)
    {
        return new PatternFeatureValues(pattern_xml.getPattern());
    }

    ObjectPathFeatureValues getObjectPathFeatureValues(ObjectPathFeatureValuesXML opfvs_xml,
                                                       String                     feature_class)
    {
        return new ObjectPathFeatureValues(feature_class, opfvs_xml.getObjectPath());
    }
    
    SingleFeatureMatcher getSingleFeatureMatcher(SingleFeatureMatcherXML sfm_xml, String obj_class)
    throws SecurityException, NoSuchMethodException, ClassNotFoundException, IOException, URISyntaxException
    {
        if (null != sfm_xml.getEnumFeatureValues()) {
            return new SingleFeatureMatcher(sfm_xml.getFeatureTypeName(),
                                            obj_class + ":" + sfm_xml.getFeaturePath(),
                                            sfm_xml.isExclude(),
                                            sfm_xml.isQuiet(),
                                            getEnumFeatureValues(sfm_xml.getEnumFeatureValues())); 
        }
        else if (null != sfm_xml.getRangeFeatureValues()) {
            return new SingleFeatureMatcher(sfm_xml.getFeatureTypeName(),
                                            obj_class + ":" + sfm_xml.getFeaturePath(),
                                            sfm_xml.isExclude(),
                                            sfm_xml.isQuiet(),
                                            getRangeFeatureValues(sfm_xml.getRangeFeatureValues())); 
        }
        else if (null != sfm_xml.getBitsetFeatureValues()) {
            return new SingleFeatureMatcher(sfm_xml.getFeatureTypeName(),
                                            obj_class + ":" + sfm_xml.getFeaturePath(),
                                            sfm_xml.isExclude(),
                                            sfm_xml.isQuiet(),
                                            getBitsetFeatureValues(sfm_xml.getBitsetFeatureValues())); 
        }
        else if (null != sfm_xml.getPatternFeatureValues()) {
            return new SingleFeatureMatcher(sfm_xml.getFeatureTypeName(),
                                            obj_class + ":" + sfm_xml.getFeaturePath(),
                                            sfm_xml.isExclude(),
                                            sfm_xml.isQuiet(),
                                            getPatternFeatureValues(sfm_xml.getPatternFeatureValues())); 
        }
        else if (null != sfm_xml.getObjectPathFeatureValues()) {
            return new SingleFeatureMatcher(sfm_xml.getFeatureTypeName(),
                                            obj_class + ":" + sfm_xml.getFeaturePath(),
                                            sfm_xml.isExclude(),
                                            sfm_xml.isQuiet(),
                                            getObjectPathFeatureValues(sfm_xml.getObjectPathFeatureValues(), sfm_xml.getFeatureTypeName())); 
        }
        else {
            return new SingleFeatureMatcher(sfm_xml.getFeatureTypeName(),
                                            obj_class + ":" + sfm_xml.getFeaturePath(),
                                            sfm_xml.isExclude(),
                                            sfm_xml.isQuiet(),
                                            new EnumFeatureValues()); // would match any value 
        }
    }
    
    GroupFeatureMatcher getGroupFeatureMatcher(GroupFeatureMatcherXML gfm_xml, String obj_class_name)
    throws SecurityException, NoSuchMethodException, ClassNotFoundException, IOException, URISyntaxException
    {
        List<SingleFeatureMatcherXML> fms_xml = gfm_xml.getFeatureMatchers();
        List<SingleFeatureMatcher> sfms = new ArrayList<SingleFeatureMatcher>(); 
        for (Iterator<SingleFeatureMatcherXML> fm_it = fms_xml.iterator(); fm_it.hasNext();) {
            sfms.add(getSingleFeatureMatcher((SingleFeatureMatcherXML)fm_it.next(), obj_class_name));
        }
        return new GroupFeatureMatcher(sfms, gfm_xml.isExclude()); 
    }
    
    PartialObjectMatcher getPartialObjectMatcher(PartialObjectMatcherXML pom_xml)
    throws SecurityException, NoSuchMethodException, IOException, URISyntaxException, ClassNotFoundException
    {
        List<GroupFeatureMatcherXML> gfms_xml = pom_xml.getGroupFeatureMatchers();
        List<GroupFeatureMatcher> gfms = new ArrayList<GroupFeatureMatcher>();
        
        for (Iterator<GroupFeatureMatcherXML> it = gfms_xml.iterator(); it.hasNext();) {
            gfms.add(getGroupFeatureMatcher((GroupFeatureMatcherXML)it.next(), pom_xml.getAnnotationTypeName()));
        }
        return new PartialObjectMatcher(pom_xml.getAnnotationTypeName(), pom_xml.getFullPath(), gfms); 
    }
    
    FeatureObjectMatcher getFeatureObjectMatcher(FeatureObjectMatcherXML fom_xml)
    throws SecurityException, NoSuchMethodException, IOException, ClassNotFoundException, URISyntaxException
    {
        List<GroupFeatureMatcherXML> gfms_xml = fom_xml.getGroupFeatureMatchers();
        List<GroupFeatureMatcher> gfms = new ArrayList<GroupFeatureMatcher>();
        
        for (Iterator<GroupFeatureMatcherXML> it = gfms_xml.iterator(); it.hasNext();) {
            gfms.add(getGroupFeatureMatcher((GroupFeatureMatcherXML)it.next(), fom_xml.getAnnotationTypeName()));
        }
        return new FeatureObjectMatcher(fom_xml.getAnnotationTypeName(), fom_xml.getFullPath(), gfms, 
                                        fom_xml.getWindowsizeLeft(),
                                        fom_xml.getWindowsizeInside(),
                                        fom_xml.getWindowsizeRight(),
                                        fom_xml.getWindowsizeEnclosed(),
                                        fom_xml.getWindowFlags(),
                                        fom_xml.isOrientation(), 
                                        fom_xml.isDistance()); 
    }
    
    TargetAnnotationDescriptor getTargetAnnotationDescriptor(TargetAnnotationXML ta_xml, int priorityOrder)
    throws SecurityException, NoSuchMethodException, IOException, ClassNotFoundException, URISyntaxException
    {
        PartialObjectMatcher ta_matcher = getPartialObjectMatcher(ta_xml.getTargetAnnotationMatcher());
        List<FeatureObjectMatcherXML> fams_xml = ta_xml.getFeatureAnnotationMatchers();
        List<FeatureObjectMatcher> fams = new ArrayList<FeatureObjectMatcher>();
        for (Iterator<FeatureObjectMatcherXML> it = fams_xml.iterator(); it.hasNext();) {
            fams.add(getFeatureObjectMatcher((FeatureObjectMatcherXML)it.next()));
        }
        return new TargetAnnotationDescriptor(ta_xml.getClassName(),
                                              ta_xml.getEnclosingAnnotation(),
                                              ta_matcher,
                                              fams,
                                              priorityOrder);
    } 

    public List<TargetAnnotationDescriptor> getTargetAnnotationDescriptors()
    throws SecurityException, NoSuchMethodException, IOException, ClassNotFoundException, URISyntaxException
    {
        List<TargetAnnotationDescriptor> result = new ArrayList<TargetAnnotationDescriptor>();
        List<TargetAnnotationXML> tans = m_CFEDescriptor.getTargetAnnotations();
        for (Iterator<TargetAnnotationXML> it = tans.iterator(); it.hasNext();) {
            result.add(getTargetAnnotationDescriptor((TargetAnnotationXML)it.next(), result.size() + 1));
        }
        return result;
    }
    
    public String getNullValueImage()
    {
        return m_CFEDescriptor.getNullValueImage();  
    }
}
