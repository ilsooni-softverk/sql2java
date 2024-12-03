package kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.custom.store;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.custom.sdo.EhchrcddiUniqueKey;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.entity.Ehchrcddi;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.store.jpa.jpo.EhchrcddiJpo;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.store.jpa.jpo.QEhchrcddiJpo;
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
public class EhchrcddiCustomStore {
    private final JPAQueryFactory jpaQueryFactory;

    private QEhchrcddiJpo qEhchrcddiJpo = QEhchrcddiJpo.ehchrcddiJpo;
    private List<Ehchrcddi> retrieveListBy(Predicate... predicates) {
        List<EhchrcddiJpo> ehchrcddiJpos = jpaQueryFactory.selectFrom(this.qEhchrcddiJpo)
                .where(predicates)
                .fetch();
        return EhchrcddiJpo.toDomains(ehchrcddiJpos);
    }

    public List<Ehchrcddi> retrieveByEhchrcddiUniqueKeyIn(List<EhchrcddiUniqueKey> uniqueKeys) {
        if (uniqueKeys == null || uniqueKeys.isEmpty()) {
            return new ArrayList<>();
        }
        List<List<EhchrcddiUniqueKey>> partitionList = ListUtils.fillPartition(uniqueKeys, 300);
        List<Ehchrcddi> results = new ArrayList<>();
        for (List<EhchrcddiUniqueKey> partition : partitionList) {
            List<Ehchrcddi> ehchrcddis = this.retrieveListBy(
                    QuerydslUtil.genMultiIn(partition, new MultiIn<>(EhchrcddiUniqueKey.class, Arrays.asList(
                            new MultiPath(this.qEhchrcddiJpo.ehsPlanManageSn),
                            new MultiPath(this.qEhchrcddiJpo.ehsDtlPlanManageSn))))
            );
            results.addAll(ehchrcddis);
        }
        return results;
    }

}
