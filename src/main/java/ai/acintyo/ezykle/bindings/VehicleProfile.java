package ai.acintyo.ezykle.bindings;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;
@Data
@Component
public class VehicleProfile {

	
	private String vehicleName;
	
	private Integer userId;

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

}
