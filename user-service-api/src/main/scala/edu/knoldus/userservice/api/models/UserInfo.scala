package edu.knoldus.userservice.api.models

import play.api.libs.json.{Format, Json}

case class UserInfo(id: Int,name: String,salary: Int)

object UserInfo {

  implicit val format: Format[UserInfo] = Json.format
}
