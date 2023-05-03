package ime.gtfs.business.concretes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import ime.gtfs.business.abstracts.BusService;
import ime.gtfs.core.utilities.results.DataResult;
import ime.gtfs.core.utilities.results.Result;
import ime.gtfs.core.utilities.results.SuccessDataResult;
import ime.gtfs.core.utilities.results.SuccessResult;
import ime.gtfs.dataAccess.abstracts.BusRepository;
import ime.gtfs.dataAccess.abstracts.StopTimeRepository;
import ime.gtfs.dataAccess.abstracts.TripRepository;
import ime.gtfs.entities.Bus;
import ime.gtfs.entities.Info;
import ime.gtfs.entities.Route;
import ime.gtfs.entities.StopTime;
import ime.gtfs.entities.Trip;

@Service
public class BusManager implements BusService {

	private BusRepository busRepository;
	private TripRepository tripRepository;
	private StopTimeRepository stopTimeRepository;
	
	@Autowired
	public BusManager(BusRepository busRepository, TripRepository tripRepository,StopTimeRepository stopTimeRepository) {
		super();
		this.busRepository = busRepository;
		this.tripRepository = tripRepository;
		this.stopTimeRepository = stopTimeRepository;
	}

	@Override
	public DataResult<List<Bus>> getAll() {
		/*
		 * List<Bus> buses = busRepository.findAll(); List<Bus> busesResponse = new
		 * ArrayList<Bus>();
		 * 
		 * for (Bus bus : buses) { Bus busResponseItem = new Bus();
		 * busResponseItem.setBusId(bus.getBusId());
		 * busResponseItem.setRoute(bus.getRoute());
		 * 
		 * busesResponse.add(busResponseItem); }
		 */
		
		return new SuccessDataResult<List<Bus>>(this.busRepository.findAll(), "all buses returned");
	}

	@Override
	public Result add(Bus bus) {
		Bus busr = new Bus();
		busr.setBusId(bus.getBusId());
		busr.setRoute(bus.getRoute());
		busr.setAgency(bus.getAgency());
		this.busRepository.save(busr);
		System.out.println("Eklendi");
		return new SuccessResult(busr.toString()+" eklendi");
	}

	
	
