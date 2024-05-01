package ai.acintyo.ezykle.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ai.acintyo.ezykle.entities.VehicleData;

public interface VehicleDataRepository extends JpaRepository<VehicleData, Integer>{

	@Query("from VehicleData where vehicleName=:name")
	public Optional<VehicleData> findByVehicle_Name(String name);
	
	public List<VehicleData> findByUserId(String userId);
	
	Optional<VehicleData> findByVehicleNo(String vehicleNo);
}

 