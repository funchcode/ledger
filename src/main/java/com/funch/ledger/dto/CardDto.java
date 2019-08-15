package com.funch.ledger.dto;


import com.funch.ledger.domain.Card;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class CardDto {

    private String company;
    private String name;
    private String memo;
    @NotNull
    private char use;
    @NotNull
    private char alarm;
    private LocalDateTime withDrawal;
    private int minimum;
    private int period;
    private int limit;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Card toEntity() {
        return null;
    }


}
