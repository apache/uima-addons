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

import java.util.Collection;
import java.util.List;

import org.apache.uima.jcas.tcas.Annotation;

public class AnnotationMatchedValue extends MatchedValue
{

    public AnnotationMatchedValue (MatchedValue mv)
    {
        super(mv.m_matchedObject, mv.m_orderedPath);
        getAnnotation(); // should throw an exception if m_matchedObject is not of Annotation type 
    }

    public AnnotationMatchedValue (Annotation matchedObject)
    {
        super(matchedObject);
    }
    
    public AnnotationMatchedValue (Annotation matchedObject,
                                   List<Object> orderedPath)
    {
        super(matchedObject, orderedPath);
    }

    
    
    public Annotation getAnnotation()
    {
        return (Annotation)super.m_matchedObject;
    }
    
    public static Collection<AnnotationMatchedValue> upcast (Collection<AnnotationMatchedValue> trg,
                                                             Collection<MatchedValue>           src)
    {
        for (MatchedValue mv : src) {
            trg.add(new AnnotationMatchedValue(mv));
        }
        return trg;
    }
}
