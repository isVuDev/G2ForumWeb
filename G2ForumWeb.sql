
CREATE DATABASE G2ForumWeb;

USE G2ForumWeb;
CREATE TABLE userTbl (
	user_id int IDENTITY(1,1) PRIMARY KEY,
	username varchar(30),
	password varchar(30),
	email varchar(30),
	birthdate date,
	isMod bit,
	isDelete bit,
	isBanned bit,
	avatar varbinary(max),
);

INSERT INTO userTbl VALUES 
('admin','12345','godcomplex@gmail.com','2077','1','0','0',null),
('admin1','12345','godcomplex1@gmail.com','2000','1','0','0',null),
('admin2','12345','godcomplex2@gmail.com','2000','1','0','0',null),
('admin3','12345','godcomplex3@gmail.com','2000','1','0','0',null),
('admin4','12345','godcomplex4@gmail.com','2000','1','0','0',null),
('admin5','12345','godcomplex5@gmail.com','2000','1','0','0',null),
('user','12345','notcomplex@gmail.com','1999','0','0','0',null),
('user1','12345','notcomplex1@gmail.com','1999','0','0','0',null),
('user2','12345','notcomplex2@gmail.com','1999','0','0','0',null),
('user3','12345','notcomplex3@gmail.com','1999','0','0','0',null),
('user4','12345','notcomplex4@gmail.com','1999','0','0','0',null),
('user5','12345','notcomplex5@gmail.com','1999','0','0','0',null),
('user6','12345','notcomplex6@gmail.com','1999','0','0','0',null),
('user7','12345','notcomplex7@gmail.com','1999','0','0','0',null),
('user8','12345','notcomplex8@gmail.com','1999','0','0','0',null),
('user9','12345','notcomplex9@gmail.com','1999','0','0','0',null);

CREATE TABLE topicTbl (
	topic_id int IDENTITY(1,1) PRIMARY KEY,
	title text,
	content text
);

INSERT INTO topicTbl VALUES
('Vote for banning user','Free our people'),
('Vote for banning admin','Free our people'),
('Vote for promote admin','Free our people');

CREATE TABLE postTbl (
	post_id int IDENTITY(1,1) PRIMARY KEY,
	user_id int FOREIGN KEY REFERENCES userTbl(user_id) NOT NULL,
	topic_id int FOREIGN KEY REFERENCES topicTbl(topic_id) NOT NULL,
	title text,
	content text,
	isHidden bit,
	isDelete bit,
	image varbinary(max),
	voteSum int,
);

INSERT INTO postTbl VALUES
('1','1','Admin content','Anyone are free to vote','0','0',null,0),
('1','1','User content','Anyone are free to vote','0','0',null,0),
('1','1','New Users','Anyone are free to vote','0','0',null,0),
('1','1','Account creation','Anyone are free to vote','0','0',null,0);

CREATE TABLE voteTbl (
	vote_id int IDENTITY(1,1) PRIMARY KEY,
	user_id int FOREIGN KEY REFERENCES userTbl(user_id) NOT NULL,
	post_id int FOREIGN KEY REFERENCES postTbl(post_id),
	vote_type numeric(2,0) NOT NULL,
);

INSERT INTO voteTbl VALUES
('1','2','1'),
('2','1','1'),
('3','1','1'),
('4','1','-1'),
('5','1','-1'),
('6','1','-1'),
('1','2','1'),
('2','2','1'),
('3','2','-1'),
('4','2','-1'),
('5','2','-1'),
('6','2','-1'),
('1','3','1'),
('2','3','-1'),
('3','3','-1'),
('4','3','-1'),
('5','3','-1'),
('6','3','-1');

CREATE TABLE commentTbl (
	comment_id int IDENTITY(1,1) PRIMARY KEY,
	user_id int FOREIGN KEY REFERENCES userTbl(user_id) NOT NULL,
	post_id int FOREIGN KEY REFERENCES postTbl(post_id) NOT NULL,
	content text,
	image varbinary(max),
	parent_id int,
);

INSERT INTO commentTbl VALUES
('7','2','free our people',null,null),
('8','2','free our people',null,'1'),
('9','2','free our people',null,'1'),
('10','2','free our people',null,'2'),
('11','2','free our people',null,'2'),
('12','2','free our people',null,'2');

SELECT * FROM commentTbl WHERE post_id = 2;
