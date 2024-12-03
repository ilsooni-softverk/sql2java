package kr.amc.amis.ca.eh.ch.entity;

import java.time.LocalDateTime;

import kr.amc.amil.message.dto.AbstractDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CrisisCtmsScenDtlDTO extends AbstractDTO {

    /**
     * EHS계획관리일련번호
     */
    private Long ehsPlanManageSn;

    /**
     * EHS세부계획관리일련번호
     */
    private Long ehsDtlPlanManageSn;

    /**
     * 상황행동요령일련번호
     */
    private Long circumBhvrKnackSn;

    /**
     * 담당자사번
     */
    private String usrEmpno;

    /**
     * 담당자 성명
     */
    private String usrEmpnm;

    /**
     * 특기사항
     */
    private String rmrk;

    private String scenNm;

    /**
     * 훈련유형코드
     */
    private String dscpTypCd;

    /**
     * 훈련유형명
     */
    private String dscpTypNm;
    
    /**
     * 시나리오상황 코드 
     */
    private String scenCircumCd;
    
    /**
     * 사용여부
     */
    private String useYn;

    /**
     * 관리부서코드
     */
    private String manageDeptCd;

    /**
     * 관리unit코드
     */
    private String manageUnitCd;

    /**
     * 주관부서코드
     */
    private String supvisnDeptCd;

    /**
     * 등록자부서
     */
    private String frstEntrDeptNm;

    /**
     * 등록자unit
     */
    private String frstEntrUnitNm;

    /**
     * 마감자사번
     */
    private String dscpScenDdppEmpno;

    /**
     * 마감자
     */
    private String dscpScenDdppEmpnm;

    /**
     * 마감일자
     */
    private LocalDateTime dscpScenDlineDtm;

    /**
     * 마감여부(Cd)
     */
    private String dscpScenDlineYn;

    /**
     * 마감상태
     */
    private String dscpScenDlineNm;

    private String circumBhvrKnackNm;
    
    //정렬순서
    private Long sortSeq;
    
    //검색용 훈련유형코드
    private String searchDscpTypCd;
    
    //검색용 시나리오상황코드
    private String searchScenCd;
    
    //검색용 상황별행동요령코드
    private Long searchCircumCd;
    
    //구분값
    private String searchDivNm;

    public CrisisCtmsScenDtlDTO(Long ehsPlanManageSn, Long ehsDtlPlanManageSn, Long circumBhvrKnackSn) {
        this.ehsPlanManageSn = ehsPlanManageSn;
        this.ehsDtlPlanManageSn = ehsDtlPlanManageSn;
        this.circumBhvrKnackSn = circumBhvrKnackSn;
    }

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
