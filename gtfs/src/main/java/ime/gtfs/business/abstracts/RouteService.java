package ime.gtfs.business.abstracts;

import java.io.FileNotFoundException;
import java.util.List;

import ime.gtfs.core.utilities.results.DataResult;
import ime.gtfs.core.utilities.results.Result;
import ime.gtfs.entities.Route;

public interface RouteService {

	DataResult<List<Route>> getAll();
	Result readFromTxtPushToDb(String txtName)  throws  FileNotFoundException;
	Result add(Route route);

}
