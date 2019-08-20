package com.funch.ledger.service;

import com.funch.ledger.domain.Card;
import com.funch.ledger.dto.CardDto;
import com.funch.ledger.repo.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;
    private final char ALL = 'A';
    /**
     * 임무 : 저장
     * 설명 : DTO 객체를 Entity 객체로 만들어 Persistence API로 전달해서 최종적으로 요청 정보를 저장한다.
     * 규칙 : Default 값과 Null 값을 허용하지 않는 데이터를 감시해야한다.
     */
    @Transactional
    public Card save(CardDto cardDto) {
        Card newCard = cardDto.toEntity();
        if(newCard == null) {
            return null;
        }
        return cardRepository.save(newCard);
    }

    /**
     * 임무 : 변경
     * 설명 : 기존 데이터를 변경하는 것이기 때문에 들어온 데이터와 기존 데이터를 비교분석하여 기존 PK와 새로운 Data로 Entity를 만들어 저장한다.
     * 규칙1 : 숫자와 같은 경우에는 null 값이 고로 0 값이기 때문에 반드시 변경하지 않을 경우 기존 데이터를 가져와야한다.
     * 규칙2 : 문자열 타입 같은 경우에는 null 값이 그대로 들어오기 때문에 비교 분석하여 기존 것을 선택할지의 여부를 선택할 수 있다.
     */
    @Transactional
    public Card update(CardDto cardDto) {
        Card origin = cardRepository.findOneByCardPk(cardDto.getCardPk());
        return cardRepository.save(cardDto.toChangeEntity(origin));
    }

    /**
     * 임무 : 등록된 카드 정보 조회
     * 설명 : 등록된 카드 중 사용여부 혹은 전체 데이터를 조회할 수 있다.
     * 규칙 : 세가지 선택을 할 수 있다. null = 전체 데이터, Y = 사용 중인 데이터, N = 사용하지 않는 데이터
     */
    @Transactional
    public List<Card> getCards(char info) {
        if(info == ALL) {
            return cardRepository.findAllByOrderByCardPkDesc();
        }
        return cardRepository.findByInfoOrderByCardPkDesc(info);
    }

    /**
     * 임무 : 등록된 카드 정보를 검색 조회
     * 설명 : 특정 키워드를 통해 등록된 카드를 검색할 수 있다.
     * 규칙 : 검색어로 %의 값이 들어오는 것을 주의, 검색어의 타입을 Object로 변경하여 문자열, 숫자 데이터도 검색.
     */
    @Transactional
    public List<Card> searchAll(String searchWord) {
        Object value = searchWord;
        return cardRepository.findCardSearchWord(value);
    }
}
