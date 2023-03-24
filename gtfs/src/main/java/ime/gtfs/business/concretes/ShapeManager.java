package ime.gtfs.business.concretes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ime.gtfs.business.abstracts.ShapeService;
import ime.gtfs.dataAccess.abstracts.ShapeRepository;
import ime.gtfs.entities.Shape;

@Service
public class ShapeManager implements ShapeService {
	
	private ShapeRepository shapeRepository;
	
	@Autowired
	public ShapeManager(ShapeRepository shapeRepository) {
		super();
		this.shapeRepository = shapeRepository;
	}

	@Override
	public List<Shape> getAll() {
		List<Shape> shapes = shapeRepository.findAll();
		List<Shape> shapesResponses = new ArrayList<Shape>();

		for (Shape shape : shapes) {
			Shape shapeResponseItem = new Shape();
			shapeResponseItem.setShapeId(shape.getShapeId());
			shapeResponseItem.setShapePtLat(shape.getShapePtLat());
			shapeResponseItem.setShapePtLon(shape.getShapePtLon());
			shapeResponseItem.setShapePtSequence(shape.getShapePtSequence());

			shapesResponses.add(shapeResponseItem);
		}

		return shapesResponses;
	}

	@Override
	public String readFromTxtPushToDb(String txtName) throws FileNotFoundException {
		File file = new File(txtName);
		List<String> lines = new ArrayList<String>();

		Scanner scanner = new Scanner(file);

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			lines.add(line);
		}

		scanner.close();

		// get column data from lines
		List<String> dataWithoutFirstLine = new ArrayList<String>();

		for (int i = 1; i < lines.size(); i++) {
			dataWithoutFirstLine.add(lines.get(i));
		}

		// process txt data
		List<Shape> shapes = new ArrayList<Shape>();
		for (String line : dataWithoutFirstLine) {
			Shape shape = new Shape();

			String[] fields = line.split(",");
			shape.setShapeId(Integer.valueOf(fields[0]));
			shape.setShapePtLat(Double.valueOf(fields[1]));
			shape.setShapePtLon(Double.valueOf(fields[2]));
			shape.setShapePtSequence(Integer.valueOf(fields[3]));

			// add to list
			shapes.add(shape);
		}

		// add to db
		for (Shape shape : shapes) {
			this.add(shape);

		}
		return "Veritabanına kaydedildi";
	}

	@Override
	public String add(Shape shape) {
		Shape shaper = new Shape();
		shaper.setShapeId(shape.getShapeId());
		shaper.setShapePtLat(shape.getShapePtLat());
		shaper.setShapePtLon(shape.getShapePtLon());
		shaper.setShapePtSequence(shape.getShapePtSequence());
		this.shapeRepository.save(shaper);
		System.out.println("Eklendi");
		return shaper.toString();
	}

}
