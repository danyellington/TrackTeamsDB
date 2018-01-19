SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS teams (
  id int PRIMARY KEY auto_increment,
  taskName VARCHAR,
  description VARCHAR,
  completed BOOLEAN,
  categoryId INTEGER
);

CREATE TABLE IF NOT EXISTS members (
 id int PRIMARY KEY auto_increment,
 name VARCHAR
 stats VARCHAR
 );