package ime.gtfs.business.concretes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import ime.gtfs.business.abstracts.BusService;
import ime.gtfs.dataAccess.abstracts.BusRepository;
import ime.gtfs.dataAccess.abstracts.TripRepository;
import ime.gtfs.entities.Bus;
import ime.gtfs.entities.Info;
import ime.gtfs.entities.Route;
import ime.gtfs.entities.StopTime;
import ime.gtfs.entities.Trip;

@Service
public class BusManager implements BusService {

	private BusRepository busRepository;
	private TripRepository tripRepository;

	@Autowired
	public BusManager(BusRepository busRepository, TripRepository tripRepository) {
		super();
		this.busRepository = busRepository;
		this.tripRepository = tripRepository;
	}

	@Override
	public List<Bus> getAll() {
		List<Bus> buses = busRepository.findAll();
		List<Bus> busesResponse = new ArrayList<Bus>();

		for (Bus bus : buses) {
			Bus busResponseItem = new Bus();
			busResponseItem.setBusId(bus.getBusId());
			busResponseItem.setRoute(bus.getRoute());

			busesResponse.add(busResponseItem);
		}

		return busesResponse;
	}

	@Override
	public String add(Bus bus) {
		Bus busr = new Bus();
		busr.setBusId(bus.getBusId());
		busr.setRoute(bus.getRoute());
		busr.setAgency(bus.getAgency());
		this.busRepository.save(busr);
		System.out.println("Eklendi");
		return busr.toString();
	}

	/*
	 * @Override public void startSimulation() { List<Bus> buses =
	 * busRepository.findAll(); List<List<Trip>> trips = new
	 * ArrayList<List<Trip>>();
	 * 
	 * for (Bus bus : buses) { List<Trip> tripsOfBus =
	 * tripRepository.findAllByRoute_RouteId(bus.getRoute().getRouteId());
	 * trips.add(tripsOfBus); }
	 * 
	 * for (List<Trip> tripsIn : trips) { for (Trip trip : tripsIn) { Route
	 * routeOfTrip = trip.getRoute(); for (Bus bus : buses) { if (bus.getRoute() ==
	 * routeOfTrip) { List<Trip> tripList = new ArrayList<Trip>();
	 * tripList.add(trip); trip.setBus(bus); bus.setTrips(tripList);
	 * tripRepository.save(trip); busRepository.save(bus); } } } }
	 * 
	 * List<List<StopTime>> stopTimesListsOfBusses = new
	 * ArrayList<List<StopTime>>(); List<Stop> stops = new ArrayList<Stop>();
	 * 
	 * for (Bus bus : buses) { List<Trip> tripsOfBus = bus.getTrips();
	 * List<StopTime> stopTimesOfTrip = new ArrayList<StopTime>(); for (Trip trip :
	 * tripsOfBus) { stopTimesOfTrip =
	 * stopTimeRepository.findAllByTrip_TripId(trip.getTripId()); }
	 * 
	 * stopTimesListsOfBusses.add(stopTimesOfTrip);
	 * 
	 * for (List<StopTime> stoptimes : stopTimesListsOfBusses) { for (StopTime
	 * stopTime : stoptimes) { Stop stop = stopTime.getStop(); stops.add(stop); } }
	 * }
	 * 
	 * for (Bus bus : buses) {
	 * 
	 * System.out.println(bus.getAgency().getAgencyName() + " - " + "Vehicle type: "
	 * + bus.getRoute().getRouteType() + " | " + " Bus Route: " +
	 * bus.getRoute().getRouteShortName() + " " +
	 * bus.getRoute().getRouteLongName());
	 * 
	 * // MUST SET TRİP ACCORDİNG TO NEAREST TİME OF TRİP for (Trip trip :
	 * trips.get(buses.indexOf(bus))) { System.out.println("Trip: " +
	 * trip.getTripId()); }
	 * 
	 * System.out.println("Stop Times: "); List<StopTime> stpTimes =
	 * stopTimesListsOfBusses.get(buses.indexOf(bus)); for (StopTime stopTime :
	 * stpTimes) { System.out.println("Stop Time Id: " + stopTime.getStopTimeId() +
	 * " Arrival Time: " + stopTime.getArrivalTime() + " Departure Time: " +
	 * stopTime.getDepartureTime() + " Trip Id: " + stopTime.getTrip().getTripId() +
	 * " Stop Name: " + stopTime.getStop().getStopName()); } }
	 * 
	 * // start - go - end
	 * 
	 * }
	 */

