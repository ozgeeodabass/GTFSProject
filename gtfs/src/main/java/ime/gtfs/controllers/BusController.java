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
import ime.gtfs.business.abstracts.BusService;
import ime.gtfs.entities.Bus;

@RestController
@RequestMapping("/api/buses")
public class BusController {
	private BusService busService;

	@Autowired
	public BusController(BusService busService) {
		super();
		this.busService = busService;
	}

	@GetMapping("/getallBus")
	public List<Bus> getAll() {
		return this.busService.getAll();
	}

	@PostMapping("/addBus")
	public String add(@RequestBody Bus bus) {
		return this.busService.add(bus);
	}

	@PostMapping("/readfromtxtpushtodbBus/{textName}")
	@ResponseBody
	public String readFromTxtPushToDb(@PathVariable(value = "textName") String textName) throws FileNotFoundException {
		return this.busService.readFromTxtPushToDb(textName);
	}

}
