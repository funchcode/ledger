package com.funch.ledger.dto;


import com.funch.ledger.domain.Card;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
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
    private LocalDate withDrawal;
    private int minimum;
    private int period;
    private int bounds;

    public Card toChangeEntity(Card card) {
        if (!validation()) {
            log.error("카드사와 카드명이 입력되지 않았거나, 빈 값으로 들어와 값이 데이터베이스에 반영되지 않았습니다.");
            return null;
        }

        setEmptyEntity(card);
        return Card.builder().cardPk(card.getCardPk())
                .company(company).name(name).alarm(alarm).info(info)
                .memo(memo).withDrawal(withDrawal).bounds(bounds)
                .minimum(minimum).period(period).build();
    }

    public Card toEntity() {
        if (!validation()) {
            log.error("카드사와 카드명이 입력되지 않았거나, 빈 값으로 들어와 값이 데이터베이스에 반영되지 않았습니다.");
            return null;
        }

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

    private boolean validation() {
        return !(StringUtils.isEmpty(this.company) || StringUtils.isEmpty(this.name));
    }

}
