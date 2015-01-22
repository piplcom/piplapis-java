package com.pipl.api.search;


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import com.google.gson.JsonSyntaxException;
import com.pipl.api.data.Utils;
import com.pipl.api.data.containers.Person;
import com.pipl.api.data.fields.Address;
import com.pipl.api.data.fields.DOB;
import com.pipl.api.data.fields.Email;
import com.pipl.api.data.fields.Field;
import com.pipl.api.data.fields.Name;
import com.pipl.api.data.fields.Phone;
import com.pipl.api.data.fields.Username;

/**
 * A request to Pipl's Search API.
 * <p/>
 * Sending the request and getting the response is very simple and can be done
 * by either making a blocking call to request.send() or by making a
 * non-blocking call to request.sendAsync(callback) which sends the request
 * asynchronously.
 */
public class SearchAPIRequest {
	public static final String USER_AGENT = "piplapis/java";
	public static SearchConfiguration defaultConfiguration = new SearchConfiguration();
	public Person person;
	public SearchConfiguration configuration;

	public SearchAPIRequest() {
	}
	
	/**
	 * @param person
	 *            The person can contain every
	 *            field allowed by the data-model and
	 *            can hold multiple fields of the same type (for example: 2
	 *            email addresses, 3 addresses etc.)
	 */
	public SearchAPIRequest(Person person) {
		this.person = person;
	}
	
	/**
	 * @param person
	 *            The person can contain every
	 *            field allowed by the data-model and
	 *            can hold multiple fields of the same type (for example: 2
	 *            email addresses, 3 addresses etc.)
	 * @param configuration
	 *            A specific configuration for this request.
	 *            If null, the default configurations will be used.
	 */
	public SearchAPIRequest(Person person, SearchConfiguration configuration) {
		this.person = person;
		this.configuration = configuration;
	}
	
	/**
	 * @param searchPointer
	 *            A searchPointer that was received in a previous response's
	 *            possible person. Will run a specific search for this person.
	 * @param configuration
	 *            A specific configuration for this request.
	 *            If null, the default configurations will be used.
	 */
	public SearchAPIRequest(String searchPointer, SearchConfiguration configuration) {
		person = new Person();
		person.searchPointer = searchPointer;
		this.configuration = configuration;
	}
	
	/**
	 * @param searchPointer
	 *            A searchPointer that was received in a previous response's
	 *            possible person. Will run a specific search for this person.
	 */
	public SearchAPIRequest(String searchPointer) {
		person = new Person();
		person.searchPointer = searchPointer;
	}
	
	/**
	 * Initiate a new request object with given query params. Each request must
	 * have at least one searchable parameter, meaning a name (at least first
	 * and last name), email, phone or username. Multiple query params are
	 * possible (for example querying by both email and phone of the person).
	 * 
	 * @param firstName
	 *            firstName, minimum 2 chars
	 * @param middleName
	 *            middleName
	 * @param lastName
	 *            lastName, minimum 2 chars
	 * @param rawName
	 *            an unparsed name containing at least a first name and a last
	 *            name.
	 * @param email
	 *            email
	 * @param phone
	 *            phone number. Will be parsed by Pipl.
	 * @param username
	 *            username, minimum 4 chars
	 * @param country
	 *            a 2 letter country code from:
	 *            http://en.wikipedia.org/wiki/ISO_3166-2
	 * @param state
	 *            a state code from:
	 *            http://en.wikipedia.org/wiki/ISO_3166-2%3AUS
	 *            http://en.wikipedia.org/wiki/ISO_3166-2%3ACA
	 * @param city
	 *            city
	 * @param rawAddress
	 *            An unparsed address
	 * @param fromAge
	 *            fromAge
	 * @param toAge
	 *            toAge
	 * @param configuration
	 *            A specific configuration for this request.
	 *            If null, the default configurations will be used.
	 */
	public SearchAPIRequest(String firstName, String middleName,
			String lastName, String rawName, String email, String phone,
			String username, String country, String state, String city,
			String rawAddress, Integer fromAge, Integer toAge, SearchConfiguration configuration) {
		ArrayList<Field> fields = new ArrayList<Field>();
		if (!Utils.isNullOrEmpty(firstName) || !Utils.isNullOrEmpty(middleName)
				|| !Utils.isNullOrEmpty(lastName)) {
			fields.add(new Name.Builder().first(firstName).middle(middleName)
					.last(lastName).build());
		}
		if (!Utils.isNullOrEmpty(rawName)) {
			fields.add(new Name(rawName));
		}
		if (!Utils.isNullOrEmpty(email)) {
			fields.add(new Email.Builder().address(email).build());
		}
		if (!Utils.isNullOrEmpty(phone)) {
			fields.add(new Phone(phone));
		}
		if (!Utils.isNullOrEmpty(username)) {
			fields.add(new Username(username));
		}
		if (!Utils.isNullOrEmpty(country) || !Utils.isNullOrEmpty(state)
				|| !Utils.isNullOrEmpty(city)) {
			fields.add(new Address.Builder().country(country).state(state)
					.city(city).build());
		}
		if (!Utils.isNullOrEmpty(rawAddress)) {
			fields.add(new Address(rawAddress));
		}
		if (fromAge != null || toAge != null) {
			fields.add(DOB.fromAgeRange(fromAge == null ? 0 : fromAge,
					toAge == null ? 1000 : fromAge));
		}
		person = new Person(fields);
		setConfiguration(configuration);
	}

