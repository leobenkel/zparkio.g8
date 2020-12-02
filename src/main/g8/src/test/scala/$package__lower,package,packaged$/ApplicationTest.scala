package $package;format="space,package"$

import com.leobenkel.zparkiotest.TestWithSpark
import org.apache.spark.sql.SparkSession
import org.scalatest.freespec.AnyFreeSpec
import zio.Exit.{Failure, Success}
import zio.{BootstrapRuntime, ZIO}

class ApplicationTest extends AnyFreeSpec with TestWithSpark {
  "Full application" - {
    "Wrong argument" in {
      val testApp = TestApp(spark)
      testApp.makeRuntime.unsafeRunSync(testApp.runTest("--bar" :: "foo" :: Nil)) match {
        case Success(value) =>
          println(s"Read: \$value")
          assertResult(0)(value)
        case Failure(cause) => fail(cause.prettyPrint)
      }
    }

    "Help" in {
      val testApp = TestApp(spark)
      testApp.makeRuntime.unsafeRunSync(testApp.runTest("--help" :: Nil)) match {
        case Success(value) =>
          println(s"Read: \$value")
          assertResult(0)(value)
        case Failure(cause) => fail(cause.prettyPrint)
      }
    }
  }
}

case class TestApp(s: SparkSession) extends Application {
  def runTest(args: List[String]): ZIO[zio.ZEnv, Throwable, Int] = {
    super.run(args)
  }

  override protected def sparkFactory: FACTORY_SPARK =
    new FACTORY_SPARK {
      lazy final override protected val appName: String = "$name$ Test"

      final override protected def createSparkSession(
        sparkBuilder: SparkSession.Builder
      ): SparkSession = s
    }

  lazy final override val makeRuntime: BootstrapRuntime = super.makeRuntime
}
