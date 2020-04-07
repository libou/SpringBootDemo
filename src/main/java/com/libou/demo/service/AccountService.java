package com.libou.demo.service;

import com.libou.demo.domain.Account;

import java.util.List;

public interface AccountService {
    List<Account> findAllAccounts();

    void addAccount(Account account);

    void deleteAccount(Integer id);

    void updateAccount(Account account);

    Account getAccountById(Integer id);
}
