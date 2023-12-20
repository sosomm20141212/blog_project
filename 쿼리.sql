INSERT into user(user_id, PASSWORD, user_name) VALUES('aaa','1234','다솜');


SELECT * FROM board;

DESC board;

DROP TABLE board;

CREATE TABLE board (
  seq INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255),
  content TEXT,
  writer VARCHAR(50),
  search_count INT DEFAULT 1,
  write_date VARCHAR(50));
  
INSERT INTO board(seq,title,content,writer,search_count,write_date) VALUES(1,'과제제출기한','토요일까지','강다솜','1', CURRENT_DATE);


COMMIT;

ALTER TABLE file_upload DROP COLUMN member_id;

ALTER TABLE board
DROP COLUMN original_file_name,
DROP COLUMN secret_flag,
DROP COLUMN uuid;

CREATE TABLE file_upload (
  seq INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  original_file_name VARCHAR(255),
  uuid VARCHAR(255),
  secret_flag VARCHAR(50),
  reg_date VARCHAR(50),
  title VARCHAR(50),
  writer VARCHAR(50)
);

