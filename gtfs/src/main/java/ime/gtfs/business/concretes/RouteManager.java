package ime.gtfs.business.concretes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ime.gtfs.business.abstracts.RouteService;
import ime.gtfs.dataAccess.abstracts.RouteRepository;
import ime.gtfs.entities.Route;

@Service
public class RouteManager implements RouteService {

	private RouteRepository routeRepository;

	@Autowired
	public RouteManager(RouteRepository routeRepository) {
		super();
		this.routeRepository = routeRepository;
	}

	@Override
	public List<Route> getAll() {
		List<Route> routes = routeRepository.findAll();
		List<Route> routesResponse = new ArrayList<Route>();

		for (Route route : routes) {
			Route routeResponseItem = new Route();
			routeResponseItem.setRouteId(route.getRouteId());
			routeResponseItem.setRouteShortName(route.getRouteShortName());
			routeResponseItem.setRouteLongName(route.getRouteLongName());
			routeResponseItem.setRouteType(route.getRouteType());

			routesResponse.add(routeResponseItem);
		}

		return routesResponse;
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
		List<Route> routes = new ArrayList<Route>();
		for (String line : dataWithoutFirstLine) {
			Route route = new Route();

			String[] fields = line.split(",");
			route.setRouteId(Integer.valueOf(fields[0]));
			route.setRouteShortName(fields[1]);
			route.setRouteLongName(fields[2]);
			route.setRouteType(fields[3]);

			// add to list
			routes.add(route);
		}

		// add to db
		for (Route route : routes) {
			this.add(route);

		}
		return "Veritabanına kaydedildi.";
	}

	@Override
	public String add(Route route) {
		Route router = new Route();
		router.setRouteId(route.getRouteId());
		router.setRouteShortName(route.getRouteShortName());
		router.setRouteLongName(route.getRouteLongName());
		router.setRouteType(route.getRouteType());
		this.routeRepository.save(router);
		System.out.println("Eklendi");
		return router.toString();
	}

}
