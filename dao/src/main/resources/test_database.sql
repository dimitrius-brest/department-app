create table departments (
  id INT(10) auto_increment,
  name VARCHAR(100) not null,
  PRIMARY KEY (id)
);

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

insert into departments (name) values ('CEO');
insert into departments (name) values ('Economists');
insert into departments (name) values ('Programmers');

insert into employees (id_department, first_name, middle_name, last_name, birth_date, salary)
            values ('1','Ivan','Ivanovich','Ivanov','1970-01-01',10000);
insert into employees (id_department, first_name, middle_name, last_name, birth_date, salary)
            values ('2','Pyotr','Petrovich','Petrov','1980-02-21',5000);
insert into employees (id_department, first_name, middle_name, last_name, birth_date, salary)
            values ('2','Vasily','Vasilyevich','Vasilyev','1985-02-15',3000);
insert into employees (id_department, first_name, middle_name, last_name, birth_date, salary)
            values ('3','Alexander','Sergeevich','Pushkin','1991-05-26',5000);
insert into employees (id_department, first_name, middle_name, last_name, birth_date, salary)
            values ('3','John','','Smith','1990-12-03',4000);

/*
drop table departments;
drop table employees;
*/