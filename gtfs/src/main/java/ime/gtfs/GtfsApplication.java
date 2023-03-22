package ime.gtfs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GtfsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GtfsApplication.class, args);
		
		/*
		 * try { UnzipUtility.unzip("bus-eshot-gtfs.zip", "/src"); } catch (IOException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */
	}

}
