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

import org.apache.uima.jcas.tcas.Annotation;



public class MatchedAnnotationDescriptor
{
    public final FeatureObjectMatcher                       m_feature_matcher;
    public final AnnotationMatchedValue                     m_feature_mv;
    public final Annotation                                 m_enclosing;
    public final Collection<MatchedSingleFeatureMatcher>    m_sfms_with_values;
    public final int                                        m_direction;
    public final int                                        m_offset;
    public final int                                        m_orderIndex;
    
    public MatchedAnnotationDescriptor(FeatureObjectMatcher fom,
                                       Annotation                               enclosing,
                                       AnnotationMatchedValue                   feature_mv,
                                       Collection<MatchedSingleFeatureMatcher>  sfms_with_values,
                                       int                                      direction,
                                       int                                      offset,
                                       int                                      priorityOrder)
    {
        m_feature_matcher   = fom;
        m_feature_mv        = feature_mv;
        m_enclosing         = enclosing;
        m_sfms_with_values  = sfms_with_values;
        m_direction         = direction;
        m_offset            = offset;
        m_orderIndex        = priorityOrder;
    }
}
