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

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;



public abstract class SimpleFileBasedDictionary<T> extends FileBasedDictionary<T> implements DictionaryMatcher<String>
{
    private Map<String, T>  m_storage;
    public final String     m_cmtsep;
    public final String     m_fldsep;
    
    public SimpleFileBasedDictionary (String            name,
                                      String            path,
                                      Map<String, T>    storage,
                                      String            comment_separator,
                                      String            filed_separator)
    {
        super (name, path);
        m_cmtsep = comment_separator;
        m_fldsep = filed_separator;
        m_storage = storage;
        if (null == m_storage) {
            m_storage = new HashMap<String, T>();
        }
    }

    public void load () throws IOException
    {
        BufferedReader bufReader = new BufferedReader (new FileReader (m_path));
        String line = null;
        int cnt = 0;
        while ((line = bufReader.readLine ()) != null) {
            ++cnt;
            line = line.trim();
            if ((null != m_cmtsep) && line.startsWith(m_cmtsep)) {
                continue;
            }
            if (0 == line.length()) {
                continue;
            }
            String[] columns = (null == m_fldsep) ? new String[]{line} : line.split(m_fldsep);
            if (0 == columns.length) {
                continue;
            }
            addLine(columns, cnt);
        }
        bufReader.close();
    }
    
    public void save () throws IOException
    {
        PrintStream ps = new PrintStream(new FileOutputStream (path(), false));
        for (Iterator<String> it = m_storage.keySet().iterator(); it.hasNext();) {
            ps.println(make_line(it.next()));
        }
        ps.close();
    }

    protected void addEntry (String key, T value, int linenum)
    {
        m_storage.put(key, value);
    }
    
    public Map<String, T> storage ()
    {
        return m_storage;
    }
    
    public T getEntry (String key)
    {
        return m_storage.get(key);
    }
    
    public int size ()
    {
        return m_storage.size();
    }
    
    
    public boolean matches (String key) 
    {
        return m_storage.containsKey(key);
    }
    
    private String make_line(String key)
    {
        if (null == m_fldsep) {
            return key.toString();
        }
        else {
            return key + m_fldsep + getEntry(key);
        }
    }
}
