
    create table app_user (
       id uuid DEFAULT uuid_generate_v4() not null,
        account_type varchar(255) not null,
        creation_date timestamp not null,
        last_updated timestamp not null,
        password varchar(255) not null,
        username varchar(255) not null,
        primary key (id)
    )

    create table cards (
       id  serial not null,
        description varchar(255),
        name varchar(255),
        stars int,
        primary key (id)
    )

    create table collection (
       id  serial not null,
        mgp int4 not null,
        user_id uuid DEFAULT uuid_generate_v4() not null,
        primary key (id)
    )

    create table collection_card (
       collection_id int4 not null,
        card_id serial not null,
        primary key (collection_id, card_id)
    )

    create table deck (
       id  serial not null,
        collection_id int4 not null,
        primary key (id)
    )

    create table user_profile (
       user_id uuid not null,
        email varchar(255) not null,
        first_name varchar(255) not null,
        last_name varchar(255) not null,
        last_updated timestamp not null,
        primary key (user_id)
    )

    alter table if exists app_user 
       add constraint UK_3k4cplvh82srueuttfkwnylq0 unique (username)

    alter table if exists user_profile 
       add constraint UK_tcks72p02h4dp13cbhxne17ad unique (email)

    alter table if exists collection 
       add constraint FKclghbvpyywq5b83wl29d7lgv 
       foreign key (user_id) 
       references app_user

    alter table if exists collection_card 
       add constraint FK2gvkkslsapn10geldbvjuqpmp 
       foreign key (card_id) 
       references cards

    alter table if exists collection_card 
       add constraint FKta30x5bp2dtixv751y318r60f 
       foreign key (collection_id) 
       references collection

    alter table if exists deck 
       add constraint FK43n3q8jbm0ikuc6m2swg1t4te 
       foreign key (collection_id) 
       references collection

    alter table if exists user_profile 
       add constraint FKpdmw33px6fmevqhcy2lpstu4w 
       foreign key (user_id) 
       references app_user

    create table app_user (
       id uuid DEFAULT uuid_generate_v4() not null,
        account_type varchar(255) not null,
        creation_date timestamp not null,
        last_updated timestamp not null,
        password varchar(255) not null,
        username varchar(255) not null,
        primary key (id)
    )

    create table cards (
       id  serial not null,
        description varchar(255),
        name varchar(255),
        stars int,
        primary key (id)
    )

    create table collection (
       id  serial not null,
        mgp int4 not null,
        user_id uuid DEFAULT uuid_generate_v4() not null,
        primary key (id)
    )

    create table collection_card (
       collection_id int4 not null,
        card_id serial not null,
        primary key (collection_id, card_id)
    )

    create table deck (
       id  serial not null,
        collection_id int4 not null,
        primary key (id)
    )

    create table user_profile (
       user_id uuid not null,
        email varchar(255) not null,
        first_name varchar(255) not null,
        last_name varchar(255) not null,
        last_updated timestamp not null,
        primary key (user_id)
    )

    alter table if exists app_user 
       add constraint UK_3k4cplvh82srueuttfkwnylq0 unique (username)

    alter table if exists user_profile 
       add constraint UK_tcks72p02h4dp13cbhxne17ad unique (email)

    alter table if exists collection 
       add constraint FKclghbvpyywq5b83wl29d7lgv 
       foreign key (user_id) 
       references app_user

    alter table if exists collection_card 
       add constraint FK2gvkkslsapn10geldbvjuqpmp 
       foreign key (card_id) 
       references cards

    alter table if exists collection_card 
       add constraint FKta30x5bp2dtixv751y318r60f 
       foreign key (collection_id) 
       references collection

    alter table if exists deck 
       add constraint FK43n3q8jbm0ikuc6m2swg1t4te 
       foreign key (collection_id) 
       references collection

    alter table if exists user_profile 
       add constraint FKpdmw33px6fmevqhcy2lpstu4w 
       foreign key (user_id) 
       references app_user
