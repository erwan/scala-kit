package io.prismic

import play.api.libs.json._

case class Experiment(
  id: String,
  name: String,
  variations: List[String])

object Experiment {

  implicit val experimentReads = Json.reads[Experiment]
}
