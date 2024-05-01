package ai.acintyo.ezykle.services;

import java.util.List;

import ai.acintyo.ezykle.dto.VehicleDataDto;
import ai.acintyo.ezykle.entities.VehicleData;

public interface IVehicleDataService {

	//public VehicleMove saveVehicleData(VehicleData vehicleData);
	
	public VehicleData saveVehicle(VehicleData vehicleData);
	
	
    List<VehicleDataDto> fetchVechicleDataUserId(String userId);
    
    
	List<VehicleDataDto> fetchVehicleDataByVehicleDataId(Integer id);
	

	//vehicle details along aggregate data
		List<VehicleDataDto> fetchVehicleDataAllByUserIdAndVehicleDataId(String vehicleNo,String userId);

}
