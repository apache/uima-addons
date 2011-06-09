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

package org.apache.uima.simpleserver.output;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/*
 * This class contains static methods responsible for creation of inline-xml from the given Result
 * object.
 */
public class InlineXMLGenerator {

  /*
   * main method of this class - decides, which annotations should be taken, and produces the string
   * with inline-xml.
   */
  public static String getInlineXML(Result result) {
    // a list of all positions where some tags begin
    TreeSet<Integer> beginPositions = new TreeSet<Integer>();

    // a list of all positions where some tags end
    TreeSet<Integer> endPositions = new TreeSet<Integer>();

    // a list of all positions where empty tags begin and end
    TreeSet<Integer> emptyPositions = new TreeSet<Integer>();

    // this will be used to merge the three lists above
    TreeSet<Integer> allPositions = new TreeSet<Integer>();

    // maps a position number to the collection of tags which
    // begin at this position.
    HashMap<Integer, List<ResultEntry>> beginTags = new HashMap<Integer, List<ResultEntry>>(result
        .getResultEntries().size());

    // maps a position number to the collection of tags which
    // end at this position.
    HashMap<Integer, List<ResultEntry>> endTags = new HashMap<Integer, List<ResultEntry>>(result
        .getResultEntries().size());

    // maps a position number to the collection of empty tags which
    // begin and end at this position.
    HashMap<Integer, List<ResultEntry>> emptyTags = new HashMap<Integer, List<ResultEntry>>();

    // first of all, we decide which annotions to take
    // this loop iterates over all available annotations
    loop: for (ResultEntry entry : result.getResultEntries()) {
      Integer begin;
      Integer end;
      try {
        begin = new Integer(entry.getBegin());
        end = new Integer(entry.getEnd());
      } catch (NumberFormatException e) {
        // we don't take annotations without features "begin" and "end"
        continue loop;
      }

      // a zero-length-annotation can not cause any conflicts and should
      // always be taken
      if (begin.equals(end)) {
        emptyPositions.add(begin);
        List<ResultEntry> emptyTagList = emptyTags.get(end);
        if (emptyTagList == null) {
          emptyTagList = new LinkedList<ResultEntry>();
          emptyTags.put(end, emptyTagList);
        }
        // and add the entry to both tag lists
        emptyTagList.add(entry);
        continue loop;
      }

      // if an anotation is not zero-length, it can cause conflicts,
      // so we should decide, whether we take it or not

      // now let's analyse the candidates for conflicts
      Set<Integer> conflictPoints;
      // 1. which begin in the body of current annotation
      conflictPoints = beginPositions.subSet(begin + 1, end);
      for (int i : conflictPoints) {
        for (ResultEntry e : beginTags.get(i)) {
          if (isConflict(e, begin, end)) {
            continue loop;
          }
        }
      }
      // 2. which end in the body of current annotation
      conflictPoints = endPositions.subSet(begin + 1, end);
      for (int i : conflictPoints) {
        for (ResultEntry e : endTags.get(i)) {
          if (isConflict(e, begin, end)) {
            continue loop;
          }
        }
      }

      // now, if no conflicts found, we can
      // add the entry to our results
      beginPositions.add(begin);
      endPositions.add(end);

      // now create the tag lists, if necessary
      // and add the tag to the lists
      List<ResultEntry> beginTagList = beginTags.get(begin);
      if (beginTagList == null) {
        beginTagList = new LinkedList<ResultEntry>();
        beginTags.put(begin, beginTagList);
      }
      List<ResultEntry> endTagList = endTags.get(end);
      if (endTagList == null) {
        endTagList = new LinkedList<ResultEntry>();
        endTags.put(end, endTagList);
      }
      // and add the entry to both tag lists
      beginTagList.add(entry);
      endTagList.add(entry);
    }
    // end of loop over all entries (annotations)

    // now we have all the entries in our collections and we can
    // begin with the output

    // first, we make a mixed set of all used positions, both of end tags
    // and
    // begin tags
    allPositions.addAll(beginPositions);
    allPositions.addAll(endPositions);
    allPositions.addAll(emptyPositions);
    allPositions.add(0);
    StringBuffer sourceText = new StringBuffer(result.getText());
    allPositions.add(sourceText.length());

    // now, construct the output
    StringBuffer resultSB = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<result>");

    int begin = 0;
    for (int end : allPositions) {
      // print closing tags
      List<ResultEntry> endingTags = endTags.get(begin);
      // BEGIN!!! it's not a typing error!
      if (endingTags != null) {
        printEndTags(endingTags, resultSB);
      }

      // print empty tags
      List<ResultEntry> empTags = emptyTags.get(begin);
      if (empTags != null) {
        printEmptyTags(empTags, resultSB);
      }

      // print opening tags
      List<ResultEntry> beginningTags = beginTags.get(begin);
      if (beginningTags != null) {
        printBeginTags(beginningTags, resultSB);
      }
      // print content
      resultSB.append(xmlEscape(sourceText.substring(begin, end)));

      // proceed to next step
      begin = end;
    }
    // printout tags that may be at the end
    List<ResultEntry> endingTags = endTags.get(begin); // BEGIN!!! it's
    if (endingTags != null) {
      printEndTags(endingTags, resultSB);
    }
    // print empty tags
    List<ResultEntry> empTags = emptyTags.get(begin);
    if (empTags != null) {
      printEmptyTags(empTags, resultSB);
    }
    List<ResultEntry> beginningTags = beginTags.get(begin);
    if (beginningTags != null) {
      printBeginTags(beginningTags, resultSB);
    }
    resultSB.append("\n</result>");
    return resultSB.toString();
  }

