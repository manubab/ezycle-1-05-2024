package ai.acintyo.ezykle.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class VehicleRoot {

	@JsonProperty("root")
	private Root root;
}
