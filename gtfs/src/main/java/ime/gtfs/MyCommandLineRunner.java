
package ime.gtfs;

import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import ime.gtfs.business.abstracts.BusService;
import ime.gtfs.entities.Bus;

@Component
public class MyCommandLineRunner implements ApplicationRunner {

	private BusService service;

	public MyCommandLineRunner(BusService service) {
		this.service = service;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		List<Bus> buses = this.service.getAll();
		for (Bus bus : buses) {
			new ScheduledThreadPoolExecutor(1).schedule(() -> service.startBus(bus.getBusId()), 0, TimeUnit.SECONDS);
			
		}

	}

}
