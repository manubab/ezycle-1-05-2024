package ai.acintyo.ezykle.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ai.acintyo.ezykle.dto.TripDetailsAggregatorDto;
import ai.acintyo.ezykle.dto.VehicleDataDto;
import ai.acintyo.ezykle.entities.TripDetailsAggregator;
import ai.acintyo.ezykle.entities.VehicleData;
import ai.acintyo.ezykle.exception.DataNotFoundException;
import ai.acintyo.ezykle.repositories.TripDetailsAggregatorRepository;
import ai.acintyo.ezykle.repositories.VehicleDataRepository;
import ai.acintyo.ezykle.repositories.VehicleMoveRepository;
import ai.acintyo.ezykle.services.IVehicleDataService;

@Service
public class VehicleDataServiceImpl implements IVehicleDataService {

	@Autowired
	private VehicleDataRepository vehicleDataRepository;
	
	@Autowired
	private VehicleMoveRepository vehicleMoveRepository;
	
	@Autowired
	private TripDetailsAggregatorRepository tripDetailsAggregatorRepo;
	
	
	
	@Override
	public VehicleData saveVehicle(VehicleData vehicleData) {
		
		vehicleData.setUserId("A1234");
		return vehicleDataRepository.save(vehicleData);
	}
	
	/*@Override
	public VehicleMove saveVehicleData(VehicleData vehicleData) {
		String vehicle_Name = vehicleData.getVehicleName();
		Optional<VehicleData> byVehicle_Name = vehicleDataRepository.findByVehicle_Name(vehicle_Name);
	    if(byVehicle_Name.isPresent())
	    {
	    	VehicleData existedVehicle = byVehicle_Name.get();
	    	BeanUtils.copyProperties(vehicleData, existedVehicle);
	    	vehicleDataRepository.save(existedVehicle);
	        
	    }
	    else
	    {
	    	vehicleDataRepository.save(vehicleData);
	    }
	    VehicleMove move=null;
	    Optional<VehicleData> vehicleMoveCheck = vehicleDataRepository.findByVehicle_Name(vehicle_Name);
	    if(vehicleMoveCheck.isPresent())
	    {
	    	VehicleData firstStatus= vehicleMoveCheck.get();
	    	if(!firstStatus.getLatitude().equals(vehicleData.getLatitude())&&!firstStatus.getLongitude().equals(vehicleData.getLongitude()))
	    	{
	    		VehicleMove vehicleMove = new VehicleMove();
	    		BeanUtils.copyProperties(vehicleData, vehicleMove);
	    		move = vehicleMoveRepository.save(vehicleMove);
	    	}
	    	
	    }
	    return move;
	    
	    
	}*/
	
