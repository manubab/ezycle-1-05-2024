package ai.acintyo.ezykle.bindings;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
@Data
public class EzUserAccountDetails {
	
//	@NotBlank(message="{user.registration.id}")
	private Integer userId;
	
	@NotBlank(message = "{user.registration.bankNameRequired}")
	private String bankName;

	@NotBlank(message = "{user.registration.accountNumberRequired}")
	@Pattern(regexp = "\\d+", message = "{user.registration.accountNumberFormat}")
	private String accountNumber;

	@NotBlank(message = "{user.registration.ifscCodeRequired}")
	@Pattern(regexp = "[A-Z]{4}0[A-Z0-9]{6}", message = "{user.registration.ifscCodeFormat}")
	private String ifscCode;

	@NotBlank(message = "{user.registration.bankBranchRequired}")
	private String bankBranch;
	
	
	

}
