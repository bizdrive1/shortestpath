package com.airport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class ConveyorService {
	private Map<String, Node> nodeMap = new HashMap<String, Node>();

	public Map<String, Node> getNodeMap() {
		return nodeMap;
	}

	public void setNodeMap(Map<String, Node> nodeMap) {
		this.nodeMap = nodeMap;
	}

	public void buildPath(String src,
			              String dest,
			              int travelTime) {
		Node destNode = null;
		Node srcNode = null;

		if (!this.nodeMap.containsKey(src)) {
			srcNode = new Node(src);
			this.nodeMap.put(src, srcNode);
		} else {
			srcNode = this.nodeMap.get(src);
		}

		if (!this.nodeMap.containsKey(dest)) {
			destNode = new Node(dest);
			this.nodeMap.put(dest, destNode);
		} else {
			destNode = this.nodeMap.get(dest);
		}

		Path sp = new Path(destNode, travelTime);
		Path dp = new Path(srcNode, travelTime);

		srcNode.addDestination(sp);
		destNode.addDestination(dp);
	}

	public int findPath(String start,
			               String end) {
		
		Node startNode = nodeMap.get(start);

		Queue<Node> queue = new PriorityQueue<Node>();
		queue.add(startNode);
		startNode.setMinDistance(0);

		while (!queue.isEmpty()) {
			Node node = queue.poll();

			List<Path> destinations = node.getDestinations();
			for (Path path : destinations) {
				Node destNode = path.getDestNode();
				int travelTime = path.getTravelTime();
				int distance = node.getMinDistance() + travelTime;

				if (distance < destNode.getMinDistance()) {
					destNode.setMinDistance(distance);
					destNode.setPrevNode(node);
					queue.add(destNode);
				}
			}
		}

		Node node2 = nodeMap.get(end);

		if (node2 != null) {
			return node2.getMinDistance();
		} else {
			return 0;
		}
	}

	public String showPath(String destNode) {
		
		Node node = this.nodeMap.get(destNode);
		StringBuilder sb = new StringBuilder();

		for (Node n = node; n != null; n = n.getPrevNode()) {
			sb.insert(0, " " + n.getName());
		}

		sb.delete(0, 1);

		return sb.toString();
	}
}
