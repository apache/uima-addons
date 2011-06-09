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
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


public class GroupFeatureMatcher
{
    Collection<SingleFeatureMatcher> m_single_feature_matchers = new ArrayList<SingleFeatureMatcher>();
    boolean    m_exclude;
    
    public GroupFeatureMatcher(Collection<SingleFeatureMatcher> sfms, boolean exclude)
    {
        m_exclude = exclude;
        m_single_feature_matchers.addAll(sfms);
    }
    
    public void addMatcher (String  feat_class,
                            String  feat_path,
                            boolean exclude,
                            boolean quiet,
                            String  path,
                            boolean case_sensitive)
    throws SecurityException, NoSuchMethodException, ClassNotFoundException, IOException
    {
        m_single_feature_matchers.add(
            new SingleFeatureMatcher(feat_class, feat_path, exclude, quiet, path, case_sensitive));
    }
    
    public void addMatcher (String              feat_class,
                            String              feat_path,
                            boolean             exclude,
                            boolean             quiet,
                            Collection<String>  values,
                            boolean             case_sensitive)
    throws SecurityException, NoSuchMethodException, ClassNotFoundException, IOException
    {
        m_single_feature_matchers.add(
            new SingleFeatureMatcher(feat_class, feat_path, exclude, quiet, values, case_sensitive));
    }

    public void addMatcher (String  feat_class,
                            String  feat_path,
                            boolean exclude,
                            boolean quiet,
                            double  lb,
                            boolean lbi,
                            double  ub,
                            boolean ubi)
    throws SecurityException, NoSuchMethodException, ClassNotFoundException, IOException
    {
        m_single_feature_matchers.add(new SingleFeatureMatcher(feat_class, feat_path, exclude, quiet, lb, lbi, ub, ubi));
    }
    
    public void addMatcher (String  feat_class,
                            String  feat_path,
                            boolean exclude,
                            boolean quiet,
                            int     bitmask,
                            boolean excact_match)
    throws SecurityException, NoSuchMethodException, ClassNotFoundException, IOException
    {
        m_single_feature_matchers.add(new SingleFeatureMatcher(feat_class, feat_path, exclude, quiet, bitmask, excact_match));
    }
    
    public boolean matches (MatchedValue mv, Collection<MatchedSingleFeatureMatcher> matched)
    throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
    {
        // if collection is emtpy - means "matches to any value"
        for (Iterator<SingleFeatureMatcher> it = m_single_feature_matchers.iterator(); it.hasNext();) {
            SingleFeatureMatcher sfm = it.next();
            List<MatchedValue> matchedValues = new ArrayList<MatchedValue>();
            if (!sfm.matches(mv, matchedValues)) {
                return m_exclude;
            }
            matched.add(new MatchedSingleFeatureMatcher(sfm, matchedValues));
        }
        return !m_exclude;
    }
    
    void update(Object source)
    throws IllegalArgumentException,
           SecurityException,
           ClassNotFoundException,
           IllegalAccessException,
           NoSuchFieldException,
           InvocationTargetException
    {
        for (Iterator<SingleFeatureMatcher> it = m_single_feature_matchers.iterator(); it.hasNext();) {
            it.next().update(source);
        }
    }
}
