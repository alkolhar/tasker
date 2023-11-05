package com.example.tasker.task;

import com.example.tasker.account.Account;
import com.example.tasker.domain.BaseAbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Getter
@Setter
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

}