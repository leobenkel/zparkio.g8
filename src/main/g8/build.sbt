val projectName = IO.readLines(new File("PROJECT_NAME")).head
val v = IO.readLines(new File("VERSION")).head

lazy val rootSettings = Seq(
  organization := "$organization$",
  scalaVersion := "$scala_version$",
  version      := v
)

lazy val sparkVersion = "$spark_version$"
lazy val zparkioVersion = {
  val raw = "$zparkio_version$"
  val zparkioVersion = raw.split("_")(1)
  s"\${sparkVersion}_\${zparkioVersion}"
}

lazy val root = (project in file("."))
  .settings(
    name := projectName,
    rootSettings,
    libraryDependencies ++= Seq(
      // https://mvnrepository.com/artifact/org.apache.spark/spark-core
      "org.apache.spark" %% "spark-core" % sparkVersion$if(spark_provided.truthy)$ % Provided $endif$,
      // https://mvnrepository.com/artifact/org.apache.spark/spark-sql
      "org.apache.spark" %% "spark-sql"  % sparkVersion$if(spark_provided.truthy)$ % Provided $endif$,
      // https://github.com/leobenkel/ZparkIO
      "com.leobenkel"    %% "zparkio"      % zparkioVersion,
      $if(zparkio_scallop_support.truthy)$
      "com.leobenkel"    %% "zparkio-config-scallop" % zparkioVersion,
      $endif$
      "com.leobenkel"    %% "zparkio-test" % zparkioVersion    % Test,
      // https://www.scalatest.org/
      "org.scalatest"    %% "scalatest"    % "$scala_test_version$" % Test
    )
  )
