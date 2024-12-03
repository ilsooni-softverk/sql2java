

package kr.amc.amis.ehs.feature.disasterpreparedness.training.domain.sdo;

import io.vizend.accent.util.json.JsonSerializable;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.entity.Ehchmcdeq;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DisciplineEvaluationQuestionRdo implements JsonSerializable {

    //훈련평가문항일련번호
    private Long dscpEvalQuestSn;

    //훈련평가문항항목코드
    private String dscpEvalQuestClauCd;

    //훈련유형코드
    private String dscpTypCd;

    //훈련평가문항내용
    private String dscpEvalQuestCnte;

    //배점시작구간점수
    private Long domStSectScor;

    //배점종료구간점수
    private Long domEndSectScor;

    //정렬순서
    private Integer sortSeq;

    public DisciplineEvaluationQuestionRdo(Ehchmcdeq ehchmcdeq) {
        this.dscpEvalQuestSn = ehchmcdeq.getDscpEvalQuestSn();
        this.dscpEvalQuestClauCd = ehchmcdeq.getDscpEvalQuestClauCd();
        this.dscpTypCd = ehchmcdeq.getDscpTypCd();
        this.dscpEvalQuestCnte = ehchmcdeq.getDscpEvalQuestCnte();
        Long ehchmcdeqDomStSectScor = ehchmcdeq.getDomStSectScor();
        this.domStSectScor = ehchmcdeqDomStSectScor != null ? ehchmcdeqDomStSectScor : 0L;
        Long ehchmcdeqDomEndSectScor = ehchmcdeq.getDomEndSectScor();
        this.domEndSectScor = ehchmcdeqDomEndSectScor != null ? ehchmcdeqDomEndSectScor : 0L;
        this.sortSeq = ehchmcdeq.getSortSeq();
    }

    @Override
    public String toString() {
        return toJson();
    }


}