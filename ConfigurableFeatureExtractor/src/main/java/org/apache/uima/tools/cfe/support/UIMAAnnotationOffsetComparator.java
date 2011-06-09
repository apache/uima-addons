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

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.apache.uima.jcas.tcas.Annotation;


public class UIMAAnnotationOffsetComparator implements Comparator<Annotation>
{
    final private List<Class<?>> m_typePriorities; 
    
    public UIMAAnnotationOffsetComparator (Class<?>[] typePriorities)
    {
        if (null == typePriorities) {
            m_typePriorities = null;
        }
        else {
            m_typePriorities = Arrays.asList(typePriorities);
        }
    }
    
    public UIMAAnnotationOffsetComparator ()
    {
        this(null);
    }
    
    public int compare(Annotation a1, Annotation a2)
    {
        if (a1.getBegin() < a2.getBegin()) {
            return -1;
        }
        else if (a1.getBegin() > a2.getBegin()) {
            return 1;
        }
        else if (a1.getEnd() < a2.getEnd()) {
            return -1;
        }
        else if (a1.getEnd() > a2.getEnd()) {
            return 1;
        }

        int p1 = 0; 
        int p2 = 0;
        if (null != m_typePriorities) {
            p1 = m_typePriorities.indexOf(a1.getClass()); 
            p2 = m_typePriorities.indexOf(a2.getClass());
        }

        return (p1 == p2) ?
                   (a1.getAddress() == a2.getAddress() ? 0 : (a1.getAddress() > a2.getAddress()) ? 1 : -1) :
                   (p1 <  p2 ? -1 : 1);  
    }
    
    public boolean equal(Annotation a1, Annotation a2)
    {
        return 0 == compare(a1, a2);
    }
}