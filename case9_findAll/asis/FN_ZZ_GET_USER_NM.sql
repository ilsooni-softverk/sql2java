
CREATE OR REPLACE FUNCTION AMISDBA.FN_ZZ_GET_USER_NM
(
  p_usr_id         IN     VARCHAR2
, p_name_tp        IN     VARCHAR2 DEFAULT 'K'
)
RETURN VARCHAR2

IS

  v_usr_nm  VARCHAR2(100) := NULL;

BEGIN

    SELECT  /*+ SQLID=FN_ZZ_GET_USER_NM.SQL_01 */
            CASE 
                WHEN p_name_tp IN ('K', 'KI') AND US.ALIAS IS NOT NULL THEN US.KOR_NM || '(' || US.ALIAS || ')'
                WHEN p_name_tp IN ('K', 'KI') AND US.ALIAS IS NULL THEN US.KOR_NM
                WHEN p_name_tp IN ('E', 'EI') AND US.ALIAS IS NOT NULL THEN US.ENG_NM || '(' || US.ALIAS || ')'
                WHEN p_name_tp IN ('E', 'EI') AND US.ALIAS IS NULL THEN US.ENG_NM
            ELSE US.KOR_NM
            END AS USR_NM
    INTO v_usr_nm
    FROM ZZANMUSER US
    WHERE US.USR_ID = p_usr_id;

    RETURN v_usr_nm;

EXCEPTION
    WHEN OTHERS THEN

    IF (p_name_tp = 'KI' OR p_name_tp = 'EI') THEN
       RETURN p_usr_id;
    ELSE RETURN NULL;
    END IF;

END;
/

GRANT EXECUTE ON AMISDBA.FN_ZZ_GET_USER_NM TO RL_AMIS_RO;