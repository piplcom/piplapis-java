package com.pipl.api.data.fields;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Base class of all data fields, made only for inheritance.
 */
public abstract class AbstractField implements Serializable, Field {
	private static final long serialVersionUID = 1L;
	@Expose
	@SerializedName("@valid_since")
	public Date validSince;
	@Expose
	@SerializedName("@inferred")
	public Boolean inferred;

	public AbstractField() {
	}
	
	@Override
	public boolean equals(Object obj) {
		Class<?> c1 = this.getClass();
		Class<?> c2 = obj.getClass();
		if (c1 == c2) {
			ArrayList<java.lang.reflect.Field> exposeAnnotatedFields1 = getFieldsWithExposeAnnotation(c1
					.getDeclaredFields());
			for (java.lang.reflect.Field refField : exposeAnnotatedFields1) {
				try {
					Object obj1 = refField.get(this);
					Object obj2 = refField.get(obj);
					if (obj1 != null && obj2 != null) {
						if (!obj1.equals(obj2)) {
							return false;
						}
					} else if ((obj1 == null && obj2 != null)
							|| (obj1 != null && obj2 == null)) {
						return false;
					}

				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			return true;
		}
		return false;
	}

	private ArrayList<java.lang.reflect.Field> getFieldsWithExposeAnnotation(
			java.lang.reflect.Field[] fieldArr) {
		Expose annotatedField;
		ArrayList<java.lang.reflect.Field> exposeAnnotatedFields = new ArrayList<java.lang.reflect.Field>();
		for (java.lang.reflect.Field f : fieldArr) {
			annotatedField = f
					.getAnnotation(com.google.gson.annotations.Expose.class);
			if (annotatedField != null) {
				f.setAccessible(true);
				exposeAnnotatedFields.add(f);
			}
		}
		return exposeAnnotatedFields;
	}

	/* (non-Javadoc)
	 * @see com.pipl.api.data.fields.Field#isSearchable()
	 */
	@Override
	public boolean isSearchable() {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.pipl.api.data.fields.Field#getValidSince()
	 */
	@Override
	public Date getValidSince() {
		return validSince;
	}
	
	/* (non-Javadoc)
	 * @see com.pipl.api.data.fields.Field#isInferred()
	 */
	@Override
	public boolean isInferred() {
		if (inferred==null)
			return false;
		return inferred;
	}
}