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

import ime.gtfs.business.abstracts.StopService;
import ime.gtfs.entities.Stop;

@RestController
@RequestMapping("/api/stops")
public class StopController {

	private StopService stopService ;
	
	@Autowired
	public StopController(StopService stopService) {
		super();
		this.stopService = stopService;
	}
	

	@GetMapping("/getallStop")
	public List<Stop> getAll() {
		return this.stopService.getAll();
	}
	
	@PostMapping("/addStop")
	public String add(@RequestBody Stop stop) {
		return this.stopService.add(stop);
	}
	
	 @PostMapping("/readfromtxtpushtodbStop/{textName}")
	  @ResponseBody
	  public String readFromTxtPushToDb(@PathVariable(value="textName") String textName) throws FileNotFoundException {
		  return this.stopService.readFromTxtPushToDb(textName); 
		  }

	
}
