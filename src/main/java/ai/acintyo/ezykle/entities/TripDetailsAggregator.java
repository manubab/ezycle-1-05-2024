package ai.acintyo.ezykle.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class TripDetailsAggregator {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String userId;
	private String vehicleId;
	private Double avgSpeed;
	private Double battery;
	private Double distanceRemaining;
	private Double co2Saved;
	private Double distanceCovered;
	private Double usageTime;
	private String insertedOn; 
	private String insertedBy;
	private String updatedOn;
	private String updatedBy;
}
