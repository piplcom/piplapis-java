package com.pipl.api.name;

import com.pipl.api.APIError;

/**
 * An exception raised when the response from the name API contains an
 * error.
 */
public class NameAPIError extends APIError {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NameAPIError(String error, int httpStatusCode) {
        super(error, httpStatusCode);
    }
}
