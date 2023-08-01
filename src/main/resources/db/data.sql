/*
    user_tb dummy
*/
insert into user_tb (id, username, password, email, phone_number, position, roles, join_date, update_date)
values (1, '윤준호', '$2a$10$HM42UGF28HVpYO8X2hDe/eDdOSpNd5tlM1YvEbACJY3ZSGBSCtwce', 'tester01@gmail.com', '01012345678', '사원', 'ROLE_USER', '2023-07-27', '2023-07-27');
insert into user_tb (id, username, password, email, phone_number, position, roles,  join_date, update_date)
values (2, '곽빈', '$2a$10$HM42UGF28HVpYO8X2hDe/eDdOSpNd5tlM1YvEbACJY3ZSGBSCtwce', 'tester02@gmail.com', '01012345678', '사원', 'ROLE_USER', '2023-07-27', '2023-07-27');
insert into user_tb (id, username, password, email, phone_number, position, roles,  join_date, update_date)
values (3, '전형근', '$2a$10$HM42UGF28HVpYO8X2hDe/eDdOSpNd5tlM1YvEbACJY3ZSGBSCtwce', 'tester03@gmail.com', '01012345678', '사원', 'ROLE_USER', '2023-07-27', '2023-07-27');
insert into user_tb (id, username, password, email, phone_number, position, roles,  join_date, update_date)
values (4, '이영하', '$2a$10$HM42UGF28HVpYO8X2hDe/eDdOSpNd5tlM1YvEbACJY3ZSGBSCtwce', 'tester04@gmail.com', '01012345678', '주임', 'ROLE_USER', '2023-07-27', '2023-07-27');
insert into user_tb (id, username, password, email, phone_number, position, roles,  join_date, update_date)
values (5, '박치국', '$2a$10$HM42UGF28HVpYO8X2hDe/eDdOSpNd5tlM1YvEbACJY3ZSGBSCtwce', 'tester05@gmail.com', '01012345678', '대리', 'ROLE_USER', '2023-07-27', '2023-07-27');
insert into user_tb (id, username, password, email, phone_number, position, roles,  join_date, update_date)
values (6, '양석환', '$2a$10$HM42UGF28HVpYO8X2hDe/eDdOSpNd5tlM1YvEbACJY3ZSGBSCtwce', 'tester06@gmail.com', '01012345678', '대리', 'ROLE_ADMIN', '2023-07-27', '2023-07-27');
insert into user_tb (id, username, password, email, phone_number, position, roles,  join_date, update_date)
values (7, '정수빈', '$2a$10$HM42UGF28HVpYO8X2hDe/eDdOSpNd5tlM1YvEbACJY3ZSGBSCtwce', 'tester07@gmail.com', '01012345678', '과장', 'ROLE_USER', '2023-07-27', '2023-07-27');
insert into user_tb (id, username, password, email, phone_number, position, roles,  join_date, update_date)
values (8, '김재환', '$2a$10$HM42UGF28HVpYO8X2hDe/eDdOSpNd5tlM1YvEbACJY3ZSGBSCtwce', 'tester08@gmail.com', '01012345678', '차장', 'ROLE_USER', '2023-07-27', '2023-07-27');
insert into user_tb (id, username, password, email, phone_number, position, roles, join_date, update_date)
values (9, '허경민', '$2a$10$HM42UGF28HVpYO8X2hDe/eDdOSpNd5tlM1YvEbACJY3ZSGBSCtwce', 'tester09@gmail.com', '01012345678', '차장', 'ROLE_ADMIN', '2023-07-27', '2023-07-27');
insert into user_tb (id, username, password, email, phone_number, position, roles, join_date, update_date)
values (10, '양의지', '$2a$10$HM42UGF28HVpYO8X2hDe/eDdOSpNd5tlM1YvEbACJY3ZSGBSCtwce', 'tester10@gmail.com', '01012345678', '부장', 'ROLE_ADMIN', '2023-07-27', '2023-07-27');
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
insert into dayoff_tb (id, user_id, start_date, end_date, reason, type, status, num_of_day_off) values (1, 10, '2023-08-27', '2023-08-27', '배고파서', '오후반차', '승인', 12);
insert into dayoff_tb (id, user_id, start_date, end_date, reason, type, status, num_of_day_off) values (2, 6, '2023-08-27', '2023-08-27', '배고파서', '오전반차', '반려', 12);
insert into dayoff_tb (id, user_id, start_date, end_date, reason, type, status, num_of_day_off) values (3, 9, '2023-08-28', '2023-08-28', '배고파서', '오후반차', '대기', 12);
insert into dayoff_tb (id, user_id, start_date, end_date, reason, type, status, num_of_day_off) values (4, 10, '2023-08-29', '2023-08-29', '배고파서', '오전반차', '승인', 12);
insert into dayoff_tb (id, user_id, start_date, end_date, reason, type, status, num_of_day_off) values (5, 9, '2023-09-02', '2023-09-02', '배고파서', '오후반차', '대기', 15);
insert into dayoff_tb (id, user_id, start_date, end_date, reason, type, status, num_of_day_off) values (6, 8, '2023-09-04', '2023-09-05', '배고파서', '연차', '대기', 15);
insert into dayoff_tb (id, user_id, start_date, end_date, reason, type, status, num_of_day_off) values (7, 7, '2023-09-06', '2023-09-06', '배고파서', '오후반차', '대기', 15);
insert into dayoff_tb (id, user_id, start_date, end_date, reason, type, status, num_of_day_off) values (8, 8, '2023-09-27', '2023-09-27', '배고파서', '연차', '대기', 15);
insert into dayoff_tb (id, user_id, start_date, end_date, reason, type, status, num_of_day_off) values (9, 9, '2023-09-27', '2023-09-27', '배고파서', '오후반차', '대기', 15);
insert into dayoff_tb (id, user_id, start_date, end_date, reason, type, status, num_of_day_off) values (10, 10, '2023-09-29', '2023-09-29', '배고파서', '오후반차', '대기', 15);
/*
 duty_tb dummy 10
 */
insert into duty_tb (id, user_id, date, reason, status) values (1, 1, '2023-08-01', '막내1', '승인');
insert into duty_tb (id, user_id, date, reason, status) values (2, 2, '2023-08-02', '막내2', '승인');
insert into duty_tb (id, user_id, date, reason, status) values (3, 3, '2023-08-03', '막내3', '승인');
insert into duty_tb (id, user_id, date, reason, status) values (4, 1, '2023-08-04', '막내1', '승인');
insert into duty_tb (id, user_id, date, reason, status) values (5, 2, '2023-08-05', '막내2', '승인');
insert into duty_tb (id, user_id, date, reason, status) values (6, 3, '2023-08-06', '막내3', '승인');
insert into duty_tb (id, user_id, date, reason, status) values (7, 1, '2023-08-07', '막내1', '대기');
insert into duty_tb (id, user_id, date, reason, status) values (8, 2, '2023-08-08', '막내2', '대기');
insert into duty_tb (id, user_id, date, reason, status) values (9, 3, '2023-08-09', '막내3', '대기');
insert into duty_tb (id, user_id, date, reason, status) values (10, 1, '2023-08-10', '막내1', '반려');