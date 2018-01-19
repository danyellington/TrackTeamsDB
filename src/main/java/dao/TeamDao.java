package dao;


import models.Team;

import java.util.List;

public interface TeamDao {
    //create
    void add (Team team);

    //read
    List<Team> getAll();
    List<Team> getAllTasksByCategory(int categoryId);

    Team findById(int id);

    //update
    void update(int id, int teamId, String teamName, String description, String memberName);

    //delete
    void deleteById(int id);
    void clearAllCategories();

}
