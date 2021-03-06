CREATE TABLE ODE_JOB (
    JOBID CHAR(64) NOT NULL, 
    TS NUMERIC NOT NULL, 
    NODEID char(64) NULL, 
    SCHEDULED int NOT NULL, 
    TRANSACTED int NOT NULL, 
    DETAILS BINARY(4096) NULL, 
    PRIMARY KEY(JOBID));
CREATE INDEX IDX_ODE_JOB_TS ON ode_job(ts);
CREATE INDEX IDX_ODE_JOB_NODEID ON ode_job(nodeid);