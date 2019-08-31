package com.funch.ledger.dto;

import com.funch.ledger.conf.Category;
import com.funch.ledger.conf.Check;
import com.funch.ledger.conf.Flow;
import com.funch.ledger.conf.Options;
import com.funch.ledger.domain.Ledger;
import com.funch.ledger.exception.EntityException;
import com.funch.ledger.util.NullUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.sound.sampled.Line;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@Slf4j
public class LedgerDto {

    private int seqPk;
    private int cardFk;
    private String detail;
    private String memo;
    @NotBlank(message = "사용날짜를 반드시 입력해야합니다.")
    private LocalDate days;
    @NotBlank(message = "사용여부를 반드시 입력해야합니다.")
    private char info;
    @NotBlank(message = "입/출금 내역을 반드시 입력해야합니다.")
    private char flow;
    @NotNull(message = "금액을 반드시 입력해야합니다.")
    private int money;
    @NotBlank(message = "결제 방법을 반드시 입력해야합니다.")
    private char options;
    @NotBlank(message = "결제 카테고리를 반드시 입력해야합니다.")
    private char category;

    public Ledger toEntity() throws EntityException {
        if(!validation()) {
            log.error("LedgerDto ::: 필수 입력 값을 입력하지 않았습니다. is not validtion");
            throw new EntityException("LedgerDto ::: 필수 입력 값을 입력하지 않았습니다. is not validtion");
        }
        return Ledger.builder().seqPk(seqPk).cardFk(cardFk).info(info).category(category).detail(detail).flow(flow).memo(memo)
                .money(money).options(options).days(days).build();
    }

    public Ledger toChangeEntity(Ledger ledger) throws EntityException {
        this.seqPk = ledger.getSeqPk();
        this.days = NullUtils.isNull(days) ? ledger.getDays() : this.days;
        this.info = Check.validation(this.info) ? this.info : ledger.getInfo();
        this.flow = Flow.validation(this.flow) ? this.flow : ledger.getFlow();
        this.options = Options.validation(this.options) ? this.options : ledger.getOptions();
        this.category = Category.validation(this.category) ? this.category : ledger.getCategory();

        return toEntity();
    }

    private boolean validation() {
        return !(NullUtils.isEmptyOfChar(flow) || NullUtils.isEmptyOfChar(options)
                || NullUtils.isEmptyOfChar(category) || NullUtils.isNull(money) || NullUtils.isNull(days)
                || !validationOfValue() );
    }

    private boolean validationOfValue() {
        return Options.validation(options) && Flow.validation(flow) && Category.validation(category)
                && Check.validation(info);
    }
}
