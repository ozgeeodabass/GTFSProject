package ime.gtfs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.maps.model.PlacesSearchResult;

import ime.gtfs.business.concretes.GooglePlacesService;

@RestController
@RequestMapping("/api/nearbyplaces")
@CrossOrigin
public class NearbyPlacesController {
	
	private final GooglePlacesService googlePlacesService;

    @Autowired
    public NearbyPlacesController(GooglePlacesService googlePlacesService) {
        this.googlePlacesService = googlePlacesService;
    }

    @GetMapping("/findNearHospitals/{latitude}/{longitude}/{radius}")
    public PlacesSearchResult[] findNearHospitals(
           @PathVariable(value="latitude") double latitude,
           @PathVariable(value="longitude") double longitude,
           @PathVariable(value="radius") int radius
    ) throws Exception {
        return googlePlacesService.findNearHospitals(latitude, longitude, radius);
    }
    
    @GetMapping("/findNearSubwayStations/{latitude}/{longitude}/{radius}")
    public PlacesSearchResult[] findNearSubwayStations(
           @PathVariable(value="latitude") double latitude,
           @PathVariable(value="longitude") double longitude,
           @PathVariable(value="radius") int radius
    ) throws Exception {
        return googlePlacesService.findNearSubwayStations(latitude, longitude, radius);
    }
    
    @GetMapping("/findNearCafes/{latitude}/{longitude}/{radius}")
    public PlacesSearchResult[] findNearCafes(
           @PathVariable(value="latitude") double latitude,
           @PathVariable(value="longitude") double longitude,
           @PathVariable(value="radius") int radius
    ) throws Exception {
        return googlePlacesService.findNearCafes(latitude, longitude, radius);
    }
    
    @GetMapping("/findNearAvms/{latitude}/{longitude}/{radius}")
    public PlacesSearchResult[] findNearAvms(
           @PathVariable(value="latitude") double latitude,
           @PathVariable(value="longitude") double longitude,
           @PathVariable(value="radius") int radius
    ) throws Exception {
        return googlePlacesService.findNearAvms(latitude, longitude, radius);
    }
    
    @GetMapping("/findNearSchools/{latitude}/{longitude}/{radius}")
    public PlacesSearchResult[] findNearSchools(
           @PathVariable(value="latitude") double latitude,
           @PathVariable(value="longitude") double longitude,
           @PathVariable(value="radius") int radius
    ) throws Exception {
        return googlePlacesService.findNearSchools(latitude, longitude, radius);
    }
    
    @GetMapping("/findNearAtms/{latitude}/{longitude}/{radius}")
    public PlacesSearchResult[] findNearAtms(
           @PathVariable(value="latitude") double latitude,
           @PathVariable(value="longitude") double longitude,
           @PathVariable(value="radius") int radius
    ) throws Exception {
        return googlePlacesService.findNearAtms(latitude, longitude, radius);
    }
    
    @GetMapping("/findNearDrugStores/{latitude}/{longitude}/{radius}")
    public PlacesSearchResult[] findNearDrugStores(
           @PathVariable(value="latitude") double latitude,
           @PathVariable(value="longitude") double longitude,
           @PathVariable(value="radius") int radius
    ) throws Exception {
        return googlePlacesService.findNearDrugStores(latitude, longitude, radius);
    }
    
    @GetMapping("/findNearUniversities/{latitude}/{longitude}/{radius}")
    public PlacesSearchResult[] findNearUniversities(
           @PathVariable(value="latitude") double latitude,
           @PathVariable(value="longitude") double longitude,
           @PathVariable(value="radius") int radius
    ) throws Exception {
        return googlePlacesService.findNearUniversities(latitude, longitude, radius);
    }
    
   

}
