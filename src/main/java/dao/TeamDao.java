package dao;


import models.Member;
import models.Team;

import java.util.List;

public interface TeamDao {
    //create
    void add (Team team);

    //read
    List<Team> getAll();
    List<Member> getAllMembersByTeam(int id);

    Team findById(int id);

    //update
    void update(int id, String teamName, String description);

    //delete
    void deleteById(int id);
    void clearAllTeams();

}
