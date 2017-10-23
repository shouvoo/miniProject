create table t_qboard(
    no  number(6) primary key,
    title varchar2(200) not null,
    content varchar2(4000) not null,
    writer varchar2(30) not null,  
    reg_date date default sysdate,
    a_comment varchar2(4000),
    a_reg_date date
);

create sequence s_qboard_no;

select * from t_qboard;