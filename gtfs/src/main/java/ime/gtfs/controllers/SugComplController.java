package ime.gtfs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ime.gtfs.business.abstracts.SugComplService;
import ime.gtfs.core.utilities.results.DataResult;
import ime.gtfs.core.utilities.results.Result;
import ime.gtfs.entities.SugCompl;

@RestController
@RequestMapping("/api/sugcompl")
@CrossOrigin
public class SugComplController {
	
	private SugComplService sugComplService;

	@Autowired
	public SugComplController(SugComplService sugComplService) {
		super();
		this.sugComplService = sugComplService;
	}
	

	@GetMapping(name = "/getAll")
	public DataResult<List<SugCompl>> getAll() {
	
		return this.sugComplService.getAll();
	}


	@PostMapping("/addSugCompl/{route}/{email}/{phone}/{text}")
	public Result add(@PathVariable(value="route")int route, @PathVariable(value="email")String email,@PathVariable(value="phone") String phone,@PathVariable(value="text") String text) {
		return this.sugComplService.add(route,email,phone,text);
		
	}

	@GetMapping("/getAllByRoute/{route}")
	public DataResult<List<SugCompl>> findAllByRoute(@PathVariable(value="route") int route) {
		
		return this.sugComplService.findAllByRoute(route);
	}


}
