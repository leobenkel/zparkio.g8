val projectName = IO.readLines(new File("PROJECT_NAME")).head
val v = IO.readLines(new File("VERSION")).head

lazy val rootSettings = Seq(
  organization := "$organization$",
  scalaVersion := "$scala_version$",
  version      := v
)

lazy val root = (project in file("."))
  .settings(
    name := projectName,
    rootSettings,
    libraryDependencies ++= Seq(
      // https://mvnrepository.com/artifact/org.apache.spark/spark-core
      "org.apache.spark" %% "spark-core" % "$spark_version$" % Provided,
      // https://mvnrepository.com/artifact/org.apache.spark/spark-sql
      "org.apache.spark" %% "spark-sql"    % "$spark_version$"      % Provided,
      // https://github.com/leobenkel/ZparkIO
      "com.leobenkel"    %% "zparkio"      % "$zparkio_version$",
      $if(zparkio_scallop_support.truthy)$
      "com.leobenkel"    %% "zparkio-config-scallop" % "$zparkio_version$",
      $endif$
      "com.leobenkel"    %% "zparkio-test" % "$zparkio_version$"    % Test,
      // https://www.scalatest.org/
      "org.scalatest"    %% "scalatest"    % "$scala_test_version$" % Test
    )
  )
