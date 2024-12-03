package kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.custom.logic;

import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.entity.Ehebmbhvr;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.store.EhebmbhvrOptionStore;
import kr.amc.amis.library.store.util.collection.ListUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EhebmbhvrCustomLogic {
    //
    private final EhebmbhvrOptionStore ehebmbhvrOptionStore;

    public List<Ehebmbhvr> findByCircumBhvrKnackSnIn(List<Long> circumBhvrKnackSns) {
        List<Ehebmbhvr> ehebmbhvrs = new ArrayList<>();
        List<List<Long>> partitionList = ListUtils.fillPartition(circumBhvrKnackSns, 300);
        for (List<Long> partition : partitionList) {
            ehebmbhvrs.addAll(ehebmbhvrOptionStore.retrieveByCircumBhvrKnackSnIn(partition));
        }
        return ehebmbhvrs;
    }

}
