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

package org.apache.uima.simpleserver.servlet;

import org.apache.uima.simpleserver.Service;
import org.apache.uima.simpleserver.config.Output;
import org.apache.uima.simpleserver.config.ServerSpec;
import org.apache.uima.simpleserver.config.TypeMap;
import org.apache.uima.simpleserver.output.Result;
import org.apache.uima.simpleserver.output.ResultConverter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * a base class that implements the specification of a UIMA Servlet
 */
public class SimpleServerServlet extends HttpServlet {

  public static final String utf8 = "utf-8";

  public static final String MODE_PARAMETER = "mode";

  public static final String DEFAULT_CODE_PAGE = utf8;

  public File baseWebappDirectory = null;

  public Service server = null;

  // If UIMA-AS is included as an ADD-ON to SimpleServer and UIMA-AS servlet 
  // is invoked, UIMA-AS servlet will set this flag
  protected boolean initializationSuccessful = false;

  private Logger logger = Logger.getAnonymousLogger();

  // define possible parameter names
  // map parameter name --> parameter description
  protected Map<String, String> servletGETParameters;

  protected Map<String, String> servletPOSTParameters;

  // for some parameters, we can define supported values
  // map parameter name --> map ( value --> description )
  protected Map<String, Map<String, String>> servletGETParamOptions;

  protected Map<String, Map<String, String>> servletPOSTParamOptions;

  private final boolean localInit;

  public SimpleServerServlet(boolean localInit) {
    super();
    this.localInit = localInit;
    this.servletGETParameters = new HashMap<String, String>();
    this.servletPOSTParameters = new HashMap<String, String>();
    this.servletGETParamOptions = new HashMap<String, Map<String, String>>();
    this.servletPOSTParamOptions = new HashMap<String, Map<String, String>>();
  }

  public SimpleServerServlet() {
    this(false);
  }

  protected Logger getLogger() {
    return this.logger;
  }

  // creates the mappings for standard parameter description
  // this method can be overridden for non-standard parameter sets
  protected void declareServletParameters() {
    this.servletGETParameters.put(MODE_PARAMETER, "This parameter should define, what"
            + " the servlet should return. Some options are available.");
    Map<String, String> options = new HashMap<String, String>();
    this.servletGETParamOptions.put(MODE_PARAMETER, options);
    options.put("description", "will return a description of a service "
            + "in  HTML (human-readable) format. This description is"
            + " partially automatically generated, and partially created "
            + "by the author of this service.");
    options.put("xsd", "will return a XSD schema definition of the text " + "analysis results");
    options.put("form", "will show a form with input fields, which will "
            + "allow you to try out this service");
    options.put("xmldesc", "will show a specification of this service in XML " + "format");

    this.servletPOSTParameters.put("text", "the value of this parameter is the "
            + "text to analyze. Expected encoding is UTF-8. This "
            + "parameter must always be set.");

    this.servletPOSTParameters.put(MODE_PARAMETER, "This parameter should define, what"
            + " view of the analyss result the servlet should return. "
            + "If this parameter is not set, XML output will be produced.");
    options = new HashMap<String, String>();
    options.put("xml", "means to output the result as a XML-document "
            + "containing a list of found entities");
    options.put("inline", "returns inline-xml containing the analyzed "
            + "text in which all found entities are represented by tags");
//    options.put("csv", "returns the found entities in a comma-separated list"); // Jira 1795
    this.servletPOSTParamOptions.put(MODE_PARAMETER, options);

    this.servletPOSTParameters.put("lang", "This parameter sets the language "
            + "of the text. If this parameter is not set, the value"
            + "&quot;en&quot; will be used");

  }

  /*
   * implements the GET behavior described in the documentation
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    if (!this.initializationSuccessful) {
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
              "The service is currently unavailable due to internal errors."
                      + "\nPlease contact the service provider.");
      return;
    }
    response.setCharacterEncoding(DEFAULT_CODE_PAGE);
    request.setCharacterEncoding(DEFAULT_CODE_PAGE);
    PrintWriter writer = response.getWriter();
    if (request.getParameterMap().isEmpty()) {
      writer.print(this.constructHtmlDescription(request.getRequestURL().toString()));
      writer.close();
      return;
    }
    String mode = request.getParameter(MODE_PARAMETER);
    try {
      if ("xsd".equals(mode)) {
        // just give out the XSD definition provided by the server
        response.setHeader("content-type","text/xml");
        writer.println(this.server.getXMLResultXSD());
        writer.close();
      } else if ("description".equals(mode)) {
        // output a service description in HTML format
        response.setHeader("content-type","text/html");
        writer.print(this.constructHtmlDescription(request.getRequestURL().toString()));
        writer.close();
      } else if ("form".equals(mode)) {
        // create a tryout HTML form and give it out
        response.setHeader("content-type","text/html");
        writer.print(this.getHtmlForm(request.getRequestURL().toString()));
        writer.close();
      } else if ("xmldesc".equals(mode)) {
        response.setHeader("content-type","text/xml");
        writer.print(this.server.getServiceDescription());
        writer.close();
      } else {
        // interpret this as a request for actual analysis
        response.setHeader("content-type","text/xml");
        analyze(request, response);
      }
    } catch (IOException e) {
      getLogger().log(Level.SEVERE, "An error occured processing this request", e);
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
              "An internal error occured, this service has not been properly initialized.");
    }
  }

  /*
   * implements the default POST behavior described in the documentation
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws IOException {
    response.setHeader("content-type","text/xml");
    analyze(request, response);
  }

  /*
   * handles requests that send some text to be analyzed
   */
  protected void analyze(HttpServletRequest request, HttpServletResponse response)
          throws IOException {
    System.out.println(this.getClass().getName() + ": POST request received: " + new Date());
    request.setCharacterEncoding(DEFAULT_CODE_PAGE);
    response.setCharacterEncoding(DEFAULT_CODE_PAGE);
    String text = request.getParameter("text");
    String lang = request.getParameter("lang");
    System.out.println("Given text: " + text.substring(0, Math.min(50, text.length())));
    String mode = request.getParameter(MODE_PARAMETER);
    if ((lang == null) || lang.equals("")) {
      lang = "en";
    }
    System.out.println(MODE_PARAMETER + ": " + mode);
    System.out.println("lang: " + lang);
    // process the text
    Result result = this.server.process(text, lang);
    PrintWriter writer = response.getWriter();
    writer.write(transformResult(result, mode));
    writer.close();
  }

