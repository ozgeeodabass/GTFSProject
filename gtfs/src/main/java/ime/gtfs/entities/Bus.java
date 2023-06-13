package ime.gtfs.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "bus")
public class Bus {
	
	@Id
	@PrimaryKeyJoinColumn
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int busId;
	
	@ManyToOne
	@JoinColumn(name = "route_id")
	private Route route;
	
	@ManyToOne()
	@JoinColumn(name = "agency_id")
	private Agency agency;
	
	

}
