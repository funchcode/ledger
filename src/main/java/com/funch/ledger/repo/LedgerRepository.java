package com.funch.ledger.repo;

import com.funch.ledger.domain.Ledger;
import com.funch.ledger.spec.LedgerSpecs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LedgerRepository extends JpaRepository<Ledger, Integer>, JpaSpecificationExecutor<Ledger> {

    Ledger findOneBySeqPk(int seqPk);


}
