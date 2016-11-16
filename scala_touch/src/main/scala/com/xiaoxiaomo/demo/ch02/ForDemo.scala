package com.xiaoxiaomo.demo.ch02

/**
 * Created by Jason on 2016/3/22.
 */
object ForDemo {

  def main(args: Array[String]) {
    var file  = null;



    //for循环遍历

    val str = "Hello" ;

    for (i <- str){
      println( i )
    }

    for ( i <- 0 until str.length ){
      println( str(i) )
    }






  }




}
