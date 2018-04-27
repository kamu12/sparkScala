import org.apache.spark.graphx.lib.TriangleCount
import org.apache.spark.graphx.{Graph, GraphLoader, PartitionStrategy}
import org.apache.spark.{SparkConf, SparkContext}


object Main {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("TriangleCount")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")
    val graph = GraphLoader
      .edgeListFile(sc, "followers.txt", true)
      .partitionBy(PartitionStrategy.RandomVertexCut)


    triCountStd(graph)
    triCountCustom(graph)
  }

  def triCountStd(graph: Graph[Int, Int]): Unit ={
    println("standard example")
    val trangleCount = TriangleCount.run(graph).vertices

    trangleCount.take(6)
    println(trangleCount.values.sum()/3)
    println(trangleCount.map(_._2).sum()/3)
    println(trangleCount.map(_._2).reduce(_ +_)/3)

  }

  def triCountCustom(graph: Graph[Int, Int]): Unit ={
    println("custom example")
    val trangleCount = TriangleCountsCustom.run(graph).vertices

    val triCounts = graph.triangleCount().vertices
    triCounts.take(6)
    println(triCounts.values.sum()/3)
    println(triCounts.map(_._2).sum()/3)
    println(triCounts.map(_._2).reduce(_ +_)/3)

  }

}
