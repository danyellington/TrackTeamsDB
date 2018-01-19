package dao;


import models.Member;

import java.util.List;

public interface MemberDao {
    //create
    void add (Member member);
    //read
    List<Member> getAll();

    Member findById(int id);
    //update
    void update(int id, String name, String stats, int memberId);
    //delete
    void deleteById(int memberId);
    void clearAllTasks();

}