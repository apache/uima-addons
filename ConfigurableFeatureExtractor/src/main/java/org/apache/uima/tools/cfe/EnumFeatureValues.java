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
import java.util.Collection;
import java.util.HashSet;

import org.apache.uima.tools.cfe.support.EnumeratedEntryDictionary;

public class EnumFeatureValues extends CollectionFeatureValues<String>
{
    final public PrimitiveFeatureTransformer m_value_transformer;
    
    public EnumFeatureValues (Collection<String> values, boolean case_sensitive)
    {
        this((String[])values.toArray(null), case_sensitive);
    }
    
    public EnumFeatureValues (Object[] values, boolean case_sensitive)
    {
        super(new HashSet<String>());
        m_value_transformer = new PrimitiveFeatureTransformer(case_sensitive);
        if (null != values) {
            // values contains strings; if not use converter   
            for (int i = 0; i < values.length; ++i) {
                m_values.add(getValueNormalizer().normalize(values[i].toString()));
            }
        }
    }

    public EnumFeatureValues (String path, boolean case_sensitive) throws IOException
    {
        super(null);
        m_value_transformer = new PrimitiveFeatureTransformer(case_sensitive);
        EnumeratedEntryDictionary fd = new EnumeratedEntryDictionary("enum feature values", path, getValueNormalizer());
        fd.load();
        m_values = fd.values();
    }
    
    public EnumFeatureValues ()
    {
        this((Object[])null, false);
    }
    
    public FeatureValueNormalizer getValueNormalizer ()
    {
        return m_value_transformer;
    }

    public FeatureValueConverter getValueConverter ()
    {
        return m_value_transformer;
    }
    
    public String getFeatureImage (Object feature)
    {
        return m_value_transformer.getValue(feature); 
    }
    
    public boolean matches (Object feature)
    {
        if (m_values.isEmpty()) {
            return true;
        }
        return m_values.contains(getFeatureImage(feature));
    }
    
}
