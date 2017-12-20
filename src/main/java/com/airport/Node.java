package com.airport;

import java.util.ArrayList;
import java.util.List;

public class Node implements Comparable<Node> {

	private String name;
	private List<Path> destinations = new ArrayList<Path>();
	private int minDistance = Integer.MAX_VALUE;
	private Node prevNode;

	public Node(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Path> getDestinations() {
		return destinations;
	}

	public void setDestinations(List<Path> dest) {
		this.destinations = dest;
	}

	public int getMinDistance() {
		return minDistance;
	}

	public void setMinDistance(int minimumDistance) {
		this.minDistance = minimumDistance;
	}

	public Node getPrevNode() {
		return prevNode;
	}

	public void setPrevNode(Node prev) {
		this.prevNode = prev;
	}

	public void addDestination(Path dest) {
		this.destinations.add(dest);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}
		Node other = (Node) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int compareTo(Node o) {
		return Integer.compare(this.minDistance, o.minDistance);
	}

}
