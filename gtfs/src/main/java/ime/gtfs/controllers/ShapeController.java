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
	public List<Shape> getAll() {
		return this.shapeService.getAll();
	}

	@PostMapping("/addShape")
	public String add(@RequestBody Shape shape) {
		return this.shapeService.add(shape);
	}

	@PostMapping("/readfromtxtpushtodbShape/{textName}")
	@ResponseBody
	public String readFromTxtPushToDb(@PathVariable(value = "textName") String textName) throws FileNotFoundException {
		return this.shapeService.readFromTxtPushToDb(textName);
	}
}
