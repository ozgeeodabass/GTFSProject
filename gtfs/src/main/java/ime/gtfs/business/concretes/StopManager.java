package ime.gtfs.business.concretes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ime.gtfs.business.abstracts.StopService;
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
	public List<Stop> getAll() {
		List<Stop> stops = stopRepository.findAll();
		List<Stop> stopsResponse = new ArrayList<Stop>();

		for (Stop stop : stops) {
			Stop stopResponseItem = new Stop();
			stopResponseItem.setStopId(stop.getStopId());
			stopResponseItem.setStopLat(stop.getStopLat());
			stopResponseItem.setStopLon(stop.getStopLon());
			stopResponseItem.setStopName(stop.getStopName());

			stopsResponse.add(stopResponseItem);
		}

		return stopsResponse;
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

		// process txt data
		List<Stop> stops = new ArrayList<Stop>();
		for (String line : dataWithoutFirstLine) {
			Stop stop = new Stop();

			String[] fields = line.split(",");
			stop.setStopId(Integer.valueOf(fields[0]));
			stop.setStopName(fields[1]);

			double lat =Double.parseDouble((fields[2])) ;
			stop.setStopLat(lat);
			
			double lon =Double.parseDouble((fields[3])) ;
			stop.setStopLon(lon);
			

			// add to list
			stops.add(stop);
		}

		// add to db
		for (Stop stop : stops) {
			this.add(stop);

		}
		return "Veritabanına kaydedildi.";
	}

	@Override
	public String add(Stop stop) {
		Stop stopr = new Stop();
		stopr.setStopId(stop.getStopId());
		stopr.setStopLat(stop.getStopLat());
		stopr.setStopLon(stop.getStopLon());
		stopr.setStopName(stop.getStopName());
		this.stopRepository.save(stopr);
		System.out.println("Eklendi");
		return stopr.toString();
	}

	@Override
	public Stop getByStopId(int id) {
		return this.stopRepository.findById(id).get();
	}

}
