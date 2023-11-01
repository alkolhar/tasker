package com.example.tasker.account;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AccountModelAssembler implements RepresentationModelAssembler<Account, EntityModel<Account>> {
    @NonNull
    @Override
    public EntityModel<Account> toModel(@NonNull Account entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(AccountController.class).getAccountById(entity.getId())).withSelfRel());
    }
}
