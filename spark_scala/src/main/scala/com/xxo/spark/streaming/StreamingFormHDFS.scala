package com.xxo.spark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.StreamingContext._

/**
 * 以HDFS作为数据源
 * Created by xiaoxiaomo on 2016/6/17.
 */
object StreamingFormHDFS {

  def main(args: Array[String]) {

    //1. 实例化配置文件
    val conf: SparkConf = new SparkConf()

    //2. steaming上下文
    val ssc: StreamingContext = new StreamingContext(conf , Seconds(args(0).toInt))


    //3. 接收数据
    val data: DStream[String] = ssc.textFileStream( args(1) )

    //4. 业务逻辑
    val rs: DStream[(String, Int)] = data.flatMap( _.split(" ") ).map((_,1)).reduceByKey(_ + _)
    //打印结果集
    rs.print


    //5. 启动任务  需要使用上下文对象启动
    ssc.start
    //等待任务完成
    ssc.awaitTermination

  }

}
