package org.codelabor.system.security.authentication.account.manager;

import java.util.List;

import org.codelabor.system.security.authentication.account.dto.AccountDto;
import org.codelabor.system.security.authentication.account.dto.AccountSearchConditionDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AccountManager {

	public abstract int deleteAccount(String username);

	public abstract int deleteAccountList(List<String> usernameList);

	public abstract void insertAccount(AccountDto account);

	public abstract int insertAccountList(List<String> usernameList);

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

	public abstract void updateAccount(AccountDto account);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codelabor.system.security.authentication.account.manager.AccountManager #loadUserByUsername(java.lang.String)
	 */
	public abstract UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

	public abstract AccountDto selectAccount(String username);

	public abstract List<AccountDto> selectAccountList();

	public abstract List<AccountDto> selectAccountListByCondition(AccountSearchConditionDto accountSearchConditionDto);

	public abstract List<AccountDto> selectAccountListByConditionWithPagination(AccountSearchConditionDto accountSearchConditionDto);

	public abstract List<AccountDto> selectAccountListByGroupId(String groupId);

	public abstract List<AccountDto> selectAccountListByRoleId(String roleId);

	public abstract Integer getNumberOfRow(AccountSearchConditionDto accountSearchConditionDto);

}