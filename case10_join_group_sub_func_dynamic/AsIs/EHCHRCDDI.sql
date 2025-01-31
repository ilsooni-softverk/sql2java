
CREATE TABLE AMISDBA.EHCHRCDDI
(
    EHS_PLAN_MANAGE_SN              NUMBER(22) NOT NULL,
    EHS_DTL_PLAN_MANAGE_SN          NUMBER(22) NOT NULL,
    DSCP_DT                         VARCHAR2(8) NOT NULL,
    CIRCUM_ASGN_NM                  VARCHAR2(200) NOT NULL,
    WTHR_CIRCUM_NM                  VARCHAR2(60),
    OCCUR_TM                        VARCHAR2(6),
    OCCUR_RGN_LOC_USE_CD            VARCHAR2(5),
    INIT_ARIVL_TM                   VARCHAR2(6),
    INIT_TKACT_TM                   VARCHAR2(6),
    ONST_HANDL_FINSH_TM             VARCHAR2(6),
    ONST_EMRCY_TKACT_HANDL_STAT_CD  VARCHAR2(4),
    NEED_TOOL_CNVY_HANDL_STAT_CD    VARCHAR2(4),
    HARM_RGN_EMRCY_HANDL_STAT_CD    VARCHAR2(4),
    ONST_ARNG_HANDL_STAT_CD         VARCHAR2(4),
    TOOL_STAT                       VARCHAR2(4000),
    CRISIS_CTMS_EVAL_SYNTH_SCOR     NUMBER(5),
    RVW_CNTE                        VARCHAR2(4000),
    ATCH_FILE_ID                    VARCHAR2(16),
    FRST_ENTR_EMPNO                 VARCHAR2(7) NOT NULL,
    FRST_ENTR_DTM                   DATE NOT NULL,
    FINL_UPID_EMPNO                 VARCHAR2(7) NOT NULL,
    FINL_UPDT_DTM                   DATE NOT NULL
)
TABLESPACE TS_ERP_D
INITRANS 5
STORAGE
(
    INITIAL 64K
    NEXT 1M
)
NOCOMPRESS;

CREATE UNIQUE INDEX AMISDBA.PK_EHCHRCDDI
ON AMISDBA.EHCHRCDDI (EHS_PLAN_MANAGE_SN,EHS_DTL_PLAN_MANAGE_SN) 
TABLESPACE TS_ERP_X
STORAGE
(
    INITIAL 64K
    NEXT 1M
);

ALTER TABLE AMISDBA.EHCHRCDDI
ADD CONSTRAINT PK_EHCHRCDDI PRIMARY KEY (EHS_PLAN_MANAGE_SN,EHS_DTL_PLAN_MANAGE_SN);

GRANT SELECT ON AMISDBA.EHCHRCDDI TO RL_AMIS_RO;

COMMENT ON COLUMN AMISDBA.EHCHRCDDI.ATCH_FILE_ID IS '첨부파일ID';
COMMENT ON COLUMN AMISDBA.EHCHRCDDI.CIRCUM_ASGN_NM IS '상황부여명';
COMMENT ON COLUMN AMISDBA.EHCHRCDDI.CRISIS_CTMS_EVAL_SYNTH_SCOR IS '위기대응평가종합점수';
COMMENT ON COLUMN AMISDBA.EHCHRCDDI.DSCP_DT IS '훈련일자';
COMMENT ON COLUMN AMISDBA.EHCHRCDDI.EHS_DTL_PLAN_MANAGE_SN IS 'EHS세부계획관리일련번호';
COMMENT ON COLUMN AMISDBA.EHCHRCDDI.EHS_PLAN_MANAGE_SN IS 'EHS계획관리일련번호';
COMMENT ON COLUMN AMISDBA.EHCHRCDDI.FINL_UPDT_DTM IS '최종수정일시';
COMMENT ON COLUMN AMISDBA.EHCHRCDDI.FINL_UPID_EMPNO IS '최종수정자사번';
COMMENT ON COLUMN AMISDBA.EHCHRCDDI.FRST_ENTR_DTM IS '최초입력일시';
COMMENT ON COLUMN AMISDBA.EHCHRCDDI.FRST_ENTR_EMPNO IS '최초입력자사번';
COMMENT ON COLUMN AMISDBA.EHCHRCDDI.HARM_RGN_EMRCY_HANDL_STAT_CD IS '피해지역응급처리상태코드';
COMMENT ON COLUMN AMISDBA.EHCHRCDDI.INIT_ARIVL_TM IS '초기도착시각';
COMMENT ON COLUMN AMISDBA.EHCHRCDDI.INIT_TKACT_TM IS '초기조치시각';
COMMENT ON COLUMN AMISDBA.EHCHRCDDI.NEED_TOOL_CNVY_HANDL_STAT_CD IS '필요도구운반처리상태코드';
COMMENT ON COLUMN AMISDBA.EHCHRCDDI.OCCUR_RGN_LOC_USE_CD IS '발생지역장소사용코드';
COMMENT ON COLUMN AMISDBA.EHCHRCDDI.OCCUR_TM IS '발생시각';
COMMENT ON COLUMN AMISDBA.EHCHRCDDI.ONST_ARNG_HANDL_STAT_CD IS '현장정리처리상태코드';
COMMENT ON COLUMN AMISDBA.EHCHRCDDI.ONST_EMRCY_TKACT_HANDL_STAT_CD IS '현장응급조치처리상태코드';
COMMENT ON COLUMN AMISDBA.EHCHRCDDI.ONST_HANDL_FINSH_TM IS '현장처리완료시각';
COMMENT ON COLUMN AMISDBA.EHCHRCDDI.RVW_CNTE IS '검토내용';
COMMENT ON COLUMN AMISDBA.EHCHRCDDI.TOOL_STAT IS '도구현황';
COMMENT ON COLUMN AMISDBA.EHCHRCDDI.WTHR_CIRCUM_NM IS '날씨상황명';
COMMENT ON TABLE AMISDBA.EHCHRCDDI IS '훈련일지내역';

CREATE PUBLIC SYNONYM EHCHRCDDI
FOR AMISDBA.EHCHRCDDI;