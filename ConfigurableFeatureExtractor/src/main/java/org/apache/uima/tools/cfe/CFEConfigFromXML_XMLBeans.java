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
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.uima.tools.cfe.config.xmlBeans.BitsetFeatureValuesXML;
import org.apache.uima.tools.cfe.config.xmlBeans.CFEConfigDocument;
import org.apache.uima.tools.cfe.config.xmlBeans.CFEDescriptorXML;
import org.apache.uima.tools.cfe.config.xmlBeans.EnumFeatureValuesXML;
import org.apache.uima.tools.cfe.config.xmlBeans.FeatureObjectMatcherXML;
import org.apache.uima.tools.cfe.config.xmlBeans.GroupFeatureMatcherXML;
import org.apache.uima.tools.cfe.config.xmlBeans.ObjectPathFeatureValuesXML;
import org.apache.uima.tools.cfe.config.xmlBeans.PartialObjectMatcherXML;
import org.apache.uima.tools.cfe.config.xmlBeans.PatternFeatureValuesXML;
import org.apache.uima.tools.cfe.config.xmlBeans.RangeFeatureValuesXML;
import org.apache.uima.tools.cfe.config.xmlBeans.SingleFeatureMatcherXML;
import org.apache.uima.tools.cfe.config.xmlBeans.TargetAnnotationXML;
import org.apache.xmlbeans.XmlException;

public class CFEConfigFromXML_XMLBeans
{
    private final CFEDescriptorXML m_CFEDescriptor;
    
    public CFEConfigFromXML_XMLBeans (String path) throws XmlException, IOException
    {
        CFEConfigDocument doc = CFEConfigDocument.Factory.parse(new File(path));
        m_CFEDescriptor = doc.getCFEConfig();
    }
    
    EnumFeatureValues getEnumFeatureValues(EnumFeatureValuesXML efvs_xml)
    throws IOException, URISyntaxException
    {
        List<String> vals = efvs_xml.getValuesList();

        if ((1 == vals.size()) && (vals.get(0).startsWith("file://"))) {
            return new EnumFeatureValues(quote(vals.get(0)).getPath(), efvs_xml.getCaseSensitive());
        }
        return new EnumFeatureValues(vals, efvs_xml.getCaseSensitive());
    }
    
    RangeFeatureValues getRangeFeatureValues(RangeFeatureValuesXML rfvs_xml)
    {
        String lb = rfvs_xml.getLowerBoundary().toString().trim();
        String ub = rfvs_xml.getUpperBoundary().toString().trim();
        
        return new RangeFeatureValues(Double.parseDouble(lb),
                                      rfvs_xml.getLowerBoundaryInclusive(),
                                      Double.parseDouble(ub),
                                      rfvs_xml.getUpperBoundaryInclusive());
    }

