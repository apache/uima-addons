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

public class BitsetFeatureValues implements FeatureValues
{
    final int m_bitmask;
    final boolean m_exact_match;
    
    public BitsetFeatureValues(int bitmask, boolean exact_match)
    {
        m_bitmask = bitmask;
        m_exact_match = exact_match;
    }
    
    public boolean matches (Object feature)
    {
        if (feature instanceof Integer) {
            int mask = ((Integer)feature).intValue();
            return m_exact_match ? ((m_bitmask & mask) == m_bitmask) : ((m_bitmask & mask) != 0); 
        }
        return false;
    }
    
    public String getFeatureImage (Object feature)
    {
        return feature.toString();
    }
}
