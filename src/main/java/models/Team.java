package models;

import java.util.ArrayList;

public class Team {
    private String teamName;
    private String description;
    private String memberName;
    private boolean published;
    private int id;
    private int teamId;



    public Team(String teamName, String description, String memberName, int teamId) {
        this.teamName = teamName;
        this.description = description;
        this.memberName = memberName;
        this.published = false;
        this.teamId = teamId;
    }


    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public void update(String memberName, String teamName) { this.memberName = memberName; this.teamName = teamName; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        if (id != team.id) return false;
        if (published != team.published) return false;
        if (teamName != null ? !teamName.equals(team.teamName) : team.teamName != null) return false;
        if (description != null ? !description.equals(team.description) : team.description != null) return false;
        return memberName != null ? memberName.equals(team.memberName) : team.memberName == null;
    }

    @Override
    public int hashCode() {
        int result = teamName != null ? teamName.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (memberName != null ? memberName.hashCode() : 0);
        result = 31 * result + id;
        result = 31 * result + (published ? 1 : 0);
        return result;
    }
}