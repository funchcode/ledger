package com.funch.ledger.domain;

import com.funch.ledger.dto.LedgerDto;
import com.funch.ledger.exception.EntityException;
import com.funch.ledger.repo.LedgerRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LedgerEntityTests {

    /**
     * Flow, Money, Option, Category 빈 값을 줬을 때
     * validation Check가 정상적으로 동작하고 null을 반환하는지 테스트
     */
    @Test
    public void Ledger_Null_Check_Tests() throws EntityException {
        LedgerDto ledgerDto = new LedgerDto();
        Ledger ledger = ledgerDto.toEntity();

        Assert.assertEquals(ledger, null);
    }

    /**
     * Flow, Option, Category 정해진 범위 내에 포함된 값이 들어왔는지 체크
     */
    @Test
    public void Ledger_Constant_Check_Tests() throws EntityException {
        char flow = 'O';
        char option = 'H';
        char category = 'F';
        int money = 1000;
        LedgerDto ledgerDto = new LedgerDto();
        ledgerDto.setMoney(money);
        ledgerDto.setOptions(option);
        ledgerDto.setCategory(category);
        ledgerDto.setFlow(flow);
        Ledger goodLedger = ledgerDto.toEntity();

        Assert.assertNotEquals(goodLedger, null);

        char ncategory = 'Z';
        ledgerDto.setCategory(ncategory);
        Ledger bedLedger = ledgerDto.toEntity();

        Assert.assertEquals(bedLedger, null);
    }

}
