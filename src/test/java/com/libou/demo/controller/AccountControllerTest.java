package com.libou.demo.controller;

import com.libou.demo.domain.Account;
import com.libou.demo.service.AccountService;
import com.libou.demo.service.TransactionService;
import org.json.JSONException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AccountControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @Before
    public void before() throws Exception{
        System.out.println("Start testing------------------");
    }

    @After
    public void after() throws Exception{
        System.out.println("Finish testing------------------");
    }

    @Test
    public void getAccountList(){
        List<Account> result = accountService.findAllAccounts();
        System.out.println(result.toString());
    }

    @Test
    public void deleteAccount(){
        int id = (int)(Math.random()*10);

        int sum_account = accountService.findAllAccounts().size();
        Account account = accountService.getAccountById(id);
        boolean flag = account != null && account.getBalance() == 0;

        restTemplate.delete("/account?id={1}", id);

        if (flag){
            Assert.assertEquals(sum_account - 1, accountService.findAllAccounts().size());
            Assert.assertNull(accountService.getAccountById(id));
        }else {
            Assert.assertNull(accountService.getAccountById(id));
        }
    }

    @Test
    public void addAccount(){
        int before_sum = accountService.findAllAccounts().size();

        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("name", "libou");
        String result = restTemplate.postForObject("/account", paramMap, String.class);
        System.out.println(accountService.findAllAccounts().size());
        Assert.assertEquals(accountService.findAllAccounts().size(), before_sum + 1);
    }

}