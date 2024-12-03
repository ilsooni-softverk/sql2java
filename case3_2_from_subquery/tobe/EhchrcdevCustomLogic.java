package kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.custom.logic;

import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.entity.Ehchrcdev;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.store.EhchrcdevOptionStore;
import kr.amc.amis.library.store.util.collection.ListUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EhchrcdevCustomLogic {
    //
    private final EhchrcdevOptionStore ehchrcdevOptionStore;

    public List<Ehchrcdev> findByEhsPlanManageSnAndEhsDtlPlanManageSnAndDscpEvalQuestSnIn(Long ehsPlanManageSn, Long ehsDtlPlanManageSn, List<Long> dscpEvalQuestSns) {
        List<Ehchrcdev> ehchrcdevs = new ArrayList<>();
        List<List<Long>> partitionList = ListUtils.fillPartition(dscpEvalQuestSns, 300);
        for (List<Long> partition : partitionList) {
            ehchrcdevs.addAll(ehchrcdevOptionStore.retrieveByEhsPlanManageSnAndEhsDtlPlanManageSnAndDscpEvalQuestSnIn(ehsPlanManageSn, ehsDtlPlanManageSn, partition));
        }
        return ehchrcdevs;
    }

}
