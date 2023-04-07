    
    create table grupo (
       id int4 not null,
        descricao varchar(80) not null,
        id_perfil int4 not null,
        primary key (id)
    );
    
    create table grupo_AUD (
       id int4 not null,
        REV int4 not null,
        REVTYPE int2,
        descricao varchar(80),
        id_perfil int4,
        primary key (id, REV)
    );
        
      
    create table REVINFO (
       REV int4 not null,
        REVTSTMP int8,
        primary key (REV)
    );
    
    
    create table usuario (
       id int4 not null,
        cpf varchar(40) not null,
        email varchar(40) not null,
        nome varchar(80) not null,
        senha varchar(255) not null,
        status varchar(10) not null,
        primary key (id)
    );
    
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
    );
    
    create table tarefa (
        tarefaid int4 not null,
        custo numeric(10, 2) not null,
        data_Limite timestamp not null,
        nome varchar(255) not null,
        primary key (tarefaid)
    );
    
    create table tarefa_AUD (
       tarefaid int4 not null,
        REV int4 not null,
        REVTYPE int2,
        custo numeric(10, 2),
        data_Limite timestamp,
        data_Inicio timestamp,
        nome varchar(255),
        primary key (tarefaid, REV)
    );
    
	
create sequence hibernate_sequence start 1 increment 1;
create sequence seq_grupo start 1 increment 1;
create sequence seq_usuario start 1 increment 1;
create sequence Tarefa_Seq start 1 increment 1;
        
    alter table grupo 
       add constraint FKpxpbrqh3x3tihg5e62omnq84f 
       foreign key (id_perfil) 
       references usuario;
    
    alter table grupo_AUD 
       add constraint FK4x926ipsuxdixln63tnm01oum 
       foreign key (REV) 
       references REVINFO;
        
    alter table uc_AUD 
       add constraint FK41crogr6ged8odrp8svc75xut 
       foreign key (REV) 
       references REVINFO;
    
    alter table usuario_AUD 
       add constraint FKccqpbcawc1yublnm3f1c0q8ie 
       foreign key (REV) 
       references REVINFO;
	   
    select
        nextval ('seq_usuario');

    select
        nextval ('seq_grupo');

    select
        nextval ('seq_grupo');

    select
        nextval ('seq_grupo');
    
     select
        nextval ('Tarefa_Seq');

    insert 
    into
        usuario
        (cpf, email, nome, senha, status, id) 
    values
        ('111.111.111-11', 'tarefa@gmail.com', 'tarefa', 123456, 'ATIVO', 1);

    select
        nextval ('seq_usuario');
		
    insert 
    into
        grupo
        ( id,descricao, id_perfil) 
    values
        (1, 'ADMIN', 1);
		
    select
        nextval ('seq_grupo');

    insert 
    into
        grupo
        (id,descricao, id_perfil) 
    values
        (2, 'ADMIN_COMMON', 1);
		
    select
        nextval ('seq_grupo');

    insert 
    into
        grupo
        ( id,descricao, id_perfil) 
    values
        (3, 'COMMON', 1);
		
    select
        nextval ('seq_grupo');   