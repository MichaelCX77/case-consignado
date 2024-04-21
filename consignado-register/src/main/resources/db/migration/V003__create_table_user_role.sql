create table user_role (
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id),
    foreign key (role_id) references role (id),
    foreign key (user_id) references user (id)
);

-- alter table user_role add constraint userrole_role foreign key (role_id) references role (id);
-- alter table user_role add constraint userrole_user foreign key (user_id) references user (id);