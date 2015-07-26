package org.codelabor.system.security.authentication.account.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

import javax.validation.constraints.Pattern;

import org.codelabor.system.security.SecurityConstants;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.ScriptAssert;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@ScriptAssert(lang = "javascript", script = "_this.password.equals(_this.passwordConfirm)", message = "{errors.confirm.password.mismatched}")
public class AccountDto implements Serializable, UserDetails {

	public AccountDto(String username, String password, String givenName, String surname, String mail, String mobile,
			Collection<? extends GrantedAuthority> authorities, Boolean enabled, Boolean accountNonLocked, Boolean accountNonExpired,
			Boolean credentialsNonExpired, Integer graceLoginsRemaining) {
		super();
		this.username = username;
		this.password = password;
		this.givenName = givenName;
		this.surname = surname;
		this.mail = mail;
		this.mobile = mobile;
		this.enabled = enabled;
		this.authorities = authorities;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
		this.graceLoginsRemaining = graceLoginsRemaining;
	}

	public AccountDto() {
		super();
	}

	/**
	 *
	 */
	private static final long serialVersionUID = -6177823371031883302L;

	private Boolean enabled = SecurityConstants.DEFAULT_ENABLED;
	private Boolean accountNonExpired = SecurityConstants.DEFAULT_ACCOUNT_NON_EXPIRED;
	private Boolean accountNonLocked = SecurityConstants.DEFAULT_ACCOUNT_NON_LOCKED;
	private Boolean credentialsNonExpired = SecurityConstants.DEFAULT_CREDENTIALS_NON_EXPIRED;

	// TODO: @Length

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String givenName;

	private Integer graceLoginsRemaining;

	@Email
	@SafeHtml(whitelistType = WhiteListType.NONE, message = "{org.hibernate.validator.constraints.SafeHtml.message}")
	private String mail;

	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String mobile;

	@NotBlank
	@Pattern(regexp = "((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[~`!@#$%^&*()_+\\-=\\[\\]\\{}|;':\",./<>?\\\\])(?=\\S+$).{10,15})", message = "{errors.password.mismatched}")
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String password;

	@NotBlank
	@Pattern(regexp = "((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[~`!@#$%^&*()_+\\-=\\[\\]\\{}|;':\",./<>?\\\\])(?=\\S+$).{10,15})", message = "{errors.password.mismatched}")
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String passwordConfirm;

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String surname;

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String username;

	private Collection<? extends GrantedAuthority> authorities = Collections.emptyList();

	/**
	 * @return the enabled
	 */
	public Boolean getEnabled() {
		return enabled;
	}

	/**
	 * @param enabled
	 *            the enabled to set
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the accountNonExpired
	 */
	public Boolean getAccountNonExpired() {
		return accountNonExpired;
	}

