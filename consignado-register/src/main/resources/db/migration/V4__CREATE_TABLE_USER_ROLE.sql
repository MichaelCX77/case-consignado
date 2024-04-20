create table user_role (user_id bigint not null, role_id bigint not null, primary key (user_id, role_id));
alter table user_role add constraint FKa68196081fvovjhkek5m97n3y foreign key (role_id) references role;
alter table user_role add constraint FK859n2jvi8ivhui0rl0esws6o foreign key (user_id) references user;