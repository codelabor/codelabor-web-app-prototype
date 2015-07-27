package org.codelabor.system.security.authentication.account.manager;

import java.util.List;

import org.codelabor.system.security.authentication.account.dto.AccountDto;
import org.codelabor.system.security.authentication.account.dto.AccountSearchConditionDto;
import org.springframework.security.core.GrantedAuthority;
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

	public abstract void deleteUser(String username);

	public abstract List<AccountDto> selectAccountList();

	public abstract List<AccountDto> selectAccountListByCondition(AccountSearchConditionDto accountSearchConditionDto);

	public abstract List<AccountDto> selectAccountListByConditionWithPagination(AccountSearchConditionDto accountSearchConditionDto);

	public abstract List<AccountDto> selectAccountListByGroupId(String groupId);

	public abstract List<AccountDto> selectAccountListByRoleId(String roleId);

	public abstract Integer getNumberOfRow(AccountSearchConditionDto accountSearchConditionDto);

	public abstract int deleteAccountList(List<String> id);

	public abstract List<GrantedAuthority> loadUserAuthorities(String username);

}