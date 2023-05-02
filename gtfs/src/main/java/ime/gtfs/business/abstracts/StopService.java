package ime.gtfs.business.abstracts;

import java.io.FileNotFoundException;
import java.util.List;

import ime.gtfs.core.utilities.results.DataResult;
import ime.gtfs.core.utilities.results.Result;
import ime.gtfs.entities.Stop;

public interface StopService {


	DataResult<List<Stop>> getAll();
	Result readFromTxtPushToDb(String txtName)  throws  FileNotFoundException;
	Result add(Stop stop);
	DataResult<Stop> getByStopId(int id);
}
