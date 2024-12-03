/* 
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package kr.amc.amis.ehs.feature.disasterpreparedness.training.extract;

import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.custom.sdo.EhchmcdbsUniqueKey;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.custom.sdo.EhchrcddiUniqueKey;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.custom.store.EhchmcdbsCustomStore;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.custom.store.EhchrcddiCustomStore;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.entity.Ehchrcddi;
import kr.amc.amis.ehs.aggregate.plan.custom.store.EhepdehplCustomStore;
import kr.amc.amis.ehs.aggregate.plan.domain.entity.Ehepdehpl;
import kr.amc.amis.ehs.feature.disasterpreparedness.training.domain.sdo.DisciplineTypeActualResultRdo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RetrieveCrisisCtmsDscpTypByAcrsExtract {
    private final EhepdehplCustomStore ehepdehplCustomStore;
    private final EhchmcdbsCustomStore ehchmcdbsCustomStore;
    private final EhchrcddiCustomStore ehchrcddiCustomStore;


    public DisciplineTypeActualResultRdo retrieveCrisisCtmsDscpTypByAcrs(String dscpTypCd, String searchPlanStDt, String searchPlanEndDt) {
        List<Ehepdehpl> ehepdehpls = ehepdehplCustomStore.retrieveByPlanDtGoeAndPlanDtLoeAndDscpTypCd(
                searchPlanStDt, searchPlanEndDt, dscpTypCd);

        long planRegCnt = ehepdehpls.stream().filter(ehepdehpl -> "00007".equals(ehepdehpl.getEhsManageClauseno())).count();

        List<EhchmcdbsUniqueKey> ehchmcdbsUniqueKeys = ehepdehpls.stream()
                .map(ehepdehpl -> new EhchmcdbsUniqueKey(ehepdehpl.getEhsPlanManageSn(), ehepdehpl.getEhsDtlPlanManageSn()))
                .toList();
        List<EhchmcdbsUniqueKey> ehchmcdbsResultUniqueKeys = ehchmcdbsCustomStore.existsByEhchmcdbsUniqueKeys(ehchmcdbsUniqueKeys);

        List<EhchrcddiUniqueKey> ehchrcddiUniqueKeys = ehchmcdbsResultUniqueKeys.stream()
                .map(ehchmcdbsUniqueKey -> new EhchrcddiUniqueKey(
                        ehchmcdbsUniqueKey.getEhsPlanManageSn(), ehchmcdbsUniqueKey.getEhsDtlPlanManageSn()))
                .toList();

        List<Ehchrcddi> ehchrcddis = ehchrcddiCustomStore.retrieveByEhchrcddiUniqueKeyIn(ehchrcddiUniqueKeys);
        if (ehchrcddis.isEmpty()) {
            return null;
        }
        return new DisciplineTypeActualResultRdo(planRegCnt, ehchrcddis);
    }

}
