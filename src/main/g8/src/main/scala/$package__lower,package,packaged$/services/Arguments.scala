package $package;format="space,package"$.services

import com.leobenkel.zparkio.Services.CommandLineArguments
import com.leobenkel.zparkio.Services.CommandLineArguments.CommandLineArguments
$if(zparkio_scallop_support.truthy)$
import com.leobenkel.zparkio.config.scallop.CommandLineArgumentScallop
import org.rogach.scallop.ScallopConf
$endif$
import zio.{Task, ZIO}

case class Arguments(input: List[String])
$if(zparkio_scallop_support.truthy)$
    extends ScallopConf(input) with CommandLineArgumentScallop.Service[Arguments] {
$else$
    extends CommandLineArguments.Service[Arguments] {
    // check validity of arguments
    override def checkValidity(): ZIO[Any, Throwable, Arguments] = Task(this)

    // display arguments status
    override final lazy val commandsDebug: Seq[String] = Seq.empty
$endif$
}

object Arguments {
  def apply[A](f: Arguments => A): ZIO[CommandLineArguments[Arguments], Throwable, A] =
    CommandLineArguments.get[Arguments].apply(f)
}
