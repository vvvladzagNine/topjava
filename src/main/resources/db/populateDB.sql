DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (description,user_id,calories,date_time) VALUES
  ('Breakfast',100000, 500,TIMESTAMP '2011-05-16 10:36:37'),
  ('Dinner',100000, 1000,TIMESTAMP '2011-05-16 15:36:37'),
  ('Supper',100000, 500,TIMESTAMP '2011-05-16 19:36:37'),
  ('Breakfast2',100000, 501,TIMESTAMP '2011-05-17 10:36:38'),
  ('supper2',100000, 500,TIMESTAMP '2011-05-17 15:36:38'),
  ('dinner2',100000,1000,TIMESTAMP '2011-05-17 19:36:38');
