package ai.acintyo.ezykle.dto;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class TripDetailsDto {

	private String userId;
	private String vehicleId;
	private String source;
	private String sourceLatitude;
	private String sourceLongitude;
	private String destination;
	private String destinationLatitude;
	private String destinationLongitude;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private Double duration;
	private Double avgSpeed;
	private Double maxSpeed;
	private Double calories;
	private Double usageTime;
	private Double distance;
	
}
