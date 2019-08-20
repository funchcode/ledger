package com.funch.ledger.controller;

import com.funch.ledger.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class CardRestControllerTests {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private CardService cardService;

    /**
     * REST Controller 테스트
     * "GET "/search/{word}"
     */
    @Test
    public void Search_REST_Tests() throws Exception {
        String word = "삼성";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/search/"+"1"))
                .andExpect(jsonPath("$.content").doesNotExist())
                .andDo(print()).andExpect(status().isOk()).andReturn();
    }

}
