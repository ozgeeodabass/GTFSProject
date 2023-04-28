package ime.gtfs.business.concretes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ime.gtfs.business.abstracts.RouteService;
import ime.gtfs.dataAccess.abstracts.AgencyRepository;
import ime.gtfs.dataAccess.abstracts.RouteRepository;
import ime.gtfs.entities.Route;

@Service
public class RouteManager implements RouteService {

	private RouteRepository routeRepository;
	private AgencyRepository agencyRepository;
	
	@Autowired
	public RouteManager(RouteRepository routeRepository,AgencyRepository agencyRepository) {
		super();
		this.routeRepository = routeRepository;
		this.agencyRepository=agencyRepository;
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
			routeResponseItem.setAgencyId(route.getAgencyId());

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

		// get columns names
		String columns = lines.get(0);
		List<String> columnNames = new ArrayList<String>();

		for (String col : columns.split(",")) {
			columnNames.add(col);
		}

		// process txt data
		List<Route> routes = new ArrayList<Route>();

		for (String line : dataWithoutFirstLine) {
			Route route = new Route();

			String[] fields = line.split(",");

			
			for (String column : columnNames) {
				switch (column) {
				case "route_id": {
					int indexOfCol = columnNames.indexOf(column);
					String data = fields[indexOfCol];
					route.setRouteId(Integer.valueOf(data));
					break;
				}
				case "route_short_name": {
					int indexOfCol = columnNames.indexOf(column);
					String data = fields[indexOfCol];
					route.setRouteShortName(data);
					break;
				}
				case "route_long_name": {
					int indexOfCol = columnNames.indexOf(column);
					String data = fields[indexOfCol];
					route.setRouteLongName(data);
					break;
				}
				case "route_type": {
					int indexOfCol = columnNames.indexOf(column);
					String data = fields[indexOfCol];
					route.setRouteType(data);
					break;
				}
				case "agency_id": {
					int indexOfCol = columnNames.indexOf(column);
					int data = Integer.valueOf(fields[indexOfCol]);
					route.setAgencyId(agencyRepository.findById(data).get());
					break;
				}
				case "route_desc": {
					break;
				}
				case "route_url": {
					break;
				}
				case "route_color": {
					break;
				}
				case "route_text_color": {
					break;
				}
				case "route_sort_order": {
					break;
				}
				case "continuous_pickup": {
					break;
				}
				case "continuous_drop_off": {
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + column);
				}
			}

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
		router.setAgencyId(route.getAgencyId());
		this.routeRepository.save(router);
		System.out.println("Eklendi");
		return router.toString();
	}

}
