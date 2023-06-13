package ime.gtfs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.maps.model.LatLng;

import ime.gtfs.business.concretes.GoogleDirectionService;

@RestController
@RequestMapping("/api/directions")
@CrossOrigin
public class DirectionsController {
	
	private final GoogleDirectionService googleDirectionService;

    @Autowired
    public DirectionsController(GoogleDirectionService googleDirectionService) {
        this.googleDirectionService = googleDirectionService;
    }
    
    @GetMapping("/getPolyline/{origin}/{destination}")
    public List<LatLng> getPolyline(
           @PathVariable(value="origin")String origin,
           @PathVariable(value="destination") String destination
    ) throws Exception {
        return googleDirectionService.getPolyline(origin, destination);
    }
    
    
    @GetMapping("/getRoutes/{origin}/{destination}")
    public List<String> getRoutes(
           @PathVariable(value="origin")String origin,
           @PathVariable(value="destination") String destination
    ) throws Exception {
        return googleDirectionService.getRoutes(origin, destination);
    }
    
    
    
   

}

