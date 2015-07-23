package org.codelabor.system.security.authentication.account.manager;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AccountManager {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codelabor.system.security.authentication.account.manager.AccountManager #createUser(org.springframework.security.core.userdetails.UserDetails)
	 */
	public abstract void createUser(UserDetails user);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.provisioning.JdbcUserDetailsManager#updateUser(org.springframework.security.core.userdetails.UserDetails)
	 */
	public abstract void updateUser(UserDetails user);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codelabor.system.security.authentication.account.manager.AccountManager #loadUserByUsername(java.lang.String)
	 */
	public abstract UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}