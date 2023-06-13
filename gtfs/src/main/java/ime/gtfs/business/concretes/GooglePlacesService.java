package ime.gtfs.business.concretes;

import org.springframework.stereotype.Service;

import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlaceType;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;

@Service
public class GooglePlacesService {

	private final GeoApiContext geoApiContext;

    public GooglePlacesService() {
        // API anahtarınızı buraya yerleştirin
        String apiKey = "";

        geoApiContext = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();
    }

    public PlacesSearchResult[] findNearHospitals(double latitude, double longitude, int radius) throws Exception {
        LatLng location = new LatLng(latitude, longitude);

        PlacesSearchResponse response = PlacesApi.nearbySearchQuery(geoApiContext, location)
                .radius(radius)
                .type(PlaceType.HOSPITAL)
                .await();
       
        
        for (PlacesSearchResult respons : response.results) {
			System.out.println(respons.name.toString());
		}

        return response.results;
    }
    
    public PlacesSearchResult[] findNearSubwayStations(double latitude, double longitude, int radius) throws Exception {
        LatLng location = new LatLng(latitude, longitude);

        PlacesSearchResponse response = PlacesApi.nearbySearchQuery(geoApiContext, location)
                .radius(radius)
                .type(PlaceType.SUBWAY_STATION)
                .await();
       
        
        for (PlacesSearchResult respons : response.results) {
			System.out.println(respons.name.toString());
		}

        return response.results;
    }
    
    public PlacesSearchResult[] findNearCafes(double latitude, double longitude, int radius) throws Exception {
        LatLng location = new LatLng(latitude, longitude);

        PlacesSearchResponse response = PlacesApi.nearbySearchQuery(geoApiContext, location)
                .radius(radius)
                .type(PlaceType.CAFE)
                .await();
       
        
        for (PlacesSearchResult respons : response.results) {
			System.out.println(respons.name.toString());
		}

        return response.results;
    }
    
    public PlacesSearchResult[] findNearAvms(double latitude, double longitude, int radius) throws Exception {
        LatLng location = new LatLng(latitude, longitude);

        PlacesSearchResponse response = PlacesApi.nearbySearchQuery(geoApiContext, location)
                .radius(radius)
                .type(PlaceType.SHOPPING_MALL)
                .await();
       
        
        for (PlacesSearchResult respons : response.results) {
			System.out.println(respons.name.toString());
		}

        return response.results;
    }
    
    public PlacesSearchResult[] findNearSchools(double latitude, double longitude, int radius) throws Exception {
        LatLng location = new LatLng(latitude, longitude);

        PlacesSearchResponse response = PlacesApi.nearbySearchQuery(geoApiContext, location)
                .radius(radius)
                .type(PlaceType.SCHOOL)
                .await();
       
        
        for (PlacesSearchResult respons : response.results) {
			System.out.println(respons.name.toString());
		}

        return response.results;
    }
    
    public PlacesSearchResult[] findNearAtms(double latitude, double longitude, int radius) throws Exception {
        LatLng location = new LatLng(latitude, longitude);

        PlacesSearchResponse response = PlacesApi.nearbySearchQuery(geoApiContext, location)
                .radius(radius)
                .type(PlaceType.ATM)
                .await();
       
        
        for (PlacesSearchResult respons : response.results) {
			System.out.println(respons.name.toString());
		}

        return response.results;
    }
    
    public PlacesSearchResult[] findNearDrugStores(double latitude, double longitude, int radius) throws Exception {
        LatLng location = new LatLng(latitude, longitude);

        PlacesSearchResponse response = PlacesApi.nearbySearchQuery(geoApiContext, location)
                .radius(radius)
                .type(PlaceType.DRUGSTORE)
                .await();
       
        
        for (PlacesSearchResult respons : response.results) {
			System.out.println(respons.name.toString());
		}

        return response.results;
    }
    
    public PlacesSearchResult[] findNearUniversities(double latitude, double longitude, int radius) throws Exception {
        LatLng location = new LatLng(latitude, longitude);

        PlacesSearchResponse response = PlacesApi.nearbySearchQuery(geoApiContext, location)
                .radius(radius)
                .type(PlaceType.UNIVERSITY)
                .await();
       
        
        for (PlacesSearchResult respons : response.results) {
			System.out.println(respons.name.toString());
		}

        return response.results;
    }
    
	     
	   
	}




