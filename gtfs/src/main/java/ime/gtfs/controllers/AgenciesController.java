package ime.gtfs.controllers;

import java.io.FileNotFoundException;
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

import ime.gtfs.business.abstracts.AgencyService;
import ime.gtfs.entities.Agency;

@RestController
@RequestMapping("/api/agencies")
public class AgenciesController {

	private AgencyService agencService;

	@Autowired
	public AgenciesController(AgencyService agencService) {
		super();
		this.agencService = agencService;
	}

	@GetMapping("/getallAgency")
	public List<Agency> getAll() {
		return this.agencService.getAll();
	}

	@PostMapping("/addAgency")
	public String add(@RequestBody Agency agency) {
		return this.agencService.add(agency);
	}

	@PostMapping("/readfromtxtpushtodbAgency/{textName}")
	@ResponseBody
	public String readFromTxtPushToDb(@PathVariable(value = "textName") String textName) throws FileNotFoundException {
		return this.agencService.readFromTxtPushToDb(textName);
	}
	
	
	@PostMapping("/readfromcsvpushtodbAgency/{textName}")
	@ResponseBody
	public String readFromCsvPushToDb(@PathVariable(value = "textName") String textName) throws IOException {
		return this.agencService.readFromCsvPushToDb(textName);
	}

}
