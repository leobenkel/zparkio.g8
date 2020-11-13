package $package;format="space,package"$.services

import com.leobenkel.zparkio.Services.CommandLineArguments
import com.leobenkel.zparkio.Services.CommandLineArguments.CommandLineArguments
import org.rogach.scallop.ScallopConf
import zio.ZIO

case class Arguments(input: List[String])
    extends ScallopConf(input) with CommandLineArguments.Service {


}

object Arguments {
  def apply[A](f: Arguments => A): ZIO[CommandLineArguments[Arguments], Throwable, A] = {
    CommandLineArguments.get[Arguments].apply(f)
  }
}
