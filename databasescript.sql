create table city
(
    id      bigint auto_increment
        primary key,
    name    varchar(255) not null,
    version bigint       not null,
    constraint city_id_uindex
        unique (id)
);

create table route
(
    id             bigint auto_increment,
    first_city_id  bigint not null,
    second_city_id bigint not null,
    distance       float  not null,
    version        bigint not null,
    primary key (first_city_id, second_city_id),
    constraint city_city_id_uindex
        unique (id),
    constraint first_city_fk
        foreign key (first_city_id) references city (id)
            on update cascade on delete cascade,
    constraint second_city_id
        foreign key (second_city_id) references city (id)
            on update cascade on delete cascade
);


