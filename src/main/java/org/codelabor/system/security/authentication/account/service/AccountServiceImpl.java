package org.codelabor.system.security.authentication.account.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.codelabor.system.security.SecurityConstants;
import org.codelabor.system.security.authentication.account.dto.AccountDto;
import org.codelabor.system.security.authentication.account.dto.AccountSearchConditionDto;
import org.codelabor.system.security.authentication.account.manager.AccountManager;
import org.codelabor.system.security.core.context.SecurityContextHolderUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class AccountServiceImpl implements AccountService {

	private Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

	@Autowired
	private AccountManager accountManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public int deleteAccount(String username) {
		accountManager.deleteUser(username);
		return 1;
	}

	@Override
	public int deleteAccountList(List<String> usernameList) {
		int affectedRowCount = 0;
		for (String username : usernameList) {
			affectedRowCount += this.deleteAccount(username);
		}
		return affectedRowCount;
	}

	@Override
	public int insertAccount(AccountDto accountDto) {
		logger.debug("accountDto before manipulation: {}", accountDto);
		// encode password
		accountDto.setPassword(passwordEncoder.encode(accountDto.getPassword()));

		// use default value or not
		if (accountDto.getAuthorities().isEmpty()) {
			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority(SecurityConstants.DEFAULT_ROLE));
			accountDto.setAuthorities(authorities);
		}
		if (accountDto.getEnabled() == null) {
			accountDto.setEnabled(SecurityConstants.DEFAULT_ENABLED);
		}
		if (accountDto.getAccountNonExpired() == null) {
			accountDto.setAccountNonExpired(SecurityConstants.DEFAULT_ACCOUNT_NON_EXPIRED);
		}
		if (accountDto.getCredentialsNonExpired() == null) {
			accountDto.setCredentialsNonExpired(SecurityConstants.DEFAULT_CREDENTIALS_NON_EXPIRED);
		}
		if (accountDto.getAccountNonLocked() == null) {
			accountDto.setAccountNonLocked(SecurityConstants.DEFAULT_ACCOUNT_NON_LOCKED);
		}
		if (accountDto.getGraceLoginsRemaining() == null) {
			accountDto.setGraceLoginsRemaining(SecurityConstants.DEFAULT_GRACE_LOGINS_REMAINING);
		}

		logger.debug("accountDto after manipulation: {}", accountDto);
		accountManager.createUser(accountDto);
		return 1;
	}

	@Override
	public int insertAccountList(List<AccountDto> accountDtoList) {
		int affectedRowCount = 0;
		for (AccountDto accountDto : accountDtoList) {
			affectedRowCount += this.insertAccount(accountDto);
		}
		return affectedRowCount;
	}

	@Override
	public int updateAccount(AccountDto accountDto) {
		logger.debug("accountDto before manipulation: {}", accountDto);
		// encode password
		accountDto.setPassword(passwordEncoder.encode(accountDto.getPassword()));

		// TODO: 불필요한 조회를 하고 있음, SQL를 2개로 분리하여 DB TX를 1개로 줄일 것
		if (!hasRole(SecurityConstants.ROLE_ADMINISTRATOR)) {
			AccountDto originAccountDto = (AccountDto) accountManager.loadUserByUsername(accountDto.getUsername());
			// set role derived from user (not group)
			accountDto.setAuthorities(accountManager.loadUserAuthorities(originAccountDto.getUsername()));
			accountDto.setEnabled(originAccountDto.getEnabled());
			accountDto.setAccountNonLocked(originAccountDto.getAccountNonLocked());
			accountDto.setAccountNonExpired(originAccountDto.getAccountNonExpired());
			accountDto.setCredentialsNonExpired(originAccountDto.getCredentialsNonExpired());
			accountDto.setGraceLoginsRemaining(originAccountDto.getGraceLoginsRemaining());
		}
		logger.debug("accountDto after manipulation: {}", accountDto);
		accountManager.updateUser(accountDto);
		return 1;
	}

	@Override
	public AccountDto selectAccount(String username) {
		return (AccountDto) accountManager.loadUserByUsername(username);
	}

	@Override
	public List<AccountDto> selectAccountList() {
		return accountManager.selectAccountList();
	}

	@Override
	public List<AccountDto> selectAccountListByCondition(AccountSearchConditionDto accountSearchConditionDto) {
		return accountManager.selectAccountListByCondition(accountSearchConditionDto);
	}

	@Override
	public List<AccountDto> selectAccountListByConditionWithPagination(AccountSearchConditionDto accountSearchConditionDto) {
		return accountManager.selectAccountListByConditionWithPagination(accountSearchConditionDto);
	}

	@Override
	public List<AccountDto> selectAccountListByGroupId(String groupId) {
		return accountManager.selectAccountListByGroupId(groupId);
	}

	@Override
	public List<AccountDto> selectAccountListByRoleId(String roleId) {
		return accountManager.selectAccountListByRoleId(roleId);
	}

	@Override
	public Integer getNumberOfRow(AccountSearchConditionDto accountSearchConditionDto) {
		return accountManager.getNumberOfRow(accountSearchConditionDto);
	}

	public void changePassword(String oldPassword, String newPassword) {
		((JdbcUserDetailsManager) accountManager).changePassword(oldPassword, passwordEncoder.encode(newPassword));
	}

	/**
	 *
	 * check current user has specific role
	 *
	 * @param roleName
	 *            Role name
	 * @return
	 */
	public static boolean hasRole(String roleName) {
		Assert.notNull(roleName);
		boolean hasRole = false;
		SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			Authentication authentication = context.getAuthentication();
			if (authentication != null) {
				Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
				for (GrantedAuthority authority : authorities) {
					if (roleName.equals(authority.getAuthority())) {
						hasRole = true;
						break;
					}
				}
			}
		}
		LoggerFactory.getLogger(SecurityContextHolderUtils.class).debug("hasRole('{}'): {}", roleName, hasRole);
		return hasRole;
	}

}
