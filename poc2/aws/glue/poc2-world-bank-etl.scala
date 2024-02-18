import com.amazonaws.services.glue.GlueContext
import com.amazonaws.services.glue.MappingSpec
import com.amazonaws.services.glue.errors.CallSite
import com.amazonaws.services.glue.util.GlueArgParser
import com.amazonaws.services.glue.util.Job
import com.amazonaws.services.glue.util.JsonOptions
import org.apache.spark.SparkContext
import scala.collection.JavaConverters._

object GlueApp {
  def main(sysArgs: Array[String]) {
    val spark: SparkContext = new SparkContext()
    val glueContext: GlueContext = new GlueContext(spark)
    // @params: [JOB_NAME]
    val args = GlueArgParser.getResolvedOptions(sysArgs, Seq("JOB_NAME").toArray)
    Job.init(args("JOB_NAME"), glueContext, args.asJava)
    // Script generated for node AWS Glue Data Catalog
    val AWSGlueDataCatalog_node1708237359808 = glueContext.getCatalogSource(database="aws-poc2-db", tableName="world-bank-indicators", transformationContext="AWSGlueDataCatalog_node1708237359808").getDynamicFrame()

    // Script generated for node Remove Prior to 1990
    val RemovePriorto1990_node1708238527332 = AWSGlueDataCatalog_node1708237359808.applyMapping(mappings=Seq(("1990", "decimal", "1990", "decimal"), ("1991", "decimal", "1991", "decimal"), ("1992", "decimal", "1992", "decimal"), ("1993", "decimal", "1993", "decimal"), ("1994", "decimal", "1994", "decimal"), ("1995", "decimal", "1995", "decimal"), ("1996", "decimal", "1996", "decimal"), ("1997", "decimal", "1997", "decimal"), ("1998", "decimal", "1998", "decimal"), ("1999", "decimal", "1999", "decimal"), ("2000", "decimal", "2000", "decimal"), ("2001", "decimal", "2001", "decimal"), ("2002", "decimal", "2002", "decimal"), ("2003", "decimal", "2003", "decimal"), ("2004", "decimal", "2004", "decimal"), ("2005", "decimal", "2005", "decimal"), ("2006", "decimal", "2006", "decimal"), ("2007", "decimal", "2007", "decimal"), ("2008", "decimal", "2008", "decimal"), ("2009", "decimal", "2009", "decimal"), ("2010", "decimal", "2010", "decimal"), ("2011", "decimal", "2011", "decimal"), ("2012", "decimal", "2012", "decimal"), ("2013", "decimal", "2013", "decimal"), ("2014", "decimal", "2014", "decimal"), ("2015", "decimal", "2015", "decimal"), ("2016", "decimal", "2016", "decimal"), ("2017", "decimal", "2017", "decimal"), ("2018", "decimal", "2018", "decimal"), ("2019", "decimal", "2019", "decimal"), ("2020", "decimal", "2020", "decimal"), ("2021", "decimal", "2021", "decimal"), ("2022", "decimal", "2022", "decimal"), ("country name", "string", "country name", "string"), ("country code", "string", "country code", "string"), ("indicator name", "string", "indicator name", "string"), ("indicator code", "string", "indicator code", "string")), caseSensitive=false, transformationContext="RemovePriorto1990_node1708238527332")

    // Script generated for node Amazon S3
    val AmazonS3_node1708238624148 = glueContext.getSinkWithFormat(connectionType="s3", options=JsonOptions("""{"path": "s3://poc-world-bank-data/GC.DOD.TOTL.GD.ZS/", "partitionKeys": ["country code"]}"""), formatOptions=JsonOptions("""{"compression": "snappy"}"""), transformationContext="AmazonS3_node1708238624148", format="glueparquet").writeDynamicFrame(RemovePriorto1990_node1708238527332)

    Job.commit()
  }
}