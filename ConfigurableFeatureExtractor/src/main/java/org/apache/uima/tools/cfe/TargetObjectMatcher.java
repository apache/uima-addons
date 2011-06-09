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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.jxpath.JXPathContext;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.tools.cfe.support.ArrayComparatorFinder;

public class TargetObjectMatcher
{
    private Class<?>        m_root_class;
    private Class<?>        m_target_class;
    private String[]        m_target_path_tokens;
    private final boolean   m_delimit_array_values;
    
    static private final String KEYWORD_SORT = "sort";
    
    

    private TargetObjectMatcher (Class<?> target_class, String[] full_target_path_tokens, boolean delimit_array_values)
    throws ClassNotFoundException
    {
        m_delimit_array_values = delimit_array_values;
        m_target_class = target_class;
        m_root_class = Class.forName(full_target_path_tokens[0]);
        m_target_path_tokens = new String[full_target_path_tokens.length - 1];
        for (int i = 1; i < full_target_path_tokens.length; ++i) {
            m_target_path_tokens[i - 1] = full_target_path_tokens[i];
        }
    }
    
    public TargetObjectMatcher (Class<? extends Object> target_class, String full_target_path, boolean delimit_array_values)
    throws ClassNotFoundException
    {
        this(target_class, full_target_path.split("\\:"), delimit_array_values);
    }

    public TargetObjectMatcher (String trg_cls_name, String full_target_path, boolean delimit_array_values)
    throws ClassNotFoundException
    {
        this((null == trg_cls_name) || (0 == trg_cls_name.length()) ? null : Class.forName(trg_cls_name),
              full_target_path,
              delimit_array_values);
    }
    
//    private TargetObjectMatcher (PartialObjectMatcher pom, boolean delimit_array_values)
//    throws ClassNotFoundException
//    {
//        this(pom.getMatcherClass(), pom.getObjectPath(), delimit_array_values);
//    }

    Class<?> getRootClass ()
    {
        return m_root_class;
    }

    Class<?> getTargetClass ()
    {
        return m_target_class;
    }

    boolean isTargetClassMatches (Class<?> cls, boolean exact)
    {
        if (null == m_target_class) {
            return exact ? (null == cls) : true;
        }
        return exact ?  m_target_class.equals(cls) :  m_target_class.isAssignableFrom(cls);
    }

