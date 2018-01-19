SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS members (
  id int PRIMARY KEY auto_increment,
  memberName VARCHAR,
  stats VARCHAR,
  teamId INTEGER
);

CREATE TABLE IF NOT EXISTS teams (
 id int PRIMARY KEY auto_increment,
 teamName VARCHAR,
 description VARCHAR
);