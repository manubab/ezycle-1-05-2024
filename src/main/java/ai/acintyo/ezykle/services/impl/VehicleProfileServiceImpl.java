package ai.acintyo.ezykle.services.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ai.acintyo.ezykle.bindings.VehicleProfile;
import ai.acintyo.ezykle.entities.EzVehicleProfile;
import ai.acintyo.ezykle.model.ApiResponse;
import ai.acintyo.ezykle.repositories.VehicleProfileRepository;
import ai.acintyo.ezykle.services.VehileProfileService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VehicleProfileServiceImpl implements VehileProfileService {

	@Autowired
	private VehicleProfileRepository vehicleProfileRepository;

	// vehicle Registration service;
	@Override
	public ResponseEntity<?> registerVehicle(VehicleProfile profile) {
		
		EzVehicleProfile vehicleprofile = new EzVehicleProfile();
		vehicleprofile.setVehicleName(profile.getVehicleName());
		vehicleprofile.setRegistrationNumer(profile.getRegistrationNumer());
		vehicleprofile.setManufacturingYear(profile.getManufacturingYear());
		vehicleprofile.setModelName(profile.getModelName());
		vehicleprofile.setDeviceIMEI(profile.getDeviceIMEI());
		vehicleprofile.setDeviceFireware(profile.getDeviceFireware());
		vehicleprofile.setClassicNo(profile.getClassicNo());
		vehicleprofile.setVimNo(profile.getVimNo());
		vehicleprofile.setWarrenty(profile.getWarrenty());
		vehicleprofile.setBatteryValidTill(profile.getBatteryValidTill());
		vehicleprofile.setMoterValidTill(profile.getMoterValidTill());
		vehicleprofile.setControllerValidTill(profile.getControllerValidTill());

		vehicleprofile = vehicleProfileRepository.save(vehicleprofile);

		if (vehicleprofile != null) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(true,"profile created",vehicleprofile), HttpStatus.CREATED);
		}
		return new ResponseEntity<Object>("please provide valid details ", HttpStatus.CREATED);
	}

	

	List<EzVehicleProfile> listOfProfles = new LinkedList<>();

	@Override
	public ResponseEntity<?> registerListOfvehicleProfile(List<VehicleProfile> listOfVehicleProfiles) {

		for (VehicleProfile profile : listOfVehicleProfiles) {
			EzVehicleProfile vehicleProfile = new EzVehicleProfile();
			vehicleProfile.setVehicleName(profile.getVehicleName());
			vehicleProfile.setRegistrationNumer(profile.getRegistrationNumer());
			vehicleProfile.setManufacturingYear(profile.getManufacturingYear());
			vehicleProfile.setModelName(profile.getModelName());
			vehicleProfile.setDeviceIMEI(profile.getDeviceIMEI());
			vehicleProfile.setDeviceFireware(profile.getDeviceFireware());
			vehicleProfile.setClassicNo(profile.getClassicNo());
			vehicleProfile.setVimNo(profile.getVimNo());
			vehicleProfile.setWarrenty(profile.getWarrenty());
			vehicleProfile.setBatteryValidTill(profile.getBatteryValidTill());
			vehicleProfile.setMoterValidTill(profile.getMoterValidTill());
			vehicleProfile.setControllerValidTill(profile.getControllerValidTill());
			listOfProfles.add(vehicleProfile);
		}
		
		listOfProfles = vehicleProfileRepository.saveAll(listOfProfles);
		return new ResponseEntity<Object>(new ApiResponse(true," profiles created succesfully",listOfProfles), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<?> byVehicle(Integer vehicleId) {
		
		return null;
	}

	
	@Override
	public ResponseEntity<?> getVehicle(Integer userId) {
		log.info("get vehicle profile service started");
		Optional<EzVehicleProfile> vehicle=    vehicleProfileRepository.findByUserId(userId);
		if(vehicle.isPresent()) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(true,"vehicle profile successfully ",vehicle.get()),HttpStatus.OK);
		}
		log.info("get vehicle profile api is completed");
		return new ResponseEntity<Object>("vehicle not found",HttpStatus.OK);
	}

}
