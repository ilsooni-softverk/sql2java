/* 
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package kr.amc.amis.ehs.feature.disasterpreparedness.training.extract;

import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.custom.store.EhchmcdeqCustomStore;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.entity.Ehchmcdeq;
import kr.amc.amis.ehs.feature.disasterpreparedness.training.domain.sdo.DisciplineEvaluationQuestionRdo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RetrieveDscpEvalQuestBasicExtract {
    private final EhchmcdeqCustomStore ehchmcdeqCustomStore;

    public List<DisciplineEvaluationQuestionRdo> retrieveDscpEvalQuestBasic(String dscpTypCd, Long dscpEvalQuestSn, String dscpEvalQuestClauCd) {
        List<Ehchmcdeq> ehchmcdeqs = ehchmcdeqCustomStore.retrieveByDscpTypCdAndDscpEvalQuestSnAndDscpEvalQuestClauCd(
                dscpTypCd, dscpEvalQuestSn, dscpEvalQuestClauCd);
        return ehchmcdeqs.stream()
                .map(DisciplineEvaluationQuestionRdo::new)
                .sorted(Comparator.comparing(DisciplineEvaluationQuestionRdo::getSortSeq, Comparator.nullsLast(Comparator.naturalOrder()))
                        .thenComparing(DisciplineEvaluationQuestionRdo::getDscpEvalQuestSn))
                .toList();
    }


}
