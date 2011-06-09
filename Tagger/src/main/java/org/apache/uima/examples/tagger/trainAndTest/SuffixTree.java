/*
 *Licensed to the Apache Software Foundation (ASF) under one
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
 * 
 */

package org.apache.uima.examples.tagger.trainAndTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Java implementation of the Ukkonen's suffix tree inspired by Mark Nelson's tutorial:
 * http://marknelson.us/1996/08/01/suffix-trees/
 */
public class SuffixTree {

  public String text = "";

  public List<Node> nodes = new ArrayList<Node>();

  public Map<EDGE_KEY, Edge> edges = new HashMap<EDGE_KEY, Edge>(); // Map to store the starting node of the edges and their corresponding

  // first characters

  char[] chars;

  public SuffixTree() {
    this("");
  }

  Suffix active_point = new Suffix(0, 0, -1); // initial active point is the first non-leaf suffix

  // in the tree

  public SuffixTree(String text) {
    // int token_begin = words.length();

    nodes.removeAll(nodes);

    this.text = text;
    chars = text.toCharArray();

    for (int i = 0; i < chars.length; i++) {
      add_prefix(i, active_point); // 
    }
  }

  // 
  public void insert_edge(Edge edge) {
    EDGE_KEY keys = new EDGE_KEY(edge.start_node, chars[edge.first_char_index]);
  //  List keys = new ArrayList();
  //  keys.add(edge.start_node);
  //  keys.add(chars[edge.first_char_index]);
    edges.put(keys, edge);
  }

  /**
   * 
   * Internal Class SUFFIX
   */

  class Suffix {

    int origin_node;
    int first_char_index;

    int last_char_index;

    public Suffix(int node, int begin, int end) {
      this.origin_node = node;
      this.first_char_index = begin;
      this.last_char_index = end;
    }

    boolean isExplicit() {
      return first_char_index > last_char_index;
    }

    boolean isImplicit() {
      return last_char_index >= first_char_index;
    }

    // "The canonical representation of the suffix simply requires that the origin_node
    // in the Suffix object be the closest parent to the end point of the string.
    // This means that the suffix string represented by the pair (0, "ABABABC"),
    // would be canonized by moving first to (1, "ABABC"), then (4, "ABC"), and finally (8,"").
    // When a suffix string ends on an explicit node, the canonical representation will use an empty
    // string
    // to define the remaining characters in the string.
    //
    // An empty string is defined by setting first_char_index to be greater than last_char_index.
    // When this is the case, we know that the suffix ends on an explicit node.
    // If first_char_index is less than or equal to last_char_index,
    // it means that the suffix string ends on an implicit node."
    // (Mark Nelson. Fast String Searching With Suffix Trees.
    // http://marknelson.us/1996/08/01/suffix-trees/)

    void canonize() {
      if (!this.isExplicit()) {
        EDGE_KEY keys = new EDGE_KEY(this.origin_node, chars[this.first_char_index]); 
      //  List keys = new ArrayList();
      //  keys.add(this.origin_node);
      //  keys.add(chars[this.first_char_index]);
        Edge edge = (Edge) edges.get(keys);

        int edge_span = edge.last_char_index - edge.first_char_index + 1;

        int suffix_span = this.last_char_index - this.first_char_index + 1;

        if (edge_span <= suffix_span) {
          this.first_char_index = this.first_char_index + edge_span;
          this.origin_node = edge.end_node;
          this.canonize();

        }
      }
    }
  }

  /**
   * Internal Class EDGE_KEY
   */
  
  public class EDGE_KEY {
    private int suffix_begin;
    private char suffix;

    public EDGE_KEY(int i, char s) {
     suffix_begin = i;
     suffix = s;
    }
    @Override
    public boolean equals(Object o) {
      if (o instanceof EDGE_KEY) {
        EDGE_KEY ek = (EDGE_KEY) o;
        if ((this.suffix_begin == ek.suffix_begin) && (this.suffix == ek.suffix)) {
          return true;
        }
      }
      return false;
    }

    public int hashCode(){
      int hash = 0;
      hash <<= 1;
      if ( hash < 0 )
      {
        hash |= 1;
      }
      hash ^= suffix_begin;
      hash ^= suffix;
        
      return hash;
    }
    
  }
  
  /**
   * Internal Class EDGE
   */

  public class Edge {
    public int first_char_index;

    public int last_char_index;

    int start_node;

    public int end_node;

    public Edge(int parent_node, int end_node, int first_char_index, int last_char_index) {
      this.first_char_index = first_char_index;
      this.last_char_index = last_char_index;
      this.start_node = parent_node;
      this.end_node = end_node;
    }

