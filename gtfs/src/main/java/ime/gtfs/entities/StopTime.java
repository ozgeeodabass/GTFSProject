package ime.gtfs.entities;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="stop_times")
public class StopTime {
	
	@Id
	@PrimaryKeyJoinColumn
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int stopTimeId;
	
	@ManyToOne()
	@JsonIgnore
	@JoinColumn(name = "trip_id")
	private Trip trip; //Trip relation olcak
	
	@Column(name="arrival_time")
	private LocalTime arrivalTime;
	
	@Column(name="departure_time")
	private LocalTime departureTime;
	
	@ManyToOne()
	@JsonIgnore
	@JoinColumn(name = "stop_id")
	private Stop stop;
	
	@Column(name = "stop_sequence")
	@Min(value = 0)
	private int stopSequence;
	
	@Column(name="timepoint")
	private int timePoint; //0 or 1
	
	
	public String toString() {
		return "stoptime_id"+":"+this.stopTimeId+","+"arrival_time"+":"+this.arrivalTime+","+"deprture_time"+":"+this.departureTime+","+ "stop_sequence"+":"+this.stopSequence+","+	"timepoint"+":"+this.timePoint+","+"stop"+":"+ this.stop;
	}
	

}
