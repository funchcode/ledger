package com.funch.ledger.controller;

import com.funch.ledger.domain.Card;
import com.funch.ledger.dto.CardDto;
import com.funch.ledger.service.CardService;
import com.funch.ledger.util.NullUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
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
        Card card = cardService.save(cardDto);
        if (!NullUtils.isNullOrEmpty(cardDto)) {
            log.info("새로 저장된 카드 정보 >> "+card.toString());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // 업데이트 API
    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody CardDto cardDto) {
        Card card = cardService.update(cardDto);
        if (!NullUtils.isNullOrEmpty(cardDto)) {
            log.info("수정된 카드 정보 >> "+card.toString());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
    @GetMapping(value = "/search/{word}", produces = "application/json;charset=utf-8")
    public ResponseEntity<List<Card>> search(@PathVariable("word") String word) {
        if ("%".equals(word) || NullUtils.isEmptyOfString(word)) {
            log.warn("WORD 파라미터 정보가 옳지 않습니다.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(cardService.searchAll(word), HttpStatus.OK);
    }
}
