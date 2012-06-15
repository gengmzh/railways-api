package org.railways.api;

/**
 * 火车类型
 * 
 * @since 2012-6-4
 * @author gmz
 * 
 */
public enum TrainType {

	/**
	 * 全部
	 */
	ALL("name_ckball", "value_ckball"),
	/**
	 * 动车
	 */
	DONGCHE("tFlagDC", "DC"),
	/**
	 * 直特
	 */
	ZHIDA("tFlagZ", "Z"),
	/**
	 * 特快
	 */
	TEKUAI("tFlagT", "T"),
	/**
	 * 快速
	 */
	KUAISU("tFlagK", "K"),
	/**
	 * 普快
	 */
	PUKUAI("tFlagPK", "PK"),
	/**
	 * 普客
	 */
	PUKE("tFlagPKE", "PKE"),
	/**
	 * 临客
	 */
	LINKE("tFlagLK", "LK");

	String key;
	String value;

	private TrainType(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}
}