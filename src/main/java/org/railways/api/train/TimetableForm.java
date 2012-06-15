package org.railways.api.train;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 列车时刻表查询表单
 * 
 * @author gmz
 * @time 2012-6-9
 */
public class TimetableForm implements Serializable {

	private static final long serialVersionUID = 3513196028014026457L;

	private Map<String, String> value;

	public TimetableForm() {
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

	public TimetableForm setDate(Date date) {
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

	public String getTrainCode() {
		return value.get("trainCode1");
	}

	public TimetableForm setTrainCode(String code) {
		value.put("trainCode1", code);
		value.put("trainCode1_new_value", "true");
		return this;
	}

	public String getRandomCode() {
		return value.get("randCode");
	}

	public TimetableForm setRandomCode(String code) {
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
		args.append("trainCode1").append("=").append(getTrainCode()).append("&");
		args.append("trainCode1_new_value").append("=").append(value.get("trainCode1_new_value")).append("&");
		args.append("randCode").append("=").append(getRandomCode());
		return args.toString();
	}

}
