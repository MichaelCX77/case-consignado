create table role (
    id bigint generated by default as identity,
    role_name varchar(255) unique
)