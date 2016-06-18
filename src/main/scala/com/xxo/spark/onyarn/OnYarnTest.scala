package com.xxo.spark.onyarn

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._

object OnYarnTest {
  def main(args: Array[String]) {
    
    if(args.length != 2){
      println("Usage: <inputpath> <outputPath>")
      System.exit(1)
    }
    
    //构造spark上下文对象
    val sc = new SparkContext
    
    //加载数据
    val file = sc.textFile(args(0))
    
    //计算逻辑
    val data = file.flatMap(_.split(" ")).map((_,1)).reduceByKey(_ + _)
    
    data.cache
    
    //保存数据
    data.repartition(1).saveAsTextFile(args(1))
    
  }
}