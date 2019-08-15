package com.funch.ledger.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@ToString
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seqPk;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, columnDefinition = "VARCHAR(2) default '00'")
    private String month;
    @Column(nullable = false, columnDefinition = "VARCHAR(2) default '01'")
    private String day;
    @Column(nullable = false)
    private char flow;
    private String memo;
    @Column(nullable = false)
    private int money;
    @Column(nullable = false, columnDefinition = "VARCHAR(1) default 'Y'")
    private char use;
    @Column(nullable = false, columnDefinition = "VARCHAR(1) default 'N'")
    private char alarm;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
