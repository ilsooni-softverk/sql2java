package kr.amc.amis.ehs.feature.base.management.domain.sdo;

import io.vizend.accent.util.json.JsonSerializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EhsManageCodeBasicSdo implements JsonSerializable {

    private String ehsManageCd;
    private String ehsManageCdNm;
    private String ehsManageUprCd;
    private String useYn;
    private String searchEhsManageCd;
    private String divNm;

    public EhsManageCodeBasicSdo(String ehsManageUprCd) {
        //
        this.ehsManageUprCd = ehsManageUprCd;
    }

    @Override
    public String toString() {
        return toJson();
    }
}
