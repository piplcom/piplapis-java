package com.pipl.api.data.fields;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pipl.api.data.Utils;

/**
 * An address of a person.
 */

public class Address extends DisplayField {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static HashSet<String> types = new HashSet<String>(Arrays.asList(
			"home", "work", "old"));
	@Expose
	private String country;
	@Expose
	private String state;
	@Expose
	private String city;
	@Expose
	@SerializedName("po_box")
	private String poBox;
	@Expose
	private String street;
	@Expose
	private String house;
	@Expose
	private String apartment;
	@Expose
	private String raw;
	@Expose
	@SerializedName("@type")
	private String type;

	/**
	 * @param country
	 *            `country` and `state` are country code (like "US") and state
	 *            code (like "NY"), note that the full value is available as
	 *            address.countryFull and address.stateFull.
	 * @param state
	 *            `country` and `state` are country code (like "US") and state
	 *            code (like "NY"), note that the full value is available as
	 *            address.countryFull and address.stateFull.
	 * @param city
	 *            city
	 * @param poBox
	 *            poBox
	 * @param street
	 *            street
	 * @param house
	 *            house
	 * @param apartment
	 *            apartment
	 * @param raw
	 *            `raw` is an unparsed address like "123 Marina Blvd, San
	 *            Francisco, California, US", useful when you want to search by
	 *            address and don't want to work hard to parse it. Note that in
	 *            response data there's never address.raw, the addresses in the
	 *            response are always parsed, this is only for querying with an
	 *            unparsed address.
	 * @param type
	 *            type is one of "home", "work", "old"
	 * @param validSince
	 *            `validSince` is a <code>Date</code> object, it's the first
	 *            time Pipl's crawlers found this data on the page.
	 */
	public Address(String country, String state, String city, String poBox,
			String street, String house, String apartment, String raw,
			String type, Date validSince) {
		super(validSince);
		setCountry(country);
		setState(state);
		setCity(city);
		setPoBox(poBox);
		setStreet(street);
		setHouse(house);
		setApartment(apartment);
		setRaw(raw);
		setType(type);
	}

	private Address(Builder builder) {
		this(builder.country, builder.state, builder.city, builder.poBox,
				builder.street, builder.house, builder.apartment, builder.raw,
				builder.type, builder.validSince);
	}

	public static class Builder {
		private String country;
		private String state;
		private String city;
		private String poBox;
		private String street;
		private String house;
		private String apartment;
		private String raw;
		private String type;
		private Date validSince;

		public Builder country(String country) {
			this.country = country;
			return this;
		}

		public Builder state(String state) {
			this.state = state;
			return this;
		}

		public Builder city(String city) {
			this.city = city;
			return this;
		}

		public Builder poBox(String poBox) {
			this.poBox = poBox;
			return this;
		}

		public Builder street(String street) {
			this.street = street;
			return this;
		}

		public Builder house(String house) {
			this.house = house;
			return this;
		}

		public Builder apartment(String apartment) {
			this.apartment = apartment;
			return this;
		}

		public Builder raw(String raw) {
			this.raw = raw;
			return this;
		}

		public Builder type(String type) {
			this.type = type;
			return this;
		}

		public Builder validSince(Date validSince) {
			this.validSince = validSince;
			return this;
		}

		public Address build() {
			return new Address(this);
		}

	}

	@Override
	public String toString() {
		return display();
	}

	@Override
	public String display() {
		String country = Utils.isNullOrEmpty(getState()) ? countryFull()
				: getCountry();
		String state = Utils.isNullOrEmpty(getCity()) ? stateFull()
				: getState();
		ArrayList<String> vals = new ArrayList<String>(Arrays.asList(
				getStreet(), getCity(), state, country));
		String disp = Utils.join(", ", Utils.filter(null, vals));
		String prefix = "";
		if (!Utils.isNullOrEmpty(getStreet())
				&& (!Utils.isNullOrEmpty(getHouse()) || !Utils
						.isNullOrEmpty(getApartment()))) {
			vals = new ArrayList<String>(Arrays.asList(getHouse(),
					getApartment()));
			vals = Utils.filter(null, vals);
			vals = Utils.filter("", vals);
			prefix = Utils.join("-", vals);
			if (!Utils.isNullOrEmpty(prefix)) {
				disp = prefix + " " + disp;
			}
		}
		String poBox = getPoBox();
		if (!Utils.isNullOrEmpty(poBox) && Utils.isNullOrEmpty(getStreet())) {
			disp = Utils.join(" ", Arrays.asList("P.O. Box", poBox, disp));
		}
		return disp;
	}

	/**
	 * @return A bool value that indicates whether the address is a valid
	 *         address to search by.
	 */
	public boolean isSearchable() {
		return (!Utils.isNullOrEmpty(raw) || (isValidCountry() && (Utils
				.isNullOrEmpty(getState()) || isValidState())));

	}

	/**
	 * @return A bool value that indicates whether the object's country is a
	 *         valid country code.
	 */
	public boolean isValidCountry() {
		return !Utils.isNullOrEmpty(country)
				&& Utils.COUNTRIES.containsKey(country.toUpperCase());
	}

	/**
	 * @return A bool value that indicates whether the object's state is a valid
	 *         state code.
	 */
	public boolean isValidState() {
		return isValidCountry()
				&& Utils.STATES.containsKey(getCountry().toUpperCase())
				&& !Utils.isNullOrEmpty(getState())
				&& Utils.STATES.get(getCountry().toUpperCase()).containsKey(
						getState().toUpperCase());

	}

	/**
	 * @return the full name of the object's country. example:
	 *         <p>
	 *         <blockquote>
	 * 
	 *         <pre>
	 *             >>> Address address = new Address.Builder().country("FR").build();
	 *             >>> System.out.println(address.getCountry());
	 *             "FR"
	 *             >>> System.out.println(address.countryFull());
	 *             "France"
	 *         <p/>
	 * </pre>
	 * 
	 *         </blockquote> Output: France
	 */
	public String countryFull() {
		String country = getCountry();
		if (null != country) {
			return Utils.COUNTRIES.get(country.toUpperCase());
		} else {
			return country;
		}
	}

	/**
	 * @return the full name of the object's state. example:
	 *         <p>
	 *         <blockquote>
	 * 
	 *         <pre>
	 * 			>>> Address address = new Address.Builder().country("US").state("CO").build();
	 * 				>>> System.out.println(address.getState());
	 * 				"CO"
	 * 				>>> System.out.println(address.stateFull());
	 * 				"Colorado"
	 *         <p/>
	 * </pre>
	 * 
	 *         </blockquote> Output: Colorado
	 */
	public String stateFull() {
		if (isValidState()) {
			return Utils.STATES.get(country.toUpperCase()).get(
					state.toUpperCase());
		}
		return null;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPoBox() {
		return poBox;
	}

	public void setPoBox(String poBox) {
		this.poBox = poBox;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	public String getApartment() {
		return apartment;
	}

	public void setApartment(String apartment) {
		this.apartment = apartment;
	}

	public String getRaw() {
		return raw;
	}

	public void setRaw(String raw) {
		this.raw = raw;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		if (!Utils.isNullOrEmpty(type)) {
			validateType(type, types);
			this.type = type;
		}
	}
}