package demo.com.bankingapplication.Service.impl;

import demo.com.bankingapplication.Entity.Account;
import demo.com.bankingapplication.Repository.AccountRepo;
import demo.com.bankingapplication.Service.AccountService;
import demo.com.bankingapplication.dto.AccountDto;
import demo.com.bankingapplication.mapper.AccountMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepo accountRepo;

    public AccountServiceImpl(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapAccount(accountDto);
        Account savedAccount=accountRepo.save(account);
        return AccountMapper.mapAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(long id) throws Exception {
        Optional<Account> account= accountRepo.findById(id);
        if(account.isEmpty()) {
            throw new Exception("Account not found");
        }
        AccountDto accountDto = AccountMapper.mapAccountDto(account.get());
        return accountDto;
    }


    @Override
    public AccountDto deposit(long id, double amount) {
        Optional<Account> account = accountRepo.findById(id);
        if (account.isEmpty()) {
            throw new RuntimeException("Account not found");
        }
        double total = account.get().getBalance() + amount;
        account.get().setBalance(total);
        Account saved = accountRepo.save(account.get());
        return AccountMapper.mapAccountDto(saved);
    }

    @Override
    public AccountDto withdraw(long id, double amount) {
        Optional<Account> account = accountRepo.findById(id);
        if (account.isEmpty()) {
            throw new RuntimeException("Account not found");
        }
        if(account.get().getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }
        double total = account.get().getBalance() - amount;
        account.get().setBalance(total);
        Account saved = accountRepo.save(account.get());
        return AccountMapper.mapAccountDto(saved);
    }
}
