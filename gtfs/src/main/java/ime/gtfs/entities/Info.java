package ime.gtfs.entities;

import java.util.List;

public class Info {

	private String agencyName;
	private String routeShortName;
	private String routeLongName;
	private int nearestTripId;
	private List<StopTime> stopTimesOfNearestTrip;
	private String remainingTime;
	private int busId;
	
	public Info(String agencyName, String routeShortName, String routeLongName, int nearestTripId,
			List<StopTime> stopTimesOfNearestTrip, String remainingTime,int busId) {
		super();
		this.agencyName = agencyName;
		this.routeShortName = routeShortName;
		this.routeLongName = routeLongName;
		this.nearestTripId = nearestTripId;
		this.stopTimesOfNearestTrip = stopTimesOfNearestTrip;
		this.remainingTime = remainingTime;
		this.busId = busId;
	}

	public Info() {
		super();
	}


	public int getBusId() {
		return busId;
	}

	public void setBusId(int busId) {
		this.busId = busId;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public String getRouteShortName() {
		return routeShortName;
	}

	public void setRouteShortName(String routeShortName) {
		this.routeShortName = routeShortName;
	}

	public String getRouteLongName() {
		return routeLongName;
	}

	public void setRouteLongName(String routeLongName) {
		this.routeLongName = routeLongName;
	}

	public int getNearestTripId() {
		return nearestTripId;
	}

	public void setNearestTripId(int nearestTripId) {
		this.nearestTripId = nearestTripId;
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
	
	public String toString() {
		return this.agencyName+" "+"bus id"+" "+this.busId+this.routeShortName+" "+this.routeLongName+" "+ "{"+"nearest trip id"+" "+ this.nearestTripId+ "}"+"{"+"stop times"+ this.stopTimesOfNearestTrip.toString()+"}"+" "+"remaining time"+ " "+this.remainingTime;
	}
	
	
	
	
}
