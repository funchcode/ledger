package com.funch.ledger.domain;

import com.funch.ledger.dto.CardDto;
import com.funch.ledger.exception.EntityException;
import com.funch.ledger.repo.CardRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.Valid;

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

    /**
     * Entity에 Null과 공백 체크가 제대로 이루어지고 있는지 확인
     * 생각 : Blank와 Null일 경우 피드백
     * 결과 : Request 요청에 @Valid를 붙이면 작동한다.
     */
    @Test
    public void DTO_NotNull_Test() throws EntityException {
        String company = "";
        String name = "";
        CardDto cardDto = new CardDto();
        cardDto.setCompany(company);
        cardDto.setName(name);
        Card blankCard = cardRepository.save(cardDto.toEntity());
        Card result = cardRepository.save(blankCard);

        Assert.assertEquals(result, null);
    }

}