  // just create a Server instance and check where the project directory is
  // (baseWebappDirectory)
  @Override
  public void init() throws ServletException {
    System.out.println("Starting UIMA servlet initialization");
    if (this.localInit) {
      return;
    }
    super.init();
    System.out.println("Servlet " + this.getClass().getCanonicalName()
            + " -- initialisation begins");
    this.baseWebappDirectory = new File(getServletContext().getRealPath(""));
    // File xsdFile = new File(baseWebappDirectory.getAbsoluteFile()
    // + "/schema/ResultSpecification.xsd");
    // server = new Server(xsdFile);
    this.server = new Service();
    this.initializationSuccessful = initServer();
    declareServletParameters();
  }

  public void init(File descriptorFile, File serviceSpecFile) throws ServletException {
    super.init();
    this.initializationSuccessful = false;
    this.server = new Service();
    try {
      this.server.configureAnalysisEngine(descriptorFile, serviceSpecFile);
    } catch (Exception e) {
      getLogger().log(Level.SEVERE, "UIMA Simple Service configuaration failed", e);
      return;
    }
    declareServletParameters();
    this.initializationSuccessful = true;
  }

  // If UIMA-AS is included as an ADD-ON to SimpleServer and UIMA-AS servlet 
  // is invoked, UIMA-AS servlet will override this method
  protected boolean initServer() {
    File resultSpec = null;
    String resultSpecParamValue = getInitParameter("ResultSpecFile");
    if (resultSpecParamValue != null) {
      resultSpec = new File(this.baseWebappDirectory.getAbsoluteFile(), resultSpecParamValue);
    }
    String pearPath = getInitParameter("PearPath");
    String descriptorPath = getInitParameter("DescriptorPath");
    String pearInstallPath = getInitParameter("PearInstallPath");

    try {
      if (pearPath == null) {
        File descriptor = new File(this.baseWebappDirectory.getAbsoluteFile(), descriptorPath);
        this.server.configureAnalysisEngine(descriptor, resultSpec);
      } else if (descriptorPath == null) {
        File pear = new File(this.baseWebappDirectory.getAbsoluteFile(), pearPath);
        // get default servlet working directory
        File pearInstallDir = (File) this.getServletContext().getAttribute(
                "javax.servlet.context.tempdir");
        // check if a special install directory is specified
        if (pearInstallPath != null) {
          pearInstallDir = new File(pearInstallPath);
          // check if a relative path is set, relative path names are
          // evaluated relative to the PEAR file location.
          if (!pearInstallDir.isAbsolute()) {
            pearInstallDir = new File(pear.getParentFile(), pearInstallPath);
          }
        }
        getLogger().log(Level.INFO, "Install PEAR file to: " + pearInstallDir.getAbsolutePath());
        this.server.configurePear(pear, pearInstallDir, resultSpec);
      }
    } catch (Exception e) {
      getLogger().log(Level.SEVERE, "UIMA Simple Service configuaration failed", e);
      return false;
    }
    return true;
  }

  /*
   * choose the output format, depending on the value of the given "mode" parameter
   */
  public String transformResult(Result result, String mode) {
    if ("xml".equals(mode)) {
      return ResultConverter.getXMLString(result);
    }
    if ("inline".equals(mode)) {
      return ResultConverter.getInlineXML(result);
    }
    return ResultConverter.getXMLString(result);
  }

  // can be overridden by the creator of the subclasses.
  // just some HTML that delivers additional info about the service
  public String getCustomDescription() {
    return "";
  }

  // creates a service description as a HTML page, using the provided
  // maps woth parameters, values and their descriptions,
  // as well as the current URL,
  // and the additional descritpion specified in the previous method.

