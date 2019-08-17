package com.funch.ledger.lang;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RunWith(SpringRunner.class)
public class JavaLangTests {

    private Logger logger = LoggerFactory.getLogger(JavaLangTests.class);

    @Test
    public void java_Time_Test() {
        LocalDateTime now = LocalDateTime.now();
        logger.info(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

}
