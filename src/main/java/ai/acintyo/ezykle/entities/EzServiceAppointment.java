package ai.acintyo.ezykle.entities;


import org.hibernate.annotations.SQLDelete;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "EZ_SERVICE_APPOINTMENT")
@SQLDelete(sql="update EZ_SERVICE_APPOINTMENT set status='deleted' where id=?")

public class EzServiceAppointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	private String phno;

	private String vehicalModel;

	private String serviceRequestDate;
	
	private String serviceRequestTime;

	private String serviceType;

	private String registrationDate;

	private String serviceOptedOn;

	private String insterdBy;

	private String lastUpdatedOn;

	private String updatedBy;
	
	private String status="Active";

}
