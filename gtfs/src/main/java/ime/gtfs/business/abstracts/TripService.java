package ime.gtfs.business.abstracts;

import java.io.FileNotFoundException;
import java.util.List;

import ime.gtfs.entities.Route;
import ime.gtfs.entities.Trip;

public interface TripService {
	

	List<Trip> getAll();
	String readFromTxtPushToDb(String txtName)  throws  FileNotFoundException;
	String add(Trip trip);
	List<Trip> findAllByRoute(Route route);
	


}
