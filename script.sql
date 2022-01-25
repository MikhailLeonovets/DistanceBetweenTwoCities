create table city
(
    id   bigint auto_increment,
    name varchar(255) not null,
    constraint city_id_uindex
        unique (id)
);

alter table city
    add primary key (id);

create table city_city
(
    id             bigint auto_increment,
    first_city_id  bigint not null,
    second_city_id bigint not null,
    distance       float  not null,
    constraint city_city_id_uindex
        unique (id),
    constraint first_city_fk
        foreign key (first_city_id) references city (id)
            on update cascade on delete cascade,
    constraint second_city_id
        foreign key (second_city_id) references city (id)
            on update cascade on delete cascade
);

alter table city_city
    add primary key (id);


