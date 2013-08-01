package com.pipl.api.name;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.pipl.api.data.Utils;
import com.pipl.api.data.fields.Name;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * A request to Pipl's Name API.
 * <p/>
 * A request is build with a name that can be provided parsed to
 * first/middle/last (in case it's already available to you parsed) or unparsed
 * (and then the API will parse it).
 * <p/>
 * Note that the name in the request can also be just a first-name or just a
 * last-name.
 */
public class NameAPIRequest {
	public static String defaultApiKey;
	public static String USER_AGENT = "piplapis/java";
	public static String BASE_URL = "http://api.pipl.com/name/v2/json/?";
	// HTTPS is also supported:
	//public static String BASE_URL = "https://api.pipl.com/name/v2/json/?";

	private String apiKey;
	private Name name;

	/**
	 * Examples:
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * NameAPIRequest.defaultApiKey = "key1";
	 * NameAPIRequest request = new NameAPIRequest(null, "abc", null,null,null);
	 * NameAPIRequest request1 = new NameAPIRequest("key2", "abc", null,null,null);
	 * <p/>
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param apiKey
	 *            `apiKey` is a valid API key (String), use "samplekey" for
	 *            experimenting, note that you can set a default API key
	 *            (NameAPIRequest.defaultApiKey = "<your_key>") instead of
	 *            passing it to each request object.
	 * @param firstName
	 *            firstName
	 * @param middleName
	 *            middleName
	 * @param lastName
	 *            lastName
	 * @param rawName
	 *            rawName
	 * @throws IllegalArgumentException
	 *             IllegalArgumentException is raised in case of illegal
	 *             parameters.
	 */
	public NameAPIRequest(String apiKey, String firstName, String middleName,
			String lastName, String rawName) throws IllegalArgumentException {
		if (Utils.isNullOrEmpty(apiKey) && Utils.isNullOrEmpty(defaultApiKey)) {
			throw new IllegalArgumentException("A valid API key is required");
		}
		if (!(!Utils.isNullOrEmpty(firstName) || !Utils.isNullOrEmpty(lastName)
				|| !Utils.isNullOrEmpty(middleName) || !Utils
					.isNullOrEmpty(rawName))) {
			throw new IllegalArgumentException("A name is missing");
		}
		if (!Utils.isNullOrEmpty(rawName)
				&& (!Utils.isNullOrEmpty(firstName) || !Utils.isNullOrEmpty(middleName) || !Utils.isNullOrEmpty(lastName))) {
			throw new IllegalArgumentException(
					"Name should be provided raw or parsed, not both");
		}
		setApiKey(apiKey);
		this.name = new Name.Builder().first(firstName).middle(middleName)
				.last(lastName).raw(rawName).build();
	}

	private NameAPIRequest(Builder builder) {
		this(builder.apiKey, builder.firstName, builder.middleName,
				builder.lastName, builder.rawName);
	}

	public static class Builder {
		private String apiKey;
		private String firstName;
		private String middleName;
		private String lastName;
		private String rawName;

		public Builder apiKey(String apiKey) {
			this.apiKey = apiKey;
			return this;
		}

		public Builder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder middleName(String middleName) {
			this.middleName = middleName;
			return this;
		}

		public Builder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder rawName(String rawName) {
			this.rawName = rawName;
			return this;
		}

		public NameAPIRequest build() {
			return new NameAPIRequest(this);
		}
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey == null ? defaultApiKey : apiKey;
	}

	public Name getName() {
		return name;
	}

	/**
	 * @return The URL of the request (String).
	 * @throws URISyntaxException
	 */
	public String url() throws URISyntaxException {
		URIBuilder ub;
		ub = new URIBuilder(BASE_URL);
		ub.addParameter("key", Utils.isNullOrEmpty(apiKey) ? defaultApiKey
				: apiKey);
		ub.addParameter("first_name",
				name.getFirst() == null ? "" : name.getFirst());
		ub.addParameter("middle_name",
				name.getMiddle() == null ? "" : name.getMiddle());
		ub.addParameter("last_name",
				name.getLast() == null ? "" : name.getLast());
		ub.addParameter("raw_name", name.getRaw() == null ? "" : name.getRaw());
		return ub.toString();
	}

	/**
	 * Send the request and return the response or raise NameAPIError.
	 * 
	 * @return <code>NameAPIResponse</code> object
	 * @throws NameAPIError
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public NameAPIResponse send() throws NameAPIError, IOException,
			URISyntaxException {
		HttpClient httpClient = new DefaultHttpClient();
		try {
			HttpGet httpGet = new HttpGet(url());
			httpGet.setHeader("User-Agent", USER_AGENT);
			HttpResponse httpResponse = httpClient.execute(httpGet);
			String responseBody = EntityUtils
					.toString(httpResponse.getEntity());
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				return (NameAPIResponse) Utils.fromJson(responseBody,
						NameAPIResponse.class);
			} else {
				NameAPIError nameAPIError = (NameAPIError) Utils.fromJson(
						responseBody, NameAPIError.class);
				throw nameAPIError;
			}
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
	}
}
