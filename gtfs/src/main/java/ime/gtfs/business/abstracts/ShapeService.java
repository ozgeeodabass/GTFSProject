package ime.gtfs.business.abstracts;

import java.io.FileNotFoundException;
import java.util.List;
import ime.gtfs.entities.Shape;

public interface ShapeService {
	
	List<Shape> getAll();
	String readFromTxtPushToDb(String txtName)  throws  FileNotFoundException;
	String add(Shape shape);
	//Shape findByShapeId(int id);

}
