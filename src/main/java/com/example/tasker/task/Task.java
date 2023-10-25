package com.example.tasker.task;

import com.example.tasker.account.Account;
import com.example.tasker.domain.BaseAbstractEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "task")
public class Task extends BaseAbstractEntity {
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(name = "description", length = 1000)
    @JdbcTypeCode(SqlTypes.NVARCHAR)
    private String description;

    @Column(name = "subject", nullable = false)
    @JdbcTypeCode(SqlTypes.NVARCHAR)
    private String subject;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}