import models.Member;
import models.Team;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import dao.Sql2oTeamDao;
import dao.Sql2oMemberDao;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String connectionString = "jdbc:h2:~/trackteams.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oMemberDao memberDao = new Sql2oMemberDao(sql2o);
        Sql2oTeamDao teamDao = new Sql2oTeamDao(sql2o);

////        shows home page
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            List<Team> allTeams = teamDao.getAll();
            model.put("teams", allTeams);

            List<Member> members = memberDao.getAll();
            model.put("members", members);
            return new ModelAndView(model, "home.hbs");
        }, new HandlebarsTemplateEngine());


////        show new form
        get("/teams/new", (request, response)->{
            Map<String, Object> model = new HashMap<>();

            List<Team> teams = teamDao.getAll();
            model.put("teams", teams);
            return new ModelAndView(model, "team-form.hbs");
        },  new HandlebarsTemplateEngine());

//        process new team
        post("/teams", (request, response)->{
            Map<String, Object> model = new HashMap<>();
            String teamName = request.queryParams("teamName");
            String description = request.queryParams("description");
            String memberName = request.queryParams("memberName");
            Team newTeam = new Team(teamName, description, 0);
            teamDao.add(newTeam);
            List<Team> teams = teamDao.getAll();
            model.put("teams", teams);
            return new ModelAndView(model, "home.hbs");
        }, new HandlebarsTemplateEngine());

//get: show a form to update a category
        get("/teams/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            model.put("editTeam", true);

            List<Team> allTeams = teamDao.getAll();
            model.put("teams", allTeams);

            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process a form to update a category
        //  /categories/update
        post("/teams/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamToEdit = Integer.parseInt(req.queryParams("editTeamId"));
            String newTeamName = req.queryParams("newTeamName");
            teamDao.update(teamDao.findById(idOfTeamToEdit).getId(), newTeamName);

            List<Team> teams = teamDao.getAll();
            model.put("teams", teams);

            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show a specific task in a specific category
        //  /categories/:category_id/tasks/:task_id
        get("/teams/:catId", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamToFind = Integer.parseInt(req.params("catId"));

            List<Team> teams = teamDao.getAll();
            model.put("teams", teams);

            Team foundTeam = teamDao.findById(idOfTeamToFind);
            model.put("team", foundTeam);
            List<Member> allMembersByTeam = teamDao.getAllMembersByTeam(idOfTeamToFind);
            model.put("members", allMembersByTeam);

            return new ModelAndView(model, "team-detail.hbs"); //new
        }, new HandlebarsTemplateEngine());


            //get: show all members in all teams and show all teams
            get("/", (req, res) -> {
                Map<String, Object> model = new HashMap<>();
                List<Member> members = memberDao.getAll();
                model.put("members", members);
                return new ModelAndView(model, "index.hbs");
            }, new HandlebarsTemplateEngine());

            //get: delete all members
            get("/members/delete", (req, res) -> {
                Map<String, Object> model = new HashMap<>();

                List<Team> allTeams = teamDao.getAll();
                model.put("teams", allTeams);

                memberDao.clearAllMembers();
                return new ModelAndView(model, "home.hbs");
            }, new HandlebarsTemplateEngine());


            //get: show new task form
            get("/members/new", (req, res) -> {
                Map<String, Object> model = new HashMap<>();

                List<Team> allTeams = teamDao.getAll();
                model.put("teams", allTeams);

                return new ModelAndView(model, "member-form.hbs");
            }, new HandlebarsTemplateEngine());


            //post: process new task form
            post("/members/new", (request, response) -> {
                Map<String, Object> model = new HashMap<>();

                List<Team> allTeams = teamDao.getAll();
                model.put("teams", allTeams);

                String memberName = request.queryParams("memberName");
                String stats = request.queryParams("stats");
                int teamId = Integer.parseInt(request.queryParams("teamId"));
                Member newMember = new Member(memberName, stats, teamId);
                memberDao.add(newMember);
                model.put("member", newMember);
                return new ModelAndView(model, "home.hbs");
            }, new HandlebarsTemplateEngine());


            //get: show an individual member in team
            get("/teams/:team_id/members/:member_id", (req, res) -> {
                Map<String, Object> model = new HashMap<>();
                int idOfMemberToFind = Integer.parseInt(req.params("member_id"));
                Member foundMember = memberDao.findById(idOfMemberToFind);
                model.put("member", foundMember);
                return new ModelAndView(model, "member-detail.hbs");
            }, new HandlebarsTemplateEngine());

            //get: show a form to update a task
            get("/members/update", (req, res) -> {
                Map<String, Object> model = new HashMap<>();

                List<Team> allTeams = teamDao.getAll();
                model.put("teams", allTeams);

                List<Member> allMembers = memberDao.getAll();
                model.put("members", allMembers);

                model.put("editMember", true);
                return new ModelAndView(model, "member-form.hbs");
            }, new HandlebarsTemplateEngine());

            //post: process a form to update a task
            post("/members/update", (req, res) -> {
                Map<String, Object> model = new HashMap<>();

                List<Team> allTeams = teamDao.getAll();
                model.put("teams", allTeams);

                String newMemberName = req.queryParams("memberName");
                String newStats = req.queryParams("stats");
                int newTeamId = Integer.parseInt(req.queryParams("teamId"));
                int memberToEditId = Integer.parseInt(req.queryParams("memberToEditId"));
                Member editMember = memberDao.findById(memberToEditId);
                memberDao.update(memberToEditId, newMemberName, newStats, newTeamId);

                return new ModelAndView(model, "home.hbs");
            }, new HandlebarsTemplateEngine());

            //get: delete an individual task
            get("teams/:team_id/tasks/:member_id/delete", (req, res) -> {
                Map<String, Object> model = new HashMap<>();
                int idOfMemberToDelete = Integer.parseInt(req.params("member_id"));
                Member deleteMember = memberDao.findById(idOfMemberToDelete);
                memberDao.deleteById(idOfMemberToDelete);
                return new ModelAndView(model, "home.hbs");
            }, new HandlebarsTemplateEngine());
        }
    }

