package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class TeamTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
        Team.clearAllTeams();
    }

//
//    @Test
//    public void createTeam_team_getTeam() throws Exception {
//        Team testTeam = new Team("Silver Snakes", "cool", "Bertie");
//        assertEquals(true, testTeam instanceof Team);
//    }
//
    @Test
    public void TeamName_getName_getTeamName() throws Exception {
        Team testTeam = new Team("Silver Snakes", "cool", "Bertie");
        assertEquals("Silver Snakes", testTeam.getTeamName());
    }

    @Test
    public void TeamDescription_getDescription_getTeamDescription() throws Exception {
        Team testTeam = new Team("Silver Snakes", "cool", "Bertie");
        assertEquals("cool", testTeam.getDescription());
    }

    @Test
    public void TeamMemberName_getMemberName_getTeamMemberName() throws Exception {
        Team testTeam = new Team("Silver Snakes", "cool", "Bertie");
        assertEquals("Tim", testTeam.getMemberName());
    }

    @Test
    public void testTeam_instantiatesCorrectly_true() {
        Team testTeam = setupNewTeam();
        assertEquals(true, testTeam instanceof Team);
    }
//
//    @Test
//    public void testTeam_correctlyReturned_true() {
//        Team testTeam = setupNewTeam();
//        assertTrue(Team.getAll().contains(testTeam));
//
//    }
//
//    @Test
//    public void testOtherTeam_correctlyReturned_true() {
//        Team otherTeam = new Team("Green Monkeys", "neat", "Gertrude");
//        assertTrue(Team.getAll().contains(otherTeam));
//    }

    public Team setupNewTeam() {
        return new Team("Silver Snakes", "cool", "Bertie");
    }



}