	/**
	 * @param accountNonExpired
	 *            the accountNonExpired to set
	 */
	public void setAccountNonExpired(Boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	/**
	 * @return the accountNonLocked
	 */
	public Boolean getAccountNonLocked() {
		return accountNonLocked;
	}

	/**
	 * @param accountNonLocked
	 *            the accountNonLocked to set
	 */
	public void setAccountNonLocked(Boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	/**
	 * @return the credentialsNonExpired
	 */
	public Boolean getCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	/**
	 * @param credentialsNonExpired
	 *            the credentialsNonExpired to set
	 */
	public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	/**
	 * @return the givenName
	 */
	public String getGivenName() {
		return givenName;
	}

	/**
	 * @param givenName
	 *            the givenName to set
	 */
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	/**
	 * @return the graceLoginsRemaining
	 */
	public Integer getGraceLoginsRemaining() {
		return graceLoginsRemaining;
	}

	/**
	 * @param graceLoginsRemaining
	 *            the graceLoginsRemaining to set
	 */
	public void setGraceLoginsRemaining(Integer graceLoginsRemaining) {
		this.graceLoginsRemaining = graceLoginsRemaining;
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail
	 *            the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the passwordConfirm
	 */
	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	/**
	 * @param passwordConfirm
	 *            the passwordConfirm to set
	 */
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname
	 *            the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the authorities
	 */
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	/**
	 * @param authorities
	 *            the authorities to set
	 */
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		if (this.accountNonExpired != null) {
			return this.accountNonExpired.booleanValue();
		} else {
			return false;
		}
	}

	@Override
	public boolean isAccountNonLocked() {
		if (this.accountNonLocked != null) {
			return this.accountNonLocked.booleanValue();
		} else {
			return false;
		}
	}

	@Override
	public boolean isCredentialsNonExpired() {
		if (this.credentialsNonExpired != null) {
			return this.credentialsNonExpired.booleanValue();
		} else {
			return false;
		}
	}

	@Override
	public boolean isEnabled() {
		if (this.enabled != null) {
			return this.enabled.booleanValue();
		} else {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AccountDto [enabled=");
		builder.append(enabled);
		builder.append(", accountNonExpired=");
		builder.append(accountNonExpired);
		builder.append(", accountNonLocked=");
		builder.append(accountNonLocked);
		builder.append(", credentialsNonExpired=");
		builder.append(credentialsNonExpired);
		builder.append(", givenName=");
		builder.append(givenName);
		builder.append(", graceLoginsRemaining=");
		builder.append(graceLoginsRemaining);
		builder.append(", mail=");
		builder.append(mail);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", password=");
		builder.append(password);
		builder.append(", passwordConfirm=");
		builder.append(passwordConfirm);
		builder.append(", surname=");
		builder.append(surname);
		builder.append(", username=");
		builder.append(username);
		builder.append(", authorities=");
		builder.append(authorities);
		builder.append("]");
		return builder.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountNonExpired == null) ? 0 : accountNonExpired.hashCode());
		result = prime * result + ((accountNonLocked == null) ? 0 : accountNonLocked.hashCode());
		result = prime * result + ((authorities == null) ? 0 : authorities.hashCode());
		result = prime * result + ((credentialsNonExpired == null) ? 0 : credentialsNonExpired.hashCode());
		result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());
		result = prime * result + ((givenName == null) ? 0 : givenName.hashCode());
		result = prime * result + ((graceLoginsRemaining == null) ? 0 : graceLoginsRemaining.hashCode());
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((passwordConfirm == null) ? 0 : passwordConfirm.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof AccountDto)) {
			return false;
		}
		AccountDto other = (AccountDto) obj;
		if (accountNonExpired == null) {
			if (other.accountNonExpired != null) {
				return false;
			}
		} else if (!accountNonExpired.equals(other.accountNonExpired)) {
			return false;
		}
		if (accountNonLocked == null) {
			if (other.accountNonLocked != null) {
				return false;
			}
		} else if (!accountNonLocked.equals(other.accountNonLocked)) {
			return false;
		}
		if (authorities == null) {
			if (other.authorities != null) {
				return false;
			}
		} else if (!authorities.equals(other.authorities)) {
			return false;
		}
		if (credentialsNonExpired == null) {
			if (other.credentialsNonExpired != null) {
				return false;
			}
		} else if (!credentialsNonExpired.equals(other.credentialsNonExpired)) {
			return false;
		}
		if (enabled == null) {
			if (other.enabled != null) {
				return false;
			}
		} else if (!enabled.equals(other.enabled)) {
			return false;
		}
		if (givenName == null) {
			if (other.givenName != null) {
				return false;
			}
		} else if (!givenName.equals(other.givenName)) {
			return false;
		}
		if (graceLoginsRemaining == null) {
			if (other.graceLoginsRemaining != null) {
				return false;
			}
		} else if (!graceLoginsRemaining.equals(other.graceLoginsRemaining)) {
			return false;
		}
		if (mail == null) {
			if (other.mail != null) {
				return false;
			}
		} else if (!mail.equals(other.mail)) {
			return false;
		}
		if (mobile == null) {
			if (other.mobile != null) {
				return false;
			}
		} else if (!mobile.equals(other.mobile)) {
			return false;
		}
		if (password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!password.equals(other.password)) {
			return false;
		}
		if (passwordConfirm == null) {
			if (other.passwordConfirm != null) {
				return false;
			}
		} else if (!passwordConfirm.equals(other.passwordConfirm)) {
			return false;
		}
		if (surname == null) {
			if (other.surname != null) {
				return false;
			}
		} else if (!surname.equals(other.surname)) {
			return false;
		}
		if (username == null) {
			if (other.username != null) {
				return false;
			}
		} else if (!username.equals(other.username)) {
			return false;
		}
		return true;
	}

}
