create table newslettermails(
email_id varchar(60) primary key);

create table newslettermailconnections(
id int primary key,
email_id varchar(60),
link varchar(10000),
constraint constraint_f_k foreign key(email_id) references newslettermails(email_id));

create sequence newslettermailconnections_id_seq start 1 increment by 1;