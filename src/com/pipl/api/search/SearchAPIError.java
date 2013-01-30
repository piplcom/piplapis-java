package com.pipl.api.search;

import com.pipl.api.APIError;

/**
 * An exception raised when the response from the search API contains an error.
 */

public class SearchAPIError extends APIError {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SearchAPIError(String error, int httpStatusCode) {
        super(error, httpStatusCode);
    }
}
