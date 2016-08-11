package controllers;

import com.google.inject.Inject;
import models.User;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.*;

import views.html.*;

public class HomeJavaController extends Controller {

    private JPAApi jpaApi;

    @Inject
    public HomeJavaController(JPAApi jpaApi) {
        this.jpaApi = jpaApi;
    }

    @Transactional
    public Result index() {

        User user = new User("moahn", 23);
        jpaApi.em().persist(user);

        User user1 = jpaApi.em().find(User.class, 1L);

        System.out.println("user1 = " + user1);

        return ok(index.render("Your new application is ready."));
    }

}
