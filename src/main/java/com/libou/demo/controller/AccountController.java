package com.libou.demo.controller;

import com.libou.demo.domain.Account;
import com.libou.demo.domain.Transaction;
import com.libou.demo.service.AccountService;
import com.libou.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionService transactionService;

    @RequestMapping("/accountInfo")
    public List<Account> userList(){
        return accountService.findAllAccounts();
    }

    @PostMapping("/account")
    public String addAccount(@RequestParam("name") String name){
        Account account = new Account(name);
        accountService.addAccount(account);
        return "Success";
    }

    @DeleteMapping("/account")
    public String deleteAccount(@RequestParam("id") Integer id){
        Account account = accountService.getAccountById(id);
        if (account == null) return "Account not found!";
        else {
            if (account.getBalance() != 0) return "Forbidden";
            else {
                accountService.deleteAccount(id);
                return "Delete successfully";
            }
        }
    }

    @GetMapping("/account/{account_id}")
    public List<Transaction> getDetails(@PathVariable("account_id") Integer account_id){
        return transactionService.findAllTransactionsByAccountId(account_id);
    }
}
