package dao;

import models.Team;
import models.Member;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oMemberDao implements MemberDao {

    private final Sql2o sql2o;

    public Sql2oMemberDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }


    @Override
    public void add(Member member) {

        String sql = "INSERT INTO members (memberName, description, teamId) VALUES (:memberName, :stats, :teamId)"; //raw sql
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql)
                    .addParameter("memberName", member.getMemberName())
                    .addParameter("stats", member.getStats())
                    .addParameter("teamId", member.getTeamId())
                    .addColumnMapping("MEMBERNAME", "memberName")
                    .addColumnMapping("STATS", "stats")
                    .addColumnMapping("TEAMID", "teamId")
                    .executeUpdate()
                    .getKey();
            member.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}

    @Override
    public List<Member> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM members")
                    .executeAndFetch(Member.class);
        }
    }

    @Override
    public Member findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM members WHERE id = :id")
                    .addParameter("id", id) //key/value pair, key must match above
                    .executeAndFetchFirst(Member.class); //fetch an individual item
        }
    }

    @Override
    public void update(int id, String newMemberName, String newStats, int newTeamId){
        String sql = "UPDATE tasks SET (memberName, stats, teamId) = (:memberName, :stats, :teamId) WHERE id=:id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("memberName", newMemberName)
                    .addParameter("stats", newStats)
                    .addParameter("categoryId", newTeamId)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from members WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void clearAllTasks() {
        String sql = "DELETE from members";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }


}
