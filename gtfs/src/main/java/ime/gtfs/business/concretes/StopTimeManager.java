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
import ime.gtfs.core.utilities.results.DataResult;
import ime.gtfs.core.utilities.results.Result;
import ime.gtfs.core.utilities.results.SuccessDataResult;
import ime.gtfs.core.utilities.results.SuccessResult;
import ime.gtfs.dataAccess.abstracts.StopRepository;
import ime.gtfs.dataAccess.abstracts.StopTimeRepository;
import ime.gtfs.dataAccess.abstracts.TripRepository;
import ime.gtfs.entities.StopTime;
import ime.gtfs.entities.Trip;

@Service
public class StopTimeManager implements StopTimeService {

	private StopTimeRepository stopTimeRepository;
	private StopRepository stopRepository;
	private TripRepository tripRepository;

	@Autowired
	public StopTimeManager(StopTimeRepository stopTimeRepository, StopRepository stopRepository,
			TripRepository tripRepository) {
		super();
		this.stopTimeRepository = stopTimeRepository;
		this.stopRepository = stopRepository;
		this.tripRepository = tripRepository;
	}

	@Override
	public DataResult<List<StopTime>> getAll() {
		/*
		 * List<StopTime> stopTimes = stopTimeRepository.findAll(); List<StopTime>
		 * stopTimesResponse = new ArrayList<StopTime>();
		 * 
		 * for (StopTime stopTime : stopTimes) { StopTime stopTimeResponseItem = new
		 * StopTime(); stopTimeResponseItem.setStopTimeId(stopTime.getStopTimeId());
		 * stopTimeResponseItem.setStop(stopTime.getStop());
		 * stopTimeResponseItem.setTrip(stopTime.getTrip());
		 * stopTimeResponseItem.setArrivalTime(stopTime.getArrivalTime());
		 * stopTimeResponseItem.setDepartureTime(stopTime.getDepartureTime());
		 * stopTimeResponseItem.setStopSequence(stopTime.getStopSequence());
		 * stopTimeResponseItem.setTimePoint(stopTime.getTimePoint());
		 * 
		 * stopTimesResponse.add(stopTimeResponseItem); }
		 */

		return new SuccessDataResult<List<StopTime>>(this.stopTimeRepository.findAll(),"all stop times returned");
	}

	@Override
	public Result readFromTxtPushToDb(String txtName) throws FileNotFoundException, ParseException {
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

				for (String column : columnNames) {
					switch (column) {
					case "trip_id": {
						int indexOfCol = columnNames.indexOf(column);
						String data = fields[indexOfCol];
						stopTime.setTrip(tripRepository.findById(Integer.valueOf(data)).get());
						break;
					}
					case "arrival_time": {
						int indexOfCol = columnNames.indexOf(column);
						String data = fields[indexOfCol];
						int arrivalTimeHour = Integer.valueOf(data.substring(0, 2));
						if (arrivalTimeHour == 24) {
							arrivalTimeHour = 00;
						}
						int arrivalTimeMinute = Integer.valueOf(data.substring(3, 5));
						int arrivalTimeSecond = Integer.valueOf(data.substring(6));
						LocalTime timeArrival = LocalTime.of(arrivalTimeHour, arrivalTimeMinute, arrivalTimeSecond);
						stopTime.setArrivalTime(timeArrival);
						break;
					}
					case "departure_time": {
						int indexOfCol = columnNames.indexOf(column);
						String data = fields[indexOfCol];
						int departureTimeHour = Integer.valueOf(data.substring(0, 2));
						if (departureTimeHour == 24) {
							departureTimeHour = 00;
						}
						int departureTimeMinute = Integer.valueOf(data.substring(3, 5));
						int departureTimeSecond = Integer.valueOf(data.substring(6));
						LocalTime timeDeparture = LocalTime.of(departureTimeHour, departureTimeMinute,
								departureTimeSecond);
						stopTime.setDepartureTime(timeDeparture);
						break;
					}
					case "stop_id": {
						int indexOfCol = columnNames.indexOf(column);
						String data = fields[indexOfCol];
						stopTime.setStop(stopRepository.findById(Integer.valueOf(data)).get());
						break;
					}
					case "stop_sequence": {
						int indexOfCol = columnNames.indexOf(column);
						String data = fields[indexOfCol];
						stopTime.setStopSequence(Integer.valueOf(data));
						break;
					}
					case "timepoint": {
						int indexOfCol = columnNames.indexOf(column);
						String data = fields[indexOfCol];
						stopTime.setTimePoint(Integer.valueOf(data));
						break;
					}
					case "stop_headsign": {
						break;
					}
					case "pickup_type": {
						break;
					}
					case "drop_off_type": {
						break;
					}
					case "continuous_pickup": {
						break;
					}
					case "continuous_drop_off": {
						break;
					}
					case "shape_dist_traveled": {
						break;
					}
					default:
						throw new IllegalArgumentException("Unexpected value: " + column);
					}
				}

				stopTimes.add(stopTime);
			}

		}
		// add to db
		for (StopTime stopTime : stopTimes) {
			this.add(stopTime);

		}
		return new SuccessResult("Veritabanına Kaydedildi");

	}

	@Override
	public Result add(StopTime stopTime) {

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
		return new SuccessResult(stopTimer.toString()+" eklendi");

	}

	@Override
	public DataResult<List<StopTime>> findAllByTrip_TripId(int id) {
		return new SuccessDataResult<List<StopTime>>(this.stopTimeRepository.findAllByTrip_TripId(id));
				
	}

}
