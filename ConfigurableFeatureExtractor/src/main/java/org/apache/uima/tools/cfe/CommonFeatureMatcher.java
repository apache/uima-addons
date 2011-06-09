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
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.cas.FSIterator;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.tools.cfe.support.FeatureConstrainedIterator;
import org.apache.uima.tools.cfe.support.UIMAAnnotationUtils;
import org.apache.xmlbeans.XmlException;


public abstract class CommonFeatureMatcher
{
    abstract protected void processFeatureGroup(JCas                                    jcas,
                                                Annotation                              trg, 
                                                String                                  group_label,
                                                Collection<MatchedAnnotationDescriptor> features)
    throws IllegalArgumentException, IllegalAccessException, InvocationTargetException;
        
    
    private Map<AnnotationMatchedValue, Map<String, Collection<MatchedAnnotationDescriptor>>> m_feature_annotations;
    private Collection<TargetAnnotationDescriptor>                                  m_TADescriptors;
    
    public final boolean    m_isAnnotationName;
    public final boolean    m_isFeatureName;
    public final boolean    m_isTargetId;
    
    public final String     m_fieldSeparator;
    public final String[]   m_valueSeparators;

    public static final String PARAM_CONFIGURATIONFILE      = "ConfigurationFile";
    public static final String PARAM_INCLUDETARGETID        = "IncludeTargetId";
    public static final String PARAM_INCLUDEANNOTATIONNAME  = "IncludeAnnotationName";
    public static final String PARAM_INCLUDEFEATURENAME     = "IncludeFeatureName";
    public static final String PARAM_XMLBEANSPARSER         = "XMLBeansParser";

    public static final int CONSTANT_INGORE         = 0;
    public static final int CONSTANT_LEFTLEFT       = 1 << 0; // 1
    public static final int CONSTANT_LEFTINSIDE     = 1 << 1; // 2
    public static final int CONSTANT_LEFTRIGHT      = 1 << 2; // 4
    public static final int CONSTANT_INSIDEINSIDE   = 1 << 3; // 8
    public static final int CONSTANT_INSIDERIGHT    = 1 << 4; // 16
    public static final int CONSTANT_RIGHTRIGHT     = 1 << 5; // 32
    
    public static final int CONSTANT_STARTSLEFT =
        CONSTANT_LEFTLEFT | CONSTANT_LEFTINSIDE | CONSTANT_LEFTRIGHT;       // 7 
    
    public static final int CONSTANT_STARTSINSIDE =
        CONSTANT_INSIDEINSIDE | CONSTANT_INSIDERIGHT;                       // 24 
    
    public static final int CONSTANT_STARTSRIGHT =
        CONSTANT_RIGHTRIGHT;                                                // 32
    
    public static final int CONSTANT_ANYENCLOSED =
        CONSTANT_STARTSLEFT | CONSTANT_STARTSINSIDE | CONSTANT_STARTSRIGHT; // 63 
    
    CommonFeatureMatcher(boolean    isTargetId,
                         boolean    isAnnotationName,
                         boolean    isFeatureName,
                         String     fieldSeparator,
                         String[]   valueSeparators)
    {
        m_isTargetId = isTargetId;
        m_isAnnotationName = isAnnotationName;
        m_isFeatureName = isFeatureName;
        m_fieldSeparator = fieldSeparator;
        m_valueSeparators = valueSeparators;
    }
    
    public void initialize (String configfile, boolean isXMLBeansParser)
    throws SecurityException,
           ClassNotFoundException,
           NoSuchMethodException,
           URISyntaxException,
           IOException,
           XmlException
    {
        if (isXMLBeansParser) {
            parseConfigFile_XMLBeans(configfile);
        }
        else {
            parseConfigFile_Ecore(configfile);
        }
    }
    
    void parseConfigFile_Ecore (String path)
    throws IOException,
           ClassNotFoundException,
           SecurityException,
           NoSuchMethodException,
           URISyntaxException
    {
        CFEConfigFromXML_Ecore xml2CFE = new CFEConfigFromXML_Ecore(path); 
        
        if (null != xml2CFE.getNullValueImage()) {
            PrimitiveFeatureTransformer.nullValueImage = xml2CFE.getNullValueImage(); 
        }
        m_TADescriptors = xml2CFE.getTargetAnnotationDescriptors();  
    }
    
