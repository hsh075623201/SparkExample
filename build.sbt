name := "SparkExample"

version := "1.0"

scalaVersion := "2.10.4"

libraryDependencies += "org.apache.spark" % "spark-core_2.10" % "1.6.0"

/*libraryDependencies += "org.apache.spark" % "spark-sql_2.10" % "1.1.0"*/

libraryDependencies += "org.apache.spark" % "spark-hive_2.10" % "1.6.0"

libraryDependencies ++= Seq(
  "mysql" % "mysql-connector-java" % "5.1.12",
  "org.apache.commons" % "commons-dbcp2" % "2.0.1"
)

/*libraryDependencies += "org.apache.spark" % "spark-network-common_2.10" % "2.0.0"*/

mergeStrategy in assembly <<= (mergeStrategy in assembly) { mergeStrategy => {
  case entry => {
    val strategy = mergeStrategy(entry)
    if (strategy == MergeStrategy.deduplicate) MergeStrategy.first
    else strategy
  }
}
}

