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
@Table(name = "CARD_TB")
public class Card extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cardPk;
    @Column(nullable = false)
    private String company;
    @Column(nullable = false)
    private String name;
    private String memo;
    @Column(nullable = false)
    private char info;
    @Column(nullable = false)
    private char alarm;
    @Column(name = "withdrawal")
    private LocalDate withDrawal;
    private int minimum;
    private int period;
    private int bounds;

    @PrePersist
    public void prePersist() {
        this.info = '\u0000' == this.info ? 'Y' : this.info;
        this.alarm = '\u0000' == this.alarm ? 'N' : this.alarm;
    }

    @Builder
    public Card(int cardPk, String company, String name, String memo, char info, char alarm,
                LocalDate withDrawal, int minimum, int period, int bounds) {
        this.cardPk = cardPk;
        this.company = company;
        this.name = name;
        this.memo = memo;
        this.info = info;
        this.alarm = alarm;
        this.withDrawal = withDrawal;
        this.minimum = minimum;
        this.period = period;
        this.bounds = bounds;
    }

}
