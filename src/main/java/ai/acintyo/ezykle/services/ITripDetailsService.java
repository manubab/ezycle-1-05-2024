package ai.acintyo.ezykle.services;

import java.util.List;

import ai.acintyo.ezykle.dto.TripDetailsAggregatorDto;
import ai.acintyo.ezykle.dto.TripDetailsDto;
import ai.acintyo.ezykle.entities.TripDetails;
import ai.acintyo.ezykle.entities.TripDetailsAggregator;

public interface ITripDetailsService {

	TripDetails saveTripDetails(TripDetailsDto tripDetails);
	
	List<TripDetails> fetchTripDetailsByUserIdAndVehicleId(String userId,String vehicleId);
	
	List<TripDetails> fetchAllTripDetails();
	
	List<TripDetails> fetchTripDetailsByUserId(String userId);
	
	TripDetails updateTripDetails(Integer id,TripDetailsDto tripUpdate);
	
	TripDetailsAggregator  saveTripAggregator(TripDetailsAggregatorDto tripAggregator);
	
	List<TripDetailsAggregator> fetchTripAggregatorByUserIdAndVehicleId(String userId,String vehicleId);
	
	List<TripDetailsAggregator> fetchAllTripAggregatorData();
	
	List<TripDetailsAggregator> fetchTripDetailsAggregatorByUserId(String userId);
	
	//service interface
	TripDetails fetchTripDetailsByTripId(Integer id);

	
	TripDetailsAggregator updateTripDetailsAggregator(Integer agId,TripDetailsAggregatorDto aggregatorDto);
}
