package ime.gtfs.business.abstracts;

import java.io.FileNotFoundException;
import java.util.List;

import ime.gtfs.entities.Agency;

public interface AgencyService {

	List<Agency> getAll();
	String readFromTxtPushToDb(String txtName)  throws  FileNotFoundException;
	String add(Agency agency);

}
