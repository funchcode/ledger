package com.funch.ledger.controller;

import com.funch.ledger.domain.Card;
import com.funch.ledger.dto.CardDto;
import com.funch.ledger.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController("/card")
public class CardController {

    @Autowired
    private CardService cardService;
    private final char YES = 'Y';
    private final char NO = 'N';
    private final char ALL = 'A';

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
    public ResponseEntity<List<Card>> getByInfo(@PathVariable("info") char info) {
        if(info != ALL && info != YES && info != NO) {
            log.warn("INFO 파라미터 정보가 잘못들어왔습니다.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(cardService.getCards(info), HttpStatus.OK);
    }

    // 검색 API
    @GetMapping("/search/{word}")
    public ResponseEntity<List<Card>> search(@PathVariable("word") String word) {
        if ("%".equals(word)) {
            log.warn("WORD 파라미터 정보가 옳지 않습니다.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(cardService.searchAll(word), HttpStatus.OK);
    }
}
