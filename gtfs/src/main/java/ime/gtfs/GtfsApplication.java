package ime.gtfs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GtfsApplication  {

	public static void main(String[] args) {
		SpringApplication.run(GtfsApplication.class, args);
		
		
		
	}

}
