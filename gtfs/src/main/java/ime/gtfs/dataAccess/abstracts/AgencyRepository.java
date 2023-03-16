package ime.gtfs.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import ime.gtfs.entities.Agency;

public interface AgencyRepository extends JpaRepository<Agency, Integer> {


}
