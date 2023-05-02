package ime.gtfs.business.abstracts;

import java.io.FileNotFoundException;
import java.util.List;
import ime.gtfs.core.utilities.results.DataResult;
import ime.gtfs.core.utilities.results.Result;
import ime.gtfs.entities.Agency;

public interface AgencyService {

	DataResult<List<Agency>> getAll();
	Result readFromTxtPushToDb(String txtName)  throws  FileNotFoundException;
	Result add(Agency agency);

}
