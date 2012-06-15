/**
 * 
 */
package org.railways.api.ticket;

import java.io.Serializable;

import org.railways.api.TrainNumber;

/**
 * 余票信息
 * 
 * @since 2012-6-6
 * @author gmz
 * 
 */
public class Ticket implements Serializable {

	private static final long serialVersionUID = -4429172143704289977L;

	private int seq;
	private TrainNumber trainNumber;
	private String departure, destination;
	private String startTime, endTime, duration;
	private String businessSeat, principalSeat, firstSeat, secondSeat, seniorSoftSleeper, softSleeper, hardSleeper,
			softSeat, hardSeat, noSeat;
	private String type;

	public Ticket() {
	}

	public int getSeq() {
		return seq;
	}

	public Ticket setSeq(int seq) {
		this.seq = seq;
		return this;
	}

	public TrainNumber getTrainNumber() {
		return trainNumber;
	}

	public Ticket setTrainNumber(TrainNumber trainNumber) {
		this.trainNumber = trainNumber;
		return this;
	}

	public String getDeparture() {
		return departure;
	}

	public Ticket setDeparture(String departure) {
		this.departure = departure;
		return this;
	}

	public String getDestination() {
		return destination;
	}

	public Ticket setDestination(String destination) {
		this.destination = destination;
		return this;
	}

	public String getStartTime() {
		return startTime;
	}

	public Ticket setStartTime(String startTime) {
		this.startTime = startTime;
		return this;
	}

	public String getEndTime() {
		return endTime;
	}

	public Ticket setEndTime(String endTime) {
		this.endTime = endTime;
		return this;
	}

	public String getDuration() {
		return duration;
	}

	public Ticket setDuration(String duration) {
		this.duration = duration;
		return this;
	}

	public String getBusinessSeat() {
		return businessSeat;
	}

	public Ticket setBusinessSeat(String businessSeat) {
		this.businessSeat = businessSeat;
		return this;
	}

	public String getPrincipalSeat() {
		return principalSeat;
	}

	public Ticket setPrincipalSeat(String principalSeat) {
		this.principalSeat = principalSeat;
		return this;
	}

	public String getFirstSeat() {
		return firstSeat;
	}

	public Ticket setFirstSeat(String firstSeat) {
		this.firstSeat = firstSeat;
		return this;
	}

	public String getSecondSeat() {
		return secondSeat;
	}

	public Ticket setSecondSeat(String secondSeat) {
		this.secondSeat = secondSeat;
		return this;
	}

	public String getSeniorSoftSleeper() {
		return seniorSoftSleeper;
	}

	public Ticket setSeniorSoftSleeper(String seniorSoftSleeper) {
		this.seniorSoftSleeper = seniorSoftSleeper;
		return this;
	}

	public String getSoftSleeper() {
		return softSleeper;
	}

	public Ticket setSoftSleeper(String softSleeper) {
		this.softSleeper = softSleeper;
		return this;
	}

	public String getHardSleeper() {
		return hardSleeper;
	}

	public Ticket setHardSleeper(String hardSleeper) {
		this.hardSleeper = hardSleeper;
		return this;
	}

	public String getSoftSeat() {
		return softSeat;
	}

	public Ticket setSoftSeat(String softSeat) {
		this.softSeat = softSeat;
		return this;
	}

	public String getHardSeat() {
		return hardSeat;
	}

	public Ticket setHardSeat(String hardSeat) {
		this.hardSeat = hardSeat;
		return this;
	}

	public String getNoSeat() {
		return noSeat;
	}

	public Ticket setNoSeat(String noSeat) {
		this.noSeat = noSeat;
		return this;
	}

	public String getType() {
		return type;
	}

	public Ticket setType(String type) {
		this.type = type;
		return this;
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer().append(Ticket.class.getSimpleName()).append("{");
		buf.append("seq:").append(getSeq()).append(", ");
		buf.append("trainNumber:").append(getTrainNumber()).append(", ");
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
		buf.append("hardSeat:").append(getHardSeat()).append(", ");
		buf.append("noSeat:").append(getNoSeat()).append(", ");
		buf.append("type:").append(getType());
		buf.append("}");
		return buf.toString();
	}

}
