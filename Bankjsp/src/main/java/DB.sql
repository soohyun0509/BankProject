drop database if exists bank;
create database bank;
use bank;
drop table if exists customer;
create table customer(
   a_no int auto_increment primary key,      -- 고유번호
   name char(10),                     -- 이름
    phone char(13),                     -- 핸드폰
    address text,                     -- 주소
    lockpassword text,                  -- 암호화된 비밀번호
    account char(15),                  -- 계좌
    depositmoney DECIMAL(15,0) unsigned ,   -- 잔고
    -- depositmoney int ,   -- 잔고
    haveloan int default 0,               -- 대출개수
    errorcount   tinyint default 0                     -- 로그인 오류횟수
);
drop table if exists securecard;
create table securecard(
   s_no int auto_increment primary key,
    secure1 char(4), secure2 char(4), secure3 char(4), secure4 char(4), secure5 char(4),
    secure6 char(4), secure7 char(4), secure8 char(4), secure9 char(4), secure10 char(4),
    secure11 char(4), secure12 char(4), secure13 char(4), secure14 char(4), secure15 char(4),
   secure16 char(4), secure17 char(4), secure18 char(4), secure19 char(4), secure20 char(4),
    secure21 char(4), secure22 char(4), secure23 char(4), secure24 char(4), secure25 char(4),
    secure26 char(4), secure27 char(4), secure28 char(4), secure29 char(4), secure30 char(4),
    a_no int,
   foreign key ( a_no ) references customer ( a_no )   
    
);
drop table if exists loan;
create table loan(
   l_no int auto_increment primary key,    -- 대출상품번호
    lname char(30),                     -- 대출상품명
    lcontent text,                     -- 상품내용
    limitmoney int,                     -- 대출한도
    rate DECIMAL(5,3),                     -- 이자율
    ltime int                    -- 대출기간
);
-- 보안카드 일련번호는 일단 보류

drop table if exists personalloan;
create table personalloan(
   p_no int auto_increment primary key, -- 대출자 번호
   lbalance DECIMAL(15,0),            -- 대출잔액
   lcount   int,            -- 남은 납부 횟수
   ldate date default (current_date) ,   -- 닙부일
    repay tinyint default 0, -- 정산 제대로 됐는지 0이면 정상 1 1달밀림 ~~~~
   a_no int,         -- 고유번호
   l_no int,         -- 대출상품번호
   foreign key ( a_no ) references customer ( a_no ),
    foreign key ( l_no ) references loan ( l_no )   
);
drop table if exists chattingroom;
create table chattingroom(
   c_room int AUTO_INCREMENT PRIMARY key,
    name char(10)
);

drop table if exists chatting;
create table chatting(
   c_no int AUTO_INCREMENT PRIMARY key,
    c_room int,
    name char(10),
    account char(15),
    c_content text,
    c_date date default (current_date),
     foreign key ( c_room ) references chattingroom ( c_room )   
);


drop table if exists saving;
create table saving(
   s_no int AUTO_INCREMENT PRIMARY key,
    s_name char(30),
    s_content text,
    s_rate int,
    s_limit int,
    s_month int
    
);

drop table if exists personalsaving;
create table personalsaving(
   ps_num int AUTO_INCREMENT PRIMARY key,
    s_no int,
    s_account VARCHAR(30),
    s_sdate varchar(30),
    s_edate VARCHAr(30),
    s_month VARchar(30),
    s_repay tinyint default 0,
    s_pay int,
    s_balance int default 0,
    a_no int,
    foreign key ( a_no ) references customer ( a_no ),
    foreign key ( s_no ) references saving ( s_no )  
);
  
-- 관리자아이디
insert customer(a_no , name, lockpassword,account) values(1, 'admin', 276974741728212199276974741728212199276974741728212199276974741728212199 , '1');
select * from customer;
select * from securecard;
select * from loan;
select * from personalloan;
select * from saving;
select * from personalsaving;
-- select ps.a_no, ps.e_date from customer c , saving s, personalsaving ps where c.depositmoney > ps.s_pay and ps.s_sdate like '________?';

delete from personalsaving where a_no = 2;
 update customer set depositmoney = depositmoney+1000000 where a_no = 2;
--   update customer set depositmoney = 10000 where a_no = 3;



-- delete from personalsaving where a_no = 2;
-- update customer c , saving s, personalsaving ps set c.depositmoney = (c.depositmoney - ps.s_pay) and ps.s_balance = (ps.s_balance + ps.s_pay) where c.depositmoney > ps.s_pay and ps.s_sdate like '________16' ;
 -- update customer c , saving s, personalsaving ps set c.depositmoney = c.depositmoney - ps.s_pay where ps.a_no = 2;
--  update customer c , saving s, personalsaving ps set c.depositmoney = c.depositmoney - ps.s_pay , ps.s_balance = (ps.s_balance + ps.s_pay) where c.depositmoney > ps.s_pay and ps.a_no = 2 and c.a_no = 2 and ps.s_sdate like '________16';
 
 -- update customer c , saving s, personalsaving ps set c.depositmoney = c.depositmoney - ps.s_pay , ps.s_balance = (ps.s_balance + ps.s_pay) where c.depositmoney > ps.s_pay and ps.s_sdate like '________17';
 -- update customer c , saving s, personalsaving ps set ps.s_repay = ps.s_repay+1 where c.depositmoney < ps.s_pay and ps.s_sdate like '________16';
 
 -- select ps.a_no, ps.s_edate from customer c , saving s, personalsaving ps where c.depositmoney > ps.s_pay and ps.s_sdate like '%17';