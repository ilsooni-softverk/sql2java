package kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.custom.store;

import com.querydsl.core.types.Ops;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.QBean;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.custom.sdo.EhebmbhvrBehaviorKnack;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.entity.Ehebmbhvr;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.store.jpa.jpo.EhebmbhvrJpo;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.store.jpa.jpo.QEhebmbhvrJpo;
import kr.amc.amis.library.store.querydsl.QuerydslUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EhebmbhvrCustomStore {

    private final JPAQueryFactory jpaQueryFactory;

    private QEhebmbhvrJpo qEhebmbhvrJpo = QEhebmbhvrJpo.ehebmbhvrJpo;

    public List<Ehebmbhvr> retrieveListBy(Predicate... predicates) {
        List<EhebmbhvrJpo> ehebmbhvrJpos = jpaQueryFactory.selectFrom(qEhebmbhvrJpo)
                .where(predicates)
                .fetch();
        return EhebmbhvrJpo.toDomains(ehebmbhvrJpos);
    }

    private List<Ehebmbhvr> retrieveListBy(QBean<EhebmbhvrJpo> ehebmbhvrJpoQBean, Predicate... predicates) {
        List<EhebmbhvrJpo> ehebmbhvrJpos = jpaQueryFactory.select(ehebmbhvrJpoQBean).from(qEhebmbhvrJpo)
                .where(predicates)
                .fetch();
        return EhebmbhvrJpo.toDomains(ehebmbhvrJpos);
    }

    public List<Ehebmbhvr> retrieveByEhebmbhvrBehaviorKnack(EhebmbhvrBehaviorKnack ehebmbhvrBehaviorKnack) {
        //
        return this.retrieveListBy(
                QuerydslUtil.dynamicZeroGreaterThanOperation(qEhebmbhvrJpo.circumBhvrKnackSn, Ops.EQ, ehebmbhvrBehaviorKnack.getCircumBhvrKnackSn()),
                QuerydslUtil.dynamicNotBlankOperation(qEhebmbhvrJpo.scenCircumCd, Ops.EQ, ehebmbhvrBehaviorKnack.getScenCircumCd()),
                QuerydslUtil.dynamicNotBlankOperation(qEhebmbhvrJpo.dscpTypCd, Ops.EQ, ehebmbhvrBehaviorKnack.getDscpTypCd()),
                QuerydslUtil.dynamicNotBlankOperation(qEhebmbhvrJpo.circumBhvrKnackNm, Ops.STARTS_WITH, ehebmbhvrBehaviorKnack.getCircumBhvrKnackNm()),
                QuerydslUtil.dynamicNotBlankOperation(qEhebmbhvrJpo.useYn, Ops.EQ, ehebmbhvrBehaviorKnack.getUseYn()),
                QuerydslUtil.dynamicNotEmptyOperation(qEhebmbhvrJpo.circumBhvrKnackSn, Ops.NOT_IN, ehebmbhvrBehaviorKnack.getCircumBhvrKnackSns())
        );
    }

    public List<Ehebmbhvr> retrieveAll() {
        //
        return this.retrieveListBy();
    }

}
