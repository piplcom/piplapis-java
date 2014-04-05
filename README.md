piplapis Java Library
===========================

This is a Java client library for easily integrating Pipl's APIs into your application.

* Full details about Pipl's APIs - [http://dev.pipl.com](http://dev.pipl.com)  
* This library is available in other languages - [http://dev.pipl.com/docs/libraries](http://dev.pipl.com/docs/libraries)

Library Requirements
--------------------

* Google Gson, you can find it inside this project under `lib/google/` or download from [here](http://code.google.com/p/google-gson/downloads/list).
* Apache HTTP Client, you can find it inside this project under `lib/apache/` or download from [here](http://www.apache.org/dist/httpcomponents/httpclient/binary/httpcomponents-client-4.2.3-bin.zip).
* The library was tested on Java 7 but will probably work on earlier versions as well.

Installation
------------

* Import the piplapis jar file (can be found under `lib/`) or compile the source code (can be found under `src/main/java`)

Maven
-----

Add a dependency to `com.pipl.api:piplapis` in your pom.  (Note: 1.0.1 was the latest version.)

    <dependency>
      <groupId>com.pipl.api</groupId>
      <artifactId>piplapis</artifactId>
      <version>1.0.1</version>
    </dependency>

Getting Started & Code Snippets
-------------------------------

**Pipl's Search API**
* Getting started tutorial - [http://dev.pipl.com/docs/search_api/getstarted](http://dev.pipl.com/docs/search_api/getstarted)  
* Code snippets - [http://dev.pipl.com/docs/search_api/code](http://dev.pipl.com/docs/search_api/code)  

**Pipl's Name API**
* Getting started tutorial - [http://dev.pipl.com/docs/name_api/getstarted](http://dev.pipl.com/docs/name_api/getstarted)  
* Code snippets - [http://dev.pipl.com/docs/name_api/code](http://dev.pipl.com/docs/name_api/code)  

**Pipl's Thumbnail API**
* Getting started tutorial - [http://dev.pipl.com/docs/thumbnail_api/getstarted](http://dev.pipl.com/docs/thumbnail_api/getstarted)  
* Code snippets - [http://dev.pipl.com/docs/thumbnail_api/code](http://dev.pipl.com/docs/thumbnail_api/code)  

Credits
-------

Thanks to [imdwolverine](https://github.com/imdwolverine) for writing this library!