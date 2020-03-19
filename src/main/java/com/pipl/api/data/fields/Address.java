package com.pipl.api.data.fields;


import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pipl.api.data.Utils;

/**
 * An address of a person.
 */

public class Address extends AbstractField {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Expose
	public String country;
	@Expose
	public String state;
	@Expose
	public String city;
	@Expose
	@SerializedName("zip_code")
	public String zipCode;
	@Expose
	@SerializedName("po_box")
	public String poBox;
	@Expose
	public String street;
	@Expose
	public String house;
	@Expose
	public String apartment;
	@Expose
	public String raw;
	@Expose
	@SerializedName("@type")
	public String type;
	@Expose
	public String display;

	public Address() {
	}
	
	/**
	 * @param raw
	 *            `raw` is an unparsed address like "123 Marina Blvd, San
	 *            Francisco, California, US", useful when you want to search by
	 *            address and don't want to work hard to parse it. Note that in
	 *            response data there's never address.raw, the addresses in the
	 *            response are always parsed, this is only for querying with an
	 *            unparsed address.
	 */
	public Address(String raw) {
		setRaw(raw);
	}
	
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
	 * @param zipCode
	 *            Zip code
	 * @param poBox
	 *            PO Box
	 * @param street
	 *            street
	 * @param house
	 *            house
	 * @param apartment
	 *            apartment
	 */
	public Address(String country, String state, String city, String zipCode, String poBox,
			String street, String house, String apartment) {
		setCountry(country);
		setState(state);
		setCity(city);
		setZipCode(zipCode);
		setPoBox(poBox);
		setStreet(street);
		setHouse(house);
		setApartment(apartment);
	}

	private Address(Builder builder) {
		this(builder.country, builder.state, builder.city, builder.zipCode, builder.poBox,
				builder.street, builder.house, builder.apartment);
	}

	public static class Builder {
		private String country;
		private String state;
		private String city;
		private String zipCode;
		private String poBox;
		private String street;
		private String house;
		private String apartment;

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

		public Builder zipCode(String zipCode) {
			this.zipCode = zipCode;
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

		public Address build() {
			return new Address(this);
		}

	}

	@Override
	public String toString() {
		if (display!=null) {
			return display;
		}
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
	 * @return true if the address is a valid address to search by.
	 */
	public boolean isSearchable() {
		return (!Utils.isNullOrEmpty(raw) || !Utils.isNullOrEmpty(country) || !Utils.isNullOrEmpty(state) || !Utils.isNullOrEmpty(city));

	}
	
	/**
	 * @return true if the address is a valid address for an address-only search.
	 */
	public boolean isViableAddressSearch() {
		return !Utils.isNullOrEmpty(raw) || (!Utils.isNullOrEmpty(city) && !Utils.isNullOrEmpty(street) && !Utils.isNullOrEmpty(house));
	}

	/**
	 * @return true if the object's country is a valid country code.
	 */
	public boolean isValidCountry() {
		return !Utils.isNullOrEmpty(country)
				&& Utils.COUNTRIES.containsKey(country.toUpperCase());
	}

	/**
	 * @return true if the object's state is a valid state code.
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
	 *
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
	 *
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

	public String getZipCode() {
		return zipCode;
	}
	
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
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
	
	public String getDisplay() {
		return display;
	}

}