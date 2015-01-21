package com.pipl.api.data.fields;

import com.google.gson.annotations.Expose;
import com.pipl.api.data.Utils;


public class Gender extends AbstractField {
	private static final long serialVersionUID = 7317039160983652017L;
	public enum Content {male, female};
	@Expose
	public Content content;

	public Gender() {
	}

	public Gender(Content content) {
		this.content = content;
	}
	
	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}
	
	public String toString() {
		return Utils.titleCase(content.name());
	}
	
}
