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
package org.apache.uima.pear.tools;

import java.util.ArrayList;
import java.util.Properties;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.Parameter;

/**
 * Class to create an ANT task to package a UIMA PEAR file. 
 * 
 * See the example below on how to add the target to your ant build.
 * 
 * <pre>
 *
 * &lt;!-- define the classpath settings for the pear packaging task. 
 *      uimaj-pear-task.jar and uima-core.jar is needed --&gt;
 * &lt;path id="pearTask.classpath"&gt;
 *     &lt;fileset dir="/home/apache-uima/lib/" includes="uimaj-pear-task.jar"/&gt;
 *     &lt;fileset dir="/home/apache-uima/lib/" includes="uima-core.jar"/&gt;
 * &lt;/path&gt;
 * 
 * &lt;!-- pear packaging sample target --&gt;
 * &lt;target name="pearPackagingSample"&gt;
 *     
 *   &lt;!-- Define pear packaging task --&gt;
 *   &lt;taskdef name="packagePear" 
 *       classname="org.apache.uima.pear.tools.PearPackagingAntTask"
 *       classpathref="pearTask.classpath"/&gt;
 *
 *    &lt;!-- Sample pear packaging --&gt;
 *    &lt;packagePear componentID="SampleAnnotator"
 *        mainComponentDesc="desc/mainComponentDesc.xml" 
 *        classpath="$main_root/pearClasspahtEntry;$main_root/anotherPearClasspahtEntry" 
 *        datapath="$main_root/resources"
 *        mainComponentDir="/home/user/workspace/SampeAnntotator" 
 *        targetDir="/home/user/pearArchive"&gt;
 *        &lt;envVar name="ENV_VAR_NO1" value="value1"/&gt;
 *        &lt;envVar name="ENV_VAR_NO2" value="value2"/&gt;
 *   &lt;/packagePear&gt;
 * &lt;/target&gt;
 * </pre>
 * 
 */
public class PearPackagingAntTask extends Task {

  private String componentID = null;

  private String mainComponentDesc = null;

  private String classpath = null;

  private String datapath = null;

  private String mainComponentDir = null;

  private String targetDir = null;

  private ArrayList params = new ArrayList();

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.tools.ant.Task#execute()
   */
  public void execute() throws BuildException {
    super.execute();

    // get environment variables (<envVar name="ENV_VAR_NO1" value="value1"/>) specified for the pear component
    Properties props = null;
    if (this.params.size() > 0) {
      props = new Properties();
      for (int i = 0; i < this.params.size(); i++) {
        Parameter param = (Parameter) this.params.get(i);
        props.setProperty(param.getName(), param.getValue());
      }
    }

    //call pear packager with the ANT defined information
    try {
      PackageCreator.generatePearPackage(this.componentID, this.mainComponentDesc, this.classpath,
              this.datapath, this.mainComponentDir, this.targetDir, props);
    } catch (PackageCreatorException ex) {
      throw new BuildException(ex);
    }
  }

  /**
   * add environment variable for the pear component
   * 
   * @param envVar 
   *          environment variable setting
   */
  public void addEnvVar(Parameter envVar) {
    this.params.add(envVar);
  }

  /**
   * set classpath for the pear component. 
   * 
   * @param classpath
   *          the classpath to set
   */
  public void setClasspath(String classpath) {
    this.classpath = classpath;
  }

  /**
   * set component ID for the pear component
   * 
   * @param componentID
   *          the componentID to set
   */
  public void setComponentID(String componentID) {
    this.componentID = componentID;
  }

  /**
   * the datapath setting for the pear component
   * 
   * @param datapath
   *          the datapath to set
   */
  public void setDatapath(String datapath) {
    this.datapath = datapath;
  }

  /**
   * the main component descriptor for the pear component
   * 
   * @param mainComponentDesc
   *          the mainComponentDesc to set
   */
  public void setMainComponentDesc(String mainComponentDesc) {
    this.mainComponentDesc = mainComponentDesc;
  }

  /**
   * the main component directory for the pear data
   * 
   * @param mainComponentDir
   *          the mainComponentDir to set
   */
  public void setMainComponentDir(String mainComponentDir) {
    this.mainComponentDir = mainComponentDir;
  }

  /**
   * the target directory for the output
   * 
   * @param targetDir
   *          the targetDir to set
   */
  public void setTargetDir(String targetDir) {
    this.targetDir = targetDir;
  }

}
