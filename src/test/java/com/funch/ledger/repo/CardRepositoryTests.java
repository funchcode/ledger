package com.funch.ledger.repo;

import com.funch.ledger.domain.Card;
import com.funch.ledger.dto.CardDto;
import org.assertj.core.api.LocalDateAssert;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.NullableUtils;
import org.springframework.lang.Nullable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CardRepositoryTests {

    @Autowired
    private CardRepository cardRepository;

    @Before
    public void before() {

    }

    /**
     * use, alarm, created, updated 기본 값 들어가는지 확인
     * Test Data
     */
    @Test
    public void Entity_Default_Value_Test() {
        String company = "삼성";
        String name = "삼성 S카드";
        String memo = "유명 카페 프렌차이즈 할인 혜택";
        LocalDate withDrawal = LocalDate.now();
        int minimun = 300000;
        int period = 12;
        int bounds = 1000000;

        // 1. use, alarm  값 null
        Card card = null;
        CardDto cardDto = new CardDto();
        cardDto.setCompany(company);
        cardDto.setName(name);
        cardDto.setMemo(memo);
        cardDto.setWithDrawal(withDrawal);
        //cardDto.setMinimum(minimun);
        cardDto.setPeriod(period);
        cardDto.setBounds(bounds);
        card = cardDto.toEntity();

        Card inCardInfo = cardRepository.save(card);

        Assert.assertEquals(inCardInfo.getName(), card.getName());
    }

    /**
     * 데이터 변경 테스트
     * 변경 허용 프로퍼티 : 메모, 사용여부, 알람사용, 출금날짜, 최소사용금액, 최소한도기간, 한도, 카드사, 카드명
     * 변경 불가 프로퍼티 : 기본키
     * 테스트 로직 : null일 경우 기존 데이터, ""일 경우 "" 값이 들어가야 한다.
     * 카드사, 카드명은 ""의 값이면 안된다.
     */
    @Test
    public void Change_Info_Value() {
        String com = "현대카드";
        String name = "현대 M 카드";
        String memo = "KT 폰 요금 할인";
        char info = 'N';
        char alarm = 'Y';
        LocalDate withdrawal = LocalDate.now();
        int period = 3;
        int minimum = 250000;
        int bounds = 10000000;

        CardDto want = new CardDto();
        want.setCompany(com);
        want.setName(null);
        want.setMemo(null);
        want.setInfo(info);
        want.setAlarm(alarm);
        want.setPeriod(period);
        want.setMinimum(100000);
        want.setBounds(bounds);

        Card origin = cardRepository.findTopByOrderByCardPkDesc();
        Card change = cardRepository.save(want.toChangeEntity(origin));

        Assert.assertEquals(origin.getCardPk(), change.getCardPk());
    }

    @After
    public void after() {
        // 모든 데이터 삭제
        //cardRepository.deleteAll();
    }

}
