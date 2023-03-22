package ime.gtfs.business.abstracts;

import java.util.List;

import ime.gtfs.entities.Bus;


public interface BusService {
	
	List<Bus> getAll();
	String add(Bus bus);
	void startSimulation();
	void startBus(int busId);

}
