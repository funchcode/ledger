package com.funch.ledger.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@ToString
public class Ledger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seqPk;
    private int cardFk;
    private String detail;
    private String memo;
    @Column(nullable = false)
    private char flow;
    @Column(nullable = false)
    private int money;
    @Column(nullable = false)
    private char option;
    @Column(nullable = false)
    private char category;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
