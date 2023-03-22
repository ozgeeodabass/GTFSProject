package ime.gtfs.business.concretes;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ime.gtfs.business.abstracts.StopTimeService;
import ime.gtfs.dataAccess.abstracts.RouteRepository;
import ime.gtfs.dataAccess.abstracts.StopRepository;
import ime.gtfs.dataAccess.abstracts.StopTimeRepository;
import ime.gtfs.dataAccess.abstracts.TripRepository;
import ime.gtfs.entities.Route;
import ime.gtfs.entities.StopTime;
import ime.gtfs.entities.Trip;

@Service
public class StopTimeManager implements StopTimeService {

	private StopTimeRepository stopTimeRepository;
	private StopRepository stopRepository;
	private TripRepository tripRepository;
	private RouteRepository routeRepository;

	
	@Autowired
	public StopTimeManager(StopTimeRepository stopTimeRepository, StopRepository stopRepository,
			TripRepository tripRepository, RouteRepository routeRepository) {
		super();
		this.stopTimeRepository = stopTimeRepository;
		this.stopRepository = stopRepository;
		this.tripRepository = tripRepository;
		this.routeRepository = routeRepository;
	}

	@Override
	public List<StopTime> getAll() {
		List<StopTime> stopTimes = stopTimeRepository.findAll();
		List<StopTime> stopTimesResponse = new ArrayList<StopTime>();

		for (StopTime stopTime : stopTimes) {
			StopTime stopTimeResponseItem = new StopTime();
			stopTimeResponseItem.setStopTimeId(stopTime.getStopTimeId());
			stopTimeResponseItem.setStop(stopTime.getStop());
			stopTimeResponseItem.setTrip(stopTime.getTrip());
			stopTimeResponseItem.setArrivalTime(stopTime.getArrivalTime());
			stopTimeResponseItem.setDepartureTime(stopTime.getDepartureTime());
			stopTimeResponseItem.setStopSequence(stopTime.getStopSequence());
			stopTimeResponseItem.setTimePoint(stopTime.getTimePoint());

			stopTimesResponse.add(stopTimeResponseItem);
		}

		return stopTimesResponse;
	}

	
	@Override
	public String readFromTxtPushToDb(String txtName) throws FileNotFoundException, ParseException {
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

		// process txt data
		List<StopTime> stopTimes = new ArrayList<StopTime>();

		List<Trip> trips = tripRepository.findAll();
		List<Integer> idsOfTrips = new ArrayList<Integer>();
		for (Trip trip : trips) {
			idsOfTrips.add(trip.getTripId());
		}

		for (String line : dataWithoutFirstLine) {
			StopTime stopTime = new StopTime();

			String[] fields = line.split(",");

			if (idsOfTrips.contains(Integer.valueOf(fields[0]))) {
				
				stopTime.setStop(stopRepository.findById(Integer.valueOf(fields[3])).get());
				stopTime.setTrip(tripRepository.findById(Integer.valueOf(fields[0])).get()); 
				
				/*
				 * String arrivalTimeString = fields[1]; LocalTime arrivalTime =
				 * LocalTime.parse(arrivalTimeString, DateTimeFormatter.ISO_LOCAL_TIME);
				 * 
				 * String departureTimeString = fields[2]; LocalTime departureTime =
				 * LocalTime.parse(departureTimeString, DateTimeFormatter.ISO_LOCAL_TIME);
				 */
				
				int arrivalTimeHour = Integer.valueOf(fields[1].substring(0,2));
				if (arrivalTimeHour==24) {
					arrivalTimeHour=00;
				}
				int arrivalTimeMinute = Integer.valueOf(fields[1].substring(3,5));
				int arrivalTimeSecond = Integer.valueOf(fields[1].substring(6));
				LocalTime timeArrival = LocalTime.of(arrivalTimeHour,arrivalTimeMinute,arrivalTimeSecond);
				
				int departureTimeHour = Integer.valueOf(fields[2].substring(0,2));
				if (departureTimeHour==24) {
					departureTimeHour=00;
				}
				int departureTimeMinute = Integer.valueOf(fields[2].substring(3,5));
				int departureTimeSecond = Integer.valueOf(fields[2].substring(6));
				LocalTime timeDeparture = LocalTime.of(departureTimeHour,departureTimeMinute,departureTimeSecond);
				
				stopTime.setArrivalTime(timeArrival);
				stopTime.setDepartureTime(timeDeparture);
				stopTime.setStopSequence(Integer.valueOf(fields[4]));
				stopTime.setTimePoint(Integer.valueOf(fields[5]));

				// add to list
				stopTimes.add(stopTime);
			}

		}
		// add to db
		for (StopTime stopTime : stopTimes) {
			this.add(stopTime);

		}
		return "Veritabanına kaydedildi." ;
		
	}

	@Override
	public String add(StopTime stopTime) {

		StopTime stopTimer = new StopTime();
		stopTimer.setStopTimeId(stopTime.getStopTimeId());
		stopTimer.setStop(stopTime.getStop());
		stopTimer.setTrip(stopTime.getTrip());
		stopTimer.setArrivalTime(stopTime.getArrivalTime());
		stopTimer.setDepartureTime(stopTime.getDepartureTime());
		stopTimer.setStopSequence(stopTime.getStopSequence());
		stopTimer.setTimePoint(stopTime.getTimePoint());
		this.stopTimeRepository.save(stopTimer);
		System.out.println("Eklendi");
		return stopTimer.toString();

	}

	@Override
	public List<StopTime> findAllByTrip_TripId(int id) {
		return this.stopTimeRepository.findAllByTrip_TripId(id);
	}

}
