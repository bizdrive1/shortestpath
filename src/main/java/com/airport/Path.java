package com.airport;

public class Path {
	
	private Node destNode;
	private int travelTime;

	public Path(Node targetNode,
			    int travelTime) {
		this.destNode = targetNode;
		this.travelTime = travelTime;
	}

	public Node getDestNode() {
		return destNode;
	}

	public void setDestNode(Node targetNode) {
		this.destNode = targetNode;
	}

	public int getTravelTime() {
		return travelTime;
	}

	public void setTravelTime(int travelTime) {
		this.travelTime = travelTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destNode == null) ? 0 : destNode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		
		if (getClass() != obj.getClass()) {
			return false;
		}
		Path other = (Path) obj;
		if (destNode == null) {
			if (other.destNode != null) {
				return false;
			}
		} else if (!destNode.equals(other.destNode)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Destination [node=" + destNode + ", travelTime=" + travelTime + "]";
	}

}