	public Info startBus(int busId) throws IOException {

		// idye göre seçilen bus
		Bus bus = busRepository.findById(busId).get();
		// route of the bus
		Route route = bus.getRoute();
		// create info objesi
		Info infos = new Info();

		// trips of the route of bus
		List<Trip> tripsOfRoute = tripRepository.findAllByRoute(route);

		// tüm tipler geliyor: doğru
		for (Trip trip : tripsOfRoute) {
			System.out.println("Trip: " + trip.getTripId());
		}

		// triplerin tüm stoptimeları
		List<StopTime> stopTimes = new ArrayList<StopTime>();

		// stop times of each trip of the route
		for (Trip trip : tripsOfRoute) {
			List<StopTime> stopTimesOfTrip = trip.getStopTimes();

			for (StopTime stopTime : stopTimesOfTrip) {
				stopTimes.add(stopTime);

			}

		}

		// true: all stopTimes fetched
		for (StopTime stopTime : stopTimes) {
			System.out.println("Stop Time: " + stopTime.getStopTimeId());
		}

		// set the nearest trip according to the current time

		Trip nearestTrip = new Trip();

		List<Duration> timeDifferences = new ArrayList<Duration>();
		LocalTime currentTime = LocalTime.now();

		// karşılaştırma için
		StopTime stopTimeOfNearestTrip = new StopTime();

		List<StopTime> stopTimesOfNearestTrip = new ArrayList<StopTime>();

		// trip bulunmadığı zaman sorun çıkarabilir
		int index = -1;

		for (StopTime stopTime : stopTimes) {
			if (stopTime.getDepartureTime().isAfter(currentTime)) {
				Duration timeDifference = Duration.between(currentTime, stopTime.getDepartureTime());
				timeDifferences.add(timeDifference);

				index = stopTimes.indexOf(stopTime);

			}

		}

		long minDiff = -1;
		if (!timeDifferences.isEmpty()) {
			minDiff = Collections.min(timeDifferences).toMinutes();

			int tripId = stopTimes.get(index).getTrip().getTripId();

			for (StopTime stopTime : stopTimes) {
				if (stopTime.getTrip().getTripId() == tripId && stopTime.getDepartureTime().isAfter(currentTime)) {
					stopTimesOfNearestTrip.add(stopTime);
				}

			}

		} else if (timeDifferences.isEmpty()) {
			stopTimeOfNearestTrip = null;
		}

		System.out.println("OTOBÜS BİLGİLERİ: " + bus.getBusId());
		System.out.println(bus.getAgency().getAgencyName() + " - " + "Vehicle type: " + bus.getRoute().getRouteType()
				+ " | " + " Bus Route: " + bus.getRoute().getRouteShortName() + " "
				+ bus.getRoute().getRouteLongName());

		if (minDiff == -1) {
			System.out.println("YAKLAŞAN BİR TRIP YOK");
		} else {
			nearestTrip = stopTimesOfNearestTrip.get(0).getTrip();

			// en yakın trip bilgileri
			System.out.println(bus.getRoute().getRouteShortName() + " Otobüsü için " + nearestTrip.getTripId()
					+ " Numaralı Trip Bilgileri: ");
			System.out.println("Stop Times: ");

			for (StopTime stopTime : stopTimes) {
				if (stopTime.getTrip().equals(nearestTrip)) {
					System.out.println("Stop Time: " + stopTime.getStopTimeId() + " Arrival Time: "
							+ stopTime.getArrivalTime() + " Departure Time: " + stopTime.getDepartureTime()
							+ " Stop Name: " + stopTime.getStop().getStopName());

				}
			}

			// SAAT HESAPLAMALARI

			switch ((59 >= minDiff) ? 0 : (59 < minDiff) ? 1 : 2) {
			case 0: {
				Duration remainingTime = Duration.ofMinutes(minDiff);
				String remainingTimeString = remainingTime.toMinutes() + " dakika";
				System.out.println(remainingTimeString + " kaldı");
				infos.setRemainingTime(remainingTimeString);

			}
			case 1: {
				long remainingHour = TimeUnit.MINUTES.toHours(minDiff);
				long remainingMinutes = minDiff - remainingHour * 60;
				// LocalTime remainingTime =
				// LocalTime.of(Integer.valueOf(String.valueOf(remainingHour)),
				// Integer.valueOf(String.valueOf(remainingMinutes)));
				Duration remainingTime = Duration.ofHours(remainingHour).plusMinutes(remainingMinutes);
				String remaningTimeString = remainingTime.toHours() + " saat " + (remainingTime.toMinutes()) % 60
						+ " dakika";

				infos.setRemainingTime(remaningTimeString);
				System.out.println(remaningTimeString + " kaldı");

			}
			case 2: {
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + Integer.valueOf(String.valueOf(minDiff)));
			}

		}

		/*
		 * infos.setAgency(bus.getAgency()); infos.setBus(bus);
		 * infos.setNearestTrip(nearestTrip);
		 * infos.setStopTimesOfNearestTrip(stopTimesOfNearestTrip);
		 */

		infos.setAgencyName(bus.getAgency().getAgencyName());
		infos.setRouteShortName(bus.getRoute().getRouteShortName());
		infos.setRouteLongName(bus.getRoute().getRouteLongName());
		infos.setNearestTripId(nearestTrip.getTripId());
		infos.setStopTimesOfNearestTrip(stopTimesOfNearestTrip);
		infos.setBusId(bus.getBusId());

		writeToTheTxt(infos);

		return infos;

	}

	public void writeToTheTxt(Info info) throws JsonProcessingException {

		String fileName = "infos.txt";
		File file = new File(fileName);

		JSONObject jsonObj = new JSONObject();
		jsonObj.append("agency", info.getAgencyName());
		jsonObj.append("bus", info.getBusId());
		jsonObj.append("stop times", info.getStopTimesOfNearestTrip().toString());
		jsonObj.append("nearest trip id", info.getNearestTripId());
		jsonObj.append("route", info.getRouteShortName() + " " + info.getRouteLongName());

		if (info.getRemainingTime() == null) {
			info.setRemainingTime("yaklaşan trip yok");
			jsonObj.append("remaining time", info.getRemainingTime().toString());
		} else if (info.getRemainingTime() != null) {
			jsonObj.append("remaining time", info.getRemainingTime().toString());
		}

		try {
			// Dosya yoksa oluştur
			if (!file.exists()) {
				file.createNewFile();
				FileWriter wr = new FileWriter(file);

				wr.write(jsonObj.toString());
				wr.close();

			} else if (file.exists() == true) {
				// Dosyanın içindeki bilgileri sil
				FileWriter wr = new FileWriter(file);

				wr.write("");
				wr.write(jsonObj.toString());

				wr.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
