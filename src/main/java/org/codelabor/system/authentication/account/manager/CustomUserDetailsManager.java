package org.codelabor.system.authentication.account.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.codelabor.system.authentication.account.dto.AccountDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.util.Assert;

public class CustomUserDetailsManager extends JdbcUserDetailsManager {
	private final Logger logger = LoggerFactory
			.getLogger(CustomUserDetailsManager.class);
	protected String usersByUsernameQuery = DEF_USERS_BY_USERNAME_QUERY;
	protected String createUserSql = DEF_CREATE_USER_SQL;
	protected String createAuthoritySql = DEF_INSERT_AUTHORITY_SQL;
	protected boolean enableAuthorities = true;
	protected boolean enableGroups;
	protected boolean usernameBasedPrimaryKey = true;

	// ~ UserDetailsManager implementation
	// ==============================================================================

	public void createUser(final UserDetails user) {
		validateUserDetails(user);
		AccountDto accountDto = (AccountDto) user;
		getJdbcTemplate().update(createUserSql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, accountDto.getUsername());
				ps.setString(2, accountDto.getPassword());
				ps.setBoolean(3, accountDto.isEnabled());
				ps.setBoolean(4, accountDto.isAccountNonExpired());
				ps.setBoolean(5, accountDto.isCredentialsNonExpired());
				ps.setBoolean(6, accountDto.isAccountNonLocked());
				ps.setString(7, accountDto.getGivenName());
				ps.setString(8, accountDto.getSurname());
				ps.setString(9, accountDto.getMail());
				ps.setString(10, accountDto.getMobile());
				ps.setInt(11, accountDto.getGraceLoginsRemaining());
			}

		});

		if (getEnableAuthorities()) {
			insertUserAuthorities(user);
		}
	}

	/**
	 * Executes the SQL <tt>usersByUsernameQuery</tt> and returns a list of
	 * UserDetails objects. There should normally only be one matching user.
	 */
	protected List<UserDetails> loadUsersByUsername(String username) {
		return getJdbcTemplate().query(usersByUsernameQuery,
				new String[] { username }, new RowMapper<UserDetails>() {
					public UserDetails mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						String username = rs.getString(1);
						String password = rs.getString(2);
						boolean enabled = rs.getBoolean(3);
						boolean accountNonExpired = rs.getBoolean(4);
						boolean credentialsNonExpired = rs.getBoolean(5);
						boolean accountNonLocked = rs.getBoolean(6);
						String givenName = rs.getString(7);
						String surname = rs.getString(8);
						String mail = rs.getString(9);
						String mobile = rs.getString(10);
						int graceLoginsRemaining = rs.getInt(11);
						return new AccountDto(username, password, enabled,
								accountNonExpired, credentialsNonExpired,
								accountNonLocked,
								AuthorityUtils.NO_AUTHORITIES, givenName,
								surname, mail, mobile, graceLoginsRemaining);
					}

				});
	}

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		List<UserDetails> users = loadUsersByUsername(username);

		if (users.size() == 0) {
			logger.debug("Query returned no results for user '{}'", username);
			throw new UsernameNotFoundException(messages.getMessage(
					"JdbcDaoImpl.notFound", new Object[] { username },
					"Username {0} not found"));
		}

		UserDetails user = users.get(0); // contains no GrantedAuthority[]
		Set<GrantedAuthority> dbAuthsSet = new HashSet<GrantedAuthority>();

		if (enableAuthorities) {
			dbAuthsSet.addAll(loadUserAuthorities(user.getUsername()));
		}

		if (enableGroups) {
			dbAuthsSet.addAll(loadGroupAuthorities(user.getUsername()));
		}

		List<GrantedAuthority> dbAuths = new ArrayList<GrantedAuthority>(
				dbAuthsSet);
		addCustomAuthorities(user.getUsername(), dbAuths);

		if (dbAuths.size() == 0) {
			logger.debug(
					"User '{}' has no authorities and will be treated as 'not found'",
					username);
			throw new UsernameNotFoundException(messages.getMessage(
					"JdbcDaoImpl.noAuthority", new Object[] { username },
					"User {0} has no GrantedAuthority"));
		}
		return createUserDetails(username, user, dbAuths);
	}

	/**
	 * Can be overridden to customize the creation of the final
	 * UserDetailsObject which is returned by the <tt>loadUserByUsername</tt>
	 * method.
	 *
	 * @param username
	 *            the name originally passed to loadUserByUsername
	 * @param userFromUserQuery
	 *            the object returned from the execution of the
	 * @param combinedAuthorities
	 *            the combined array of authorities from all the authority
	 *            loading queries.
	 * @return the final UserDetails which should be used in the system.
	 */
	protected UserDetails createUserDetails(String username,
			UserDetails userFromUserQuery,
			List<GrantedAuthority> combinedAuthorities) {
		String returnUsername = userFromUserQuery.getUsername();

		if (!usernameBasedPrimaryKey) {
			returnUsername = username;
		}

		AccountDto accountDto = (AccountDto) userFromUserQuery;

		return new AccountDto(returnUsername, accountDto.getPassword(),
				accountDto.isEnabled(), accountDto.isAccountNonExpired(),
				accountDto.isCredentialsNonExpired(),
				accountDto.isAccountNonLocked(), combinedAuthorities,
				accountDto.getGivenName(), accountDto.getSurname(),
				accountDto.getMail(), accountDto.getMobile(),
				accountDto.getGraceLoginsRemaining());
	}

	protected void validateUserDetails(UserDetails user) {
		Assert.hasText(user.getUsername(), "Username may not be empty or null");
		validateAuthorities(user.getAuthorities());
	}

	protected void validateAuthorities(
			Collection<? extends GrantedAuthority> authorities) {
		Assert.notNull(authorities, "Authorities list must not be null");

		for (GrantedAuthority authority : authorities) {
			Assert.notNull(authority, "Authorities list contains a null entry");
			Assert.hasText(authority.getAuthority(),
					"getAuthority() method must return a non-empty string");
		}
	}

	protected void insertUserAuthorities(UserDetails user) {
		for (GrantedAuthority auth : user.getAuthorities()) {
			getJdbcTemplate().update(createAuthoritySql, user.getUsername(),
					auth.getAuthority());
		}
	}

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
	public void setUsersByUsernameQuery(String usersByUsernameQueryString) {
		Assert.hasText(usersByUsernameQueryString);
		this.usersByUsernameQuery = usersByUsernameQueryString;
	}

	public void setCreateUserSql(String createUserSql) {
		Assert.hasText(createUserSql);
		this.createUserSql = createUserSql;
	}

	public void setCreateAuthoritySql(String createAuthoritySql) {
		Assert.hasText(createAuthoritySql);
		this.createAuthoritySql = createAuthoritySql;
	}
}
