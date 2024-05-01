package ai.acintyo.ezykle.bindings;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserRegistrationForm {

	@NotBlank(message = "{user.registration.nameRequired}")
	private String name;

	@NotBlank(message = "{user.registration.mobileNumberRequired}")
	private String mobileNumber;

	@NotBlank(message = "{user.registration.emailRequired}")
	@Email(message = "{user.registration.emailFormat}")
	private String email;

	@NotBlank(message = "{user.registration.passwordRequired}")
	private String password;

	@NotBlank(message = "{user.registration.confirmPasswordRequired}")
	private String confirmPassword;

	@Pattern(regexp = "^(ADMIN|USER)$", message = "Invalid role. Only ADMIN or USER roles are allowed.")
	private String role;
}
