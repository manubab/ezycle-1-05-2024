package ai.acintyo.ezykle.entities;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ai.acintyo.ezykle.bindings.Role;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "EZ_USER_REGISTRATION")
public class EzUserRegistration implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 99523977307357780L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	private String mobile;

	private String email;

	private String password;

	private String confirmPassword;

	private String registrationDate;

	private String serviceOptedOn;

	private String insertedBy;

	private String lastUpdatedOn;

	private String updatedBy;

//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "user_account_id")
//	private EzUserAccount userAccount;

	private String userStatus = "Active";

	@Enumerated(value = EnumType.STRING)
	private Role role;

	@OneToOne(mappedBy = "user")
	private ForgotPassword forgotPassword;
	

	@Override
	public String getUsername() {

		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}
}
