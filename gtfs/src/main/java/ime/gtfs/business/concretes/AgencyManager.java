package ime.gtfs.business.concretes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ime.gtfs.business.abstracts.AgencyService;
import ime.gtfs.core.utilities.results.DataResult;
import ime.gtfs.core.utilities.results.Result;
import ime.gtfs.core.utilities.results.SuccessDataResult;
import ime.gtfs.core.utilities.results.SuccessResult;
import ime.gtfs.dataAccess.abstracts.AgencyRepository;
import ime.gtfs.entities.Agency;

@Service
public class AgencyManager implements AgencyService {

	private AgencyRepository agencyRepository;

	@Autowired
	public AgencyManager(AgencyRepository agencyRepository) {
		super();
		this.agencyRepository = agencyRepository;
	}

	@Override
	public DataResult<List<Agency>> getAll() {
		/*
		 * List<Agency> agencies = agencyRepository.findAll(); List<Agency>
		 * agenciesResponse = new ArrayList<Agency>();
		 * 
		 * for (Agency agency : agencies) { Agency agencyResponseItem = new Agency();
		 * agencyResponseItem.setAgencyId(agency.getAgencyId());
		 * agencyResponseItem.setAgencyName(agency.getAgencyName());
		 * agencyResponseItem.setAgencyTimezone(agency.getAgencyTimezone());
		 * agencyResponseItem.setAgencyUrl(agency.getAgencyUrl());
		 * 
		 * agenciesResponse.add(agencyResponseItem);
		 * 
		 * }
		 */

		return new SuccessDataResult<List<Agency>>(this.agencyRepository.findAll(),"all agencies returned");
	}

	@Override
	public Result readFromTxtPushToDb(String txtName) throws FileNotFoundException {

		File file = new File(txtName);
		List<String> lines = new ArrayList<>();
		List<String> dataWithoutColumnNames = new ArrayList<>();

		Scanner scanner = new Scanner(file);

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			lines.add(line);
		}

		scanner.close();

		for (int i = 1; i < lines.size(); i++) {
			dataWithoutColumnNames.add(lines.get(i));
		}

		String columns = lines.get(0);
		List<String> columnNames = new ArrayList<String>();

		for (String col : columns.split(",")) {
			columnNames.add(col);
		}

		List<Agency> agencies = new ArrayList<Agency>();
		

		for (String line : dataWithoutColumnNames) {
			Agency agency = new Agency();
			String[] fields = line.split(",");
			
			

			for (String column : columnNames) {
				switch (column) {
				case "agency_id": {
					int indexOfCol = columnNames.indexOf(column);
					String data = fields[indexOfCol];
					agency.setAgencyId(Integer.valueOf(data));
					break;
				}
				case "agency_name": {
					int indexOfCol = columnNames.indexOf(column);
					String data = fields[indexOfCol];
					agency.setAgencyName(data);
					break;
				}
				case "agency_url": {
					int indexOfCol = columnNames.indexOf(column);
					String data = fields[indexOfCol];
					agency.setAgencyUrl(data);
					break;
				}
				case "agency_timezone": {
					int indexOfCol = columnNames.indexOf(column);
					String data = fields[indexOfCol];
					agency.setAgencyTimezone(data);
					break;
				}
				case "agency_phone":
					break;
				case "agency_lang":
					break;
				case "agency_fare_url":
					break;
				case "agency_email":
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + column);
				}
			}

			// add to list
			agencies.add(agency);
		}

		// add to db
		for (Agency agency : agencies) {
			this.add(agency);
		}

		return new SuccessResult("Veritabanına eklendi");

	}

	@Override
	public Result add(Agency agency) {
		Agency agencyr = new Agency();
		agencyr.setAgencyId(agency.getAgencyId());
		agencyr.setAgencyName(agency.getAgencyName());
		agencyr.setAgencyUrl(agency.getAgencyUrl());
		agencyr.setAgencyTimezone(agency.getAgencyTimezone());
		this.agencyRepository.save(agencyr);
		System.out.println("Eklendi");
		return new SuccessResult(agencyr.toString()+ " eklendi");

	}


}
