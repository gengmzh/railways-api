/**
 * 
 */
package org.railways.api;

import java.io.Serializable;

/**
 * 火车车次
 * 
 * @since 2012-6-6
 * @author gmz
 * 
 */
public class TrainNumber implements Serializable {

	private static final long serialVersionUID = -5751597655106908269L;

	private String code;
	private String departure, destination;

	public TrainNumber(String code, String departure, String destination) {
		super();
		this.code = code;
		this.departure = departure;
		this.destination = destination;
	}

	public String getCode() {
		return code;
	}

	public String getDeparture() {
		return departure;
	}

	public String getDestination() {
		return destination;
	}

	@Override
	public int hashCode() {
		int hash = 17 * 37 + code.hashCode();
		hash = hash * 37 + departure.hashCode();
		hash = hash * 37 + destination.hashCode();
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof TrainNumber)) {
			return false;
		}
		TrainNumber other = (TrainNumber) obj;
		if ((this.code == null && other.code != null) || (this.code != null && other.code == null)
				|| (!this.code.equals(other.code))) {
			return false;
		}
		if ((this.departure == null && other.departure != null) || (this.departure != null && other.departure == null)
				|| (!this.departure.equals(other.departure))) {
			return false;
		}
		if ((this.destination == null && other.destination != null)
				|| (this.destination != null && other.destination == null)
				|| (!this.destination.equals(other.destination))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return TrainNumber.class.getSimpleName() + "{code:" + getCode() + ", departure:" + getDeparture()
				+ ", destination:" + getDestination() + "}";
	}

}
