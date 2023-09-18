piplapis Java Library
===========================

This is a Java client library for easily integrating Pipl's APIs into your application.

* Full details about Pipl's APIs - [https://pipl.com/api](https://pipl.com/api)  
* This library is available in other languages - [https://docs.pipl.com/docs/code-libraries](https://docs.pipl.com/docs/code-libraries)

Library Requirements
--------------------

* Google Gson, you can find it inside this project under `lib/google/` or download from [here](http://code.google.com/p/google-gson/downloads/list).
* The library SDK version 5.1.0 is compatible with Java 8 and above.

Installation
------------

* Import the piplapis jar file (can be found under `lib/`) or compile the source code (can be found under `src/`)

Hello World
-----------
```
import com.pipl.api.search.SearchAPIRequest;
import com.pipl.api.search.SearchAPIResponse;
import com.pipl.api.search.SearchConfiguration;

SearchConfiguration configuration = new SearchConfiguration();
configuration.setProtocol("https");
configuration.apiKey = 'YOURKEY'
  
SearchAPIRequest request = new
SearchAPIRequest.Builder().email("clark.kent@example.com").firstName("Clark").lastName("Kent").configuration(configuration).build();
```

Getting Started & Code Snippets
-------------------------------

**Pipl's Search API**
* API Portal - [https://pipl.com/api/](https://pipl.com/api/)
* Code snippets - [https://docs.pipl.com/docs/code-snippets](https://docs.pipl.com/docs/code-snippets)  
* Full reference - [https://docs.pipl.com/reference/](https://docs.pipl.com/reference/)
