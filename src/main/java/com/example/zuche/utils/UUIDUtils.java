package com.example.zuche.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class UUIDUtils {


    /**
     * 去掉-的uuid  32 位长度
     *
     * @return
     */
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();

        String randomString = uuid.toString().replace("-", "");

        return randomString;


    }


}
