
CREATE TABLE AMISDBA.EHCHMCDBS
(
    EHS_PLAN_MANAGE_SN          NUMBER(22) NOT NULL,
    EHS_DTL_PLAN_MANAGE_SN      NUMBER(22) NOT NULL,
    MNTG_RSLT_CNTE              VARCHAR2(1000),
    SYNTH_OPIN                  VARCHAR2(4000),
    RMRK1                       VARCHAR2(4000),
    RMRK2                       VARCHAR2(4000),
    RMRK3                       VARCHAR2(4000),
    DSCP_SCEN_DLINE_YN          VARCHAR2(1) DEFAULT 'N' NOT NULL,
    DSCP_SCEN_DLINE_DTM         DATE,
    DSCP_SCEN_DDPP_EMPNO        VARCHAR2(7),
    DSCP_CKLIST_DLINE_YN        VARCHAR2(1) DEFAULT 'N' NOT NULL,
    DSCP_CKLIST_DLINE_DTM       DATE,
    DSCP_CKLIST_DDPP_EMPNO      VARCHAR2(7),
    DSCP_EVAL_DLINE_YN          VARCHAR2(1) DEFAULT 'N' NOT NULL,
    DSCP_EVAL_DLINE_DTM         DATE,
    DSCP_EVAL_DDPP_EMPNO        VARCHAR2(7),
    DSCP_IMPV_PLAN_DLINE_YN     VARCHAR2(1) DEFAULT 'N' NOT NULL,
    DSCP_IMPV_PLAN_DLINE_DTM    DATE,
    DSCP_IMPV_PLAN_DDPP_EMPNO   VARCHAR2(7),
    EVAL_DOCU_CONFMR_EMPNO      VARCHAR2(7),
    EVAL_DOCU_CONFM_DTM         DATE,
    EVAL_DOCU_AUTH_EMPNO        VARCHAR2(7),
    EVAL_DOCU_AUTH_DTM          DATE,
    EVAL_DOCU_APRVAL_STAT_CD    VARCHAR2(4),
    IMPV_PLAN_CONFMR_EMPNO      VARCHAR2(7),
    IMPV_PLAN_CONFM_DTM         DATE,
    IMPV_PLAN_AUTH_EMPNO        VARCHAR2(7),
    IMPV_PLAN_AUTH_DTM          DATE,
    IMPV_PLAN_APRVAL_STAT_CD    VARCHAR2(4),
    RSLT_RPORT_APRVAL_NO        VARCHAR2(14),
    FRST_ENTR_EMPNO             VARCHAR2(7) NOT NULL,
    FRST_ENTR_DTM               DATE NOT NULL,
    FINL_UPID_EMPNO             VARCHAR2(7) NOT NULL,
    FINL_UPDT_DTM               DATE NOT NULL
)
TABLESPACE TS_MST_D
INITRANS 5
STORAGE
(
    INITIAL 64K
    NEXT 1M
)
NOCOMPRESS;

CREATE UNIQUE INDEX AMISDBA.PK_EHCHMCDBS
ON AMISDBA.EHCHMCDBS (EHS_PLAN_MANAGE_SN,EHS_DTL_PLAN_MANAGE_SN) 
TABLESPACE TS_MST_X
STORAGE
(
    INITIAL 64K
    NEXT 1M
);

ALTER TABLE AMISDBA.EHCHMCDBS
ADD CONSTRAINT PK_EHCHMCDBS PRIMARY KEY (EHS_PLAN_MANAGE_SN,EHS_DTL_PLAN_MANAGE_SN);

GRANT SELECT ON AMISDBA.EHCHMCDBS TO RL_AMIS_RO;

