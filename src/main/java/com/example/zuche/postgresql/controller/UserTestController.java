package com.example.zuche.postgresql.controller;


import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author chengzhang
 * @since 2022-04-20
 */
@Controller
@RequestMapping("/postgresql/user-test")
public class UserTestController {


    public static void main(String[] args) {

        Stream<Double> limit = Stream.generate(Math::random).limit(10);
        Double reduce = limit.reduce(0.0, (x, y) -> x + y);
        System.out.println(reduce);


    }

    private static void update(Integer[] arr) {

        for (int i = 0; i < arr.length; i++) {
            arr[i]++;
        }

    }

    private static void add(Integer a) {

        a++;

        System.out.println("a1 = " + a);
    }


}

