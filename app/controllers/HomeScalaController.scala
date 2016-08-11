package controllers

import java.util.function.Function
import javax.inject._
import javax.persistence.EntityManager

import models.User
import play.api.mvc._
import play.db.jpa.{JPAApi, Transactional}

@Transactional
class HomeScalaController @Inject()(jpaApi: JPAApi) extends Controller {

  @Transactional
  def index = Action {

    thisIsNotWorking
    //    thisWorks

    Ok(views.html.index("Your new application is ready."))
  }

  def thisIsNotWorking: Unit = {
    val user: User = new User("moahn", 23)
    jpaApi.em.persist(user)
    val user1: User = jpaApi.em.find(classOf[User], 1L)

    System.out.println("user1 = " + user1)
  }

  def thisWorks: User = {
    jpaApi.withTransaction(new Function[EntityManager, User] {
      override def apply(t: EntityManager): User = {
        val user: User = new User("moahn", 23)
        jpaApi.em.persist(user)
        val user1: User = jpaApi.em.find(classOf[User], 1L)

        System.out.println("user1 = " + user1)
        user1
      }
    })
  }
}