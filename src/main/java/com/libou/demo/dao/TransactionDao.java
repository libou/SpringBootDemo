package com.libou.demo.dao;

import com.libou.demo.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionDao extends JpaRepository<Transaction, Integer> {
    @Query(value = "select t from Transaction t where t.account_id=?1")
    List<Transaction> getTransactionsByAccountId(int account_id);
}
