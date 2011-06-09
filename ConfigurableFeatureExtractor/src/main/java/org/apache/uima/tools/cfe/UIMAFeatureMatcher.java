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

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.tools.cfe.support.ComparableArray;


public class UIMAFeatureMatcher extends CommonFeatureMatcher 
{
    Map<ComparableArray, String> m_featureImages;
    
    public UIMAFeatureMatcher (boolean includeTargetId)
    {
        super(includeTargetId, false, false, "|", new String[] {";", "^", "!", "%"});
    }
    
    public void processJCas (JCas jcas)
    throws IllegalArgumentException,
           IllegalAccessException,
           InvocationTargetException,
           ClassNotFoundException,
           SecurityException,
           NoSuchFieldException
    {
        // to make features sorted by begin/end offsets
        m_featureImages = new TreeMap<ComparableArray, String>();
        super.processJCas(jcas);
    }

    protected void processFeatureGroup(JCas                                     jcas,
                                       Annotation                               trg,
                                       String                                   group_label,
                                       Collection<MatchedAnnotationDescriptor>  features)
    throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
    {
        for (Iterator<MatchedAnnotationDescriptor> feat_it = features.iterator(); feat_it.hasNext();) {
            MatchedAnnotationDescriptor mad = feat_it.next();
            ComparableArray key = new ComparableArray(
                new Integer[] {
                        Integer.valueOf(mad.m_feature_mv.getAnnotation().getEnd()),
                        Integer.valueOf(mad.m_feature_mv.getAnnotation().getBegin()),
                        Integer.valueOf(mad.m_orderIndex),
                        Integer.valueOf(m_featureImages.size())
                 });
            
            String feature = make_UIMA_feature(mad, make_image(mad, trg));
            m_featureImages.put(key, group_label + m_fieldSeparator + feature);
        }
    }
    

    
    String make_UIMA_feature (MatchedAnnotationDescriptor   mad,
                              String                        other_fields)
    {
        StringBuilder stBuff = new StringBuilder();
        if (mad.m_feature_matcher.m_orientation || mad.m_feature_matcher.m_distance) {
            stBuff.append(make_image_position(mad));
            stBuff.append(m_fieldSeparator);
        }
        stBuff.append(other_fields);
        return stBuff.toString(); 
    }
}
