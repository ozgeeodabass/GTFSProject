package ime.gtfs.business.concretes;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ime.gtfs.business.abstracts.BusService;
import ime.gtfs.dataAccess.abstracts.BusRepository;
import ime.gtfs.entities.Bus;

@Service
public class BusManager implements BusService {
	
	private BusRepository busRepository;
	
	@Autowired
	public BusManager(BusRepository busRepository) {
		super();
		this.busRepository = busRepository;
	}

	@Override
	public List<Bus> getAll() {
		List<Bus> buses = busRepository.findAll();
		List<Bus> busesResponse = new ArrayList<Bus>();

		for (Bus bus : buses) {
			Bus busResponseItem = new Bus();
			busResponseItem.setBusId(bus.getBusId());
			busResponseItem.setBusCode(bus.getBusCode());
			busResponseItem.setRoute(bus.getRoute());

			busesResponse.add(busResponseItem);
		}

		return busesResponse;
	}

	@Override
	public String readFromTxtPushToDb(String txtName) throws FileNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String add(Bus bus) {
		Bus busr = new Bus();
		busr.setBusId(bus.getBusId());
		busr.setBusCode(bus.getBusCode());
		busr.setRoute(bus.getRoute());
		this.busRepository.save(busr);
		System.out.println("Eklendi");
		return busr.toString();
	}

}
