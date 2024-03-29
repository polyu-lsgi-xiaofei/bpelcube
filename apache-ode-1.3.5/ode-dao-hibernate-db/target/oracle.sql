create table ODE_SCHEMA_VERSION(VERSION integer);
insert into ODE_SCHEMA_VERSION values (6);
-- Apache ODE - SimpleScheduler Database Schema
-- 
-- Apache Derby scripts by Maciej Szefler.
-- 
-- 

DROP TABLE ode_job;

CREATE TABLE ode_job (
  jobid VARCHAR(64)  NOT NULL,
  ts number(37)  NOT NULL,
  nodeid varchar(64),
  scheduled int  NOT NULL,
  transacted int  NOT NULL,
  
  instanceId number(37),
  mexId varchar(255),
  processId varchar(255),
  type varchar(255),
  channel varchar(255),
  correlatorId varchar(255),
  correlationKeySet varchar(255),
  retryCount int,
  inMem int,
  detailsExt blob,

  PRIMARY KEY(jobid));

CREATE INDEX IDX_ODE_JOB_TS ON ode_job(ts);
CREATE INDEX IDX_ODE_JOB_NODEID ON ode_job(nodeid);


create table BPEL_ACTIVITY_RECOVERY (ID number(19,0) not null, PIID number(19,0), AID number(19,0), CHANNEL varchar2(255 char), REASON varchar2(255 char), DATE_TIME timestamp, DETAILS blob(2G), ACTIONS varchar2(255 char), RETRIES number(10,0), INSERT_TIME timestamp, MLOCK number(10,0) not null, primary key (ID));
create table BPEL_CORRELATION_PROP (ID number(19,0) not null, NAME varchar2(255 char), NAMESPACE varchar2(255 char), VALUE varchar2(255 char), CORR_SET_ID number(19,0), INSERT_TIME timestamp, MLOCK number(10,0) not null, primary key (ID));
create table BPEL_CORRELATION_SET (ID number(19,0) not null, VALUE varchar2(255 char), CORR_SET_NAME varchar2(255 char), SCOPE_ID number(19,0), PIID number(19,0), PROCESS_ID number(19,0), INSERT_TIME timestamp, MLOCK number(10,0) not null, primary key (ID));
create table BPEL_CORRELATOR (ID number(19,0) not null, CID varchar2(255 char), PROCESS_ID number(19,0), INSERT_TIME timestamp, MLOCK number(10,0) not null, primary key (ID));
create table BPEL_CORRELATOR_MESSAGE_CKEY (ID number(19,0) not null, CKEY varchar2(255 char), CORRELATOR_MESSAGE_ID number(19,0), INSERT_TIME timestamp, MLOCK number(10,0) not null, primary key (ID));
create table BPEL_EVENT (ID number(19,0) not null, IID number(19,0), PID number(19,0), TSTAMP timestamp, TYPE varchar2(255 char), DETAIL clob, DATA blob(2G), SID number(19,0), INSERT_TIME timestamp, MLOCK number(10,0) not null, primary key (ID));
create table BPEL_FAULT (ID number(19,0) not null, FAULTNAME varchar2(255 char), DATA blob(2G), EXPLANATION varchar2(4000 char), LINE_NUM number(10,0), AID number(10,0), INSERT_TIME timestamp, MLOCK number(10,0) not null, primary key (ID));
create table BPEL_INSTANCE (ID number(19,0) not null, INSTANTIATING_CORRELATOR number(19,0), FAULT number(19,0), JACOB_STATE_DATA blob(2G), PREVIOUS_STATE number(5,0), PROCESS_ID number(19,0), STATE number(5,0), LAST_ACTIVE_DT timestamp, SEQUENCE number(19,0), FAILURE_COUNT number(10,0), FAILURE_DT timestamp, INSERT_TIME timestamp, MLOCK number(10,0) not null, primary key (ID));
create table BPEL_MESSAGE (ID number(19,0) not null, MEX number(19,0), TYPE varchar2(255 char), MESSAGE_DATA blob(2G), MESSAGE_HEADER blob(2G), INSERT_TIME timestamp, MLOCK number(10,0) not null, primary key (ID));
create table BPEL_MESSAGE_EXCHANGE (ID number(19,0) not null, PORT_TYPE varchar2(255 char), CHANNEL_NAME varchar2(255 char), CLIENTKEY varchar2(255 char), ENDPOINT blob(2G), CALLBACK_ENDPOINT blob(2G), REQUEST number(19,0), RESPONSE number(19,0), INSERT_DT timestamp, OPERATION varchar2(255 char), STATE varchar2(255 char), PROCESS number(19,0), PIID number(19,0), DIR char(1 char), PLINK_MODELID number(10,0), PATTERN varchar2(255 char), CORR_STATUS varchar2(255 char), FAULT_TYPE varchar2(255 char), FAULT_EXPL varchar2(255 char), CALLEE varchar2(255 char), PARTNERLINK number(19,0), PIPED_ID varchar2(255 char), SUBSCRIBER_COUNT number(10,0), INSERT_TIME timestamp, MLOCK number(10,0) not null, primary key (ID));
create table BPEL_MEX_PROPS (MEX number(19,0) not null, VALUE long, NAME varchar2(255 char) not null, primary key (MEX, NAME));
create table BPEL_PLINK_VAL (ID number(19,0) not null, PARTNER_LINK varchar2(100 char) not null, PARTNERROLE varchar2(100 char), MYROLE_EPR_DATA blob(2G), PARTNERROLE_EPR_DATA blob(2G), PROCESS number(19,0), SCOPE number(19,0), SVCNAME varchar2(255 char), MYROLE varchar2(100 char), MODELID number(10,0), MYSESSIONID varchar2(255 char), PARTNERSESSIONID varchar2(255 char), INSERT_TIME timestamp, MLOCK number(10,0) not null, primary key (ID));
create table BPEL_PROCESS (ID number(19,0) not null, PROCID varchar2(255 char) not null unique, deployer varchar2(255 char), deploydate timestamp, type_name varchar2(255 char), type_ns varchar2(255 char), version number(19,0), ACTIVE_ number(1,0), guid varchar2(255 char), INSERT_TIME timestamp, MLOCK number(10,0) not null, primary key (ID));
create table BPEL_SCOPE (ID number(19,0) not null, PIID number(19,0), PARENT_SCOPE_ID number(19,0), STATE varchar2(255 char) not null, NAME varchar2(255 char) not null, MODELID number(10,0), INSERT_TIME timestamp, MLOCK number(10,0) not null, primary key (ID));
create table BPEL_SELECTORS (ID number(19,0) not null, PIID number(19,0) not null, SELGRPID varchar2(255 char) not null, IDX number(10,0) not null, CORRELATION_KEY varchar2(255 char) not null, PROC_TYPE varchar2(255 char) not null, ROUTE_POLICY varchar2(255 char), CORRELATOR number(19,0) not null, INSERT_TIME timestamp, MLOCK number(10,0) not null, primary key (ID), unique (CORRELATION_KEY, CORRELATOR));
create table BPEL_UNMATCHED (ID number(19,0) not null, MEX number(19,0), CORRELATION_KEY varchar2(255 char), CORRELATOR number(19,0) not null, INSERT_TIME timestamp, MLOCK number(10,0) not null, primary key (ID));
create table BPEL_XML_DATA (ID number(19,0) not null, DATA blob(2G), NAME varchar2(255 char) not null, SIMPLE_VALUE varchar2(255 char), SCOPE_ID number(19,0), PIID number(19,0), IS_SIMPLE_TYPE number(1,0), INSERT_TIME timestamp, MLOCK number(10,0) not null, primary key (ID));
create table STORE_DU (NAME varchar2(255 char) not null, deployer varchar2(255 char), DEPLOYDT timestamp, DIR varchar2(255 char), primary key (NAME));
create table STORE_PROCESS (PID varchar2(255 char) not null, DU varchar2(255 char), TYPE varchar2(255 char), version number(19,0), STATE varchar2(255 char), primary key (PID));
create table STORE_PROCESS_PROP (propId varchar2(255 char) not null, value varchar2(255 char), name varchar2(255 char) not null, primary key (propId, name));
create table STORE_VERSIONS (ID number(10,0) not null, VERSION number(19,0), primary key (ID));
create table VAR_PROPERTY (ID number(19,0) not null, XML_DATA_ID number(19,0), PROP_VALUE varchar2(255 char), PROP_NAME varchar2(255 char) not null, INSERT_TIME timestamp, MLOCK number(10,0) not null, primary key (ID));
create index IDX_CORRELATOR_CID on BPEL_CORRELATOR (CID);
create index IDX_BPEL_CORRELATOR_MESSAGE_CKEY on BPEL_CORRELATOR_MESSAGE_CKEY (CKEY);
create index IDX_SELECTOR_SELGRPID on BPEL_SELECTORS (SELGRPID);
create index IDX_SELECTOR_CKEY on BPEL_SELECTORS (CORRELATION_KEY);
create index IDX_SELECTOR_CORRELATOR on BPEL_SELECTORS (CORRELATOR);
create index IDX_UNMATCHED_CORRELATOR on BPEL_UNMATCHED (CORRELATOR);
create index IDX_UNMATCHED_CKEY on BPEL_UNMATCHED (CORRELATION_KEY);
create sequence hibernate_seqhilo;
