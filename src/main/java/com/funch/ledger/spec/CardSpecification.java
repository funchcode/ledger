package com.funch.ledger.spec;

import com.funch.ledger.domain.Card;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

final class CardSpecification  {

    private CardSpecification() {}

    static Specification<Card> searchCard() {
        return new Specification<Card>() {
            @Override
            public Predicate toPredicate(Root<Card> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                
                return null;
            }
        };
    }


}
