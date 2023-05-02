package ime.gtfs.business.concretes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ime.gtfs.business.abstracts.ShapeService;
import ime.gtfs.core.utilities.results.DataResult;
import ime.gtfs.core.utilities.results.Result;
import ime.gtfs.core.utilities.results.SuccessDataResult;
import ime.gtfs.core.utilities.results.SuccessResult;
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
	public DataResult<List<Shape>> getAll() {
		/*
		 * List<Shape> shapes = shapeRepository.findAll(); List<Shape> shapesResponses =
		 * new ArrayList<Shape>();
		 * 
		 * for (Shape shape : shapes) { Shape shapeResponseItem = new Shape();
		 * shapeResponseItem.setShapeId(shape.getShapeId());
		 * shapeResponseItem.setShapePtLat(shape.getShapePtLat());
		 * shapeResponseItem.setShapePtLon(shape.getShapePtLon());
		 * shapeResponseItem.setShapePtSequence(shape.getShapePtSequence());
		 * 
		 * shapesResponses.add(shapeResponseItem); }
		 */

		return new SuccessDataResult<List<Shape>>(this.shapeRepository.findAll(),"all shapes returned");
	}

	@Override
	public Result readFromTxtPushToDb(String txtName) throws FileNotFoundException {
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

		// get columns names
		String columns = lines.get(0);
		List<String> columnNames = new ArrayList<String>();

		for (String col : columns.split(",")) {
			columnNames.add(col);
		}

		// process txt data
		List<Shape> shapes = new ArrayList<Shape>();

		for (String line : dataWithoutFirstLine) {
			Shape shape = new Shape();

			String[] fields = line.split(",");
			
			for (String column : columnNames) {
				switch (column) {
				case "shape_id": {
					int indexOfCol = columnNames.indexOf(column);
					String data = fields[indexOfCol];
					shape.setShapeId(Integer.valueOf(data));
					break;
				}
				case "shape_pt_lat": {
					int indexOfCol = columnNames.indexOf(column);
					String data = fields[indexOfCol];
					shape.setShapePtLat(Double.valueOf(data));
					break;
				}
				case "shape_pt_lon": {
					int indexOfCol = columnNames.indexOf(column);
					String data = fields[indexOfCol];
					shape.setShapePtLon(Double.valueOf(data));
					break;
				}
				case "shape_pt_sequence": {
					int indexOfCol = columnNames.indexOf(column);
					String data = fields[indexOfCol];
					shape.setShapePtSequence(Integer.valueOf(data));
					break;
				}
				case "shape_dist_traveled": {
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + column);
				}
			
			}

			// add to list
			shapes.add(shape);
		}

		// add to db
		for (Shape shape : shapes) {
			this.add(shape);

		}
		return new SuccessResult("Veritabanına Kaydedildi");
	}

	@Override
	public Result add(Shape shape) {
		Shape shaper = new Shape();
		shaper.setShapeId(shape.getShapeId());
		shaper.setShapePtLat(shape.getShapePtLat());
		shaper.setShapePtLon(shape.getShapePtLon());
		shaper.setShapePtSequence(shape.getShapePtSequence());
		this.shapeRepository.save(shaper);
		System.out.println("Eklendi");
		return new SuccessResult(shaper.toString()+" eklendi");
	}

}
