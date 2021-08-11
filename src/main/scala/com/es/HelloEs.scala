package com.es

object HelloEs extends App {
	
	import com.sksamuel.elastic4s.ElasticApi._
	import com.sksamuel.elastic4s.ElasticDsl.CreateIndexHandler
	import com.sksamuel.elastic4s.{ElasticClient, ElasticProperties, Response}
	import com.sksamuel.elastic4s.http.JavaClient
	import com.sksamuel.elastic4s.json.XContentFactory
	import com.sksamuel.elastic4s.requests.indexes.CreateIndexResponse
	import org.apache.commons.io.FileUtils

	import java.io.File
	import java.nio.charset.Charset

	
	val props = ElasticProperties("http://192.169.4.245:9200,http://192.169.4.245:9201," +
		"http://192.169.4.245:9202")
	val client: ElasticClient = ElasticClient(JavaClient(props))
	private val study_painless: String = this.getClass.getClassLoader.getResource("script/study.painless")
		.getPath;
	println(study_painless)
	private val mapping: String = this.getClass.getClassLoader.getResource("data/mapping.json").getPath
	private val mapping_str: String = FileUtils.readFileToString(new File(mapping), Charset.defaultCharset())
	private val await: Response[CreateIndexResponse] = client.execute(
		createIndex("seats")
			.source(source = mapping_str)
	
	
	).await
	println(await.result.acknowledged)
	client.close()
	
	
	
	
	
	//val correctJson =
	//	XContentFactory.parse(ingestStr).string()
}
