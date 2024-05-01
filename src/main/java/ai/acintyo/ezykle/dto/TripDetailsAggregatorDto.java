package ai.acintyo.ezykle.dto;

import lombok.Data;

@Data
public class TripDetailsAggregatorDto {

	private Integer id;
	private String userId;
	private String vehicleId;
	private Double avgSpeed;
	private Double battery;
	private Double distanceRemaining;
	private Double co2Saved;
	private Double distanceCovered;
	private Double usageTime;
	
}
