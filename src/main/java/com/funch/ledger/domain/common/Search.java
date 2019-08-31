package com.funch.ledger.domain.common;

import com.funch.ledger.domain.Card;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Search {

    private Card card;
    private String toDays;
    private String fromDays;
    private String toMoney;
    private String fromMoney;

    public Card getCard() {
        return card;
    }

    public String getToDays() {
        return toDays;
    }

    public String getFromDays() {
        return fromDays;
    }

    public String getToMoney() {
        return toMoney;
    }

    public String getFromMoney() {
        return fromMoney;
    }
}
