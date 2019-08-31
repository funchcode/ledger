package com.funch.ledger.controller;

import com.funch.ledger.domain.common.Search;
import com.funch.ledger.spec.LedgerSpecs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {

    @GetMapping("/search/test")
    public ResponseEntity<String> search(@RequestBody Search entity) {
        log.debug(entity.toString());

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
