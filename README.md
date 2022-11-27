
![example event parameter](https://github.com/TatianaKoma/HomeWorkShop7/actions/workflows/main.yml/badge.svg?event=push)

## Run 
To launch this app in isolation mode:
1. start your docker
2. docker build .
3. docker-compose up --build homeworkshop7-app 
4. copy and execute sql queries:
   INSERT INTO role (id, name) VALUES (1,'ROLE_USER');
   INSERT INTO role (id, name) VALUES (2,'ADMIN');