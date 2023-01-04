package $package;format="space,package"$

import com.leobenkel.zparkio.ZparkioApp.ZIOEnv
import com.leobenkel.zparkiotest.TestWithSpark
import org.apache.spark.sql.SparkSession
import org.scalatest.Assertions
import org.scalatest.freespec.AnyFreeSpec
import zio.{Exit, Runtime, Unsafe, ZIO}

class ApplicationTest extends AnyFreeSpec with TestWithSpark {
  "Full application" - {
    "Wrong argument" in
      Unsafe.unsafe { implicit unsafe =>
        val testApp = TestApp(spark)
        testApp.makeRuntime.unsafe.run(testApp.runTest("--bar" :: "foo" :: Nil)) match {
          case Exit.Success(value) =>
            println(s"Read: \$value")
            assertResult(0)(value)
          case Exit.Failure(cause) => Assertions.fail(cause.prettyPrint)
        }
      }

    "Help" in
      Unsafe.unsafe { implicit unsafe =>
        val testApp = TestApp(spark)
        testApp.makeRuntime.unsafe.run(testApp.runTest("--help" :: Nil)) match {
          case Exit.Success(value) =>
            println(s"Read: \$value")
            assertResult(0)(value)
          case Exit.Failure(cause) => Assertions.fail(cause.prettyPrint)
        }
      }
  }
}

case class TestApp(s: SparkSession) extends Application {
  def runTest(args: List[String]): ZIO[ZIOEnv, Throwable, Int] = super.run(args)

  override protected def sparkFactory: FACTORY_SPARK =
    new FACTORY_SPARK {
      lazy final override protected val appName: String = "$name$ Test"

      final override protected def createSparkSession(
        sparkBuilder: SparkSession.Builder
      ): SparkSession = s
    }

  lazy final override val makeRuntime: Runtime[ZIOEnv] = super.makeRuntime
}
