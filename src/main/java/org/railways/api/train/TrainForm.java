/**
 * 
 */
package org.railways.api.train;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.railways.api.StationType;
import org.railways.api.TrainType;

/**
 * 列车查询表单
 * <p>
 * nmonth1:06 nmonth1_new_value:true nday1:09 nday1_new_value:false
 * startStation:北京 startStation_new_value:false arriveStation:合肥
 * arriveStation_new_value:false rFlag:1 name_ckball:value_ckball tFlagDC:DC
 * tFlagZ:Z tFlagT:T tFlagK:K tFlagPK:PK tFlagPKE:PKE tFlagLK:LK randCode:5
 * 
 * @since 2012-5-30
 * @author gmz
 * 
 */
public class TrainForm implements Serializable {

	private static final long serialVersionUID = -6959746120346730726L;

	private Map<String, String> value;

	public TrainForm() {
		this.value = new HashMap<String, String>();
		this.value.put("fdl", "");
	}

	public Date getDate() {
		String month = value.get("nmonth1"), date = value.get("nday1");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, Integer.parseInt(month));
		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date));
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public TrainForm setDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH) + 1;
		value.put("nmonth1", (month < 10 ? "0" : "") + month);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		value.put("nday1", (day < 10 ? "0" : "") + day);
		// TODO
		value.put("nmonth1_new_value", "true");
		value.put("nday1_new_value", "false");
		return this;
	}

	public String getDeparture() {
		return value.get("startStation");
	}

	public TrainForm setDeparture(String from) {
		value.put("startStation", from);
		value.put("startStation_new_value", "true");
		return this;
	}

	public String getDestination() {
		return value.get("arriveStation");
	}

	public TrainForm setDestination(String to) {
		value.put("arriveStation", to);
		value.put("arriveStation_new_value", "false");
		return this;
	}

	public String getStationType() {
		return value.get("rFlag");
	}

	public TrainForm setStationType(StationType type) {
		value.put("rFlag", type != null ? type.getValue() : StationType.ALL.getValue());
		return this;
	}

	public TrainType[] getTrainType() {
		List<TrainType> types = new ArrayList<TrainType>();
		for (TrainType type : TrainType.values()) {
			if (value.containsKey(type.getKey())) {
				types.add(type);
			}
		}
		return types.toArray(new TrainType[types.size()]);
	}

	public TrainForm setTrainType(TrainType type) {
		if (type == TrainType.ALL) {
			for (TrainType t : TrainType.values()) {
				value.put(t.getKey(), t.getValue());
			}
		} else {
			value.put(type.getKey(), type.getValue());
		}
		return this;
	}

	public String getRandomCode() {
		return value.get("randCode");
	}

	public TrainForm setRandomCode(String code) {
		value.put("randCode", code);
		return this;
	}

	/**
	 * @return 转化为http请求参数，各参数顺序固定
	 */
	public String toParameters() {
		StringBuffer args = new StringBuffer();
		args.append("nmonth1").append("=").append(value.get("nmonth1")).append("&");
		args.append("nmonth1_new_value").append("=").append(value.get("nmonth1_new_value")).append("&");
		args.append("nday1").append("=").append(value.get("nday1")).append("&");
		args.append("nday1_new_value").append("=").append(value.get("nday1_new_value")).append("&");
		args.append("startStation").append("=").append(getDeparture()).append("&");
		args.append("startStation_new_value").append("=").append(value.get("startStation_new_value")).append("&");
		args.append("arriveStation").append("=").append(getDestination()).append("&");
		args.append("arriveStation_new_value").append("=").append(value.get("arriveStation_new_value")).append("&");
		args.append("rFlag").append("=").append(getStationType()).append("&");
		for (TrainType type : TrainType.values()) {
			if (value.containsKey(type.getKey())) {
				args.append(type.getKey()).append("=").append(type.getValue()).append("&");
			}
		}
		args.append("randCode").append("=").append(value.get("randCode"));
		return args.toString();
	}

}
