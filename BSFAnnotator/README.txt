Apache UIMA BSFAnnotator README file

INTRODUCTION

Note: This is the only documentation for this annotator at the moment.  Please feel free to add more!

The BSFAnnotator component provides an UIMA annotator implementation that allow the use of a 
BSF-supported scripting language to implement the initialize and process methods.  The annotator 
has one mandatory parameter the 'SourceFile' that contains the script.  

Sample code is provided; have a look at the 
BeanshellTestAnnotator.xml, RhinoTestAnnotator.xml or at the more complex BSFAggregatedAE.xml and 
their associated scripts.  These are found in the src/test/java and src/test/resources directory 
in the source distribution.