  public String constructHtmlDescription(String servletURL) {
    ServerSpec rspec = this.server.getServiceSpec();
    String html = "<html>" + "<head>" + "<title>"
            + rspec.getShortDescription()
            + "</title>"
            + "</head>"
            + "<body>"
            + "<h2>"
            + rspec.getShortDescription()
            + "</h2>"
            + rspec.getLongDescription()
            + "<h3>Usage</h3>"
            + "In order to use this service, a POST- or GET-request should be sent to the server with the following URL:"
            + "<pre>"
            + servletURL
            + "</pre>"
            + "<br/>"
            + "The following request parameters are expected:"
            + constructParameterDescription()
            + "<h3>Result</h3>"
            + "If XML or inline-XML output is requested, it will contain the tags listed below. "
            + "The XSD-definition of the output in XML-format can be downloaded "
            + "<a href=\""
            + servletURL
            + "?mode=xsd\">here</a>."
            + constructResultDescription()
            + ""
            + (getCustomDescription().equals("") ? "" : "<h3>Additional description </h3> "
                    + getCustomDescription())
            + ""
            + "<h3>Example of usage</h3>"
            + "<pre>"
            + "String text = \"Hello Mr. John Smith !\"; \n"
            + "String parameters = \"text=\" + URLEncoder.encode(text, \"UTF-8\") + \"&mode=inline\";\n"
            + "URL url = new URL(\""
            + servletURL
            + "\"); \n"
            + "URLConnection connection = url.openConnection(); \n"
            + "connection.setDoOutput(true); \n"
            + "OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream()); \n"
            + "writer.write(parameters);\n"
            + "writer.flush(); \n\n"
            + "BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), \"UTF-8\")); \n"
            + "String line; \n" + "while ((line = reader.readLine()) != null) { \n"
            + "    System.out.println(line);\n" + "} \n" + "</pre>" + "<body>" + "</html>";

    return html;
  }

  // routine used in the previous method
  public String constructParameterDescription() {
    String s = "";
    s += "<h3>POST-parameters</h3> POST request should be sent to use " + "the service";
    s += parameterDescription(this.servletPOSTParameters, this.servletPOSTParamOptions);
    s += "<h3>GET-parameters</h3> GET request should be sent to obtain "
            + "information about the service";
    s += parameterDescription(this.servletGETParameters, this.servletGETParamOptions);
    return s;
  }

  // routine used in the previous method
  public String parameterDescription(Map<String, String> params,
          Map<String, Map<String, String>> options) {
    String s = "";
    s += "<ul>";
    for (String param : params.keySet()) {
      String desc = params.get(param);
      s += "<li/> ";
      s += "<b>" + param + "</b> " + " -- " + desc;
      Map<String, String> opts = options.get(param);
      if (opts != null) {
        s += "<ul>";
        s += "Possible values: <br/>";
        for (String opt : opts.keySet()) {
          s += "<li/> ";

          s += "<b>" + opt + "</b> " + " -- " + opts.get(opt);
        }
        s += "</ul>";
      }
    }
    s += "</ul>";
    return s;
  }

  public String constructResultDescription() {
    String s = "";
    try {
      s += "<h4>XML elemets of result</h4>";
      s += "<ul>";
      for (TypeMap t : this.server.getServiceSpec().getTypeSpecs()) {
        s += "<li/>";
        s += "Element <b>" + t.getOutputTag() + "</b> -- " + t.getShortDescription();
        s += "<br/> " + t.getLongDescription();
        s += "<ul>";
        s += "Attributes: <br/>";

        for (Output o : t.getOutputs()) {
          s += "<li/>";
          s += "Attribute <b>" + o.getAttribute() + "</b> -- " + o.getShortDescription();
          s += "<br/> " + o.getLongDescription();
        }

        s += "</ul>";
        s += "<br/>";
      }

      s += "</ul>";

    } catch (Exception e) {
      e.printStackTrace();
    }
    return s;
  }

  // creates a HTML page with a form which allows to try out the service
  public String getHtmlForm(String serverUrl) {
    String str = "<html>" + "<head>" + "<title>"
            + serverUrl
            + " tryout form "
            + "</title>"
            + "<meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\" >"
            + "</head>"
            + "<body>"
            + "<h2>Tryout form of "
            + serverUrl
            + "</h2>"
            + "<form method=\"post\"  action=\""
            + serverUrl
            + "\">"
            + "Enter text: <br/>"
            + "<textarea name=\"text\" rows=\"25\" cols=\"80\"></textarea><br/>"
            + "Enter language(optional):<br/>"
            + "<input type=\"text\" name=\"lang\" /><br/>"
            + "Display result as<br/>"
            + "<input type=\"radio\" name=\"mode\" value=\"xml\" checked=\"checked\"/> XML document <br/>"
            + "<input type=\"radio\" name=\"mode\" value=\"inline\"/> inline XML <br/>" + "" + ""
            + "" + "" + "" + "" + "" + "" + "<input type=\"submit\">" + "</form>" + "</body>"
            + "</html>";
    return str;
  }

}
