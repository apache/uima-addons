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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.tools.cfe.support.FeatureConstrainedIterator;
import org.apache.uima.tools.cfe.support.UIMAAnnotationUtils;


public class ObjectPathFeatureValues extends CollectionFeatureValues<MatchedValue>
{
    String m_object_type_name;
    String m_object_path;
    
    public ObjectPathFeatureValues (String object_type_name, String object_path)
    {
        super(new HashSet<MatchedValue>());
        m_object_type_name = object_type_name;
        m_object_path = object_path;
    }
    
    void update (Object source, Annotation enclosing, Collection<MatchedValue> previosly_matched_objects)
    throws ClassNotFoundException,
           IllegalArgumentException,
           SecurityException,
           IllegalAccessException,
           NoSuchFieldException,
           InvocationTargetException
    {
        m_values.clear();

        JCas jcas = (JCas)source;
        TargetObjectMatcher tom = new TargetObjectMatcher(m_object_type_name, m_object_path, false);

        Class<? extends Annotation> cls_ann = UIMAAnnotationUtils.getAnnotationClass(tom.getRootClass());
        for (FSIterator<? extends Annotation> root_ann_it = FeatureConstrainedIterator.getEnclosedIterator(jcas, cls_ann, enclosing); root_ann_it.hasNext();) {
            List<MatchedValue> mfvs = tom.getFeatureValues(new AnnotationMatchedValue((Annotation)root_ann_it.next(), new ArrayList<Object>()));
            // exclude prioritized models (previously specified in config file)
            if (null != previosly_matched_objects) {
                MatchedValue.removeAll(mfvs, previosly_matched_objects);
                if (!tom.isDetached()) {
                    // add annotation only if it is included into a model
                    previosly_matched_objects.addAll(mfvs);
                }
            }
            m_values.addAll(mfvs);
        }
    }
    

    public String getFeatureImage (Object feature)
    {
        return m_object_type_name + "," + m_object_path;
    }
    
    public boolean matches (Object feature)
    {
        return m_values.contains(feature);
    }
    
}
