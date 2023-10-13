create table if not exists contact (
	contact_id int auto_increment primary key ,
	name varchar(50) not null,
    mobileNum Bigint not null,
    email varchar(50) not null,
    subject varchar(50) not null,
    message varchar(50) not null,
    status varchar(20) not null,
    createdAt timestamp not null,
    createdBy varchar(20) not null,
    updatedAt timestamp ,
    updatedBy varchar(20)
);

create table if not exists holidays(
	day varchar(50) not null,
    reason varchar(50) not null,
    type varchar(50) not null,
    createdAt timestamp not null,
    createdBy varchar(20) not null,
    updatedAt timestamp ,
    updatedBy varchar(20)
);