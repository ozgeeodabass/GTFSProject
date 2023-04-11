package ime.gtfs.entities;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
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
@Table(name = "stops")
public class Stop {
	
	@Id
	@PrimaryKeyJoinColumn
	private int stopId;
	
	@Column(name = "stop_name")
	private String stopName;
	
	@Column(name = "stop_lat")
	private double stopLat;
	
	@Column(name = "stop_lon")
	private double stopLon;

	@JsonIgnore
	@OneToMany(mappedBy = "stop")
	List<StopTime> stopTimes;
	
	public String toString() {
		return "stop_id"+":"+this.stopId+","+"stop_name"+":"+this.stopName+","+"stop_lat"+":"+this.stopLat+","+"stop_lon"+":"+this.stopLon;
	}

}
