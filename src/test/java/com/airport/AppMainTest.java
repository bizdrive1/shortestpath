package com.airport;

import java.io.ByteArrayInputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

import com.airport.AppMain;

public class AppMainTest {

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {

	}
	
	@Test
	public void testAppMain() throws Exception {
		
		StringBuilder sb = new StringBuilder();
		sb.append(buildConveyor());
		sb.append("# Section: Bags\n" + 
				"0001 Concourse_A_Ticketing UA12\n" + 
				"0002 A5 UA17\n" + 
				"0003 A2 UA10\n" + 
				"0004 A8 UA18\n" + 
				"0005 A7 ARRIVAL\n");
		
		AppMain app = new AppMain(new ByteArrayInputStream(sb.toString().getBytes()));
		System.out.println(app.process());
	}
	
	@Test
	public void testAppMainInvalidFlightId() throws Exception {
		
		StringBuilder sb = new StringBuilder();
		sb.append(buildConveyor());
		sb.append("# Section: Bags\n" + 
				"0004 A8 UA9\n" +
				"0005 A7 ARRIVAL\n");
		
		AppMain app = new AppMain(new ByteArrayInputStream(sb.toString().getBytes()));
		String str = app.process();
		assertTrue(str.contains("invalid flight id"));
	}
	
	private String buildConveyor() {
		StringBuilder sb = new StringBuilder();
		sb.append("# Section: Conveyor System\n" + 
				"Concourse_A_Ticketing A5 5\n" + 
				"A5 BaggageClaim 5\n" + 
				"A5 A10 4\n" + 
				"A5 A1 6\n" + 
				"A1 A2 1\n" + 
				"A2 A3 1\n" + 
				"A3 A4 1\n" + 
				"A10 A9 1\n" + 
				"A9 A8 1\n" + 
				"A8 A7 1\n" + 
				"A7 A6 1\n" + 
				"# Section: Departures\n" + 
				"UA10 A1 MIA 08:00\n" + 
				"UA11 A1 LAX 09:00\n" + 
				"UA12 A1 JFK 09:45\n" + 
				"UA13 A2 JFK 08:30\n" + 
				"UA14 A2 JFK 09:45\n" + 
				"UA15 A2 JFK 10:00\n" + 
				"UA16 A3 JFK 09:00\n" + 
				"UA17 A4 MHT 09:15\n" + 
				"UA18 A5 LAX 10:15\n");
		return sb.toString();
	}
}
