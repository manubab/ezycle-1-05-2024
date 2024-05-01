package ai.acintyo.ezykle.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class TripDetails  {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String userId;
	private String vehicleId;
	private String source;
	private String sourceLatitude;
	private String sourceLongitude;
	private String destination;
	private String destinationLatitude;
	private String destinationLongitude;
	private String startDate;
	private String endDate;
	private Double duration;
	private Double avgSpeed;
	private Double maxSpeed;
	private Double calories;
	private Double usageTime;
	private Double distance;
	private String insertedOn;
	private String insertedBy;
	private String updatedOn;
	private String updatedBy;
}
