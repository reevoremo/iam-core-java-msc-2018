CREATE TABLE TEST.IDENTITIES
    (IDENTITY_ID INT NOT NULL GENERATED ALWAYS AS IDENTITY
    CONSTRAINT IDENTITY_PK PRIMARY KEY, 
    IDENTITY_DISPLAYNAME VARCHAR(55),
    IDENTITY_EMAIL VARCHAR(55),
    IDENTITY_UID VARCHAR(55)
    );
 
CREATE TABLE TEST.USERS (USER_ID INT NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT USER_PK PRIMARY KEY, USER_NAME VARCHAR(55) UNIQUE, USER_PASSWORD VARCHAR(32));

INSERT INTO TEST.USERS (USER_NAME, USER_PASSWORD) VALUES ('admin', '21232f297a57a5a743894a0e4a801fc3');