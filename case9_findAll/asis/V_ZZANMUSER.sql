
CREATE OR REPLACE VIEW AMISDBA.V_ZZANMUSER
(
    USR_ID,
    USR_PWD,
    PIN_NO,
    LOGIN_USR_TYP_CD,
    UNMP_TYP_CD,
    UNMP_TYP_NM,
    UNMP_OUTSD_INSTT_CD,
    UNMP_OUTSD_INSTT_NM,
    KOR_NM,
    ALIAS,
    NAME_WITH_ALIAS,
    ENG_NM,
    BIRTHDT,
    SEX_DIV_CD,
    PHOTO_FILE_ID,
    PHOTO_FILE_NO,
    MBPH_NO,
    MBPH_NO_OPEN_YN,
    INHOSP_TELNO,
    EMAL_ADDR,
    FAX_NO,
    PGR_NO,
    BLNG_DEPT_CD,
    BLNG_DEPT_NM,
    BLNG_UNIT_CD,
    BLNG_UNIT_NM,
    REAL_WORK_DEPT_CD,
    REAL_WORK_DEPT_NM,
    REAL_WORK_UNIT_CD,
    REAL_WORK_UNIT_NM,
    PCOST_DEPT_CD,
    PCOST_DEPT_NM,
    REAL_WORK_LOC_CD,
    ENTCOM_DT,
    RESIGN_DT,
    EMPL_DIV_CD,
    JOBGP_CD,
    JOBPOS_CD,
    JOBGR_CD,
    LOCP_CD,
    SOCP_CD,
    HLOG_DIV_CD,
    RLS_POSBL_ORGZ_LVL_CD,
    USE_YN,
    MDEX_JOBTY_CD,
    DR_JOBTY_CD,
    DR_JOBTY_SEQ,
    MR_JOBTY_CD,
    TASK_ORGZ_CD,
    FINL_UPDT_DTM,
    SUPVISN_DEPT_CD,
    SUPVISN_DEPT_NM,
    PWD_CHNG_DT,
    PWD_RPET_ENTR_CNT
)
AS
SELECT UR.USR_ID                                                   --사용자ID
     , UR.USR_PWD                                                  --사용자PASSWORD
	 , UR.PIN_NO                                                   --PIN_NO추가
     , UR.LOGIN_USR_TYP_CD                                         --로그인사용자유형코드
     , UR.UNMP_TYP_CD                                              --비고용직유형코드
     , FN_ZZ_GET_CD_NM('HR0259', UR.UNMP_TYP_CD) AS UNMP_TYP_NM    --비고용직유형명
     , UR.UNMP_OUTSD_INSTT_CD                                      --비고용직외부기관코드
     , (SELECT UNMP_OUTSD_INSTT_NM
          FROM HRPOMNOOR   /* 비고용직외부기관기본 */
          WHERE USE_YN='Y'
            AND UNMP_OUTSD_INSTT_CD = UR.UNMP_OUTSD_INSTT_CD
        ) AS UNMP_OUTSD_INSTT_NM								--비고용직외부기관명
     , UR.KOR_NM                                                   --한글성명
     , UR.ALIAS                                                    --별칭
     , UR.KOR_NM || UR.ALIAS AS NAME_WITH_ALIAS                    --이름 + 별칭
     , UR.ENG_NM                                                   --영문성명
     , UR.BIRTHDT                                                  --생년월일
     , UR.SEX_DIV_CD                                               --성별구분코드
     , UR.PHOTO_FILE_ID                                            --사진파일ID
     , UR.PHOTO_FILE_NO                                            --사진파일번호
     , UR.MBPH_NO                                                  --휴대전화번호
     , UR.MBPH_NO_OPEN_YN                                          --휴대전화번호공개여부
     , UR.INHOSP_TELNO                                             --원내전화번호
     , UR.EMAL_ADDR                                                --e-mail주소
     , UR.FAX_NO                                                   --팩스번호
     , UR.PGR_NO                                                   --페이저번호
     , UR.BLNG_DEPT_CD                                             --소속부서코드
     , (SELECT PCOST_DEPT_NM
          FROM BPCOMDEPT
         WHERE PCOST_DEPT_CD = UR.BLNG_DEPT_CD
       ) AS BLNG_DEPT_NM                                           --소속부서명
     , UR.BLNG_UNIT_CD                                             --소속UNIT코드
     , (SELECT PCOST_DEPT_NM
          FROM BPCOMDEPT 
         WHERE PCOST_DEPT_CD = UR.BLNG_UNIT_CD
        ) AS BLNG_UNIT_NM                                          --소속UNIT명
     , UR.REAL_WORK_DEPT_CD                                        --실근무부서코드
     , (SELECT PCOST_DEPT_NM
          FROM BPCOMDEPT
         WHERE PCOST_DEPT_CD = UR.REAL_WORK_DEPT_CD
       ) AS REAL_WORK_DEPT_NM                                      --실근무부서명
     , UR.REAL_WORK_UNIT_CD                                        --실근무UNIT코드
     , (SELECT PCOST_DEPT_NM 
          FROM BPCOMDEPT 
         WHERE PCOST_DEPT_CD = UR.REAL_WORK_UNIT_CD
       ) AS REAL_WORK_UNIT_NM                                      --실근무UNIT명
     , UR.PCOST_DEPT_CD                                            --원가부서코드
     , (SELECT PCOST_DEPT_NM 
          FROM BPCOMDEPT 
         WHERE PCOST_DEPT_CD = UR.PCOST_DEPT_CD
       ) AS PCOST_DEPT_NM                                          --원가부서명
     , UR.REAL_WORK_LOC_CD                                         --실근무장소코드
     , UR.ENTCOM_DT                                                --입사일자
     , UR.RESIGN_DT                                                --사직일자
     , UR.EMPL_DIV_CD                                              --고용구분코드
     , UR.JOBGP_CD                                                 --직군코드
     , UR.JOBPOS_CD                                                --직위코드
     , UR.JOBGR_CD                                                 --직급코드
     , UR.LOCP_CD                                                  --대직종코드
     , UR.SOCP_CD                                                  --소직종코드
     , UR.HLOG_DIV_CD                                              --재직구분코드
     , UR.RLS_POSBL_ORGZ_LVL_CD                                    --배포가능조직레벨코드
     , UR.USE_YN                                                   --사용여부
     , FN_ZZ_GET_MDEX_JOBTY_CD(LOCP_CD, SOCP_CD) AS MDEX_JOBTY_CD  --진료직종코드
     , CC.FRTH_ADD_CD_VAL AS DR_JOBTY_CD                           --의사직종코드
     , CASE WHEN CC.FRTH_ADD_CD_VAL = 'S' THEN 1
            WHEN FRTH_ADD_CD_VAL IS NOT NULL THEN 2
            ELSE NULL
       END AS DR_JOBTY_SEQ                                         --의사직종순서
     , FN_ZZ_GET_MR_JOBTY_CD (LOCP_CD, SOCP_CD, UR.JOBGP_CD, CC.FRTH_ADD_CD_VAL) AS MR_JOBTY_CD   --진료기록직종코드
	 , UR.TASK_ORGZ_CD                                             --업무조직코드
     , UR.FINL_UPDT_DTM                                            --최종수정일시
     , UR.SUPVISN_DEPT_CD										   --주관부서코드
     , (SELECT PCOST_DEPT_NM
          FROM BPCOMDEPT
         WHERE PCOST_DEPT_CD = UR.SUPVISN_DEPT_CD
       ) AS SUPVISN_DEPT_NM                                        --주관부서명
	 , UR.PWD_CHNG_DT											   --패스워드변경일자
	 , UR.PWD_RPET_ENTR_CNT                                        --패스워드반복입력건수
  FROM ZZANMUSER UR
     , ZZANDCDVL CC
 WHERE UR.SOCP_CD = CC.CD_VAL(+)
   AND CC.CD_NO(+) = 'EI0143'
  WITH READ ONLY;

COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.ALIAS IS '별칭';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.BIRTHDT IS '생년월일';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.BLNG_DEPT_CD IS '소속부서코드';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.BLNG_DEPT_NM IS '소속부서명';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.BLNG_UNIT_CD IS '소속UNIT코드';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.BLNG_UNIT_NM IS '소속UNIT명';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.DR_JOBTY_CD IS '의사직종코드';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.DR_JOBTY_SEQ IS '의사직종순서';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.EMAL_ADDR IS 'e-mail주소';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.EMPL_DIV_CD IS '고용구분코드';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.ENG_NM IS '영문성명';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.ENTCOM_DT IS '입사일자';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.FAX_NO IS '팩스번호';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.HLOG_DIV_CD IS '재직구분코드';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.INHOSP_TELNO IS '원내전화번호';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.JOBGP_CD IS '직군코드';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.JOBGR_CD IS '직급코드';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.JOBPOS_CD IS '직위코드';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.KOR_NM IS '한글성명';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.LOCP_CD IS '대직종코드';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.LOGIN_USR_TYP_CD IS '로그인사용자유형코드';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.MBPH_NO IS '휴대전화번호';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.MBPH_NO_OPEN_YN IS '휴대전화번호공개여부';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.MDEX_JOBTY_CD IS '진료직종코드';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.MR_JOBTY_CD IS '진료기록직종코드';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.NAME_WITH_ALIAS IS '이름+별칭';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.PCOST_DEPT_CD IS '원가부서코드';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.PCOST_DEPT_NM IS '원가부서명';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.PGR_NO IS '페이저번호';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.PHOTO_FILE_ID IS '사진파일ID';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.PHOTO_FILE_NO IS '사진파일번호';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.PIN_NO IS 'PIN번호';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.PWD_CHNG_DT IS '패스워드변경일자';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.PWD_RPET_ENTR_CNT IS '패스워드반복입력건수';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.REAL_WORK_DEPT_CD IS '실근무부서코드';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.REAL_WORK_DEPT_NM IS '실근무부서명';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.REAL_WORK_LOC_CD IS '실근무장소코드';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.REAL_WORK_UNIT_CD IS '실근무UNIT코드';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.REAL_WORK_UNIT_NM IS '실근무UNIT명';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.RESIGN_DT IS '사직일자';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.RLS_POSBL_ORGZ_LVL_CD IS '배포가능조직레벨코드';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.SEX_DIV_CD IS '성별구분코드';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.SOCP_CD IS '소직종코드';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.SUPVISN_DEPT_CD IS '주관부서코드';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.SUPVISN_DEPT_NM IS '주관부서명';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.TASK_ORGZ_CD IS '업무조직코드';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.UNMP_OUTSD_INSTT_CD IS '비고용직외부기관코드';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.UNMP_OUTSD_INSTT_NM IS '비고용직외부기관명';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.UNMP_TYP_CD IS '비고용직유형코드';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.UNMP_TYP_NM IS '비고용직유형명';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.USE_YN IS '사용여부';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.USR_ID IS '사용자ID';
COMMENT ON COLUMN AMISDBA.V_ZZANMUSER.USR_PWD IS '사용자PASSWORD';
COMMENT ON TABLE AMISDBA.V_ZZANMUSER IS '사용자기본_뷰';

GRANT SELECT ON AMISDBA.V_ZZANMUSER TO RL_AMIS_RO;