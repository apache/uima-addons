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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;


public class UIMAAnnotationUtils
{
    public static int getTypeIndexId (Class<? extends Annotation> cls)
    throws IllegalArgumentException, SecurityException, IllegalAccessException, NoSuchFieldException
    {
        return cls.getField("typeIndexID").getInt(null);
    }
    
    public static int getTypeIndexId (String typename)
    throws IllegalArgumentException, SecurityException, IllegalAccessException, NoSuchFieldException, ClassNotFoundException
    {
        return getTypeIndexId(forName(typename));
    }
    
    public static int getTypeIndexId (Type type)
    throws IllegalArgumentException, SecurityException, IllegalAccessException, NoSuchFieldException, ClassNotFoundException
    {
        return getTypeIndexId(type.getName());
    }

    public static boolean containsAnnotationBySpan (Object[] annotations, Annotation test)
    {
        for (int i = 0; i < annotations.length; ++i) {
            Annotation co = (Annotation)annotations[i];
            if ((co.getBegin() == test.getBegin()) && (co.getEnd() == test.getEnd())) {
                return true;
            }
        }
        return false;
    }
    
    public static void addFSArrayToAnnotations (List<Annotation> dest, FSArray source)
    {
        for (int i = 0; i < source.size(); ++i) {
            dest.add((Annotation)source.get(i));
        }
    }
    
    public static List<Annotation>  fsArrayToAnnotations (FSArray source)
    {
        List<Annotation> result = new ArrayList<Annotation>();
        addFSArrayToAnnotations(result, source);
        return result;
    }
    
    
    public static void addAnnotationsToFSArray (JCas                    jcas,
                                                Annotation              parent,
                                                String                  feat_name,
                                                Collection<Annotation>  anns,
                                                boolean                 update_span)
    {
        Feature feat = jcas.getCasType(parent.getTypeIndexID()).getFeatureByBaseName(feat_name);
        FSArray fsa = (FSArray)parent.getFeatureValue(feat);
        
        List<Annotation> new_elements = new ArrayList<Annotation>();
        if (null != fsa ) {
            addFSArrayToAnnotations(new_elements, fsa);
        }
        new_elements.addAll(anns);
        int b = Integer.MAX_VALUE, e = Integer.MIN_VALUE;
        FSArray new_fsa = new FSArray(jcas, new_elements.size());
        for (int i = 0; i < new_elements.size(); ++i) {
            Annotation ann = (Annotation)new_elements.get(i);
            new_fsa.set(i, ann);
            if (b > ann.getBegin()) {
                b = ann.getBegin();
            }
            if (e < ann.getEnd()) {
                e = ann.getEnd();
            }
        }
        parent.setFeatureValue(feat, new_fsa);
        
        if (update_span) {
            parent.removeFromIndexes();
            updateSpan(parent, b, e);
            parent.addToIndexes();
        }
    }
    
    public static void updateSpan (Annotation target, Annotation included)
    {
        updateSpan(target, included.getBegin(), included.getEnd());
    }
    
    public static void updateSpan (Annotation target, int b, int e)
    {
        boolean force_offset = false;
        if ((0 == target.getBegin()) && (0 == target.getEnd())) {
            force_offset = true;
        }
        if (force_offset || target.getBegin() > b) {
            target.setBegin(b);
        }
        if (force_offset || target.getEnd() < e) {
            target.setEnd(e);
        }
    }

    public static boolean sameSpanAnnotations (Annotation ann1, Annotation ann2)
    {
        return (ann1.getBegin() == ann2.getBegin()) && (ann1.getEnd() == ann2.getEnd()); 
    }

    public static Annotation getSameOffsetAnnotation (JCas jcas, Class<? extends Annotation> ann_class, int begin, int end)
    {
        try {
            Iterator<?> it = FeatureConstrainedIterator.getEnclosedIterator(jcas, ann_class, begin, begin + 1, end - 1, end); 
            if (it.hasNext()) {
                return (Annotation)it.next();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static List<Annotation> getSameOffsetAnnotations (JCas jcas, Class<? extends Annotation> ann_class, int begin, int end)
    {
        List<Annotation> result = new ArrayList<Annotation>(); 
        try {
            Iterator<?> it = FeatureConstrainedIterator.getEnclosedIterator(jcas, ann_class, begin, begin + 1, end - 1, end); 
            while (it.hasNext()) {
                result.add((Annotation)it.next());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public static Annotation getSameOffsetAnnotation (JCas jcas, Class<? extends Annotation> ann_class, Annotation source)
    {
        return getSameOffsetAnnotation(jcas, ann_class, source.getBegin(), source.getEnd());
    }
    
    public static List<Annotation> getSameOffsetAnnotations (JCas jcas, Class<? extends Annotation> ann_class, Annotation source)
    {
        return getSameOffsetAnnotations(jcas, ann_class, source.getBegin(), source.getEnd());
    }

    
    static public FSIterator<? extends Annotation> fsIterator (JCas jcas, Class<? extends Annotation> cls_ann)
    throws IllegalArgumentException,
           SecurityException,
           IllegalAccessException,
           NoSuchFieldException
    {
        AnnotationIndex<? extends Annotation> ind = jcas.getJFSIndexRepository().getAnnotationIndex(UIMAAnnotationUtils.getTypeIndexId(cls_ann));
        
        return ind.iterator();
    }
    
    

    @SuppressWarnings("unchecked")
    public static Class<? extends Annotation> getAnnotationClass (Class<?> cls)
    {
        if (!Annotation.class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("class '" + cls.getName() + "' is not derived from " + Annotation.class.getName());
        }
        
        return (Class<? extends Annotation>)cls;
    }
    
    @SuppressWarnings("unchecked")
    static public <T extends Annotation> Iterator<T> iterator (JCas jcas, Class<T> cls) 
    throws IllegalArgumentException,
           SecurityException,
           IllegalAccessException,
           NoSuchFieldException
    {
        return (Iterator<T>)fsIterator(jcas, cls);
    }
    
    static public Class<? extends Annotation> forName (String name) throws ClassNotFoundException
    {
        return getAnnotationClass(Class.forName(name)); 
    }
    
    @SuppressWarnings("unchecked")
    static public <B extends Annotation, T extends B> Class<T> forName (Class<B> cls_base, String name) throws ClassNotFoundException
    {
        return (Class<T>)getAnnotationClass(Class.forName(name));
    }
    
}
