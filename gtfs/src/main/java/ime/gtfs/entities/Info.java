package ime.gtfs.entities;

public class Info {

	private String agencyName;
	private String routeShortName;
	private String routeLongName;
	private Trip nearestTrip;
	private StopTime nearestStopTime;
	private String remainingTime;
	private String stop;
	private int stopId;
	private int busId;
	private int wheechairAccessible;
	private int bikesAllowed;
	private String service;
	private int direction;
	
	public Info(String agencyName, String routeShortName, String routeLongName, Trip nearestTrip,
			StopTime nearestStopTime, String remainingTime,int busId,String stop, int stopId) {
		super();
		this.agencyName = agencyName;
		this.routeShortName = routeShortName;
		this.routeLongName = routeLongName;
		this.nearestTrip = nearestTrip;
		this.nearestStopTime = nearestStopTime;
		this.remainingTime = remainingTime;
		this.busId = busId;
		this.stop=stop;
		this.stopId=stopId;
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

	public String getStop() {
		return stop;
	}

	public void setStop(String stop) {
		this.stop = stop;
	}
	

	public int getStopId() {
		return stopId;
	}

	public void setStopId(int stopId) {
		this.stopId = stopId;
	}

	public int isWheechairAccessible() {
		return wheechairAccessible;
	}

	public void setWheechairAccessible(int wheechairAccessible) {
		this.wheechairAccessible = wheechairAccessible;
	}

	public int isBikesAllowed() {
		return bikesAllowed;
	}

	public void setBikesAllowed(int bikesAllowed) {
		this.bikesAllowed = bikesAllowed;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getWheechairAccessible() {
		return wheechairAccessible;
	}

	public int getBikesAllowed() {
		return bikesAllowed;
	}
	
	
	
	
}
