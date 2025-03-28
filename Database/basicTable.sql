create table if not exists Accounts(
	
	owner_name varchar(100) not null,
	amount numeric(10, 2),
	id int generated always as identity primary key
	
);


insert into Accounts values ('Ivanov', 21000.00);
insert into Accounts values ('Carolin', 1000.00);
insert into Accounts values ('Alexander Bolsky', 1000.00);
insert into Accounts values ('Mahdi', 2300.00);