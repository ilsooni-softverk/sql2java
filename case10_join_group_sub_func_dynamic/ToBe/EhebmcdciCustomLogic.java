package kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.custom.logic;

import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.entity.Ehebmcdci;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.store.EhebmcdciOptionStore;
import kr.amc.amis.library.store.util.collection.ListUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EhebmcdciCustomLogic {
    //
    private final EhebmcdciOptionStore ehebmcdciOptionStore;

    public List<Ehebmcdci> findByDscpTypCdIn(List<String> dscpTypCds) {
        List<Ehebmcdci> ehebmcdcis = new ArrayList<>();
        List<List<String>> partitionList = ListUtils.fillPartition(dscpTypCds, 300);
        for (List<String> partition : partitionList) {
            ehebmcdcis.addAll(ehebmcdciOptionStore.retrieveByDscpTypCdIn(partition));
        }
        return ehebmcdcis;
    }

}
