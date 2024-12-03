package kr.amc.amis.ca.eh.eb.entity;

import java.time.LocalDateTime;

import kr.amc.amil.message.dto.AbstractDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PreDsmsManageDTO extends AbstractDTO {

    /**
     * 안전관리자유형코드
     */
    private String safeMngrTypCd;

    /**
     * 사번
     */
    private String empno;

    /**
     * 선임일자
     */
    private String nmntDt;

    /**
     * 해임일자
     */
    private String dsmsDt;

    /**
     * 선임승인여부
     */
    private String nmntAuthYn;

    /**
     * 선임승인일자
     */
    private String nmntAuthDt;

    /**
     * 해임승인여부
     */
    private String dsmsAuthYn;

    /**
     * 해임승인일자
     */
    private String dsmsAuthDt;

    /**
     * 자격증번호
     */
    private String qlcrNo;

    /**
     * 자격증유효기간일자
     */
    private String qlcrVlprDt;

    /**
     * 선임담당유형코드
     */
    private String nmntChrgTypCd;

    /**
     * 특기사항
     */
    private String rmrk;

    /**
     * 주민등록번호
     */
    private String rgno;

    private String frstEntrEmpnm;

    private String finlUpidEmpnm;

    /* 선해임내역 추가 2017-06-02 */
    private String empnm;

    // PK
    private String nmntDsmsSn;

    // 확인/승인/반려 여부
    private String workState;

    // 선임확인일자
    private String nmntConfmDt;

    // 선임확인자성명
    private String nmntConfmrNm;

    // 선임승인자성명
    private String nmntAuthNm;

    // 해임확인일자
    private String dsmsConfmDt;

    // 해임확인자명
    private String dsmsConfmrNm;

    // 해임승인자명
    private String dsmsAuthNm;

    // 업체명
    private String compNm;

    // 대상자성명
    private String targterNm;

    // 선임해임진행상태코드
    private String nmntDsmsProgrsStatCd;

    private String blngUnitCd;

    private String blngUnitNm;

    // 법률근거내용
    private String actBasisCnte;

    // 관련기관명
    private String relInsttNm;

    private String nmntStDt;

    private String nmntEndDt;

    private String dsmsStDt;

    private String dsmsEndDt;

    private String nmntDsms;

    private String deptCd;

    private String deptNm;

    private String unitCd;

    private String unitNm;

    /* 자격면허 대상자 2017-06-02*/
    private Long sn;

    private String quafiLicnsCd;

    private String quafiLicnsNo;

    private String quafiSchmBrkdwn;

    private String acqsDt;

    private String issuInsttNm;

    private String mdexUseLicnsYn;

    private String licnsClsfCd;

    private String dtlCdNm;

    private String blngDeptCd;

    private String blngDeptNm;

    private String qlcrNm;

    // 거래처명
    private String actcustNm;

    /* 보수교육내역 2017-06-02 */
    private String cneduKndCd;

    private String cneduKndNm;

    private String sbjtNm;

    private String eduStDt;

    private String eduEndDt;

    private String eduYear;

    private String turno;

    private String turnoNm;

    private String finishYn;

    private Long inhospEduExpn;

    private Long eduExpn;

    private String dtlLocNm;

    // 분류유형 정규직/비정규직/전체 조회 여부
    private String preDsmsTrgetClsf;

    // 로그인관련정보
    private String loginUsrTypCd;
    
  //페이지 크기
    private Long pageSize;

    public PreDsmsManageDTO(String safeMngrTypCd, String empno, String nmntDt) {
        this.safeMngrTypCd = safeMngrTypCd;
        this.empno = empno;
        this.nmntDt = nmntDt;
    }

    /**
     * 분류유형 setter 메소드
     * @param preDsmsTrgetClsf, 분류유형
     */
    /**
     * 분류유형 setter 메소드
     * @param safeMngrTypCd, 분류유형
     */
    /**
     * 점검시작일자
     */
    private String inspectStDt;

    /**
     * 점검종료일자
     */
    private String inspectEndDt;

    /**
     * 최초등록자사번 getter 메소드
     * @return frstRgisterEmpno, 최초등록자사번
     */
    @Override
    public String getFrstEntrEmpno() {
        return super.getFrstEntrEmpno();
    }

    /**
     * 최초등록일시 getter 메소드
     * @return frstRegDtm, 최초등록일시
     */
    @Override
    public LocalDateTime getFrstEntrDtm() {
        return super.getFrstEntrDtm();
    }

    /**
     * 최종수정자사번 getter 메소드
     * @return FinlUpidEmpno, 최종수정자사번
     */
    @Override
    public String getFinlUpidEmpno() {
        return super.getFinlUpidEmpno();
    }

    /**
     * 최종수정일시 getter 메소드
     * @return FinlUpdtDtm, 최종수정일시
     */
    @Override
    public LocalDateTime getFinlUpdtDtm() {
        return super.getFinlUpdtDtm();
    }

    @Override
    public String toString() {
        return "PreDsmsManageDTO [safeMngrTypCd=" + safeMngrTypCd + ", empno=" + empno + ", nmntDt=" + nmntDt + ", dsmsDt=" + dsmsDt + ", nmntAuthYn=" + nmntAuthYn + ", nmntAuthDt=" + nmntAuthDt + ", dsmsAuthYn=" + dsmsAuthYn + ", dsmsAuthDt=" + dsmsAuthDt + ", qlcrNo=" + qlcrNo + ", qlcrVlprDt=" + qlcrVlprDt + ", nmntChrgTypCd=" + nmntChrgTypCd + ", rmrk=" + rmrk + ", rgno=" + rgno + ", frstEntrEmpnm=" + frstEntrEmpnm + ", finlUpidEmpnm=" + finlUpidEmpnm + ", empnm=" + empnm + ", nmntDsmsSn=" + nmntDsmsSn + ", workState=" + workState + ", nmntConfmDt=" + nmntConfmDt + ", nmntConfmrNm=" + nmntConfmrNm + ", nmntAuthNm=" + nmntAuthNm + ", dsmsConfmDt=" + dsmsConfmDt + ", dsmsConfmrNm=" + dsmsConfmrNm + ", dsmsAuthNm=" + dsmsAuthNm + ", compNm=" + compNm + ", targterNm=" + targterNm + ", nmntDsmsProgrsStatCd=" + nmntDsmsProgrsStatCd + ", blngUnitCd=" + blngUnitCd + ", blngUnitNm=" + blngUnitNm + ", actBasisCnte=" + actBasisCnte + ", relInsttNm=" + relInsttNm + ", nmntStDt=" + nmntStDt + ", nmntEndDt=" + nmntEndDt + ", dsmsStDt=" + dsmsStDt + ", dsmsEndDt=" + dsmsEndDt + ", nmntDsms=" + nmntDsms + ", deptCd=" + deptCd + ", deptNm=" + deptNm + ", unitCd=" + unitCd + ", unitNm=" + unitNm + ", sn=" + sn + ", quafiLicnsCd=" + quafiLicnsCd + ", quafiLicnsNo=" + quafiLicnsNo + ", quafiSchmBrkdwn=" + quafiSchmBrkdwn + ", acqsDt=" + acqsDt + ", issuInsttNm=" + issuInsttNm + ", mdexUseLicnsYn=" + mdexUseLicnsYn + ", licnsClsfCd=" + licnsClsfCd + ", dtlCdNm=" + dtlCdNm + ", blngDeptCd=" + blngDeptCd + ", blngDeptNm=" + blngDeptNm + ", qlcrNm=" + qlcrNm + ", actcustNm=" + actcustNm + ", cneduKndCd=" + cneduKndCd + ", cneduKndNm=" + cneduKndNm + ", sbjtNm=" + sbjtNm + ", eduStDt=" + eduStDt + ", eduEndDt=" + eduEndDt + ", eduYear=" + eduYear + ", turno=" + turno + ", turnoNm=" + turnoNm + ", finishYn=" + finishYn + ", inhospEduExpn=" + inhospEduExpn + ", eduExpn=" + eduExpn + ", dtlLocNm=" + dtlLocNm + ", preDsmsTrgetClsf=" + preDsmsTrgetClsf + ", loginUsrTypCd=" + loginUsrTypCd + ", inspectStDt=" + inspectStDt + ", inspectEndDt=" + inspectEndDt + "]";
    }
}
