package com.example.tasker.account;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Page<Account> findAllAccounts(int page, int size) {
        return accountRepository.findAll(PageRequest.of(page, size));
    }

    public Optional<Account> findAccountById(Long id) {
        return accountRepository.findById(id);
    }

    public Account save(Account account) {
        return accountRepository.save(account);
    }

    public Page<Account> findAccountBySearchText(String searchText, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return accountRepository.searchForAccount(searchText, searchText, searchText, searchText, pageRequest);
    }
}
