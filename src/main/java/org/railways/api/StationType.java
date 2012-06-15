package org.railways.api;

/**
 * 发站、到站类型
 * 
 * @since 2012-6-4
 * @author gmz
 * 
 */
public enum StationType {
	/** 全部 */
	ALL("1"),
	/** 始发 */
	ORIGIN("3"),
	/** 终到 */
	TERMINAL("4"),
	/** 始发终到 */
	ORIGIN_AND_TERMINAL("5"),
	/** 过路 */
	PASSING_BY("6");

	String value;

	private StationType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}