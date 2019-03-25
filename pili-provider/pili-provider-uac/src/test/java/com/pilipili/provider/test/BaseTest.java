package com.pilipili.provider.test;

import com.pilipili.provider.PiliProviderUacApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;


/**
 * 描述： test case
 *
 * @author ChenJianChuan
 * @date 2019/3/6　10:40
 */
//@SpringBootTest(classes = PiliProviderUacApplication.class)
//@RunWith(SpringRunner.class)
public class BaseTest {

    @Test
    public void doTest() {
//        new Random().nextInt();
        for(int a=0;a<20;a++) {
            System.out.println(new Random().nextInt(10));
        }
    }
}
