name := "learn-scala-sbt"

version := "1.0"

scalaVersion := "2.11.10"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.1.0",
  "org.apache.spark" %% "spark-mllib" % "2.1.0",
  "org.apache.hbase" % "hbase-common" % "1.3.1",
  "org.apache.hbase" % "hbase-client" % "1.3.1",
  "org.apache.hbase" % "hbase-server" % "1.3.1",
  "org.anarres.lzo" % "lzo-hadoop" % "1.0.5",
  "org.slf4j" % "slf4j-api" % "1.7.25",
  "org.slf4j" % "slf4j-log4j12" % "1.7.25",
  "log4j" % "log4j" % "1.2.17"
)

resolvers ++= Seq(
  Resolver.mavenLocal,
  "aliyun" at "http://maven.aliyun.com/nexus/content/groups/public/",
  "central" at "http://repo1.maven.org/maven2/"
)