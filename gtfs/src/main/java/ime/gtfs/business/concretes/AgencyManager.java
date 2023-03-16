package ime.gtfs.business.concretes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ime.gtfs.business.abstracts.AgencyService;
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
	public List<Agency> getAll() {
		List<Agency> agencies = agencyRepository.findAll();
		List<Agency> agenciesResponse = new ArrayList<Agency>();

		for (Agency agency : agencies) {
			Agency agencyResponseItem = new Agency();
			agencyResponseItem.setAgencyId(agency.getAgencyId());
			agencyResponseItem.setAgencyName(agency.getAgencyName());
			agencyResponseItem.setAgencyTimezone(agency.getAgencyTimezone());
			agencyResponseItem.setAgencyUrl(agency.getAgencyUrl());

			agenciesResponse.add(agencyResponseItem);
		}

		return agenciesResponse;
	}

	@Override
	public String readFromTxtPushToDb(String txtName) throws FileNotFoundException {
		File file = new File(txtName);
		List<String> lines = new ArrayList<String>();

		Scanner scanner = new Scanner(file);

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			lines.add(line);
		}

		scanner.close();

		// get column data from lines
		List<String> dataWithoutFirstLine = new ArrayList<String>();

		for (int i = 1; i < lines.size(); i++) {
			dataWithoutFirstLine.add(lines.get(i));
		}

		// process txt data
		List<Agency> agencies = new ArrayList<Agency>();
		for (String line : dataWithoutFirstLine) {
			Agency agency = new Agency();

			String[] fields = line.split(",");
			agency.setAgencyId(Integer.valueOf(fields[0]));
			agency.setAgencyName(fields[1]);
			agency.setAgencyUrl(fields[2]);
			agency.setAgencyTimezone(fields[3]);

			// add to list
			agencies.add(agency);
		}

		// add to db
		for (Agency agency : agencies) {
			this.add(agency);

		}
		return "Veritabanına kaydedildi.";

	}

	@Override
	public String add(Agency agency) {
		Agency agencyr = new Agency();
		agencyr.setAgencyName(agency.getAgencyName());
		agencyr.setAgencyUrl(agency.getAgencyUrl());
		agencyr.setAgencyTimezone(agency.getAgencyTimezone());
		this.agencyRepository.save(agencyr);
		System.out.println("Eklendi");
		return agencyr.toString();

	}

}
