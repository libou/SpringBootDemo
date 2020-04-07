package com.libou.demo.controller;

import com.libou.demo.domain.Account;
import com.libou.demo.domain.Transaction;
import com.libou.demo.service.AccountService;
import com.libou.demo.service.TransactionService;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TransactionControllerTest {
    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void before() throws Exception{
        System.out.println("Start testing------------------");
    }

    @After
    public void after() throws Exception{
        System.out.println("Finish testing------------------");
    }

    @Test
    public void deposit(){
        int account_id = (int)(Math.random()*10);
        int amount = (int)(Math.random()*100);
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("account_id", account_id);
        paramMap.add("action", "withdraw");
        paramMap.add("amount", amount);
        paramMap.add("user", "libou");


        Account account = accountService.getAccountById(account_id);
        int previous_balance = 0;
        if (account != null)
            previous_balance = account.getBalance();
        String result = restTemplate.postForObject("/transaction", paramMap, String.class);

        if (account == null){
            assertEquals("Account not existed", result);
        }else {
            assertEquals(previous_balance + amount, account.getBalance());
        }
    }

    @Test
    public void withdraw(){
        int account_id = (int)(Math.random()*10);
        int amount = (int)(Math.random()*100);
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("account_id", account_id);
        paramMap.add("action", "withdraw");
        paramMap.add("amount", amount);
        paramMap.add("user", "libou");

        Account account = accountService.getAccountById(account_id);
        int previous_balance = 0;
        if (account != null)
            previous_balance = account.getBalance();
        String result = restTemplate.postForObject("/transaction", paramMap, String.class);

        if (account == null){
            assertEquals("Account not existed", result);
        }else {
            if (amount > previous_balance){
                assertEquals(result, "Insufficient Balance!");
                assertEquals(previous_balance, account.getBalance());
            }else {
                assertEquals(previous_balance - amount, account.getBalance());
            }
        }
    }

}