    void parseConfigFile_XMLBeans (String path)
    throws IOException,
           ClassNotFoundException,
           SecurityException,
           NoSuchMethodException,
           URISyntaxException,
           XmlException
    {
        CFEConfigFromXML_XMLBeans xml2CFE = new CFEConfigFromXML_XMLBeans(path); 
        
        if (null != xml2CFE.getNullValueImage()) {
            PrimitiveFeatureTransformer.nullValueImage = xml2CFE.getNullValueImage(); 
        }
        m_TADescriptors = xml2CFE.getTargetAnnotationDescriptors();  
    }

    void processSelectedFeatureAnnotation(JCas jcas)
    throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
    {
        for (Iterator<AnnotationMatchedValue> it = m_feature_annotations.keySet().iterator(); it.hasNext();) {
            AnnotationMatchedValue trg_mv = it.next();
            Map<String, Collection<MatchedAnnotationDescriptor>> target_feature_groups =
                m_feature_annotations.get(trg_mv);
            
            for (Map.Entry<String, Collection<MatchedAnnotationDescriptor>> entry : target_feature_groups.entrySet()) {
                String class_label = entry.getKey();
                Collection<MatchedAnnotationDescriptor> features = entry.getValue();
                processFeatureGroup(jcas, trg_mv.getAnnotation(), class_label, features);
            }
        }
    }
    
    
    protected static String make_image_position (MatchedAnnotationDescriptor mad)
    {
        StringBuilder strBuf = new StringBuilder();
        if (mad.m_feature_matcher.m_orientation) {
            strBuf.append((CONSTANT_LEFTLEFT == mad.m_direction) ? "L" :
                            (CONSTANT_LEFTINSIDE == mad.m_direction) ? "LI" :
                                (CONSTANT_LEFTRIGHT == mad.m_direction) ? "LR" :
                                    (CONSTANT_INSIDEINSIDE == mad.m_direction) ? "I" :
                                        (CONSTANT_INSIDERIGHT == mad.m_direction) ? "IR" :
                                            (CONSTANT_RIGHTRIGHT == mad.m_direction) ? "R" : "X");
        }
        if (mad.m_feature_matcher.m_distance) {
            strBuf.append(mad.m_offset);
        }
        return strBuf.toString();
    }
    

    protected String make_image (MatchedAnnotationDescriptor mad, Annotation trg)
    throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
    {
        StringBuilder sb = new StringBuilder(mad.m_sfms_with_values.size() * 20);
        
        if (m_isAnnotationName) {
            String[] name_toks = mad.m_feature_mv.m_matchedObject.getClass().getName().split("\\.");
            sb.append(name_toks[name_toks.length - 1]);
        }
        
        for (Iterator<MatchedSingleFeatureMatcher> fm_it = mad.m_sfms_with_values.iterator(); fm_it.hasNext();) {
            MatchedSingleFeatureMatcher sfm_values = fm_it.next();
            if (!sfm_values.m_matcher.m_quiet) {
                if (0 != sb.length()) {
                    sb.append(m_fieldSeparator);
                }
                sb.append(sfm_values.getFeatureImage(m_isFeatureName, m_valueSeparators));
            }
        }
        if (m_isTargetId) {
            sb.append(m_fieldSeparator);
            sb.append(trg.getAddress());
        }
        return sb.toString();
    }
    
    static private Pattern m_featname_pattern = Pattern.compile("\\{([^\\}\\s]+)\\}");
    
    String parseClassLabel (String class_label, MatchedValue trg_mv)
    throws IllegalArgumentException,
           IllegalAccessException,
           InvocationTargetException,
           ClassNotFoundException
    {
        Matcher m = m_featname_pattern.matcher(class_label);
        
        StringBuilder result = new StringBuilder();
        int class_label_ind = 0;
        
        while (m.find()) {
            String featname = m.group(1);
            int begin = m.start(1);
            int end = m.end(1);
            if (begin  - 1 > class_label_ind) { // -1 for the '['
                result.append(class_label.substring(class_label_ind, begin - 1));
            }
            class_label_ind = end + 1;
            TargetObjectMatcher tom = new TargetObjectMatcher((Class<?>)null, trg_mv.m_matchedObject.getClass().getName() + ":" + featname, false);
            List<MatchedValue> vals = tom.getFeatureValues(trg_mv);
            StringBuilder feat_image = new StringBuilder();
            for (Iterator<MatchedValue> it = vals.iterator(); it.hasNext();){
                feat_image.append(it.next().m_matchedObject);
                if (it.hasNext()) {
                    feat_image.append(m_fieldSeparator);
                }
            }
            result.append(feat_image);
        }
        result.append(class_label.substring(class_label_ind));
        return result.toString(); 
    }
    
