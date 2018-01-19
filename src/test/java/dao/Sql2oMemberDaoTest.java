package dao;

import models.Member;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Sql2o;

import org.sql2o.Connection;

import static org.junit.Assert.*;
import static junit.framework.TestCase.assertEquals;

public class Sql2oMemberDaoTest {

    private Sql2oMemberDao memberDao;
    private Connection conn;

    public Member setupNewMember() { return new Member("Edith", "Software Engineer", 4); }

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        memberDao = new Sql2oMemberDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addMemberSetsId() throws Exception {
        Member testMember = setupNewMember();
        int originalMemberId = testMember.getId();
        memberDao.add(testMember);
        assertNotEquals(originalMemberId, testMember.getId());
    }



}