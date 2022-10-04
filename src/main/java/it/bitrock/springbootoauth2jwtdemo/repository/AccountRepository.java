package it.bitrock.springbootoauth2jwtdemo.repository;

import it.bitrock.springbootoauth2jwtdemo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findAccountByUsername(String username);
}
