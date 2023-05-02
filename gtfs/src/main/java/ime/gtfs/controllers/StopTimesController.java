package ime.gtfs.controllers;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ime.gtfs.business.abstracts.StopTimeService;
import ime.gtfs.core.utilities.results.DataResult;
import ime.gtfs.core.utilities.results.Result;
import ime.gtfs.entities.StopTime;

@RestController
@RequestMapping("/api/stoptimes")
public class StopTimesController {
	

	private StopTimeService stopTimeService ;
	
	@Autowired
	public StopTimesController(StopTimeService stopTimeService) {
		super();
		this.stopTimeService = stopTimeService;
	}
	

	@GetMapping("/getallStopTime")
	public DataResult<List<StopTime>> getAll() {
		return this.stopTimeService.getAll();
	}
	
	@PostMapping("/addStopTime")
	public Result add(@RequestBody StopTime stopTime) {
		return this.stopTimeService.add(stopTime);
	}
	
	 @PostMapping("/readfromtxtpushtodbStopTime/{textName}")
	  @ResponseBody
	  public Result readFromTxtPushToDb(@PathVariable(value="textName") String textName) throws FileNotFoundException, ParseException {
		  return this.stopTimeService.readFromTxtPushToDb(textName); 
		  }


}
