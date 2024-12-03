package kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.custom.store;

import com.querydsl.core.types.Ops;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.custom.sdo.EhchmcdbsUniqueKey;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.entity.Ehchmcdbs;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.store.jpa.jpo.EhchmcdbsJpo;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.store.jpa.jpo.QEhchmcdbsJpo;
import kr.amc.amis.library.store.querydsl.MultiIn;
import kr.amc.amis.library.store.querydsl.MultiPath;
import kr.amc.amis.library.store.querydsl.QuerydslUtil;
import lombok.RequiredArgsConstructor;
import kr.amc.amis.library.store.util.collection.ListUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class EhchmcdbsCustomStore {
    private final JPAQueryFactory jpaQueryFactory;

    private QEhchmcdbsJpo qEhchmcdbsJpo = QEhchmcdbsJpo.ehchmcdbsJpo;
    private List<Ehchmcdbs> retrieveListBy(Predicate... predicates) {
        List<EhchmcdbsJpo> ehchmcdbsJpos = jpaQueryFactory.selectFrom(this.qEhchmcdbsJpo)
                .where(predicates)
                .fetch();
        return EhchmcdbsJpo.toDomains(ehchmcdbsJpos);
    }
    private List<Ehchmcdbs> retrieveListBy(QBean<EhchmcdbsJpo> ehchmcdbsJpoQBean, Predicate... predicates) {
        List<EhchmcdbsJpo> ehchmcdbsJpos = jpaQueryFactory.select(ehchmcdbsJpoQBean).from(this.qEhchmcdbsJpo)
                .where(predicates)
                .fetch();
        return EhchmcdbsJpo.toDomains(ehchmcdbsJpos);
    }

    public List<Ehchmcdbs> retrieveByEhchmcdbsUniqueKey(EhchmcdbsUniqueKey uniqueKey) {
        if (uniqueKey == null) {
            return new ArrayList<>();
        }
        return this.retrieveListBy(
                QuerydslUtil.dynamicZeroGreaterThanOperation(this.qEhchmcdbsJpo.ehsPlanManageSn, Ops.EQ, uniqueKey.getEhsPlanManageSn()),
                QuerydslUtil.dynamicZeroGreaterThanOperation(this.qEhchmcdbsJpo.ehsDtlPlanManageSn, Ops.EQ, uniqueKey.getEhsDtlPlanManageSn())
        );
    }

    public List<Ehchmcdbs> retrieveByEhchmcdbsUniqueKeys(List<EhchmcdbsUniqueKey> uniqueKeys) {
        if (uniqueKeys == null || uniqueKeys.isEmpty()) {
            return new ArrayList<>();
        }
        List<List<EhchmcdbsUniqueKey>> partitionList = ListUtils.fillPartition(uniqueKeys, 300);
        List<Ehchmcdbs> results = new ArrayList<>();
        for (List<EhchmcdbsUniqueKey> partition : partitionList) {
            List<Ehchmcdbs> ehchmcdbs = this.retrieveListBy(
                    QuerydslUtil.genMultiIn(partition, new MultiIn<>(EhchmcdbsUniqueKey.class, Arrays.asList(
                            new MultiPath(this.qEhchmcdbsJpo.ehsPlanManageSn),
                            new MultiPath(this.qEhchmcdbsJpo.ehsDtlPlanManageSn))))
            );
            results.addAll(ehchmcdbs);
        }
        return results;
    }

    public List<EhchmcdbsUniqueKey> existsByEhchmcdbsUniqueKeys(List<EhchmcdbsUniqueKey> uniqueKeys) {
        if (uniqueKeys == null || uniqueKeys.isEmpty()) {
            return new ArrayList<>();
        }
        List<List<EhchmcdbsUniqueKey>> partitionList = ListUtils.fillPartition(uniqueKeys, 300);
        List<Ehchmcdbs> results = new ArrayList<>();
        for (List<EhchmcdbsUniqueKey> partition : partitionList) {
            List<Ehchmcdbs> ehchmcdbs = this.retrieveListBy(
                    Projections.bean(EhchmcdbsJpo.class,
                            this.qEhchmcdbsJpo.ehsPlanManageSn,
                            this.qEhchmcdbsJpo.ehsDtlPlanManageSn),
                    QuerydslUtil.genMultiIn(partition, new MultiIn<>(EhchmcdbsUniqueKey.class, Arrays.asList(
                            new MultiPath(this.qEhchmcdbsJpo.ehsPlanManageSn),
                            new MultiPath(this.qEhchmcdbsJpo.ehsDtlPlanManageSn))))
            );
            results.addAll(ehchmcdbs);
        }
        return results.stream().map(Ehchmcdbs::uniqueKey).toList();
    }

}