    String getFeaturePathImage ()
    {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < m_target_path_tokens.length; ++i) {
            if (0 != i) {
                result.append("_");
            }
            result.append(m_target_path_tokens[i]);
        }
        return result.toString();
    }

    boolean isDetached()
    {
        return isTargetClassMatches(m_root_class, true);
    }
    
    static private Method findGetMethod (Class<?>      obj_class,
                                         String        method_name)
    {
        return findMethod(obj_class, method_name, (Class[])null, null);
    }
    
    static private Method findMethod (Class<?>      obj_class,
                                         String        method_name,
                                         Class<?>[]    method_params,
                                         Class<?>      method_return)
    {
        Method[] mtds = obj_class.getMethods();
        for (int m_ind = 0; m_ind < mtds.length; ++m_ind) {
            if (!mtds[m_ind].getName().equals(method_name)) {
                continue;
            }
            if ((null == method_params) && (mtds[m_ind].getParameterTypes().length > 0)) {
                continue;
            }
            else if ((null != method_params) && !Arrays.equals(method_params, mtds[m_ind].getParameterTypes())) {
                continue;
            }
            if ((null != method_return) && !method_return.equals(mtds[m_ind].getReturnType())) {
                continue;
            }
            return mtds[m_ind];
        }
        return null;
    }
    
    
    static private Pattern m_featname_pattern = Pattern.compile("([^\\[\\]]+)");
    
    static String parseFeatureName(String feature, List<String> postProcOps)
    {
        Matcher m = m_featname_pattern.matcher(feature);
        
        String result = null;
        while (m.find()) {
            String group = m.group();
            if (null == result) {
                result = group;
                continue;
            }
            postProcOps.add(group);
        }
        if (null == result) {
            return feature;
        }
        return result;
    }
    
    static Object[] processPostProcOps (Object[] elements, List<String> postProcOps)
    {
        List<Object> new_elements = new ArrayList<Object>();
        boolean use_new_elements = false;
        
        for (Iterator<String> it = postProcOps.iterator(); it.hasNext();) {
            String op_name = it.next();
            
            if (op_name.matches("^\\d+")) {
                use_new_elements = true;
                int ind = Integer.parseInt(op_name);
                if (ind < elements.length) {
                    new_elements.add(elements[ind]);
                }
            }
            else {
                if (op_name.matches("\\d+..(-1|\\d+)")) {
                    use_new_elements = true;
                    String[] boundaries = op_name.split("\\.\\.");
                    assert(2 == boundaries.length);
                    int b = Integer.parseInt(boundaries[0]);
                    int e = Integer.parseInt(boundaries[1]);
                    e = (-1 == e) ? elements.length - 1 : Math.min(e, elements.length - 1);
                    while (b <= e) {
                        new_elements.add(elements[b++]);
                    }
                }
                else if (op_name.equals(KEYWORD_SORT)) {
                    if (use_new_elements) {
                        elements = new_elements.toArray();
                        new_elements.clear();
                    }
                    Comparator<Annotation> cmptr = ArrayComparatorFinder.find(elements);
                    if (null != cmptr) {
                        Arrays.sort((Annotation[])elements, cmptr);
                    }
                }
            }
        }
        if (use_new_elements) {
            elements = new_elements.toArray();
            new_elements.clear();
        }
        return elements;
    }
