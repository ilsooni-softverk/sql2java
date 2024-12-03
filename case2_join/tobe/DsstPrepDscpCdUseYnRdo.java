package kr.amc.amis.ehs.feature.base.management.domain.sdo;

import io.vizend.accent.util.json.JsonSerializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DsstPrepDscpCdUseYnRdo implements JsonSerializable {

    private String dscpTypCd;
    private String scenCircumCd;
    private Long circumBhvrKnackSn;

    @Override
    public String toString() {
        return toJson();
    }
}
