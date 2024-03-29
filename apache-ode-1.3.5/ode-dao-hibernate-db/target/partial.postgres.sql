create table BPEL_ACTIVITY_RECOVERY (ID int8 not null, PIID int8, AID int8, CHANNEL varchar(255), REASON varchar(255), DATE_TIME timestamp, DETAILS blob(2G), ACTIONS varchar(255), RETRIES int4, INSERT_TIME timestamp, MLOCK int4 not null, primary key (ID));
create table BPEL_CORRELATION_PROP (ID int8 not null, NAME varchar(255), NAMESPACE varchar(255), VALUE varchar(255), CORR_SET_ID int8, INSERT_TIME timestamp, MLOCK int4 not null, primary key (ID));
create table BPEL_CORRELATION_SET (ID int8 not null, VALUE varchar(255), CORR_SET_NAME varchar(255), SCOPE_ID int8, PIID int8, PROCESS_ID int8, INSERT_TIME timestamp, MLOCK int4 not null, primary key (ID));
create table BPEL_CORRELATOR (ID int8 not null, CID varchar(255), PROCESS_ID int8, INSERT_TIME timestamp, MLOCK int4 not null, primary key (ID));
create table BPEL_CORRELATOR_MESSAGE_CKEY (ID int8 not null, CKEY varchar(255), CORRELATOR_MESSAGE_ID int8, INSERT_TIME timestamp, MLOCK int4 not null, primary key (ID));
create table BPEL_EVENT (ID int8 not null, IID int8, PID int8, TSTAMP timestamp, TYPE varchar(255), DETAIL text, DATA blob(2G), SID int8, INSERT_TIME timestamp, MLOCK int4 not null, primary key (ID));
create table BPEL_FAULT (ID int8 not null, FAULTNAME varchar(255), DATA blob(2G), EXPLANATION varchar(4000), LINE_NUM int4, AID int4, INSERT_TIME timestamp, MLOCK int4 not null, primary key (ID));
create table BPEL_INSTANCE (ID int8 not null, INSTANTIATING_CORRELATOR int8, FAULT int8, JACOB_STATE_DATA blob(2G), PREVIOUS_STATE int2, PROCESS_ID int8, STATE int2, LAST_ACTIVE_DT timestamp, SEQUENCE int8, FAILURE_COUNT int4, FAILURE_DT timestamp, INSERT_TIME timestamp, MLOCK int4 not null, primary key (ID));
create table BPEL_MESSAGE (ID int8 not null, MEX int8, TYPE varchar(255), MESSAGE_DATA blob(2G), MESSAGE_HEADER blob(2G), INSERT_TIME timestamp, MLOCK int4 not null, primary key (ID));
create table BPEL_MESSAGE_EXCHANGE (ID int8 not null, PORT_TYPE varchar(255), CHANNEL_NAME varchar(255), CLIENTKEY varchar(255), ENDPOINT blob(2G), CALLBACK_ENDPOINT blob(2G), REQUEST int8, RESPONSE int8, INSERT_DT timestamp, OPERATION varchar(255), STATE varchar(255), PROCESS int8, PIID int8, DIR char(1), PLINK_MODELID int4, PATTERN varchar(255), CORR_STATUS varchar(255), FAULT_TYPE varchar(255), FAULT_EXPL varchar(255), CALLEE varchar(255), PARTNERLINK int8, PIPED_ID varchar(255), SUBSCRIBER_COUNT int4, INSERT_TIME timestamp, MLOCK int4 not null, primary key (ID));
create table BPEL_MEX_PROPS (MEX int8 not null, VALUE varchar(8000), NAME varchar(255) not null, primary key (MEX, NAME));
create table BPEL_PLINK_VAL (ID int8 not null, PARTNER_LINK varchar(100) not null, PARTNERROLE varchar(100), MYROLE_EPR_DATA blob(2G), PARTNERROLE_EPR_DATA blob(2G), PROCESS int8, SCOPE int8, SVCNAME varchar(255), MYROLE varchar(100), MODELID int4, MYSESSIONID varchar(255), PARTNERSESSIONID varchar(255), INSERT_TIME timestamp, MLOCK int4 not null, primary key (ID));
create table BPEL_PROCESS (ID int8 not null, PROCID varchar(255) not null unique, deployer varchar(255), deploydate timestamp, type_name varchar(255), type_ns varchar(255), version int8, ACTIVE_ bool, guid varchar(255), INSERT_TIME timestamp, MLOCK int4 not null, primary key (ID));
create table BPEL_SCOPE (ID int8 not null, PIID int8, PARENT_SCOPE_ID int8, STATE varchar(255) not null, NAME varchar(255) not null, MODELID int4, INSERT_TIME timestamp, MLOCK int4 not null, primary key (ID));
create table BPEL_SELECTORS (ID int8 not null, PIID int8 not null, SELGRPID varchar(255) not null, IDX int4 not null, CORRELATION_KEY varchar(255) not null, PROC_TYPE varchar(255) not null, ROUTE_POLICY varchar(255), CORRELATOR int8 not null, INSERT_TIME timestamp, MLOCK int4 not null, primary key (ID), unique (CORRELATION_KEY, CORRELATOR));
create table BPEL_UNMATCHED (ID int8 not null, MEX int8, CORRELATION_KEY varchar(255), CORRELATOR int8 not null, INSERT_TIME timestamp, MLOCK int4 not null, primary key (ID));
create table BPEL_XML_DATA (ID int8 not null, DATA blob(2G), NAME varchar(255) not null, SIMPLE_VALUE varchar(255), SCOPE_ID int8, PIID int8, IS_SIMPLE_TYPE bool, INSERT_TIME timestamp, MLOCK int4 not null, primary key (ID));
create table STORE_DU (NAME varchar(255) not null, deployer varchar(255), DEPLOYDT timestamp, DIR varchar(255), primary key (NAME));
create table STORE_PROCESS (PID varchar(255) not null, DU varchar(255), TYPE varchar(255), version int8, STATE varchar(255), primary key (PID));
create table STORE_PROCESS_PROP (propId varchar(255) not null, value varchar(255), name varchar(255) not null, primary key (propId, name));
create table STORE_VERSIONS (ID int4 not null, VERSION int8, primary key (ID));
create table VAR_PROPERTY (ID int8 not null, XML_DATA_ID int8, PROP_VALUE varchar(255), PROP_NAME varchar(255) not null, INSERT_TIME timestamp, MLOCK int4 not null, primary key (ID));
create index IDX_CORRELATOR_CID on BPEL_CORRELATOR (CID);
create index IDX_BPEL_CORRELATOR_MESSAGE_CKEY on BPEL_CORRELATOR_MESSAGE_CKEY (CKEY);
create index IDX_SELECTOR_SELGRPID on BPEL_SELECTORS (SELGRPID);
create index IDX_SELECTOR_CKEY on BPEL_SELECTORS (CORRELATION_KEY);
create index IDX_SELECTOR_CORRELATOR on BPEL_SELECTORS (CORRELATOR);
create index IDX_UNMATCHED_CORRELATOR on BPEL_UNMATCHED (CORRELATOR);
create index IDX_UNMATCHED_CKEY on BPEL_UNMATCHED (CORRELATION_KEY);
create sequence hibernate_seqhilo;
