/**
 * 
 */
package org.railways.api.ticket;

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
 * 火车票余票查询表单
 * <p>
 * 
 * ictM=2793&fdl=&lx=00&nmonth3=06&nmonth3_new_value=true&nday3=04&
 * nday3_new_value=false
 * &startStation_ticketLeft=53174e13013bc902&startStation_ticketLeft_new_value
 * =true&arriveStation_ticketLeft=5408801a01691bd8&
 * arriveStation_ticketLeft_new_value=true &trainCode=&trainCode_new_value=true
 * &rFlag=1&name_ckball=value_ckball&tFlagDC=DC
 * &tFlagZ=Z&tFlagT=T&tFlagK=K&tFlagPK=PK&tFlagPKE=PKE&tFlagLK=LK&randCode=3
 * 
 * @since 2012-5-30
 * @author gmz
 * 
 */
public class TicketForm implements Serializable {

	private static final long serialVersionUID = -3391812860623773049L;

	/**
	 * 火车票查询类型
	 * 
	 * @since 2012-6-4
	 * @author gmz
	 * 
	 */
	public enum SearchType {
		/**
		 * 普通查询
		 */
		DEFAULT("00"),
		/**
		 * 学生团体查询
		 */
		STUDENT("0X"),
		/**
		 * 农民工团体查询
		 */
		FARMER("1F");
		private String type;

		private SearchType(String type) {
			this.type = type;
		}

		public String getType() {
			return type;
		}

	}

	// /**
	// * ict参数，必填，每天会变，参数名有顺序，但值随机 //星期一 ictM //星期二 ictN 5231 //星期三 ictO 2376
	// * //星期四 ictP //星期五 ictQ 719 //星期六 ictR 6714 1058 //星期日 ictS
	// */
	// public enum ICT {
	// ictM, ictN, ictO, ictP, ictQ, ictR, ictS;
	//
	// public static ICT getCurrent() {
	// int week = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
	// int ordinal = (week + 5) % 7;
	// for (ICT ict : ICT.values()) {
	// if (ict.ordinal() == ordinal) {
	// return ict;
	// }
	// }
	// return null;
	// }
	//
	// }

	private Map<String, String> value;
	private String ictKey, depart, dest;

	public TicketForm() {
		this.value = new HashMap<String, String>();
		this.value.put("fdl", "");
	}

	public String getSearchType() {
		return value.get("lx");
	}

	public TicketForm setSearchType(SearchType type) {
		value.put("lx", type != null ? type.getType() : SearchType.DEFAULT.type);
		return this;
	}

	/**
	 * @return 获取ICT验证码
	 */
	public IctCode getIctCode() {
		return new IctCode(ictKey, value.get(ictKey));
	}

	/**
	 * 设置ICT验证码
	 */
	public TicketForm setIctCode(IctCode ictCode) {
		ictKey = ictCode.getKey();
		value.put(ictKey, ictCode.getValue());
		return this;
	}

	public Date getDate() {
		String month = value.get("nmonth3"), date = value.get("nday3");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date));
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public TicketForm setDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH) + 1;
		value.put("nmonth3", (month < 10 ? "0" : "") + month);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		value.put("nday3", (day < 10 ? "0" : "") + day);
		// TODO
		value.put("nmonth3_new_value", "true");
		value.put("nday3_new_value", "false");
		return this;
	}

	public String getDeparture() {
		// TODO 编码
		return value.get("startStation_ticketLeft");
	}

	public String getStartStation() {
		return depart;
	}

	public TicketForm setDeparture(String from) {
		value.put("startStation_ticketLeft", StationEncoder.encode(from));
		depart = from;
		// TODO
		value.put("startStation_ticketLeft_new_value", "true");
		return this;
	}

	public String getDestination() {
		// TODO 编码
		return value.get("arriveStation_ticketLeft");
	}

	public String getArriveStation() {
		return dest;
	}

	public TicketForm setDestination(String to) {
		value.put("arriveStation_ticketLeft", StationEncoder.encode(to));
		dest = to;
		// TODO
		value.put("arriveStation_ticketLeft_new_value", "false");
		return this;
	}

	public String getTrainCode() {
		String v = value.get("trainCode");
		return v != null ? v : "";
	}

	public TicketForm setTrainCode(String trainCode) {
		value.put("trainCode", trainCode);
		// TODO
		value.put("trainCode_new_value", "true");
		return this;
	}

	public String getStationType() {
		return value.get("rFlag");
	}

	public TicketForm setStationType(StationType type) {
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

	public TicketForm setTrainType(TrainType type) {
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

	public TicketForm setRandomCode(String code) {
		value.put("randCode", code);
		return this;
	}

	/**
	 * @return 转化为http请求参数，各参数顺序固定
	 */
	public String toParameters() {
		StringBuffer args = new StringBuffer();
		IctCode ic = getIctCode();
		args.append(ic.getKey()).append("=").append(ic.getValue()).append("&");
		args.append("fdl").append("=").append(value.get("fdl")).append("&");
		args.append("lx").append("=").append(getSearchType()).append("&");
		args.append("nmonth3").append("=").append(value.get("nmonth3")).append("&");
		args.append("nmonth3_new_value").append("=").append(value.get("nmonth3_new_value")).append("&");
		args.append("nday3").append("=").append(value.get("nday3")).append("&");
		args.append("nday3_new_value").append("=").append(value.get("nday3_new_value")).append("&");
		args.append("startStation_ticketLeft").append("=").append(getDeparture()).append("&");
		args.append("startStation_ticketLeft_new_value").append("=")
				.append(value.get("startStation_ticketLeft_new_value")).append("&");
		args.append("arriveStation_ticketLeft").append("=").append(getDestination()).append("&");
		args.append("arriveStation_ticketLeft_new_value").append("=")
				.append(value.get("arriveStation_ticketLeft_new_value")).append("&");
		args.append("trainCode").append("=").append(getTrainCode()).append("&");
		String isNew = value.get("trainCode_new_value");
		args.append("trainCode_new_value").append("=").append(isNew != null ? isNew : "true").append("&");
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
