package ai.acintyo.ezykle.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import ai.acintyo.ezykle.bindings.VehicleProfile;

public interface VehileProfileService
{
	
	public ResponseEntity<?>  registerVehicle(VehicleProfile profile);
	
	public ResponseEntity<?>  byVehicle(Integer vehicleId);

	public ResponseEntity<?> registerListOfvehicleProfile(List<VehicleProfile> listOfVehicleProfiles);

	public ResponseEntity<?> getVehicle(Integer userId);

	

	

}
