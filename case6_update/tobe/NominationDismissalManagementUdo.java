package kr.amc.amis.ehs.feature.base.management.domain.sdo;

import io.vizend.accent.util.json.JsonSerializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NominationDismissalManagementUdo implements JsonSerializable {

    private String empno;
    private String compNm;
    private String nmntDt;
    private String dsmsDt;
    private String qlcrNo;
    private String qlcrVlprDt;
    private String nmntChrgTypCd;
    private String nmntDsmsProgrsStatCd;
    private String rmrk;
    private String actBasisCnte;
    private String relInsttNm;
    private Long nmntDsmsSn;

    @Override
    public String toString() {
        return toJson();
    }
}
