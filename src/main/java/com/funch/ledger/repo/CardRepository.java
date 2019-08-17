package com.funch.ledger.repo;

import com.funch.ledger.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer>, JpaSpecificationExecutor<Card> {

    // Junit 테스트 용
    Card findTopByOrderByCardPkDesc();

    Card findOneByCardPk(int cardPk);

    @Query("SELECT c FROM Card c WHERE c.info = :info ORDER BY c.cardPk DESC")
    List<Card> findByInfoOrderByCardPkDesc(@Param("info") char info);

    List<Card> findAllByOrderByCardPkDesc();

}
