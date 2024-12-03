package kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.custom.store;

import com.querydsl.core.types.Ops;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.custom.sdo.EhebmcdscUniqueKey;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.entity.Ehebmcdsc;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.store.jpa.jpo.EhebmcdscJpo;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.store.jpa.jpo.QEhebmcdscJpo;
import kr.amc.amis.library.store.querydsl.MultiIn;
import kr.amc.amis.library.store.querydsl.MultiPath;
import kr.amc.amis.library.store.querydsl.QuerydslUtil;
import kr.amc.amis.library.store.querydsl.WildcardPosition;
import lombok.RequiredArgsConstructor;
import kr.amc.amis.library.store.util.collection.ListUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class EhebmcdscCustomStore {
    private final JPAQueryFactory jpaQueryFactory;

    private QEhebmcdscJpo qEhebmcdscJpo = QEhebmcdscJpo.ehebmcdscJpo;

    private List<Ehebmcdsc> retrieveListBy(Predicate... predicates) {
        List<EhebmcdscJpo> ehebmcdscJpos = jpaQueryFactory.selectFrom(this.qEhebmcdscJpo)
                .where(predicates)
                .fetch();
        return EhebmcdscJpo.toDomains(ehebmcdscJpos);
    }

    private List<Ehebmcdsc> retrieveListBy(QBean<EhebmcdscJpo> ehebmcdscJpoQBean, Predicate... predicates) {
        List<EhebmcdscJpo> ehebmcdscJpos = jpaQueryFactory.select(ehebmcdscJpoQBean).from(this.qEhebmcdscJpo)
                .where(predicates)
                .fetch();
        return EhebmcdscJpo.toDomains(ehebmcdscJpos);
    }

    public List<Ehebmcdsc> retrieveByEhebmcdscUniqueKeyIn(List<EhebmcdscUniqueKey> uniqueKeys) {
        if (uniqueKeys == null || uniqueKeys.isEmpty()) {
            return new ArrayList<>();
        }
        List<List<EhebmcdscUniqueKey>> partitionList = ListUtils.fillPartition(uniqueKeys, 300);
        List<Ehebmcdsc> results = new ArrayList<>();
        for (List<EhebmcdscUniqueKey> partition : partitionList) {
            List<Ehebmcdsc> ehebmcdscs = this.retrieveListBy(
                    QuerydslUtil.genMultiIn(partition, new MultiIn<>(EhebmcdscUniqueKey.class, Arrays.asList(
                            new MultiPath(this.qEhebmcdscJpo.scenCircumCd),
                            new MultiPath(this.qEhebmcdscJpo.dscpTypCd))))
            );
            results.addAll(ehebmcdscs);
        }
        return results;
    }

    public List<EhebmcdscUniqueKey> existsByEhebmcdscUniqueKeyIn(List<EhebmcdscUniqueKey> uniqueKeys) {
        if (uniqueKeys == null || uniqueKeys.isEmpty()) {
            return new ArrayList<>();
        }
        List<List<EhebmcdscUniqueKey>> partitionList = ListUtils.fillPartition(uniqueKeys, 300);
        List<Ehebmcdsc> results = new ArrayList<>();
        for (List<EhebmcdscUniqueKey> partition : partitionList) {
            List<Ehebmcdsc> ehebmcdscs = this.retrieveListBy(
                    Projections.bean(EhebmcdscJpo.class,
                            this.qEhebmcdscJpo.scenCircumCd,
                            this.qEhebmcdscJpo.dscpTypCd),
                    QuerydslUtil.genMultiIn(partition, new MultiIn<>(EhebmcdscUniqueKey.class, Arrays.asList(
                            new MultiPath(this.qEhebmcdscJpo.scenCircumCd),
                            new MultiPath(this.qEhebmcdscJpo.dscpTypCd))))
            );
            results.addAll(ehebmcdscs);
        }
        return results.stream().map(Ehebmcdsc::uniqueKey).toList();
    }

    public List<Ehebmcdsc> retrieveByScenCircumCdAndDscpTypCdAndScenNmLikeAndUseYn(String scenCircumCd, String dscpTypCd, String scenNm, String useYn) {
        //
        return this.retrieveListBy(
                QuerydslUtil.dynamicNotBlankOperation(qEhebmcdscJpo.scenCircumCd, Ops.EQ, scenCircumCd),
                QuerydslUtil.dynamicNotBlankOperation(qEhebmcdscJpo.dscpTypCd, Ops.EQ, dscpTypCd),
                QuerydslUtil.dynamicNotBlankOperation(qEhebmcdscJpo.scenNm, Ops.LIKE, scenNm, WildcardPosition.RIGHT),
                QuerydslUtil.dynamicNotBlankOperation(qEhebmcdscJpo.useYn, Ops.EQ, useYn)
        );
    }

}
