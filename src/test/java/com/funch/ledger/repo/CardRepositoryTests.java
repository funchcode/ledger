package com.funch.ledger.repo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CardRepository.class})
public class CardRepositoryTests {

    @Autowired
    private CardRepository cardRepository;

    @Before
    public void before() {

    }

    /**
     * use, alarm, created, updated 기본 값 들어가는지 확인
     */
    @Test
    public void Entity_Default_Value_Test() {

    }

    @After
    public void after() {

    }

}
