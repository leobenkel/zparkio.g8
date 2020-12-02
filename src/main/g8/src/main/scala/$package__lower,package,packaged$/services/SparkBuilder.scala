package $package;format="space,package"$.services

import com.leobenkel.zparkio.Services.SparkModule

object SparkBuilder extends SparkModule.Factory[Arguments] {
  lazy final override protected val appName: String = "$name$"
}
