package ai.acintyo.ezykle.bindings;

import ai.acintyo.ezykle.entities.EzUserRegistration;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class AuthenticationResponse {
	private String token;
	private String message;
	private EzUserRegistration ezUserRegistration;

	public AuthenticationResponse(String message) {
		this.message = message;
	}

	public AuthenticationResponse(String token, String message, EzUserRegistration ezUserRegistration) 
	{
		this.ezUserRegistration =ezUserRegistration;
		this.message=message;
		this.token=token;
	}
	
	

	
}
