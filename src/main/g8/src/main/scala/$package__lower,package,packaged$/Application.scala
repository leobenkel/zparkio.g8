package $package;format="space,package"$

import $package;format="space,package"$.Application.RuntimeEnv
import $package;format="space,package"$.services._
import com.leobenkel.zparkio.Services._
import com.leobenkel.zparkio.ZparkioApp
import izumi.reflect.Tag
import zio.{Has, UIO, ZIO, ZLayer}

trait Application extends ZparkioApp[Arguments, RuntimeEnv, String] {
  implicit lazy final override val tagC:   Tag[Arguments] = Tag.tagFromTagMacro
  implicit lazy final override val tagEnv: Tag[RuntimeEnv] = Tag.tagFromTagMacro

  lazy final override protected val env: ZLayer[ZPARKIO_ENV, Throwable, RuntimeEnv] =
    ZLayer.succeed(())

  lazy final override protected val sparkFactory:  FACTORY_SPARK = SparkBuilder
  lazy final override protected val loggerFactory: FACTORY_LOG = Logger.Factory(Log)
  override protected def makeCli(args: List[String]): Arguments = Arguments(args)

  override def runApp(): ZIO[COMPLETE_ENV, Throwable, String] = ???
}

object Application {
  type RuntimeEnv = Has[Unit]
}
