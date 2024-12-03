package kr.amc.amis.ca.eh.ch.entity;

import java.time.LocalDateTime;

import kr.amc.amil.message.dto.AbstractDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DscpChkListBrkdwnDTO extends AbstractDTO {

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
     * 시나리오상황명
     */
    private String scenNm;

    /**
     * 일련번호
     */
    private Long sn;

    /**
     * 행동요령내용
     */
    private String bhvrKnackCnte;

    /**
     * 행동시각
     */
    private String bhvrTm;

    /**
     * 담당자사번
     */
    private String usrEmpno;

    /**
     * 담당자
     */
    private String usrEmpnm;

    /**
     * 특기사항
     */
    private String rmrk;

    /**
     * 체크리스트 마감여부
     */
    private String dscpCklistDlineYn;

    /**
     * 체크리스트 마감일시
     */
    private LocalDateTime dscpCklistDlineDtm;

    /**
     * 체크리스트 마감자사번
     */
    private String dscpCklistDdppEmpno;

    /**
     * 등록자부서
     */
    private String frstEntrDeptNm;

    /**
     * 등록자UNIT
     */
    private String frstEntrUnitNm;

    /**
     * 시나리오상황코드
     */
    private String scenCircumCd;

    /**
     * 훈련유형코드
     */
    private String dscpTypCd;
    
    //정렬순서
    private Long sortSeq;
    
    //훈련평가서 조회 용 행동시각
    private String readBhvrTm;

	@Override
	public LocalDateTime getFinlUpdtDtm() {
		return super.getFinlUpdtDtm();
	}

	@Override
	public String getFinlUpidEmpno() {
		return super.getFinlUpidEmpno();
	}

	@Override
	public LocalDateTime getFrstEntrDtm() {
		return super.getFrstEntrDtm();
	}

	@Override
	public String getFrstEntrEmpno() {
		return super.getFrstEntrEmpno();
	}
}
