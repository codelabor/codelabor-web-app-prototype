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

	private boolean isEnabled;
	private boolean isAccountNonExpired;
	private boolean isAccountNonLocked;
	private boolean isCredentialsNonExpired;

	@NotBlank
	private String givenName;
	private int graceLoginsRemainin;
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
		return isEnabled;
	}

	/**
	 * @param isEnabled
	 *            the isEnabled to set
	 */
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	/**
	 * @return the isAccountNonExpired
	 */
	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}

	/**
	 * @param isAccountNonExpired
	 *            the isAccountNonExpired to set
	 */
	public void setAccountNonExpired(boolean isAccountNonExpired) {
		this.isAccountNonExpired = isAccountNonExpired;
	}

	/**
	 * @return the isAccountNonLocked
	 */
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	/**
	 * @param isAccountNonLocked
	 *            the isAccountNonLocked to set
	 */
	public void setAccountNonLocked(boolean isAccountNonLocked) {
		this.isAccountNonLocked = isAccountNonLocked;
	}

	/**
	 * @return the isCredentialsNonExpired
	 */
	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	/**
	 * @param isCredentialsNonExpired
	 *            the isCredentialsNonExpired to set
	 */
	public void setCredentialsNonExpired(boolean isCredentialsNonExpired) {
		this.isCredentialsNonExpired = isCredentialsNonExpired;
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
	 * @return the graceLoginsRemainin
	 */
	public int getGraceLoginsRemainin() {
		return graceLoginsRemainin;
	}

	/**
	 * @param graceLoginsRemainin
	 *            the graceLoginsRemainin to set
	 */
	public void setGraceLoginsRemainin(int graceLoginsRemainin) {
		this.graceLoginsRemainin = graceLoginsRemainin;
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
		builder.append(isEnabled);
		builder.append(", isAccountNonExpired=");
		builder.append(isAccountNonExpired);
		builder.append(", isAccountNonLocked=");
		builder.append(isAccountNonLocked);
		builder.append(", isCredentialsNonExpired=");
		builder.append(isCredentialsNonExpired);
		builder.append(", givenName=");
		builder.append(givenName);
		builder.append(", graceLoginsRemainin=");
		builder.append(graceLoginsRemainin);
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
		result = prime * result + graceLoginsRemainin;
		result = prime * result + (isAccountNonExpired ? 1231 : 1237);
		result = prime * result + (isAccountNonLocked ? 1231 : 1237);
		result = prime * result + (isCredentialsNonExpired ? 1231 : 1237);
		result = prime * result + (isEnabled ? 1231 : 1237);
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
		if (graceLoginsRemainin != other.graceLoginsRemainin) {
			return false;
		}
		if (isAccountNonExpired != other.isAccountNonExpired) {
			return false;
		}
		if (isAccountNonLocked != other.isAccountNonLocked) {
			return false;
		}
		if (isCredentialsNonExpired != other.isCredentialsNonExpired) {
			return false;
		}
		if (isEnabled != other.isEnabled) {
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
