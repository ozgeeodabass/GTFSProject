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
import ime.gtfs.dataAccess.abstracts.ShapeRepository;
import ime.gtfs.dataAccess.abstracts.TripRepository;
import ime.gtfs.entities.Route;
import ime.gtfs.entities.Trip;

@Service
public class TripManager implements TripService {

	private TripRepository tripRepository;
	private RouteRepository routeRepository;
	private CalendarRepository calendarRepository;
	private ShapeRepository shapeRepository;

	@Autowired
	public TripManager(TripRepository tripRepository, RouteRepository routeRepository,
			CalendarRepository calendarRepository, ShapeRepository shapeRepository) {
		super();
		this.tripRepository = tripRepository;
		this.routeRepository = routeRepository;
		this.calendarRepository = calendarRepository;
		this.shapeRepository = shapeRepository;
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

		for (int i = 1; i < lines.size(); i++) {
			dataWithoutFirstLine.add(lines.get(i));
		}

		// get columns names
		String columns = lines.get(0);
		List<String> columnNames = new ArrayList<String>();

		for (String col : columns.split(",")) {
			columnNames.add(col);
		}

		// process txt data
		List<Trip> trips = new ArrayList<Trip>();

		for (String line : dataWithoutFirstLine) {
			Trip trip = new Trip();

			String[] fields = line.split(",");

			for (String column : columnNames) {
				switch (column) {
				case "route_id": {
					int indexOfCol = columnNames.indexOf(column);
					String data = fields[indexOfCol];
					trip.setRoute(routeRepository.findById(Integer.valueOf(data)).get());
					break;
				}
				case "service_id": {
					int indexOfCol = columnNames.indexOf(column);
					String data = fields[indexOfCol];
					trip.setServiceId(calendarRepository.findById(Integer.valueOf(data)).get());
					break;
				}
				case "trip_id": {
					int indexOfCol = columnNames.indexOf(column);
					String data = fields[indexOfCol];
					trip.setTripId(Integer.valueOf(data));
					break;
				}
				case "direction_id": {
					int indexOfCol = columnNames.indexOf(column);
					String data = fields[indexOfCol];
					trip.setDirectionId(Integer.valueOf(data));
					break;
				}
				case "shape_id": {
					int indexOfCol = columnNames.indexOf(column);
					String data = fields[indexOfCol];
					trip.setShape(shapeRepository.findById(Integer.valueOf(data)).get());
					break;
				}
				case "wheelchair_accessible": {
					int indexOfCol = columnNames.indexOf(column);
					String data = fields[indexOfCol];
					trip.setWheelchairAccessible(Integer.valueOf(data));
					break;
				}
				case "bikes_allowed": {
					int indexOfCol = columnNames.indexOf(column);
					String data = fields[indexOfCol];
					trip.setBikesAllowed(Integer.valueOf(data));
					break;
				}
				case "trip_headsign": {
					break;
				}
				case "trip_short_name": {
					break;
				}
				case "block_id": {
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + column);
				}
			}

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

	@Override
	public List<Trip> findAllByRoute(Route route) {
		List<Trip> trips = this.tripRepository.findAllByRoute(route);
		return trips;
	}

}
