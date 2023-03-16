package ime.gtfs.business.abstracts;

import java.io.FileNotFoundException;
import java.util.List;

import ime.gtfs.entities.Stop;

public interface StopService {


	List<Stop> getAll();
	String readFromTxtPushToDb(String txtName)  throws  FileNotFoundException;
	String add(Stop stop);
	Stop getByStopId(int id);
}
