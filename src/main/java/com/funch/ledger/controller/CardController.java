package com.funch.ledger.controller;

import com.funch.ledger.domain.Card;
import com.funch.ledger.dto.CardDto;
import com.funch.ledger.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController("/card")
public class CardController {

    @Autowired
    private CardService cardService;

    // 저장 API
    @PostMapping("/put")
    public ResponseEntity<String> save(@RequestBody CardDto cardDto) {
        return null;
    }

    // 업데이트 API
    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody CardDto cardDto) {
        return null;
    }

    // 조회 API
    @GetMapping("/get/{info}")
    public List<CardDto> getByInfo(@PathVariable("info") char info) {
        return null;
    }

    // 검색 API
    @GetMapping("/get/{word}")
    public List<Card> search(@PathVariable("word") String word) {
        return null;
    }
}
