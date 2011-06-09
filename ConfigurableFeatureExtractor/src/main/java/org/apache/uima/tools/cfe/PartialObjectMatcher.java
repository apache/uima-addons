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
import java.util.Iterator;

public class PartialObjectMatcher
{
    Class<?>    m_matcher_class;
    String      m_full_path;
    Collection<GroupFeatureMatcher>  m_group_feature_matchers = new ArrayList<GroupFeatureMatcher>();
    
    public PartialObjectMatcher(String class_name, String full_path, Collection<GroupFeatureMatcher> gfms)
    throws ClassNotFoundException
    {
        m_matcher_class = Class.forName(class_name);
        if ((null == full_path) || (0 == full_path.length())) {
            m_full_path = m_matcher_class.getName();
        }
        else {
            m_full_path = full_path;
        }
        if (null != gfms) {
            m_group_feature_matchers.addAll(gfms);
        }
    }

    protected PartialObjectMatcher(String class_name, String full_annotation_path)
    throws ClassNotFoundException
    {
        this(class_name, full_annotation_path, new ArrayList<GroupFeatureMatcher>());
    }
    
    String getObjectPath ()
    {
        return m_full_path;
    }
    
    public void addGroupMatcher(GroupFeatureMatcher gfm)
    {
        m_group_feature_matchers.add(gfm);
    }

    public boolean matches (MatchedValue mv, Collection<MatchedSingleFeatureMatcher> matched)
    throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
    {
        if (!m_matcher_class.isAssignableFrom(mv.m_matchedObject.getClass())) {
            return false;
        }
        if (m_group_feature_matchers.isEmpty()) {
            return true; 
        }
        for (Iterator<GroupFeatureMatcher> it = m_group_feature_matchers.iterator(); it.hasNext();) {
            
            GroupFeatureMatcher gfm = it.next();
            Collection<MatchedSingleFeatureMatcher> grp_matched = new ArrayList<MatchedSingleFeatureMatcher>(); 
            if (gfm.matches(mv, grp_matched)) {
                matched.addAll(grp_matched);
                return true;
            }
        }
        return false;
    }

    void update(Object source)
    throws IllegalArgumentException,
           SecurityException,
           ClassNotFoundException,
           IllegalAccessException,
           NoSuchFieldException,
           InvocationTargetException
    {
        for (Iterator<GroupFeatureMatcher> it = m_group_feature_matchers.iterator(); it.hasNext();) {
            it.next().update(source);
        }
    }
    
    public Class<?> getMatcherClass ()
    {
        return m_matcher_class;
    }
}
