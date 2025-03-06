package demo.com.bankingapplication.mapper;

import demo.com.bankingapplication.Entity.Account;
import demo.com.bankingapplication.dto.AccountDto;

public class AccountMapper {
    public static Account mapAccount(AccountDto accountDto) {
        Account account = new Account(
                accountDto.getId(),
                accountDto.getBalance(),
                accountDto.getAccountHolderName()
        );
        return account;
    }
    public static AccountDto mapAccountDto(Account account) {
     AccountDto accountDto = new AccountDto(
             account.getId(),
             account.getBalance(),
             account.getAccountHolderName()
     );
     return accountDto;
    }
}
