Note to maintainers:

The Java source files in 
  org.apache.uima.tools.cfe.config,
  org.apache.uima.tools.cfe.config.impl, and
  org.apache.uima.tools.cfe.config.utils are 
generated using Eclipse EMF tooling, from the CFEConfig.xsd
file.  Note: One file: org.apache.uima.tools.cfe.config.utils.ConfigResourceUtil.java
is *not* generated but should be kept, if you regenerate the others.

The procedure for regenerating these, using Eclipse (version 3.5), is as follows:
    
1.  Open Java perspective and select ConfigurableFeatureExtractor project in ProjectExplorer
2.  Create new EMF Model from CFEConfig.xsd by following these steps:
2.1.  Click to select the folder ConfigurableFeatureExtractor/cfeModelGen
2.2   From main menu select File->New->Other->Eclipse Modeling Framework->EMF Generator Model
      If you don't see this selection, install the EMF Modeling Framework plugin 
      (Help -> Install New Software).
2.3.  In the "EMF Model" dialog:
2.3.1.  In the "Enter or select the parent folder" edit box verify the cfeModelGen folder is
        selected. 
2.3.2.  In the "File Name" edit box enter CFE.genmodel" and press "Next" 
2.4.  In the "Select a Model Importer" dialog select "XML Schema" 
      from the "Model Importers" list and press the "Next" button.
      If it's not a choice, install the XSL - XSD Schema Definition plugin, and try again.
2.5.  In "XML Schema Import" dialog browse the workspace to select 
      the schema file (src/main/xsdForEmf/CFEConfig.xsd) and press the "Next" button
2.6.  In the "Package selection" dialog press the "Finish" button
3.  Generating source files
3.1.  After step 2.6 a genmodel file CFEConfig.genmodel should be created and open in Eclipse editor
3.2.  Right-click this and select Show Properties View.  Change the following 4 properties:
        All -> Bundle Manifest: false
        Model -> Model Directory: change the value by appending /main/java so the value looks like
          /ConfigurableFeatureExtractor/src/main/java
        Model -> Model Plug in Id: delete (clear) this field
        Templates & Merge -> Update Classpath: false  
3.4.  Go back to the CFE.genmodel screen, save the modified model, and then 
      right-click the model and select "Generate Model Code"

This will regenerate the source files for the model under src/main/java in the 
org.apache.uima.tools.cfe.config, ...config.impl and ... config.utils packages.