COMMENT ON COLUMN AMISDBA.EHCHMCDBS.DSCP_CKLIST_DDPP_EMPNO IS '훈련점검표마감자사번';
COMMENT ON COLUMN AMISDBA.EHCHMCDBS.DSCP_CKLIST_DLINE_DTM IS '훈련점검표마감일시';
COMMENT ON COLUMN AMISDBA.EHCHMCDBS.DSCP_CKLIST_DLINE_YN IS '훈련점검표마감여부';
COMMENT ON COLUMN AMISDBA.EHCHMCDBS.DSCP_EVAL_DDPP_EMPNO IS '훈련평가마감자사번';
COMMENT ON COLUMN AMISDBA.EHCHMCDBS.DSCP_EVAL_DLINE_DTM IS '훈련평가마감일시';
COMMENT ON COLUMN AMISDBA.EHCHMCDBS.DSCP_EVAL_DLINE_YN IS '훈련평가마감여부';
COMMENT ON COLUMN AMISDBA.EHCHMCDBS.DSCP_IMPV_PLAN_DDPP_EMPNO IS '훈련개선계획마감자사번';
COMMENT ON COLUMN AMISDBA.EHCHMCDBS.DSCP_IMPV_PLAN_DLINE_DTM IS '훈련개선계획마감일시';
COMMENT ON COLUMN AMISDBA.EHCHMCDBS.DSCP_IMPV_PLAN_DLINE_YN IS '훈련개선계획마감여부';
COMMENT ON COLUMN AMISDBA.EHCHMCDBS.DSCP_SCEN_DDPP_EMPNO IS '훈련시나리오마감자사번';
COMMENT ON COLUMN AMISDBA.EHCHMCDBS.DSCP_SCEN_DLINE_DTM IS '훈련시나리오마감일시';
COMMENT ON COLUMN AMISDBA.EHCHMCDBS.DSCP_SCEN_DLINE_YN IS '훈련시나리오마감여부';
COMMENT ON COLUMN AMISDBA.EHCHMCDBS.EHS_DTL_PLAN_MANAGE_SN IS 'EHS세부계획관리일련번호';
COMMENT ON COLUMN AMISDBA.EHCHMCDBS.EHS_PLAN_MANAGE_SN IS 'EHS계획관리일련번호';
COMMENT ON COLUMN AMISDBA.EHCHMCDBS.EVAL_DOCU_APRVAL_STAT_CD IS '평가서결재상태코드';
COMMENT ON COLUMN AMISDBA.EHCHMCDBS.EVAL_DOCU_AUTH_DTM IS '평가서승인일시';
COMMENT ON COLUMN AMISDBA.EHCHMCDBS.EVAL_DOCU_AUTH_EMPNO IS '평가서승인자사번';
COMMENT ON COLUMN AMISDBA.EHCHMCDBS.EVAL_DOCU_CONFMR_EMPNO IS '평가서확인자사번';
COMMENT ON COLUMN AMISDBA.EHCHMCDBS.EVAL_DOCU_CONFM_DTM IS '평가서확인일시';
COMMENT ON COLUMN AMISDBA.EHCHMCDBS.FINL_UPDT_DTM IS '최종수정일시';
COMMENT ON COLUMN AMISDBA.EHCHMCDBS.FINL_UPID_EMPNO IS '최종수정자사번';
COMMENT ON COLUMN AMISDBA.EHCHMCDBS.FRST_ENTR_DTM IS '최초입력일시';
COMMENT ON COLUMN AMISDBA.EHCHMCDBS.FRST_ENTR_EMPNO IS '최초입력자사번';
COMMENT ON COLUMN AMISDBA.EHCHMCDBS.IMPV_PLAN_APRVAL_STAT_CD IS '개선계획결재상태코드';
COMMENT ON COLUMN AMISDBA.EHCHMCDBS.IMPV_PLAN_AUTH_DTM IS '개선계획승인일시';
COMMENT ON COLUMN AMISDBA.EHCHMCDBS.IMPV_PLAN_AUTH_EMPNO IS '개선계획승인자사번';
COMMENT ON COLUMN AMISDBA.EHCHMCDBS.IMPV_PLAN_CONFMR_EMPNO IS '개선계획확인자사번';
COMMENT ON COLUMN AMISDBA.EHCHMCDBS.IMPV_PLAN_CONFM_DTM IS '개선계획확인일시';
COMMENT ON COLUMN AMISDBA.EHCHMCDBS.MNTG_RSLT_CNTE IS '모니터링결과내용';
COMMENT ON COLUMN AMISDBA.EHCHMCDBS.RMRK1 IS '특기사항1';
COMMENT ON COLUMN AMISDBA.EHCHMCDBS.RMRK2 IS '특기사항2';
COMMENT ON COLUMN AMISDBA.EHCHMCDBS.RMRK3 IS '특기사항3';
COMMENT ON COLUMN AMISDBA.EHCHMCDBS.RSLT_RPORT_APRVAL_NO IS '결과보고결재번호';
COMMENT ON COLUMN AMISDBA.EHCHMCDBS.SYNTH_OPIN IS '종합의견';
COMMENT ON TABLE AMISDBA.EHCHMCDBS IS '위기대응훈련평가기본';

CREATE PUBLIC SYNONYM EHCHMCDBS
FOR AMISDBA.EHCHMCDBS;