package ai.acintyo.ezykle.entities;

import org.hibernate.annotations.SQLDelete;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Setter
@Getter
@Table(name = "EZ_ADMIN_SERVICE_CENTER")
@SQLDelete(sql = "update EZ_ADMIN_SERVICE_CENTER set status='deleted' where id=?")

public class EzAdminServiceCenter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String centerName;

	private String centerLocation;

	private String contact;

	private String email;

	private String centerOpenTime;

	private String centerCloseTime;

	private String registrationDate;

	private String serviceOptedOn;

	private String insertedBy;

	private String lastUpdatedOn;

	private String updatedBy;

	private String status = "Active";
}
