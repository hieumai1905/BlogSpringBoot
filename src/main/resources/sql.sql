create table accounts
(
    account_id bigint auto_increment
        primary key,
    birthday   datetime(6)  not null,
    create_at  datetime(6)  not null,
    email      varchar(255) not null,
    fullname   varchar(255) not null,
    gender     int          not null,
    password   varchar(255) not null,
    role       int          not null,
    status     int          not null
);

create table categories
(
    category_id   bigint auto_increment
        primary key,
    create_at     datetime(6)  not null,
    name_category varchar(255) not null
);

create table images_slide
(
    image_id    bigint auto_increment
        primary key,
    description varchar(255) not null,
    link        varchar(255) not null,
    url         varchar(255) not null
);

create table types
(
    type_id   bigint auto_increment
        primary key,
    create_at datetime(6)  not null,
    type_name varchar(255) not null
);

create table posts
(
    post_id       bigint auto_increment
        primary key,
    brief_content varchar(255) not null,
    content       varchar(255) not null,
    create_at     datetime(6)  not null,
    picture       varchar(255) not null,
    rate          int          not null,
    status        int          not null,
    title         varchar(255) not null,
    account_id    bigint       not null,
    category_id   bigint       not null,
    type_id       bigint       not null,
    constraint FK2lkhwrqhy2gfs1ops6uqekoxr
        foreign key (type_id) references types (type_id),
    constraint FKf9l8slr2hdv6us03g5fkn5tcd
        foreign key (account_id) references accounts (account_id),
    constraint FKijnwr3brs8vaosl80jg9rp7uc
        foreign key (category_id) references categories (category_id)
);

create table comments
(
    account_id bigint      not null,
    post_id    bigint      not null,
    comment_at datetime(6) not null,
    content    text        not null,
    primary key (account_id, post_id),
    constraint FKagkmt4oa6cdwdop1odcp2ala4
        foreign key (account_id) references accounts (account_id),
    constraint FKh4c7lvsc298whoyd4w9ta25cr
        foreign key (post_id) references posts (post_id)
);

create table rate
(
    account_id   bigint not null,
    post_id      bigint not null,
    rating_value bigint not null,
    primary key (account_id, post_id),
    constraint FKcc6fqtvnr8ebg64d1nv3ku3i5
        foreign key (post_id) references posts (post_id),
    constraint FKhk0vrpgg64bxpo2sp9xvai9bk
        foreign key (account_id) references accounts (account_id)
);

INSERT INTO posts (content, create_at, status, title, picture, brief_content, rate, account_id, category_id, type_id) VALUES
('Sample content 1', NOW(), 1, 'Sample title 1', 'sample_picture_1.jpg', 'Sample brief content 1', 5, 1, 1, 1),
('Sample content 2', NOW(), 1, 'Sample title 2', 'sample_picture_2.jpg', 'Sample brief content 2', 5, 1, 1, 1),
('Sample content 3', NOW(), 1, 'Sample title 3', 'sample_picture_3.jpg', 'Sample brief content 3', 5, 1, 1, 1),
('Sample content 4', NOW(), 1, 'Sample title 4', 'sample_picture_4.jpg', 'Sample brief content 4', 5, 1, 1, 1),
('Sample content 5', NOW(), 1, 'Sample title 5', 'sample_picture_5.jpg', 'Sample brief content 5', 5, 1, 1, 1),
('Sample content 6', NOW(), 1, 'Sample title 6', 'sample_picture_6.jpg', 'Sample brief content 6', 5, 1, 1, 1),
('Sample content 7', NOW(), 1, 'Sample title 7', 'sample_picture_7.jpg', 'Sample brief content 7', 5, 1, 1, 1),
('Sample content 8', NOW(), 1, 'Sample title 8', 'sample_picture_8.jpg', 'Sample brief content 8', 5, 1, 1, 1),
('Sample content 9', NOW(), 1, 'Sample title 9', 'sample_picture_9.jpg', 'Sample brief content 9', 5, 1, 1, 1),
('Sample content 10', NOW(), 1, 'Sample title 10', 'sample_picture_10.jpg', 'Sample brief content 10', 5, 1, 1, 1);


INSERT INTO comments (post_id, account_id, content, comment_at) VALUES
        (1, 1, 'Sample comment 1', NOW()),
        (2, 1, 'Sample comment 2', NOW()),
        (3, 1, 'Sample comment 3', NOW()),
        (4, 2, 'Sample comment 4', NOW()),
        (5, 1, 'Sample comment 5', NOW()),
        (6, 2, 'Sample comment 6', NOW()),
        (7, 1, 'Sample comment 7', NOW()),
        (8, 2, 'Sample comment 8', NOW()),
        (9, 1, 'Sample comment 9', NOW()),
        (10, 1, 'Sample comment 10', NOW());