	public DataResult<Info> startBus(int busId) throws IOException {

		Bus bus = busRepository.findById(busId).get();	
		
		Route route = bus.getRoute();		
		
		// create info object
		Info infos = new Info();

		// trips of the route of bus
		List<Trip> tripsOfRoute = tripRepository.findAllByRoute(route);

		// all stop times of the trips
		List<StopTime> stopTimes = new ArrayList<StopTime>();
		List<StopTime> stopTimesOfTrip = new ArrayList<StopTime>();

		// stop times of each trip of the route
		for (Trip trip : tripsOfRoute) {
			stopTimesOfTrip = trip.getStopTimes();

			for (StopTime stopTime : stopTimesOfTrip) {
				stopTimes.add(stopTime);

			}

		}
		
		// set the nearest trip according to the current time
		Trip nearestTrip = new Trip();

		List<Duration> timeDifferences = new ArrayList<Duration>();
		LocalTime currentTime = LocalTime.now();

		StopTime stopTimeOfNearestTrip = new StopTime();

		List<StopTime> stopTimesOfNearestTrip = new ArrayList<StopTime>();
		
		List<Integer> stopTimeIds = new ArrayList<Integer>();
		
		// trip bulunmadığı zaman sorun çıkarabilir
		int index = -1;

		for (StopTime stopTime : stopTimes) {
			
			if (stopTime.getDepartureTime().isAfter(currentTime)) {
			
				Duration timeDifference = Duration.between(stopTime.getDepartureTime(), currentTime);
				
				if(timeDifference.isNegative()) {
					timeDifference=timeDifference.abs();
				}
				
				timeDifferences.add(timeDifference);
				stopTimeIds.add(stopTime.getStopTimeId());
				index = stopTimes.indexOf(stopTime);
				System.out.println(index);

			}

		}

		long minDiff = -1;
		if (!timeDifferences.isEmpty()) {
			
			minDiff = Collections.min(timeDifferences).toMinutes();
			int indexx = timeDifferences.indexOf(Collections.min(timeDifferences));
			int id = stopTimeIds.get(indexx);
			stopTimeOfNearestTrip = stopTimeRepository.findById(id).get();
			
			//min elementn indexini alıp o o şekilde yap burayı
			int tripId = stopTimes.get(index).getTrip().getTripId();

			for (StopTime stopTime : stopTimes) {
				if (stopTime.getTrip().getTripId() == tripId && stopTime.getDepartureTime().isAfter(currentTime)) {
					stopTimesOfNearestTrip.add(stopTime);
	
				}

			}

		} else if (timeDifferences.isEmpty()) {
			stopTimeOfNearestTrip = null;
		}

		System.out.println("OTOBÜS BİLGİLERİ: " + bus.getBusId());
		System.out.println(bus.getAgency().getAgencyName() + " - " + "Vehicle type: " + bus.getRoute().getRouteType()
				+ " | " + " Bus Route: " + bus.getRoute().getRouteShortName() + " "
				+ bus.getRoute().getRouteLongName());

		if (minDiff == -1) {
			System.out.println(busRepository.findById(bus.getBusId()).get().getRoute().getRouteShortName() + " HATTI İÇİN YAKLAŞAN BİR SEFER YOK");
		} else {
			nearestTrip = stopTimesOfNearestTrip.get(0).getTrip();

			// en yakın trip bilgileri
			System.out.println(bus.getRoute().getRouteShortName() + " Otobüsü için " + nearestTrip.getTripId()
					+ " Numaralı Trip Bilgileri: ");
			System.out.println("Nearest Stop Time: ");

					System.out.println("Stop Time: " + stopTimeOfNearestTrip.getStopTimeId() + " Arrival Time: "
							+ stopTimeOfNearestTrip.getArrivalTime() + " Departure Time: " + stopTimeOfNearestTrip.getDepartureTime()
							+ " Stop Name: " + stopTimeOfNearestTrip.getStop());

				

			// SAAT HESAPLAMALARI

			switch ((minDiff<=59) ? 0 : (minDiff>59) ? 1 : 2) {
			case 0: {
				Duration remainingTime = Duration.ofMinutes(minDiff);
				String remainingTimeString = remainingTime.toMinutes() + " dakika";
				System.out.println(remainingTimeString + " kaldı");
				infos.setRemainingTime(remainingTimeString);

			}
			case 1: {
				long remainingHour = TimeUnit.MINUTES.toHours(minDiff);
				long remainingMinutes = minDiff - remainingHour * 60;
				// LocalTime remainingTime =
				// LocalTime.of(Integer.valueOf(String.valueOf(remainingHour)),
				// Integer.valueOf(String.valueOf(remainingMinutes)));
				Duration remainingTime = Duration.ofHours(remainingHour).plusMinutes(remainingMinutes);
				String remaningTimeString = remainingTime.toHours() + " saat " + (remainingTime.toMinutes()) % 60
						+ " dakika";

				infos.setRemainingTime(remaningTimeString);
				System.out.println(remaningTimeString + " kaldı" + ", Durak: " + stopTimeOfNearestTrip.getStop());

			}
			case 2: {
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + Integer.valueOf(String.valueOf(minDiff)));
			}

		}


		infos.setAgencyName(bus.getAgency().getAgencyName());
		infos.setRouteShortName(bus.getRoute().getRouteShortName());
		infos.setRouteLongName(bus.getRoute().getRouteLongName());
		
		
			infos.setNearestTrip(nearestTrip);
			infos.setNearestStopTime(stopTimeOfNearestTrip);
		
		
		infos.setBusId(bus.getBusId());

		writeToTheTxt(infos);
		
		String message="";
		if(infos.getNearestStopTime()!=null) {
			message+=infos.getNearestStopTime().getStop().getStopName()+" durağından kalkış için kalan süre: "+ infos.getRemainingTime();
		}
		return new SuccessDataResult<Info>(infos,message);

	}

	public void writeToTheTxt(Info info) throws JsonProcessingException {

		String fileName = "infos.txt";
		File file = new File(fileName);
		JSONObject jsonObj = new JSONObject();
		
		if(info.getNearestStopTime()!=null) {
			
			jsonObj.append("agency", info.getAgencyName());
			jsonObj.append("bus", info.getBusId());
			jsonObj.append("stop_time", info.getNearestStopTime().toString());
			jsonObj.append("trip", info.getNearestTrip().toString());
			jsonObj.append("route", info.getRouteShortName() + " " + info.getRouteLongName());
			jsonObj.append("shapes", info.getNearestTrip().getShapes().toString());
		}
		

		if (info.getRemainingTime() == null) {
			info.setRemainingTime(busRepository.findById(info.getBusId()).get().getRoute().getRouteShortName() + " HATTI İÇİN YAKLAŞAN BİR SEFER YOK");
			jsonObj.append("remaining time", info.getRemainingTime().toString());
		} else if (info.getRemainingTime() != null) {
			jsonObj.append("remaining time", info.getRemainingTime().toString());
		}

		try {
			// Dosya yoksa oluştur
			if (!file.exists()) {
				file.createNewFile();
				FileWriter wr = new FileWriter(file);

				wr.write(jsonObj.toString());
				wr.close();

			} else if (file.exists() == true) {
				// Dosyanın içindeki bilgileri sil
				FileWriter wr = new FileWriter(file);

				wr.write("");
				wr.write(jsonObj.toString());

				wr.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
