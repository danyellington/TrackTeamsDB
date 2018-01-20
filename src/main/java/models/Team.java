package models;

import java.util.ArrayList;

public class Team {
    private String teamName;
    private String description;
    private int id;





    public Team(String teamName, String description, int id) {
        this.teamName = teamName;
        this.description = description;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        if (id != team.id) return false;
        if (teamName != null ? !teamName.equals(team.teamName) : team.teamName != null) return false;
        return description != null ? description.equals(team.description) : team.description == null;
    }

    @Override
    public int hashCode() {
        int result = teamName != null ? teamName.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}