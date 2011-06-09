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
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


public class SingleFeatureMatcher
{
    TargetObjectMatcher m_feature_matcher;
    FeatureValues       m_feature_values;
    final boolean       m_exclude;
    final boolean       m_quiet;
    
    public SingleFeatureMatcher (String         feat_class_name,
                                 String         feat_full_path,
                                 boolean        exclude,
                                 boolean        quiet,
                                 FeatureValues  fvals)
    throws SecurityException, NoSuchMethodException, ClassNotFoundException
    {
        m_feature_matcher = new TargetObjectMatcher(feat_class_name, feat_full_path, true);
        m_exclude = exclude;
        m_feature_values = fvals;
        m_quiet = quiet;
    }

    public SingleFeatureMatcher (String             feat_class_name,
                                 String             full_feat_path,
                                 boolean            exclude,
                                 boolean            quiet,
                                 Collection<String> values,
                                 boolean            case_sensitive)
    throws SecurityException, NoSuchMethodException, ClassNotFoundException
    {
        this(feat_class_name, full_feat_path, exclude, quiet, new EnumFeatureValues(values, case_sensitive));
    }

    public SingleFeatureMatcher (String     feat_class_name,
                                 String     full_feat_path,
                                 boolean    exclude,
                                 boolean    quiet,
                                 String     path,
                                 boolean    case_sensitive)
    throws SecurityException, NoSuchMethodException, ClassNotFoundException, IOException
    {
        this(feat_class_name, full_feat_path, exclude, quiet, new EnumFeatureValues(path, case_sensitive));
    }

    public SingleFeatureMatcher (String     feat_class_name,
                                 String     full_feat_path, 
                                 boolean    exclude, 
                                 boolean    quiet,
                                 Double     lb,
                                 boolean    lbi,
                                 Double     ub,
                                 boolean    ubi)
    throws SecurityException, NoSuchMethodException, ClassNotFoundException
    {
        this(feat_class_name, full_feat_path, exclude, quiet, new RangeFeatureValues(lb, lbi, ub, ubi));
    }

    public SingleFeatureMatcher (String     feat_class_name,
                                 String     full_feat_path,
                                 boolean    exclude,
                                 boolean    quiet,
                                 int        bitmask,
                                 boolean    exact_match)
    throws SecurityException, NoSuchMethodException, ClassNotFoundException
    {
        this(feat_class_name, full_feat_path, exclude, quiet, new BitsetFeatureValues(bitmask, exact_match));
    }

    public SingleFeatureMatcher (String     feat_class_name,
                                 String     full_feat_path,
                                 boolean    exclude,
                                 boolean    quiet,
                                 String     pattern)
    throws SecurityException, NoSuchMethodException, ClassNotFoundException
    {
        this(feat_class_name, full_feat_path, exclude, quiet, new PatternFeatureValues(pattern));
    }

    public boolean matches (MatchedValue mv, List<MatchedValue> matchedValues)
    throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
    {
        List<MatchedValue> vals = m_feature_matcher.getFeatureValues(mv);
        // if vals is empty that means the feature value is not present (not even null)
        // null pointers will be added to the list
        
        matchedValues.addAll(vals);

        for (Iterator<MatchedValue> it = vals.iterator(); it.hasNext();) {
            if (m_feature_values.matches(it.next().m_matchedObject)) {
                return !m_exclude;
            }
        }
        return m_exclude;
    }
    
    void update(Object source)
    throws IllegalArgumentException,
           SecurityException,
           ClassNotFoundException,
           IllegalAccessException,
           NoSuchFieldException,
           InvocationTargetException
    {
        if (m_feature_values instanceof ObjectPathFeatureValues) { 
            ((ObjectPathFeatureValues)m_feature_values).update(source, null, null);
        }
    }
}
