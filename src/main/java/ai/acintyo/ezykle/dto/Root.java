package ai.acintyo.ezykle.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import ai.acintyo.ezykle.entities.VehicleData;
import lombok.Data;
@Data
public class Root {
	
	 @JsonProperty("VehicleData")
	private  List<VehicleData> VehicleData;
}
