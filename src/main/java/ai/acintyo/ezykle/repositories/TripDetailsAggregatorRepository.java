package ai.acintyo.ezykle.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ai.acintyo.ezykle.entities.TripDetailsAggregator;

public interface TripDetailsAggregatorRepository extends JpaRepository<TripDetailsAggregator, Integer>{

	
	//List<TripDetailsAggregator> findByUserId(String userId);
	
	Optional<TripDetailsAggregator> findByUserId(String userId);
	//Optional<TripDetailsAggregator> find
	Optional<TripDetailsAggregator> findByUserIdAndId(String userId,Integer id);
	Optional<TripDetailsAggregator> findByUserIdAndVehicleId(String userId,String vehicleId);
	
	
	
}
