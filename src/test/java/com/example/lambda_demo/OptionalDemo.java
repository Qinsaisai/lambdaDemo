package com.example.lambda_demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

/**
 * 参考菜鸟教程
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OptionalDemo {
    @Test
    public void optionalDemo(){
        OptionalDemo optionalDemo=new OptionalDemo();
        Integer value1=null;
        Integer value2=new Integer(10);

        //Optional.ofNullable()允许传递为null参数
        Optional<Integer> a=Optional.ofNullable(value1);

        //Optional.of()如果传递的参数是null,抛出异常NullPointerException
        Optional<Integer> b=Optional.of(value2);
        System.out.println(optionalDemo.sum(a,b));
    }
    public Integer sum(Optional<Integer> a,Optional<Integer> b){

        //Optional.isPresent()判断值是否存在
        System.out.println("第一个参数值存在："+a.isPresent());
        System.out.println("第二个参数值存在："+b.isPresent());

        //Optional.orElse()如果值存在，返回它，否则返回默认值
        Integer value1=a.orElse(new Integer(0));

        //Optional.get()获取值，值需要存在
        Integer value2=b.get();
        return value1+value2;
    }
}
