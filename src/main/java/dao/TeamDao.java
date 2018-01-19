package dao;


import models.Team;

import java.util.List;

public interface TeamDao {
    //create
    void add (Team team);

    //read
    List<Team> getAll();


    Team findById(int id);

    //update
    void update(int id, String teamName, String description);

    //delete
    void deleteById(int id);
    void clearAllTeams();

}
