create table if not exists member (
                        id bigint not null auto_increment,
                        created_at datetime(6),
                        created_by varchar(255),
                        modified_at datetime(6),
                        modified_by varchar(255),
                        name varchar(255),
                        team_id bigint,
                        primary key (id)
) engine=InnoDB;

create table if not exists team (
                      id bigint not null auto_increment,
                      created_at datetime(6),
                      created_by varchar(255),
                      modified_at datetime(6),
                      modified_by varchar(255),
                      name varchar(255),
                      primary key (id)
) engine=InnoDB;

alter table member
    add constraint FK_TEAM_ID
        foreign key (team_id)
            references team (id);