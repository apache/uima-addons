Apache UIMA TikaAnnotator README file

INTRODUCTION

Apache Tika is a toolkit for detecting and extracting metadata 
and structured text content from various documents 
using existing parser libraries. 

TikaAnnotator uses Tika to generate annotations representing 
the original markup of a document, extract its text and metadata. 
It consists of three resources (see /desc):

- FileSystemCollectionReader : similar to the one in UIMA examples but uses 
  TIKA to extract the text from binary documents and generates annotations 
  to represent the markup
- MarkupAnnotator : takes the original content from a view and generates 
  a new view containing the extracted text with markup annotations
- TikaWrapper : utility class which allows to populate a CAS 
  from a binary document; used by the FileSystemCollectionReader
  
VERSION

This version wraps Tika 0.4.  In that version of Tika, the packaging 
for Tika was split into several parts.

The tika-core jar contains only the core client-visible classes and
interfaces and has zero dependencies beyond Java 5. All the actual
parser implementations and external parser dependencies are in the
tika-parsers jar. 

See http://lucene.apache.org/tika/gettingstarted.html for the full
details.

COMPILATION 

You can use the ANT script to compile the sources. 
Note that you need to add the Tika-jars in the /lib directory; 
it is recommended to use the Tika-*-standalone.jar 
which contains all the libraries used internally by Tika.

For more information on UIMA, see:
  http://incubator.apache.org/uima

For more information on Tika, see:
  http://incubator.apache.org/tika/
