package com.airport;

public class Baggage {
	
	private String baggageNumber;
	private String entryPoint;
	private String flightId;

	public Baggage(String baggageNumber,
			       String entryPoint,
			       String flightId) {
		this.baggageNumber = baggageNumber;
		this.entryPoint = entryPoint;
		this.flightId = flightId;
	}

	public String getBaggageNumber() {
		return baggageNumber;
	}

	public void setBaggageNumber(String baggageNumber) {
		this.baggageNumber = baggageNumber;
	}

	public String getEntryPoint() {
		return entryPoint;
	}

	public void setEntryPoint(String entryPoint) {
		this.entryPoint = entryPoint;
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}
}
