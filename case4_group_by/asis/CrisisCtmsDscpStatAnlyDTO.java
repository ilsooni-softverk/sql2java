package kr.amc.amis.ca.eh.ch.entity;

import kr.amc.amil.message.dto.AbstractDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 작성자 : AMIS3
 * 생성일자 : 2017-11-27 오후 3:31:46         .TempDTO
 * 주요테이블 : EHEPDEHPL, , EHCHMCDBS, EHCHRCDIP, EHCHRCDDI
 * 메모 :
 */
@Getter
@Setter
@NoArgsConstructor
public class CrisisCtmsDscpStatAnlyDTO extends AbstractDTO {

    // N   22 EHEPDEHPL : EHS계획관리일련번호
    private Long ehsPlanManageSn;

    // N   22 EHEPDEHPL : EHS세부계획관리일련번호
    private Long ehsDtlPlanManageSn;

    // N  400 EHEPDEHPL : EHS세부계획관리명
    private String ehsDtlPlanManageNm;

    // N    5 EHEPDEHPL : EHS관리항목번호
    private String ehsManageClauseno;

    // Y    8 EHEPDEHPL : 계획일자
    private String planDt;

    // Y    8 EHEPDEHPL : 계획시작일자
    private String planStDt;

    // Y    8 EHEPDEHPL : 계획종료일자
    private String planEndDt;

    // Y    4 EHEPDEHPL : 계획시작시각
    private String planStTm;

    // Y    4 EHEPDEHPL : 계획종료시각
    private String planEndTm;

    // Y    3 EHEPDEHPL : 훈련유형코드
    private String dscpTypCd;

    // Y   60           : 훈련유형명
    private String dscpTypNm;

    // Y 4000 EHEPDEHPL : 훈련근거내용
    private String dscpBasisCnte;

    // Y 1000 EHCHMCDBS : 모니터링결과내용
    private String mntgRsltCnte;

    // Y 4000 EHCHMCDBS : 종합의견
    private String synthOpin;

    // Y 4000 EHCHMCDBS : 특기사항
    private String rmrk1;

    // Y 4000 EHCHMCDBS : 특기사항
    private String rmrk2;

    // N 4000 EHCHRCDIP : 미비사항
    private String incmplSchm;

    // N 4000 EHCHRCDIP : 개선대책내용
    private String impvCntpCnte;

    // Y 4000 EHCHRCDIP : 특기사항
    private String rmrk;

    // Y   60 EHCHRCDDI : 날씨상황명
    private String wthrCircumNm;

    // Y    6 EHCHRCDDI : 발생시각
    private String occurTm;

    // Y    6 EHCHRCDDI : 초기도착시각
    private String initArivlTm;

    // Y    6 EHCHRCDDI : 초기조치시각
    private String initTkactTm;

    // Y    6 EHCHRCDDI : 현장처리완료시각
    private String onstHandlFinshTm;

    // Y    4 EHCHRCDDI : 현장응급조치처리상태코드
    private String onstEmrcyTkactHandlStatCd;

    // Y 4000           :
    private String onstEmrcyTkactHandlStatNm;

    // Y    4 EHCHRCDDI : 필요도구운반처리상태코드
    private String needToolCnvyHandlStatCd;

    // Y 4000           :
    private String needToolCnvyHandlStatNm;

    // Y    4 EHCHRCDDI : 피해지역응급처리상태코드
    private String harmRgnEmrcyHandlStatCd;

    // Y 4000           :
    private String harmRgnEmrcyHandlStatNm;

    // Y    4 EHCHRCDDI : 현장정리처리상태코드
    private String onstArngHandlStatCd;

    // Y 4000           :
    private String onstArngHandlStatNm;

    // Y 4000 EHCHRCDDI : 도구현황
    private String toolStat;

    // Y   22 EHCHRCDDI : 위기대응평가종합점수
    private String crisisCtmsEvalSynthScor;

    // Y 4000 EHCHRCDDI : 검토내용
    private String rvwCnte;

    // 검색용 시작일자
    private String searchPlanStDt;

    // 검색용 종료일자
    private String searchPlanEndDt;

    // 검색용 미비사항유무
    private String searchIncmplYn;

    // 검색용 현장응급조치처리상태
    private String searchOnstEmrcy;

    // 검색용 필요도구운반처리상태
    private String searchNeedTool;

    // 검색용 피해지역응급처리상태
    private String searchHarmRgn;

    // 검색용 현장정리처리상태
    private String searchOnstArng;

    // 검색용 훈련유형코드
    private String searchDscpTypCd;

    // 훈련평가종합점수
    private Long totalSocr;

    // 훈련평가평균점수
    private Double avrTotalSocr;

    // 관리부서코드
    private String manageDeptCd;

    // 관리부서
    private String manageDeptNm;

    // 관리UNIT코드
    private String manageUnitCd;

    // 관리UNIT
    private String manageUnitNm;

    // 훈련연도
    private String dscpYear;
    
    //주관부서코드
    private String chrgDeptCd; 
    
    //주관부서
    private String chrgDeptNm;
    
    //미비사항 작성여부
    private String dscpImpvYn;
    
    /**************************실적관련 Parameter*****************************/
    
    //평균종합점수
    private Double avgTotalScor;
    
    //현장응급조치처리 상태(양호)
    private Long onstEmrScor1;
    
    //현장응급조치처리 상태(불량)
    private Long onstEmrScor2;
    
    //현장응급조치처리 상태(미흡)
    private Long onstEmrScor3;
    
    //현장응급조치처리 상태(미선택)
    private Long onstEmrScor4;
    
    //필요도구운반처리 상태(양호)
    private Long needToolScor1;
    
    //필요도구운반처리 상태(불량)
    private Long needToolScor2;
    
    //필요도구운반처리 상태(미흡)
    private Long needToolScor3;
    
    //필요도구운반처리 상태(미선택)
    private Long needToolScor4;
    
    //2차피해지역응급조치처리 상태(양호)
    private Long scdHarmScor1;
    
    //2차피해지역응급조치처리 상태(불량)
    private Long scdHarmScor2;
    
    //2차피해지역응급조치처리 상태(미흡)
    private Long scdHarmScor3;
    
    //2차피해지역응급조치처리 상태(미선택)
    private Long scdHarmScor4;
    
    //현장정리상태(양호)
    private Long onstArngScor1;
    
    //현장정리상태(불량)
    private Long onstArngScor2;
    
    //현장정리상태(미흡)
    private Long onstArngScor3;
    
    //현장정리상태(미선택)
    private Long onstArngScor4;
    
    //종합점수(90점이상)
    private Long dscpSynthScor1;
    
    //종합점수(70점이상 90점 미만)
    private Long dscpSynthScor2;
    
    //종합점수(50점이상 70점 미만)
    private Long dscpSynthScor3;
    
    //종합점수(50점미만)
    private Long dscpSynthScor4;
    
    //상세계획등록건수
    private Long planRegCnt;
    
    //훈련일지 등록건수
    private Long dscpDirCnt;
    
    //계획대비 훈련일지 작성율
    private Double planPrepAcrsPer;
    

    /**************************검색관련 Parameter*****************************/
    
    // 검색용 관리부서코드
    private String searchDeptCd;

    // 검색용 관리UNIT코드
    private String searchUnitCd;
    
    //검색용 주관부서코드
    private String searchChrgDeptCd;
    
    //검색용 종합점수 
    private String searchTotalScor;
}
