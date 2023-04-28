package ime.gtfs.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
//@IdClass(ShapeId.class)
public class Shape {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "unique_id")
	private int UniqueId;
	
	
	@Column(name="shape_id")
	private int shapeId;

	@Column(name = "shape_pt_lat")
	private double shapePtLat;

	
	@Column(name = "shape_pt_lon")
	private double shapePtLon;


	@Min(0)
	@Column(name = "shape_pt_sequence")
	private int shapePtSequence;
	
	@ManyToMany(mappedBy = "shapes")
	@JsonIgnore
	private List<Trip> trips; 
	
	
	public String toString() {
		return "shape_id"+":"+this.shapeId+","+"shape_pt_lat"+":"+this.shapePtLat+","+"shape_pt_lon"+":"+this.shapePtLon+","+ "shape_pt_sequence"+":"+this.shapePtSequence;
	}
	
}
