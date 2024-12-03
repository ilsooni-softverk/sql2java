package kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.custom.store;

import com.querydsl.core.types.Ops;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.custom.sdo.EhchrcdipPlan;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.custom.sdo.EhchrcdipUniqueKey;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.entity.Ehchrcdip;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.store.jpa.jpo.EhchrcdipJpo;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.store.jpa.jpo.QEhchrcdipJpo;
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
public class EhchrcdipCustomStore {
    private final JPAQueryFactory jpaQueryFactory;

    private QEhchrcdipJpo qEhchrcdipJpo = QEhchrcdipJpo.ehchrcdipJpo;
    private List<Ehchrcdip> retrieveListBy(Predicate... predicates) {
        List<EhchrcdipJpo> ehchrcdipJpos = jpaQueryFactory.selectFrom(this.qEhchrcdipJpo)
                .where(predicates)
                .fetch();
        return EhchrcdipJpo.toDomains(ehchrcdipJpos);
    }
    private List<Ehchrcdip> retrieveListBy(QBean<EhchrcdipJpo> ehchrcdipJpoQBean, Predicate... predicates) {
        List<EhchrcdipJpo> ehchrcdipJpos = jpaQueryFactory.select(ehchrcdipJpoQBean).from(this.qEhchrcdipJpo)
                .where(predicates)
                .fetch();
        return EhchrcdipJpo.toDomains(ehchrcdipJpos);
    }

    public List<Ehchrcdip> retrieveByEhsPlanManageSnAndEhsDtlPlanManageSnAndImpvSn(
            Long ehsPlanManageSn, Long ehsDtlPlanManageSn, Long impvSn) {
        return this.retrieveListBy(
                this.qEhchrcdipJpo.ehsPlanManageSn.eq(ehsPlanManageSn),
                this.qEhchrcdipJpo.ehsDtlPlanManageSn.eq(ehsDtlPlanManageSn),
                QuerydslUtil.dynamicZeroGreaterThanOperation(this.qEhchrcdipJpo.impvSn, Ops.EQ, impvSn)
        );
    }

    public List<Ehchrcdip> retrieveByEhchrcdipUniqueKeyIn(List<EhchrcdipUniqueKey> uniqueKeys) {
        if (uniqueKeys == null || uniqueKeys.isEmpty()) {
            return new ArrayList<>();
        }
        List<List<EhchrcdipUniqueKey>> partitionList = ListUtils.fillPartition(uniqueKeys, 300);
        List<Ehchrcdip> results = new ArrayList<>();
        for (List<EhchrcdipUniqueKey> partition : partitionList) {
            List<Ehchrcdip> ehchrcdips = this.retrieveListBy(
                    QuerydslUtil.genMultiIn(partition, new MultiIn<>(EhchrcdipUniqueKey.class, Arrays.asList(
                            new MultiPath(this.qEhchrcdipJpo.ehsPlanManageSn),
                            new MultiPath(this.qEhchrcdipJpo.ehsDtlPlanManageSn),
                            new MultiPath(this.qEhchrcdipJpo.impvSn))))
            );
            results.addAll(ehchrcdips);
        }
        return results;
    }

    public List<EhchrcdipPlan> existsByEhchrcdipPlanIn(List<EhchrcdipPlan> groupKeys) {
        if (groupKeys == null || groupKeys.isEmpty()) {
            return new ArrayList<>();
        }
        List<List<EhchrcdipPlan>> partitionList = ListUtils.fillPartition(groupKeys, 300);
        List<Ehchrcdip> results = new ArrayList<>();
        for (List<EhchrcdipPlan> partition : partitionList) {
            List<Ehchrcdip> ehchrcdips = this.retrieveListBy(
                    Projections.bean(EhchrcdipJpo.class,
                            this.qEhchrcdipJpo.ehsPlanManageSn,
                            this.qEhchrcdipJpo.ehsDtlPlanManageSn),
                    QuerydslUtil.genMultiIn(partition, new MultiIn<>(EhchrcdipPlan.class, Arrays.asList(
                            new MultiPath(this.qEhchrcdipJpo.ehsPlanManageSn),
                            new MultiPath(this.qEhchrcdipJpo.ehsDtlPlanManageSn))))
            );
            results.addAll(ehchrcdips);
        }
        return results.stream().map(Ehchrcdip::plan).distinct().toList();
    }

}
