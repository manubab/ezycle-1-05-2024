package ai.acintyo.ezykle.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ai.acintyo.ezykle.entities.EzUserAccount;

public interface EzUserAccountRepo extends JpaRepository<EzUserAccount, Integer> {

	public Optional<EzUserAccount> findByUserId(Integer userId);
	
	@Query(value="SELECT * FROM ez_user_account WHERE delete_by =?1",nativeQuery = true)
	public List<EzUserAccount> findByAlldeleteBy(String deleteBy);
	
	public List<EzUserAccount> findByDeleteByIgnoreCase(String deletedBy);
}
