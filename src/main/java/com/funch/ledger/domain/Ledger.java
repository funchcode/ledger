package com.funch.ledger.domain;

import com.funch.ledger.domain.common.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@ToString
@Table(name = "LEDGER_TB")
public class Ledger extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ_PK", length = 5)
    private int seqPk;
    @Column(name = "CARD_FK", length = 5)
    private int cardFk;
    @Column(length = 100)
    private String detail;
    @Column(length = 100)
    private String memo;
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDate days;
    @Column(nullable = false, columnDefinition = "VARCHAR(1) default 'Y'")
    private char info;
    @Column(nullable = false, columnDefinition = "VARCHAR(1) default 'I'")
    private char flow;
    @Column(nullable = false, length = 9)
    private int money;
    @Column(nullable = false, columnDefinition = "VARCHAR(1)")
    private char options;
    @Column(nullable = false, columnDefinition = "VARCHAR(1)")
    private char category;

    @Builder
    public Ledger(int seqPk, int cardFk, String detail, String memo, char info, char flow, int money,
                  LocalDate days, char options, char category) {
        this.seqPk = seqPk;
        this.cardFk = cardFk;
        this.detail = detail;
        this.memo = memo;
        this.info = info;
        this.flow = flow;
        this.days = days;
        this.money = money;
        this.options = options;
        this.category = category;
    }
}
