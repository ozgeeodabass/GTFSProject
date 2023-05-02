package ime.gtfs.business.concretes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ime.gtfs.business.abstracts.StopService;
import ime.gtfs.core.utilities.results.DataResult;
import ime.gtfs.core.utilities.results.Result;
import ime.gtfs.core.utilities.results.SuccessDataResult;
import ime.gtfs.core.utilities.results.SuccessResult;
import ime.gtfs.dataAccess.abstracts.StopRepository;
import ime.gtfs.entities.Stop;

@Service
public class StopManager implements StopService {

	private StopRepository stopRepository;

	@Autowired
	public StopManager(StopRepository stopRepository) {
		super();
		this.stopRepository = stopRepository;
	}

	@Override
	public DataResult<List<Stop>> getAll() {
		/*List<Stop> stops = stopRepository.findAll();
		List<Stop> stopsResponse = new ArrayList<Stop>();

		for (Stop stop : stops) {
			Stop stopResponseItem = new Stop();
			stopResponseItem.setStopId(stop.getStopId());
			stopResponseItem.setStopLat(stop.getStopLat());
			stopResponseItem.setStopLon(stop.getStopLon());
			stopResponseItem.setStopName(stop.getStopName());

			stopsResponse.add(stopResponseItem);
		}*/

		return new SuccessDataResult<List<Stop>>(this.stopRepository.findAll(),"all stops returned");
	}

	@Override
	public Result readFromTxtPushToDb(String txtName) throws FileNotFoundException {
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
		List<Stop> stops = new ArrayList<Stop>();

		for (String line : dataWithoutFirstLine) {
			Stop stop = new Stop();

			String[] fields = line.split(",");

			for (String column : columnNames) {
				switch (column) {
				case "stop_id": {
					int indexOfCol = columnNames.indexOf(column);
					String data = fields[indexOfCol];
					stop.setStopId(Integer.valueOf(data));
					break;
				}
				case "stop_name": {
					int indexOfCol = columnNames.indexOf(column);
					String data = fields[indexOfCol];
					stop.setStopName(data);
					break;
				}
				case "stop_lat": {
					int indexOfCol = columnNames.indexOf(column);
					String data = fields[indexOfCol];
					if(!data.isEmpty()) {
						stop.setStopLat(Double.parseDouble(data));
					}
					
					break;
				}
				case "stop_lon": {
					int indexOfCol = columnNames.indexOf(column);
					String data = fields[indexOfCol];
					if(!data.isEmpty()) {
						stop.setStopLon(Double.parseDouble(data));
					}
					break;
				}
				case "stop_code": {
					break;
				}
				case "stop_desc": {
					break;
				}
				case "zone_id": {
					break;
				}
				case "stop_url": {
					break;
				}
				case "location_type": {
					break;
				}
				case "parent_station": {
					break;
				}
				case "stop_timezone": {
					break;
				}
				case "wheelchair_boarding": {
					break;
				}
				case "level_id": {
					break;
				}
				case "platform_code": {
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + column);
				}
			}

			// add to list
			stops.add(stop);
		}

		Comparator<Stop> locationComparator = new Comparator<Stop>() {
			@Override
			public int compare(Stop location1, Stop location2) {
				int latComparison = Double.compare(location1.getStopLat(), location2.getStopLat());
				if (latComparison != 0) {
					return latComparison;
				}
				return Double.compare(location1.getStopLon(), location2.getStopLon());
			}
		};

		Collections.sort(stops, locationComparator);

		// add to db
		for (Stop stop : stops) {
			this.add(stop);

		}
		return new SuccessResult("Veritabanına Kaydedildi");
	}

	@Override
	public Result add(Stop stop) {
		Stop stopr = new Stop();
		stopr.setStopId(stop.getStopId());
		stopr.setStopLat(stop.getStopLat());
		stopr.setStopLon(stop.getStopLon());
		stopr.setStopName(stop.getStopName());
		this.stopRepository.save(stopr);
		System.out.println("Eklendi");
		return new SuccessResult(stopr.toString()+" eklendi");
	}

	@Override
	public DataResult<Stop> getByStopId(int id) {
		return new SuccessDataResult<Stop>(this.stopRepository.findById(id).get());
				
	}

}
