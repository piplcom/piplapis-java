package com.pipl.api.data.fields;

import java.lang.reflect.Type;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.Expose;

/**
 * This class is used to calculate a string attribute named display
 * before serializing a class that inherits from DisplayField.
 * No need to use this class explicitly. It is used by Utils.toJson
 */
public class DisplayField extends Field implements JsonSerializer<DisplayField>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Expose(deserialize = false, serialize = true)
	public String display;
	
	public DisplayField(Date validSince) {
		super(validSince);
	}
	
	public String display(){
		return "";
	}
	
	@Override
	public JsonElement serialize(DisplayField displayField, Type arg1,
			JsonSerializationContext arg2) {
		displayField.display = displayField.display();
		return new Gson().toJsonTree(displayField);
	}

}
