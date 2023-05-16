package ime.gtfs.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
	
	//@Column(name = "shape_id")
	//private int shapeId;
	
	
	//@ManyToOne
	//@JsonIgnore
	//@JoinColumn(name = "bus_id")
	//private Bus bus;

	
	//manytomany olarak düzenle
	@JsonIgnore
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "trips_shapes", 
        joinColumns = { @JoinColumn(name = "trip_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "shape_id") }
    )
	private List<Shape> shapes;
	

	public String toString() {
		return "trip_id"+":"+this.tripId+","+"direction_id"+":"+this.directionId+","+"bikes_allowed"+":"+this.bikesAllowed+","+ "wheelchair_accessible"+":"+this.wheelchairAccessible+","+	"service_id"+":"+this.serviceId.getServiceId()+","+"route_id"+":"+ this.route.getRouteId()+","+"shape_id"+":"+this.shapes.get(0).getShapeId();
	}
	
}
