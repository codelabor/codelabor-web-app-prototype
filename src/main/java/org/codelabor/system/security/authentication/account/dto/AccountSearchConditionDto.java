package org.codelabor.system.security.authentication.account.dto;

import java.util.Collection;
import java.util.Collections;

import javax.validation.constraints.Pattern;

import org.codelabor.system.data.pagination.dto.PaginationSearchConditionDto;
import org.codelabor.system.security.SecurityConstants;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.security.core.GrantedAuthority;

public class AccountSearchConditionDto extends PaginationSearchConditionDto {

	/**
	 *
	 */
	public AccountSearchConditionDto() {
		super();
	}

	/**
	 * @param pageNo
	 * @param maxRowPerPage
	 */
	public AccountSearchConditionDto(Integer pageNo, Integer maxRowPerPage) {
		super(pageNo, maxRowPerPage);
	}

	/**
	 * @param accountNonExpired
	 * @param accountNonLocked
	 * @param authorities
	 * @param credentialsNonExpired
	 * @param enabled
	 * @param givenName
	 * @param graceLoginsRemaining
	 * @param mail
	 * @param mobile
	 * @param passwordConfirm
	 * @param surname
	 * @param username
	 */
	public AccountSearchConditionDto(Boolean accountNonExpired, Boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
			Boolean credentialsNonExpired, Boolean enabled, String givenName, Integer graceLoginsRemaining, String mail, String mobile, String passwordConfirm,
			String surname, String username) {
		super();
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.authorities = authorities;
		this.credentialsNonExpired = credentialsNonExpired;
		this.enabled = enabled;
		this.givenName = givenName;
		this.graceLoginsRemaining = graceLoginsRemaining;
		this.mail = mail;
		this.mobile = mobile;
		this.passwordConfirm = passwordConfirm;
		this.surname = surname;
		this.username = username;
	}

	/**
	 *
	 */
	private static final long serialVersionUID = -3782133407554964140L;
	private Boolean accountNonExpired = SecurityConstants.DEFAULT_ACCOUNT_NON_EXPIRED;
	private Boolean accountNonLocked = SecurityConstants.DEFAULT_ACCOUNT_NON_LOCKED;
	private Collection<? extends GrantedAuthority> authorities = Collections.emptyList();
	private Boolean credentialsNonExpired = SecurityConstants.DEFAULT_CREDENTIALS_NON_EXPIRED;
	private Boolean enabled = SecurityConstants.DEFAULT_ENABLED;

	// TODO: @Length
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String givenName;
	private Integer graceLoginsRemaining;
	@Email
	@SafeHtml(whitelistType = WhiteListType.NONE, message = "{org.hibernate.validator.constraints.SafeHtml.message}")
	private String mail;
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String mobile;
	@Pattern(regexp = "((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[~`!@#$%^&*()_+\\-=\\[\\]\\{}|;':\",./<>?\\\\])(?=\\S+$).{10,15})", message = "{errors.password.mismatched}")
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String passwordConfirm;
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String surname;
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String username;

	/**
	 * @return the accountNonExpired
	 */
	public Boolean getAccountNonExpired() {
		return accountNonExpired;
	}

	/**
	 * @return the accountNonLocked
	 */
	public Boolean getAccountNonLocked() {
		return accountNonLocked;
	}

	/**
	 * @return the authorities
	 */
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	/**
	 * @return the credentialsNonExpired
	 */
	public Boolean getCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	/**
	 * @return the enabled
	 */
	public Boolean getEnabled() {
		return enabled;
	}

	/**
	 * @return the givenName
	 */
	public String getGivenName() {
		return givenName;
	}

	/**
	 * @return the graceLoginsRemaining
	 */
	public Integer getGraceLoginsRemaining() {
		return graceLoginsRemaining;
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	public boolean isAccountNonExpired() {
		if (this.accountNonExpired != null) {
			return this.accountNonExpired.booleanValue();
		} else {
			return false;
		}
	}

	public boolean isAccountNonLocked() {
		if (this.accountNonLocked != null) {
			return this.accountNonLocked.booleanValue();
		} else {
			return false;
		}
	}

	public boolean isCredentialsNonExpired() {
		if (this.credentialsNonExpired != null) {
			return this.credentialsNonExpired.booleanValue();
		} else {
			return false;
		}
	}

	public boolean isEnabled() {
		if (this.enabled != null) {
			return this.enabled.booleanValue();
		} else {
			return false;
		}
	}

	/**
	 * @param accountNonExpired
	 *            the accountNonExpired to set
	 */
	public void setAccountNonExpired(Boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	/**
	 * @param accountNonLocked
	 *            the accountNonLocked to set
	 */
	public void setAccountNonLocked(Boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	/**
	 * @param authorities
	 *            the authorities to set
	 */
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	/**
	 * @param credentialsNonExpired
	 *            the credentialsNonExpired to set
	 */
	public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	/**
	 * @param enabled
	 *            the enabled to set
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @param givenName
	 *            the givenName to set
	 */
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	/**
	 * @param graceLoginsRemaining
	 *            the graceLoginsRemaining to set
	 */
	public void setGraceLoginsRemaining(Integer graceLoginsRemaining) {
		this.graceLoginsRemaining = graceLoginsRemaining;
	}

	/**
	 * @param mail
	 *            the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @param surname
	 *            the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((accountNonExpired == null) ? 0 : accountNonExpired.hashCode());
		result = prime * result + ((accountNonLocked == null) ? 0 : accountNonLocked.hashCode());
		result = prime * result + ((authorities == null) ? 0 : authorities.hashCode());
		result = prime * result + ((credentialsNonExpired == null) ? 0 : credentialsNonExpired.hashCode());
		result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());
		result = prime * result + ((givenName == null) ? 0 : givenName.hashCode());
		result = prime * result + ((graceLoginsRemaining == null) ? 0 : graceLoginsRemaining.hashCode());
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
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
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof AccountSearchConditionDto)) {
			return false;
		}
		AccountSearchConditionDto other = (AccountSearchConditionDto) obj;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AccountSearchConditionDto [accountNonExpired=");
		builder.append(accountNonExpired);
		builder.append(", accountNonLocked=");
		builder.append(accountNonLocked);
		builder.append(", authorities=");
		builder.append(authorities);
		builder.append(", credentialsNonExpired=");
		builder.append(credentialsNonExpired);
		builder.append(", enabled=");
		builder.append(enabled);
		builder.append(", givenName=");
		builder.append(givenName);
		builder.append(", graceLoginsRemaining=");
		builder.append(graceLoginsRemaining);
		builder.append(", mail=");
		builder.append(mail);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", passwordConfirm=");
		builder.append(passwordConfirm);
		builder.append(", surname=");
		builder.append(surname);
		builder.append(", username=");
		builder.append(username);
		builder.append(", pageNo=");
		builder.append(pageNo);
		builder.append(", maxRowPerPage=");
		builder.append(maxRowPerPage);
		builder.append(", upperBound=");
		builder.append(upperBound);
		builder.append(", lowerBound=");
		builder.append(lowerBound);
		builder.append("]");
		return builder.toString();
	}

}