    // When a suffix ends on an implicit node, adding a new character
    // means I have to split an existing edge.
    // The existing edge loses its parent, as well as some of its leading
    // characters. The newly created edge descends from the original
    // parent, and now has the existing edge as a child.
    //
    // Since the existing edge is getting a new parent and starting
    // character, it is re-inserted.
    //
    // The number of characters stolen from the original node and given
    // to the new node is equal to the number of characters in the suffix
    // argument, which is last - first + 1;

    // Comment to the suffix pointers: The suffix pointers are built at the same time the update to
    // the tree is taking place.
    // As I move from the active point to the end point, I keep track of the parent node of each of
    // the new leaves I create.
    // Each time I create a new edge, I also create a suffix pointer
    // from the parent node of the last leaf edge I created to the current parent edge.
    // (Mark Nelson. Fast String Searching With Suffix Trees.
    // http://marknelson.us/1996/08/01/suffix-trees/)

    public int split_edge(Suffix suffix) {

      Node next = new Node();
      nodes.add(next);

      int new_node_index = nodes.size();

      int suf_span = suffix.last_char_index - suffix.first_char_index + 1;
      nodes.get(new_node_index - 1).suffix_node = suffix.origin_node;
      Edge new_edge = new Edge(new_node_index, this.end_node, this.first_char_index + suf_span,
              this.last_char_index);
      insert_edge(new_edge);

      // SuffixTree.remove_edge(this.start_node, this.first_char_index);
      // shorten existing edge
      this.last_char_index = this.first_char_index + suf_span - 1;
      // this.end_node = new_node_index;

      insert_edge(new Edge(this.start_node, new_node_index, this.first_char_index,
              this.last_char_index));

      return new_node_index; // return the new origin node index of the last edge
    }

  }

  /**
   * Internal Class NODE
   */

  // The only information contained in a node is the
  // suffix link. Each suffix in the tree that ends
  // at a particular node can find the next smaller suffix
  // by following the suffix_node link to a new node. Nodes
  // are stored in a simple array.
  public class Node {
    public int suffix_node;

    // static int count=0;

    // Nodes with suffix link of (-1) are leaf nodes
    public Node() {
      suffix_node = -1;
    }

  }

  public void add_prefix(int last_char, Suffix active_point) {
    int last_parent_node = -1;
    int parent_node = 0;

    for (;;) {
      parent_node = active_point.origin_node;

      Edge edge;
      if (active_point.isExplicit()) {
        EDGE_KEY keys = new EDGE_KEY(active_point.origin_node, chars[last_char]); 
        
      //  List keys = new ArrayList();
     //   keys.add(active_point.origin_node);
     //   keys.add(chars[last_char]);
        if (edges.containsKey(keys)) {
          edge = (Edge) edges.get(keys);
          break;
        }
      } else if (active_point.isImplicit()) { // if suffix is implicit, i.e. it does not end in a
        // leaf node $
        
          EDGE_KEY keys2 = new EDGE_KEY(active_point.origin_node, chars[active_point.first_char_index]); 
        
      //  List keys2 = new ArrayList();
      //  keys2.add(active_point.origin_node);
      //  keys2.add(chars[active_point.first_char_index]);
          edge = (Edge) edges.get(keys2);

          int span = active_point.last_char_index - active_point.first_char_index;
        // if the given prefix is already in the tree, do nothing
          if (chars[edge.first_char_index + span + 1] == chars[last_char]) {
            break;
        } else {
          parent_node = edge.split_edge(active_point);
        }

      }

      Node next = new Node();
      nodes.add(next);
      int new_node_index = nodes.size();
      edge = new Edge(parent_node, new_node_index, last_char, chars.length - 1);
      insert_edge(edge);

      // When we create a new node, it also means we need to create a suffix link to the new node
      // from
      // the last node we visited.
      if (last_parent_node > 0) {
        nodes.get(last_parent_node - 1).suffix_node = parent_node;
      }
      last_parent_node = parent_node;

      // here we move to the next smaller suffix
      if (active_point.origin_node == 0) {
        active_point.first_char_index += 1;
      } else {

        active_point.origin_node = nodes.get(active_point.origin_node - 1).suffix_node;

      }
      active_point.canonize();
    }
    if (last_parent_node > 0) {
      nodes.get(last_parent_node - 1).suffix_node = parent_node;
    }
    active_point.last_char_index += 1;
    active_point.canonize();
  }

}
