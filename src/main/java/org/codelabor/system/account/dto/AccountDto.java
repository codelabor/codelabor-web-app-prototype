package org.codelabor.system.account.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AccountDto implements Serializable, UserDetails {

	/**
	 *
	 */
	private static final long serialVersionUID = -6177823371031883302L;

	private boolean enabled;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;

	@NotBlank
	private String givenName;
	private int graceLoginsRemaining;
	private String mail;
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
	private String surname;

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String username;
	private Collection<? extends GrantedAuthority> authorites = Collections
			.emptyList();

	/**
	 * @return the isEnabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param isEnabled
	 *            the isEnabled to set
	 */
	public void setEnabled(boolean isEnabled) {
		this.enabled = isEnabled;
	}

	/**
	 * @return the isAccountNonExpired
	 */
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	/**
	 * @param isAccountNonExpired
	 *            the isAccountNonExpired to set
	 */
	public void setAccountNonExpired(boolean isAccountNonExpired) {
		this.accountNonExpired = isAccountNonExpired;
	}

	public AccountDto() {
		super();
	}

	/**
	 * @return the isAccountNonLocked
	 */
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public AccountDto(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorites,
			String givenName, String surname, String mail, String mobile,
			int graceLoginsRemaining) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.accountNonExpired = accountNonExpired;
		this.credentialsNonExpired = credentialsNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.authorites = authorites;
		this.givenName = givenName;
		this.surname = surname;
		this.mail = mail;
		this.mobile = mobile;
		this.graceLoginsRemaining = graceLoginsRemaining;
	}

	/**
	 * @param isAccountNonLocked
	 *            the isAccountNonLocked to set
	 */
	public void setAccountNonLocked(boolean isAccountNonLocked) {
		this.accountNonLocked = isAccountNonLocked;
	}

	/**
	 * @return the isCredentialsNonExpired
	 */
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	/**
	 * @param isCredentialsNonExpired
	 *            the isCredentialsNonExpired to set
	 */
	public void setCredentialsNonExpired(boolean isCredentialsNonExpired) {
		this.credentialsNonExpired = isCredentialsNonExpired;
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
	public int getGraceLoginsRemaining() {
		return graceLoginsRemaining;
	}

	/**
	 * @param graceLoginsRemaining
	 *            the graceLoginsRemaining to set
	 */
	public void setGraceLoginsRemaining(int graceLoginsRemaining) {
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
	 * @return the authorites
	 */
	public Collection<? extends GrantedAuthority> getAuthorites() {
		return authorites;
	}

	/**
	 * @param authorites
	 *            the authorites to set
	 */
	public void setAuthorites(Collection<? extends GrantedAuthority> authorites) {
		this.authorites = authorites;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorites;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AccountDto [isEnabled=");
		builder.append(enabled);
		builder.append(", isAccountNonExpired=");
		builder.append(accountNonExpired);
		builder.append(", isAccountNonLocked=");
		builder.append(accountNonLocked);
		builder.append(", isCredentialsNonExpired=");
		builder.append(credentialsNonExpired);
		builder.append(", givenName=");
		builder.append(givenName);
		builder.append(", graceLoginsRemainin=");
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
		builder.append(", authorites=");
		builder.append(authorites);
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
		result = prime * result
				+ ((authorites == null) ? 0 : authorites.hashCode());
		result = prime * result
				+ ((givenName == null) ? 0 : givenName.hashCode());
		result = prime * result + graceLoginsRemaining;
		result = prime * result + (accountNonExpired ? 1231 : 1237);
		result = prime * result + (accountNonLocked ? 1231 : 1237);
		result = prime * result + (credentialsNonExpired ? 1231 : 1237);
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((passwordConfirm == null) ? 0 : passwordConfirm.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
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
		if (authorites == null) {
			if (other.authorites != null) {
				return false;
			}
		} else if (!authorites.equals(other.authorites)) {
			return false;
		}
		if (givenName == null) {
			if (other.givenName != null) {
				return false;
			}
		} else if (!givenName.equals(other.givenName)) {
			return false;
		}
		if (graceLoginsRemaining != other.graceLoginsRemaining) {
			return false;
		}
		if (accountNonExpired != other.accountNonExpired) {
			return false;
		}
		if (accountNonLocked != other.accountNonLocked) {
			return false;
		}
		if (credentialsNonExpired != other.credentialsNonExpired) {
			return false;
		}
		if (enabled != other.enabled) {
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
