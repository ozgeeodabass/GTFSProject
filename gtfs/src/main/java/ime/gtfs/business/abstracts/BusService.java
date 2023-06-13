package ime.gtfs.business.abstracts;

import java.io.IOException;
import java.util.List;

import ime.gtfs.core.utilities.results.DataResult;
import ime.gtfs.core.utilities.results.Result;
import ime.gtfs.entities.Bus;
import ime.gtfs.entities.Info;
import ime.gtfs.entities.Stop;


public interface BusService {
	
	DataResult<List<Bus>> getAll();
	Result add(Bus bus);
	DataResult<Info> startBus(int busId) throws IOException;
	DataResult<List<Bus>> getByRoute(String name);
	DataResult<List<Stop>> getStopsOfBus(int id);
	DataResult<Bus> getById(int id);
	DataResult<List<Bus>> findAllByRouteId(int id);

}
