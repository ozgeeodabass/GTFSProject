package ime.gtfs.business.concretes;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

		Bus bus = busRepository.findById(busId).get();
		Route route = bus.getRoute();
		Info infos = new Info();

		// trips of the route of bus
		List<Trip> tripsOfRoute = tripRepository.findAllByRoute(route);
		List<StopTime> stopTimes = new ArrayList<StopTime>();

		// stop times of each trip of the route
		for (Trip trip : tripsOfRoute) {
			List<StopTime> stopTimesOfTrip = trip.getStopTimes();

			for (StopTime stopTime : stopTimesOfTrip) {
				stopTimes.add(stopTime);
				
				System.out.println(stopTime.getStopTimeId());

			}

		}
		
		
		System.out.println("OTOBÜS BİLGİLERİ: " + bus.getBusId());
		System.out.println(bus.getAgency().getAgencyName() + " - " + "Vehicle type: " + bus.getRoute().getRouteType()
				+ " | " + " Bus Route: " + bus.getRoute().getRouteShortName() + " "
				+ bus.getRoute().getRouteLongName());

		// saate göre en yakın tripi seçtir

		Trip nearestTrip = new Trip();

		List<Duration> timeDiffs = new ArrayList<Duration>();
		LocalTime now = LocalTime.now();
		// karşılaştırma için
		StopTime stopTimeOfNearestTrip = new StopTime();

		List<StopTime> stopTimesOfNearestTrip = new ArrayList<StopTime>();

		for (StopTime stopTime : stopTimes) {
			if (stopTime.getDepartureTime().isAfter(now)) {
				Duration timeDiff = Duration.between(now, stopTime.getDepartureTime());
				timeDiffs.add(timeDiff);
				stopTimesOfNearestTrip.add(stopTime);
			}
			
		}

		long minDiff = -1;
		if (!timeDiffs.isEmpty()) {
			minDiff = Collections.min(timeDiffs).toMinutes();

		}

		
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
				String remainingTimeString = remainingTime + " dakika";
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
				String remaningTimeString = remainingTime.toHours()+" saat "+(remainingTime.toMinutes())%60+ " dakika";
				
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
		infos.setAgency(bus.getAgency());
		infos.setBus(bus);
		infos.setNearestTrip(nearestTrip);
		infos.setStopTimesOfNearestTrip(stopTimesOfNearestTrip);
		
		

		return infos;

	}

}
