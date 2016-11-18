package com.xxo.spark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.StreamingContext._
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * StreamingWCByWindow1
 * Created by xiaoxiaomo on 2016/6/17.
 */
object StreamingWCByWindow1 {

  def main(args: Array[String]) {

    val conf: SparkConf = new SparkConf()
    val ssc: StreamingContext = new StreamingContext(conf , Seconds(args(0).toInt))

    ssc.checkpoint("/tmp/test")

    //3. 接收数据
    //参数一：发送socket消息的主机  参数二：发送socket消息的端口  参数三：存储级别
    val data: ReceiverInputDStream[String] = ssc.socketTextStream(args(1),args(2).toInt,StorageLevel.MEMORY_AND_DISK)

    //4. 业务逻辑
    //参数一计算函数  ，参数二数据时间间隔 ， 参数三处理时间间隔
    val rs: DStream[(String, Int)] = data.map((_,1)).reduceByKeyAndWindow( (a:Int,b:Int) => a+b,Seconds(args(3).toInt), Seconds(args(4).toInt) )

    rs.updateStateByKey(StreamingStatusOpera.wordCountFunc)
    rs.print

    ssc.start
    ssc.awaitTermination

  }

}
