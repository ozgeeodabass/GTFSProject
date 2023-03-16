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
import ime.gtfs.business.abstracts.CalendarService;
import ime.gtfs.entities.Calendar;

@RestController
@RequestMapping("/api/calendars")
public class CalendarController {

	private CalendarService calendarService;

	@Autowired
	public CalendarController(CalendarService calendarService) {
		super();
		this.calendarService = calendarService;
	}

	@GetMapping("/getallCalendar")
	public List<Calendar> getAll() {
		return this.calendarService.getAll();
	}

	@PostMapping("/addCalendar")
	public String add(@RequestBody Calendar calendar) {
		return this.calendarService.add(calendar);
	}

	@PostMapping("/readfromtxtpushtodbCalendar/{textName}")
	@ResponseBody
	public String readFromTxtPushToDb(@PathVariable(value = "textName") String textName) throws FileNotFoundException {
		return this.calendarService.readFromTxtPushToDb(textName);
	}

}
