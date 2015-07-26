package org.codelabor.system.security.authentication.account.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.codelabor.system.security.authentication.account.dto.AccountDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.cache.NullUserCache;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.util.Assert;

public class AccountManagerImpl extends JdbcUserDetailsManager implements AccountManager {
	private final Logger logger = LoggerFactory.getLogger(AccountManagerImpl.class);

	// from JdbcDaoImpl
	protected String authoritiesByUsernameQuery = DEF_AUTHORITIES_BY_USERNAME_QUERY;
	protected String groupAuthoritiesByUsernameQuery = DEF_GROUP_AUTHORITIES_BY_USERNAME_QUERY;
	protected String usersByUsernameQuery = DEF_USERS_BY_USERNAME_QUERY;

	// JdbcUserDetailsManager
	protected String createUserSql = DEF_CREATE_USER_SQL;
	protected String deleteUserSql = DEF_DELETE_USER_SQL;
	protected String updateUserSql = DEF_UPDATE_USER_SQL;
	protected String createAuthoritySql = DEF_INSERT_AUTHORITY_SQL;
	protected String deleteUserAuthoritiesSql = DEF_DELETE_USER_AUTHORITIES_SQL;
	protected String userExistsSql = DEF_USER_EXISTS_SQL;
	protected String changePasswordSql = DEF_CHANGE_PASSWORD_SQL;

	protected String findAllGroupsSql = DEF_FIND_GROUPS_SQL;
	protected String findUsersInGroupSql = DEF_FIND_USERS_IN_GROUP_SQL;
	protected String insertGroupSql = DEF_INSERT_GROUP_SQL;
	protected String findGroupIdSql = DEF_FIND_GROUP_ID_SQL;
	protected String insertGroupAuthoritySql = DEF_INSERT_GROUP_AUTHORITY_SQL;
	protected String deleteGroupSql = DEF_DELETE_GROUP_SQL;
	protected String deleteGroupAuthoritiesSql = DEF_DELETE_GROUP_AUTHORITIES_SQL;
	protected String deleteGroupMembersSql = DEF_DELETE_GROUP_MEMBERS_SQL;
	protected String renameGroupSql = DEF_RENAME_GROUP_SQL;
	protected String insertGroupMemberSql = DEF_INSERT_GROUP_MEMBER_SQL;
	protected String deleteGroupMemberSql = DEF_DELETE_GROUP_MEMBER_SQL;
	protected String groupAuthoritiesSql = DEF_GROUP_AUTHORITIES_QUERY_SQL;
	protected String deleteGroupAuthoritySql = DEF_DELETE_GROUP_AUTHORITY_SQL;

	protected boolean enableAuthorities = true;
	protected boolean enableGroups;
	protected boolean usernameBasedPrimaryKey = true;

	protected UserCache userCache = new NullUserCache();

