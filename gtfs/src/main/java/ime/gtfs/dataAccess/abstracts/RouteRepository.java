package ime.gtfs.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import ime.gtfs.entities.Route;

public interface RouteRepository extends JpaRepository<Route, Integer> {

}
