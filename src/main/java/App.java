import models.Member;
import models.Team;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
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


////        show new team form
        get("/teams/new", (request, response)->{
            Map<String, Object> model = new HashMap<>();

            List<Team> teams = teamDao.getAll();
            model.put("teams", teams);
            return new ModelAndView(model, "team-form.hbs");
        },  new HandlebarsTemplateEngine());


//        process new team
        post("/teams/new", (request, response)->{
            Map<String, Object> model = new HashMap<>();
            String teamName = request.queryParams("teamName");
            String description = request.queryParams("description");
            Team newTeam = new Team(teamName, description);
            teamDao.add(newTeam);
            List<Team> teams = teamDao.getAll();
            model.put("teams", teams);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());


        //shows team list
        get("/teams/all", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Team> allTeams = teamDao.getAll();
            model.put("teams", allTeams);
            return new ModelAndView(model, "all-teams.hbs");
        }, new HandlebarsTemplateEngine());


        //get: show a form to update a team
        get("/teams/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();

            model.put("editTeam", true);

            List<Team> allTeams = teamDao.getAll();
            model.put("teams", allTeams);

            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());


        //process a form to update a team
        post("/teams/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamToEdit = Integer.parseInt(request.queryParams("editTeamId"));
            String newTeamName = request.queryParams("newTeamName");
            String newDescription = request.queryParams("newDescription");
            List<Team> teams = teamDao.getAll();
            model.put("teams", teams);
            teamDao.update(idOfTeamToEdit, newTeamName, newDescription);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());


        //show new member form
        get("/members/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            List<Team> allTeams = teamDao.getAll();
            model.put("teams", allTeams);

            return new ModelAndView(model, "member-form.hbs");
        }, new HandlebarsTemplateEngine());


        //process new member form
        post("/members/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();

            String memberName = request.queryParams("memberName");
            String stats = request.queryParams("stats");
            int teamId = Integer.parseInt(request.queryParams("teamId"));
            Member newMember = new Member(memberName, stats, teamId);
            memberDao.add(newMember);
            model.put("member", newMember);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //show a form to update a member
        get("/teams/:team_id/members/:member_id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            List<Team> allTeams = teamDao.getAll();
            model.put("teams", allTeams);

            List<Member> allMembers = memberDao.getAll();
            model.put("members", allMembers);

            model.put("editMember", true);
            return new ModelAndView(model, "member-form.hbs");
        }, new HandlebarsTemplateEngine());


        //process a form to update a member
        post("/members/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            List<Team> allTeams = teamDao.getAll();
            model.put("teams", allTeams);

            String newMemberName = req.queryParams("memberName");
            String newStats = req.queryParams("stats");
            int newTeamId = Integer.parseInt(req.queryParams("teamId"));
            int memberToEditId = Integer.parseInt(req.queryParams("memberToEditId"));
            Member editMember = memberDao.findMemberById(memberToEditId);
            memberDao.update(memberToEditId, newMemberName, newStats, newTeamId);

            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());


        //show a team
        get("/teams/:teamId", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamToFind = Integer.parseInt(request.params("teamId"));
            Team foundTeam = teamDao.findById(idOfTeamToFind);
            model.put("team", foundTeam);
            List<Member> allMembersByTeam = teamDao.getAllMembersByTeam(idOfTeamToFind);
            model.put("members", allMembersByTeam);
            return new ModelAndView(model, "team-detail.hbs");
        }, new HandlebarsTemplateEngine());


//        //show a specific member on a specific team
        get("/teams/:teamId/members/:memberId", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfMemberToFind = Integer.parseInt(request.params("memberId"));
            Member findMember = memberDao.findMemberById(idOfMemberToFind);
            model.put("member", findMember);
            return new ModelAndView(model, "member-detail.hbs");
        }, new HandlebarsTemplateEngine());


        //show an individual member on a team
        get("/teams/:team_id/members/:member_id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfMemberToFind = Integer.parseInt(req.params("member_id"));
            Member foundMember = memberDao.findMemberById(idOfMemberToFind);
            model.put("member", foundMember);
            return new ModelAndView(model, "member-detail.hbs");
        }, new HandlebarsTemplateEngine());


        //delete an individual member
        get("teams/:team_id/members/:member_id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfMemberToDelete = Integer.parseInt(req.params("member_id"));
            Member deleteMember = memberDao.findMemberById(idOfMemberToDelete);
            memberDao.deleteById(idOfMemberToDelete);
            return new ModelAndView(model, "home.hbs");
        }, new HandlebarsTemplateEngine());

        //delete a team
        get("/teams/:teamId/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamToDelete = Integer.parseInt(request.params("teamId"));
            Team deleteTeam = teamDao.findById(idOfTeamToDelete);
            teamDao.deleteById(idOfTeamToDelete);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

    }


}

