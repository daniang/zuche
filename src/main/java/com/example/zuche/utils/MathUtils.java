package com.example.zuche.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * @desc :  math的一些常用方法
 * @Author : chengzhang
 * @Date : 2022/6/9 9:33
 */
@Slf4j
public class MathUtils {

    public static void main(String[] args) {

        //Math.sqrt 计算平方根
        log.info(" 计算平方根 Math.sqrt(16)------{}", Math.sqrt(16));

        //Math.cbrt(8)计算立方根
        log.info("计算立方根Math.cbrt(8) = {}", Math.cbrt(8));
        //计算(x的平方+y的平方)的平方根Math.hypot(3,4)
        log.info("计算(x的平方+y的平方)的平方根Math.hypot(3,4) = {} ", Math.hypot(3, 4));
        //计算a的b次方Math.pow(3, 2)
        log.info(" 计算a的b次方Math.pow(3, 2) = {}", Math.pow(3, 2));
        //计算e^x 的值 Math.exp(3)
        log.info(" 计算e^x 的值 Math.exp(3) = {}", Math.exp(3));

        //计算最大值 Math.max(7, 15)
        log.info("计算最大值 Math.max(7, 15)={}", Math.max(7, 15.1));

        //计算最小值 Math.min(2.3,4.5) = {}
        log.info("计算最小值 Math.min(2.3,4.5) = {}", Math.min(2.3, 4.5));

        //Math.abs 求绝对值
        log.info("Math.abs(-10.4) = {}", Math.abs(-10.4));
        log.info("Math.abs(10.1) = {}", Math.abs(10.1));

        //Math.ceil天花板的意思,就是返回大的值
        log.info("Math.ceil(-10.1) = {}", Math.ceil(-10.1));
        log.info("Math.ceil(10.7) = {}", Math.ceil(10.7));
        log.info("Math.ceil(-0.7) ={}", Math.ceil(-0.7));
        log.info("Math.ceil(0.0) = {} ", Math.ceil(0.0));
        log.info("Math.ceil(-0.0) = {}", Math.ceil(-0.0));
        log.info("Math.ceil(-1.7) = {}", Math.ceil(-1.7));

        //Math.floor地板的意思,就是返回最小的值
        log.info("Math.floor(-10.1) = {}", Math.floor(-10.1));
        log.info("Math.floor(10.7) = {}", Math.floor(10.7));
        log.info("Math.floor(-0.7) = {}", Math.floor(-0.7));
        log.info("Math.floor(0.0) = {}", Math.floor(0.0));
        log.info("Math.floor(-0.0) = {}", Math.floor(-0.0));

        //Math.random 取得一个大于或者等于0.0小于不等于1.0的随机数[0,1)
        log.info("Math.random() = {}", Math.random()); //输出[0,1)间的随机数
        log.info("Math.random() *100 = {}", Math.random() * 100);

        //Math.rint 四舍五入 返回double 值
        log.info("Math.rint(10.1) ={}", Math.rint(10.1));
        log.info("Math.rint(10.7) = {}", Math.rint(10.7));
        log.info("Math.rint(-10.5) = {}", Math.rint(-10.5));
        log.info("Math.rint(-10.51) = {}", Math.rint(-10.51));
        log.info("Math.rint(-10.2) = {}", Math.rint(-10.2));

        //Math.round 四舍五入 float 时返回int值,double时返回long值
        log.info("Math.round(10.1) = {}", Math.round(10.1));
        log.info("Math.round(10.7) = {}", Math.round(10.7));
        log.info("Math.round(-10.5) = {}", Math.round(-10.5));
        log.info("Math.round(-10.51) = {}", Math.round(-10.51));
        log.info("Math.rount(9) = {}", Math.round(9));

        //Math.nextUp(a) 返回比a大一点点的浮点数
        log.info("Math.nextUp(1.2) = {}", Math.nextUp(1.2));

        //Math.nextDown(a) 返回比a小一点点的浮点数
        log.info("Math.nextDown(1.2) = {}", Math.nextDown(1.2));

        //Math.nextAfter(a,b) 返回(a,b) 或(b,a)间与a相邻的浮点数 b可以比a小
        log.info("Math.nextAfter(1.2,2.7) = {}", Math.nextAfter(1.2, 0));
        log.info("Math.nextAfter(1.2,-1) = {}", Math.nextAfter(1.2, -1));


    }


}
