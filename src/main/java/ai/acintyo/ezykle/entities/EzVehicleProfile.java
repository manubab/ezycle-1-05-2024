package ai.acintyo.ezykle.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Table(name="Ez_VehicleProfilesData")
public class EzVehicleProfile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer vehicleProfileId;
	
	private Integer userId;
	
	private String vehicleName;

	private String registrationNumer;

	private Integer manufacturingYear;

	private String modelName;

	private Long deviceIMEI;

	private String deviceFireware;

	private Integer classicNo;

	private Integer vimNo;

	private String warrenty;

	private Date batteryValidTill;

	private Date moterValidTill;

	private Date controllerValidTill;
	
	private EzUserRegistration ezUserRegistration;

}
