package com.funch.ledger.sample;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PerformanceTests {

    /**
     * Object 에서 Primitive Type과 Wrapper Type Boxing UnBoxing 성능이 궁금
     */
    @Test
    public void Boxing_Unboxing_Tests() {
        Object obj = 12345;

        long strTime = System.currentTimeMillis();



        long endTime = System.currentTimeMillis();
    }

}
