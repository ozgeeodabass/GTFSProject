package ime.gtfs.business.abstracts;

import java.io.FileNotFoundException;
import java.util.List;
import ime.gtfs.entities.Bus;


public interface BusService {
	
	List<Bus> getAll();
	String readFromTxtPushToDb(String txtName)  throws  FileNotFoundException;
	String add(Bus bus);

}
