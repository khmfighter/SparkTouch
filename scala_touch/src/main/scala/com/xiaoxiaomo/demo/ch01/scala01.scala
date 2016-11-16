package com.xiaoxiaomo.demo.ch01

import scala.math
import scala.util.Random

/**
 * 第一章
 * Created by Jason on 2016/3/23.
 */
object scala01 {


    //1. 变量的声明
    var a = 17 ;
    var b:Int = 18 ;
    var c:String = "this is string" ;
    var a1 = 17 ;

    //2. 常量的声明
    val d = 17 ;
    val e:Double = 20.00 ;
    val f:Float = 21f ;
    val d1 = 17 ;

    //3. 调用函数和方法
    def main(args: Array[String]) {
        val s = Math.min(89,23) ;   //java中的方法
        val a = Math.abs(-134) ;


        //scala中的min函数
        //其实还是调用的java的Math.min
        //def min(x: Int, y: Int): Int = java.lang.Math.min(x, y)
        val s1 = math.min(89,23) ;


        println(s+" "+a) ;
        println(s1) ;
        println("s == s1:"+s == s1)            //true：常量

        println("a==a1："+a==a1)              //false
        println("d==d1："+d==d1)              //true：常量
        println("a==d："+a==d)               //false


        //apply 方法
        //从0开始
        println(c(2));  /*==等等于 */       println(c.apply(2))

        //伴生对象
        println(BigInt("1289048203"));  /*==等等于 */ println( BigInt.apply("1289048203"))


        val rand = math.BigInt.probablePrime(100 , Random)

        println(rand)

    }





}
