package kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.custom.sdo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EhchrcdclUniqueKey {
    //EHS계획관리일련번호
    private Long ehsPlanManageSn;
    //EHS세부계획관리일련번호
    private Long ehsDtlPlanManageSn;
    //상황행동요령일련번호
    private Long circumBhvrKnackSn;
    //일련번호
    private Long sn;

    public EhchrcdclUniqueKey(EhchrcdclPlanCircum planCircumGroupKey, Long sn) {
        this.ehsPlanManageSn = planCircumGroupKey.getEhsPlanManageSn();
        this.ehsDtlPlanManageSn = planCircumGroupKey.getEhsDtlPlanManageSn();
        this.circumBhvrKnackSn = planCircumGroupKey.getCircumBhvrKnackSn();
        this.sn = sn;
    }
}
