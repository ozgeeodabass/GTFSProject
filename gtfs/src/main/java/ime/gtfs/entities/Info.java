package ime.gtfs.entities;

public class Info {

	private String agencyName;
	private String routeShortName;
	private String routeLongName;
	private Trip nearestTrip;
	private StopTime nearestStopTime;
	private String remainingTime;
	private int busId;
	
	public Info(String agencyName, String routeShortName, String routeLongName, Trip nearestTrip,
			StopTime nearestStopTime, String remainingTime,int busId) {
		super();
		this.agencyName = agencyName;
		this.routeShortName = routeShortName;
		this.routeLongName = routeLongName;
		this.nearestTrip = nearestTrip;
		this.nearestStopTime = nearestStopTime;
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

	public Trip getNearestTrip() {
		return nearestTrip;
	}

	public void setNearestTrip(Trip nearestTrip) {
		this.nearestTrip = nearestTrip;
	}

	public StopTime getNearestStopTime() {
		return nearestStopTime;
	}

	public void setNearestStopTime(StopTime nearestStopTime) {
		this.nearestStopTime = nearestStopTime;
	}

	public String getRemainingTime() {
		return remainingTime;
	}

	public void setRemainingTime(String remainingTime) {
		this.remainingTime = remainingTime;
	}
	
	public String toString() {
		return this.agencyName+" "+"bus id"+" "+this.busId+this.routeShortName+" "+this.routeLongName+" "+ "{"+"nearest trip"+" "+ this.nearestTrip+ "}"+"{"+"stop times"+ this.nearestStopTime.toString()+"}"+" "+"remaining time"+ " "+this.remainingTime;
	}
	
	
	
	
}
