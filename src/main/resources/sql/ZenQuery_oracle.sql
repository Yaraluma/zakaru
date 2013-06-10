--------------------------------------------------------
--  DDL for Sequence DATABASE_CONNECTIONS_SQ
--------------------------------------------------------

CREATE SEQUENCE  "CARCREDIT_ADM_CIT"."DATABASE_CONNECTIONS_SQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence QUERIES_SQ
--------------------------------------------------------

CREATE SEQUENCE  "CARCREDIT_ADM_CIT"."QUERIES_SQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence QUERY_VERSIONS_SQ
--------------------------------------------------------

CREATE SEQUENCE  "CARCREDIT_ADM_CIT"."QUERY_VERSIONS_SQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Table DATABASE_CONNECTIONS
--------------------------------------------------------

CREATE TABLE "CARCREDIT_ADM_CIT"."DATABASE_CONNECTIONS"
(	"ID" NUMBER,
   "NAME" VARCHAR2(200 CHAR),
   "URL" VARCHAR2(2048 CHAR),
   "USERNAME" VARCHAR2(200 CHAR),
   "PASSWORD" VARCHAR2(200 CHAR)
) SEGMENT CREATION IMMEDIATE
PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255
  NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
TABLESPACE "CARCREDIT_ADM_CIT_DATA" ;
--------------------------------------------------------
--  DDL for Table QUERIES
--------------------------------------------------------

CREATE TABLE "CARCREDIT_ADM_CIT"."QUERIES"
(	"ID" NUMBER,
   "KEY" VARCHAR2(256 BYTE),
   "DATABASE_CONNECTION_ID" NUMBER
) SEGMENT CREATION IMMEDIATE
PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255
  NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
TABLESPACE "CARCREDIT_ADM_CIT_DATA" ;
--------------------------------------------------------
--  DDL for Table QUERY_VERSIONS
--------------------------------------------------------

CREATE TABLE "CARCREDIT_ADM_CIT"."QUERY_VERSIONS"
(	"ID" NUMBER,
   "VERSION" NUMBER,
   "IS_CURRENT_VERSION" NUMBER,
   "QUERY_ID" NUMBER,
   "CONTENT" CLOB
) SEGMENT CREATION IMMEDIATE
PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255
  NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "CARCREDIT_ADM_CIT_DATA"
  LOB ("CONTENT") STORE AS BASICFILE (
TABLESPACE "CARCREDIT_ADM_CIT_DATA" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION
NOCACHE LOGGING
STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) ;
--------------------------------------------------------
--  DDL for Index DATABASE_CONNECTIONS_PK
--------------------------------------------------------

CREATE UNIQUE INDEX "CARCREDIT_ADM_CIT"."DATABASE_CONNECTIONS_PK" ON "CARCREDIT_ADM_CIT"."DATABASE_CONNECTIONS" ("ID")
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
TABLESPACE "CARCREDIT_ADM_CIT_DATA" ;
--------------------------------------------------------
--  DDL for Index QUERIES_PK
--------------------------------------------------------

CREATE UNIQUE INDEX "CARCREDIT_ADM_CIT"."QUERIES_PK" ON "CARCREDIT_ADM_CIT"."QUERIES" ("ID")
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
TABLESPACE "CARCREDIT_ADM_CIT_DATA" ;
--------------------------------------------------------
--  DDL for Index QUERY_VERSIONS_PK
--------------------------------------------------------

CREATE UNIQUE INDEX "CARCREDIT_ADM_CIT"."QUERY_VERSIONS_PK" ON "CARCREDIT_ADM_CIT"."QUERY_VERSIONS" ("ID")
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
TABLESPACE "CARCREDIT_ADM_CIT_DATA" ;
--------------------------------------------------------
--  Constraints for Table DATABASE_CONNECTIONS
--------------------------------------------------------

ALTER TABLE "CARCREDIT_ADM_CIT"."DATABASE_CONNECTIONS" ADD CONSTRAINT "DATABASE_CONNECTIONS_PK" PRIMARY KEY ("ID")
USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
TABLESPACE "CARCREDIT_ADM_CIT_DATA"  ENABLE;
ALTER TABLE "CARCREDIT_ADM_CIT"."DATABASE_CONNECTIONS" MODIFY ("PASSWORD" NOT NULL ENABLE);
ALTER TABLE "CARCREDIT_ADM_CIT"."DATABASE_CONNECTIONS" MODIFY ("USERNAME" NOT NULL ENABLE);
ALTER TABLE "CARCREDIT_ADM_CIT"."DATABASE_CONNECTIONS" MODIFY ("URL" NOT NULL ENABLE);
ALTER TABLE "CARCREDIT_ADM_CIT"."DATABASE_CONNECTIONS" MODIFY ("NAME" NOT NULL ENABLE);
ALTER TABLE "CARCREDIT_ADM_CIT"."DATABASE_CONNECTIONS" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table QUERIES
--------------------------------------------------------