  /*
   * sorts the beginning tags by length descending and adds them to the given string buffer
   */
  private static void printBeginTags(List<ResultEntry> list, StringBuffer sb) {
    while (!list.isEmpty()) {
      ResultEntry selectedEntry = null;
      int maxlen = -1;
      for (ResultEntry e : list) {
        // ONLY > , will not work properly with >= !
        if (len(e) > maxlen) {
          maxlen = len(e);
          selectedEntry = e;
        }
      }
      sb.append(constructBeginTag(selectedEntry));
      list.remove(selectedEntry);
    }
  }

  /*
   * sorts the ending tags by length ascending and adds them to the given string buffer
   */
  private static void printEndTags(List<ResultEntry> list, StringBuffer sb) {
    while (!list.isEmpty()) {
      ResultEntry selectedEntry = null;
      int minlen = Integer.MAX_VALUE;
      for (ResultEntry e : list) {
        // ONLY <= , will not work properly with < !
        if (len(e) <= minlen) {
          minlen = len(e);
          selectedEntry = e;
        }
      }
      sb.append(constructEndTag(selectedEntry));
      list.remove(selectedEntry);
    }
  }

  /*
   * adds the given empty tags to the given string buffer
   */
  private static void printEmptyTags(List<ResultEntry> list, StringBuffer sb) {
    for (ResultEntry entry : list) {
      sb.append(constructEmptyTag(entry));
    }
    list.clear();
  }

  /*
   * gets the length of an annotation
   */
  private static int len(ResultEntry entry) {
    return entry.getEnd() - entry.getBegin() + 1;
  }

  /*
   * constructs an opening XML tag specified by the given ResultEntry
   */
  private static String constructBeginTag(ResultEntry entry) {
    StringBuffer result = new StringBuffer("");
    result.append("<");
    result.append(entry.getEntryName());
    for (String attribute : entry.getAttributeNames()) {
      // TODO covered text - DONE
      // exclude the next "if"
      // if (!attribute.equals("coveredText")) {

      result.append(" ");
      result.append(attribute);
      result.append("=\"");
      result.append(xmlEscape(entry.getAttriuteValue(attribute)));
      result.append("\"");
      // }
    }
    result.append(">");
    return result.toString();
  }

  /*
   * constructs a closing XML tag specified by the given ResultEntry
   */
  private static String constructEndTag(ResultEntry entry) {
    return "</" + entry.getEntryName() + ">";
  }

  /*
   * constructs an empty XML tag specified by the given ResultEntry
   */
  private static String constructEmptyTag(ResultEntry entry) {
    StringBuffer result = new StringBuffer("");
    result.append("<");
    result.append(entry.getEntryName());
    for (String attribute : entry.getAttributeNames()) {
      if (!attribute.equals("coveredText")) {
        result.append(" ");
        result.append(attribute);
        result.append("=\"");
        result.append(xmlEscape(entry.getAttriuteValue(attribute)));
        result.append("\"");
      }
    }
    result.append("/>");
    return result.toString();
  }

  /*
   * used to escape XML-specific characters
   */
  private static StringBuffer xmlEscape(String s) {
    StringBuffer sb = new StringBuffer();
    normalize(s, sb, false);
    return sb;
  }

  /*
   * escapes the XML characters in the given text and adds the result to the given string buffer
   */
  public static void normalize(String aStr, StringBuffer aResultBuf, boolean aNewlinesToSpaces) {
    if (aStr != null) {
      int len = aStr.length();
      for (int i = 0; i < len; i++) {
        char c = aStr.charAt(i);
        switch (c) {
        case '<':
          aResultBuf.append("&lt;");
          break;
        case '>':
          aResultBuf.append("&gt;");
          break;
        case '&':
          aResultBuf.append("&amp;");
          break;
        case '"':
          aResultBuf.append("&quot;");
          break;
        case '\'':
          aResultBuf.append("&apos;");
          break;
        case '\n':
          aResultBuf.append(aNewlinesToSpaces ? " " : "\n");
          break;
        case '\r':
          aResultBuf.append(aNewlinesToSpaces ? " " : "\r");
          break;
        default:
          aResultBuf.append(c);
        }
      }
    }
  }

  /*
   * determines whether the given annotation has a conflict with another annotation with specified
   * begin and end positions.
   */
  private static boolean isConflict(ResultEntry entry, int begin, int end) {
    return isConflict(entry.getBegin(), entry.getEnd(), begin, end);
  }

  /*
   * determines whether the anotations with begin positions b1 and b2 and end positions e1 and e2
   * cross each other, producing a conflict in the xml output
   */
  private static boolean isConflict(int b1, int e1, int b2, int e2) {
    if (b1 == b2) {
      return false;
    }
    if (e1 == e2) {
      return false;
    }
    if (b1 > b2) {
      return isConflict(b2, e2, b1, e1);
    }

    if ((e1 > b2) && (e1 < e2)) {
      return true;
    }
    return false;
  }

}
