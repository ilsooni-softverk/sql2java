package kr.amc.amis.ca.eh.ch.entity;

import java.time.LocalDateTime;

import kr.amc.amil.message.dto.AbstractDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DscpEvalBrkdwnDTO extends AbstractDTO {

    /**
     * EHS계획관리일련번호
     */
    private Long ehsPlanManageSn;

    /**
     * EHS세부계획관리일련번호
     */
    private Long ehsDtlPlanManageSn;

    /**
     * 훈련평가문항일련번호
     */
    private Long dscpEvalQuestSn;

    /**
     * 평가항목코드
     */
    private String dscpEvalQuestClauCd;

    /**
     * 평가항목
     */
    private String dscpEvalQuestClauNm;

    /**
     * 세부평가내용
     */
    private String dscpEvalQuestCnte;

    /**
     * 전회평균점수
     */
    private Double prvAvgScor;

    /**
     * 평균점수
     */
    private Double avgScor;

    /**
     * 증감점수
     */
    private Double iadvScor;

    /**
     * 훈련유형코드
     */
    private String dscpTypCd;

    /**
     * 전회 평가점수1
     */
    private Double prvEvalScor1;

    /**
     * 전회 평가점수2
     */
    private Double prvEvalScor2;

    /**
     * 전회 평가점수3
     */
    private Double prvEvalScor3;

    /**
     * 평가점수1
     */
    private Double evalScor1;

    /**
     * 평가점수2
     */
    private Double evalScor2;

    /**
     * 평가점수3
     */
    private Double evalScor3;

    /**
     * 배점시작구간점수
     */
    private Long domStSectScor;

    /**
     * 배점종료구간점수
     */
    private Long domEndSectScor;

    /**
     * 배점
     */
    private String domSectCnte;

    /**
     * 등록자부서
     */
    private String frstEntrDeptNm;

    /**
     * 등록자UNIT
     */
    private String frstEntrUnitNm;
    
    /**
     * 임시저장, 결재요청, 반려 구분값
     */
    private String divNm;

    /* 훈련평가서 - 훈련계획목록 DTO*/
    /**
     * 산업안전보건환경 세부계획관리명
     */
    private String ehsDtlPlanManageNm;

    /**
     * 산업안전보건환경 관리항목번호
     */
    private String ehsManageClauseno;

    /**
     * 훈련일자
     */
    private String dscpDt;

    /**
     * 훈련시작시간
     */
    private String dscpStTm;

    /**
     * 훈련종료시간
     */
    private String dscpEndTm;

    /**
     * 훈련장소코드
     */
    private String dscpLocUseCd;

    /**
     * 훈련장소
     */
    private String dscpLocUseNm;

    /**
     * 훈련사항
     */
    private String dscpSchm;

    /**
     * 훈련내용
     */
    private String dscpCnte;

    /**
     * 훈련유형
     */
    private String dscpTypNm;

    /**
     * 평가자사번1
     */
    private String evalEmpno1;

    /**
     * 평가자사번2
     */
    private String evalEmpno2;

    /**
     * 평가자사번3
     */
    private String evalEmpno3;

    /**
     * 평가자1
     */
    private String evalNm1;

    /**
     * 평가자2
     */
    private String evalNm2;

    /**
     * 평가자3
     */
    private String evalNm3;

    /**
     * 훈련평가서 결재확인일시
     */
    private LocalDateTime evalDocuConfmDtm;

    /**
     * 훈련평가서 결재확인자사번
     */
    private String evalDocuConfmrEmpno;

    /**
     * 훈련평가서 결재확인자
     */
    private String evalDocuConfmrEmpnm;

    /**
     * 훈련평가서 승인일시
     */
    private LocalDateTime evalDocuAuthDtm;

    /**
     * 훈련평가서 승인자사번
     */
    private String evalDocuAuthEmpno;

    /**
     * 훈련평가서 승인자
     */
    private String evalDocuAuthEmpnm;

    /**
     * 훈련평가서 결재진행상태코드
     */
    private String evalDocuAprvalStatCd;

    /**
     * 훈련평가서 결재진행상태
     */
    private String evalDocuAprvalStatNm;
    
    /**
     * 평가서 마감여부
     */
    private String dscpEvalDlineYn;
    
    /**
     * 평가서 마감상태
     */
    private String dscpEvalDlineNm;
    
    /**
     * 평가서 마감일시
     */
    private LocalDateTime dscpEvalDlineDtm;
    
    /**
     * 평가서 마감자사번
     */
    private String dscpEvalDdppEmpno;
    
    /**
     * 평가서 마감자
     */
    private String dscpEvalDdppEmpnm;

    /**
     * 검색시작일자
     */
    private String dscpStDt;

    /**
     * 검색종료일자
     */
    private String dscpEndDt;

    /**
     * 검색 유형코드
     */
    private String searchDscpTypCd;

    /**
     * 검색용 훈련시작일자(상세계획 -> 계획시작일자 사용)
     */
    private String searchPlanStDt;

    /**
     * 검색용 훈련종료일자(상세계획 -> 계획종료일자 사용)
     */
    private String searchPlanEndDt;

    /**
     * 계획일자
     */
    private String planDt;

    /**
     * 계획시작시각
     */
    private String planStTm;

    /**
     * 계획종료시각
     */
    private String planEndTm;

    /**
     * 사용자 부서코드
     */
    private String userDeptCd;

    /**
     * 사용자 Unit코드
     */
    private String userUnitCd;

    /**
     * 체크리스트 마감여부
     */
    private String dscpCklistDlineYn;

    /**
     * 체크리스트 마감상태
     */
    private String dscpCklistDlineNm;

    /**
     * 체크리스트 마감일시
     */
    private LocalDateTime dscpCklistDlineDtm;

    /**
     * 체크리스트 마감자사번
     */
    private String dscpCklistDdppEmpno;

    @Override
    public LocalDateTime getFrstEntrDtm() {
        return super.getFrstEntrDtm();
    }

    @Override
    public String getFrstEntrEmpno() {
        return super.getFrstEntrEmpno();
    }

	@Override
	public LocalDateTime getFinlUpdtDtm() {
		return super.getFinlUpdtDtm();
	}

	@Override
	public String getFinlUpidEmpno() {
		return super.getFinlUpidEmpno();
	}
}
