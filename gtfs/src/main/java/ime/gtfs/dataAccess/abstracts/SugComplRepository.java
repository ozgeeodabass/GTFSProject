package ime.gtfs.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ime.gtfs.entities.SugCompl;

public interface SugComplRepository extends JpaRepository<SugCompl, Integer> {
	
	List<SugCompl> findAllByRoute(int route);

}
