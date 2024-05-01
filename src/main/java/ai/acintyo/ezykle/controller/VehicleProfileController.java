package ai.acintyo.ezykle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ai.acintyo.ezykle.bindings.VehicleProfile;
import ai.acintyo.ezykle.services.VehileProfileService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/ezycle-vehicleprofile")
@Slf4j
public class VehicleProfileController {

	@Autowired
	private VehileProfileService vehicleProfileService;

	// vehicle profile registration end point
	@PostMapping("/register/vehicle")
	public ResponseEntity<?> registerVehicle(@RequestBody VehicleProfile vehicleProfile) {
		return vehicleProfileService.registerVehicle(vehicleProfile);
	}

	@PostMapping("/register/listOfvehicleProfiles")
	public ResponseEntity<?> registerListOfvehicleProfile(@RequestBody List<VehicleProfile> listOfVehicleProfiles) {

		return vehicleProfileService.registerListOfvehicleProfile(listOfVehicleProfiles);

	}

	@PostMapping("/byVehicle/{vehicleId}")
	public ResponseEntity<?> byVehicle(@PathVariable("vehicleId") Integer vehicleId) {
		return vehicleProfileService.byVehicle(vehicleId);
	}

	@PostMapping("/getVehicle/{userId}")
	public ResponseEntity<?> getVehicle(@PathVariable("userId") Integer userId) {
		log.info("get vehicle api started");
		return vehicleProfileService.getVehicle(userId);
	}

}
