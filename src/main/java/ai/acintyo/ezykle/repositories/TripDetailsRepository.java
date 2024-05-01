package ai.acintyo.ezykle.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ai.acintyo.ezykle.entities.TripDetails;

public interface TripDetailsRepository extends JpaRepository<TripDetails,Integer>{

	List<TripDetails> findByUserIdAndVehicleId(String userId,String vehicleId);
	
	List<TripDetails> findByUserId(String userId);
}
