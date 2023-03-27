package ime.gtfs.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "shapes")
public class Shape {
	
	@Id
	private int shapeId;
	
	@Column(name="shape_pt_lat")
	private double shapePtLat;
	
	@Column(name="shape_pt_lon")
	private double shapePtLon;
	
	@Column(name="shape_pt_sequence")
	@Min(0)
	private int shapePtSequence;
	
	@OneToMany(mappedBy ="shape")
	@JsonIgnore
	private List<Trip> trips; 
	
	
}
