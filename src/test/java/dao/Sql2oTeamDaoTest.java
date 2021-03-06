package dao;

import models.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Sql2o;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class Sql2oTeamDaoTest {

    private Sql2oTeamDao teamDao;
    private Sql2oMemberDao memberDao;
    private Connection conn;


    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        teamDao = new Sql2oTeamDao(sql2o);
        memberDao = new Sql2oMemberDao(sql2o);

        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    public Team setupNewTeam() {
        return new Team("Purple Parrots", "So Great");
    }

    @Test
    public void SetsId() throws Exception {
        Team team = setupNewTeam();
        int originalTeamId = team.getId();
        teamDao.add(team);
        assertNotEquals(originalTeamId, team.getId());
    }

    @Test
    public void getAll() throws Exception {
        Team testTeam = setupNewTeam();
        Team testTeam2 = setupNewTeam();
        teamDao.add(testTeam);
        teamDao.add(testTeam2);
        assertEquals(2, teamDao.getAll().size());
    }

    @Test
    public void findById() throws Exception {
        Team testTeam = setupNewTeam();
        Team testTeam2 = new Team("Orange Iguanas", "Cool");
        Team testTeam3 = new Team("Orange Iguanas", "Cool");
        Team testTeam4 = new Team("Orange Iguanas", "Cool");
        Team testTeam5 = new Team("Orange Iguanas", "Cool");
        teamDao.add(testTeam);
        teamDao.add(testTeam2);
        teamDao.add(testTeam3);
        teamDao.add(testTeam4);
        teamDao.add(testTeam5);
        assertEquals(5, teamDao.findById(5).getId());
    }



    @Test
    public void updateTeam() throws Exception {
        String initialTeamName = ("actual");
        Team team = new Team (initialTeamName, "yeah");
        teamDao.add(team);

        teamDao.update(team.getId(),"Cleaning", "cool");
        Team updatedTeam = teamDao.findById(team.getId());
        assertNotEquals(initialTeamName, updatedTeam.getTeamName());
    }

    @Test
    public void getAllMembersByTeam() throws Exception {
        Team team = setupNewTeam();
        teamDao.add(team);
        int teamId = team.getId();
        Member newMember = new Member("Karen", "ok", teamId);
        Member secondMember = new Member("Bill", "meh", teamId);
        Member thirdMember = new Member("Steve", "jerk", teamId);
        memberDao.add(newMember);
        memberDao.add(secondMember);


        assertTrue(teamDao.getAllMembersByTeam(teamId).size() == 2);
        assertTrue(teamDao.getAllMembersByTeam(teamId).contains(newMember));
        assertTrue(teamDao.getAllMembersByTeam(teamId).contains(secondMember));
        assertFalse(teamDao.getAllMembersByTeam(teamId).contains(thirdMember));
    }

    @Test
    public void clearAll() throws Exception {
        Team team = setupNewTeam();
        Team otherTeam = new Team("Red Jaguars", "Neat");
        teamDao.add(team);
        teamDao.add(otherTeam);
        int daoSize = teamDao.getAll().size();
        teamDao.clearAllTeams();
        assertTrue(daoSize > 0 && daoSize > teamDao.getAll().size());
    }




}