package edu.knoldus.userservice.api.services

import akka.{Done, NotUsed}
import com.lightbend.lagom.scaladsl.api.transport.Method
import com.lightbend.lagom.scaladsl.api.{Descriptor, Service, ServiceCall}
import edu.knoldus.userservice.api.models.UserInfo

trait UserService extends Service {

  def getUser(): ServiceCall[NotUsed,String]

  def addUser(): ServiceCall[UserInfo,Done]

  override final def descriptor: Descriptor = {
    import Service._
    named("user-service")
      .withCalls(
        restCall(Method.GET, "/user/get", getUser _),
        restCall(Method.POST,"/user/create", addUser _)
      )
      .withAutoAcl(true)
  }
}
