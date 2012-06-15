package org.railways.api.train;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.railways.api.TrainNumber;

/**
 * 列车信息
 * 
 * @author gmz
 * @time 2012-6-9
 */
public class Train implements Serializable {

	private static final long serialVersionUID = 2962999497802072905L;

	private Map<String, Object> value;

	public Train() {
		this.value = new HashMap<String, Object>();
	}

	public int getSeq() {
		return (Integer) value.get("seq");
	}

	public Train setSeq(int seq) {
		value.put("seq", seq);
		return this;
	}

	public TrainNumber getTrainNumber() {
		return (TrainNumber) value.get("trainNumber");
	}

	public Train setTrainNumber(TrainNumber trainNumber) {
		value.put("trainNumber", trainNumber);
		return this;
	}

	public String getType() {
		return (String) value.get("type");
	}

	public Train setType(String type) {
		value.put("type", type);
		return this;
	}

	public String getAirConditioner() {
		return (String) value.get("airConditioner");
	}

	public Train setAirConditioner(String airConditioner) {
		value.put("airConditioner", airConditioner);
		return this;
	}

	public String getDeparture() {
		return (String) value.get("departure");
	}

	public Train setDeparture(String departure) {
		value.put("departure", departure);
		return this;
	}

	public String getDestination() {
		return (String) value.get("destination");
	}

	public Train setDestination(String destination) {
		value.put("destination", destination);
		return this;
	}

	public String getStartTime() {
		return (String) value.get("startTime");
	}

	public Train setStartTime(String startTime) {
		value.put("startTime", startTime);
		return this;
	}

	public String getEndTime() {
		return (String) value.get("endTime");
	}

	public Train setEndTime(String endTime) {
		value.put("endTime", endTime);
		return this;
	}

	public String getDuration() {
		return (String) value.get("duration");
	}

	public Train setDuration(String duration) {
		value.put("duration", duration);
		return this;
	}

	public String getBusinessSeat() {
		return (String) value.get("businessSeat");
	}

	public Train setBusinessSeat(String businessSeat) {
		value.put("businessSeat", businessSeat);
		return this;
	}

	public String getPrincipalSeat() {
		return (String) value.get("principalSeat");
	}

	public Train setPrincipalSeat(String principalSeat) {
		value.put("principalSeat", principalSeat);
		return this;
	}

	public String getFirstSeat() {
		return (String) value.get("firstSeat");
	}

	public Train setFirstSeat(String firstSeat) {
		value.put("firstSeat", firstSeat);
		return this;
	}

	public String getSecondSeat() {
		return (String) value.get("secondSeat");
	}

	public Train setSecondSeat(String secondSeat) {
		value.put("secondSeat", secondSeat);
		return this;
	}

	public String getSeniorSoftSleeper() {
		return (String) value.get("seniorSoftSleeper");
	}

	public Train setSeniorSoftSleeper(String seniorSoftSleeper) {
		value.put("seniorSoftSleeper", seniorSoftSleeper);
		return this;
	}

	public String getSoftSleeper() {
		return (String) value.get("softSleeper");
	}

	public Train setSoftSleeper(String softSleeper) {
		value.put("softSleeper", softSleeper);
		return this;
	}

	public String getHardSleeper() {
		return (String) value.get("hardSleeper");
	}

	public Train setHardSleeper(String hardSleeper) {
		value.put("hardSleeper", hardSleeper);
		return this;
	}

	public String getSoftSeat() {
		return (String) value.get("softSeat");
	}

	public Train setSoftSeat(String softSeat) {
		value.put("softSeat", softSeat);
		return this;
	}

	public String getHardSeat() {
		return (String) value.get("hardSeat");
	}

	public Train setHardSeat(String hardSeat) {
		value.put("hardSeat", hardSeat);
		return this;
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer().append(Train.class.getSimpleName()).append("{");
		buf.append("seq:").append(getSeq()).append(", ");
		buf.append("trainNumber:").append(getTrainNumber()).append(", ");
		buf.append("type:").append(getType()).append(", ");
		buf.append("airConditioner:").append(getAirConditioner()).append(", ");
		buf.append("departure:").append(getDeparture()).append(", ");
		buf.append("destination:").append(getDestination()).append(", ");
		buf.append("startTime:").append(getStartTime()).append(", ");
		buf.append("endTime:").append(getEndTime()).append(", ");
		buf.append("duration:").append(getDuration()).append(", ");
		buf.append("businessSeat:").append(getBusinessSeat()).append(", ");
		buf.append("principalSeat:").append(getPrincipalSeat()).append(", ");
		buf.append("firstSeat:").append(getFirstSeat()).append(", ");
		buf.append("secondSeat:").append(getSecondSeat()).append(", ");
		buf.append("seniorSoftSleeper:").append(getSeniorSoftSleeper()).append(", ");
		buf.append("softSleeper:").append(getSoftSleeper()).append(", ");
		buf.append("hardSleeper:").append(getHardSleeper()).append(", ");
		buf.append("softSeat:").append(getSoftSeat()).append(", ");
		buf.append("hardSeat:").append(getHardSeat());
		buf.append("}");
		return buf.toString();
	}

}
