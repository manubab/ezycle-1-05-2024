package ai.acintyo.ezykle.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ai.acintyo.ezykle.entities.EzVehicleProfile;

public interface VehicleProfileRepository extends  JpaRepository<EzVehicleProfile, Integer> 
{
	
   Optional<EzVehicleProfile> findByUserId(Integer id);
}
