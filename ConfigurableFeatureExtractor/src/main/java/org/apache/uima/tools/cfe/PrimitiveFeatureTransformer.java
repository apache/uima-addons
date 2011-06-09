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

public class PrimitiveFeatureTransformer implements FeatureValueConverter, FeatureValueNormalizer
{
    final boolean m_case_sensitive;
    static public String nullValueImage = "null";
    
    public PrimitiveFeatureTransformer (boolean case_sensitive)
    {
        m_case_sensitive = case_sensitive;
    }
    
    static public String capitalize (String str)
    {
        StringBuilder strBuf = new StringBuilder(str);
        
        if (strBuf.length() > 1) {
            if (Character.isLetter(strBuf.charAt(0))) {
                strBuf.setCharAt(0, Character.toUpperCase(strBuf.charAt(0)));
            }
        }
        return strBuf.toString();
    }
        
    public String convert (Object feature)
    {
        if (null == feature) {
            return nullValueImage;
        }
        return feature.toString();
    }
        
    public String normalize (String feature)
    {
        return m_case_sensitive ?  capitalize(feature) : feature.toLowerCase();
    }
        
    public String getValue (Object feature)
    {
        return normalize(convert(feature));
    }
    
}
