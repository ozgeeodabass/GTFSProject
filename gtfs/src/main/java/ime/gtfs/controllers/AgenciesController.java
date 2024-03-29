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
import ime.gtfs.business.abstracts.AgencyService;
import ime.gtfs.core.utilities.results.DataResult;
import ime.gtfs.core.utilities.results.Result;
import ime.gtfs.entities.Agency;

@RestController
@RequestMapping("/api/agencies")
@CrossOrigin
public class AgenciesController {

	private AgencyService agencService;

	@Autowired
	public AgenciesController(AgencyService agencService) {
		super();
		this.agencService = agencService;
	}

	@GetMapping("/getallAgency")
	public DataResult<List<Agency>> getAll() {
		return this.agencService.getAll();
	}

	@PostMapping("/addAgency")
	public Result add(@RequestBody Agency agency) {
		return this.agencService.add(agency);
	}

	@PostMapping("/readfromtxtpushtodbAgency/{textName}")
	@ResponseBody
	public Result readFromTxtPushToDb(@PathVariable(value = "textName") String textName) throws FileNotFoundException {
		return this.agencService.readFromTxtPushToDb(textName);
	}
	
	
}
