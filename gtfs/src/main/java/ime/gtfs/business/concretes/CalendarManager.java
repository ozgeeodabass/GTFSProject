package ime.gtfs.business.concretes;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ime.gtfs.business.abstracts.CalendarService;
import ime.gtfs.dataAccess.abstracts.CalendarRepository;
import ime.gtfs.entities.Calendar;

@Service
public class CalendarManager implements CalendarService {

	private CalendarRepository calendarRepository;

	@Autowired
	public CalendarManager(CalendarRepository calendarRepository) {
		super();
		this.calendarRepository = calendarRepository;
	}

	@Override
	public List<Calendar> getAll() {
		List<Calendar> calendars = calendarRepository.findAll();
		List<Calendar> calendarsResponse = new ArrayList<Calendar>();

		for (Calendar calendar : calendars) {
			Calendar calendarResponseItem = new Calendar();
			calendarResponseItem.setServiceId(calendar.getServiceId());
			calendarResponseItem.setStartDate(calendar.getStartDate());
			calendarResponseItem.setEndDate(calendar.getEndDate());
			calendarResponseItem.setMonday(calendar.getMonday());
			calendarResponseItem.setFriday(calendar.getFriday());
			calendarResponseItem.setSaturday(calendar.getSaturday());
			calendarResponseItem.setSunday(calendar.getSunday());
			calendarResponseItem.setThursday(calendar.getThursday());
			calendarResponseItem.setTuesday(calendar.getTuesday());
			calendarResponseItem.setWednesday(calendar.getWednesday());

			calendarsResponse.add(calendarResponseItem);
		}

		return calendarsResponse;
	}

	@Override
	public String readFromTxtPushToDb(String txtName) throws FileNotFoundException {
		File file = new File(txtName);
		List<String> lines = new ArrayList<String>();
		
		// get column data from lines
		List<String> dataWithoutFirstLine = new ArrayList<String>();

		for (int i = 1; i < lines.size(); i++) {
			dataWithoutFirstLine.add(lines.get(i));
		}

		Scanner scanner = new Scanner(file);

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			lines.add(line);
		}

		scanner.close();
		
		//get columns names
		String columns = lines.get(0);
		List<String> columnNames = new ArrayList<String>();

		for (String col : columns.split(",")) {
			columnNames.add(col);
		}
		

		// process txt data
		List<Calendar> calendars = new ArrayList<Calendar>();
		
		for (String line : dataWithoutFirstLine) {
			Calendar calendar = new Calendar();

			String[] fields = line.split(",");
			
			for (String column : columnNames) {
				switch (column) {
				case "service_id": {
					int indexOfCol = columnNames.indexOf(column);
					String data = fields[indexOfCol];
					calendar.setServiceId(Integer.valueOf(data));
					break;
				}
				case "monday": {
					int indexOfCol = columnNames.indexOf(column);
					String data = fields[indexOfCol];
					calendar.setMonday(Integer.valueOf(data));
					break;
				}
				case "tuesday": {
					int indexOfCol = columnNames.indexOf(column);
					String data = fields[indexOfCol];
					calendar.setTuesday(Integer.valueOf(data));
					break;
				}
				case "wednesday": {
					int indexOfCol = columnNames.indexOf(column);
					String data = fields[indexOfCol];
					calendar.setWednesday(Integer.valueOf(data));
					break;
				}
				case "thursday": {
					int indexOfCol = columnNames.indexOf(column);
					String data = fields[indexOfCol];
					calendar.setThursday(Integer.valueOf(data));
					break;
				}
				case "friday": {
					int indexOfCol = columnNames.indexOf(column);
					String data = fields[indexOfCol];
					calendar.setFriday(Integer.valueOf(data));
					break;
				}
				case "saturday": {
					int indexOfCol = columnNames.indexOf(column);
					String data = fields[indexOfCol];
					calendar.setSaturday(Integer.valueOf(data));
					break;
				}
				case "sunday": {
					int indexOfCol = columnNames.indexOf(column);
					String data = fields[indexOfCol];
					calendar.setSunday(Integer.valueOf(data));
					break;
				}
				case "start_date": {
					int indexOfCol = columnNames.indexOf(column);
					String data = fields[indexOfCol];
					LocalDate startDate = LocalDate.parse(data, DateTimeFormatter.BASIC_ISO_DATE);
					calendar.setStartDate(startDate);
					break;
				}
				case "end_date": {
					int indexOfCol = columnNames.indexOf(column);
					String data = fields[indexOfCol];
					LocalDate endDate = LocalDate.parse(data, DateTimeFormatter.BASIC_ISO_DATE);
					calendar.setEndDate(endDate);
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + column);
				}
				
				
				
			}
			// add to list
				calendars.add(calendar);
			
			/*
			 * 
			 * calendar.setServiceId(Integer.valueOf(fields[0]));
			 * 
			 * calendar.setMonday(Integer.valueOf(fields[1]));
			 * calendar.setTuesday(Integer.valueOf(fields[2]));
			 * calendar.setWednesday(Integer.valueOf(fields[3]));
			 * calendar.setThursday(Integer.valueOf(fields[4]));
			 * calendar.setFriday(Integer.valueOf(fields[5]));
			 * calendar.setSaturday(Integer.valueOf(fields[6]));
			 * calendar.setSunday(Integer.valueOf(fields[7]));
			 * 
			 * // String to date // for startDate String startDateString = fields[8];
			 * LocalDate startDate = LocalDate.parse(startDateString,
			 * DateTimeFormatter.BASIC_ISO_DATE);
			 * 
			 * // for endDate String endDateString = fields[9]; LocalDate endDate =
			 * LocalDate.parse(endDateString, DateTimeFormatter.BASIC_ISO_DATE);
			 * 
			 * calendar.setStartDate(startDate); calendar.setEndDate(endDate);
			 * 
			 * // add to list calendars.add(calendar);
			 */
		}

		// add to db
		for (Calendar calendar : calendars) {
			this.add(calendar);

		}
		return "Veritabanına kaydedildi.";
	}

	@Override
	public String add(Calendar calendar) {
		Calendar calendarr = new Calendar();
		calendarr.setServiceId(calendar.getServiceId());
		calendarr.setStartDate(calendar.getStartDate());
		calendarr.setEndDate(calendar.getEndDate());
		calendarr.setMonday(calendar.getMonday());
		calendarr.setFriday(calendar.getFriday());
		calendarr.setSaturday(calendar.getSaturday());
		calendarr.setSunday(calendar.getSunday());
		calendarr.setThursday(calendar.getThursday());
		calendarr.setTuesday(calendar.getTuesday());
		calendarr.setWednesday(calendar.getWednesday());
		this.calendarRepository.save(calendarr);
		System.out.println("Eklendi");
		return calendarr.toString();
	}

}
