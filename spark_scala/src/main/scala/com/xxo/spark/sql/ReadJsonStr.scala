package com.xxo.spark.sql

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.sql.{SQLContext, SchemaRDD}
import org.apache.spark.{SparkConf, SparkContext}

/**
 *
 * Spark SQL
 * 读取Json File
 * Created by xiaoxiaomo on 2016/6/18.
 */
object ReadJsonStr {

  def main(args: Array[String]) {
    //1. 加载配置文件
    val conf: SparkConf = new SparkConf()
    conf.setAppName( "Spark-SQL ReadJsonFile" )
    conf.setMaster("local")

    //2. 实例化上下文
    val sc: SparkContext = new SparkContext(conf)

    //2.1 实例化SQL上下文
    val hiveContext: HiveContext = new HiveContext( sc ) //推荐使用
    val sqlc: SQLContext = new SQLContext( sc )

    //3.读取数据源
    val list = List("""{"name":"zhangsan","age":18}""","""{"name":"lisi","age":25,"saraly":19000}""","""{"name":"wangwu","age":19,"saraly":16000}""")
    val strRdd: RDD[String] = sc.parallelize( list )

    val schemaRDD: SchemaRDD = hiveContext.jsonRDD(strRdd)

//    println( rDD )
    schemaRDD.printSchema() //表结构

    println( "================================================" )

    //表操作
    //1. 注册表
    schemaRDD.registerTempTable( "t_xiaoxiaomo" )

    //2. 创建sql
    val rs: SchemaRDD = hiveContext.sql("select name,saraly from t_xiaoxiaomo where saraly > 15000")
//    rs.foreach( println _ ) //遍历result数据

    //遍历
    rs.map( row => (row(1) , row(0)  ) ).foreach(println)

    //遍历
    val map: RDD[String] = rs.map( row => row.getString(0) )
    map.foreach( println )


  }
}
