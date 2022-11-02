package $package;format="space,package"$.services

import com.leobenkel.zparkio.Services.Logger
import zio.Task
import zio.Console

case class Log(console: Console) extends Logger.Service {
  override def info(txt: => String): Task[Unit] =
    console.printLine(s"[INFO] \$txt")

  override def error(txt: => String): Task[Unit] =
    console.printLineError(s"[ERROR] \$txt")

  override def debug(txt: => String): Task[Unit] =
    console.printLine(s"[DEBUG] \$txt")
}
