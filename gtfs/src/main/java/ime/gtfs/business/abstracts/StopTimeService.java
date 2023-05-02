package ime.gtfs.business.abstracts;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;

import ime.gtfs.core.utilities.results.DataResult;
import ime.gtfs.core.utilities.results.Result;
import ime.gtfs.entities.StopTime;

public interface StopTimeService {

	DataResult<List<StopTime>> getAll();
	Result readFromTxtPushToDb(String txtName)  throws  FileNotFoundException,  ParseException;
	Result add(StopTime stopTime);
	DataResult<List<StopTime>> findAllByTrip_TripId(int id);

}
