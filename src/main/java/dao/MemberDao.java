package dao;


import models.Member;

import java.util.List;

public interface MemberDao {
    //create
    void add (Member member);
    //read
    List<Member> getAll();

    Member findMemberById(int id);
    //update
    void update(int id, String memberName, String stats, int teamId);
    //delete
    void deleteById(int id);

    void clearAllMembers();

}