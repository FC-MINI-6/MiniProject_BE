/*
    user_tb dummy
*/
insert into user_tb (id, username, password, email, phone_number, position, roles, join_date, update_date)
values (1, '윤준호', '0000', 'tester01@gmail.com', '01012345678', '0', '0', '2023-07-27', '2023-07-27');
insert into user_tb (id, username, password, email, phone_number, position, roles,  join_date, update_date)
values (2, '곽빈', '0000', 'tester02@gmail.com', '01012345678', '0', '0', '2023-07-27', '2023-07-27');
insert into user_tb (id, username, password, email, phone_number, position, roles,  join_date, update_date)
values (3, '전형근', '0000', 'tester03@gmail.com', '01012345678', '0', '0', '2023-07-27', '2023-07-27');
insert into user_tb (id, username, password, email, phone_number, position, roles,  join_date, update_date)
values (4, '이영하', '0000', 'tester04@gmail.com', '01012345678', '1', '0', '2023-07-27', '2023-07-27');
insert into user_tb (id, username, password, email, phone_number, position, roles,  join_date, update_date)
values (5, '박치국', '0000', 'tester05@gmail.com', '01012345678', '2', '0', '2023-07-27', '2023-07-27');
insert into user_tb (id, username, password, email, phone_number, position, roles,  join_date, update_date)
values (6, '양석환', '0000', 'tester06@gmail.com', '01012345678', '2', '1', '2023-07-27', '2023-07-27');
insert into user_tb (id, username, password, email, phone_number, position, roles,  join_date, update_date)
values (7, '정수빈', '0000', 'tester07@gmail.com', '01012345678', '3', '0', '2023-07-27', '2023-07-27');
insert into user_tb (id, username, password, email, phone_number, position, roles,  join_date, update_date)
values (8, '김재환', '0000', 'tester08@gmail.com', '01012345678', '4', '0', '2023-07-27', '2023-07-27');
insert into user_tb (id, username, password, email, phone_number, position, roles, join_date, update_date)
values (9, '허경민', '0000', 'tester09@gmail.com', '01012345678', '4', '1', '2023-07-27', '2023-07-27');
insert into user_tb (id, username, password, email, phone_number, position, roles, join_date, update_date)
values (10, '양의지', '0000', 'tester10@gmail.com', '01012345678', '5', '1', '2023-07-27', '2023-07-27');
insert into user_tb (id, username, password, email, phone_number, position, roles, join_date, update_date)
values (11, '어드민', '{bcrypt}$2a$10$5nJepi7hydvCqZRDWjhlBe/isQmCbZFtmjUCAu8Bm7WAtfEfXMRUG', 'admin@admin.com', '01012345678', '5', '1', '2023-07-27', '2023-07-27');
/*
 login_tb dummy 10
 */
insert into login_tb (id, user_id, ip, user_agent, login_at) values (1, 10, '255.255.255.255', 'Mozilla/...','2023-07-27');
insert into login_tb (id, user_id, ip, user_agent, login_at) values (2, 9, '255.255.255.0', 'Mozilla/...','2023-07-27');
insert into login_tb (id, user_id, ip, user_agent, login_at) values (3, 8, '255.255.255.0', 'Mozilla/...','2023-07-27');
insert into login_tb (id, user_id, ip, user_agent, login_at) values (4, 7, '255.255.255.0', 'Mozilla/...','2023-07-27');
insert into login_tb (id, user_id, ip, user_agent, login_at) values (5, 6, '255.255.255.0', 'Mozilla/...','2023-07-27');
insert into login_tb (id, user_id, ip, user_agent, login_at) values (6, 5, '255.255.255.0', 'Mozilla/...','2023-07-27');
insert into login_tb (id, user_id, ip, user_agent, login_at) values (7, 4, '255.255.255.0', 'Mozilla/...','2023-07-27');
insert into login_tb (id, user_id, ip, user_agent, login_at) values (8, 3, '255.255.255.1', 'Mozilla/...','2023-07-27');
insert into login_tb (id, user_id, ip, user_agent, login_at) values (9, 2, '255.255.255.255', 'Mozilla/...','2023-07-27');
insert into login_tb (id, user_id, ip, user_agent, login_at) values (10, 1, '255.255.255.255', 'Mozilla/...','2023-07-27');
/*
 dayoff_tb dummy 10
 */
