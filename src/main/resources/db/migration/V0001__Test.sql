create table person (
  id int not null primary key,
  name varchar(255) not null,
  age int not null
);

insert into person values
(1, 'Test', 15),
(2, 'Test 2', 25);