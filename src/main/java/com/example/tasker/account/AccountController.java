package com.example.tasker.account;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Transactional
@RestController
@RequestMapping("api/accounts")
public class AccountController {

    private final AccountService accountService;
    private final AccountModelAssembler modelAssembler;
    private final PagedResourcesAssembler<Account> pagedResourcesAssembler;

    public AccountController(AccountService accountService, AccountModelAssembler modelAssembler, PagedResourcesAssembler<Account> pagedResourcesAssembler) {
        this.accountService = accountService;
        this.modelAssembler = modelAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Account>>> getAllAccounts(@RequestParam(defaultValue = "0") int page,
                                                                                @RequestParam(defaultValue = "10") int size) {
        var accountPage = accountService.findAllAccounts(page, size);
        return ResponseEntity.ok(pagedResourcesAssembler.toModel(accountPage, modelAssembler));
    }

    @GetMapping("search")
    public ResponseEntity<CollectionModel<EntityModel<Account>>> searchForAccount(@RequestParam(defaultValue = "") String searchText,
                                                                                  @RequestParam(defaultValue = "0") int page,
                                                                                  @RequestParam(defaultValue = "10") int size) {
        var accounts = accountService.findAccountBySearchText(searchText, page, size);
        return ResponseEntity.ok(pagedResourcesAssembler.toModel(accounts, modelAssembler));
    }

    @GetMapping("{id}")
    public ResponseEntity<EntityModel<Account>> getAccountById(@PathVariable Long id) {
        EntityModel<Account> account = accountService.findAccountById(id)
                .map(modelAssembler::toModel)
                .orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok(account);
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody @Validated Account account) {
        Account save = accountService.save(account);
        EntityModel<Account> model = modelAssembler.toModel(save);
        return ResponseEntity
                .created(model.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .build();
    }
}
