/*
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.entity.sdo;

import io.vizend.accent.domain.entity.CreationDataObject;
import io.vizend.accent.util.json.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class EhebmcdscCdo extends CreationDataObject {

    //시나리오상황코드
    private String scenCircumCd;

    //훈련유형코드
    private String dscpTypCd;

    //시나리오명
    private String scenNm;

    //정렬순서
    private Integer sortSeq;

    //사용여부
    private String useYn;

    public EhebmcdscCdo() {
        //
        this.useYn = "Y";
    }

    @Override
    public String genId() {
        // 
        return super.genId();
    }

    @Override
    public String toString() {
        // 
        return toJson();
    }

    public static EhebmcdscCdo fromJson(String json) {
        // 
        return JsonUtil.fromJson(json, EhebmcdscCdo.class);
    }

    public static EhebmcdscCdo sample() {
        // 
        return new EhebmcdscCdo();
    }

    public static void main(String[] args) {
        // 
        System.out.println(sample().toPrettyJson());
    }
}
