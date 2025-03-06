package demo.com.bankingapplication.Controller;


import demo.com.bankingapplication.Service.AccountService;
import demo.com.bankingapplication.dto.AccountDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/accounts")

public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // add Account restApi
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }


    //get account restapi
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(long id) {
        try {
            AccountDto accountDto1 = accountService.getAccountById(id);
            return new ResponseEntity<>(accountDto1, HttpStatus.OK);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Account not found");
        }
    }

    // Deposit REST API
// Deposit REST API
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable long id, @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        if (amount == null || amount <= 0) {
            throw new RuntimeException("Invalid deposit amount");
        }
        AccountDto accountDto = accountService.deposit(id, amount);
        return ResponseEntity.ok(accountDto);
    }
    // withdraw RestApi

    public ResponseEntity<AccountDto> withdrawal(@PathVariable long id, @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        if (amount == null || amount <= 0) {
            throw new RuntimeException("Invalid withdrawal amount");
        }
        AccountDto accountDto = accountService.withdraw(id, amount);
        return ResponseEntity.ok(accountDto);
    }

}
