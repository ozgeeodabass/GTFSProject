package ime.gtfs.controllers;

import java.io.FileNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ime.gtfs.business.abstracts.ShapeService;
import ime.gtfs.core.utilities.results.DataResult;
import ime.gtfs.core.utilities.results.Result;
import ime.gtfs.entities.Shape;

@RestController
@RequestMapping("/api/shapes")
public class ShapeController {

	private ShapeService shapeService;

	@Autowired
	public ShapeController(ShapeService shapeService) {
		super();
		this.shapeService = shapeService;
	}

	@GetMapping("/getallShape")
	public DataResult<List<Shape>> getAll() {
		return this.shapeService.getAll();
	}

	@PostMapping("/addShape")
	public Result add(@RequestBody Shape shape) {
		return this.shapeService.add(shape);
	}

	@PostMapping("/readfromtxtpushtodbShape/{textName}")
	@ResponseBody
	public Result readFromTxtPushToDb(@PathVariable(value = "textName") String textName) throws FileNotFoundException {
		return this.shapeService.readFromTxtPushToDb(textName);
	}
}
