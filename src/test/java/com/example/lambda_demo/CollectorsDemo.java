package com.example.lambda_demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 参考菜鸟教程
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CollectorsDemo {
    @Test
    public void collectorsDemo(){
        //Collectors可用于返回列表或字符串
        List<String> strings= Arrays.asList("abc","","bc","efg","abcd","","jkl");
        //返回列表
        List<String> filtered=strings.stream().filter(string->!string.isEmpty()).collect(Collectors.toList());
        System.out.println("筛选列表："+filtered);

        //返回字符串
        String mergedString=strings.stream().filter(string->!string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合并字符串："+mergedString);
    }
}
