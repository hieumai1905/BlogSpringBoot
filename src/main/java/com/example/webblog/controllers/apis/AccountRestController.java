package com.example.webblog.controllers.apis;

import com.example.webblog.models.Account;
import com.example.webblog.servies.account.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/accounts")
public class AccountRestController {
    @Autowired
    private IAccountService accountService;

    @GetMapping
    public ResponseEntity<List<Account>> getAccounts() {
        return ResponseEntity.ok((List<Account>) accountService.findAll());
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable("accountId") Long accountId) {
        Account account = accountService.findById(accountId).orElse(null);
        if (account == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(account);
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<Account> updateAccount(@PathVariable("accountId") Long accountId, @RequestBody Account account){
        account.setAccountId(accountId);
        Account accountUpdate = accountService.save(account);
        return ResponseEntity.ok(accountUpdate);
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Boolean> deleteAccount(@PathVariable("accountId") Long accountId){
        Account account = accountService.findById(accountId).orElse(null);
        if(account == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(accountService.remove(accountId));
    }
}
