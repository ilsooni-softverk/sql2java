

package kr.amc.amis.ehs.feature.disasterpreparedness.training.domain.sdo;

import io.vizend.accent.util.json.JsonSerializable;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.entity.Ehchmcdeq;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.entity.Ehchrcdev;
import kr.amc.amis.ehs.proxy.query.bpbpquery.co.bpcomdept.domain.entity.Bpcomdept;
import kr.amc.amis.ehs.proxy.query.zzanquery.an.zzanmuser.domain.entity.Zzanmuser;
import kr.amc.amis.ehs.proxy.query.zzanquery.an.zzanmuser.domain.sdo.UserNameRdo;
import kr.amc.amis.library.core.domain.entity.vo.UpperCodeName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class DisciplineEvaluationDetailRdo implements JsonSerializable {

    //훈련평가문항일련번호
    private Long dscpEvalQuestSn;
    //평가항목코드
    private String dscpEvalQuestClauCd;
    //평가항목
    private String dscpEvalQuestClauNm;
    //훈련유형코드
    private String dscpTypCd;
    //배점시작구간점수
    private Long domStSectScor;
    //배점종료구간점수
    private Long domEndSectScor;
    //배점
    private String domSectCnte;
    //세부평가내용
    private String dscpEvalQuestCnte;

    //EHS계획관리일련번호
    private Long ehsPlanManageSn;
    //EHS세부계획관리일련번호
    private Long ehsDtlPlanManageSn;
    //평가점수1
    private Double evalScor1;
    //등록자정보
    private String registeredDeptName;
    private String registeredUnitName;
    private String registeredName;
    private LocalDateTime registeredDateTime;
    //수정자정보
    private String modifiedName;
    private LocalDateTime modifiedDateTime;

    //정렬순서
    private Integer sortSeq;


    //전회 평가점수1
    private Double prvEvalScor1;
    //증감점수
    private Double iadvScor;


    public DisciplineEvaluationDetailRdo(Ehchmcdeq ehchmcdeq,
                                         Map<String, UpperCodeName> fnZzGetCdNmRdoMap) {
        this.dscpEvalQuestSn = ehchmcdeq.getDscpEvalQuestSn();
        String ehchmcdeqDscpEvalQuestClauCd = ehchmcdeq.getDscpEvalQuestClauCd();
        this.dscpEvalQuestClauCd = ehchmcdeqDscpEvalQuestClauCd;
        UpperCodeName upperCodeName = fnZzGetCdNmRdoMap.get(ehchmcdeqDscpEvalQuestClauCd);
        this.dscpEvalQuestClauNm = upperCodeName != null ? upperCodeName.getName() : null;
        this.dscpTypCd = ehchmcdeq.getDscpTypCd();
        Long ehchmcdeqDomStSectScor = ehchmcdeq.getDomStSectScor();
        this.domStSectScor = ehchmcdeqDomStSectScor;
        Long ehchmcdeqDomEndSectScor = ehchmcdeq.getDomEndSectScor();
        this.domEndSectScor = ehchmcdeqDomEndSectScor;
        this.domSectCnte = ehchmcdeqDomStSectScor + " ~ " + ehchmcdeqDomEndSectScor;
        this.dscpEvalQuestCnte = ehchmcdeq.getDscpEvalQuestCnte();
        this.evalScor1 = 0.0D;
        this.sortSeq = ehchmcdeq.getSortSeq();
    }

    public DisciplineEvaluationDetailRdo(Ehchmcdeq ehchmcdeq, Ehchrcdev ehchrcdev, Zzanmuser zzanmuser,
                                         Map<String, UpperCodeName> fnZzGetCdNmRdoMap, Map<String, Bpcomdept> bpcomdeptMap,
                                         Map<String, UserNameRdo> userNmRdoMap) {
        this(ehchmcdeq, fnZzGetCdNmRdoMap);
        if (ehchrcdev != null) {
            this.ehsPlanManageSn = ehchrcdev.getEhsPlanManageSn();
            this.ehsDtlPlanManageSn = ehchrcdev.getEhsDtlPlanManageSn();
            Double ehchrcdevEvalScor1 = ehchrcdev.getEvalScor1();
            this.evalScor1 = ehchrcdevEvalScor1 != null ? ehchrcdevEvalScor1 : 0.0D;

            String realWorkDeptCd = zzanmuser.getRealWorkDeptCd();
            Bpcomdept realWorkDeptBpcomdept = bpcomdeptMap.get(realWorkDeptCd);
            this.registeredDeptName = realWorkDeptBpcomdept != null ? realWorkDeptBpcomdept.getPcostDeptNm() : null;

            String realWorkUnitCd = zzanmuser.getRealWorkUnitCd();
            Bpcomdept realWorkUnitBpcomdept = bpcomdeptMap.get(realWorkUnitCd);
            this.registeredUnitName = realWorkUnitBpcomdept != null ? realWorkUnitBpcomdept.getPcostDeptNm() : null;

            String registeredBy = ehchrcdev.getRegisteredBy();
            if (registeredBy != null) {
                UserNameRdo registeredUserNmRdo = userNmRdoMap.get(registeredBy);
                String registeredUserNm = registeredUserNmRdo != null ? registeredUserNmRdo.getUsrNm() : null;
                this.registeredName = registeredUserNm + "(" + registeredBy + ")";
            }
            this.registeredDateTime = Instant.ofEpochMilli(ehchrcdev.getRegisteredOn()).atZone(ZoneId.systemDefault()).toLocalDateTime();

            String modifiedBy = ehchrcdev.getModifiedBy();
            if (modifiedBy != null) {
                UserNameRdo modifiedUserNmRdo = userNmRdoMap.get(modifiedBy);
                String modifiedUserNm = modifiedUserNmRdo != null ? modifiedUserNmRdo.getUsrNm() : null;
                this.modifiedName = modifiedUserNm + "(" + modifiedBy + ")";
            }
            this.modifiedDateTime =Instant.ofEpochMilli(ehchrcdev.getModifiedOn()).atZone(ZoneId.systemDefault()).toLocalDateTime();
        }
    }

    public DisciplineEvaluationDetailRdo(Ehchrcdev ehchrcdev, Ehchmcdeq ehchmcdeq, Map<String, UpperCodeName> fnZzGetCdNmRdoMap) {
        this.ehsPlanManageSn = ehchrcdev.getEhsPlanManageSn();
        this.ehsDtlPlanManageSn = ehchrcdev.getEhsDtlPlanManageSn();
        Double ehchrcdevEvalScor1 = ehchrcdev.getEvalScor1();
        this.evalScor1 = ehchrcdevEvalScor1 != null ? ehchrcdevEvalScor1 : 0.0D;

        this.dscpEvalQuestSn = ehchmcdeq.getDscpEvalQuestSn();
        String ehchmcdeqDscpEvalQuestClauCd = ehchmcdeq.getDscpEvalQuestClauCd();
        this.dscpEvalQuestClauCd = ehchmcdeqDscpEvalQuestClauCd;
        UpperCodeName upperCodeName = fnZzGetCdNmRdoMap.get(ehchmcdeqDscpEvalQuestClauCd);
        this.dscpEvalQuestClauNm = upperCodeName != null ? upperCodeName.getName() : null;
        this.dscpTypCd = ehchmcdeq.getDscpTypCd();
        Long ehchmcdeqDomStSectScor = ehchmcdeq.getDomStSectScor();
        this.domStSectScor = ehchmcdeqDomStSectScor;
        Long ehchmcdeqDomEndSectScor = ehchmcdeq.getDomEndSectScor();
        this.domEndSectScor = ehchmcdeqDomEndSectScor;
        this.domSectCnte = ehchmcdeqDomStSectScor + " ~ " + ehchmcdeqDomEndSectScor;
        this.dscpEvalQuestCnte = ehchmcdeq.getDscpEvalQuestCnte();
        this.sortSeq = ehchmcdeq.getSortSeq();
    }

    public void compareCalculateIadvScor(DisciplineEvaluationDetailRdo prvEvaluationRdo) {
        this.iadvScor = 0.0D;
        if (prvEvaluationRdo == null) {
            return;
        }
        Double prvEvaluationEvalScor1 = prvEvaluationRdo.getEvalScor1();
        if (prvEvaluationEvalScor1 != null) {
            this.prvEvalScor1 = prvEvaluationEvalScor1;
            this.iadvScor = this.evalScor1 - this.prvEvalScor1;
        } else {
            this.prvEvalScor1 = 0.0D;
        }
    }

    public void compareInputPrvEvalScor(DisciplineEvaluationDetailRdo prvEvaluationRdo) {
        if (prvEvaluationRdo == null) {
            return;
        }
        Double evalScor1 = prvEvaluationRdo.getEvalScor1();
        if (evalScor1 == null) {
            this.prvEvalScor1 = 0.0D;
        } else {
            this.prvEvalScor1 = evalScor1;
        }
    }

    public void compareInputIadvScor(DisciplineEvaluationDetailRdo disciplineEvaluationDetailRdo) {
        double score = 0.0D;
        if (disciplineEvaluationDetailRdo.getPrvEvalScor1() != null) {
            score = disciplineEvaluationDetailRdo.getEvalScor1() - disciplineEvaluationDetailRdo.getPrvEvalScor1();
        } else {
            score = score;
        }
        disciplineEvaluationDetailRdo.setIadvScor(score);
    }

    @Override
    public String toString() {
        return toJson();
    }


}