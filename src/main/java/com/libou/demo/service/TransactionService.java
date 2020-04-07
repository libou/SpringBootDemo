package com.libou.demo.service;

import com.libou.demo.domain.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> findAllTransactions();

    List<Transaction> findAllTransactionsByAccountId(Integer account_id);

    void addTransaction(Transaction transaction);
}
