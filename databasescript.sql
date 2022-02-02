create database distance_between_cities;

create table distance_between_cities.city
(
    id   bigint auto_increment,
    name varchar(255) not null,
    constraint city_id_uindex
        unique (id)
);

alter table distance_between_cities.city
    add primary key (id);

create table distance_between_cities.route
(
    id             bigint auto_increment,
    first_city_id  bigint not null,
    second_city_id bigint not null,
    distance       float  not null,
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


