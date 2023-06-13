package ime.gtfs.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ime.gtfs.entities.Bus;
import ime.gtfs.entities.Route;

public interface BusRepository extends JpaRepository<Bus, Integer> {
	List<Bus>findAllByRoute(Route route);
	List<Bus> findAllByRoute_RouteId(int id);

}
