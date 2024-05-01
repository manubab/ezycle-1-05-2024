package ai.acintyo.ezykle.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class VehicleMove {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String Vehicle_Name;

	private String Company;

	private String Temperature;
	
	private String Latitude;
	
	private String GPS;
	
	private String Vehicle_No;
	
	private String Door1;
	
	private String Door4;
	
	private String Branch;
	
	private String Vehicletype;
	
	private String Door2;
	
	private String Door3;
	
	private String GPSActualTime;
	
	private String Datetime;
	
	private String Status;
	
	private String DeviceModel;
	
	private String Speed;

	private String AC;
	
	private String Imeino;
	
	private String Odometer;
	
	private String POI;
	
	private String Driver_Middle_Name;
	
	private String Longitude;
	
	private String Immobilize_State;
	
	private String IGN;
	
	private String Driver_First_Name;
	
	private String Angle;

	private String SOS;

	private String[] Fuel;//-->doubt

	private String battery_percentage;
	
	private String ExternalVolt;

	private String Driver_Last_Name;
	
	private String Power;
	
	private String Location;
	
	@CreationTimestamp
	private LocalDateTime hist_rec_timestamp;
}
