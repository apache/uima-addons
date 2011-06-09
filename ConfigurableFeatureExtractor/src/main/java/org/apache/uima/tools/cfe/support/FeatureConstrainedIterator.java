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
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.uima.cas.ConstraintFactory;
import org.apache.uima.cas.FSIntConstraint;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.FSMatchConstraint;
import org.apache.uima.cas.FSStringConstraint;
import org.apache.uima.cas.FSTypeConstraint;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeaturePath;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JFSIndexRepository;
import org.apache.uima.jcas.tcas.Annotation;


;

public class FeatureConstrainedIterator
{
    static private class ConstraintDescriptor
    {
        protected Map<String, FeaturePath> m_feat2path = new HashMap<String, FeaturePath>();
        final protected int m_typeId;
        
        public ConstraintDescriptor (JCas               jcas,
                                     int                typeId,
                                     Collection<String> featureNames)
        throws ClassNotFoundException,
               IllegalArgumentException,
               SecurityException,
               IllegalAccessException,
               NoSuchFieldException
        {
            m_typeId = typeId;
            if (null != featureNames) {
                addFeatures(jcas, featureNames);
            }
        }
        
        void addFeatures (JCas jcas, Collection<String> featureNames)
        {
            for (Iterator<String> it = featureNames.iterator(); it.hasNext();) {
                String featureName = it.next();
                FeaturePath featurePath = jcas.createFeaturePath();
                Feature feature = jcas.getCasType(m_typeId).getFeatureByBaseName(featureName);
                featurePath.addFeature(feature);
                m_feat2path.put(featureName, featurePath);
            }
        }
        
        FSIterator<? extends Annotation> createFilteredIterator (JCas jcas, FSMatchConstraint constraint)
        {
            JFSIndexRepository idxs = jcas.getJFSIndexRepository ();
            AnnotationIndex<? extends Annotation> sdi_ind = idxs.getAnnotationIndex(m_typeId);
            return jcas.createFilteredIterator(sdi_ind.iterator(), constraint);
        }
    }
    
    static private class SubiteratorConstraintDescriptor extends ConstraintDescriptor
    {
        public SubiteratorConstraintDescriptor (JCas jcas, Class<? extends Annotation> enclosed)
        throws ClassNotFoundException,
               IllegalArgumentException,
               SecurityException,
               IllegalAccessException,
               NoSuchFieldException
        {
            super(jcas, UIMAAnnotationUtils.getTypeIndexId(enclosed), null);
        }
        
        FSIterator<? extends Annotation> createSubiterator (JCas jcas, AnnotationFS enclosing)
        {
            JFSIndexRepository idxs = jcas.getJFSIndexRepository ();
            AnnotationIndex<? extends Annotation> sdi_ind = idxs.getAnnotationIndex(m_typeId);
            return sdi_ind.subiterator(enclosing);
        }
    }
    
    static private class EnclosedConstraintDescriptor extends ConstraintDescriptor 
    {
        private EnclosedConstraintDescriptor (JCas   jcas,
                                              int    typeId)
        throws ClassNotFoundException,
               IllegalArgumentException,
               SecurityException,
               IllegalAccessException,
               NoSuchFieldException
        {
            super(jcas, typeId, Arrays.asList(new String[] {"begin", "end"}));
        }
        
        public EnclosedConstraintDescriptor (JCas jcas, Class<? extends Annotation> enclosed)
        throws ClassNotFoundException,
               IllegalArgumentException,
               SecurityException,
               IllegalAccessException,
               NoSuchFieldException
        {
            this(jcas, UIMAAnnotationUtils.getTypeIndexId(enclosed));
        }

        FSMatchConstraint createConstraint (JCas    jcas,
                                            int     begin_lower,
                                            int     begin_upper,
                                            int     end_lower,
                                            int     end_upper)
        {
            FeaturePath begin_fp = m_feat2path.get("begin");              
            FeaturePath end_fp = m_feat2path.get("end");              

            ConstraintFactory cf = jcas.getConstraintFactory();
            FSIntConstraint bcLower = cf.createIntConstraint(); 
            bcLower.geq(begin_lower);
            FSMatchConstraint mcBegin = cf.embedConstraint(begin_fp, bcLower); 
            if (begin_upper > begin_lower) {
                FSIntConstraint bcUpper = cf.createIntConstraint();
                bcUpper.lt(begin_upper);
                mcBegin = cf.and(mcBegin, cf.embedConstraint(begin_fp, bcUpper));
            }

            FSIntConstraint ecUpper = cf.createIntConstraint(); 
            ecUpper.leq(end_upper);         
            FSMatchConstraint mcEnd = cf.embedConstraint(end_fp, ecUpper); 
            if (end_upper > end_lower) {
                FSIntConstraint ecLower = cf.createIntConstraint();
                ecLower.gt(end_lower);
                mcEnd = cf.and(mcEnd, cf.embedConstraint(end_fp, ecLower));
            }
            
            FSMatchConstraint mcBounds = cf.and(mcBegin, mcEnd);
            return mcBounds;
        }
    }
    
