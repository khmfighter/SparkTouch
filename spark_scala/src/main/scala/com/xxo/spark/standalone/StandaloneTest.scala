package com.xxo.spark.standalone

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._

/**
 * standalone任务提交
 */
object StandaloneTest {
  def main(args: Array[String]) {
    
    if(args.length != 4){
      println("Usage: <MasterURL> <SparkHome> <inputpath> <outputPath>")
      System.exit(1)
    }
	  //构造配置对象
    val conf = new SparkConf
    conf.setAppName("standalone demo") //job名称
    conf.setMaster(args(0))  //Spark URL
    conf.setSparkHome(args(1)) //spark安装目录
    conf.set("spark.executor.memory", "2g")  //配置自定义参数
    
    //构造spark上下文对象
    val sc = new SparkContext(conf)
    
    //加载数据
    val file = sc.textFile(args(2))
    
    //计算逻辑
    val data = file.flatMap(_.split(" ")).map((_,1)).reduceByKey(_ + _)
    
    data.cache
    
    //保存数据
    data.repartition(1).saveAsTextFile(args(3))
    
  }
}