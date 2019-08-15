package com.funch.ledger.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@ToString
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cardPk;
    private String company;
    private String name;
    private String memo;
    @Column(nullable = false, columnDefinition = "VARCHAR(1) default 'Y'")
    private char use;
    @Column(nullable = false, columnDefinition = "VARCHAR(1) default 'N'")
    private char alarm;
    private LocalDateTime withDrawal;
    private int minimum;
    private int period;
    private int limit;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