    static private class FeatureConstraintDescriptor extends ConstraintDescriptor 
    {
        private FeatureConstraintDescriptor (JCas               jcas,
                                             int                typeId,
                                             Collection<String> featureNames)
         throws ClassNotFoundException,
                IllegalArgumentException,
                SecurityException,
                IllegalAccessException,
                NoSuchFieldException
         {
             super(jcas, typeId, featureNames);
         }
         
        public FeatureConstraintDescriptor (JCas                        jcas,
                                            Class<? extends Annotation> annotation_class,
                                            Collection<String>          feature_names)
         throws ClassNotFoundException,
                IllegalArgumentException,
                SecurityException,
                IllegalAccessException,
                NoSuchFieldException
         {
            this(jcas, UIMAAnnotationUtils.getTypeIndexId(annotation_class), feature_names);
         }
         
        FSMatchConstraint createConstraint (JCas        jcas,
                                            String      feature_name,
                                            Object[]    values)
        {
            ConstraintFactory cf = jcas.getConstraintFactory();

            FeaturePath feature_path = m_feat2path.get(feature_name);
            FSMatchConstraint feat_mc = null;
            for (int i = 0; i < values.length; ++i) {
                FSStringConstraint val_sc = cf.createStringConstraint();
                val_sc.equals(values[i].toString());
                if (null == feat_mc) {
                    feat_mc = cf.embedConstraint(feature_path, val_sc);
                }
                else {
                    feat_mc = cf.or(feat_mc, cf.embedConstraint(feature_path, val_sc));
                }
            }
            return feat_mc; 
        }
    }
    
    
    static public FSIterator<? extends Annotation> getEnclosedIterator(JCas                           jcas,
                                                                       Class<? extends Annotation>    enclosed,
                                                                       int                            begin,
                                                                       int                            end)
        throws IllegalArgumentException,
               SecurityException,
               ClassNotFoundException,
               IllegalAccessException,
               NoSuchFieldException
    {
        return getEnclosedIterator(jcas, enclosed, begin, begin, end, end);
    }

    static public FSIterator<? extends Annotation> getEnclosedIterator(JCas   jcas,
                                                                       int[]  typeIds,
                                                                       int    begin,
                                                                       int    end)
        throws IllegalArgumentException,
               SecurityException,
               ClassNotFoundException,
               IllegalAccessException,
               NoSuchFieldException
    {
        return getEnclosedIterator(jcas, typeIds, begin, begin, end, end);
    }

    static public FSIterator<? extends Annotation> getEnclosedIterator(JCas                           jcas,
                                                                       Class<? extends Annotation>    enclosed,
                                                                       int                            begin_lower,
                                                                       int                            begin_upper,
                                                                       int                            end_lower,
                                                                       int                            end_upper)
    throws IllegalArgumentException,
           SecurityException,
           ClassNotFoundException,
           IllegalAccessException,
           NoSuchFieldException
    {
        EnclosedConstraintDescriptor ecDescr = new EnclosedConstraintDescriptor(jcas, enclosed);
        FSMatchConstraint mcBounds = ecDescr.createConstraint(jcas, begin_lower, begin_upper, end_lower, end_upper);
        return ecDescr.createFilteredIterator(jcas, mcBounds); 
    }
    
    
    static public FSIterator<? extends Annotation> getEnclosedIterator(JCas   jcas,
                                                                       int[]  enclosedTypes,
                                                                       int    begin_lower,
                                                                       int    begin_upper,
                                                                       int    end_lower,
                                                                       int    end_upper)
    throws IllegalArgumentException,
           SecurityException,
           ClassNotFoundException,
           IllegalAccessException,
           NoSuchFieldException
    {
        EnclosedConstraintDescriptor ecDescr = new EnclosedConstraintDescriptor(jcas, Annotation.typeIndexID);
        FSMatchConstraint mcBounds = ecDescr.createConstraint(jcas, begin_lower, begin_upper, end_lower, end_upper);
        
        if ((null != enclosedTypes) && (0 < enclosedTypes.length)) {
            ConstraintFactory cf = jcas.getConstraintFactory();
            FSTypeConstraint tc = cf.createTypeConstraint();
            for (int i = 0; i < enclosedTypes.length; ++i) {
                tc.add(jcas.getCasType(enclosedTypes[i]));
            }
            mcBounds = cf.and(mcBounds, tc);
        }
        return ecDescr.createFilteredIterator(jcas, mcBounds); 
    }
    
    
    static public FSIterator<? extends Annotation> getEnclosedIterator(JCas                           jcas,
                                                                       Class<? extends Annotation>    enclosed,
                                                                       Annotation                     enclosing)
    throws IllegalArgumentException, 
           SecurityException,
           ClassNotFoundException,
           IllegalAccessException,
           NoSuchFieldException
    {
        if (null == enclosing) {
            return getEnclosedIterator(jcas, enclosed, 0, Integer.MAX_VALUE);
        }
        
        SubiteratorConstraintDescriptor scd = new SubiteratorConstraintDescriptor(jcas, enclosed);
        return scd.createSubiterator(jcas, enclosing);
    }
    
