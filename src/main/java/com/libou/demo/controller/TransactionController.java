package com.libou.demo.controller;

import com.libou.demo.domain.Account;
import com.libou.demo.domain.Transaction;
import com.libou.demo.service.AccountService;
import com.libou.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {
    @Autowired
    AccountService accountService;

    @Autowired
    TransactionService transactionService;

    @PostMapping("/transaction")
    public String makeTransaction(Transaction transaction){
        Account account = accountService.getAccountById(transaction.getAccount_id());
        if (account == null) return "Account not existed";
        switch (transaction.getAction()){
            case "deposit":{
                account.setBalance(transaction.getAmount() + account.getBalance());
                accountService.updateAccount(account);
                transactionService.addTransaction(transaction);
                return "Transaction successes";
            }
            case "withdraw":{
                int current_balance = account.getBalance();
                if (current_balance < transaction.getAmount()){
                    return "Insufficient Balance!";
                }else {
                    account.setBalance(current_balance - transaction.getAmount());
                    accountService.updateAccount(account);
                    transactionService.addTransaction(transaction);
                    return "Transaction successes";
                }
            }
            default:
                return "Wrong transaction";
        }
    }

    @RequestMapping("/transactionList")
    public List<Transaction> getAllTransactions(){
        return transactionService.findAllTransactions();
    }
}
