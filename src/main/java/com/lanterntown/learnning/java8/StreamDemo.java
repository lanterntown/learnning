package com.lanterntown.learnning.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author Wangst
 * @Date 2021/6/22 13:39
 */
public class StreamDemo {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        System.out.println(filtered);
        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        // 获取对应的平方数
        List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
        System.out.println(squaresList);
        random.ints().limit(10).sorted().forEach(System.out::println);
    }
}
