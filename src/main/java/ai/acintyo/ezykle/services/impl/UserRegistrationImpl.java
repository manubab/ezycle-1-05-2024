package ai.acintyo.ezykle.services.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ai.acintyo.ezykle.bindings.AuthenticationResponse;
import ai.acintyo.ezykle.bindings.Role;
import ai.acintyo.ezykle.bindings.UserRegistrationForm;
import ai.acintyo.ezykle.entities.EzUserRegistration;
import ai.acintyo.ezykle.entities.Token;
import ai.acintyo.ezykle.exception.DataNotFoundException;
import ai.acintyo.ezykle.jwtservice.JwtService;
import ai.acintyo.ezykle.repositories.TokenRepository;
import ai.acintyo.ezykle.repositories.UserRegistrationRepo;
import ai.acintyo.ezykle.services.UserRegistrationService;
import ai.acintyo.ezykle.util.FormatterDateTime;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Data
@ConfigurationProperties(prefix="user.registration")
public class UserRegistrationImpl implements UserRegistrationService {

	private String userLoginSuccess;
	private String userLoginFail;
	
	private String userLoginFailMessage;
    private String userExisted;
    private String saveError;
    
    private String success;
    private String userDataNotFound;
    private String userNotFound;
    private String userDeleted;
	
	@Autowired
	private final PasswordEncoder passwordEncoder;

	@Autowired
	private final UserRegistrationRepo registrationRepo;
	@Autowired
	private final FormatterDateTime formatter;

	@Autowired
	private  TokenRepository tokenRepository;

	@Autowired
	private final JwtService jwtService;

	@Autowired
	private final AuthenticationManager authenticationManager;
	


