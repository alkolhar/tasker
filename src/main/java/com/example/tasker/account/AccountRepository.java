package com.example.tasker.account;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("""
            select a from Account a
            where upper(a.name) like upper(concat('%', ?1, '%')) or upper(a.firstName) like upper(concat('%', ?2, '%'))
             or upper(a.login) like upper(concat('%', ?3, '%')) or upper(a.email) like upper(concat('%', ?4, '%'))
             """)
    Page<Account> searchForAccount(String name, String firstName, String login, String email, Pageable pageable);
}