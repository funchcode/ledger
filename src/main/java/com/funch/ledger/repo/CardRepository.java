package com.funch.ledger.repo;

import com.funch.ledger.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

    // Junit 테스트 용
    Card findTopByOrderByCardPkDesc();

    Card findOneByCardPk(int cardPk);

    @Query("SELECT c FROM Card c WHERE c.info = :info ORDER BY c.cardPk DESC")
    List<Card> findByInfoOrderByCardPkDesc(@Param("info") char info);

    List<Card> findAllByOrderByCardPkDesc();

    @Query("SELECT c FROM Card c " +
            "WHERE c.company LIKE %:word% OR c.name LIKE %:word% OR c.memo LIKE %:word% " +
            "OR c.minimum LIKE %:word% OR c.bounds LIKE %:word% OR c.period LIKE %:word% ")
    List<Card> findCardSearchWord(@Param("word") Object word);

}
