package ime.gtfs.business.abstracts;

import java.io.IOException;
import java.util.List;

import ime.gtfs.entities.Bus;
import ime.gtfs.entities.Info;


public interface BusService {
	
	List<Bus> getAll();
	String add(Bus bus);
	//void startSimulation();
	Info startBus(int busId) throws IOException;

}
