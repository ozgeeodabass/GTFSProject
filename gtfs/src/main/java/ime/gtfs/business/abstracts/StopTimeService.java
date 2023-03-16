package ime.gtfs.business.abstracts;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;

import ime.gtfs.entities.StopTime;

public interface StopTimeService {

	List<StopTime> getAll();
	String readFromTxtPushToDb(String txtName)  throws  FileNotFoundException,  ParseException;
	String add(StopTime stopTime);
	List<StopTime> findAllByTrip_TripId(int id);

}
