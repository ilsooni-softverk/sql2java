package kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.custom.store;

import com.querydsl.core.types.Ops;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.custom.sdo.EhchdcdscPlan;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.custom.sdo.EhchdcdscUniqueKey;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.entity.Ehchdcdsc;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.store.jpa.jpo.EhchdcdscJpo;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.store.jpa.jpo.QEhchdcdscJpo;
import kr.amc.amis.library.store.querydsl.MultiIn;
import kr.amc.amis.library.store.querydsl.MultiPath;
import kr.amc.amis.library.store.querydsl.QuerydslUtil;
import kr.amc.amis.library.store.util.collection.ListUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class EhchdcdscCustomStore {
    private final JPAQueryFactory jpaQueryFactory;

    private QEhchdcdscJpo qEhchdcdscJpo = QEhchdcdscJpo.ehchdcdscJpo;
    private List<Ehchdcdsc> retrieveListBy(Predicate... predicates) {
        List<EhchdcdscJpo> ehchdcdscJpos = jpaQueryFactory.selectFrom(this.qEhchdcdscJpo)
                .where(predicates)
                .fetch();
        return EhchdcdscJpo.toDomains(ehchdcdscJpos);
    }

    public List<Ehchdcdsc> retrieveByEhsPlanManageSnAndEhsDtlPlanManageSnAndCircumBhvrKnackSn(
            Long ehsPlanManageSn, Long ehsDtlPlanManageSn, Long circumBhvrKnackSn) {

        return this.retrieveListBy(
                this.qEhchdcdscJpo.ehsPlanManageSn.eq(ehsPlanManageSn),
                QuerydslUtil.dynamicZeroGreaterThanOperation(this.qEhchdcdscJpo.ehsDtlPlanManageSn, Ops.EQ, ehsDtlPlanManageSn),
                QuerydslUtil.dynamicZeroGreaterThanOperation(this.qEhchdcdscJpo.circumBhvrKnackSn, Ops.EQ, circumBhvrKnackSn)
        );
    }

    public List<Ehchdcdsc> retrieveByEhchdcdscUniqueKeyIn(List<EhchdcdscUniqueKey> uniqueKeys) {
        if (uniqueKeys == null || uniqueKeys.isEmpty()) {
            return new ArrayList<>();
        }
        List<List<EhchdcdscUniqueKey>> partitionList = ListUtils.fillPartition(uniqueKeys, 300);
        List<Ehchdcdsc> results = new ArrayList<>();
        for (List<EhchdcdscUniqueKey> partition : partitionList) {
            List<Ehchdcdsc> ehchdcdscs = this.retrieveListBy(
                    QuerydslUtil.genMultiIn(partition, new MultiIn<>(EhchdcdscUniqueKey.class, Arrays.asList(
                            new MultiPath(this.qEhchdcdscJpo.ehsPlanManageSn),
                            new MultiPath(this.qEhchdcdscJpo.ehsDtlPlanManageSn),
                            new MultiPath(this.qEhchdcdscJpo.circumBhvrKnackSn))))
            );
            results.addAll(ehchdcdscs);
        }
        return results;
    }

    public List<Ehchdcdsc> retrieveByEhchdcdscPlanIn(List<EhchdcdscPlan> ehchdcdscPlans) {
        if (ehchdcdscPlans == null || ehchdcdscPlans.isEmpty()) {
            return new ArrayList<>();
        }
        List<List<EhchdcdscPlan>> partitionList = ListUtils.fillPartition(ehchdcdscPlans, 300);
        List<Ehchdcdsc> results = new ArrayList<>();
        for (List<EhchdcdscPlan> partition : partitionList) {
            List<Ehchdcdsc> ehchdcdscs = this.retrieveListBy(
                    QuerydslUtil.genMultiIn(partition, new MultiIn<>(EhchdcdscPlan.class, Arrays.asList(
                            new MultiPath(this.qEhchdcdscJpo.ehsPlanManageSn),
                            new MultiPath(this.qEhchdcdscJpo.ehsDtlPlanManageSn))))
            );
            results.addAll(ehchdcdscs);
        }
        return results;
    }

}
