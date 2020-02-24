drop table if exists departments;

create table departments (
  id INT(10) auto_increment,
  name VARCHAR(100) not null,
  PRIMARY KEY (id)
);

drop table if exists employees;

create table employees (
  id INT(10) auto_increment,
  id_department INT(10) not null,
  first_name VARCHAR(50),
  middle_name VARCHAR(50),
  last_name VARCHAR(100) not null,
  birth_date DATE,
  salary INT(10),
  PRIMARY KEY (id),
  FOREIGN KEY (id_department) REFERENCES departments (id)
);