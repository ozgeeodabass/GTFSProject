package ime.gtfs.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "trips")
public class Trip {

	@Id
	@PrimaryKeyJoinColumn
	private int tripId;

	@Column(name = "direction_id")
	private int directionId;

	@Column(name = "bikes_allowed")
	private int bikesAllowed;

	@Column(name = "wheelchair_accessible")
	private int wheelchairAccessible;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "service_id")
	private Calendar serviceId;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="route_id")
	private Route route;
	
	@OneToMany(mappedBy ="trip")
	@JsonIgnore
	private List<StopTime> stopTimes; 
	
	@ManyToOne
	@JoinColumn(name = "bus_id")
	private Bus bus;
	
	@ManyToOne
	@JoinColumn(name = "shape_id")
	private Shape shape;
	

	
}
