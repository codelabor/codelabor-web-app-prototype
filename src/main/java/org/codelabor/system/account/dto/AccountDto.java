package org.codelabor.system.account.dto;

import java.io.Serializable;

public class AccountDto implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -6177823371031883302L;

	private Boolean accountEnabled;
	private Boolean accountNonExpired;
	private Boolean accountNonLocked;
	private Boolean credentialsNonExpired;
	private String givenName;
	private Integer graceLoginsRemainin;
	private String mail;
	private String mobile;
	private String password;
	private String passwordConfirm;
	private String surname;
	private String username;

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
		if (accountEnabled == null) {
			if (other.accountEnabled != null) {
				return false;
			}
		} else if (!accountEnabled.equals(other.accountEnabled)) {
			return false;
		}
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
		if (credentialsNonExpired == null) {
			if (other.credentialsNonExpired != null) {
				return false;
			}
		} else if (!credentialsNonExpired.equals(other.credentialsNonExpired)) {
			return false;
		}
		if (givenName == null) {
			if (other.givenName != null) {
				return false;
			}
		} else if (!givenName.equals(other.givenName)) {
			return false;
		}
		if (graceLoginsRemainin == null) {
			if (other.graceLoginsRemainin != null) {
				return false;
			}
		} else if (!graceLoginsRemainin.equals(other.graceLoginsRemainin)) {
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

	/**
	 * @return the accountEnabled
	 */
	public Boolean getAccountEnabled() {
		return accountEnabled;
	}

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
	 * @return the credentialsNonExpired
	 */
	public Boolean getCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	/**
	 * @return the givenName
	 */
	public String getGivenName() {
		return givenName;
	}

	/**
	 * @return the graceLoginsRemainin
	 */
	public Integer getGraceLoginsRemainin() {
		return graceLoginsRemainin;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the passwordConfirm
	 */
	public String getPasswordConfirm() {
		return passwordConfirm;
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
				+ ((accountEnabled == null) ? 0 : accountEnabled.hashCode());
		result = prime
				* result
				+ ((accountNonExpired == null) ? 0 : accountNonExpired
						.hashCode());
		result = prime
				* result
				+ ((accountNonLocked == null) ? 0 : accountNonLocked.hashCode());
		result = prime
				* result
				+ ((credentialsNonExpired == null) ? 0 : credentialsNonExpired
						.hashCode());
		result = prime * result
				+ ((givenName == null) ? 0 : givenName.hashCode());
		result = prime
				* result
				+ ((graceLoginsRemainin == null) ? 0 : graceLoginsRemainin
						.hashCode());
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

	/**
	 * @param accountEnabled
	 *            the accountEnabled to set
	 */
	public void setAccountEnabled(Boolean accountEnabled) {
		this.accountEnabled = accountEnabled;
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
	 * @param credentialsNonExpired
	 *            the credentialsNonExpired to set
	 */
	public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	/**
	 * @param givenName
	 *            the givenName to set
	 */
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	/**
	 * @param graceLoginsRemainin
	 *            the graceLoginsRemainin to set
	 */
	public void setGraceLoginsRemainin(Integer graceLoginsRemainin) {
		this.graceLoginsRemainin = graceLoginsRemainin;
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
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param passwordConfirm
	 *            the passwordConfirm to set
	 */
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AccountDto [mail=");
		builder.append(mail);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", givenName=");
		builder.append(givenName);
		builder.append(", surname=");
		builder.append(surname);
		builder.append(", password=");
		builder.append(password);
		builder.append(", passwordConfirm=");
		builder.append(passwordConfirm);
		builder.append(", username=");
		builder.append(username);
		builder.append(", accountNonExpired=");
		builder.append(accountNonExpired);
		builder.append(", accountNonLocked=");
		builder.append(accountNonLocked);
		builder.append(", credentialsNonExpired=");
		builder.append(credentialsNonExpired);
		builder.append(", accountEnabled=");
		builder.append(accountEnabled);
		builder.append(", graceLoginsRemainin=");
		builder.append(graceLoginsRemainin);
		builder.append("]");
		return builder.toString();
	}

}
