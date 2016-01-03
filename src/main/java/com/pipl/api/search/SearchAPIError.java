package com.pipl.api.search;

import java.io.Serializable;

import com.pipl.api.APIError;

/**
 * An exception raised when the response from the search API contains an error.
 */

public class SearchAPIError extends APIError implements Serializable {
	private static final long serialVersionUID = 1L;

	public SearchAPIError(String message, Integer httpStatusCode) {
		super(message, httpStatusCode);
	}

	public SearchAPIError(String message, Integer httpStatusCode, Throwable cause) {
		super(message, httpStatusCode, cause);
	}

}
