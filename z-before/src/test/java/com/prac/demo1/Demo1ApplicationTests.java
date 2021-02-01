package com.prac.demo1;

import com.prac.demo1.task.TestAsync;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 功能描述: 
 *
 * @auther: liweizhi
 * @date: 2019/1/18 下午3:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class Demo1ApplicationTests {

    @Autowired
    private TestAsync testAsync;
    /**
     * 功能描述: 
     *
     * @param: 
     * @return: 
     * @auther: liweizhi
     * @date: 2019/1/18 下午3:02
     */
    @Test
    public void contextLoads() throws InterruptedException, ExecutionException, TimeoutException {

        System.out.println(testAsync.test01().get(10, TimeUnit.SECONDS));
        System.out.println("first ended");
        System.out.println(testAsync.test01().get(10, TimeUnit.SECONDS));
        System.out.println("second ended");
        System.out.println(testAsync.test02().get(10, TimeUnit.SECONDS));
        System.out.println("all ended");
    }

}

