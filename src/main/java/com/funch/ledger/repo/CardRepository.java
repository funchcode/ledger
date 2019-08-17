package com.funch.ledger.repo;

import com.funch.ledger.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

    // Junit 테스트 용
    Card findTopByOrderByCardPkDesc();

}
