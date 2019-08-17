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

    @Transactional
    public Card save(CardDto cardDto) {
        return cardRepository.save(cardDto.toEntity());
    }

    @Transactional
    public Card update(CardDto cardDto) {
        Card origin = cardRepository.findOneByCardPk(cardDto.getCardPk());
        return cardRepository.save(cardDto.toChangeEntity(origin));
    }

    @Transactional
    public List<Card> getCards(char info) {
        if(info == '\u0000') {
            return cardRepository.findAllByOrderByCardPkDesc();
        }
        return cardRepository.findByInfoOrderByCardPkDesc(info);
    }

}
