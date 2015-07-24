alter table users
modify (
	password varchar2(100)
);

alter table users
add (
	accountNonExpired number(1) not null,
	credentialsNonExpired number(1) not null,
	accountNonLocked number(1) not null,
	givenName varchar2(50) not null,
	surname varchar2(50) not null,
	mail varchar2(50) not null,
	mobile varchar2(50) not null,
	graceLoginsRemaining number(1) not null
);