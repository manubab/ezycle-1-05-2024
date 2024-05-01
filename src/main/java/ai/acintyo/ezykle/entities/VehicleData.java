package ai.acintyo.ezykle.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class VehicleData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String userId;

	@JsonProperty("Vehicle_Name")
	private String vehicleName;

	@JsonProperty("Company")
	private String company;

	@JsonProperty("Temperature")
	private String temperature;

	@JsonProperty("Latitude")
	private String latitude;

	@JsonProperty("GPS")
	private String gps;

	@JsonProperty("Vehicle_No")
	private String vehicleNo;

	@JsonProperty("Door1")
	private String door1;

	@JsonProperty("Door4")
	private String door4;

	@JsonProperty("Branch")
	private String branch;

	@JsonProperty("Vehicletype")
	private String vehicleType;

	@JsonProperty("Door2")
	private String door2;

	@JsonProperty("Door3")
	private String door3;

	@JsonProperty("GPSActualTime")
	private String gpsActualTime;

	@JsonProperty("Datetime")
	private String dateTime;

	@JsonProperty("Status")
	private String status;

	@JsonProperty("DeviceModel")
	private String deviceModel;

	@JsonProperty("Speed")
	private String speed;
	@JsonProperty("AC")
	private String ac;
	@JsonProperty("Imeino")
	private String imeino;

	@JsonProperty("Odometer")
	private String odometer;

	@JsonProperty("POI")
	private String poi;

	@JsonProperty("Driver_Middle_Name")
	private String driverMiddleName;

	@JsonProperty("Longitude")
	private String longitude;

	@JsonProperty("Immobilize_State")
	private String immobilizeState;

	@JsonProperty("IGN")
	private String ign;

	@JsonProperty("Driver_First_Name")
	private String driverFirstName;
	@JsonProperty("Angle")
	private String angle;
	@JsonProperty("SOS")
	private String sos;
	@JsonProperty("Fuel")
	private String[] fuel;// -->doubt
	@JsonProperty("battery_percentage")
	private String batteryPercentage;
	@JsonProperty("ExternalVolt")
	private String externalVolt;
	@JsonProperty("Driver_Last_Name")
	private String driverLastName;
	@JsonProperty("Power")
	private String power;
	@JsonProperty("Location")
	private String location;

	@CreationTimestamp
	private LocalDateTime histRecTimestamp;

}

/*{
    "root": {
        "VehicleData": [
            {
                "Vehicle_Name": "KB01-23110242",
                "Company": "KBike",
                "Temperature": "--",
                "Latitude": "17.3802989",
                "GPS": "ON",
                "Vehicle_No": "N23L-07798",
                "Door1": "--",
                "Door4": "--",
                "Branch": "KBike",
                "Vehicletype": "Bicycle",
                "Door2": "--",
                "Door3": "--",
                "GPSActualTime": "26-04-2024 23:00:13",
                "Datetime": "26-04-2024 23:00:14",
                "Status": "INACTIVE",
                "DeviceModel": "ACUMEN X-1",
                "Speed": "20",
                "AC": "--",
                "Imeino": "868720062305283",
                "Odometer": "0",
                "POI": "--",
                "Driver_Middle_Name": "--",
                "Longitude": "78.4396178",
                "Immobilize_State": "--",
                "IGN": "ON",
                "Driver_First_Name": "--",
                "Angle": "195",
                "SOS": "--",
                "Fuel": [],
                "battery_percentage": "617",
                "ExternalVolt": "37.00",
                "Driver_Last_Name": "--",
                "Power": "ON",
                "Location": "Gautami Vidyaniketan High School, 13-6-457/76, Talla Gadda Road, Haridas Nagar, Gayatri Nagar, Gudimalkapur, Hyderabad, Talla Gadda Rd, Haridas Nagar, Gayatri Nagar, Gudimalkapur, Hyderabad, Telangana (SW)"
            }
        ]
    }*/
