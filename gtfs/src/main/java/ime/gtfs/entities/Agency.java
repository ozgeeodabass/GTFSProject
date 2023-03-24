package ime.gtfs.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = "agency")
public class Agency {

	@Id
	@PrimaryKeyJoinColumn
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int agencyId;
	
	@Column(name = "agency_name")
	private String agencyName;
	
	@Column(name = "agency_url")
	private String agencyUrl;
	
	@Column(name = "agency_timezone")
	private String agencyTimezone;
	
	@OneToMany(mappedBy = "agency")
	@JsonIgnore
	private List<Bus> buses;
	
	
}
