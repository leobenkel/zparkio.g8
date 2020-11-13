package $package;format="space,package"$.services

import com.leobenkel.zparkio.Services.SparkModule
import org.apache.spark.sql.SparkSession

object SparkBuilder extends SparkModule.Factory[Arguments] {
  override protected def appName: String = "$name$"
}
