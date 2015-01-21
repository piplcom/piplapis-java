package com.pipl.api.data.fields;

import com.google.gson.annotations.Expose;
import com.pipl.api.data.Utils;

public class Ethnicity extends AbstractField {
	private static final long serialVersionUID = -3577292750530003315L;
	public enum Content {white, black, american_indian, alaska_native, asian_indian, chinese, filipino, other_asian, japanese, korean, viatnamese, native_hawaiian, guamanian, chamorro, samoan, other_pacific_islander, other};
	@Expose
	public Content content;

	public Ethnicity() {
	}

	public Content getContent() {
		return content;
	}

	public String toString() {
		return Utils.titleCase(content.name());
	}
	
}
