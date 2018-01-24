package models;

import java.util.ArrayList;

public class Team {
    private String teamName;
    private String description;
    private int id;





    public Team(String teamName, String description) {
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
        if (!teamName.equals(team.teamName)) return false;
        return description.equals(team.description);
    }

    @Override
    public int hashCode() {
        int result = teamName.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + id;
        return result;
    }
}