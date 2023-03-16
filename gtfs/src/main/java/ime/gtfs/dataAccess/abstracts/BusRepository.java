package ime.gtfs.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import ime.gtfs.entities.Bus;

public interface BusRepository extends JpaRepository<Bus, Integer> {

}
