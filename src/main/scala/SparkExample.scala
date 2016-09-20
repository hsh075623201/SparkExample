import java.text.SimpleDateFormat
import java.util.{Calendar, Date, Properties}

import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkConf, SparkContext}
//import org.apache.spark.sql.SparkSession

/**
  * Created by huang on 2016/9/19.
  */

object SparkExample {



  def main(args: Array[String]) {

    val conf = new SparkConf().setAppName("Test by Hank")
    val sc = new SparkContext(conf)

    //获取数据库数据
    val dataList = getDBDataSource(sc)


    sc.stop()


  }


  def getDBDataSource(sc:SparkContext) ={
    val prop = new Properties()
    prop.put("driver",driver)
    prop.put("user",user)
    prop.put("password",password)
    val  dateFormat = new SimpleDateFormat("yyyy-MM-dd")
    val cal = Calendar.getInstance()
    cal.add(Calendar.DATE,-30) //30天
    val date = dateFormat.format(cal.getTime())
    println(date)
    val predicates = Array(s"""date>'$date'""")
    val sqlContext = new SQLContext(sc)
    val data = sqlContext.read.jdbc(url,"fact_ahs_recommend_user_visit_product",predicates,prop)
    println(data.first())
    println(data.count())
    data.collect()
  }

  def getHiveDataSource(sc:SparkContext) ={
    val hiveContext = new HiveContext(sc)
    hiveContext.setConf("hive.execution.engine","spark")
    val dataFrame = hiveContext.sql("show databases")
    val data = dataFrame.collect()
    data
  }
}
