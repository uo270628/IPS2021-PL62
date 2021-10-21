create table article(
    id varchar(20) not null,
    title varchar(20) not null,
    summary varchar(100),
    srcfile varchar(20) not null,
    presentation_card varchar(20) not null,
    constraint pk_article primary key (id)
);

create table author(
    name varchar(20) not null,
    cv varchar(20) not null,
    constraint pk_author primary key (name)
);

create table publish(
    name_author varchar(20) not null,
    id_article varchar(20) not null,
    constraint pk_publish primary key (name_author, id_article),
    constraint fk_name_author foreign key (name_author) references author (name),
    constraint fk_id_article foreign key (id_article) references article (id)
);

create table keyword(
    value varchar(20) not null,
    constraint pk_keyword primary key (value)
);

create table contains(
    id_article varchar(20) not null,
    keyword varchar(20) not null,
    constraint pk_include primary key (keyword, id_article),
    constraint fk_keyword foreign key (keyword) references keyword (value),
    constraint fk_article_id foreign key (id_article) references article (id)
);

insert into author values ('felipe', 'felipecv.pdf');