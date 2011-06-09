/**
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.apache.uima.alchemy.mapper.processor;

import org.apache.uima.UIMAFramework;
import org.apache.uima.alchemy.digester.domain.EntitiesResults;
import org.apache.uima.alchemy.digester.domain.Entity;
import org.apache.uima.alchemy.digester.domain.Results;
import org.apache.uima.alchemy.ts.entity.AlchemyAnnotation;
import org.apache.uima.alchemy.ts.entity.BaseEntity;
import org.apache.uima.alchemy.ts.sentiment.SentimentFS;
import org.apache.uima.cas.Type;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.NonEmptyFSList;
import org.apache.uima.jcas.cas.StringArray;
import org.apache.uima.util.Level;

public class RankedEntitiesProcessor implements AlchemyOutputProcessor {

  private static final String ENTITY_PACKAGE_NAME = "org.apache.uima.alchemy.ts.entity.";

  public void process(JCas cas, Results results) throws Exception {
    for (Entity entity : ((EntitiesResults) results).getEntities().getEntities()) {

      // get feature structure for the entity
      BaseEntity fs = getFeatureStructure(entity.getType(), cas);

      if (fs != null) {

        Type type = fs.getType();

        /* set each FS feature value */
        fs.setFeatureValueFromString(type.getFeatureByBaseName("count"), entity.getCount()); // count
        fs.setFeatureValueFromString(type.getFeatureByBaseName("text"), entity.getText()); // text
        fs.setFeatureValueFromString(type.getFeatureByBaseName("relevance"), entity.getRelevance()); // relevance
        if (entity.getDisambiguated() != null) {
          fs.setFeatureValueFromString(type.getFeatureByBaseName("disambiguation"), entity
                  .getDisambiguated().getName()); // disambiguation name
          fs.setFeatureValueFromString(type.getFeatureByBaseName("dbpedia"), entity
                  .getDisambiguated().getDbpedia()); // dbpedia
          fs.setFeatureValueFromString(type.getFeatureByBaseName("website"), entity
                  .getDisambiguated().getWebsite()); // website
          fs.setFeatureValueFromString(type.getFeatureByBaseName("subType"), entity
                  .getDisambiguated().getSubType()); // subtype
          fs.setFeatureValueFromString(type.getFeatureByBaseName("geo"), entity.getDisambiguated()
                  .getGeo()); // geo
          fs.setFeatureValueFromString(type.getFeatureByBaseName("opencyc"), entity
                  .getDisambiguated().getOpencyc()); // opencyc
          fs.setFeatureValueFromString(type.getFeatureByBaseName("yago"), entity.getDisambiguated()
                  .getYago()); // yago
          fs.setFeatureValueFromString(type.getFeatureByBaseName("umbel"), entity
                  .getDisambiguated().getUmbel()); // umbel
          fs.setFeatureValueFromString(type.getFeatureByBaseName("freebase"), entity
                  .getDisambiguated().getFreebase()); // freebase
          fs.setFeatureValueFromString(type.getFeatureByBaseName("ciaFactbook"), entity
                  .getDisambiguated().getCiaFactbook()); // ciaFactbook
          fs.setFeatureValueFromString(type.getFeatureByBaseName("census"), entity
                  .getDisambiguated().getCensus()); // census
          fs.setFeatureValueFromString(type.getFeatureByBaseName("geonames"), entity
                  .getDisambiguated().getGeonames()); // geonames
          fs.setFeatureValueFromString(type.getFeatureByBaseName("musicBrainz"), entity
                  .getDisambiguated().getMusicBrainz()); // musicBrainz
        }
        if (entity.getQuotations() != null && entity.getQuotations().getQuotations() != null
                && entity.getQuotations().getQuotations().size() > 0) {
          StringArray quotationsFeatureStructure = new StringArray(cas, entity.getQuotations()
                  .getQuotations().size());
          int i = 0;
          for (String quotation : entity.getQuotations().getQuotations()) {
            quotationsFeatureStructure.set(i, quotation);
            i++;
          }
          fs.setFeatureValue(type.getFeatureByBaseName("quotations"), quotationsFeatureStructure);
        }
        if (entity.getSentiment() != null) {
          SentimentFS sentimentFS = new SentimentFS(cas);
          sentimentFS.setScore(entity.getSentiment().getScore());
          sentimentFS.setSentimentType(entity.getSentiment().getType());
          sentimentFS.addToIndexes();
          fs.setFeatureValue(type.getFeatureByBaseName("sentiment"), sentimentFS);
        }
        cas.addFsToIndexes(fs);
        /* build annotations on this fs */
        buildAnnotations(cas, fs);
      }
    }
  }

  /* reverse build annotations from entities */
  private void buildAnnotations(JCas cas, BaseEntity fs) {
    Type type = fs.getType();
    String entityText = fs.getStringValue(type.getFeatureByBaseName("text"));
    int annotationStart = cas.getDocumentText().indexOf(entityText);
    if (annotationStart > 0) {
      // create annotation
      AlchemyAnnotation alchemyAnnotation = new AlchemyAnnotation(cas, annotationStart, annotationStart + entityText
              .length());
      alchemyAnnotation.setAlchemyType(type.toString());
      alchemyAnnotation.addToIndexes();
      UIMAFramework.getLogger().log(Level.INFO, new StringBuilder("added AlchemyAnnotation for ").append(alchemyAnnotation.getCoveredText()).append(" of type ").append(type.toString()).toString());
      // update entity occurrences
      NonEmptyFSList list = (NonEmptyFSList) fs.getOccurrences();
      if (list != null) {
        NonEmptyFSList newTail = new NonEmptyFSList(cas);
        newTail.setHead(list.getHead());
        newTail.setTail(list.getTail());
      } else {
        list = new NonEmptyFSList(cas);
      }
      list.setHead(alchemyAnnotation);
      fs.setOccurrences(list);
    }

  }

  private BaseEntity getFeatureStructure(String type, JCas aJCas) {
    BaseEntity fsObject = null;
    String typeName = new StringBuilder(ENTITY_PACKAGE_NAME).append(type).toString();
    Class<?> typeClass = getClassFromName(typeName);
    if (typeClass != null) {
      try {
        /* use reflection to build a BaseEntity object */
        fsObject = (BaseEntity) typeClass.getConstructor(JCas.class).newInstance(aJCas);
      } catch (Exception e) {
        // fsObject remains null
      }
    }
    return fsObject;
  }

  private Class<?> getClassFromName(String typeName) {
    Class<?> toReturn = null;
    try {
      toReturn = Class.forName(typeName);
    } catch (ClassNotFoundException cnfe) {
      // do nothing
    }

    return toReturn;
  }

}
