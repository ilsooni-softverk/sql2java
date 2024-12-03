/* 
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package kr.amc.amis.ehs.feature.disasterpreparedness.training.extract;

import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.custom.sdo.EhchrcdclUniqueKey;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.entity.Ehchrcdcl;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.logic.EhchrcdclLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class DeleteDscpChkListBrkdwnExtract {
    private final EhchrcdclLogic ehchrcdclLogic;

    public List<String> deleteDscpChkListBrkdwn(List<EhchrcdclUniqueKey> ehchrcdclDeleteUniqueKeys) {
        List<String> entityIds = new ArrayList<>();
        if (ehchrcdclDeleteUniqueKeys == null || ehchrcdclDeleteUniqueKeys.isEmpty()) {
            return entityIds;
        }

        for (EhchrcdclUniqueKey ehchrcdclDeleteUniqueKey : ehchrcdclDeleteUniqueKeys) {
            Long sn = ehchrcdclDeleteUniqueKey.getSn();
            List<Ehchrcdcl> deleteEhchrcdcls = new ArrayList<>();
            if (sn == null) {
                deleteEhchrcdcls.addAll(
                        ehchrcdclLogic.findByEhsPlanManageSnAndEhsDtlPlanManageSnAndCircumBhvrKnackSn(
                                ehchrcdclDeleteUniqueKey.getEhsPlanManageSn(), ehchrcdclDeleteUniqueKey.getEhsDtlPlanManageSn(), ehchrcdclDeleteUniqueKey.getCircumBhvrKnackSn()));
            } else {
                deleteEhchrcdcls.add(
                        ehchrcdclLogic.findByEhsPlanManageSnAndEhsDtlPlanManageSnAndCircumBhvrKnackSnAndSn(
                                ehchrcdclDeleteUniqueKey.getEhsPlanManageSn(), ehchrcdclDeleteUniqueKey.getEhsDtlPlanManageSn(), ehchrcdclDeleteUniqueKey.getCircumBhvrKnackSn(), sn));
            }

            List<String> removeIds = deleteEhchrcdcls.stream().filter(Objects::nonNull).map(ehchrcdcl -> {
                String ehchrcdclId = ehchrcdcl.getId();
                ehchrcdclLogic.removeEhchrcdcl(ehchrcdclId);
                return ehchrcdclId;
            }).toList();
            entityIds.addAll(removeIds);
        }
        return entityIds;
    }

}
