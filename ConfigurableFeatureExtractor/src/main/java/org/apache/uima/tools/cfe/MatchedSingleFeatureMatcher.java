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

import java.util.Iterator;
import java.util.List;


public class MatchedSingleFeatureMatcher
{
    final public SingleFeatureMatcher                   m_matcher; 
    final public List<MatchedValue> m_values; 
    MatchedSingleFeatureMatcher(SingleFeatureMatcher matcher, List<MatchedValue> values)
    {
        m_matcher   = matcher;
        m_values    = values;
    }
    
    String getFeatureImage(boolean feat_name, String[] value_separators)
    {
        StringBuilder result = new StringBuilder();
        ArrayDelimiterObject max_level = null;
        if (!m_values.isEmpty()) {
            // first element must be max_level
            max_level = (ArrayDelimiterObject) m_values.get(0).m_matchedObject;
        }
        
        for (Iterator<MatchedValue> it = m_values.iterator(); it.hasNext();) {
            Object obj = it.next().m_matchedObject;
            if (max_level == obj) {
                continue;
            }
            if (obj instanceof ArrayDelimiterObject) {
                ArrayDelimiterObject ado = (ArrayDelimiterObject)obj; 
                int ind = Math.min(max_level.m_level - ado.m_level, value_separators.length - 1);
                result.append(value_separators[ind]);
            }
            else {
                result.append(m_matcher.m_feature_values.getFeatureImage(obj));
            }
        }
        if (feat_name) {
            return m_matcher.m_feature_matcher.getFeaturePathImage() + value_separators[0] + result;
        }
        else {
            return result.toString();
        }
    }
}
