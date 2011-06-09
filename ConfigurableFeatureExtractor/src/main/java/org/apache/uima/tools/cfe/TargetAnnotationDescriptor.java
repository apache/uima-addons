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

import java.util.Collection;


public class TargetAnnotationDescriptor
{
    String                  m_class_name;
    String                  m_enclosing_annotation_name;
    
    PartialObjectMatcher                m_target_annotation_matcher;
    Collection<FeatureObjectMatcher>    m_feature_annotation_matchers;
    
    final int               m_priorityOrder;
    
    
    public TargetAnnotationDescriptor(String                            class_name,
                                      String                            enclosing_annotation_name,
                                      PartialObjectMatcher              target_annotation_matcher,
                                      Collection<FeatureObjectMatcher>  feature_annotation_matchers,
                                      int                               priorityOrder)
   {
        m_class_name = class_name;
        m_enclosing_annotation_name = enclosing_annotation_name;
        m_target_annotation_matcher = target_annotation_matcher;
        m_feature_annotation_matchers = feature_annotation_matchers;
        m_priorityOrder = priorityOrder;
   }
    
    String getClassName ()
    {
        return m_class_name;
    }
    
    String getEnclosingAnnotationName ()
    {
        return m_enclosing_annotation_name;
    }
    
    PartialObjectMatcher getTargetAnnotationMatcher ()
    {
        return m_target_annotation_matcher;
    }

    Collection<FeatureObjectMatcher> getFeatureAnnotationMatchers ()
    {
        return m_feature_annotation_matchers;
    }
}
