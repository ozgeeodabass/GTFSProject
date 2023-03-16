package ime.gtfs.business.abstracts;

import java.io.FileNotFoundException;
import java.util.List;
import ime.gtfs.entities.Calendar;

public interface CalendarService {
	
	List<Calendar> getAll();
	String readFromTxtPushToDb(String txtName)  throws  FileNotFoundException;
	String add(Calendar calendar);

}
