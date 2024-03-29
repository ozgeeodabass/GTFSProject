package ime.gtfs.controllers;

import java.io.FileNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ime.gtfs.business.abstracts.TripService;
import ime.gtfs.core.utilities.results.DataResult;
import ime.gtfs.core.utilities.results.Result;
import ime.gtfs.entities.Trip;

@RestController
@RequestMapping("/api/trips")
@CrossOrigin
public class TripsController {

	private TripService tripService;

	@Autowired
	public TripsController(TripService tripService) {
		super();
		this.tripService = tripService;
	}

	@GetMapping("/getallTrip")
	public DataResult<List<Trip>> getAll() {
		return this.tripService.getAll();
	}

	@PostMapping("/addTrip")
	public Result add(Trip trip) {
		return this.tripService.add(trip);
	}

	@PostMapping("/readfromtxtpushtodbTrip/{textName}")
	@ResponseBody
	public Result readFromTxtPushToDb(@PathVariable(value = "textName") String textName) throws FileNotFoundException {
		return this.tripService.readFromTxtPushToDb(textName);
	}


}
