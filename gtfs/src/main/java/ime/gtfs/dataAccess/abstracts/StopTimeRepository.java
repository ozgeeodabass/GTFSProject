package ime.gtfs.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import ime.gtfs.entities.StopTime;

public interface StopTimeRepository extends JpaRepository<StopTime, Integer> {

	List<StopTime> findAllByTrip_TripId(int id);
	
}
