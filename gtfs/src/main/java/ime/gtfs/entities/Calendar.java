package ime.gtfs.entities;

import java.time.LocalDate;
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
@Table(name = "calendar" )
public class Calendar {

	@Id
	private int serviceId;
	
	@Column(name = "monday")
	private int monday; 
	
	@Column(name = "tuesday")
	private int tuesday; 
	
	@Column(name = "wednesday")
	private int wednesday; 
	
	@Column(name = "thursday")
	private int thursday; 
	
	@Column(name = "friday")
	private int friday; 
	
	@Column(name = "saturday")
	private int saturday; 
	
	@Column(name = "sunday")
	private int sunday; 
	
	@Column(name = "start_date")
	private LocalDate startDate;
	
	@Column(name = "end_date")
	private LocalDate endDate;
	
	@OneToMany(mappedBy = "serviceId")
	@JsonIgnore
	private List<Trip> trips;
	
	
}
