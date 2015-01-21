package com.pipl.api.search;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

/**
 * An exception raised when the response from the search API contains an error.
 */

public class SearchAPIError extends Exception implements Serializable {
	private static final long serialVersionUID = 1L;
	@SerializedName("@http_status_code")
	private Integer httpStatusCode;

	public SearchAPIError(String message, Integer httpStatusCode) {
		super(message);
		this.httpStatusCode = httpStatusCode;
	}

	public SearchAPIError(String message, Integer httpStatusCode, Throwable cause) {
		super(message, cause);
		this.httpStatusCode = httpStatusCode;
	}

	/**
	 * @return A boolean that indicates whether the error is on the user's side.
	 */
	public boolean isUserError() {
		return 400 <= httpStatusCode && httpStatusCode < 500;
	}

	/**
	 * @return A boolean that indicates whether the error is on Pipl's side.
	 */
	public boolean isPiplError() {
		//
		return !isUserError();
	}

	public Integer getHttpStatusCode() {
		return httpStatusCode;
	}
}
