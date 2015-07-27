package org.codelabor.system.security.authentication.account.service;

import java.util.List;

import org.codelabor.system.security.authentication.account.dto.AccountDto;
import org.codelabor.system.security.authentication.account.dto.AccountSearchConditionDto;

public interface AccountService {

	public abstract int deleteAccount(String username);

	public abstract int deleteAccountList(List<String> usernameList);

	public abstract int insertAccount(AccountDto accountDto);

	public abstract int insertAccountList(List<AccountDto> accountDtoList);

	public abstract int updateAccount(AccountDto accountDto);

	public abstract AccountDto selectAccount(String username);

	public abstract List<AccountDto> selectAccountList();

	public abstract List<AccountDto> selectAccountListByCondition(AccountSearchConditionDto accountSearchConditionDto);

	public abstract List<AccountDto> selectAccountListByConditionWithPagination(AccountSearchConditionDto accountSearchConditionDto);

	public abstract List<AccountDto> selectAccountListByGroupId(String groupId);

	public abstract List<AccountDto> selectAccountListByRoleId(String roleId);

	public abstract Integer getNumberOfRow(AccountSearchConditionDto accountSearchConditionDto);

}