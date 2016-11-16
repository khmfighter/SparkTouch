package com.xxo.spark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.StreamingContext._
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * StreamingByWindow1
 * Created by xiaoxiaomo on 2016/6/17.
 */
object StreamingByWindow1 {

  def main(args: Array[String]) {

    //1. 实例化配置文件
    val conf: SparkConf = new SparkConf()

    //2. steaming上下文
    val ssc: StreamingContext = new StreamingContext(conf , Seconds(args(0).toInt))


    //设置检查点目录  带状态的操作必须要做检查点  特别是window操作
    ssc.checkpoint("/tmp/15826008619/spark")


    //3. 接收数据
    //参数一：发送socket消息的主机  参数二：发送socket消息的端口  参数三：存储级别
    val data: ReceiverInputDStream[String] = ssc.socketTextStream(args(1),args(2).toInt,StorageLevel.MEMORY_AND_DISK)

    //4. 业务逻辑
    //参数一计算函数  ，参数二数据时间间隔 ， 参数三处理时间间隔
    val rs: DStream[(String, Int)] = data.map((_,1)).reduceByKeyAndWindow( (a:Int,b:Int) => a+b,Seconds(args(3).toInt), Seconds(args(4).toInt) )

    ////状态累加统计
    rs.updateStateByKey(updateFunc)

    //打印结果集
    rs.print


    //5. 启动任务  需要使用上下文对象启动
    ssc.start
    //等待任务完成
    ssc.awaitTermination

  }

  //Seq[Int]一种scala集合，可以存储重复数据，可以快速插入和删除数据数据（有序）
  //Option[Int]也是一种集合，如果有值，返回Some[A]，如果没值，返回NONE
  def updateFunc  = ( value:Seq[Int] , status:Option[Int] ) => {
    //累加当前状态
    val data = value.foldLeft(0)(_ + _)
    //取出过去状态   括号中是初始值  第一次为0
    val last = status.getOrElse(0)
    //返回
    Some(data + last)
  }


  //spark-submit --class com.xxo.com.xxo.spark.streaming.StreamingByWindow1 original-SparkStudy_20160616-1.0-SNAPSHOT.jar 5 192.168.33.88 2017 20 10
}
