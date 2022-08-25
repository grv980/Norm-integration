ALTER TABLE blogs
ADD userid int,
ADD FOREIGN KEY(userid) REFERENCES users(id);
