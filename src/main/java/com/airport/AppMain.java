package com.airport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AppMain {
	
	private ConveyorService conveyorService;
	private InputStream in;
	
	
	public AppMain(InputStream in) {
		conveyorService = new ConveyorService();
		this.in = in;
	}
	
	public void process() {

		Map<String, Departure> departures = new HashMap<String, Departure>();
		List<Baggage> baggages = new ArrayList<Baggage>();

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
			int cnt = 0;
			String line = null;

			while ((line = reader.readLine()) != null) {
				if (line.contains("#")) {
					cnt++;
					continue;
				}
				String strs[] = line.split(" ");
				if ((cnt == 1) && (strs.length >= 3)) {
					conveyorService.buildPath(strs[0], strs[1], Integer.parseInt(strs[2]));
				}

				if ((cnt == 2)  && (strs.length >= 4))  {
					Departure departure = new Departure(strs[0], strs[1], strs[2], strs[3]);
					departures.put(strs[0], departure);
				}

				if ((cnt == 3) && (strs.length >= 3)) {
					Baggage baggage = new Baggage(strs[0], strs[1], strs[2]);

					if (!strs[2].equalsIgnoreCase("ARRIVAL")) {
						Departure departure = departures.get(strs[2]);
						departure.addBaggage(baggage);
					}

					baggages.add(baggage);
				}

				if (line.contains("ARRIVAL")) {
					break;
				}
			}

			for (Baggage baggage : baggages) {
				String end = null;

				if (baggage.getFlightId().equalsIgnoreCase("ARRIVAL")) {
					end = "BaggageClaim";
				} else {
					Departure departure = departures.get(baggage.getFlightId());
					end = departure.getFlightGate();
				}

				int totalTime = conveyorService.findPath(baggage.getEntryPoint(), end);
				System.out.println(
						baggage.getBaggageNumber() + " " + conveyorService.showPath(end) + " : " + totalTime);

				Map<String, Node> nodeMap = conveyorService.getNodeMap();
				Collection<Node> values = nodeMap.values();

				for (Node node : values) {
					node.setMinDistance(Integer.MAX_VALUE);
					node.setPrevNode(null);
				}
			}
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputStream in = null;
		if (args.length >= 1) {
			try {
				in = new FileInputStream(new File(args[0]));
			} catch (FileNotFoundException ex) {
				in = System.in;
			}
		} else {
			in = System.in;
		}
		AppMain appMain = new AppMain(in);
		appMain.process();
	}

}
