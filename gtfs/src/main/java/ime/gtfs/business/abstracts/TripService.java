package ime.gtfs.business.abstracts;

import java.io.FileNotFoundException;
import java.util.List;

import ime.gtfs.core.utilities.results.DataResult;
import ime.gtfs.core.utilities.results.Result;
import ime.gtfs.entities.Route;
import ime.gtfs.entities.Trip;

public interface TripService {
	

	DataResult<List<Trip>> getAll();
	Result readFromTxtPushToDb(String txtName)  throws  FileNotFoundException;
	Result add(Trip trip);
	DataResult<List<Trip>> findAllByRoute(Route route);
	


}