	// ~ UserDetailsManager implementation
	// ==============================================================================

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codelabor.system.security.authentication.account.manager.AccountManager #createUser(org.springframework.security.core.userdetails.UserDetails)
	 */
	@Override
	public void createUser(final UserDetails user) {
		validateUserDetails(user);
		AccountDto accountDto = (AccountDto) user;
		getJdbcTemplate().update(createUserSql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, accountDto.getUsername());
				ps.setString(2, accountDto.getPassword());
				ps.setString(3, accountDto.getGivenName());
				ps.setString(4, accountDto.getSurname());
				ps.setString(5, accountDto.getMail());
				ps.setString(6, accountDto.getMobile());
				ps.setBoolean(7, accountDto.isEnabled());
				ps.setBoolean(8, accountDto.isAccountNonLocked());
				ps.setBoolean(9, accountDto.isAccountNonExpired());
				ps.setBoolean(10, accountDto.isCredentialsNonExpired());
				if (accountDto.getGraceLoginsRemaining() != null) {
					ps.setInt(11, accountDto.getGraceLoginsRemaining().intValue());
				} else {
					ps.setInt(11, 0);
				}
			}
		});

		if (getEnableAuthorities()) {
			insertUserAuthorities(user);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.provisioning.JdbcUserDetailsManager#updateUser(org.springframework.security.core.userdetails.UserDetails)
	 */
	@Override
	public void updateUser(UserDetails user) {
		validateUserDetails(user);
		getJdbcTemplate().update(updateUserSql, new PreparedStatementSetter() {
			AccountDto accountDto = (AccountDto) user;

			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, accountDto.getPassword());
				ps.setString(2, accountDto.getGivenName());
				ps.setString(3, accountDto.getSurname());
				ps.setString(4, accountDto.getMail());
				ps.setString(5, accountDto.getMobile());
				ps.setBoolean(6, accountDto.isEnabled());
				ps.setBoolean(7, accountDto.isAccountNonLocked());
				ps.setBoolean(8, accountDto.isAccountNonExpired());
				ps.setBoolean(9, accountDto.isCredentialsNonExpired());
				if (accountDto.getGraceLoginsRemaining() != null) {
					ps.setInt(10, accountDto.getGraceLoginsRemaining().intValue());
				} else {
					ps.setInt(10, 0);
				}
				ps.setString(11, accountDto.getUsername());
			}
		});

		if (getEnableAuthorities()) {
			deleteUserAuthorities(user.getUsername());
			insertUserAuthorities(user);
		}

		userCache.removeUserFromCache(user.getUsername());
	}

	/**
	 * Executes the SQL <tt>usersByUsernameQuery</tt> and returns a list of UserDetails objects. There should normally only be one matching user.
	 */
	protected List<UserDetails> loadUsersByUsername(String username) {
		return getJdbcTemplate().query(usersByUsernameQuery, new String[] { username }, new RowMapper<UserDetails>() {
			public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
				String username = rs.getString(1);
				String password = rs.getString(2);
				String givenName = rs.getString(3);
				String surname = rs.getString(4);
				String mail = rs.getString(5);
				String mobile = rs.getString(6);
				boolean enabled = rs.getBoolean(7);
				boolean accountNonLocked = rs.getBoolean(8);
				boolean accountNonExpired = rs.getBoolean(9);
				boolean credentialsNonExpired = rs.getBoolean(10);
				int graceLoginsRemaining = rs.getInt(11);
				return new AccountDto(username, password, givenName, surname, mail, mobile, AuthorityUtils.NO_AUTHORITIES, Boolean.valueOf(enabled), Boolean
						.valueOf(accountNonLocked), Boolean.valueOf(accountNonExpired), Boolean.valueOf(credentialsNonExpired), Integer
						.valueOf(graceLoginsRemaining));
			}

		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codelabor.system.security.authentication.account.manager.AccountManager #loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<UserDetails> users = loadUsersByUsername(username);

		if (users.size() == 0) {
			logger.debug("Query returned no results for user '{}'", username);
			throw new UsernameNotFoundException(messages.getMessage("JdbcDaoImpl.notFound", new Object[] { username }, "Username {0} not found"));
		}

		UserDetails user = users.get(0); // contains no GrantedAuthority[]
		Set<GrantedAuthority> dbAuthsSet = new HashSet<GrantedAuthority>();

		if (enableAuthorities) {
			dbAuthsSet.addAll(loadUserAuthorities(user.getUsername()));
		}

		if (enableGroups) {
			dbAuthsSet.addAll(loadGroupAuthorities(user.getUsername()));
		}

		List<GrantedAuthority> dbAuths = new ArrayList<GrantedAuthority>(dbAuthsSet);
		addCustomAuthorities(user.getUsername(), dbAuths);

		if (dbAuths.size() == 0) {
			logger.debug("User '{}' has no authorities and will be treated as 'not found'", username);
			throw new UsernameNotFoundException(messages.getMessage("JdbcDaoImpl.noAuthority", new Object[] { username }, "User {0} has no GrantedAuthority"));
		}
		return createUserDetails(username, user, dbAuths);
	}

	public void setUsersByUsernameQuery(String usersByUsernameQueryString) {
		Assert.hasText(usersByUsernameQueryString);
		this.usersByUsernameQuery = usersByUsernameQueryString;
	}

	public void setAuthoritiesByUsernameQuery(String queryString) {
		Assert.hasText(queryString);
		authoritiesByUsernameQuery = queryString;
	}

	protected String getAuthoritiesByUsernameQuery() {
		Assert.hasText(createUserSql);
		return authoritiesByUsernameQuery;
	}

	public void setCreateUserSql(String createUserSql) {
		Assert.hasText(createUserSql);
		this.createUserSql = createUserSql;
	}

	public void setDeleteUserSql(String deleteUserSql) {
		Assert.hasText(deleteUserSql);
		this.deleteUserSql = deleteUserSql;
	}

	public void setUpdateUserSql(String updateUserSql) {
		Assert.hasText(updateUserSql);
		this.updateUserSql = updateUserSql;
	}

	public void setCreateAuthoritySql(String createAuthoritySql) {
		Assert.hasText(createAuthoritySql);
		this.createAuthoritySql = createAuthoritySql;
	}

	public void setDeleteUserAuthoritiesSql(String deleteUserAuthoritiesSql) {
		Assert.hasText(deleteUserAuthoritiesSql);
		this.deleteUserAuthoritiesSql = deleteUserAuthoritiesSql;
	}

	public void setUserExistsSql(String userExistsSql) {
		Assert.hasText(userExistsSql);
		this.userExistsSql = userExistsSql;
	}

	public void setChangePasswordSql(String changePasswordSql) {
		Assert.hasText(changePasswordSql);
		this.changePasswordSql = changePasswordSql;
	}

	public void setFindAllGroupsSql(String findAllGroupsSql) {
		Assert.hasText(findAllGroupsSql);
		this.findAllGroupsSql = findAllGroupsSql;
	}

	public void setFindUsersInGroupSql(String findUsersInGroupSql) {
		Assert.hasText(findUsersInGroupSql);
		this.findUsersInGroupSql = findUsersInGroupSql;
	}

	public void setInsertGroupSql(String insertGroupSql) {
		Assert.hasText(insertGroupSql);
		this.insertGroupSql = insertGroupSql;
	}

	public void setFindGroupIdSql(String findGroupIdSql) {
		Assert.hasText(findGroupIdSql);
		this.findGroupIdSql = findGroupIdSql;
	}

	public void setInsertGroupAuthoritySql(String insertGroupAuthoritySql) {
		Assert.hasText(insertGroupAuthoritySql);
		this.insertGroupAuthoritySql = insertGroupAuthoritySql;
	}

	public void setDeleteGroupSql(String deleteGroupSql) {
		Assert.hasText(deleteGroupSql);
		this.deleteGroupSql = deleteGroupSql;
	}

	public void setDeleteGroupAuthoritiesSql(String deleteGroupAuthoritiesSql) {
		Assert.hasText(deleteGroupAuthoritiesSql);
		this.deleteGroupAuthoritiesSql = deleteGroupAuthoritiesSql;
	}

	public void setDeleteGroupMembersSql(String deleteGroupMembersSql) {
		Assert.hasText(deleteGroupMembersSql);
		this.deleteGroupMembersSql = deleteGroupMembersSql;
	}

	public void setRenameGroupSql(String renameGroupSql) {
		Assert.hasText(renameGroupSql);
		this.renameGroupSql = renameGroupSql;
	}

	public void setInsertGroupMemberSql(String insertGroupMemberSql) {
		Assert.hasText(insertGroupMemberSql);
		this.insertGroupMemberSql = insertGroupMemberSql;
	}

	public void setDeleteGroupMemberSql(String deleteGroupMemberSql) {
		Assert.hasText(deleteGroupMemberSql);
		this.deleteGroupMemberSql = deleteGroupMemberSql;
	}

	public void setGroupAuthoritiesSql(String groupAuthoritiesSql) {
		Assert.hasText(groupAuthoritiesSql);
		this.groupAuthoritiesSql = groupAuthoritiesSql;
	}

	public void setDeleteGroupAuthoritySql(String deleteGroupAuthoritySql) {
		Assert.hasText(deleteGroupAuthoritySql);
		this.deleteGroupAuthoritySql = deleteGroupAuthoritySql;
	}

	/**
	 * Optionally sets the UserCache if one is in use in the application. This allows the user to be removed from the cache after updates have taken place to
	 * avoid stale data.
	 *
	 * @param userCache
	 *            the cache used by the AuthenticationManager.
	 */
	public void setUserCache(UserCache userCache) {
		Assert.notNull(userCache, "userCache cannot be null");
		this.userCache = userCache;
	}

	/**
	 * Can be overridden to customize the creation of the final UserDetailsObject which is returned by the <tt>loadUserByUsername</tt> method.
	 *
	 * @param username
	 *            the name originally passed to loadUserByUsername
	 * @param userFromUserQuery
	 *            the object returned from the execution of the
	 * @param combinedAuthorities
	 *            the combined array of authorities from all the authority loading queries.
	 * @return the final UserDetails which should be used in the system.
	 */
	protected UserDetails createUserDetails(String username, UserDetails userFromUserQuery, List<GrantedAuthority> combinedAuthorities) {
		String returnUsername = userFromUserQuery.getUsername();

		if (!usernameBasedPrimaryKey) {
			returnUsername = username;
		}

		AccountDto accountDto = (AccountDto) userFromUserQuery;

		return new AccountDto(returnUsername, accountDto.getPassword(), accountDto.getGivenName(), accountDto.getSurname(), accountDto.getMail(),
				accountDto.getMobile(), combinedAuthorities, accountDto.getEnabled(), accountDto.getAccountNonExpired(), accountDto.getCredentialsNonExpired(),
				accountDto.getAccountNonLocked(), accountDto.getGraceLoginsRemaining());
	}

	protected void validateUserDetails(UserDetails user) {
		Assert.hasText(user.getUsername(), "Username may not be empty or null");
		validateAuthorities(user.getAuthorities());
	}

	protected void validateAuthorities(Collection<? extends GrantedAuthority> authorities) {
		Assert.notNull(authorities, "Authorities list must not be null");

		for (GrantedAuthority authority : authorities) {
			Assert.notNull(authority, "Authorities list contains a null entry");
			Assert.hasText(authority.getAuthority(), "getAuthority() method must return a non-empty string");
		}
	}

	protected void insertUserAuthorities(UserDetails user) {
		for (GrantedAuthority auth : user.getAuthorities()) {
			getJdbcTemplate().update(createAuthoritySql, user.getUsername(), auth.getAuthority());
		}
	}

	protected void deleteUserAuthorities(String username) {
		getJdbcTemplate().update(deleteUserAuthoritiesSql, username);
	}
}