    BitsetFeatureValues getBitsetFeatureValues(BitsetFeatureValuesXML bfvs_xml)
    {
        return new BitsetFeatureValues(Integer.parseInt(bfvs_xml.getBitmask(), 16),
                                       bfvs_xml.getExactMatch());
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
                                            sfm_xml.getExclude(),
                                            sfm_xml.getQuiet(),
                                            getEnumFeatureValues(sfm_xml.getEnumFeatureValues())); 
        }
        else if (null != sfm_xml.getRangeFeatureValues()) {
            return new SingleFeatureMatcher(sfm_xml.getFeatureTypeName(),
                                            obj_class + ":" + sfm_xml.getFeaturePath(),
                                            sfm_xml.getExclude(),
                                            sfm_xml.getQuiet(),
                                            getRangeFeatureValues(sfm_xml.getRangeFeatureValues())); 
        }
        else if (null != sfm_xml.getBitsetFeatureValues()) {
            return new SingleFeatureMatcher(sfm_xml.getFeatureTypeName(),
                                            obj_class + ":" + sfm_xml.getFeaturePath(),
                                            sfm_xml.getExclude(),
                                            sfm_xml.getQuiet(),
                                            getBitsetFeatureValues(sfm_xml.getBitsetFeatureValues())); 
        }
        else if (null != sfm_xml.getPatternFeatureValues()) {
            return new SingleFeatureMatcher(sfm_xml.getFeatureTypeName(),
                                            obj_class + ":" + sfm_xml.getFeaturePath(),
                                            sfm_xml.getExclude(),
                                            sfm_xml.getQuiet(),
                                            getPatternFeatureValues(sfm_xml.getPatternFeatureValues())); 
        }
        else if (null != sfm_xml.getObjectPathFeatureValues()) {
            return new SingleFeatureMatcher(sfm_xml.getFeatureTypeName(),
                                            obj_class + ":" + sfm_xml.getFeaturePath(),
                                            sfm_xml.getExclude(),
                                            sfm_xml.getQuiet(),
                                            getObjectPathFeatureValues(sfm_xml.getObjectPathFeatureValues(), sfm_xml.getFeatureTypeName())); 
        }
        else {
            return new SingleFeatureMatcher(sfm_xml.getFeatureTypeName(),
                                            obj_class + ":" + sfm_xml.getFeaturePath(),
                                            sfm_xml.getExclude(),
                                            sfm_xml.getQuiet(),
                                            new EnumFeatureValues()); // would match any value 
        }
    }
    
    GroupFeatureMatcher getGroupFeatureMatcher(GroupFeatureMatcherXML gfm_xml, String obj_class_name)
    throws SecurityException, NoSuchMethodException, ClassNotFoundException, IOException, URISyntaxException
    {
        List<SingleFeatureMatcherXML> fms_xml = gfm_xml.getFeatureMatchersList();
        List<SingleFeatureMatcher> sfms = new ArrayList<SingleFeatureMatcher>();

        for (SingleFeatureMatcherXML fm_xml : fms_xml) {
            sfms.add(getSingleFeatureMatcher(fm_xml, obj_class_name));
        }
        return new GroupFeatureMatcher(sfms, gfm_xml.getExclude()); 
    }
    
    PartialObjectMatcher getPartialObjectMatcher(PartialObjectMatcherXML pom_xml)
    throws SecurityException, NoSuchMethodException, IOException, URISyntaxException, ClassNotFoundException
    {
        List<GroupFeatureMatcherXML> gfms_xml = pom_xml.getGroupFeatureMatchersList();
        List<GroupFeatureMatcher> gfms = new ArrayList<GroupFeatureMatcher>();
        
        for (GroupFeatureMatcherXML gfm_xml : gfms_xml) {
            gfms.add(getGroupFeatureMatcher(gfm_xml, pom_xml.getAnnotationTypeName()));
        }
        return new PartialObjectMatcher(pom_xml.getAnnotationTypeName(), pom_xml.getFullPath(), gfms); 
    }
    
    FeatureObjectMatcher getFeatureObjectMatcher(FeatureObjectMatcherXML fom_xml)
    throws SecurityException, NoSuchMethodException, IOException, ClassNotFoundException, URISyntaxException
    {
        List<GroupFeatureMatcherXML> gfms_xml = fom_xml.getGroupFeatureMatchersList();
        List<GroupFeatureMatcher> gfms = new ArrayList<GroupFeatureMatcher>();

        for (GroupFeatureMatcherXML gfm_xml : gfms_xml) {
            gfms.add(getGroupFeatureMatcher(gfm_xml, fom_xml.getAnnotationTypeName()));
        }
        return new FeatureObjectMatcher(fom_xml.getAnnotationTypeName(), fom_xml.getFullPath(), gfms, 
                                        fom_xml.getWindowsizeLeft(),
                                        fom_xml.getWindowsizeInside(),
                                        fom_xml.getWindowsizeRight(),
                                        fom_xml.getWindowsizeEnclosed(),
                                        fom_xml.getWindowFlags(),
                                        fom_xml.getOrientation(), 
                                        fom_xml.getDistance()); 
    }
    
    TargetAnnotationDescriptor getTargetAnnotationDescriptor(TargetAnnotationXML ta_xml, int priorityOrder)
    throws SecurityException, NoSuchMethodException, IOException, ClassNotFoundException, URISyntaxException
    {
        PartialObjectMatcher ta_matcher = getPartialObjectMatcher(ta_xml.getTargetAnnotationMatcher());
        List<FeatureObjectMatcherXML> fams_xml = ta_xml.getFeatureAnnotationMatchersList();
        
        List<FeatureObjectMatcher> fams = new ArrayList<FeatureObjectMatcher>();
        for (FeatureObjectMatcherXML fam_xml : fams_xml) {
            fams.add(getFeatureObjectMatcher(fam_xml));
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
        List<TargetAnnotationXML> tans = m_CFEDescriptor.getTargetAnnotationsList();
        for (TargetAnnotationXML tan_xml : tans) {
            result.add(getTargetAnnotationDescriptor(tan_xml, result.size() + 1));
        }
        return result;
    }
    
    public String getNullValueImage()
    {
        return m_CFEDescriptor.getNullValueImage();  
    }
    
    // Maintainer note: remove these methods once base sdk 2.3.2 is released, and switch
    // to using these methods from there
    /**
     * Create a URI from a string, with proper quoting.
     * Already quoted things in the input string are not re-quoted.
     * There are several cases:
     *   String has no characters needing quoting
     *   String has chars needing quoting, but no chars are currently quoted (e.g. %20)
     *   String has quoted (e.g. %20) characters but no other chars needing quoting
     *   String has quoted (e.g. %20) characters and chars needing quoting, not currently quoted
     *     -- this case will throw an exception
     * @param s
     * @return URI with proper quoting
     * @throws URISyntaxException 
     */
    private static URI quote (String s) throws URISyntaxException {
      if (-1 == s.indexOf('%')) {
        // 3 argument constructor does any needed quoting of otherwise illegal chars
        // https://issues.apache.org/jira/browse/UIMA-2097
        return new URI(null, s, null);  
      }
      
      // assume s already has all otherwise illegal chars properly quoted
      return new URI(s);
    }

}
