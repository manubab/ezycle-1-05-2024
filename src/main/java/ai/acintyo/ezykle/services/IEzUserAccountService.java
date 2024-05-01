package ai.acintyo.ezykle.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import ai.acintyo.ezykle.bindings.EzUserAccountDetails;
import ai.acintyo.ezykle.entities.EzUserAccount;
import ai.acintyo.ezykle.model.ApiResponse;

public interface IEzUserAccountService {

	public ApiResponse<EzUserAccount> addAccount(EzUserAccountDetails userAccount);

	public ApiResponse<EzUserAccount> findByUserId(Integer userId);

	public ApiResponse<EzUserAccount> updateAccount(EzUserAccount userAccount);

	public ApiResponse<EzUserAccount> deleteAccount(Integer userId);

	ResponseEntity<?> findAllDeleteBy(String deleteBy);

	public ResponseEntity<?> findAllAccounts();

}
