package models;

import java.util.ArrayList;

public class Team {
    private String teamName;
    private String description;
    private String memberName;
    private int id;
    private boolean published;
    private static ArrayList<Team> submit = new ArrayList<>();



    public Team(String teamName, String description, String memberName) {
        this.teamName = teamName;
        this.description = description;
        this.memberName = memberName;
        submit.add(this);
        this.id = submit.size();
        this.published = false;
    }


    public String getTeamName() { return teamName; }

    public String getDescription() { return description; }

    public String getMemberName() { return memberName; }

    public static ArrayList<Team> getAll() { return submit; }

    public int getId() { return id; }

    public static Team findById(int id) { return submit.get(id-1); }

    public void update(String memberName, String teamName) { this.memberName = memberName; this.teamName = teamName; }

    public boolean getPublished() { return this.published; }

    public static void clearAllTeams() { submit.clear(); }

}