	@Override
	public List<VehicleDataDto> fetchVechicleDataUserId(String userId) 
	{
		List<VehicleDataDto>  vehicleDataDtoList=new ArrayList<>();
		
		
		 List<VehicleData> listVehicleData = vehicleDataRepository.findByUserId(userId);
		
		 
		 
		 listVehicleData.forEach(vehicle->{
			 
			 VehicleDataDto vehicleDataDto=new VehicleDataDto();
			 Optional<TripDetailsAggregator> optional=tripDetailsAggregatorRepo.findByUserIdAndVehicleId(vehicle.getUserId(), vehicle.getVehicleNo());
			
			 TripDetailsAggregator tripDetailsAggregator= new TripDetailsAggregator();
			
			 if(optional.isPresent())
			 {
				tripDetailsAggregator = optional.get();
			 }
			 TripDetailsAggregatorDto tripDetailsAggregatorDto = new TripDetailsAggregatorDto();
			 BeanUtils.copyProperties(tripDetailsAggregator, tripDetailsAggregatorDto);
			 vehicleDataDto.setTripDetailsAggregator(tripDetailsAggregatorDto);
			 vehicleDataDto.setAc(vehicle.getAc());
			 vehicleDataDto.setAngle(vehicle.getAngle());
			 vehicleDataDto.setBatteryPercentage(vehicle.getBatteryPercentage());
			 vehicleDataDto.setBranch(vehicle.getBranch());
			 vehicleDataDto.setCompany(vehicle.getCompany());
			 vehicleDataDto.setDateTime(vehicle.getDateTime());
			 vehicleDataDto.setDeviceModel(vehicle.getDeviceModel());
			 vehicleDataDto.setDoor1(vehicle.getDoor1());
			 vehicleDataDto.setDoor2(vehicle.getDoor2());
			 vehicleDataDto.setDoor3(vehicle.getDoor3());
			 vehicleDataDto.setDoor4(vehicle.getDoor4());
			 vehicleDataDto.setDriverFirstName(vehicle.getDriverFirstName());
			 vehicleDataDto.setDriverLastName(vehicle.getDriverLastName());
			 vehicleDataDto.setDriverMiddleName(vehicle.getDriverMiddleName());
			 vehicleDataDto.setExternalVolt(vehicle.getExternalVolt());
			 vehicleDataDto.setFuel(vehicle.getFuel());
			 vehicleDataDto.setGps(vehicle.getGps());
			 vehicleDataDto.setGpsActualTime(vehicle.getGpsActualTime());
			 vehicleDataDto.setHistRecTimestamp(vehicle.getHistRecTimestamp());
			 vehicleDataDto.setId(vehicle.getId());
			 vehicleDataDto.setIgn(vehicle.getIgn());
			 vehicleDataDto.setImeino(vehicle.getImeino());
			 vehicleDataDto.setImmobilizeState(vehicle.getImmobilizeState());
			 vehicleDataDto.setLatitude(vehicle.getLatitude());
			 vehicleDataDto.setLocation(vehicle.getLocation());
			 vehicleDataDto.setLongitude(vehicle.getLongitude());
			 vehicleDataDto.setOdometer(vehicle.getOdometer());
			 vehicleDataDto.setPoi(vehicle.getPoi());
			 vehicleDataDto.setPower(vehicle.getPower());
			 vehicleDataDto.setSos(vehicle.getSos());
			 vehicleDataDto.setSpeed(vehicle.getSpeed());
			 vehicleDataDto.setStatus(vehicle.getStatus());
			 vehicleDataDto.setTemperature(vehicle.getTemperature());
			 vehicleDataDto.setUserId(vehicle.getUserId());
			 vehicleDataDto.setVehicleName(vehicle.getVehicleName());
			 vehicleDataDto.setVehicleNo(vehicle.getVehicleNo());
			 vehicleDataDto.setVehicleType(vehicle.getVehicleType());
			 
			 
			 vehicleDataDtoList.add(vehicleDataDto);
		 });
		 
		 if(vehicleDataDtoList.isEmpty())
		 {
			 throw new DataNotFoundException("data not found");
		 }
		 
		 return vehicleDataDtoList;
				 
	}
	
	@Override
	public List<VehicleDataDto> fetchVehicleDataByVehicleDataId(Integer id) {
		
		Optional<VehicleData> optional = vehicleDataRepository.findById(id);
		if(optional.isPresent())
		{
			VehicleDataDto vehicleDataDto = new VehicleDataDto();
			 VehicleData vehicleData = optional.get();
			 BeanUtils.copyProperties(vehicleData, vehicleDataDto);
			 return List.of(vehicleDataDto);
		}
		else
		{
			throw new IllegalArgumentException("Vehicle data not found by given id:"+id);
		}
	}
	

@Override
	public List<VehicleDataDto> fetchVehicleDataAllByUserIdAndVehicleDataId(String  vehicleNo,String userId) {
		
		Optional<VehicleData> optional = vehicleDataRepository.findByVehicleNo(vehicleNo);
		VehicleDataDto vehicleDataDto = new VehicleDataDto();
		
		if(optional.isPresent())
		{
			VehicleData vehicleData = optional.get();
				BeanUtils.copyProperties(vehicleData, vehicleDataDto);
		}
		else
		{
			throw new IllegalArgumentException("vehicle data not found by given vehicleNo:"+vehicleNo);
		}
		TripDetailsAggregatorDto detailsAggregatorDto = new TripDetailsAggregatorDto();
		
		Optional<TripDetailsAggregator> byUserIdAndVehicleId = tripDetailsAggregatorRepo.findByUserIdAndVehicleId(userId,vehicleNo);
		if(byUserIdAndVehicleId.isPresent())
		{
			TripDetailsAggregator detailsAggregator = byUserIdAndVehicleId.get();
			BeanUtils.copyProperties(detailsAggregator, detailsAggregatorDto);
			vehicleDataDto.setTripDetailsAggregator(detailsAggregatorDto);
		}
		else
		{
			throw new IllegalArgumentException("trip details aggregators not found by given userId: "+userId+", vehicle id:"+vehicleNo);
		}
		
		return List.of(vehicleDataDto);
	}
	

	
}