	@Override
	public ResponseEntity<AuthenticationResponse> authenticate(UserRegistrationForm registrationForm) {
		
		if(!registrationRepo.findByEmail(registrationForm.getEmail()).isPresent()) {
			return ResponseEntity.ok(new AuthenticationResponse(null,userLoginFailMessage,null));
		}
		
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(registrationForm.getEmail(), registrationForm.getPassword()));
		EzUserRegistration user = registrationRepo.findByEmail(registrationForm.getEmail()).orElseThrow();
		if (user != null) {
			String jwt = jwtService.generateToken(user);
			System.out.println(user + jwt);
			revokeAllTokenByUser(user);
			saveUserToken(jwt, user);
			return ResponseEntity.ok(new AuthenticationResponse(jwt,userLoginSuccess,user));
		}
		return ResponseEntity
				.ok(new AuthenticationResponse(userLoginFailMessage));
	}

	private void revokeAllTokenByUser(EzUserRegistration user) {
		List<Token> validTokens = tokenRepository.findAllTokensByUserId(user.getId());
		if (validTokens.isEmpty()) {
			return;
		}
		validTokens.forEach(t -> {
			t.setLoggedOut(true);
		});

		tokenRepository.saveAll(validTokens);
	}

	private void saveUserToken(String jwt, EzUserRegistration user) {
		Token token = new Token();
		token.setToken(jwt);
		token.setLoggedOut(false);
		token.setUser(user);
		tokenRepository.save(token);
	}

	@Override
	public ResponseEntity<AuthenticationResponse> saveRegistration(UserRegistrationForm registrationForm) {
		Optional<EzUserRegistration> account=registrationRepo.findByEmail(registrationForm.getEmail().toLowerCase());
			if(account.isPresent() && (account.get().getEmail().equalsIgnoreCase(registrationForm.getEmail()))) {
				return ResponseEntity.ok(new AuthenticationResponse("user already exist"));
			}
			
		if(!registrationForm.getPassword().equals(registrationForm.getConfirmPassword())) {
			return ResponseEntity.ok(new AuthenticationResponse("password does not match to confirm password"));
		}
		
		EzUserRegistration ezUserRegistration = new EzUserRegistration();
		ezUserRegistration.setName(registrationForm.getName());
		ezUserRegistration.setMobile(registrationForm.getMobileNumber());
		ezUserRegistration.setEmail(registrationForm.getEmail().toLowerCase());
		ezUserRegistration.setPassword(passwordEncoder.encode(registrationForm.getPassword()));
		ezUserRegistration.setConfirmPassword(passwordEncoder.encode(registrationForm.getConfirmPassword()));
		ezUserRegistration.setRegistrationDate(formatter.formatLocalDate());
		ezUserRegistration.setRole(registrationForm.getRole().equalsIgnoreCase("ADMIN") ? Role.ADMIN : Role.USER);
		ezUserRegistration.setServiceOptedOn(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy:MM:dd-HH:mm:ss")));
		ezUserRegistration.setLastUpdatedOn(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy:MM:dd-HH:mm:ss")));
		
//		EzUserAccount ezUserAccount = new EzUserAccount();
//		ezUserAccount.setBankName(registrationForm.getBankName());
//		ezUserAccount.setAccountNumber(passwordEncoder.encode(registrationForm.getAccountNumber()));
//		ezUserAccount.setIfscCode(registrationForm.getIfscCode());
//		ezUserAccount.setBranch(registrationForm.getBankBranch());
//		ezUserAccount.setRegistrationDate(formatter.formatLocalDate());
//		ezUserAccount.setServiceOptedOn(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy:MM:dd-HH:mm:ss")));
//		ezUserAccount.setLastUpdatedOn(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy:MM:dd-HH:mm:ss")));
//		ezUserRegistration.setUserAccount(ezUserAccount);
		
		
			
			EzUserRegistration euser = registrationRepo.save(ezUserRegistration);
			
			if(euser!=null) {
				String jwt = jwtService.generateToken(ezUserRegistration);
				log.info("Successfully registered user: {}");
				saveUserToken(jwt, euser);
				return ResponseEntity.ok(new AuthenticationResponse(jwt,success,euser));
			}
			return ResponseEntity.ok(new AuthenticationResponse(saveError));
	}
	@Override
	public Page<EzUserRegistration> fetchAllUsers(Pageable pageable) {
		log.info(" fetch all User method executed:");
		Page<EzUserRegistration> page = registrationRepo.findAllUsers(pageable);
		if (page.isEmpty()) {
			throw new DataNotFoundException(userDataNotFound);
		} else {
			return page;
		}

	}
	@Override
	public EzUserRegistration fetchUserById(Integer id) {
		Optional<EzUserRegistration> opt = registrationRepo.findById(id);
		if (opt.isEmpty()) {
			throw new IllegalArgumentException(userNotFound + id);
		} else {
			return opt.get();
		}
	}
	@Override
	public ResponseEntity<?>  UpdateUserById(Integer id, UserRegistrationForm registrationForm) {
		EzUserRegistration existUser = registrationRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException(userNotFound));
		
		if(!registrationForm.getPassword().equals(registrationForm.getConfirmPassword())) {
			return new ResponseEntity<Object>("password does not match to confirm password",HttpStatus.BAD_REQUEST);
		}
		
		existUser.setName(registrationForm.getName());
		existUser.setMobile(registrationForm.getMobileNumber());
		existUser.setEmail(registrationForm.getEmail());
		existUser.setPassword(registrationForm.getPassword());
		existUser.setConfirmPassword(registrationForm.getConfirmPassword());
//		EzUserAccount existUserAccount = existUser.getUserAccount();
//		if (existUserAccount == null) {
//			existUserAccount = new EzUserAccount();
//		}
//		existUserAccount.setBankName(registrationForm.getBankName());
//		existUserAccount.setAccountNumber(registrationForm.getAccountNumber());
//		existUserAccount.setIfscCode(registrationForm.getIfscCode());
//		existUserAccount.setBranch(registrationForm.getBankBranch());
//		existUser.setUserAccount(existUserAccount);
		existUser.setLastUpdatedOn(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy:MM:dd-HH:mm:ss")));
		return new ResponseEntity<Object>(registrationRepo.save(existUser),HttpStatus.OK);
	}
	@Override
	public String deleteUserById(Integer id) {
		EzUserRegistration existUser = registrationRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException(userNotFound + id));
		registrationRepo.delete(existUser);
		return userDeleted;
	}

	
}




