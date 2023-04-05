Hibernate: 
    
    create table tarefa (
        tarefaid int4 not null,
        custo numeric(10, 2) not null,
        data_Limite timestamp not null,
        nome varchar(255) not null,
        primary key (tarefaid)
    )
Hibernate: 
    
    create table tarefa_AUD (
       tarefaid int4 not null,
        REV int4 not null,
        REVTYPE int2,
        custo numeric(10, 2),
        data_Limite timestamp,
        data_Inicio timestamp,
        nome varchar(255),
        primary key (tarefaid, REV)
    )
Hibernate: 
    
    create table grupo (
       id int4 not null,
        descricao varchar(80) not null,
        id_perfil int4 not null,
        primary key (id)
    )
Hibernate: 
    
    create table grupo_AUD (
       id int4 not null,
        REV int4 not null,
        REVTYPE int2,
        descricao varchar(80),
        id_perfil int4,
        primary key (id, REV)
    )
Hibernate: 
    
    create table REVINFO (
       REV int4 not null,
        REVTSTMP int8,
        primary key (REV)
    )

Hibernate: 
    
    create table usuario (
       id int4 not null,
        cpf varchar(40) not null,
        email varchar(40) not null,
        nome varchar(80) not null,
        senha varchar(255) not null,
        status varchar(10) not null,
        primary key (id)
    )
Hibernate: 
    
    create table usuario_AUD (
       id int4 not null,
        REV int4 not null,
        REVTYPE int2,
        cpf varchar(40),
        email varchar(40),
        nome varchar(80),
        senha varchar(255),
        status varchar(10),
        primary key (id, REV)
    )
Hibernate: create sequence hibernate_sequence start 1 increment 1
Hibernate: create sequence Tarefa_Seq start 1 increment 1
Hibernate: create sequence seq_usuario start 1 increment 1
Hibernate: 
    
    alter table grupo 
       add constraint FKpxpbrqh3x3tihg5e62omnq84f 
       foreign key (id_perfil) 
       references usuario
Hibernate: 
    
    alter table grupo_AUD 
       add constraint FK4x926ipsuxdixln63tnm01oum 
       foreign key (REV) 
       references REVINFO
Hibernate: 
    
    alter table usuario_AUD 
       add constraint FKccqpbcawc1yublnm3f1c0q8ie 
       foreign key (REV) 
       references REVINFO
2021-04-10 12:35:54,795 INFO  [org.hibernate.engine.transaction.jta.platform.internal.JtaPlatformInitiator] HHH000490: Using JtaPlatform implementation: [org.hibernate.engine.transaction.jta.platform.internal.NoJtaPlatform]
Hibernate: 
    select
        nextval ('seq_usuario')
Hibernate: 
    select
        nextval ('Tarefa_Seq')