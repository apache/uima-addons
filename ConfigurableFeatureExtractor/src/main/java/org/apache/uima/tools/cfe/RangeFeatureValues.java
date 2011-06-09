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

public class RangeFeatureValues implements FeatureValues
{
    Double  m_lower_boundary;
    boolean m_lower_boundary_inclusive;

    Double  m_upper_boundary;
    boolean m_upper_boundary_inclusive;
    
    
    public RangeFeatureValues(double    lb,
                              boolean   lbi,
                              double    ub,
                              boolean   ubi)
    {
        m_lower_boundary = lb;
        m_lower_boundary_inclusive = lbi;
        m_upper_boundary = ub;
        m_upper_boundary_inclusive = ubi;
    }

    public boolean matches (Object feature)
    {
        if (!(feature instanceof Number)) {
            return false;
        }
        Number nfeature = (Number)feature;
        
        int lb_res = m_lower_boundary.compareTo(nfeature.doubleValue());
        if (((0 == lb_res) && !m_lower_boundary_inclusive) || (lb_res < 0)) {
            return false;
        }

        int ub_res = m_upper_boundary.compareTo(nfeature.doubleValue());
        if (((0 == ub_res) && !m_upper_boundary_inclusive) || (ub_res > 0)) {
            return false;
        }
        return true;
    }
    
    public String getFeatureImage (Object feature)
    {
        return feature.toString();
    }
}
