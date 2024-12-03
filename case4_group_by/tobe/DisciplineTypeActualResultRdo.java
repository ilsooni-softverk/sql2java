package kr.amc.amis.ehs.feature.disasterpreparedness.training.domain.sdo;

import io.vizend.accent.util.json.JsonSerializable;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.entity.Ehchrcddi;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class DisciplineTypeActualResultRdo implements JsonSerializable {

    //평균종합점수
    private Double avgTotalScor = 0D;

    //현장응급조치처리 상태(양호)
    private Long onstEmrScor1 = 0L;

    //현장응급조치처리 상태(불량)
    private Long onstEmrScor2 = 0L;

    //현장응급조치처리 상태(미흡)
    private Long onstEmrScor3 = 0L;

    //현장응급조치처리 상태(미선택)
    private Long onstEmrScor4 = 0L;

    //필요도구운반처리 상태(양호)
    private Long needToolScor1 = 0L;

    //필요도구운반처리 상태(불량)
    private Long needToolScor2 = 0L;

    //필요도구운반처리 상태(미흡)
    private Long needToolScor3 = 0L;

    //필요도구운반처리 상태(미선택)
    private Long needToolScor4 = 0L;

    //2차피해지역응급조치처리 상태(양호)
    private Long scdHarmScor1 = 0L;

    //2차피해지역응급조치처리 상태(불량)
    private Long scdHarmScor2 = 0L;

    //2차피해지역응급조치처리 상태(미흡)
    private Long scdHarmScor3 = 0L;

    //2차피해지역응급조치처리 상태(미선택)
    private Long scdHarmScor4 = 0L;

    //현장정리상태(양호)
    private Long onstArngScor1 = 0L;

    //현장정리상태(불량)
    private Long onstArngScor2 = 0L;

    //현장정리상태(미흡)
    private Long onstArngScor3 = 0L;

    //현장정리상태(미선택)
    private Long onstArngScor4 = 0L;

    //종합점수(90점이상)
    private Long dscpSynthScor1 = 0L;

    //종합점수(70점이상 90점 미만)
    private Long dscpSynthScor2 = 0L;

    //종합점수(50점이상 70점 미만)
    private Long dscpSynthScor3 = 0L;

    //종합점수(50점미만)
    private Long dscpSynthScor4 = 0L;

    //상세계획등록건수
    private Long planRegCnt = 0L;

    //훈련일지 등록건수
    private Long dscpDirCnt = 0L;

    //계획대비 훈련일지 작성율
    private Double planPrepAcrsPer = 0D;


    public DisciplineTypeActualResultRdo(long planRegCnt, List<Ehchrcddi> ehchrcddis) {
        this.planRegCnt = planRegCnt;

        Integer ehchrcddisSize = ehchrcddis.size();
        double sumCrisisCtmsEvalSynthScor = 0D;

        for (Ehchrcddi ehchrcddi : ehchrcddis) {
            Long crisisCtmsEvalSynthScor = ehchrcddi.getCrisisCtmsEvalSynthScor() != null ? ehchrcddi.getCrisisCtmsEvalSynthScor() : 0L;
            sumCrisisCtmsEvalSynthScor = sumCrisisCtmsEvalSynthScor + crisisCtmsEvalSynthScor;

            String onstEmrcyTkactHandlStatCd = ehchrcddi.getOnstEmrcyTkactHandlStatCd();
            if (onstEmrcyTkactHandlStatCd == null) {
                this.onstEmrScor4 = this.onstEmrScor4 + 1;
            } else {
                switch (onstEmrcyTkactHandlStatCd) {
                    case "F111" -> this.onstEmrScor1 = this.onstEmrScor1 + 1;
                    case "F112" -> this.onstEmrScor2 = this.onstEmrScor2 + 1;
                    case "F113" -> this.onstEmrScor3 = this.onstEmrScor3 + 1;
                    default -> {
                    }
                }
            }

            String needToolCnvyHandlStatCd = ehchrcddi.getNeedToolCnvyHandlStatCd();
            if (needToolCnvyHandlStatCd == null) {
                this.needToolScor4 = this.needToolScor4 + 1;
            } else {
                switch (needToolCnvyHandlStatCd) {
                    case "F121" -> this.needToolScor1 = this.needToolScor1 + 1;
                    case "F122" -> this.needToolScor2 = this.needToolScor2 + 1;
                    case "F123" -> this.needToolScor3 = this.needToolScor3 + 1;
                    default -> {
                    }
                }
            }

            String harmRgnEmrcyHandlStatCd = ehchrcddi.getHarmRgnEmrcyHandlStatCd();
            if (harmRgnEmrcyHandlStatCd == null) {
                this.scdHarmScor4 = this.scdHarmScor4 + 1;
            } else {
                switch (harmRgnEmrcyHandlStatCd) {
                    case "F131" -> this.scdHarmScor1 = this.scdHarmScor1 + 1;
                    case "F132" -> this.scdHarmScor2 = this.scdHarmScor2 + 1;
                    case "F133" -> this.scdHarmScor3 = this.scdHarmScor3 + 1;
                    default -> {
                    }
                }
            }

            String onstArngHandlStatCd = ehchrcddi.getOnstArngHandlStatCd();
            if (onstArngHandlStatCd == null) {
                this.onstArngScor4 = this.onstArngScor4 + 1;
            } else {
                switch (onstArngHandlStatCd) {
                    case "F141" -> this.onstArngScor1 = this.onstArngScor1 + 1;
                    case "F142" -> this.onstArngScor2 = this.onstArngScor2 + 1;
                    case "F143" -> this.onstArngScor3 = this.onstArngScor3 + 1;
                    default -> {
                    }
                }
            }

            if (crisisCtmsEvalSynthScor.compareTo(90L) >= 0) {
                this.dscpSynthScor1 = this.dscpSynthScor1 + 1;
            } else if (crisisCtmsEvalSynthScor.compareTo(70L) >= 0
                    && crisisCtmsEvalSynthScor.compareTo(90L) < 0) {
                this.dscpSynthScor2 = this.dscpSynthScor2 + 1;
            } else if (crisisCtmsEvalSynthScor.compareTo(50L) >= 0
                    && crisisCtmsEvalSynthScor.compareTo(70L) < 0) {
                this.dscpSynthScor3 = this.dscpSynthScor3 + 1;
            } else if (crisisCtmsEvalSynthScor.compareTo(49L) <= 0) {
                this.dscpSynthScor4 = this.dscpSynthScor4 + 1;
            }
        }

        if (ehchrcddisSize != 0) {
            this.avgTotalScor = new BigDecimal(sumCrisisCtmsEvalSynthScor)
                    .divide(new BigDecimal(ehchrcddisSize), 14, RoundingMode.HALF_UP).doubleValue();
        }

        this.dscpDirCnt = ehchrcddisSize.longValue();

        if (planRegCnt != 0) {
            this.planPrepAcrsPer = new BigDecimal(ehchrcddisSize)
                    .multiply(new BigDecimal(100))
                    .divide(new BigDecimal(planRegCnt), 14, RoundingMode.HALF_UP).doubleValue();
        }
    }

    @Override
    public String toString() {
        return toJson();
    }

}
