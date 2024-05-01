package ai.acintyo.ezykle.templates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import ai.acintyo.ezykle.dto.VehicleRoot;
import ai.acintyo.ezykle.entities.VehicleData;
import ai.acintyo.ezykle.entities.VehicleMove;
import ai.acintyo.ezykle.services.IVehicleDataService;

@Component
public class VehicleDataTemplate {

	@Autowired
	private IVehicleDataService service;

	
	public VehicleData saveVehicleData()throws Exception
	{
		RestTemplate template = new RestTemplate();
		ResponseEntity<String> responseEntiy = template.getForEntity(
				"http://13.127.228.11/webservice?token=getLiveData&user=api@test&pass=Admin@123", String.class);
		System.out.println(responseEntiy);
		String body = responseEntiy.getBody();
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(body);
		VehicleRoot value = mapper.readValue(body, VehicleRoot.class);

		VehicleData vehicleData = value.getRoot().getVehicleData().get(0);
		VehicleData saveVehicle = service.saveVehicle(vehicleData);
		return saveVehicle;
	}
	// @Scheduled(cron="0 * * * * *")
	//@Scheduled(fixedDelay = 61000)
	public VehicleMove getVehicleData() throws Exception {
		RestTemplate template = new RestTemplate();
		ResponseEntity<String> responseEntiy = template.getForEntity(
				"http://13.127.228.11/webservice?token=getLiveData&user=api@test&pass=Admin@123", String.class);
		System.out.println(responseEntiy);
		String body = responseEntiy.getBody();
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("***********************");
		System.out.println(body);
		VehicleRoot value = mapper.readValue(body, VehicleRoot.class);

		VehicleData vehicleData = value.getRoot().getVehicleData().get(0);
		VehicleData saveVehicle = service.saveVehicle(vehicleData);
		return null;
	//	VehicleMove saveVehicleData = service.saveVehicleData(vehicleData);
	//	return saveVehicleData;
	}
}
