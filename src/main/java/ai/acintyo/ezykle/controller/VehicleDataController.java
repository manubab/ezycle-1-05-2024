package ai.acintyo.ezykle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ai.acintyo.ezykle.dto.VehicleDataDto;
import ai.acintyo.ezykle.model.ApiResponse;
import ai.acintyo.ezykle.services.IVehicleDataService;
import ai.acintyo.ezykle.templates.VehicleDataTemplate;


@RestController
public class VehicleDataController {

	@Autowired
	private IVehicleDataService vehicleDataService;
	
	@Autowired
	private VehicleDataTemplate   vehicleDataTemplate;
	
	@GetMapping("/save-vehicle-data")
	public ResponseEntity<?> saveVehicleData()throws Exception
	{
		return ResponseEntity.ok(new ApiResponse<>(true,"vehicle data saved ",vehicleDataTemplate.saveVehicleData()));
	}
	
	@GetMapping("/get-vehicle-data/userIdBy/{userId}")
	public ResponseEntity<?> getVehicleDetails(@PathVariable String userId)
	{
		
	return	ResponseEntity.ok(new ApiResponse<>(true, "Vehicle data has been fetched Successfully",vehicleDataService.fetchVechicleDataUserId(userId)));
	}
	
	     //get vehicle data by vehicle data id
		@GetMapping("/get-vehicle-data/vehicleDataIdBy/{vehicleDataId}")
		public ResponseEntity<?> getVehicleDataByVehicleId(@PathVariable Integer vehicleDataId) {
			return ResponseEntity.ok(new ApiResponse<>(true,"Vehicle data has been fetched successfully",vehicleDataService.fetchVehicleDataByVehicleDataId(vehicleDataId)));
		}
		
		@GetMapping("/get-vehicle-data-all/vehicleNoBy/{vehicleNo}/userIdBy/{userId}")
		public ResponseEntity<ApiResponse<List<VehicleDataDto>>> getAllVehicleData(@PathVariable String vehicleNo,@PathVariable String userId)
		{
			return ResponseEntity.ok(new ApiResponse<>(true,"All vehicle Data has been fetched succesfully",vehicleDataService.fetchVehicleDataAllByUserIdAndVehicleDataId(vehicleNo,  userId)));
					
		}
	
		


	
	
}
