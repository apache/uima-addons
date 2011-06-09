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
package org.apache.uima.annotator.bsf;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;

/**
 * 
 * 
 */
public class BSFAnnotatorProcessException extends AnalysisEngineProcessException {


   /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
    * Creates a new exception with a the specified message.
    * 
    * @param aMessageKey
    *           an identifier that maps to the message for this exception. The
    *           message may contain placeholders for arguments as defined by the
    *           {@link java.text.MessageFormat MessageFormat} class.
    * @param aArguments
    *           The arguments to the message. <code>null</code> may be used if
    *           the message has no arguments.
    */
   public BSFAnnotatorProcessException(String aMessageKey, Object[] aArguments) {
      super(BSFAnnotator.MESSAGE_DIGEST, aMessageKey, aArguments);
   }

   /**
    * Creates a new exception with the specified cause and a message from the
    * {@link #STANDARD_MESSAGE_CATALOG}.
    * 
    * @param aMessageKey
    *           an identifier that maps to the message for this exception. The
    *           message may contain placeholders for arguments as defined by the
    *           {@link java.text.MessageFormat MessageFormat} class.
    * @param aArguments
    *           The arguments to the message. <code>null</code> may be used if
    *           the message has no arguments.
    * @param aCause
    *           the original exception that caused this exception to be thrown,
    *           if any
    */
   public BSFAnnotatorProcessException(String aMessageKey,
         Object[] aArguments, Throwable aCause) {
      super(BSFAnnotator.MESSAGE_DIGEST, aMessageKey, aArguments, aCause);
   }

   /**
    * Creates a new exception with the specified cause and a null message.
    * 
    * @param aCause
    *          the original exception that caused this exception to be thrown, if any
    */
   public BSFAnnotatorProcessException(Throwable aCause) {
     super(aCause);
   }

}
