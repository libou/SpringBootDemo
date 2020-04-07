package com.libou.demo.service;

import com.libou.demo.dao.AccountDao;
import com.libou.demo.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountDao accountDao;

    @Override
    public List<Account> findAllAccounts() {
        return accountDao.findAll();
    }

    @Override
    public void addAccount(Account account) {
        accountDao.save(account);
    }

    @Override
    public void deleteAccount(Integer id) {
        accountDao.deleteById(id);
    }

    @Override
    public void updateAccount(Account account) {
        accountDao.save(account);
    }

    @Override
    public Account getAccountById(Integer id) {
        return accountDao.findById(id).orElse(null);
    }
}
