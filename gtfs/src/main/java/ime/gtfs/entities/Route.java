package ime.gtfs.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "routes")
public class Route {

	@Id
	private int routeId;
	
	@Column(name = "route_short_name")
	private String routeShortName;
	
	@Column(name = "route_long_name")
	private String routeLongName;
	
	@Column(name = "route_type")
	private String routeType;
	
	@OneToMany(mappedBy = "route" )
	@JsonIgnore
	private List<Bus> buses;
	
	@OneToMany(mappedBy = "tripId")
	private List<Trip> trips;
	
	
	/*
	 * public String getRouteType() { switch (this.routeType){ case "0": { return
	 * "TRAM"; }case "1": { return "SUBWAY"; }case "2": { return "RAIL"; }case "3":
	 * { return "BUS"; }case "4": { return "FERRY"; }case "5": { return "CABLETRAM";
	 * } default: throw new IllegalArgumentException("Unexpected value: " +
	 * this.routeType); } }
	 */
	
	
}
