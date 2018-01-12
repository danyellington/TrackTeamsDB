import models.Team;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

//        shows home page
        get("/", (request, response)->{
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "home.hbs");
        },  new HandlebarsTemplateEngine());

//        show new form
        get("/teams/new", (request, response)->{
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "form.hbs");
        },  new HandlebarsTemplateEngine());

//        process new
        post("/teams/new", (request, response)->{
            Map<String, Object> model = new HashMap<>();
            String teamName = request.queryParams("teamName");
            String description = request.queryParams("description");
            String memberName = request.queryParams("memberName");
            Team newTeam = new Team(teamName, description, memberName);
            return new ModelAndView(model, "form.hbs");
        }, new HandlebarsTemplateEngine());

//        show processed
        get("/teams/all", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Team> teams = Team.getAll();
            model.put("teams", teams);
            return new ModelAndView(model, "all-teams.hbs");
        }, new HandlebarsTemplateEngine());

//        show an individual post
        get("/teams/:id",(req, res) -> {
            Map<String,Object> model = new HashMap<>();
            int idOfTeamToFind = Integer.parseInt(req.params("id"));
            Team foundTeam = Team.findById(idOfTeamToFind);
            model.put("team", foundTeam);
            return new ModelAndView(model, "team-detail.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show a form to update a post
        get("/teams/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamToEdit = Integer.parseInt(req.params("id"));
            Team editTeam = Team.findById(idOfTeamToEdit);
            model.put("editTeam", editTeam);
            return new ModelAndView(model, "edit.hbs");
        }, new HandlebarsTemplateEngine());



        //post: process a form to update a post.

        post("/teams/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String newMemberName = req.queryParams("memberName");
            String newTeamName = req.queryParams("teamName");
            int idOfTeamToEdit = Integer.parseInt(req.params("id"));
            Team editTeam = Team.findById(idOfTeamToEdit);
            editTeam.update(newMemberName, newTeamName);
            return new ModelAndView(model, "team-detail.hbs");
        }, new HandlebarsTemplateEngine());



    }
}
