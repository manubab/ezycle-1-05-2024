package ai.acintyo.ezykle.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import ai.acintyo.ezykle.bindings.UserRegistrationForm;
import ai.acintyo.ezykle.entities.EzUserRegistration;

public interface UserRegistrationService {

	public ResponseEntity<?> authenticate(UserRegistrationForm request);

	public ResponseEntity<?> saveRegistration(UserRegistrationForm registrationForm);

	public Page<EzUserRegistration> fetchAllUsers(Pageable pageable);

	public EzUserRegistration fetchUserById(Integer id);

	public ResponseEntity<?>  UpdateUserById(Integer id, UserRegistrationForm userForm);

	public String deleteUserById(Integer id);

}
