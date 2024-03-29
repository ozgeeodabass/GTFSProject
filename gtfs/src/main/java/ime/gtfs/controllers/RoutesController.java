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
import ime.gtfs.business.abstracts.RouteService;
import ime.gtfs.core.utilities.results.DataResult;
import ime.gtfs.core.utilities.results.Result;
import ime.gtfs.entities.Route;

@RestController
@CrossOrigin
@RequestMapping("/api/routes")
public class RoutesController {
	
	private RouteService routeService ;
	
	@Autowired
	public RoutesController(RouteService routeService) {
		super();
		this.routeService = routeService;
	}
	
	@GetMapping("/getallRoute")
	public DataResult<List<Route>> getAll() {
		return this.routeService.getAll();
	}
	
	@PostMapping("/addRoute")
	public Result add(@RequestBody Route route) {
		return this.routeService.add(route);
	}
	
	 @PostMapping("/readfromtxtpushtodbRoute/{textName}")
	  @ResponseBody
	  public Result readFromTxtPushToDb(@PathVariable(value="textName") String textName) throws FileNotFoundException {
		  return this.routeService.readFromTxtPushToDb(textName); 
		  }
	 
	


}
