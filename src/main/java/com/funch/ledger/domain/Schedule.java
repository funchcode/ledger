package com.funch.ledger.domain;

import com.funch.ledger.domain.common.BaseTimeEntity;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@ToString
public class Schedule extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seqPk;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String month;
    @Column(nullable = false)
    private String day;
    @Column(nullable = false)
    private char flow;
    private String memo;
    @Column(nullable = false)
    private int money;
    @Column(nullable = false)
    private char info;
    @Column(nullable = false)
    private char alarm;
}
