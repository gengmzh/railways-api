package org.railways.api.ticket;

import java.io.Serializable;

/**
 * @author gmz
 * @time 2012-6-8
 */
public class IctCode implements Serializable {

	private static final long serialVersionUID = -5650121872963977572L;

	private String key, value;

	public IctCode(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return IctCode.class.getSimpleName() + "{key:" + getKey() + ", value:" + getValue() + "}";
	}

}
