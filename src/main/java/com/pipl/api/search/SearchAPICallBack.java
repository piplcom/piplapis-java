package com.pipl.api.search;


/**
 * Call back class for Asynchronous send
 * In case of successful execution exception callback will be called with response.
 * In case of failure errback will be called with exception.
 */
public interface SearchAPICallBack {
    public void callback(SearchAPIResponse searchAPIResponse);
    public void errback(Exception exception);
}
