package com.funch.ledger.domain;

import com.funch.ledger.repo.CardRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CardEntityTests {

    @Autowired
    private CardRepository cardRepository;

    /**
     * Card Entity에 @Column(nullable = false)로 줬을 경우 테스트
     * 생각 : Null일 경우 어떠한 피드백이 발생
     * 결과 : 데이터를 체크하는 것이 아니라 스키마 생성 시에 가져가는 옵션이다.
     */
    @Test
    public void Entity_Nullable_False_Test() {
        Card allEmptyCard = Card.builder().build();

        Card result = cardRepository.save(allEmptyCard);

        Assert.assertEquals(result, null);
    }

}
