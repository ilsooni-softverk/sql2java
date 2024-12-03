package kr.amc.amis.ca.eh.eb.entity;

import java.time.LocalDateTime;

import kr.amc.amil.message.dto.AbstractDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CrisisCtmsScenCircumBasicDTO extends AbstractDTO {

    /**
     *  훈련유형코드
     */
    private String dscpTypCd;

    /**
     * 시나리오상황코드
     */
    private String scenCircumCd;

    /**
     * 시나리오명
     */
    private String scenNm;

    /**
     * 정렬순서
     */
    private Long sortSeq;

    /**
     * 사용여부
     */
    private String useYn;

    public CrisisCtmsScenCircumBasicDTO(String scenCircumCd) {
        this.scenCircumCd = scenCircumCd;
    }

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

    @Override
    public String toString() {
        return "CrisisCtmsScenCircumBasicDTO [" + "dscpTypCd=" + dscpTypCd + "scenCircumCd=" + scenCircumCd + ", scenNm=" + scenNm + ", sortSeq=" + sortSeq + ", useYn=" + useYn + "]";
    }
}
