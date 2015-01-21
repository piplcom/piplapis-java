package com.pipl.api;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

/**
 * An exception raised when the response from the API contains an error.
 */
public class APIError extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String error;
	@SerializedName("@http_status_code")
	private Integer httpStatusCode;

	public APIError(String error, Integer httpStatusCode) {
		super(error);
		this.error = error;
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

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Integer getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(Integer httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}
}