	public SearchAPIRequest(Builder builder) {
		this(builder.firstName, builder.middleName,
				builder.lastName, builder.rawName, builder.email,
				builder.phone, builder.username, builder.country,
				builder.state, builder.city, builder.rawAddress,
				builder.fromAge, builder.toAge, builder.configuration);
	}

	/****
	 * Same as the "regular constructor, but enables default values. If not
	 * passed: all params will get their java default value except for
	 * queryParamsMode that is defaulted to "and"
	 */
	public static class Builder {
		private String firstName;
		private String middleName;
		private String lastName;
		private String rawName;
		private String email;
		private String phone;
		private String username;
		private String country;
		private String state;
		private String city;
		private String rawAddress;
		private Integer fromAge;
		private Integer toAge;
		private SearchConfiguration configuration;

		public Builder city(String city) {
			this.city = city;
			return this;
		}

		public Builder rawAddress(String rawAddress) {
			this.rawAddress = rawAddress;
			return this;
		}

		public Builder fromAge(int fromAge) {
			this.fromAge = fromAge;
			return this;
		}

		public Builder toAge(int toAge) {
			this.toAge = toAge;
			return this;
		}

		public Builder configuration(SearchConfiguration configuration) {
			this.configuration = configuration;
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

		public Builder email(String email) {
			this.email = email;
			return this;
		}

		public Builder phone(String phone) {
			this.phone = phone;
			return this;
		}

		public Builder username(String username) {
			this.username = username;
			return this;
		}

		public Builder country(String country) {
			this.country = country;
			return this;
		}

		public Builder state(String state) {
			this.state = state;
			return this;
		}

		public SearchAPIRequest build() {
			return new SearchAPIRequest(this);
		}

	}

	/**
	 * Check if the request is valid and can be sent, raise ValueError if not.
	 * 
	 * @param strict
	 *            `strict` is a boolean argument that defaults to True which
	 *            means an exception is raised on every invalid query parameter,
	 *            if set to False an exception is raised only when the search
	 *            request cannot be performed because required query params are
	 *            missing.
	 */
	public void validateQueryParams(boolean strict)
			throws IllegalArgumentException {
		
		if (Utils.isNullOrEmpty(configuration!=null ? configuration.apiKey : defaultConfiguration.apiKey)) {
			throw new IllegalArgumentException("API key is missing");
		}
		if (!person.isSearchable()) {
			throw new IllegalArgumentException(
					"No valid name/username/phone/email in request");
		}
		if (strict && !person.unsearchableFields().isEmpty()) {
			throw new IllegalArgumentException("Some fields are unsearchable: "
					+ person.unsearchableFields());
		}
	}

