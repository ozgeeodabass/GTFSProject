package ime.gtfs.entities;

import java.util.List;

public class Info {

	private Agency agency;
	private Bus bus;
	private Trip nearestTrip;
	private List<StopTime> stopTimesOfNearestTrip;
	private String remainingTime;
	
	public Info(Agency agency, Bus bus, Trip nearestTrip, List<StopTime> stopTimesOfNearestTrip,String remainingTime) {
		super();
		this.agency = agency;
		this.bus = bus;
		this.nearestTrip = nearestTrip;
		this.stopTimesOfNearestTrip = stopTimesOfNearestTrip;
		this.remainingTime = remainingTime;
	}
	
	public Info() {
		super();
	}

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public Trip getNearestTrip() {
		return nearestTrip;
	}

	public void setNearestTrip(Trip nearestTrip) {
		this.nearestTrip = nearestTrip;
	}

	public List<StopTime> getStopTimesOfNearestTrip() {
		return stopTimesOfNearestTrip;
	}

	public void setStopTimesOfNearestTrip(List<StopTime> stopTimesOfNearestTrip) {
		this.stopTimesOfNearestTrip = stopTimesOfNearestTrip;
	}

	public String getRemainingTime() {
		return remainingTime;
	}

	public void setRemainingTime(String remainingTime) {
		this.remainingTime = remainingTime;
	}
	
	
	
	
}
