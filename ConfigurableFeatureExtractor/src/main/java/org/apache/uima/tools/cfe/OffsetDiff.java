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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OffsetDiff
{
    public String m_source_id;
    public String m_target_id;
    
    public OffsetDiff(String source_id, String target_id)
    {
        m_source_id = source_id;
        m_target_id = target_id;
    }

    static class Span
    {
        public String[] m_toks;
        public int m_begin;
        public int m_end;
        public int m_printed;
        
        public Span(String line)
        {
            final String CONSTANT_EOL_NULL = "CONSTANT_EOL_NULL";
            
            if (line.endsWith("|")) {
                line += CONSTANT_EOL_NULL;
            }
            m_toks = line.split("\\|");
            if (CONSTANT_EOL_NULL.equals(m_toks[m_toks.length - 1])) {
                m_toks[m_toks.length - 1] = "";                
            }
            m_begin = Integer.parseInt(m_toks[0]);
            m_end = Integer.parseInt(m_toks[1]);
        }
        
        int getBegin()
        {
            return m_begin;
        }

        int getEnd()
        {
            return m_end;
        }
        
        public static final int COMPARE_NONE = -1;
        public static final int COMPARE_EQUAL = 0;
        public static final int COMPARE_CONTAINS = 1;
        public static final int COMPARE_BELONGS = 2;
        public static final int COMPARE_AHEAD = 3;
        public static final int COMPARE_BEHIND = 4;
        public static final int COMPARE_FARAHEAD = 5;
        public static final int COMPARE_FARBEHIND = 6;
        
        public int printed()
        {
            return m_printed;
        }
        
        public String printImage()
        {
            ++m_printed;
            StringBuilder image = new StringBuilder(m_toks[0] + "|" + m_toks[1]);
            for (int i = 2; i < m_toks.length; ++i) {
                image.append("|");
                image.append(m_toks[i]);
            }
            return image.toString();
        }
        
        String printNullImage()
        {
            StringBuilder image = new StringBuilder("|"); // for begin/end
            for (int i = 2; i < m_toks.length; ++i) {
                image.append("|");
            }
            return image.toString();
        }

        public int compare (Span other)
        {
            if ((getBegin() == other.getBegin()) &&
                (getEnd() == other.getEnd())) {
                return COMPARE_EQUAL;
            }
            if ((getBegin() <= other.getBegin()) &&
                (getEnd() >= other.getEnd())) {
                return COMPARE_CONTAINS;
            }
            if ((getBegin() >= other.getBegin()) &&
                (getEnd() <= other.getEnd())) {
                return COMPARE_BELONGS;
            }
            if ((getBegin() < other.getBegin()) && (getEnd() > other.getBegin()) &&
                (getEnd() < other.getEnd())) {
                return COMPARE_BEHIND;
            }
            if ((getBegin() > other.getBegin()) && (other.getEnd() > getBegin()) &&
                (getEnd() > other.getEnd())) {
                return COMPARE_AHEAD;
            }
            if ((getBegin() > other.getBegin()) &&
                (getBegin() >= other.getEnd())) {
                return COMPARE_FARAHEAD;
            }
            if ((getBegin() < other.getBegin()) &&
                (getEnd() <= other.getBegin())) {
                return COMPARE_FARBEHIND;
            }
            return COMPARE_NONE;
        }
    }
    
    private static int nextSpanInd(Collection<Span> spans, int curr)
    {
        if (curr < spans.size()) {
            return curr + 1;  
        }
        return curr;
    }

    private void print(Span source, Span target)
    {
        String m = null;
        if (null == source) {
            m = target.printNullImage();  
        }
        else {
            m = source.printImage();
        }
        m += "|";
        
        if (null == target) {
            m += source.printNullImage();  
        }
        else {
            m += target.printImage();  
        }
        m += "|" + m_source_id + "|" + m_target_id;
        System.out.println(m);  
    }

    public void process (List<Span> source, List<Span> target)
    {
        findEqual(source, target);
        
        int trg_ind = 0;
        int src_ind = 0;

        Span sspan = null;
        Span tspan = null;

        List<Span> smatched = new ArrayList<Span>();
        List<Span> tmatched = new ArrayList<Span>();
        
        while ((src_ind < source.size()) || (trg_ind < target.size())) {
            int curr_state = Span.COMPARE_NONE;

            if (src_ind < source.size()) {
                sspan = (Span)source.get(src_ind);
            }
            if (trg_ind < target.size()) {
                tspan = (Span)target.get(trg_ind);
            }
            
            if ((src_ind < source.size()) && (trg_ind < target.size())) {
                curr_state = sspan.compare(tspan); 
            } 
            else if (trg_ind >= target.size()) {
                curr_state = Span.COMPARE_FARBEHIND;
            }
            else if (src_ind >= source.size()) {
                curr_state = Span.COMPARE_FARAHEAD;
            }
            switch (curr_state) {
            case Span.COMPARE_CONTAINS:
                print(sspan, tspan);
                trg_ind = nextSpanInd(target, trg_ind);
                src_ind = nextSpanInd(source, src_ind);
                tmatched.add(tspan);
                smatched.add(sspan);
                break;
            case Span.COMPARE_BELONGS:
                print(sspan, tspan);
                trg_ind = nextSpanInd(target, trg_ind);
                src_ind = nextSpanInd(source, src_ind);
                tmatched.add(tspan);
                smatched.add(sspan);
                break;
                
            case Span.COMPARE_AHEAD:
                print(sspan, tspan);
                src_ind = nextSpanInd(source, src_ind);
                trg_ind = nextSpanInd(target, trg_ind);
                tmatched.add(tspan);
                smatched.add(sspan);
                break;

            case Span.COMPARE_BEHIND:
                print(sspan, tspan);
                src_ind = nextSpanInd(source, src_ind);
                trg_ind = nextSpanInd(target, trg_ind);
                tmatched.add(tspan);
                smatched.add(sspan);
                break;

            case Span.COMPARE_FARAHEAD:
                print(null, tspan);
                trg_ind = nextSpanInd(target, trg_ind);
                tmatched.add(tspan);
                break;
                
            case Span.COMPARE_FARBEHIND:
                print(sspan, null);
                src_ind = nextSpanInd(source, src_ind); 
                smatched.add(sspan);
                break;
            }
        }
        
        source.removeAll(smatched);
        target.removeAll(tmatched);
        if (!source.isEmpty() || !target.isEmpty()) {
            System.err.println("ERROR: comparison incomplete. source:" + source.size() + ", target:" + target.size());
        }
    }

    

    void findEqual (List<Span> source, List<Span> target)
    {
        int trg_ind = 0;

        Span sspan = null;
        Span tspan = null;

        List<Span> smatched = new ArrayList<Span>();
        List<Span> tmatched = new ArrayList<Span>();
        
        for (int i = 0; i < source.size(); ++i) {
            sspan = (Span)source.get(i);
            for (int j = trg_ind; j < target.size(); ++j) {
                tspan = (Span)target.get(j);
                int curr_state = sspan.compare(tspan);
                if  (Span.COMPARE_EQUAL == curr_state) {
                    print(sspan, tspan);
                    trg_ind = j + 1;
                    smatched.add(sspan);
                    tmatched.add(tspan);
                    break;
                }
                else if  (Span.COMPARE_FARBEHIND == curr_state) {
                    break;
                }
            }
        }
        source.removeAll(smatched);
        target.removeAll(tmatched);
    }
    
    static private List<Span> readSpannedFile (String filename) throws FileNotFoundException
    {
        String line = null;
        List<Span> result = new ArrayList<Span>();
        
        File fs = new File(filename);
        if (fs.exists()) {
            int cnt = 0;
            try {
                BufferedReader in = new BufferedReader(new FileReader(fs));
                while (null != (line = in.readLine())) {
                    cnt++;
                    if (line.startsWith("#")) {
                        continue;
                    }
                    Span s = new Span(line);
                    if (null != s) {
                        result.add(s);
                    }        
                }
                in.close();
            }
            catch (IOException e) {
                System.err.println(filename + ": failed to read " + cnt +" line"); 
            }
        }
        return result;
    }

    public static void main (String[] args) throws IOException
    {
        if (2 != args.length) {
            System.err.println("Usage: com.ibm.medtas.common source_file target_file");
            return;
        }
        
        OffsetDiff od = new OffsetDiff(args[0], args[1]);
        List<Span> source = readSpannedFile(args[0]);
        List<Span> target = readSpannedFile(args[1]);
        od.process(source, target);
    }
}
