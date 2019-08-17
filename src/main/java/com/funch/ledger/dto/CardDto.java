package com.funch.ledger.dto;


import com.funch.ledger.domain.Card;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@Setter
public class CardDto {

    private String company;
    private String name;
    private String memo;
    @NotNull
    private char info;
    @NotNull
    private char alarm;
    private LocalDate withDrawal;
    private int minimum;
    private int period;
    private int bounds;

    public Card toChangeEntity(Card card) {
        setEmptyEntity(card);

        return Card.builder().cardPk(card.getCardPk())
                .company(company).name(name).alarm(alarm).info(info)
                .memo(memo).withDrawal(withDrawal).bounds(bounds)
                .minimum(minimum).period(period).build();
    }

    public Card toEntity() {
        return Card.builder().company(company).name(name).memo(memo).info(info).alarm(alarm)
                .withDrawal(withDrawal).minimum(minimum).period(period).bounds(bounds).build();
    }

    private void setEmptyEntity(Card card) {
        this.alarm = this.alarm == '\u0000' ? card.getAlarm() : this.alarm;
        this.info = this.info == '\u0000' ? card.getInfo() : this.info;
        this.memo = this.memo == null ? card.getMemo() : this.memo;
        this.company = this.company == null ? card.getCompany() : this.company;
        this.name = this.name == null ? card.getName() : this.name;
        this.withDrawal = this.withDrawal == null ? card.getWithDrawal() : this.withDrawal;
    }

}
