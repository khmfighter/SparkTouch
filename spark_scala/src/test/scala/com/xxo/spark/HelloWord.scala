package com.xxo.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.SparkContext._


/**
  *
  * Created by TangXD on 2016/11/18.
  */
object HelloWord {

  def main(args: Array[String]) {
    val conf: SparkConf = new SparkConf()
    conf.setAppName("Hello Word")
    conf.setMaster("local")

    val sc: SparkContext = new SparkContext(conf)

    val file = sc.textFile("spark_scala/src/main/resources/hivedata.txt")
    val map = file.flatMap(line=>line.split("^A"))
    val wordNumber = map.map( w => w+1 )


    val data = file.flatMap(_.split(" ")).map((_,1)).reduceByKey(_ + _)

    data.foreach( println )
  }
}
