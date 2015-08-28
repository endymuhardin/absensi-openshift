create table m_user (
    id VARCHAR(255) PRIMARY KEY,
    username VARCHAR(20) NOT NULL,
    password VARCHAR(255) NOT NULL,
    enabled BOOLEAN NOT NULL
) Engine=InnoDB ;

create table m_role(
    id VARCHAR(255) PRIMARY KEY,
    role_name VARCHAR(20) NOT NULL
) Engine=InnoDB;

create table m_role_user(
    id_user VARCHAR(255) NOT NULL,
    id_role VARCHAR(255) NOT NULL
) Engine=InnoDB;

insert into m_user (id, username, password, enabled) values
('a', 'endy', '123', true),
('b', 'adi', '123', true);

insert into m_role (id, role_name) values
('adm', 'ROLE_ADMIN'),
('oper', 'ROLE_OPERATOR');

insert into m_role_user (id_user, id_role) values
('a', 'oper'),
('b', 'adm'),
('b', 'oper');

select username, password, enabled from m_user where username='endy';
select username, role_name as authority 
from m_user 
inner join m_role_user on m_user.id = m_role_user.id_user
inner join m_role on m_role_user.id_role = m_role.id
where m_user.username='adi';