package ime.gtfs.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ime.gtfs.entities.Shape;

public interface ShapeRepository extends JpaRepository<Shape, Integer> {
	
	List<Shape> findAllByShapeId(int id);
	

}
