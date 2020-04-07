package com.libou.demo.service;

import com.libou.demo.dao.TransactionDao;
import com.libou.demo.domain.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionDao transactionDao;

    @Override
    public List<Transaction> findAllTransactions() {
        return transactionDao.findAll();
    }

    @Override
    public List<Transaction> findAllTransactionsByAccountId(Integer account_id) {
        return transactionDao.getTransactionsByAccountId(account_id);
    }

    @Override
    public void addTransaction(Transaction transaction) {
        transactionDao.save(transaction);
    }
}
