package ai.acintyo.ezykle.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="EZ_USER_ACCOUNT")
public class EzUserAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer userId;
	
	private String bankName;
	
	private String accountNumber;
	
	private String ifscCode;
	
	private String branch;
	
	private String registrationDate;
		
	private String serviceOptedOn;

	private String insertedBy;

	private String lastUpdatedOn;
	
	private String updatedBy;
	
	@Column(name="delete_by")
	private String  deleteBy;

}
