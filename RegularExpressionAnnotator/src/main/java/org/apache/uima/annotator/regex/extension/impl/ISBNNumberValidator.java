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
package org.apache.uima.annotator.regex.extension.impl;

import org.apache.uima.annotator.regex.extension.Validation;

/**
 * ISBN number validator
 * 
 */
public class ISBNNumberValidator implements Validation {

   /*
    * (non-Javadoc)
    * 
    * @see org.apache.uima.annotator.regex.extension.Validation#validate(java.lang.String,
    *      java.lang.String)
    */
   public boolean validate(String coveredText, String ruleID) throws Exception {

      // cleanup '-' signs from the covered text to check the isbn number
      coveredText = coveredText.replaceAll("-", "");

      // get character array for ISBN number digits
      char[] isbnCharArray = coveredText.toCharArray();

      // check if we have a ISBN-10 or ISBN-13
      if (coveredText.length() == 10) {
         // covert ISBN number digits to integer array
         int[] isbnDigits = new int[9];
         for (int i = 0; i < 9; i++) {
            isbnDigits[i] = Character.digit(isbnCharArray[i], 10);
         }

         // calculate ISBN-10 check digit
         int checkDigit = 11 - (10 * isbnDigits[0] + 9 * isbnDigits[1] + 8
               * isbnDigits[2] + 7 * isbnDigits[3] + 6 * isbnDigits[4] + 5
               * isbnDigits[5] + 4 * isbnDigits[6] + 3 * isbnDigits[7] + 2 * isbnDigits[8]) % 11;

         // check if ISBN number if it is a real ISBN number
         if (checkDigit == 10) {
            if (isbnCharArray[9] == 'X' || isbnCharArray[9] == 'x') {
               // real ISBN number
               return true;
            }
         } else if (Character.digit(isbnCharArray[9], 10) == checkDigit) {
            // real ISBN number
            return true;
         } else if (checkDigit == 11) {
            // normally this is an invalid ISBN number but they are still
            // available so we decided they are valid
            return true;
         }
      } else if (coveredText.length() == 13) {
         // covert ISBN number digits to integer array
         int[] isbnDigits = new int[12];
         for (int i = 0; i < 12; i++) {
            isbnDigits[i] = Character.digit(isbnCharArray[i], 10);
         }

         // calculate ISBN-13 check digit
         int checkDigit = 10 - (isbnDigits[0] + 3 * isbnDigits[1]
               + isbnDigits[2] + 3 * isbnDigits[3] + isbnDigits[4] + 3
               * isbnDigits[5] + isbnDigits[6] + 3 * isbnDigits[7]
               + isbnDigits[8] + 3 * isbnDigits[9] + isbnDigits[10] + 3 * isbnDigits[11]) % 10;

         // check if ISBN number if it is a real ISBN number
         if (checkDigit == 10) {
            if (Character.digit(isbnCharArray[12], 10) == 0) {
               // real ISBN number
               return true;
            }
         } else if (Character.digit(isbnCharArray[12], 10) == checkDigit) {
            // real ISBN number
            return true;
         }
      }

      return false;

   }

}
