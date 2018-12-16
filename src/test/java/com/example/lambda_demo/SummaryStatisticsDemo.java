package com.example.lambda_demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;

/**
 * 参考菜鸟教程
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SummaryStatisticsDemo {
    @Test
    public void summaryStatisticsDemo(){
        List<Integer> numbers= Arrays.asList(3,2,2,3,7,3,5);
        IntSummaryStatistics stats=numbers.stream().mapToInt((x)->x).summaryStatistics();
        System.out.println("列表中最大的数："+stats.getMax());
        System.out.println("列表中最小的数："+stats.getMin());
        System.out.println("所有数之和："+stats.getSum());
        System.out.println("平均数："+stats.getAverage());
    }
}