insert into dayoff_tb (id, user_id, start_date, end_date, reason, type, status, num_of_day_off, apply_at, process_at)
values (1, 10, '2023-08-27', '2023-08-27', '배고파서', '2', '1', 12, '2023-08-07', null);
insert into dayoff_tb (id, user_id, start_date, end_date, reason, type, status, num_of_day_off, apply_at, process_at)
values (2, 6, '2023-08-27', '2023-08-27', '배고파서', '1', '2', 12, '2023-08-08', null);
insert into dayoff_tb (id, user_id, start_date, end_date, reason, type, status, num_of_day_off, apply_at, process_at)
values (3, 9, '2023-08-28', '2023-08-28', '배고파서', '2', '0', 12, '2023-08-09', null);
insert into dayoff_tb (id, user_id, start_date, end_date, reason, type, status, num_of_day_off, apply_at, process_at)
values (4, 10, '2023-08-29', '2023-08-29', '배고파서', '1', '1', 12, '2023-08-10', null);
insert into dayoff_tb (id, user_id, start_date, end_date, reason, type, status, num_of_day_off, apply_at, process_at)
values (5, 9, '2023-09-02', '2023-09-02', '배고파서', '2', '0', 15, '2023-08-11', null);
insert into dayoff_tb (id, user_id, start_date, end_date, reason, type, status, num_of_day_off, apply_at, process_at)
values (6, 8, '2023-09-04', '2023-09-05', '배고파서', '0', '0', 15, '2023-08-12', null);
insert into dayoff_tb (id, user_id, start_date, end_date, reason, type, status, num_of_day_off, apply_at, process_at)
values (7, 7, '2023-09-06', '2023-09-06', '배고파서', '2', '0', 15, '2023-08-13', null);
insert into dayoff_tb (id, user_id, start_date, end_date, reason, type, status, num_of_day_off, apply_at, process_at)
values (8, 8, '2023-09-27', '2023-09-27', '배고파서', '0', '0', 15, '2023-08-14', null);
insert into dayoff_tb (id, user_id, start_date, end_date, reason, type, status, num_of_day_off, apply_at, process_at)
values (9, 9, '2023-09-27', '2023-09-27', '배고파서', '2', '0', 15, '2023-08-15', null);
insert into dayoff_tb (id, user_id, start_date, end_date, reason, type, status, num_of_day_off, apply_at, process_at)
values (10, 10, '2023-09-29', '2023-09-29', '배고파서', '2', '0', 15, '2023-08-16', null);
insert into dayoff_tb (id, user_id, start_date, end_date, reason, type, status, num_of_day_off, apply_at, process_at)
values (11, 10, '2023-09-29', '2023-10-29', '배고파서', '0', '0', 15, '2023-08-17', null);
/*
 duty_tb dummy 10
 */
insert into duty_tb (id, user_id, date, reason, status) values (1, 1, '2023-08-01', '막내1', '1');
insert into duty_tb (id, user_id, date, reason, status) values (2, 2, '2023-08-02', '막내2', '1');
insert into duty_tb (id, user_id, date, reason, status) values (3, 3, '2023-08-03', '막내3', '1');
insert into duty_tb (id, user_id, date, reason, status) values (4, 1, '2023-08-04', '막내1', '1');
insert into duty_tb (id, user_id, date, reason, status) values (5, 2, '2023-08-05', '막내2', '1');
insert into duty_tb (id, user_id, date, reason, status) values (6, 3, '2023-08-06', '막내3', '1');
insert into duty_tb (id, user_id, date, reason, status) values (7, 1, '2023-08-07', '막내1', '0');
insert into duty_tb (id, user_id, date, reason, status) values (8, 2, '2023-08-08', '막내2', '0');
insert into duty_tb (id, user_id, date, reason, status) values (9, 3, '2023-08-09', '막내3', '0');
insert into duty_tb (id, user_id, date, reason, status) values (10, 1, '2023-08-10', '막내1', '2');