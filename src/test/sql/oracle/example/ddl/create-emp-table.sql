CREATE TABLE EMP (
		EMPNO NUMBER(4 , 0) NOT NULL,
		ENAME VARCHAR2(10),
		JOB VARCHAR2(9),
		MGR NUMBER(4 , 0),
		HIREDATE DATE,
		SAL NUMBER(7 , 2),
		COMM NUMBER(7 , 2),
		DEPTNO NUMBER(2 , 0)
	);

CREATE UNIQUE INDEX PK_EMP ON EMP (EMPNO ASC);

ALTER TABLE EMP ADD CONSTRAINT PK_EMP PRIMARY KEY (EMPNO);

ALTER TABLE EMP ADD CONSTRAINT FK_DEPTNO FOREIGN KEY (DEPTNO)
	REFERENCES DEPT (DEPTNO)
	ON DELETE RESTRICT
	ON UPDATE CASCADE;