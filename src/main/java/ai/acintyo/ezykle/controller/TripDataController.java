package ai.acintyo.ezykle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ai.acintyo.ezykle.dto.TripDetailsAggregatorDto;
import ai.acintyo.ezykle.dto.TripDetailsDto;
import ai.acintyo.ezykle.entities.TripDetails;
import ai.acintyo.ezykle.model.ApiResponse;
import ai.acintyo.ezykle.services.ITripDetailsService;


@RestController
@RequestMapping("/trip")
public class TripDataController {

	@Autowired
	private ITripDetailsService tripDetailsService;
	
	@PostMapping("/save-trip-details")
	public ResponseEntity<?> saveTripDetails(@RequestBody TripDetailsDto tripDetails)
	{
		System.out.println(tripDetails);
	  return ResponseEntity.ok(new ApiResponse<>(true,"Trip Details saved successfully",tripDetailsService.saveTripDetails(tripDetails)));
	}
	
	//get trip details using userId and vehicleId
	@GetMapping("/get-trip-details/userIdBy/{userId}/vehicleIdBy/{vehicleId}")
	public ResponseEntity<?> getTripDetailsByUserIdAndVehicleId(@PathVariable String userId,@PathVariable String vehicleId)
	{
		return ResponseEntity.ok(new ApiResponse<>(true, "Trip Details has been fetched successfully", tripDetailsService.fetchTripDetailsByUserIdAndVehicleId(userId, vehicleId)));
	}
	
	@GetMapping("/get-all-trip-details")
	public ResponseEntity<?> getAllTripDetails()
	{
		return ResponseEntity.ok(new ApiResponse<>(true,"Trip Details has been fetched successfully",tripDetailsService.fetchAllTripDetails()));
	}
	
	
	
	@GetMapping("/get-trip-details/userIdBy/{userId}")
	public ResponseEntity<?> getTripDetailsByUserId(@PathVariable String userId)
	{
		return ResponseEntity.ok(new ApiResponse<>(true,"Trip details has been fetched successfully",tripDetailsService.fetchTripDetailsByUserId(userId)));
	}
	
	@PutMapping("/update-trip-details/tripIdBy/{tripIdBy}")
	public ResponseEntity<ApiResponse<TripDetails>> updateTripDetails(@PathVariable Integer tripIdBy,@RequestBody TripDetailsDto tripDetails)
	{
		return ResponseEntity.ok(new ApiResponse<>(true,"Trip details are updated succesfully:",tripDetailsService.updateTripDetails(tripIdBy, tripDetails)));
	}
	
	
	//trip controller

	//find trip details by trip id
		@GetMapping("/get-trip-details/tripIdBy/{tripId}")
		public ResponseEntity<ApiResponse<TripDetails>> getTripDetailsByTripId(@PathVariable Integer tripId)
		{
			return ResponseEntity.ok(new ApiResponse<>(true,"Trip detials has been fetched successfully",tripDetailsService.fetchTripDetailsByTripId(tripId)));
		}

	
	
	//trip Aggregator save
	
	@PostMapping("/save-trip-aggregator")
	public ResponseEntity<?> saveTripAggregatorData(@RequestBody TripDetailsAggregatorDto tripAggregator)
	{
		return ResponseEntity.ok(new ApiResponse<>(true,"Trip aggregator data saved successfully",tripDetailsService.saveTripAggregator(tripAggregator)));
	}
	
	@GetMapping("/get-trip-aggregator/userIdBy/{userId}/vehicleIdBy/{vehicleId}")
	public ResponseEntity<?> getTripAggregatorData(@PathVariable String userId,@PathVariable String vehicleId)
	{
		return ResponseEntity.ok(new ApiResponse<>(true,"Trip aggregator data has been fetched successfully",tripDetailsService.fetchTripAggregatorByUserIdAndVehicleId(userId, vehicleId)));
	}
	
	@GetMapping("/get-all-trip-aggregator")
	public ResponseEntity<?> getAllTripAggregatorData() {
		return  ResponseEntity.ok(new ApiResponse<>(true,"Trip Aggregator data has been fetched successfully",tripDetailsService.fetchAllTripAggregatorData()));
	}
	
	@PutMapping("/update-trip-aggregator/aggregateIdBy/{aggregateIdBy}")
	public ResponseEntity<?> updateTripDetailsAggregator(@PathVariable Integer aggregateIdBy,@RequestBody TripDetailsAggregatorDto aggregator)
	{	
		return ResponseEntity.ok(new ApiResponse<>(true,"Trip aggregator updated successfully",tripDetailsService.updateTripDetailsAggregator(aggregateIdBy, aggregator)));
	}
	
	
}
