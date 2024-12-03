package kr.amc.amis.ehs.aggregate.plan.custom.logic;

import kr.amc.amis.ehs.aggregate.plan.custom.store.EhebmmncdCustomStore;
import kr.amc.amis.ehs.aggregate.plan.domain.entity.Ehebmmncd;
import kr.amc.amis.ehs.aggregate.plan.store.EhebmmncdOptionStore;
import lombok.RequiredArgsConstructor;
import kr.amc.amis.library.store.util.collection.ListUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EhebmmncdCustomLogic {
    //
    private final EhebmmncdOptionStore ehebmmncdOptionStore;
    private final EhebmmncdCustomStore ehebmmncdCustomStore;

    public List<Ehebmmncd> findByEhsManageCdIn(List<String> ehsManageCds) {
        List<Ehebmmncd> ehebmmncds = new ArrayList<>();
        List<List<String>> partitionList = ListUtils.fillPartition(ehsManageCds, 300);
        for (List<String> partition : partitionList) {
            ehebmmncds.addAll(ehebmmncdOptionStore.retrieveByEhsManageCdIn(partition));
        }

        return ehebmmncds;
    }

    public List<Ehebmmncd> findByEhsManageUprCdAndEhsManageCdIn(String ehsManageUprCd, List<String> ehsManageCds) {
        List<Ehebmmncd> ehebmmncds = new ArrayList<>();
        List<List<String>> partitionList = ListUtils.fillPartition(ehsManageCds, 300);
        for (List<String> partition : partitionList) {
            ehebmmncds.addAll(ehebmmncdOptionStore.retrieveByEhsManageUprCdAndEhsManageCdIn(ehsManageUprCd, partition));
        }
        return ehebmmncds;
    }

    public List<Ehebmmncd> findByEhsManageUprCdIn(List<String> ehsManageUprCds) {
        List<Ehebmmncd> ehebmmncds = new ArrayList<>();
        List<List<String>> partitionList = ListUtils.fillPartition(ehsManageUprCds, 300);
        for (List<String> partition : partitionList) {
            ehebmmncds.addAll(ehebmmncdOptionStore.retrieveByEhsManageUprCdIn(partition));
        }
        return ehebmmncds;
    }

    public List<Ehebmmncd> findByEhsManageUprCdAndSconAddCdDescIn(String ehsManageCd, List<String> sconAddCdDescs) {
        List<Ehebmmncd> ehebmmncds = new ArrayList<>();
        List<List<String>> partitionList = ListUtils.fillPartition(sconAddCdDescs, 300);
        for (List<String> partition : partitionList) {
            ehebmmncds.addAll(ehebmmncdOptionStore.retrieveByEhsManageUprCdAndSconAddCdDescIn(ehsManageCd, partition));
        }
        return ehebmmncds;
    }

    public List<Ehebmmncd> findByEhsManageUprCdInAndThirAddCdValLike(List<String> ehsManageUprCds, String thirAddCdVal) {
        List<Ehebmmncd> ehebmmncds = new ArrayList<>();
        List<List<String>> partitionList = ListUtils.fillPartition(ehsManageUprCds, 300);
        for (List<String> partition : partitionList) {
            ehebmmncds.addAll(ehebmmncdOptionStore.retrieveByEhsManageUprCdInAndThirAddCdValLike(partition, thirAddCdVal));
        }
        return ehebmmncds;
    }

    public List<Ehebmmncd> findByEhsManageUprCdInAndFrstAddCdValInAndThirAddCdValLike(List<String> ehsManageUprCds, List<String> frstAddCdVals, String thirAddCdVal) {
        List<Ehebmmncd> ehebmmncds = new ArrayList<>();
        List<List<String>> partitionList = ListUtils.fillPartition(frstAddCdVals, 300);
        List<String> fillEhsManageUprCds = ListUtils.fillWithValue(ehsManageUprCds);
        for (List<String> partition : partitionList) {
            ehebmmncds.addAll(ehebmmncdOptionStore.retrieveByEhsManageUprCdInAndFrstAddCdValInAndThirAddCdValLike(fillEhsManageUprCds, partition, thirAddCdVal));
        }
        return ehebmmncds;
    }

    public List<Ehebmmncd> findByEhsManageUprCdAndSconAddCdValIn(String ehsManageCd, List<String> sconAddCdVal) {
        List<Ehebmmncd> ehebmmncds = new ArrayList<>();
        List<List<String>> partitionList = ListUtils.fillPartition(sconAddCdVal, 300);
        for (List<String> partition : partitionList) {
            ehebmmncds.addAll(ehebmmncdOptionStore.retrieveByEhsManageUprCdAndSconAddCdValIn(ehsManageCd, partition));
        }
        return ehebmmncds;
    }

    public String modifyEhebmmncdModifyBy(Ehebmmncd ehebmmncd) {
        return ehebmmncdCustomStore.update(ehebmmncd);
    }
}
