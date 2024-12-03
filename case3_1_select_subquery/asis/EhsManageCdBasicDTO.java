package kr.amc.amis.ca.eh.eb.entity;

import kr.amc.amil.message.dto.AbstractDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EhsManageCdBasicDTO extends AbstractDTO {

    /**
     * EHS관리코드
     */
    private String ehsManageCd;

    /**
     * EHS관리코드명
     */
    private String ehsManageCdNm;

    /**
     * EHS관리상위코드
     */
    private String ehsManageUprCd;

    /**
     * 변경전 EHS관리코드
     */
    private String prvEhsManageCd;

    /**
     * 정렬순서
     */
    private Long sortSeq;

    /**
     * 특기사항
     */
    private String rmrk;

    /**
     * 사용여부
     */
    private String useYn;

    /**
     * 칼럼명
     */
    private String comnNm;

    /**
     *검색용 searchDivCd
     */
    private String searchDivCd;

    /**
     * 등록일시
     */
    private String frstEntrDt;

    /**
     * 수정일시
     */
    private String finlUpdtDt;

    // 1번째추가코드값
    private String frstAddCdVal;

    // 1번째추가코드설명
    private String frstAddCdDesc;

    // 2번째추가코드값
    private String sconAddCdVal;

    // 2번째추가코드설명
    private String sconAddCdDesc;

    // 3번째추가코드값
    private String thirAddCdVal;

    // 3번째추가코드설명
    private String thirAddCdDesc;
    
    // 4번째추가코드값
    private String frthAddCdVal;
    
    // 4번째추가코드설명
    private String frthAddCdDesc;

    // 타프로그램 검색전용 코드
    private String searchEhsManageCd;
    
    //공통코드 및 분류정보사용 구분값
    private String divNm;

    public EhsManageCdBasicDTO(String ehsManageCd) {
        this.ehsManageCd = ehsManageCd;
    }

    @Override
    public String toString() {
        return "EhsManageCdBasicDTO [" + "ehsManageCd=" + ehsManageCd + ", prvEhsManageCd=" + prvEhsManageCd + ", ehsManageCdNm=" + ehsManageCdNm + ", ehsManageUprCd=" + ehsManageUprCd + ", sortSeq=" + sortSeq + ", rmrk=" + rmrk + ", useYn=" + useYn + ", comnNm=" + comnNm + ", searchDivCd=" + searchDivCd + "]";
    }

    /**
     * 상위코드명
     */
    private String ehsManageUprNm;

    @Override
    public String getFinlUpidEmpno() {
        return super.getFinlUpidEmpno();
    }

    @Override
    public String getFrstEntrEmpno() {
        return super.getFrstEntrEmpno();
    }
}
