package edu.knoldus.userservice.impl

import akka.{Done, NotUsed}
import com.knoldus.entities.User
import com.knoldus.service.ExternalUserService
import com.lightbend.lagom.scaladsl.api.ServiceCall
import edu.knoldus.userservice.api.models.UserInfo
import edu.knoldus.userservice.api.services.UserService

import scala.concurrent.ExecutionContext

class UserServiceImpl(userService: ExternalUserService)(implicit exec: ExecutionContext) extends UserService {

  override def getUser(): ServiceCall[NotUsed, String] = ServiceCall {
    _ =>
      val result = userService.getUserDetails(1).invoke()
      result.map(response => response)
  }

  override def addUser(): ServiceCall[UserInfo, Done] = ServiceCall {
    request =>
      val user = User(request.id, request.name, request.salary)
      userService.createUser().invoke(user)
  }
}
