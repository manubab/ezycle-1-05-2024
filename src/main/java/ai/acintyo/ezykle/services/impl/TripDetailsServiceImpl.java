package ai.acintyo.ezykle.services.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ai.acintyo.ezykle.dto.TripDetailsAggregatorDto;
import ai.acintyo.ezykle.dto.TripDetailsDto;
import ai.acintyo.ezykle.entities.TripDetails;
import ai.acintyo.ezykle.entities.TripDetailsAggregator;
import ai.acintyo.ezykle.exception.DataNotFoundException;
import ai.acintyo.ezykle.repositories.TripDetailsAggregatorRepository;
import ai.acintyo.ezykle.repositories.TripDetailsRepository;
import ai.acintyo.ezykle.services.ITripDetailsService;

@Service
public class TripDetailsServiceImpl implements ITripDetailsService {

	@Autowired
	private TripDetailsRepository tripDetailsRepository;

	@Autowired
	private TripDetailsAggregatorRepository tripAggregatorRepository;


	@Override
	public TripDetails saveTripDetails(TripDetailsDto tripDetails) {
		
		System.out.println(tripDetails);
		
		TripDetails trip = new TripDetails();
		trip.setAvgSpeed(tripDetails.getAvgSpeed());
		trip.setCalories(tripDetails.getCalories());
		trip.setDestination(tripDetails.getDestination());
		trip.setDestinationLatitude(tripDetails.getDestinationLatitude());
		trip.setDestinationLongitude(tripDetails.getDestinationLongitude());
		trip.setDistance(tripDetails.getDistance());
		
		trip.setDuration(tripDetails.getDuration());
		trip.setEndDate(tripDetails.getEndDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm:ss")));
		trip.setInsertedOn(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm:ss")));
		trip.setMaxSpeed(tripDetails.getMaxSpeed());
		trip.setSource(tripDetails.getSource());
		trip.setSourceLatitude(tripDetails.getSourceLatitude());
		trip.setSourceLongitude(tripDetails.getSourceLongitude());
		trip.setStartDate(tripDetails.getStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm:ss")));
		trip.setUsageTime(tripDetails.getUsageTime());
		trip.setUserId(tripDetails.getUserId());
		trip.setVehicleId(tripDetails.getVehicleId());
		return tripDetailsRepository.save(trip);
	}

	@Override
	public List<TripDetails> fetchTripDetailsByUserIdAndVehicleId(String userId, String vehicleId) {

		List<TripDetails> userIdAndVehicleId = tripDetailsRepository.findByUserIdAndVehicleId(userId, vehicleId);
		if (userIdAndVehicleId.isEmpty()) {
			throw new IllegalArgumentException(
					"Trip details are not found by given userId:" + userId + ",vehicleId:" + vehicleId);
		}
		return userIdAndVehicleId;
	}

	@Override
	public List<TripDetails> fetchAllTripDetails() {
		// TODO Auto-generated method stub
		List<TripDetails> list = tripDetailsRepository.findAll();
		if (list.isEmpty()) {
			throw new DataNotFoundException("trip details not found");
		}
		return list;
	}

	@Override
	public List<TripDetails> fetchTripDetailsByUserId(String userId) {

		List<TripDetails> byUserId = tripDetailsRepository.findByUserId(userId);
		// TODO Auto-generated method stub
		if (byUserId.isEmpty()) {
			throw new IllegalArgumentException("trip details were not found by given userId:" + userId);
		}
		return byUserId;
	}

	@Override
	public TripDetails updateTripDetails(Integer tripId, TripDetailsDto tripUpdate) {

		Optional<TripDetails> optional = tripDetailsRepository.findById(tripId);
		if (optional.isPresent()) {
			TripDetails existDetails = optional.get();
			existDetails.setAvgSpeed(tripUpdate.getAvgSpeed());
			existDetails.setCalories(tripUpdate.getCalories());
			existDetails.setDestination(tripUpdate.getDestination());
			existDetails.setDestinationLatitude(tripUpdate.getDestinationLatitude());
			existDetails.setDestinationLongitude(tripUpdate.getDestinationLongitude());
			existDetails.setDistance(tripUpdate.getDistance());
			existDetails.setDuration(tripUpdate.getDuration());
			existDetails.setEndDate(tripUpdate.getEndDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm:ss")));
			existDetails.setMaxSpeed(tripUpdate.getMaxSpeed());
			existDetails.setSource(tripUpdate.getSource());
			existDetails.setSourceLatitude(tripUpdate.getSourceLatitude());
			existDetails.setSourceLongitude(tripUpdate.getSourceLongitude());
			existDetails.setStartDate(tripUpdate.getSource());
			existDetails.setUpdatedOn(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm:ss")));
			existDetails.setUsageTime(tripUpdate.getUsageTime());
			existDetails.setUserId(tripUpdate.getUserId());
			existDetails.setVehicleId(tripUpdate.getVehicleId());
			return tripDetailsRepository.save(existDetails);
		} else {
			throw new IllegalArgumentException("trip details were not found by given tripId:" + tripId);
		}
	}

	@Override
	public TripDetailsAggregator saveTripAggregator(TripDetailsAggregatorDto tripAggregator) {

		TripDetailsAggregator aggregator = new TripDetailsAggregator();
		aggregator.setAvgSpeed(tripAggregator.getAvgSpeed());
		aggregator.setBattery(tripAggregator.getBattery());
		aggregator.setCo2Saved(tripAggregator.getCo2Saved());
		aggregator.setDistanceCovered(tripAggregator.getDistanceCovered());
		aggregator.setDistanceRemaining(tripAggregator.getDistanceRemaining());
		aggregator.setInsertedOn(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm:ss")));
		aggregator.setUsageTime(tripAggregator.getUsageTime());
		aggregator.setUserId(tripAggregator.getUserId());
		aggregator.setVehicleId(tripAggregator.getVehicleId());

		return tripAggregatorRepository.save(aggregator);
	}

	@Override
	public List<TripDetailsAggregator> fetchTripAggregatorByUserIdAndVehicleId(String userId, String vehicleId) {
		Optional<TripDetailsAggregator>  byUserIdAndVehicleId = tripAggregatorRepository.findByUserIdAndVehicleId(userId,
				vehicleId);
		if (byUserIdAndVehicleId.isEmpty()) {
			throw new IllegalArgumentException("Trip Details Aggregator details were not found by given userId:"
					+ userId + ",and vehicleId:" + vehicleId);
		} else {
		return	List.of(byUserIdAndVehicleId.get());
		}
	}

	@Override
	public List<TripDetailsAggregator> fetchAllTripAggregatorData() {
		List<TripDetailsAggregator> list = tripAggregatorRepository.findAll();
		if (list.isEmpty()) {
			throw new DataNotFoundException("Data not found");
		} else {
			return list;
		}
	}

	/*
	 * @Override public List<TripDetailsAggregator>
	 * fetchTripDetailsAggregatorByUserId(String userId) { // TODO Auto-generated
	 * method stub List<TripDetailsAggregator> byUserId =
	 * tripAggregatorRepository.findByUserId(userId); if (byUserId.isEmpty()) {
	 * throw new
	 * IllegalArgumentException("trip details aggregator were not found by given userId:"
	 * + userId); } return byUserId; }
	 */

	@Override
	public TripDetailsAggregator updateTripDetailsAggregator(Integer agId, TripDetailsAggregatorDto aggregatorDto) {

		Optional<TripDetailsAggregator> optional = tripAggregatorRepository.findById(agId);
		if (optional.isPresent()) {
			TripDetailsAggregator existAggregator = optional.get();
			existAggregator.setAvgSpeed(aggregatorDto.getAvgSpeed());
			existAggregator.setBattery(aggregatorDto.getBattery());
			existAggregator.setCo2Saved(aggregatorDto.getCo2Saved());
			existAggregator.setDistanceCovered(aggregatorDto.getDistanceCovered());
			existAggregator.setDistanceRemaining(aggregatorDto.getDistanceRemaining());
			existAggregator
					.setUpdatedOn(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm:ss")));
			existAggregator.setUsageTime(aggregatorDto.getUsageTime());
			existAggregator.setUserId(aggregatorDto.getUserId());
			existAggregator.setVehicleId(aggregatorDto.getVehicleId());
			return tripAggregatorRepository.save(existAggregator);
		} else {
			throw new IllegalArgumentException("Trip details aggregator not found by given aggregator id:" + agId);
		}
	}
	
	//tripDetails service

	//fetch trip details by tripid
		@Override
		public TripDetails fetchTripDetailsByTripId(Integer id) {
			
			Optional<TripDetails> optional = tripDetailsRepository.findById(id);
			if(optional.isPresent())
			{
				return optional.get();
			}
			else
			{
				throw new IllegalArgumentException("trip details were not found by trip id:"+id);
			}
		}

		@Override
		public List<TripDetailsAggregator> fetchTripDetailsAggregatorByUserId(String userId) {
			// TODO Auto-generated method stub
			return null;
		}

}