    static public FSIterator<? extends Annotation> getEnclosedIterator(JCas jcas,
                                                                       String enclosed_name,
                                                                       Annotation enclosing)
        throws IllegalArgumentException,
               SecurityException,
               ClassNotFoundException,
               IllegalAccessException,
               NoSuchFieldException
    {
        return getEnclosedIterator(jcas, UIMAAnnotationUtils.forName(enclosed_name), enclosing);
    }
    
    static public FSIterator<? extends Annotation> getEnclosedIterator(JCas jcas,
                                                                       int[] typeIds,
                                                                       Annotation enclosing)
        throws IllegalArgumentException,
               SecurityException,
               ClassNotFoundException,
               IllegalAccessException,
               NoSuchFieldException
    {
        return getEnclosedIterator(jcas, typeIds, enclosing.getBegin(), enclosing.getEnd());
    }
    
    static public FSIterator<? extends Annotation> getLeftIterator (JCas                          jcas,
                                                                    Class<? extends Annotation>   feat_ann_class,
                                                                    Annotation                    enclosing,
                                                                    Annotation                    target)
    throws IllegalArgumentException,
           SecurityException,
           ClassNotFoundException,
           IllegalAccessException,
           NoSuchFieldException
    {
        int begin = enclosing.getBegin();
        int end = target.getBegin();
        
        FSIterator<? extends Annotation> it = FeatureConstrainedIterator.getEnclosedIterator(jcas, feat_ann_class, begin, end);
        it.moveToLast();
        return it;
    }
    
    static public FSIterator<? extends Annotation> getLeftIterator (JCas          jcas,
                                                                    int[]         typeIds,
                                                                    Annotation    enclosing,
                                                                    Annotation    target)
    throws IllegalArgumentException,
           SecurityException,
           ClassNotFoundException,
           IllegalAccessException,
           NoSuchFieldException
    {
        int begin = enclosing.getBegin();
        int end = target.getBegin();
        
        FSIterator<? extends Annotation> it = FeatureConstrainedIterator.getEnclosedIterator(jcas, typeIds, begin, end);
        it.moveToLast();
        return it;
    }
    
    static public FSIterator<? extends Annotation> getRightIterator (JCas                         jcas,
                                                                     Class<? extends Annotation>  feat_ann_class,
                                                                     Annotation                   enclosing,
                                                                     Annotation                   target)
    throws IllegalArgumentException,
           SecurityException,
           ClassNotFoundException,
           IllegalAccessException,
           NoSuchFieldException
    {
        int begin = target.getEnd();
        int end = enclosing.getEnd();
        
        return FeatureConstrainedIterator.getEnclosedIterator(jcas, feat_ann_class, begin, end);
    }
    
