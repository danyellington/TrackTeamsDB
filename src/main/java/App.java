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
            return new ModelAndView(model, "index.hbs");
        },  new HandlebarsTemplateEngine());

//        show new word form
        get("/teams/new", (request, response)->{
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "form.hbs");
        },  new HandlebarsTemplateEngine());

//        process a new word form
        post("/teams/new", (request, response)->{
            Map<String, Object> model = new HashMap<>();
            String teamName = request.queryParams("teamName");
            String description = request.queryParams("description");
            String memberName = request.queryParams("memberName");
            Team newTeam = new Team("Silver Snakes", "cool", "tim");
            return new ModelAndView(model, "form.hbs");
        }, new HandlebarsTemplateEngine());

//        show processed words
        get("/teams/all", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Team> teams = Team.getAll();
            model.put("teams", teams);
            return new ModelAndView(model, "all-teams.hbs");
        }, new HandlebarsTemplateEngine());

//        show an individual post
        get("/teams/:id",(req, res) -> {
            Map<String,Object> model = new HashMap<>();
            int idOfWordToFind = Integer.parseInt(req.params("id"));
            Team foundTeam = Team.findById(idOfWordToFind);
            model.put("team", foundTeam);
            return new ModelAndView(model, "team-detail.hbs");
        }, new HandlebarsTemplateEngine());

//        get: show a form to update a post NAMES
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
            String newDefinition = req.queryParams("definition");
            int idOfTeamToEdit = Integer.parseInt(req.params("id"));
            Team editTeam = Team.findById(idOfTeamToEdit);
            editTeam.update(newDefinition); //don’t forget me
            return new ModelAndView(model, "team-detail.hbs");
        }, new HandlebarsTemplateEngine());

    }
}
