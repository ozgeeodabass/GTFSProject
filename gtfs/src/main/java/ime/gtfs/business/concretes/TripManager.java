package ime.gtfs.business.concretes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ime.gtfs.business.abstracts.TripService;
import ime.gtfs.dataAccess.abstracts.CalendarRepository;
import ime.gtfs.dataAccess.abstracts.RouteRepository;
import ime.gtfs.dataAccess.abstracts.TripRepository;
import ime.gtfs.entities.Trip;

@Service
public class TripManager implements TripService {
	
	private TripRepository tripRepository;
	private RouteRepository routeRepository;
	private CalendarRepository calendarRepository;
	
	@Autowired
	public TripManager(TripRepository tripRepository,RouteRepository routeRepository,CalendarRepository calendarRepository) {
		super();
		this.tripRepository = tripRepository;
		this.routeRepository=routeRepository;
		this.calendarRepository=calendarRepository;
	}

	@Override
	public List<Trip> getAll() {
		List<Trip> trips = tripRepository.findAll();
		List<Trip> tripsResponse = new ArrayList<Trip>();

		for (Trip trip : trips) {
			Trip tripResponseItem = new Trip();
			tripResponseItem.setTripId(trip.getTripId());
			tripResponseItem.setDirectionId(trip.getDirectionId());
			tripResponseItem.setBikesAllowed(trip.getBikesAllowed());
			tripResponseItem.setRoute(trip.getRoute());
			tripResponseItem.setServiceId(trip.getServiceId());
			tripResponseItem.setWheelchairAccessible(trip.getWheelchairAccessible());

			tripsResponse.add(tripResponseItem);
		}

		return tripsResponse;
	}

	@Override
	public String readFromTxtPushToDb(String txtName) throws FileNotFoundException {
		File file = new File(txtName);
		List<String> lines = new ArrayList<String>();

		Scanner scanner = new Scanner(file);

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			lines.add(line);
		}

		scanner.close();

		// get column data from lines
		List<String> dataWithoutFirstLine = new ArrayList<String>();

		for (int i = 1; i <=102; i++) {
			dataWithoutFirstLine.add(lines.get(i));
		}

		// process txt data
		List<Trip> trips = new ArrayList<Trip>();
		for (String line : dataWithoutFirstLine) {
			Trip trip = new Trip();

			String[] fields = line.split(",");
			trip.setTripId(Integer.valueOf(fields[2]));
			trip.setRoute(routeRepository.findById(Integer.valueOf(fields[0])).get());
			trip.setServiceId(calendarRepository.findById(Integer.valueOf(fields[1])).get());
			trip.setDirectionId(Integer.valueOf(fields[3]));
			trip.setWheelchairAccessible(Integer.valueOf(fields[4]));
			trip.setBikesAllowed(Integer.valueOf(fields[5]));

			// add to list
			trips.add(trip);
		}

		// add to db
		for (Trip trip : trips) {
			this.add(trip);

		}
		return "Veritabanına kaydedildi.";
	}

	@Override
	public String add(Trip trip) {
		Trip tripr = new Trip();
		tripr.setTripId(trip.getTripId());
		tripr.setBikesAllowed(trip.getBikesAllowed());
		tripr.setDirectionId(trip.getDirectionId());
		tripr.setRoute(trip.getRoute());
		tripr.setServiceId(trip.getServiceId());
		tripr.setWheelchairAccessible(trip.getWheelchairAccessible());
		this.tripRepository.save(tripr);
		System.out.println("Eklendi");
		return tripr.toString();
	}
	
	

}
