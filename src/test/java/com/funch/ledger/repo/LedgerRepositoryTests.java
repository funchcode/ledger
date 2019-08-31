package com.funch.ledger.repo;

import com.funch.ledger.conf.Category;
import com.funch.ledger.conf.Check;
import com.funch.ledger.conf.Flow;
import com.funch.ledger.conf.Options;
import com.funch.ledger.domain.Card;
import com.funch.ledger.domain.Ledger;
import com.funch.ledger.dto.LedgerDto;
import com.funch.ledger.exception.EntityException;
import com.funch.ledger.spec.LedgerSpecs;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class LedgerRepositoryTests {

    @Autowired
    private LedgerRepository ledgerRepository;

    @Autowired
    private CardRepository cardRepository;

    /**
     * INSERT TEST
     * 필수 값 있을 때와
     * 필수 값 없을 경우
     * 필수 값 : days, info, money, category, flow, options
     */
    @Test(expected = EntityException.class)
    public void Save_Test() throws EntityException {
        Card card = cardRepository.findTopByOrderByCardPkDesc();
        int cardFk = card.getCardPk();
        String detail = "장부 디테일 테스트";
        String memo = "장부 메모 테스트";
        char flow = Flow.OUT.value();
        LocalDate days = LocalDate.now();
        int money = 100000;
        char info = Check.YES.value();
        char option = Options.CARD.value();
        char category = 'Z';

        // 필수 값 모두 포함
        LedgerDto trueLedgerDto = new LedgerDto();
        trueLedgerDto.setCardFk(cardFk);
        trueLedgerDto.setDetail(detail);
        trueLedgerDto.setMemo(memo);
        trueLedgerDto.setFlow(flow);
        trueLedgerDto.setDays(days);
        trueLedgerDto.setMoney(money);
        trueLedgerDto.setInfo(info);
        trueLedgerDto.setOptions(option);
        trueLedgerDto.setCategory(category);
        Ledger trueLedger = trueLedgerDto.toEntity();
        Ledger expectTrue = ledgerRepository.save(trueLedger);
        Assert.assertNotEquals(expectTrue, null);
        if (!ObjectUtils.isEmpty(expectTrue)) {
            ledgerRepository.delete(expectTrue);
        }

        // 필수 값 없을 경우
        LedgerDto falseLedgerDto = new LedgerDto();
        falseLedgerDto.setCardFk(cardFk);
        falseLedgerDto.setDetail(detail);
        falseLedgerDto.setMemo(memo);
        falseLedgerDto.setFlow(flow);
        falseLedgerDto.setDays(days);
        falseLedgerDto.setMoney(money);
        falseLedgerDto.setInfo(info);
        falseLedgerDto.setOptions(option);
        falseLedgerDto.setCategory(category);
        Ledger falseLedger = falseLedgerDto.toEntity();
        Assert.assertEquals(falseLedger, null);
        if (!ObjectUtils.isEmpty(falseLedger)) {
            ledgerRepository.delete(falseLedger);
        }
    }

    /**
     * TODO : Update 테스트
     * 테스트 : 필수 값을 넣지 않을 경우
     */
    @Test
    public void Update_Test() throws EntityException {
        int seqPk = 1;

        char flow = 'O';
        char option = 'C';
        char info = 'N';
        char category = 'J';

        Ledger originLedger = ledgerRepository.findOneBySeqPk(seqPk);

        LedgerDto ledgerDto = new LedgerDto();
        ledgerDto.setFlow(flow);
        ledgerDto.setOptions(option);
        ledgerDto.setInfo(info);
        ledgerDto.setCategory(category);

        Ledger newLedger = ledgerDto.toChangeEntity(originLedger);

        Assert.assertNotEquals(newLedger, null);

        Ledger inLedger = ledgerRepository.save(newLedger);

        Assert.assertEquals(inLedger.getFlow(), flow);
        Assert.assertEquals(inLedger.getOptions(), option);
        Assert.assertEquals(inLedger.getCategory(), category);
        Assert.assertEquals(inLedger.getInfo(), info);
    }

    /**
     *  TODO 데이터 검색 :
     *  카드 방법 데이터, 특정 금액 이상|이하, 특정 카드 검색,
     *  카테고리 검색, 입출 데이터, 사용 날짜 검색
     */
    @Test
    public void SEARCH_TEST() {
        Map<String, String> filter = new HashMap<>();
        filter.put("cardFk","0");
        filter.put("detail","");
        filter.put("memo","");
        filter.put("info","");
        filter.put("category","");
        filter.put("flow","");
        filter.put("options","");
        filter.put("money","");
        filter.put("days","");
        filter.put("toMoney","");
        filter.put("fromMoney","");
        filter.put("toDays","");
        filter.put("fromDays","");
        List<Ledger> ledgers = ledgerRepository.findAll(LedgerSpecs.searchLedger(filter));
        log.info("FILTER INFO :::::: "+filter.toString());
        log.info("RESULT LEDGER :::::: "+ledgers.toString());

//        ledgers = ledgerRepository.findAll(LedgerSpecs.searchLedger(filter));
//        log.info("FILTER INFO :::::: "+filter.toString());
//        log.info("RESULT LEDGER :::::: "+ledgers.toString());
//
//        ledgers = ledgerRepository.findAll(LedgerSpecs.searchLedger(filter));
//        log.info("FILTER INFO :::::: "+filter.toString());
//        log.info("RESULT LEDGER :::::: "+ledgers.toString());
    }

    /**
     *
     */
}
