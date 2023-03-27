package ime.gtfs.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ime.gtfs.business.abstracts.BusService;
import ime.gtfs.dataAccess.abstracts.BusRepository;
import ime.gtfs.dataAccess.abstracts.StopTimeRepository;
import ime.gtfs.dataAccess.abstracts.TripRepository;
import ime.gtfs.entities.Bus;
import ime.gtfs.entities.Route;
import ime.gtfs.entities.Stop;
import ime.gtfs.entities.StopTime;
import ime.gtfs.entities.Trip;

@Service
public class BusManager implements BusService {

	private BusRepository busRepository;
	private TripRepository tripRepository;
	private StopTimeRepository stopTimeRepository;

	@Autowired
	public BusManager(BusRepository busRepository, TripRepository tripRepository,
			StopTimeRepository stopTimeRepository) {
		super();
		this.busRepository = busRepository;
		this.tripRepository = tripRepository;
		this.stopTimeRepository = stopTimeRepository;
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
		busr.setTrips(bus.getTrips());
		this.busRepository.save(busr);
		System.out.println("Eklendi");
		return busr.toString();
	}

	@Override
	public void startSimulation() {
		List<Bus> buses = busRepository.findAll();
		List<List<Trip>> trips = new ArrayList<List<Trip>>();

		for (Bus bus : buses) {
			List<Trip> tripsOfBus = tripRepository.findAllByRoute_RouteId(bus.getRoute().getRouteId());
			trips.add(tripsOfBus);
		}

		for (List<Trip> tripsIn : trips) {
			for (Trip trip : tripsIn) {
				Route routeOfTrip = trip.getRoute();
				for (Bus bus : buses) {
					if (bus.getRoute() == routeOfTrip) {
						List<Trip> tripList = new ArrayList<Trip>();
						tripList.add(trip);
						trip.setBus(bus);
						bus.setTrips(tripList);
						tripRepository.save(trip);
						busRepository.save(bus);
					}
				}
			}
		}

		List<List<StopTime>> stopTimesListsOfBusses = new ArrayList<List<StopTime>>();
		List<Stop> stops = new ArrayList<Stop>();

		for (Bus bus : buses) {
			List<Trip> tripsOfBus = bus.getTrips();
			List<StopTime> stopTimesOfTrip = new ArrayList<StopTime>();
			for (Trip trip : tripsOfBus) {
				stopTimesOfTrip = stopTimeRepository.findAllByTrip_TripId(trip.getTripId());
			}

			stopTimesListsOfBusses.add(stopTimesOfTrip);

			for (List<StopTime> stoptimes : stopTimesListsOfBusses) {
				for (StopTime stopTime : stoptimes) {
					Stop stop = stopTime.getStop();
					stops.add(stop);
				}
			}
		}

		for (Bus bus : buses) {

			System.out.println(bus.getAgency().getAgencyName() + " - " + "Vehicle type: "
					+ bus.getRoute().getRouteType() + " | " + " Bus Route: " + bus.getRoute().getRouteShortName() + " "
					+ bus.getRoute().getRouteLongName());

			//MUST SET TRİP ACCORDİNG TO NEAREST TİME OF TRİP
			for (Trip trip : trips.get(buses.indexOf(bus))) {
				System.out.println("Trip: " + trip.getTripId());
			}

			System.out.println("Stop Times: ");
			List<StopTime> stpTimes = stopTimesListsOfBusses.get(buses.indexOf(bus));
			for (StopTime stopTime : stpTimes) {
				System.out.println("Stop Time Id: " + stopTime.getStopTimeId() + " Arrival Time: "
						+ stopTime.getArrivalTime() + " Departure Time: " + stopTime.getDepartureTime() + " Trip Id: "
						+ stopTime.getTrip().getTripId() + " Stop Name: " + stopTime.getStop().getStopName());
			}
		}

		// start - go - end

	}

	public void startBus(int busId) {
		Bus bus = busRepository.findById(busId).get();
		int routeId = bus.getRoute().getRouteId();
		List<Trip> trips = bus.getTrips();
		List<List<StopTime>> stopTimesOfTrips = new ArrayList<List<StopTime>>();

		for (Trip trip : trips) {
			List<StopTime> stopTimes = trip.getStopTimes();
			stopTimesOfTrips.add(stopTimes);
		}

		System.out.println(bus.getAgency().getAgencyName() + " - " + "Vehicle type: " + bus.getRoute().getRouteType()
				+ " | " + " Bus Route: " + bus.getRoute().getRouteShortName() + " "
				+ bus.getRoute().getRouteLongName());

		System.out.println("Trips: ");
		for (Trip trip : trips) {
			System.out.println("Trip: " + trip.getTripId());
		}

		System.out.println("Stop Times: ");

		for (List<StopTime> stopTimes : stopTimesOfTrips) {
			System.out.println(bus.getRoute().getRouteShortName() + " OTOBÜSÜ İÇİN YENİ TRİP:"
					+ stopTimes.get(0).getTrip().getTripId());
			for (StopTime stopTime : stopTimes) {
				System.out.println("Stop Time Id: " + stopTime.getStopTimeId() + " Arrival Time: "
						+ stopTime.getArrivalTime() + " Departure Time: " + stopTime.getDepartureTime() + " Trip Id: "
						+ stopTime.getTrip().getTripId() + " Stop Name: " + stopTime.getStop().getStopName());
			}

		}

	}

}
