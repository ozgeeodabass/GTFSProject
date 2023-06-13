package ime.gtfs.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import ime.gtfs.entities.Stop;

@RestController
@CrossOrigin
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

	@GetMapping("/startBus/{busId}")
	@ResponseBody
	public DataResult<Info> startBus(@PathVariable(value = "busId") int busId) throws IOException {
		return this.busService.startBus(busId);
	}

	@GetMapping("/getByRoute/{routeShortName}")
	public DataResult<List<Bus>> getByRoute(@PathVariable(value="routeShortName") String routeName) {
		return this.busService.getByRoute(routeName);
	}
	
	@GetMapping("/getStopsOfBus/{id}")
	DataResult<List<Stop>> getStopsOfBus(@PathVariable(value="id") int id){
		return this.busService.getStopsOfBus(id);
	}
	
	@GetMapping("/getById/{id}")
	public DataResult<Bus> getById(@PathVariable(value="id") int id) {
		return this.busService.getById(id);
	}
	
	@GetMapping("/getAllByRouteId/{id}")
	public DataResult<List<Bus>> findAllByRouteId(@PathVariable(value="id")int id) {
		return this.busService.findAllByRouteId(id);
	}
	

}
