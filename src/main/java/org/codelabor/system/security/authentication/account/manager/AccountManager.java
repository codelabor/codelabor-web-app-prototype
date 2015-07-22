package org.codelabor.system.security.authentication.account.manager;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AccountManager {

	public abstract void createUser(UserDetails user);

	public abstract UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException;

	/**
	 * Allows the default query string used to retrieve users based on username
	 * to be overridden, if default table or column names need to be changed.
	 * The default query is {@link #DEF_USERS_BY_USERNAME_QUERY}; when modifying
	 * this query, ensure that all returned columns are mapped back to the same
	 * column names as in the default query. If the 'enabled' column does not
	 * exist in the source database, a permanent true value for this column may
	 * be returned by using a query similar to
	 *
	 * <pre>
	 * &quot;select username,password,'true' as enabled from users where username = ?&quot;
	 * </pre>
	 *
	 * @param usersByUsernameQueryString
	 *            The query string to set
	 */
	public abstract void setUsersByUsernameQuery(
			String usersByUsernameQueryString);

	public abstract void setCreateUserSql(String createUserSql);

	public abstract void setCreateAuthoritySql(String createAuthoritySql);

}