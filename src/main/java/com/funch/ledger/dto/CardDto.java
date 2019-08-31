package com.funch.ledger.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.funch.ledger.conf.Check;
import com.funch.ledger.domain.Card;
import com.funch.ledger.exception.EntityException;
import com.funch.ledger.util.NullUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.swing.text.html.parser.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@Slf4j
public class CardDto {

    private int cardPk;
    @NotBlank(message = "카드의 카드사를 반드시 입력해야합니다.")
    private String company;
    @NotBlank(message = "카드의 명칭을 반드시 입력해야합니다.")
    private String name;
    private String memo;
    @NotNull(message = "카드의 사용유무를 반드시 정해야합니다.")
    private char info;
    @NotNull(message = "알람의 사용유무를 반드시 정해야합니다.")
    private char alarm;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate withDrawal;
    private int minimum;
    private int period;
    private int bounds;

    public Card toChangeEntity(Card card) throws EntityException {
        this.cardPk = card.getCardPk();
        this.alarm = Check.validation(this.alarm) ? card.getAlarm() : this.alarm;
        this.info = Check.validation(this.info) ? card.getInfo() : this.info;
        this.memo = this.memo == null ? card.getMemo() : this.memo;
        this.company = this.company == null ? card.getCompany() : this.company;
        this.name = this.name == null ? card.getName() : this.name;
        this.withDrawal = this.withDrawal == null ? card.getWithDrawal() : this.withDrawal;

        return toEntity();
    }

    public Card toEntity() throws EntityException {
        if (!validation()) {
            log.error("카드사와 카드명이 입력되지 않았거나, 빈 값으로 들어와 값이 데이터베이스에 반영되지 않았습니다.");
            throw new EntityException("카드사와 카드명이 입력되지 않았거나, 빈 값으로 들어와 값이 데이터베이스에 반영되지 않았습니다.");
        }

        return Card.builder().cardPk(cardPk).company(company).name(name).memo(memo).info(info).alarm(alarm)
                .withDrawal(withDrawal).minimum(minimum).period(period).bounds(bounds).build();
    }

    private boolean validation() {
        return !(NullUtils.isEmptyOfString(this.company) || NullUtils.isEmptyOfString(this.name)
                || !validationOfValue());
    }

    private boolean validationOfValue() {
        return Check.validation(this.alarm) && Check.validation(this.info);
    }

}
