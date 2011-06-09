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

public class FeatureObjectMatcher extends PartialObjectMatcher
{
    public final int m_windowsizeLeft;
    public final int m_windowsizeInside;
    public final int m_windowsizeRight;
    public final int m_windowsizeEnclosed;
    public final int m_windowFlags;
    public final boolean m_orientation;
    public final boolean m_distance;

    public FeatureObjectMatcher (String                             class_name,
                                 String                             full_path,
                                 Collection<GroupFeatureMatcher>    gfms,
                                 int                                windowsizeLeft,
                                 int                                windowsizeInside,
                                 int                                windowsizeRight,
                                 int                                windowsizeEnclosed,
                                 int                                windowFlags,
                                 boolean                            orientation,
                                 boolean                            distance)
    throws ClassNotFoundException
    {
        super(class_name, full_path, gfms);
        m_windowsizeLeft = windowsizeLeft;
        m_windowsizeInside = windowsizeInside;
        m_windowsizeRight = windowsizeRight;
        m_windowsizeEnclosed = windowsizeEnclosed;
        m_windowFlags = windowFlags;
        m_orientation = orientation;
        m_distance = distance;
    }

    public FeatureObjectMatcher (String     class_name,
                                 String     ful_path,
                                 int        windowsizeLeft,
                                 int        windowsizeInside,
                                 int        windowsizeRight,
                                 int        windowsizeEnclosed,
                                 int        windowFlags,
                                 boolean    orientation,
                                 boolean    distance)
    throws ClassNotFoundException
    {
        this(class_name,
             ful_path,
             null,
             windowsizeLeft,
             windowsizeInside,
             windowsizeRight,
             windowsizeEnclosed,
             windowFlags,
             orientation,
             distance);
    }
}