    void collectFeatures (JCas                          jcas,
                          TargetAnnotationDescriptor    tad,
                          AnnotationMatchedValue        trg_mv,
                          MatchedAnnotationDescriptor   feat_ann_mad)
    throws IllegalArgumentException,
           IllegalAccessException,
           InvocationTargetException,
           ClassNotFoundException
    {
        Map<String, Collection<MatchedAnnotationDescriptor>> target_feature_groups = null;
        if (m_feature_annotations.containsKey(trg_mv)) {
            target_feature_groups = m_feature_annotations.get(trg_mv);
        }
        else {
            target_feature_groups = new HashMap<String, Collection<MatchedAnnotationDescriptor>>();
            m_feature_annotations.put(trg_mv, target_feature_groups);
        }
        
        Collection<MatchedAnnotationDescriptor> features = null;
        String class_label = parseClassLabel(tad.getClassName(), trg_mv);
        if (target_feature_groups.containsKey(class_label)) {
            features = target_feature_groups.get(class_label);
        }
        else {
            features = new ArrayList<MatchedAnnotationDescriptor>();
            target_feature_groups.put(class_label, features);
        }
        features.add(feat_ann_mad);
    }
    
    public static String getDocumentId (JCas jcas, Annotation doc_ann)
    {
        // doc_ann contains a span that covers the doc
        // override this method to extract doc id from doc_ann
        // for example in WSD it is SnippetAnnotation
        String doc_id = null;
        try {
            Iterator<? extends Annotation> it;
            it = UIMAAnnotationUtils.iterator(jcas, SourceDocumentInformation.class);
            while (it.hasNext() && (null == doc_id)) {
                SourceDocumentInformation sdi = (SourceDocumentInformation)it.next();
                String[] toks = sdi.getUri().split("\\/");
                doc_id = toks[toks.length - 1];
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null == doc_id ? "" : doc_id;
    }
    
    boolean processFeatureAnnotation (JCas                          jcas,
                                      Annotation                    enclosing,
                                      AnnotationMatchedValue        trg_mv,
                                      AnnotationMatchedValue        feat_mv,
                                      TargetAnnotationDescriptor    tad,
                                      FeatureObjectMatcher          fom,
                                      int                           direction,
                                      int                           offset)
    throws IllegalArgumentException,
           IllegalAccessException,
           InvocationTargetException,
           ClassNotFoundException
    {
        // individual features that were matched 
        Collection<MatchedSingleFeatureMatcher> ann_matched_features = new ArrayList<MatchedSingleFeatureMatcher>();
        if (fom.matches(feat_mv, ann_matched_features)) {
            MatchedAnnotationDescriptor mad = new MatchedAnnotationDescriptor(
                    fom, enclosing, feat_mv, ann_matched_features, direction, offset, tad.m_priorityOrder);
            collectFeatures(jcas, tad, trg_mv, mad);
            return true;
        }
        return false;
    }
    
    private int orientationFlags (Annotation ann, Annotation trg)
    {
        int result = CONSTANT_INGORE;
        if ((ann.getEnd() < trg.getBegin()) && (ann.getEnd() < trg.getBegin())) {
            result = CONSTANT_LEFTLEFT; 
        }
        else if ((ann.getBegin() < trg.getBegin()) && (ann.getEnd() <= trg.getEnd())) {
            result = CONSTANT_LEFTINSIDE; 
        }
        else if ((ann.getBegin() < trg.getBegin()) && (ann.getEnd() > trg.getEnd())) {
            result = CONSTANT_LEFTRIGHT; 
        }
        else if ((ann.getBegin() <= trg.getEnd()) && (ann.getEnd() <= trg.getEnd())) {
            result = CONSTANT_INSIDEINSIDE; 
        }
        else if ((ann.getBegin() <= trg.getEnd()) && (ann.getEnd() > trg.getEnd())) {
            result = CONSTANT_INSIDERIGHT; 
        }
        else if ((ann.getBegin() > trg.getEnd()) && (ann.getEnd() > trg.getEnd())) {
            result = CONSTANT_RIGHTRIGHT; 
        }
        else {
            // never happens
        }
        return result;
    }
    

    void processFeatureAnnotationWindow(JCas                                jcas,
                                        Annotation                          enclosing,
                                        AnnotationMatchedValue              trg_mv,
                                        TargetAnnotationDescriptor          tad,
                                        FeatureObjectMatcher                fom,
                                        Collection<AnnotationMatchedValue>  allowedAnnotations,
                                        int                                 begin_lower,
                                        int                                 begin_upper,
                                        int                                 end_lower,
                                        int                                 end_upper,
                                        int                                 windowsize,
                                        boolean                             advance_right)
    throws IllegalArgumentException,
           SecurityException,
           NoSuchElementException,
           ClassNotFoundException,
           IllegalAccessException,
           NoSuchFieldException,
           InvocationTargetException
    {
        Class<? extends Annotation> ann_cls = UIMAAnnotationUtils.getAnnotationClass(fom.getMatcherClass()); 
        FSIterator<? extends Annotation> ann_it = FeatureConstrainedIterator.getEnclosedIterator(
                jcas, ann_cls, begin_lower, begin_upper, end_lower, end_upper);
        if (!advance_right) {
            ann_it.moveToLast();
        }
        
        Annotation trg = (Annotation)trg_mv.m_matchedObject;
        
        for (int wc = 1; ann_it.isValid() && (wc <= windowsize);) {
            Annotation ann = (Annotation)ann_it.get();
            AnnotationMatchedValue feat_mv = MatchedValue.get(allowedAnnotations, ann);
            if (null == feat_mv) {
                continue;
            }
            
            if (processFeatureAnnotation(jcas, enclosing, trg_mv, feat_mv, tad, fom, orientationFlags(ann, trg), wc)) {
                ++wc;
            }

            if (advance_right) {
                ann_it.moveToNext();                
            }
            else {
                ann_it.moveToPrevious();
            }
        }
    }

    void processFeatureAnnotationWindow (JCas                               jcas,
                                         Annotation                         enclosing,
                                         AnnotationMatchedValue             trg_mv,
                                         TargetAnnotationDescriptor         tad,
                                         FeatureObjectMatcher               fom,
                                         Collection<AnnotationMatchedValue> allowedAnnotations,
                                         int                                windowflags,                         
                                         int                                windowsize,
                                         boolean                            advance_right)
    throws IllegalArgumentException,
           SecurityException,
           NoSuchElementException,
           ClassNotFoundException,
           IllegalAccessException,
           NoSuchFieldException,
           InvocationTargetException
    {
        int bl = Integer.MAX_VALUE, bu = -Integer.MAX_VALUE, el = Integer.MAX_VALUE, eu = -Integer.MAX_VALUE;
        
        if (CONSTANT_ANYENCLOSED == (windowflags & CONSTANT_ANYENCLOSED)) {
            bl = bu = enclosing.getBegin();
            el = eu = enclosing.getEnd();
        }
        else {
            Annotation trg = (Annotation)trg_mv.m_matchedObject;
            if (0 != (windowflags & CONSTANT_LEFTLEFT)) {
                bl = Math.min(bl, enclosing.getBegin());
                bu = Math.max(bu, trg.getBegin());
                el = Math.min(el, enclosing.getBegin());
                eu = Math.max(eu, trg.getBegin());
            }
            if (0 != (windowflags & CONSTANT_LEFTINSIDE)) {
                bl = Math.min(bl, enclosing.getBegin());
                bu = Math.max(bu, trg.getBegin());
                el = Math.min(el, trg.getBegin());
                eu = Math.max(eu, trg.getEnd());
            }
            if (0 != (windowflags & CONSTANT_LEFTRIGHT)) {
                bl = Math.min(bl, enclosing.getBegin());
                bu = Math.max(bu, trg.getBegin());
                el = Math.min(el, trg.getEnd());
                eu = Math.max(eu, enclosing.getEnd());
            }
            if (0 != (windowflags & CONSTANT_INSIDEINSIDE)) {
                bl = Math.min(bl, trg.getBegin());
                bu = Math.max(bu, trg.getEnd());
                el = Math.min(el, trg.getBegin());
                eu = Math.max(eu, trg.getEnd());
            }
            if (0 != (windowflags & CONSTANT_INSIDERIGHT)) {
                bl = Math.min(bl, trg.getBegin());
                bu = Math.max(bu, trg.getEnd());
                el = Math.min(el, trg.getEnd());
                eu = Math.max(eu, enclosing.getEnd());
            }
            if (0 != (windowflags & CONSTANT_RIGHTRIGHT)) {
                bl = Math.min(bl, trg.getEnd());
                bu = Math.max(bu, enclosing.getEnd());
                el = Math.min(el, trg.getEnd());
                eu = Math.max(eu, enclosing.getEnd());
            }
            if (Integer.MAX_VALUE == bl) {
                throw new IllegalArgumentException("Invalid windowflags: " + windowflags);
            }
            if ((bl == el) && (bu == eu)) {
                bu = bl;
                el = eu;
            }
        }
        processFeatureAnnotationWindow(jcas, enclosing, trg_mv, tad, fom, allowedAnnotations,
                                       bl, bu, el, eu, windowsize, advance_right);
    }
    
    
    private Map<PartialObjectMatcher, Collection<AnnotationMatchedValue>> 
    createAllowedAnnotationsMap(JCas jcas, Collection<? extends PartialObjectMatcher> poms, Annotation enclosing)
    throws ClassNotFoundException,
           IllegalArgumentException,
           SecurityException,
           IllegalAccessException,
           NoSuchFieldException,
           InvocationTargetException
    {
        Map<PartialObjectMatcher, Collection<AnnotationMatchedValue>> per_model_matched =
            new HashMap<PartialObjectMatcher, Collection<AnnotationMatchedValue>>();
        Collection<MatchedValue> all_models_matched = new HashSet<MatchedValue>();
        
        for (Iterator<? extends PartialObjectMatcher> ta_it = poms.iterator(); ta_it.hasNext();) {
            PartialObjectMatcher pom = ta_it.next();
            if (!per_model_matched.keySet().contains(pom)) {
                ObjectPathFeatureValues opvfs = 
                    new ObjectPathFeatureValues(pom.getMatcherClass().getName(), pom.getObjectPath());
                opvfs.update(jcas, enclosing, all_models_matched);
                if (!opvfs.m_values.isEmpty()) {
                    Collection<AnnotationMatchedValue> amvs = new ArrayList<AnnotationMatchedValue>();
                    per_model_matched.put(pom, AnnotationMatchedValue.upcast(amvs, opvfs.m_values));
                }
            }
        }
        return per_model_matched;
    }
    
    void processMatchedTargetAnnotation(JCas                        jcas,
                                        Annotation                  enclosing,
                                        AnnotationMatchedValue      trg_mv,
                                        TargetAnnotationDescriptor  tad)
    throws IllegalArgumentException,
           IllegalAccessException,
           InvocationTargetException,
           SecurityException,
           NoSuchElementException,
           ClassNotFoundException,
           NoSuchFieldException
    {
        
        Map<PartialObjectMatcher, Collection<AnnotationMatchedValue>> allowedAnnotationsMap =
            createAllowedAnnotationsMap(jcas, tad.getFeatureAnnotationMatchers(), enclosing); 
        
        for (Iterator<FeatureObjectMatcher> it = tad.getFeatureAnnotationMatchers().iterator(); it.hasNext();) {
            // matcher for annotations from whose features are extracted
            FeatureObjectMatcher fom = it.next();
            Collection<AnnotationMatchedValue> allowedAnnotations = allowedAnnotationsMap.get(fom);
            
            if (fom.m_windowsizeLeft > 0) {
                int mask = fom.m_windowFlags & CONSTANT_STARTSLEFT;
                processFeatureAnnotationWindow(jcas, enclosing, trg_mv, tad, fom, allowedAnnotations,
                                               0 == mask ? CONSTANT_LEFTLEFT : mask,
                                               fom.m_windowsizeLeft, false);
            }
            if ((1 == fom.m_windowsizeInside) && tad.getTargetAnnotationMatcher().getMatcherClass().equals(fom.getMatcherClass())) {
                processFeatureAnnotation(jcas, enclosing, trg_mv, trg_mv, tad, fom, CONSTANT_INSIDEINSIDE, 1);
            }
            else if (fom.m_windowsizeInside > 0) {
                int mask = fom.m_windowFlags & CONSTANT_STARTSINSIDE;
                processFeatureAnnotationWindow(jcas, enclosing, trg_mv, tad, fom, allowedAnnotations,
                                               0 == mask ? CONSTANT_INSIDEINSIDE : mask,
                                               fom.m_windowsizeInside, true);
            }
            if (fom.m_windowsizeRight > 0) {
                int mask = fom.m_windowFlags & CONSTANT_STARTSRIGHT;
                processFeatureAnnotationWindow(jcas, enclosing, trg_mv, tad, fom, allowedAnnotations,
                                               0 == mask ? CONSTANT_RIGHTRIGHT : mask,
                                               fom.m_windowsizeRight, true);
            }
            if (fom.m_windowsizeEnclosed > 0) {
                processFeatureAnnotationWindow(jcas, enclosing, trg_mv, tad, fom, allowedAnnotations, 
                                               CONSTANT_ANYENCLOSED,
                                               fom.m_windowsizeEnclosed, true);
            }
        }
    }
    

    void processEnclosingAnnotation(JCas                                jcas,
                                    Annotation                          enclosing,
                                    Collection<AnnotationMatchedValue>  targetAnnotations,
                                    TargetAnnotationDescriptor          tad)
    throws IllegalArgumentException,
           IllegalAccessException,
           InvocationTargetException,
           SecurityException,
           NoSuchElementException,
           ClassNotFoundException,
           NoSuchFieldException
    {
        
        PartialObjectMatcher targetAnnotationMatcher = tad.getTargetAnnotationMatcher();
        
        Class<? extends Annotation> trg_cls = UIMAAnnotationUtils.getAnnotationClass(targetAnnotationMatcher.getMatcherClass()); 
        for (Iterator<?> it = FeatureConstrainedIterator.getEnclosedIterator(
                jcas, trg_cls, enclosing.getBegin(), enclosing.getEnd()); it.hasNext();) { 
            Annotation trg = (Annotation)it.next();
            AnnotationMatchedValue trg_mv = MatchedValue.get(targetAnnotations, trg);
            if (null == trg_mv) {
                continue;
            }
            Collection<MatchedSingleFeatureMatcher> trg_matched_features = new ArrayList<MatchedSingleFeatureMatcher>();
            if (targetAnnotationMatcher.matches(trg_mv, trg_matched_features)) {
                processMatchedTargetAnnotation(jcas, enclosing, trg_mv, tad);
            }
        }
    }
    
    public void processJCas (JCas jcas)
    throws IllegalArgumentException,
           IllegalAccessException,
           InvocationTargetException,
           ClassNotFoundException,
           SecurityException,
           NoSuchFieldException
    {
        m_feature_annotations = new HashMap<AnnotationMatchedValue, Map<String, Collection<MatchedAnnotationDescriptor>>>();

        Collection<PartialObjectMatcher> tams = new ArrayList<PartialObjectMatcher>();

        for (Iterator<TargetAnnotationDescriptor> tad_it = m_TADescriptors.iterator(); tad_it.hasNext();) {
            TargetAnnotationDescriptor tad = tad_it.next();
            PartialObjectMatcher pom = tad.getTargetAnnotationMatcher(); 
            tams.add(pom);
            pom.update(jcas);
        }
        
        Map<PartialObjectMatcher, Collection<AnnotationMatchedValue>> allowedAnnotationsMap =
            createAllowedAnnotationsMap(jcas, tams, null);
        
        for (Iterator<TargetAnnotationDescriptor> ta_it = m_TADescriptors.iterator(); ta_it.hasNext();) {
            TargetAnnotationDescriptor tad = ta_it.next();
            if (!allowedAnnotationsMap.containsKey(tad.getTargetAnnotationMatcher())) {
                continue;
            }
            Collection<AnnotationMatchedValue> allowedAnnotations = allowedAnnotationsMap.get(tad.getTargetAnnotationMatcher());
            Class <? extends Annotation> cls = UIMAAnnotationUtils.forName(tad.getEnclosingAnnotationName());
            for (Iterator<? extends Annotation> eg_it = UIMAAnnotationUtils.iterator(jcas, cls); eg_it.hasNext();) {
                processEnclosingAnnotation(jcas, (Annotation)eg_it.next(), allowedAnnotations, tad);
            }
        }
        processSelectedFeatureAnnotation(jcas);
    }
}
