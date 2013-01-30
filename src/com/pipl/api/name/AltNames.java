package com.pipl.api.name;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.annotations.Expose;

/**
 * Helper class for NameAPIResponse, holds alternate first/middle/last names for
 * a name.
 */
public class AltNames implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Expose
	private ArrayList<String> first;
	@Expose
	private ArrayList<String> middle;
	@Expose
	private ArrayList<String> last;

	public AltNames(ArrayList<String> first, ArrayList<String> middle,
			ArrayList<String> last) {
		setFirst(first);
		setMiddle(middle);
		setLast(last);
	}

	public ArrayList<String> getFirst() {
		return first;
	}

	public void setFirst(ArrayList<String> first) {
		if (first == null) {
			this.first = new ArrayList<String>();
		} else {
			this.first = first;
		}
	}

	public ArrayList<String> getMiddle() {
		return middle;
	}

	public void setMiddle(ArrayList<String> middle) {
		if (middle == null) {
			this.middle = new ArrayList<String>();
		} else {
			this.middle = middle;
		}

	}

	public ArrayList<String> getLast() {
		return last;
	}

	public void setLast(ArrayList<String> last) {
		if (last == null) {
			this.last = new ArrayList<String>();
		} else {
			this.last = last;
		}
	}
}
