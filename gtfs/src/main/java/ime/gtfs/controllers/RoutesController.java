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

import ime.gtfs.business.abstracts.RouteService;
import ime.gtfs.entities.Route;

@RestController
@RequestMapping("/api/routes")
public class RoutesController {
	
	private RouteService routeService ;
	
	@Autowired
	public RoutesController(RouteService routeService) {
		super();
		this.routeService = routeService;
	}
	
	@GetMapping("/getallRoute")
	public List<Route> getAll() {
		return this.routeService.getAll();
	}
	
	@PostMapping("/addRoute")
	public String add(@RequestBody Route route) {
		return this.routeService.add(route);
	}
	
	 @PostMapping("/readfromtxtpushtodbRoute/{textName}")
	  @ResponseBody
	  public String readFromTxtPushToDb(@PathVariable(value="textName") String textName) throws FileNotFoundException {
		  return this.routeService.readFromTxtPushToDb(textName); 
		  }
	 
	


}
