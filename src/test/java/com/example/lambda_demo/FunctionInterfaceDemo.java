package com.example.lambda_demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 参考菜鸟教程
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class FunctionInterfaceDemo {
    @Test
    public void functionInterfaceDemo(){
        List<Integer> list= Arrays.asList(1,2,3,4,5,6,7,8,9);

        //Predicate<Integer> predicate=n->true
        //n是一个参数，传递到Predicate接口的test方法
        //n如果存在，则test方法返回true
        System.out.println("输出所有数据：");
        eval(list,n->true);

        /**
         * Predicate<Integer>  predicate=n->n%2==0
         * n是一个参数，传递到Predicate接口的test方法
         * 如果n%2为0，test方法则返回true
         */
        System.out.println("输出所有偶数：");
        eval(list,n -> n%2==0);

        /**
         * Predicate<Integer> predicate=n->n>3
         * n是一个参数，传递到Predicate接口的test方法
         * 如果n大于3，则test方法返回true
         */
        System.out.println("输出大于3的所有数字：");
        eval(list,n->n>3);
    }
    public static void eval(List<Integer> list, Predicate<Integer> predicate){
        for (Integer n:list){
            if(predicate.test(n)){
                System.out.println(n+" ");
            }
        }
    }
}
