
CREATE OR REPLACE FUNCTION AMISDBA.FN_ZZ_GET_DEPT_NM
(
  p_dept_cd        IN     VARCHAR2 
, p_name_tp        IN     VARCHAR2 DEFAULT 'K'
) 
RETURN VARCHAR2 

IS

  v_dept_nm         VARCHAR2(100);
   
BEGIN
      
  SELECT /* SQLID=FN_ZZ_GET_DEPT_NM.SQL_01 */ 
         CASE WHEN p_name_tp = 'E' THEN DP.DEPT_ENG_NM    --부서영문명
              WHEN p_name_tp = 'A' THEN DP.DEPT_ABRV_NM   --부서약어명 
              ELSE DP.PCOST_DEPT_NM                       --원가부서명
         END       -- 부서명
    INTO v_dept_nm
    FROM BPCOMDEPT DP
   WHERE DP.PCOST_DEPT_CD  = p_dept_cd;

  RETURN v_dept_nm;
 
EXCEPTION 
  WHEN OTHERS THEN
    RETURN NULL;
  
END;
/

GRANT EXECUTE ON AMISDBA.FN_ZZ_GET_DEPT_NM TO RL_AMIS_RO;