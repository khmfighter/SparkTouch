package com.xiaoxiaomo.demo.ch00

/**
 * Created by xiaoxiaomo on 2016/6/14.
 */
object ArrayStudy {

  def main(args: Array[String]) {
    var array: Array[String] = new Array[String](3)
    for ( i <- array ){ //看一下默认值
      print(i + " ")
    }

    println()

    array(0) = "发射点"
    array(1)= "abc"
    array(2)="c"
    for ( i <- array ){//赋值后遍历
      print(i + " ")
    }

    println()
    array(2) = "cc"
    println (array(2)) //修改值

    var a = Array( 1,2,3,4,6456,642 )
    for ( i <- 0 until( a.length ) ){
      print(" "+ a(i) )
    }
    println()
    println( a.sum )
    println( a.max )
    println( a.min )

    println( a.mkString( " " ) )
    println( a.mkString( ", " ) )
    println( a.mkString( "<",",", ">" ) )

//    val arr1 = Array(9,"a",4,"fsdf",43)
    val arr1 = Array(9,34,4,90,43)
    val arr2 = arr1.sorted
    println( arr2.mkString(",") )

  }

}
