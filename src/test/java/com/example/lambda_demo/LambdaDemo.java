package com.example.lambda_demo;

import com.example.lambda_demo.pojo.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toSet;

/**
 * 参考https://www.cnblogs.com/franson-2016/p/5593080.html
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LambdaDemo {

    @Test
    public void lambdaDemo1(){
        String[] atp={"Rafael Nadal","Novak Djokovic","Stanislas Wawrinka","David Ferrer","Roger Federer","Andy Murray","Tomas Berdych","Juan Martin Del Potro"};
        List<String> players= Arrays.asList(atp);

        //以前的循环方式
        for (String player:players){
            System.out.println(player+"; ");
        }
        System.out.println("------------------------");
        //使用lambda表达式以及函数操作
        players.forEach((player) -> System.out.println(player+"; "));
        System.out.println("--------------------------");
        //在java8中使用双冒号操作符(方法引用)
        players.forEach(System.out::println);
    }

    @Test
    public void lambdaDemo2(){
        //使用匿名内部类
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world11!");
            }
        }).start();
        //使用lambda expression
        new Thread(() -> System.out.println("Hello world12!")).start();

        //使用匿名内部类
        Runnable race1=new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world21!");
            }
        };
        race1.run();
        //使用lambda expression
        Runnable race2=() -> System.out.println("Hello world22!");
        race2.run();
    }

    @Test
    public void lambdaDemo3(){
        String[] players={"Rafael Nadal","Novak Djokovic","Stanislas Wawrinka","David Ferrer","Roger Federer","Andy Murray","Tomas Berdych","Juan Martin Del Potro"};
        //使用匿名内部类根据name排序players
        Arrays.sort(players, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1.compareTo(o2));
            }
        });
        for(String player:players){
            System.out.println(player+"; ");
        }
        System.out.println("------------------------------");
        //使用lambda expression排序players
        Comparator<String> sortByName=(String o1,String o2) -> (o1.compareTo(o2));
        Arrays.sort(players,sortByName);
        List<String> playersList=Arrays.asList(players);
        playersList.forEach((player) -> System.out.println(player+"; "));
        System.out.println("---------------------------------");
        //也可以采用以下方式
        Arrays.sort(players,(String o1,String o2) -> (o1.compareTo(o2)));
        List<String> playersList1=Arrays.asList(players);
        playersList1.forEach((player) -> System.out.println(player+"; "));
    }

    @Test
    public void lambdaStreamDemo(){
        Person person1=new Person("Elsdon", "Jaycob", "Java programmer", "male", 2000, 43);
        Person person2=new Person("Tamsen", "Brittany", "Java programmer", "female", 1500,23 );
        Person person3=new Person("Floyd", "Donny", "Java programmer", "male", 1800, 23);
        Person person4=new Person("Sindy", "Jonie", "Java programmer", "female", 1600, 32);
        Person person5=new Person("Vere", "Hervey", "Java programmer", "male", 1200, 22);
        Person person6=new Person("Maude", "Jaimie", "Java programmer", "female", 1900, 27);
        Person person7=new Person("Shawn", "Randall", "Java programmer", "male", 2300, 30);
        Person person8=new Person("Jayden", "Corrina", "Java programmer", "female", 1700, 35);
        Person person9=new Person("Palmer", "Dene", "Java programmer", "male", 2000, 33);
        Person person10=new Person("Addison", "Pam", "Java programmer", "female", 1300, 34);
        List<Person> javaProgrammers=new ArrayList<>();
        javaProgrammers.add(person1);
        javaProgrammers.add(person2);
        javaProgrammers.add(person3);
        javaProgrammers.add(person4);
        javaProgrammers.add(person5);
        javaProgrammers.add(person6);
        javaProgrammers.add(person7);
        javaProgrammers.add(person8);
        javaProgrammers.add(person9);
        javaProgrammers.add(person10);
        List<Person> phpProgrammers=new ArrayList<Person>(){
            {
                add(new Person("Jarrod", "Pace", "PHP programmer", "male",  1550,34));
                add(new Person("Clarette", "Cicely", "PHP programmer", "female", 1200,23));
                add(new Person("Victor", "Channing", "PHP programmer", "male",  1600,32));
                add(new Person("Tori", "Sheryl", "PHP programmer", "female", 1000,21));
                add(new Person("Osborne", "Shad", "PHP programmer", "male", 1100,32));
                add(new Person("Rosalind", "Layla", "PHP programmer", "female", 1300,25));
                add(new Person("Fraser", "Hewie", "PHP programmer", "male", 1100,36));
                add(new Person("Quinn", "Tamara", "PHP programmer", "female", 1000,21));
                add(new Person("Alvin", "Lance", "PHP programmer", "male", 1600,38));
                add(new Person("Evonne", "Shari", "PHP programmer", "female", 1800,40));
            }
        };
        //lambda expression输出上述列表
        System.out.println("所有程序员的姓名：");
        javaProgrammers.forEach((p) -> System.out.printf("%s %s;",p.getFirstName(),p.getLastName()));
        phpProgrammers.forEach((p) -> System.out.printf("%s %s",p.getFirstName(),p.getLastName()));

        //增加工资5%
        System.out.println("给程序员加薪5%:");
        Consumer<Person> giveRaise=e -> e.setSalary(e.getSalary()/100*5+e.getSalary());
        javaProgrammers.forEach(giveRaise);
        javaProgrammers.forEach((p) -> System.out.println(p.getSalary()));
        System.out.println("----------------------------------");
        phpProgrammers.forEach(giveRaise);
        phpProgrammers.forEach((p) -> System.out.println(p.getSalary()));

        System.out.println("下面是月薪超过$1400的PHP程序员");
        phpProgrammers.stream()
                .filter((p) -> (p.getSalary()>1400))
                .forEach((p) -> System.out.printf("%s %s;",p.getFirstName(),p.getLastName()));
        //定义过滤器，然后重用他们来执行其他操作
        Predicate<Person> ageFilter=(p) -> (p.getAge()>25);
        Predicate<Person> salaryFilter=(p) -> (p.getSalary()>1400);
        Predicate<Person> genderFilter=(p) -> ("female".equals(p.getGender()));

        System.out.println("年龄大于25且月薪大于1400的女php程序员");
        phpProgrammers.stream()
                .filter(ageFilter)
                .filter(salaryFilter)
                .filter(genderFilter)
                .forEach((p)->System.out.printf("%s %s;",p.getFirstName(),p.getLastName()));

        System.out.printf("最前面的3个Java programmers");
        javaProgrammers.stream()
                .limit(3)
                .forEach((p)->System.out.printf("%s %s;",p.getFirstName(),p.getLastName()));

        System.out.println("根据name排序，并显示前5个Java programmers:");
        List<Person> sortedJavaProgrammers=javaProgrammers
                .stream()
                .sorted((p1,p2)->(p1.getFirstName().compareTo(p2.getFirstName())))
                .limit(5)
                .collect(Collectors.toList());
        sortedJavaProgrammers.forEach((p)->System.out.printf("%s %s;",p.getFirstName(),p.getLastName()));

        System.out.println("工资最低的 Java programmer:");
        Person pers = javaProgrammers
                .stream()
                .min((p1, p2) -> (p1.getSalary() - p2.getSalary()))
                .get();
        System.out.printf("Name: %s %s; Salary: $%,d.", pers.getFirstName(), pers.getLastName(), pers.getSalary());

        System.out.println("将 PHP programmers 的 first name 拼接成字符串:");
        String phpDevelopers = phpProgrammers
                .stream()
                .map(Person::getFirstName)
                .collect(joining(" ; ")); // 在进一步的操作中可以作为标记(token)

        System.out.println("将 Java programmers 的 first name 存放到 Set:");
        Set<String> javaDevFirstName = javaProgrammers
                .stream()
                .map(Person::getFirstName)
                .collect(toSet());

        System.out.println("将 Java programmers 的 first name 存放到 TreeSet:");
        TreeSet<String> javaDevLastName = javaProgrammers
                .stream()
                .map(Person::getLastName)
                .collect(toCollection(TreeSet::new));

        //streams还可以是并行的(parallel)
        System.out.println("计算付给 Java programmers 的所有money:");
        int totalSalary=javaProgrammers
                .parallelStream()
                .mapToInt(p->p.getSalary())
                .sum();
        System.out.println(totalSalary);

        //我们可以使用summaryStatistics方法获得stream 中元素的各种汇总数据。 接下来,我们可以访问这些方法,比如getMax, getMin, getSum或getAverage:
        List<Integer> numbers=Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        IntSummaryStatistics stats=numbers
                .stream()
                .mapToInt((x)->x)
                .summaryStatistics();
        System.out.println("List中最大的数字 : " + stats.getMax());
        System.out.println("List中最小的数字 : " + stats.getMin());
        System.out.println("所有数字的总和   : " + stats.getSum());
        System.out.println("所有数字的平均值 : " + stats.getAverage());
    }
}