	/**
	 * Send the request and return the response or raise SearchAPIError.
	 * <p/>
	 * Calling this method blocks the program until the response is returned, if
	 * you want the request to be sent asynchronously please refer to the
	 * sendAsync method.
	 * 
	 * @param strictValidation
	 *            `strictValidation` is a bool argument that's passed to the
	 *            validateQueryParams method.
	 * @return The response is returned as a SearchAPIResponse object.
	 * @throws IllegalArgumentException
	 *             Raises IllegalArgumentException (raised from
	 *             validateQueryParams)
	 * @throws IOException
	 *             IOException
	 * @throws SearchAPIError
	 *             SearchAPIError (when the response is returned but contains an
	 *             error).
	 * @throws URISyntaxException
	 */
	public SearchAPIResponse send(boolean strictValidation)
			throws SearchAPIError, IOException, URISyntaxException {
		
		validateQueryParams(strictValidation);
		SearchConfiguration effectiveConfiguration = configuration!=null ? configuration : defaultConfiguration;
		URL url = new URL(effectiveConfiguration.toString());
		HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
		urlConnection.setDoOutput(true);
		urlConnection.setRequestProperty("User-Agent", USER_AGENT);
		urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		urlConnection.connect();
		OutputStreamWriter osw = new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8");
		if (person.searchPointer!=null) {
			osw.append("search_pointer=").append(person.searchPointer);
		} else {
			osw.append("person=").append(URLEncoder.encode(Utils.toJson(person), "UTF-8"));
		}
		osw.flush();
		try {
			if (urlConnection.getResponseCode()==HttpURLConnection.HTTP_OK) {
				SearchAPIResponse searchAPIResponse = (SearchAPIResponse) Utils.fromJson(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"), SearchAPIResponse.class);
				return searchAPIResponse;
			} else {
				SearchAPIError searchAPIError = (SearchAPIError) Utils.fromJson(new InputStreamReader(urlConnection.getErrorStream(), "UTF-8"), SearchAPIError.class);
				throw searchAPIError;
			}
		} catch (JsonSyntaxException e) {
			throw new SearchAPIError("Response did not contain valid JSON", urlConnection.getResponseCode(), e);
		}
	}

	public SearchAPIResponse send() throws SearchAPIError, IOException,
			URISyntaxException {
		return send(true);
	}

	/**
	 * Same as send() but in a non-blocking way.
	 * <p/>
	 * Use this method if you want to send the request asynchronously so your
	 * program can do other things while waiting for the response.
	 * 
	 * @param strictValidation
	 *            `strictValidation` is a bool argument that's passed to the
	 *            validateQueryParams method.
	 * @param searchAPICallBack
	 *            a callback that will receive the response once the response is
	 *            returned
	 * @throws IllegalArgumentException
	 *             Raises IllegalArgumentException (raised from
	 *             validateQueryParams)
	 * @throws IOException
	 *             IOException
	 * @throws URISyntaxException
	 */
	public void sendAsync(boolean strictValidation,
			SearchAPICallBack searchAPICallBack) throws IOException,
			URISyntaxException {
		new ResponseThread(searchAPICallBack, strictValidation).start();
	}

	/**
	 * Same as sendAsync(boolean strictValidation,SearchAPICallBack
	 * searchAPICallBack) but with default strictValidation==true
	 * 
	 * @param searchAPICallBack
	 *            a callback that will receive the response once the response is
	 *            returned
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public void sendAsync(SearchAPICallBack searchAPICallBack)
			throws IOException, URISyntaxException {
		sendAsync(true, searchAPICallBack);
	}

	private class ResponseThread extends Thread {
		private SearchAPICallBack searchAPICallBack;
		private boolean strictValidation;

		private ResponseThread(SearchAPICallBack searchAPICallBack,
				boolean strictValidation) {
			this.searchAPICallBack = searchAPICallBack;
			this.strictValidation = strictValidation;
		}

		@Override
		public void run() {
			try {
				searchAPICallBack.callback(send(strictValidation));
			} catch (SearchAPIError e) {
				searchAPICallBack.errback(e);
			} catch (IOException e) {
				searchAPICallBack.errback(e);
			} catch (URISyntaxException e) {
				searchAPICallBack.errback(e);
			}
		}
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public SearchConfiguration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(SearchConfiguration configuration) {
		this.configuration = configuration;
	}

	static public SearchConfiguration getDefaultConfiguration() {
		return defaultConfiguration;
	}
	
	@Override
	public String toString() {
		SearchConfiguration effectiveConfiguration = configuration!=null ? configuration : defaultConfiguration;
		StringBuilder sb = new StringBuilder(effectiveConfiguration.toString()).append("&");
		if (person.searchPointer!=null) {
			sb.append("search_pointer=").append(person.searchPointer);
		} else if (person!=null) {
			sb.append("person=");
			try {
				sb.append(URLEncoder.encode(Utils.toJson(person), "UTF-8"));
			} catch (Throwable t) {
			}
		}
		return sb.toString();
	}
}
