package ime.gtfs.business.abstracts;

import java.io.IOException;
import java.util.List;
import ime.gtfs.core.utilities.results.DataResult;
import ime.gtfs.core.utilities.results.Result;
import ime.gtfs.entities.Bus;
import ime.gtfs.entities.Info;


public interface BusService {
	
	DataResult<List<Bus>> getAll();
	Result add(Bus bus);
	DataResult<Info> startBus(int busId) throws IOException;

}
