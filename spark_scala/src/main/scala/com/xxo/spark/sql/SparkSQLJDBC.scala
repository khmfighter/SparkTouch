package com.xxo.spark.sql

import java.sql.DriverManager

/**
 *
 * Spark SQL
 * 读取hive
 * Created by xiaoxiaomo on 2016/6/18.
 */
object SparkSQLJDBC {
  def main(args: Array[String]) {
    Class.forName("org.apache.hive.jdbc.HiveDriver")
    val conn = DriverManager.getConnection("jdbc:hive2://192.168.33.88:10000/default","","")
    val pstatm = conn.prepareStatement("select * from test")
    val rs = pstatm.executeQuery()
    while(rs.next()){
      println(rs.getString(1))
    }
    rs.close
    pstatm.close()
    conn.close
  }
}