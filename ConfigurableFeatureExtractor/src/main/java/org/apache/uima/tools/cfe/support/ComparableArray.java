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

package org.apache.uima.tools.cfe.support;

public class ComparableArray implements Comparable
{
    public final Comparable[] m_src;
    
    public ComparableArray(Comparable[] src)
    {
        m_src = src;
    }
    
    private int compare(Comparable[] ia1, Comparable[] ia2)
    {
        if (ia1 == ia2) {
            return 0;
        }
        if ((null == ia1) || (null == ia2)) {
            return null == ia2 ? 1 : -1; 
        }
        if (ia1.length != ia2.length) {
            return ia1.length > ia2.length ? 1 : -1; 
        }
        for (int i = 0; i < ia1.length; ++i) {
            int res = ia1[i].compareTo(ia2[i]);
            if (0 != res) {
                return res;  
            }
        }
        return 0;
    }
    
    public int compareTo (Object other)
    {
        if (null == other) {
            return 1;
        }

        if (other instanceof ComparableArray) {
            return compare(m_src, ((ComparableArray)other).m_src);
        }
        throw new ClassCastException("Cannot compare ComparableArray to object of type " + 
            other.getClass().getName());
    }
    
    public  boolean equals (Object other)
    {
        return 0 == compareTo(other);
    }
    
    public int hashCode()
    {
        int result = 0;
        result += Math.min(m_src.length, 9) * m_src.length;
        for (int i = 0; i < m_src.length; ++i) {
            result += Math.min(m_src.length, 9) * m_src[i].hashCode();
        }
        return result;
    }

    
    public String toString ()
    {
        assert (null != m_src);
        
        StringBuilder result = new StringBuilder(m_src.length * 8);
        result.append("[");
        for (int i = 0; i < m_src.length; ++i) {
            if (0 != i) {
                result.append(",");
            }  
            result.append(m_src[i]);
        }
        result.append("]");
        return result.toString();
    }
}
