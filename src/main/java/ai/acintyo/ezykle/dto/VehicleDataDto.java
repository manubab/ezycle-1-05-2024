package ai.acintyo.ezykle.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class VehicleDataDto {

	private Integer id;

	private String userId;

	private String vehicleName;

	private String company;

	private String temperature;

	private String latitude;

	private String gps;

	private String vehicleNo;

	private String door1;

	private String door4;

	private String branch;

	private String vehicleType;

	private String door2;

	private String door3;

	private String gpsActualTime;

	private String dateTime;

	private String status;

	private String deviceModel;

	private String speed;
	private String ac;
	private String imeino;

	private String odometer;

	private String poi;

	private String driverMiddleName;

	private String longitude;

	private String immobilizeState;

	private String ign;

	private String driverFirstName;
	private String angle;
	private String sos;
	private String[] fuel;// -->doubt
	private String batteryPercentage;
	private String externalVolt;
	private String driverLastName;
	private String power;
	private String location;

	private LocalDateTime histRecTimestamp;
	
	private TripDetailsAggregatorDto tripDetailsAggregator;

}
