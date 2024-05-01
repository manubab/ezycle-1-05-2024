package ai.acintyo.ezykle.services.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ai.acintyo.ezykle.bindings.EzUserAccountDetails;
import ai.acintyo.ezykle.entities.EzUserAccount;
import ai.acintyo.ezykle.model.ApiResponse;
import ai.acintyo.ezykle.repositories.EzUserAccountRepo;
import ai.acintyo.ezykle.services.IEzUserAccountService;

@Service
public class EzUserAccountServiceImpl implements IEzUserAccountService {

	@Autowired
	private EzUserAccountRepo userAccountRepo;

	@Override
	public ApiResponse<EzUserAccount> addAccount(EzUserAccountDetails userAccount) {
		Optional<EzUserAccount> account = userAccountRepo.findByUserId(userAccount.getUserId());
		if (account.isPresent()) {
			return new ApiResponse<EzUserAccount>(true, "already have an account", account.get());
		}

		EzUserAccount ezUserAccount = new EzUserAccount();
		ezUserAccount.setUserId(userAccount.getUserId());
		ezUserAccount.setAccountNumber(userAccount.getAccountNumber());
		ezUserAccount.setBankName(userAccount.getBankName());
		ezUserAccount.setIfscCode(userAccount.getIfscCode());
		ezUserAccount.setBranch(userAccount.getBankBranch());
		ezUserAccount.setRegistrationDate(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-dd")));
		ezUserAccount.setServiceOptedOn(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-dd:HH:mm:ss")));
		ezUserAccount.setLastUpdatedOn(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-dd:HH:mm:ss")));
		ezUserAccount.setDeleteBy("N");

		return new ApiResponse<EzUserAccount>(true, "account added successfully ", userAccountRepo.save(ezUserAccount));
	}

	@Override
	public ApiResponse<EzUserAccount> findByUserId(Integer userId) {
		Optional<EzUserAccount> account = userAccountRepo.findByUserId(userId);
		if (account.isPresent()) {
			return new ApiResponse<EzUserAccount>(true, "account data finded sucessfully", account.get());
		}
		return new ApiResponse<EzUserAccount>(false, " user id does not have an account", null);
	}

	@Override
	public ApiResponse<EzUserAccount> updateAccount(EzUserAccount userAccount) {

		Optional<EzUserAccount> account = userAccountRepo.findByUserId(userAccount.getUserId());
		Integer accountId = account.get().getId();
		if (account.isPresent()) {
			EzUserAccount ezUserAccount = account.get();
			ezUserAccount.setId(accountId);
			ezUserAccount.setUserId(userAccount.getUserId());
			ezUserAccount.setAccountNumber(userAccount.getAccountNumber());
			ezUserAccount.setBankName(userAccount.getBankName());
			ezUserAccount.setIfscCode(userAccount.getIfscCode());
			ezUserAccount.setBranch(userAccount.getBranch());

			ezUserAccount
					.setLastUpdatedOn(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-dd:HH:mm:ss")));
			ezUserAccount.setDeleteBy("N");
			ezUserAccount = userAccountRepo.save(ezUserAccount);
			return new ApiResponse<EzUserAccount>(true, "updated", ezUserAccount);

		}
		return new ApiResponse<EzUserAccount>(false, "your account deleted please contact admin", userAccount);
	}

	@Override
	public ApiResponse<EzUserAccount> deleteAccount(Integer userId) {
		Optional<EzUserAccount> account = userAccountRepo.findByUserId(userId);
		if (account.isPresent()) {
			EzUserAccount ezUserAccount = account.get();
			ezUserAccount.setDeleteBy("N");
			userAccountRepo.save(ezUserAccount);
			return new ApiResponse<EzUserAccount>(true, "deleted successfully ", null);
		}
		return new ApiResponse<EzUserAccount>(false, "you don't have an account please contact admin", null);
	}

	@Override
	public ResponseEntity<ApiResponse> findAllDeleteBy(String deleteBy) {

		System.out.println(deleteBy);
		List<EzUserAccount> listOfAccounts = userAccountRepo.findByAlldeleteBy(deleteBy);
				//findByDeleteByIgnoreCase(deleteBy);
	//	listOfAccounts = listOfAccounts.stream().filter(ua->ua.getDeleteBy().equalsIgnoreCase(deleteBy)).collect(Collectors.toList());
		if (listOfAccounts.isEmpty()) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(true, "no content found", null),
					HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "deleted list of accounts", listOfAccounts), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> findAllAccounts() {
		List<EzUserAccount> listOfUserAccounts = userAccountRepo.findAll();
		if(!listOfUserAccounts.isEmpty()) {
			return new ResponseEntity<ApiResponse>(new ApiResponse<List<EzUserAccount>>(true,"list of accounts",listOfUserAccounts),HttpStatus.OK);
		}
		return 	new ResponseEntity<ApiResponse>(new ApiResponse<String>(false,"no content",null),HttpStatus.OK);

	}

}
