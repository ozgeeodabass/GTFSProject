package ime.gtfs.controllers;

import java.io.FileNotFoundException;
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
import ime.gtfs.business.abstracts.StopService;
import ime.gtfs.core.utilities.results.DataResult;
import ime.gtfs.core.utilities.results.Result;
import ime.gtfs.core.utilities.results.SuccessDataResult;
import ime.gtfs.entities.Stop;

@RestController
@CrossOrigin
@RequestMapping("/api/stops")
public class StopController {

	private StopService stopService ;
	
	@Autowired
	public StopController(StopService stopService) {
		super();
		this.stopService = stopService;
	}
	

	@GetMapping("/getallStop")
	public DataResult<List<Stop>> getAll() {
		return this.stopService.getAll();
	}
	
	@PostMapping("/addStop")
	public Result add(@RequestBody Stop stop) {
		return this.stopService.add(stop);
	}
	
	 @PostMapping("/readfromtxtpushtodbStop/{textName}")
	  @ResponseBody
	  public Result readFromTxtPushToDb(@PathVariable(value="textName") String textName) throws FileNotFoundException {
		  return this.stopService.readFromTxtPushToDb(textName); 
		  }

	 
	 @GetMapping("/getAllByRouteId/{id}")
	 public DataResult<List<Stop>> getAllByRouteId(@PathVariable(value="id") int id){
		 return this.stopService.getAllByRouteId(id);
	 }
	 
	 @GetMapping("/getByStopName/{name}")
	 public DataResult<Stop> findByStopName(@PathVariable(value="name") String name) {
			return this.stopService.findByStopName(name);
		}
	 
	 @GetMapping("/getByStopId/{id}")
	 public DataResult<Stop> getByStopId(@PathVariable(value="id") int id) {
		 return this.stopService.getByStopId(id);
	 }
	
}
