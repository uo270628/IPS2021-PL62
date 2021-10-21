drop table article;

create table article(
    id varchar(20) not null,
    title varchar(20),
    author varchar(20),
    other_authors varchar(100),
    summary varchar(2000),
    keywords varchar(2000),
    srcfile varchar(20),
    presentation_card varchar(20),
    cv_authors varchar(2000),
    state varchar(20) not null,
    constraint pk_article primary key (id),
    constraint ck_article_state check (state in ('SENT', 'WITH_EDITOR', 'IN_REVISION', 'ACCEPTED', 'REJECTED', 'IN_EDITION', 'PUBLISHED')) 
);