package com.localjobs.jdbc.repository;

import com.localjobs.domain.Account;

public interface AccountRepository {
	
	void createAccount(Account account) throws UsernameAlreadyInUseException;

	Account findAccountByUsername(String username);
	
}
