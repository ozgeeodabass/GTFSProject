package ime.gtfs.controllers;

import java.io.IOException;
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
import ime.gtfs.core.utilities.results.DataResult;
import ime.gtfs.core.utilities.results.Result;
import ime.gtfs.entities.Bus;
import ime.gtfs.entities.Info;

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
	public DataResult<List<Bus>> getAll() {
		return this.busService.getAll();
	}

	@PostMapping("/addBus")
	public Result add(@RequestBody Bus bus) {
		return this.busService.add(bus);
	}

	/*
	 * @GetMapping("/busSimulation") public void startSimulation() {
	 * this.busService.startSimulation(); }
	 */
	
	
	@GetMapping("/startBus/{busId}")
	@ResponseBody
	public DataResult<Info> startBus(@PathVariable(value="busId") int busId) throws IOException {
		return this.busService.startBus(busId);
	}
}
