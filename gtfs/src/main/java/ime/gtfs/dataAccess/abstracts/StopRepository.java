package ime.gtfs.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import ime.gtfs.entities.Stop;

public interface StopRepository extends JpaRepository<Stop, Integer> {

}
