package demo.com.bankingapplication.Service;

import demo.com.bankingapplication.dto.AccountDto;

public interface AccountService {
     AccountDto createAccount(AccountDto accountDto);
     AccountDto getAccountById(long id) throws Exception;
     AccountDto deposit(long id, double amount);
     AccountDto withdraw(long id, double amount) ;
}
