package ai.acintyo.ezykle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ai.acintyo.ezykle.bindings.EzUserAccountDetails;
import ai.acintyo.ezykle.entities.EzUserAccount;
import ai.acintyo.ezykle.services.IEzUserAccountService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/ezycle-account")
public class EzUserAccountController {

	@Autowired
	private IEzUserAccountService accountService;

	@PostMapping("/add")
	public ResponseEntity<?> addAccount(@RequestBody @Valid EzUserAccountDetails userAccount) {
		return new ResponseEntity<Object>(accountService.addAccount(userAccount), HttpStatus.OK);
	}

	@GetMapping("/getAccount/{userId}")
	public ResponseEntity<?> getAccount(@PathVariable("userId") Integer userId) {
		return new ResponseEntity<Object>(accountService.findByUserId(userId), HttpStatus.OK);
	}

	@PutMapping("/updateAccount")
	public ResponseEntity<?> updateAccount(@RequestBody @Valid EzUserAccount userAccount) {
		return new ResponseEntity<Object>(accountService.updateAccount(userAccount), HttpStatus.OK);
	}

	@DeleteMapping("/deleteAccount/{userId}")
	public ResponseEntity<?> deleteAccount(@PathVariable("userId") Integer userId) {
		return new ResponseEntity<Object>(accountService.deleteAccount(userId), HttpStatus.OK);
	}

	@PostMapping("/deleteBy/{deleteBy}")
	public ResponseEntity<?> findAllDeleteBy(@PathVariable("deleteBy") String deleteBy) {
		return accountService.findAllDeleteBy(deleteBy);
	}

	@GetMapping("/get/AllAccounts")
	public ResponseEntity<?> findAllAccounts(){
		return accountService.findAllAccounts();
	}
}
