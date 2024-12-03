package kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.custom.logic;

import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.entity.Ehchdcdsc;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.store.EhchdcdscOptionStore;
import kr.amc.amis.library.store.util.collection.ListUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EhchdcdscCustomLogic {
    //
    private final EhchdcdscOptionStore ehchdcdscOptionStore;

    public List<Ehchdcdsc> findByCircumBhvrKnackSnIn(List<Long> circumBhvrKnackSns) {
        List<Ehchdcdsc> ehchdcdscs = new ArrayList<>();
        List<List<Long>> partitionList = ListUtils.fillPartition(circumBhvrKnackSns, 300);
        for (List<Long> partition : partitionList) {
            ehchdcdscs.addAll(ehchdcdscOptionStore.retrieveByCircumBhvrKnackSnIn(partition));
        }
        return ehchdcdscs;
    }

}