/*
    private void getMatchedValues (Object[]             objs,
                                   String[]             features,
                                   int                  feat_ind,
                                   List<MatchedValue>   matchedValues,
                                   int                  array_level,
                                   List<Object>         orderedPath)
    throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
    {        

        String toks[] = features[feat_ind].split("\\|");

        for (int i = 0; i < objs.length; ++i) {
            if (null == objs[i]) {
                continue;
            }
            Object result = null;
            
            List<String> postProcOps = new ArrayList<String>();
            for (int tok_ind = 0; tok_ind < toks.length; ++tok_ind) {
                postProcOps.clear();
                String feat_name = parseFeatureName(toks[tok_ind], postProcOps);
                Method get_feat_method = findGetMethod(objs[i].getClass(), feat_name);
                if (null == get_feat_method) {
                    feat_name = "get" + PrimitiveFeatureTransformer.capitalize(feat_name); 
                    get_feat_method = findGetMethod(objs[i].getClass(), feat_name);
                    if (null == get_feat_method) {
                        // no such feature 
                        System.err.println("Class \"" + objs[i].getClass().getName() +
                                           "\": Feature:\"" + feat_name + "\" is not found");
                        break;
                    }
                }
                result = get_feat_method.invoke(objs[i], (Object[])null);
                if (null != result) {
                    break;
                }
            }
            // increment the feature index now
            int next_feat_ind = feat_ind + 1;
            
            if (null == result) {
                matchedValues.add(new MatchedValue(null, orderedPath));
                if (m_delimit_array_values && (i < (objs.length - 1))) {
                    matchedValues.add(new MatchedValue(new ArrayDelimiterObject(array_level), orderedPath));
                }
                continue; // through the rest of obj
            }
            if (result.getClass().isArray()) {
                Object[] elements = (Object[])result;
                if (!postProcOps.isEmpty()) {
                    elements = processPostProcOps(elements, postProcOps);
                }
                orderedPath.add(elements);
                for (int j = 0; j < elements.length; ++j) {
                    if (features.length == next_feat_ind) {
                        if ((null == elements[j]) || isTargetClassMatches(elements[j].getClass(), false)) {
                            matchedValues.add(new MatchedValue(elements[j], orderedPath));
                            if (m_delimit_array_values && (j < (elements.length - 1))) {
                                matchedValues.add(new MatchedValue(new ArrayDelimiterObject(array_level), orderedPath));
                            }
                        }
                    }
                }
                if (features.length == next_feat_ind) {
                    continue; // through the rest of obj
                }
                if (m_delimit_array_values) {
                    MatchedValue mv = matchedValues.get(0);
                    mv.m_matchedObject = new ArrayDelimiterObject(array_level + 1);
                }
                getMatchedValues(elements, features, next_feat_ind, matchedValues, array_level + 1, orderedPath);
            }
            else {
                if (features.length == next_feat_ind) {
                    if (isTargetClassMatches(result.getClass(), false)) {
                        matchedValues.add(new MatchedValue(result, orderedPath));
                        if (m_delimit_array_values && (i < (objs.length - 1))) {
                            matchedValues.add(new MatchedValue(new ArrayDelimiterObject(array_level), orderedPath));
                        }
                    }
                    continue; // through the rest of obj
                }
                orderedPath.add(result);
                
                getMatchedValues(new Object[] {result}, features, next_feat_ind, matchedValues, array_level, orderedPath);
            }
            if (m_delimit_array_values && (i < (objs.length - 1))) {
                matchedValues.add(new MatchedValue(new ArrayDelimiterObject(array_level), orderedPath));
            }
        }
    }
  */  
    
    public List<MatchedValue> getFeatureValues (MatchedValue mv)
    throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
    {
        // allows derived classes to be processed
        List<MatchedValue> result = new ArrayList<MatchedValue>();
        if (m_delimit_array_values) {
            // set max array level
            result.add(new MatchedValue(new ArrayDelimiterObject(0), new ArrayList<Object>()));
        }
        
        if (! m_root_class.isAssignableFrom(mv.m_matchedObject.getClass())) {
            return result;
        }
        else if (0 == m_target_path_tokens.length) {
            if (isTargetClassMatches(mv.m_matchedObject.getClass(), false)) {
                result.add(mv);
            }
            return result;
        }
        
        if ((1 == m_target_path_tokens.length) && m_target_path_tokens[0].contains("/")) {
            // if the path is an xpath (separator is '/') then it will 
            // be a single "token" as parsing only splits on ':'
            // WARNING: JXPath is not supported!!! just experimenting
            // the biggest issue so far is that elements of arrays are not separated
            // by the objects containing them,
            // so for the path:
            //      array_feature1/regular_feature/array_feature2/regular_feature2
            // all values for regular_feature2 are iterated by a single iterator
            // and it is not clear to which element of array_feature1 a particular 
            // regular_feature2 belongs
            JXPathContext ctx = JXPathContext.newContext(mv.m_matchedObject);
            ctx.setLenient(true);
            for (Iterator<?> it = ctx.iterate(m_target_path_tokens[0]); it.hasNext();) {
                // System.out.printf("%s=%s\n", m_target_path_tokens[0], it.next());
                if (result.size() > 1 && m_delimit_array_values) {
                    result.add(new MatchedValue(new ArrayDelimiterObject(0), new ArrayList<Object>()));                    
                }
                result.add(new MatchedValue(it.next(), new ArrayList<Object>()));
            }
        }
        else {
            getMatchedValues(new Object[] {mv.m_matchedObject}, mv.m_orderedPath, m_target_path_tokens, 0, result, new ArrayList<Object>(), -1);
        }
        return result;
    }
    
    
    private void getMatchedValues (Object[]             objs,
                                   List<Object>         rootOrderedPath,
                                   String[]             features,
                                   int                  feat_ind,
                                   List<MatchedValue>   matchedValues,
                                   List<Object>         matchedOrderedPath,
                                   int                  array_level)
    throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
    {        

        String operations[] = feat_ind < features.length ? features[feat_ind].split("\\|") : null;
        
        for (int i = 0; i < objs.length; ++i) {
            if (feat_ind == features.length) {
                if ((null == objs[i]) || isTargetClassMatches(objs[i].getClass(), false)) {
                    matchedValues.add(new MatchedValue(objs[i], matchedOrderedPath));
                }
                continue;
            }
            if (null == objs[i]) {
                continue;
            }
            matchedOrderedPath.add(objs[i]);
            List<Object> mop = (1 == objs.length) ? matchedOrderedPath : new ArrayList<Object>(matchedOrderedPath);
            getMatchedValues(objs[i], rootOrderedPath, features, feat_ind, matchedValues, mop, array_level, operations);
            if (m_delimit_array_values && (i < (objs.length - 1))) {
                matchedValues.add(new MatchedValue(new ArrayDelimiterObject(array_level), mop));
            }
        }
    }

    
    static private Pattern m_parentPattern = Pattern.compile("__p([\\d]+)");
    
    Object getOperationResult (Object       obj,
                               List<Object> rootOrderedPath,
                               String []    operations,
                               List<String> postProcOps)
    throws IllegalArgumentException,
           IllegalAccessException, 
           InvocationTargetException
    {
        Object result = null;
        for (int tok_ind = 0; tok_ind < operations.length; ++tok_ind) {
            postProcOps.clear();
            String feat_name = parseFeatureName(operations[tok_ind], postProcOps);
            Matcher m = m_parentPattern.matcher(feat_name);
            int parInd = Integer.MIN_VALUE;
            if (m.find()) {
                parInd = Integer.parseInt(m.group(1));
                if (m.find()) {
                    throw new IllegalArgumentException("malformed operation name");
                }
                // no check. an exception to be thrown in case of an error
                result = rootOrderedPath.get(parInd);
                break;
            }
            Method get_feat_method = findGetMethod(obj.getClass(), feat_name);
            if (null == get_feat_method) {
                feat_name = "get" + PrimitiveFeatureTransformer.capitalize(feat_name); 
                get_feat_method = findGetMethod(obj.getClass(), feat_name);
                if (null == get_feat_method) {
                    // no such feature 
                    System.err.println("Class \"" + obj.getClass().getName() +
                                       "\": Feature:\"" + feat_name + "\" is not found");
                    break;
                }
            }
            result = get_feat_method.invoke(obj, (Object[])null);
            if (null != result) {
                break;
            }
        }
        return result;
    }
    
    private void getMatchedValues (Object               obj,
                                   List<Object>         rootOrderedPath,
                                   String[]             features,
                                   int                  feat_ind,
                                   List<MatchedValue>   matchedValues,
                                   List<Object>         matchedOrderedPath,
                                   int                  array_level,
                                   String []            operations)
    throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
    {
        List<String> postProcOps = new ArrayList<String>();
        Object result = getOperationResult(obj, rootOrderedPath, operations, postProcOps);
        if (null == result) {
            matchedValues.add(new MatchedValue(null, matchedOrderedPath));
            return;
        }
        
        // increment the feature index now
        int next_feat_ind = feat_ind + 1;
        
        
        if (result.getClass().isArray()) {
            Object[] elements = (Object[])result;
            if (!postProcOps.isEmpty()) {
                elements = processPostProcOps(elements, postProcOps);
            }
            if (m_delimit_array_values) {
                MatchedValue mv = matchedValues.get(0);
                mv.m_matchedObject = new ArrayDelimiterObject(array_level + 1);
            }
            getMatchedValues(elements, rootOrderedPath, features, next_feat_ind, matchedValues, matchedOrderedPath, array_level + 1);
        }
        else {
            getMatchedValues(new Object[] {result}, rootOrderedPath, features, next_feat_ind, matchedValues, matchedOrderedPath, array_level);
        }
    }
}
