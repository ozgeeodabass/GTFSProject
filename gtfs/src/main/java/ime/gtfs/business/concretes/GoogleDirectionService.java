package ime.gtfs.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.LatLng;
import com.google.maps.model.TransitLine;
import com.google.maps.model.TravelMode;

@Service
public class GoogleDirectionService {
	 
	 private final GeoApiContext geoApiContext;

	    public GoogleDirectionService() {
	    	String apiKey = "";
	        geoApiContext = new GeoApiContext.Builder()
	                .apiKey(apiKey)
	                .build();
	
}
	    
	    public List<LatLng> getPolyline(String origin, String destination) throws Exception {
	    	
	    	DirectionsApiRequest request = DirectionsApi.newRequest(geoApiContext)
	                .origin(origin)
	                .destination(destination)
	                .mode(TravelMode.TRANSIT)
	                .optimizeWaypoints(true); 

	    	List<LatLng> polyline = new ArrayList<LatLng>();
	        try {
	            DirectionsResult result = request.await();
	            if (result.routes.length > 0) {
	            	for (com.google.maps.model.LatLng latLng : result.routes[0].overviewPolyline.decodePath()) {
	                    polyline.add(new LatLng(latLng.lat, latLng.lng));
	                }
	            	return polyline;
	            } else {
	                return null;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	      
	       
	    }
	    
	    public List<String> getRoutes(String origin, String destination) throws Exception {
	    	
	    	DirectionsApiRequest request = DirectionsApi.newRequest(geoApiContext)
	    	        .origin(origin)
	    	        .destination(destination)
	    	        .mode(com.google.maps.model.TravelMode.TRANSIT); // Transit modunu kullanarak otobüs hatlarını ara

	    	    try {
	    	      DirectionsResult result = request.await();
	    	      List<String> busRoutes = new ArrayList<>();
	    	      for (DirectionsRoute route : result.routes) {
	    	        for (DirectionsLeg leg : route.legs) {
	    	          for (DirectionsStep step : leg.steps) {
	    	            if (step.transitDetails != null) {
	    	              TransitLine transitLine = step.transitDetails.line;
	    	              if (transitLine != null) {
	    	                String busRoute = transitLine.shortName;
	    	                busRoutes.add(busRoute);
	    	              }
	    	            }
	    	          }
	    	        }
	    	      }
	    	      return busRoutes;
	    	    } catch (Exception e) {
	    	      e.printStackTrace();
	    	      return null;
	    	    }
	    	
	    }
}