ALTER TABLE "CARCREDIT_ADM_CIT"."QUERIES" ADD CONSTRAINT "QUERIES_PK" PRIMARY KEY ("ID")
USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
TABLESPACE "CARCREDIT_ADM_CIT_DATA"  ENABLE;
ALTER TABLE "CARCREDIT_ADM_CIT"."QUERIES" MODIFY ("DATABASE_CONNECTION_ID" NOT NULL ENABLE);
ALTER TABLE "CARCREDIT_ADM_CIT"."QUERIES" MODIFY ("KEY" NOT NULL ENABLE);
ALTER TABLE "CARCREDIT_ADM_CIT"."QUERIES" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table QUERY_VERSIONS
--------------------------------------------------------

ALTER TABLE "CARCREDIT_ADM_CIT"."QUERY_VERSIONS" ADD CONSTRAINT "QUERY_VERSIONS_PK" PRIMARY KEY ("ID")
USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
TABLESPACE "CARCREDIT_ADM_CIT_DATA"  ENABLE;
ALTER TABLE "CARCREDIT_ADM_CIT"."QUERY_VERSIONS" MODIFY ("QUERY_ID" NOT NULL ENABLE);
ALTER TABLE "CARCREDIT_ADM_CIT"."QUERY_VERSIONS" MODIFY ("IS_CURRENT_VERSION" NOT NULL ENABLE);
ALTER TABLE "CARCREDIT_ADM_CIT"."QUERY_VERSIONS" MODIFY ("VERSION" NOT NULL ENABLE);
ALTER TABLE "CARCREDIT_ADM_CIT"."QUERY_VERSIONS" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Ref Constraints for Table QUERIES
--------------------------------------------------------

ALTER TABLE "CARCREDIT_ADM_CIT"."QUERIES" ADD CONSTRAINT "QUERIES_FKEY" FOREIGN KEY ("ID")
REFERENCES "CARCREDIT_ADM_CIT"."DATABASE_CONNECTIONS" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table QUERY_VERSIONS
--------------------------------------------------------

ALTER TABLE "CARCREDIT_ADM_CIT"."QUERY_VERSIONS" ADD CONSTRAINT "QUERY_VERSIONS_QUERIES_FK1" FOREIGN KEY ("ID")
REFERENCES "CARCREDIT_ADM_CIT"."QUERIES" ("ID") ENABLE;
--------------------------------------------------------
--  DDL for Trigger CREATE_DATABASE_CONNECTIONS_PK
--------------------------------------------------------

CREATE OR REPLACE TRIGGER "CARCREDIT_ADM_CIT"."CREATE_DATABASE_CONNECTIONS_PK"
before insert on "CARCREDIT_ADM_CIT"."DATABASE_CONNECTIONS"
for each row
begin
if inserting then
if :NEW."ID" is null then
select DATABASE_CONNECTIONS_SQ.nextval into :NEW."ID" from dual;
end if;
end if;
end;

/
ALTER TRIGGER "CARCREDIT_ADM_CIT"."CREATE_DATABASE_CONNECTIONS_PK" ENABLE;
--------------------------------------------------------
--  DDL for Trigger CREATE_QUERIES_PK
--------------------------------------------------------

CREATE OR REPLACE TRIGGER "CARCREDIT_ADM_CIT"."CREATE_QUERIES_PK"
before insert on "CARCREDIT_ADM_CIT"."QUERIES"
for each row
begin
if inserting then
if :NEW."ID" is null then
select QUERIES_SQ.nextval into :NEW."ID" from dual;
end if;
end if;
end;

/
ALTER TRIGGER "CARCREDIT_ADM_CIT"."CREATE_QUERIES_PK" ENABLE;
--------------------------------------------------------
--  DDL for Trigger CREATE_QUERY_VERSIONS_PK
--------------------------------------------------------

CREATE OR REPLACE TRIGGER "CARCREDIT_ADM_CIT"."CREATE_QUERY_VERSIONS_PK"
before insert on "CARCREDIT_ADM_CIT"."QUERY_VERSIONS"
for each row
begin
if inserting then
if :NEW."ID" is null then
select QUERY_VERSIONS_SQ.nextval into :NEW."ID" from dual;
end if;
end if;
end;

/
ALTER TRIGGER "CARCREDIT_ADM_CIT"."CREATE_QUERY_VERSIONS_PK" ENABLE;
