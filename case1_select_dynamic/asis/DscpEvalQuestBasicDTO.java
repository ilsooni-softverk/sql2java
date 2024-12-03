package kr.amc.amis.ca.eh.ch.entity;

import kr.amc.amil.message.dto.AbstractDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DscpEvalQuestBasicDTO extends AbstractDTO {

    /**
     * 훈련평가문항일련번호
     */
    private Long dscpEvalQuestSn;

    /**
     * 훈련평가문항항목코드
     */
    private String dscpEvalQuestClauCd;

    /**
     * 훈련유형코드
     */
    private String dscpTypCd;

    /**
     * 훈련평가문항내용
     */
    private String dscpEvalQuestCnte;

    /**
     * 증감점수
     */
    private Double iadvScor;

    /**
     * 전회 평가점수1
     */
    private Double prvEvalScor1;

    /**
     * 배점
     */
    private String domSectCnte;

    /**
     * 금회훈련평가점수
     */
    private Double evalScor1;
    
    //정렬순서
    private long sortSeq;

    public DscpEvalQuestBasicDTO(Long dscpEvalQuestSn) {
        this.dscpEvalQuestSn = dscpEvalQuestSn;
    }

    @Override
    public String toString() {
        return "DscpEvalQuestBasicDTO [" + "dscpEvalQuestSn=" + dscpEvalQuestSn + ", dscpEvalQuestClauCd=" + dscpEvalQuestClauCd + ", dscpTypCd=" + dscpTypCd + ", dscpEvalQuestCnte=" + dscpEvalQuestCnte + "]";
    }

    /**
     * 배점시작구간점수
     */
    private Long domStSectScor;

    /**
     * 배점종료구간점수
     */
    private Long domEndSectScor;
}
