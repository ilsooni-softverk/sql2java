package kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.custom.store;

import com.querydsl.core.types.Ops;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.entity.Ehchmcdeq;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.store.jpa.jpo.EhchmcdeqJpo;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.store.jpa.jpo.QEhchmcdeqJpo;
import kr.amc.amis.library.store.querydsl.QuerydslUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EhchmcdeqCustomStore {
    private final JPAQueryFactory jpaQueryFactory;
    
    private QEhchmcdeqJpo qEhchmcdeqJpo = QEhchmcdeqJpo.ehchmcdeqJpo;
    private List<Ehchmcdeq> retrieveListBy(Predicate... predicates) {
        List<EhchmcdeqJpo> ehchmcdeqJpos = jpaQueryFactory.selectFrom(this.qEhchmcdeqJpo)
                .where(predicates)
                .fetch();
        return EhchmcdeqJpo.toDomains(ehchmcdeqJpos);
    }

    public List<Ehchmcdeq> retrieveByDscpTypCdAndDscpEvalQuestSnAndDscpEvalQuestClauCd(
            String dscpTypCd, Long dscpEvalQuestSn, String dscpEvalQuestClauCd) {
        return this.retrieveListBy(
                QuerydslUtil.dynamicNotBlankOperation(this.qEhchmcdeqJpo.dscpTypCd, Ops.EQ, dscpTypCd),
                QuerydslUtil.dynamicZeroGreaterThanOperation(this.qEhchmcdeqJpo.dscpEvalQuestSn, Ops.EQ, dscpEvalQuestSn),
                QuerydslUtil.dynamicNotBlankOperation(this.qEhchmcdeqJpo.dscpEvalQuestClauCd, Ops.EQ, dscpEvalQuestClauCd)
        );
    }

}
