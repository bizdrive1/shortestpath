package com.airport;

import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class Departure {
	
	private String flightId;
	private String flightGate;
	private String destination;
	private Date flightTime;
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");

	List<Baggage> baggages = new ArrayList<Baggage>();

	public Departure(String flightId,
				     String flightGate,
				     String destination,
				     String date) {
		this.flightId = flightId;
		this.flightGate = flightGate;
		this.destination = destination;

		try {
			Calendar cal = Calendar.getInstance();
			Calendar cal1 = Calendar.getInstance();

			cal.setTime(dateFormat.parse(date));
			cal1.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY));
			cal1.set(Calendar.MINUTE, cal.get(Calendar.MINUTE));
			cal1.set(Calendar.SECOND, 00);

			this.flightTime = cal1.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public String getFlightGate() {
		return flightGate;
	}

	public void setFlightGate(String flightGate) {
		this.flightGate = flightGate;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public Date getFlightTime() {
		return flightTime;
	}

	public void setFlightTime(Date flightTime) {
		this.flightTime = flightTime;
	}

	public void addBaggage(Baggage baggage) {
		this.baggages.add(baggage);
	}
}
