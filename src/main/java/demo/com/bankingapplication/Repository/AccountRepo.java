package demo.com.bankingapplication.Repository;

import demo.com.bankingapplication.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {

    Account save(Account account);
}
