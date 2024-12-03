/* 
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package kr.amc.amis.ehs.feature.disasterpreparedness.training.extract;

import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.custom.sdo.EhchmcdbsUniqueKey;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.custom.store.EhchmcdbsCustomStore;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.entity.Ehchmcdbs;
import kr.amc.amis.ehs.feature.disasterpreparedness.training.domain.sdo.DisciplineBasicRdo;
import kr.amc.amis.ehs.proxy.query.zzanquery.an.zzanmuser.ZzanmuserSeekProxy;
import kr.amc.amis.ehs.proxy.query.zzanquery.an.zzanmuser.domain.entity.Zzanmuser;
import kr.amc.amis.ehs.proxy.query.bpbpquery.co.bpcomdept.BpcomdeptSeekProxy;
import kr.amc.amis.ehs.proxy.query.bpbpquery.co.bpcomdept.domain.entity.Bpcomdept;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RetrieveCrisisCtmsDscpBasicListExtract {

    private final EhchmcdbsCustomStore ehchmcdbsCustomStore;

    private final ZzanmuserSeekProxy zzanmuserSeekProxy;
    private final BpcomdeptSeekProxy bpcomdeptSeekProxy;


    public List<DisciplineBasicRdo> retrieveCrisisCtmsDscpBasicList(Long ehsPlanManageSn, Long ehsDtlPlanManageSn) {

        List<Ehchmcdbs> ehchmcdbss = ehchmcdbsCustomStore.retrieveByEhchmcdbsUniqueKey(new EhchmcdbsUniqueKey(ehsPlanManageSn, ehsDtlPlanManageSn));

        List<String> registeredEmpnos = ehchmcdbss.stream().map(Ehchmcdbs::getRegisteredBy).distinct().toList();
        List<Zzanmuser> zzanmusers = zzanmuserSeekProxy.retrieveZzanmuserList(registeredEmpnos);
        Map<String, Zzanmuser> zzanmuserMap = zzanmusers.stream().collect(Collectors.toMap(Zzanmuser::getUsrId, Function.identity()));

        Set<String> pcostDeptCds = new HashSet<>();
        pcostDeptCds.addAll(zzanmusers.stream().map(Zzanmuser::getRealWorkDeptCd).filter(Objects::nonNull).distinct().toList());
        pcostDeptCds.addAll(zzanmusers.stream().map(Zzanmuser::getRealWorkUnitCd).filter(Objects::nonNull).distinct().toList());

        List<Bpcomdept> bpcomdepts = bpcomdeptSeekProxy.retrieveListBpcomdeptByPcostDeptCdIn(pcostDeptCds);
        Map<String, Bpcomdept> bpcomdeptMap = bpcomdepts.stream().collect(Collectors.toMap(Bpcomdept::getPcostDeptCd, Function.identity()));

        return ehchmcdbss.stream().map(ehchmcdbs -> {
                    String registeredBy = ehchmcdbs.getRegisteredBy();
                    Zzanmuser registeredZzanmuser = zzanmuserMap.get(registeredBy);
                    if (registeredZzanmuser == null) {
                        return null;
                    }
                    String realWorkDeptCd = registeredZzanmuser.getRealWorkDeptCd();
                    String realWorkUnitCd = registeredZzanmuser.getRealWorkUnitCd();

                    return new DisciplineBasicRdo(ehchmcdbs, registeredZzanmuser, bpcomdeptMap.get(realWorkDeptCd), bpcomdeptMap.get(realWorkUnitCd));
                })
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(DisciplineBasicRdo::getEhsPlanManageSn)
                        .thenComparing(DisciplineBasicRdo::getEhsDtlPlanManageSn))
                .toList();
    }

}
