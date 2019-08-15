package com.funch.ledger.repo;

import com.funch.ledger.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Integer, Card> {

}
