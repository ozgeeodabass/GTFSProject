package ime.gtfs.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ime.gtfs.entities.Route;
import ime.gtfs.entities.Trip;

public interface TripRepository extends JpaRepository<Trip, Integer> {
	
	List<Trip> findAllByRoute_RouteId(int id);
	List<Trip> findAllByRoute(Route route);

}