    static public FSIterator<? extends Annotation> getEnclosedIterator(JCas                           jcas,
                                                                       Class<? extends Annotation>    enclosed,
                                                                       int                            begin_lower,
                                                                       int                            begin_upper,
                                                                       int                            end_lower,
                                                                       int                            end_upper,
                                                                       String                         feat_name,
                                                                       Object[]                       values)
        throws IllegalArgumentException,
               SecurityException,
               ClassNotFoundException,
               IllegalAccessException,
               NoSuchFieldException
    {
        
        EnclosedConstraintDescriptor ecDescr = new EnclosedConstraintDescriptor(jcas, enclosed);
        FeatureConstraintDescriptor fcDescr = new FeatureConstraintDescriptor(jcas, enclosed, Arrays.asList(new String[] {feat_name}));
        
        FSMatchConstraint mc_bounds = ecDescr.createConstraint(jcas, begin_lower, begin_upper, end_lower, end_upper);
        FSMatchConstraint mc_feature = fcDescr.createConstraint(jcas, feat_name, values);
        
        return fcDescr.createFilteredIterator(jcas, jcas.getConstraintFactory().and(mc_bounds, mc_feature));
    }

    
    static public FSIterator<? extends Annotation> getEnclosedIterator(JCas                           jcas,
                                                                       Class<? extends Annotation>    enclosed,
                                                                       int                            begin,
                                                                       int                            end,
                                                                       String                         feat_name,
                                                                       Object[]                       values)
        throws IllegalArgumentException,
               SecurityException,
               ClassNotFoundException,
               IllegalAccessException,
               NoSuchFieldException
    {
        return getEnclosedIterator(jcas, enclosed, begin, begin, end, end, feat_name,values);
    }

    static public FSIterator<? extends Annotation> getEnclosedIterator(JCas                           jcas,
                                                                       Class<? extends Annotation>    enclosed,
                                                                       Annotation                     enclosing,
                                                                       String                         feat_name,
                                                                       Object[]                       values)
        throws IllegalArgumentException,
               SecurityException,
               ClassNotFoundException,
               IllegalAccessException,
               NoSuchFieldException
    {
        return getEnclosedIterator(jcas, enclosed, enclosing.getBegin(), enclosing.getEnd(), feat_name, values);
    }

    static public List<Annotation> getEnclosingAnnotations (JCas                        jcas,
                                                            Class<? extends Annotation> enclosing_class,
                                                            Annotation                  enclosed)
    throws IllegalArgumentException,
           SecurityException,
           IllegalAccessException,
           NoSuchFieldException
    {
        List<Annotation> result = new ArrayList<Annotation>();
        for (Iterator<? extends Annotation> enclosing_it = UIMAAnnotationUtils.iterator(jcas, enclosing_class); enclosing_it.hasNext();) {
            Annotation enclosing = enclosing_it.next();
            if ((enclosing.getBegin() <= enclosed.getBegin()) && (enclosing.getEnd() >= enclosed.getEnd())) {
                result.add(enclosing);
            }
        }
        return result;
    }
    
    static public Annotation getEnclosingAnnotation (JCas                        jcas,
                                                     Class<? extends Annotation> enclosing_class,
                                                     Annotation                  enclosed)
    throws IllegalArgumentException,
           SecurityException,
           IllegalAccessException,
           NoSuchFieldException
    {
        for (Iterator<? extends Annotation> enclosing_it = UIMAAnnotationUtils.iterator(jcas, enclosing_class); enclosing_it.hasNext();) {
            Annotation enclosing = enclosing_it.next();
            if ((enclosing.getBegin() <= enclosed.getBegin()) && (enclosing.getEnd() >= enclosed.getEnd())) {
                return enclosing;
            }
        }
        return null;
    }
    
    static public FSIterator<? extends Annotation> getSameOffsetIterator (JCas                        jcas,
                                                                          Class<? extends Annotation> enclosed_class,
                                                                          Annotation                  enclosing)
    throws IllegalArgumentException,
           SecurityException,
           ClassNotFoundException,
           IllegalAccessException,
           NoSuchFieldException
    {
        return getEnclosedIterator(jcas, enclosed_class,
                                   enclosing.getBegin(), enclosing.getBegin() + 1,
                                   enclosing.getEnd() - 1, enclosing.getEnd()); 
    }
    
    static public FSIterator<? extends Annotation> getSameOffsetIterator (JCas                        jcas,
                                                                          Class<? extends Annotation> enclosed_class,
                                                                          Annotation                  enclosing,
                                                                          String                      feat_name,
                                                                          Object[]                    values)
    throws IllegalArgumentException, SecurityException, ClassNotFoundException, IllegalAccessException, NoSuchFieldException
    {
        return getEnclosedIterator(jcas, enclosed_class,
                                   enclosing.getBegin(), enclosing.getBegin() + 1,
                                   enclosing.getEnd() - 1, enclosing.getEnd(),
                                   feat_name, values); 
    }
}


