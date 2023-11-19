drop database if exists olduo_perfect_pair;
select @@global.transaction_isolation, @@transaction_isolation;
set @@transaction_isolation="read-committed";

create database olduo_perfect_pair;
use olduo_perfect_pair;

create table t_user(
    userId varchar(100) primary key,
    name varchar(100) not null,
    pass varchar(100) not null
);

create table t_group(
    id integer auto_increment primary key, 
    creator varchar(100) not null,
    title varchar(100) not null,
    description varchar(100) not null,
    code varchar(100) not null
);

create table t_group_user(
    id integer auto_increment primary key, 
    uId varchar(100) not null,
    gId integer not null,
    constraint foreign key(uId) references t_user(userId) on update cascade on delete cascade,
    constraint foreign key(gId) references t_group(id) on update cascade on delete cascade
);

create table t_quiz(
    id integer auto_increment primary key,
    gId integer not null,
    question varchar(1000) not null,
    constraint foreign key(gId) references t_group(id) on update cascade on delete cascade
);

create table t_answer(
    id integer auto_increment primary key,
    qId integer not null,
    userId varchar(100) not null,
    answer varchar(1000) not null,
    constraint foreign key(qId) references t_quiz(id) on update cascade on delete cascade,
    constraint foreign key(userId) references t_user(userId) on update cascade on delete cascade
);

INSERT INTO t_user (userId, name, pass) VALUES ('id 01', 'name 01', 'pass 01');
INSERT INTO t_user (userId, name, pass) VALUES ('id 02', 'name 02', 'pass 02');
INSERT INTO t_user (userId, name, pass) VALUES ('id 03', 'name 03', 'pass 03');
INSERT INTO t_user (userId, name, pass) VALUES ('id 04', 'name 04', 'pass 04');
INSERT INTO t_user (userId, name, pass) VALUES ('id 05', 'name 05', 'pass 05');
INSERT INTO t_user (userId, name, pass) VALUES ('id 06', 'name 06', 'pass 06');
INSERT INTO t_user (userId, name, pass) VALUES ('id 07', 'name 07', 'pass 07');
INSERT INTO t_user (userId, name, pass) VALUES ('id 08', 'name 08', 'pass 08');
INSERT INTO t_user (userId, name, pass) VALUES ('id 09', 'name 09', 'pass 09');
INSERT INTO t_user (userId, name, pass) VALUES ('id 10', 'name 10', 'pass 10');

INSERT INTO t_group (creator, title, description, code) VALUES ('id 01', '그룹1', '그룹1입니다.', 'G1');
INSERT INTO t_group (creator, title, description, code) VALUES ('id 01', '그룹2', '그룹2입니다.', 'G2');
INSERT INTO t_group (creator, title, description, code) VALUES ('id 02', '그룹3', '그룹3입니다.', 'G3');
INSERT INTO t_group (creator, title, description, code) VALUES ('id 03', '그룹4', '그룹4입니다.', 'G4');

INSERT INTO t_answer(qId, userId, answer) values ('2', 'id 01', 'test1111111111111111');
INSERT INTO t_answer(qId, userId, answer) values ('1', 'id 02', 'test2222222222222222');

select * from t_user;
select * from t_group;
select * from t_group_user;
select * from t_quiz;
select * from t_answer;

#drop table t_user;
#drop table t_group; 
#drop table t_group_user;
#drop table t_quiz;
#drop table t_answer;

commit; 