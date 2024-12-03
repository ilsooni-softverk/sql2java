package kr.amc.amis.ehs.feature.disasterpreparedness.training.domain.sdo;

import io.vizend.accent.util.json.JsonSerializable;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.custom.sdo.EhchdcdscDepartment;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.entity.Ehchmcdbs;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.entity.Ehchrcddi;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.entity.Ehebmcdci;
import kr.amc.amis.ehs.aggregate.plan.domain.entity.Ehepdehpl;
import kr.amc.amis.ehs.feature.disasterpreparedness.training.domain.entity.DisciplineStatisticsAnalysisSummary;
import kr.amc.amis.library.core.domain.entity.vo.UpperCodeName;
import kr.amc.amis.ehs.proxy.query.bpbpquery.co.bpcomdept.domain.sdo.DepartmentNameRdo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
public class DisciplineStatisticsAnalysisRdo implements JsonSerializable {

    //EHS계획관리일련번호
    private Long ehsPlanManageSn;
    //EHS세부계획관리일련번호
    private Long ehsDtlPlanManageSn;

    //훈련연도
    private String dscpYear;
    // 훈련평가종합점수
    private Long totalSocr;

    // EHS세부계획관리명
    private String ehsDtlPlanManageNm;

    //주관부서코드
    private String chrgDeptCd;
    //주관부서
    private String chrgDeptNm;

    // 계획일자
    private String planDt;

    // 훈련유형코드
    private String dscpTypCd;
    // 훈련유형명
    private String dscpTypNm;
    //미비사항 작성여부
    private String dscpImpvYn;

    // 관리부서코드
    private String manageDeptCd;
    // 관리부서
    private String manageDeptNm;
    // 관리UNIT코드
    private String manageUnitCd;
    // 관리UNIT
    private String manageUnitNm;

    // 현장응급조치처리상태코드
    private String onstEmrcyTkactHandlStatCd;
    // 현장응급조치처리상태명
    private String onstEmrcyTkactHandlStatNm;
    // 필요도구운반처리상태코드
    private String needToolCnvyHandlStatCd;
    // 필요도구운반처리상태명
    private String needToolCnvyHandlStatNm;
    // 피해지역응급처리상태코드
    private String harmRgnEmrcyHandlStatCd;
    // 피해지역응급처리상태명
    private String harmRgnEmrcyHandlStatNm;
    // 현장정리처리상태코드
    private String onstArngHandlStatCd;
    // 현장정리처리상태명
    private String onstArngHandlStatNm;

    //평균종합점수
    private Double avrTotalSocr;


    public DisciplineStatisticsAnalysisRdo(Ehepdehpl ehepdehpl, Ehebmcdci ehebmcdci, Ehchmcdbs ehchmcdbs,
                                           Ehchrcddi ehchrcddi, EhchdcdscDepartment ehchdcdscManageGroupeKey, boolean existsEhchrcdip,
                                           Map<String, UpperCodeName> codeNameRdoMap, Map<String, DepartmentNameRdo> deptNmRdoMap,
                                           DisciplineStatisticsAnalysisSummary summary
    ) {
        this.ehsPlanManageSn = ehepdehpl.getEhsPlanManageSn();
        this.ehsDtlPlanManageSn = ehepdehpl.getEhsDtlPlanManageSn();

        this.dscpYear = genDscpYear(ehepdehpl.getPlanDt());

        Long crisisCtmsEvalSynthScor = ehchrcddi.getCrisisCtmsEvalSynthScor();
        this.totalSocr = crisisCtmsEvalSynthScor != null ? crisisCtmsEvalSynthScor : 0;
        this.ehsDtlPlanManageNm =ehepdehpl.getEhsDtlPlanManageNm();

        String ehepdehplChrgDeptCd = ehepdehpl.getChrgDeptCd();
        this.chrgDeptCd = ehepdehplChrgDeptCd;
        DepartmentNameRdo ehepdehplChrgDeptCdNmRdo = deptNmRdoMap.get(ehepdehplChrgDeptCd);
        this.chrgDeptNm = ehepdehplChrgDeptCdNmRdo != null ? ehepdehplChrgDeptCdNmRdo.getDeptNm() : null;

        this.planDt = ehepdehpl.getPlanDt();
        this.dscpTypCd = ehepdehpl.getDscpTypCd();
        this.dscpTypNm = ehebmcdci.getDscpTypNm();

        this.dscpImpvYn = existsEhchrcdip && ehchmcdbs != null
                && (ehchmcdbs.getMntgRsltCnte() != null || ehchmcdbs.getSynthOpin() != null || ehchmcdbs.getRmrk1() != null || ehchmcdbs.getRmrk2() != null)
                ? "Y" : "N";

        String ehchdcdscManageDeptCd = ehchdcdscManageGroupeKey.getManageDeptCd();
        this.manageDeptCd = ehchdcdscManageDeptCd;
        DepartmentNameRdo ehchdcdscManageDeptNmRdo = deptNmRdoMap.get(ehchdcdscManageDeptCd);
        this.manageDeptNm = ehchdcdscManageDeptNmRdo!= null ? ehchdcdscManageDeptNmRdo.getDeptNm() : null;

        String ehchdcdscManageUnitCd = ehchdcdscManageGroupeKey.getManageUnitCd();
        this.manageUnitCd = ehchdcdscManageUnitCd;
        DepartmentNameRdo ehchdcdscManageUnitNmRdo = deptNmRdoMap.get(ehchdcdscManageUnitCd);
        this.manageUnitNm = ehchdcdscManageUnitNmRdo != null ? ehchdcdscManageUnitNmRdo.getDeptNm() : null;

        String ehchrcddiOnstEmrcyTkactHandlStatCd = ehchrcddi.getOnstEmrcyTkactHandlStatCd();
        this.onstEmrcyTkactHandlStatCd = ehchrcddiOnstEmrcyTkactHandlStatCd;
        if (ehchrcddiOnstEmrcyTkactHandlStatCd != null) {
            UpperCodeName ehchrcddiOnstEmrcyTkactHandlStatUpperCodeName = codeNameRdoMap.get(ehchrcddiOnstEmrcyTkactHandlStatCd);
            this.onstEmrcyTkactHandlStatNm = ehchrcddiOnstEmrcyTkactHandlStatUpperCodeName != null ? ehchrcddiOnstEmrcyTkactHandlStatUpperCodeName.getName() : " ";
        }

        String ehchrcddiNeedToolCnvyHandlStatCd = ehchrcddi.getNeedToolCnvyHandlStatCd();
        this.needToolCnvyHandlStatCd = ehchrcddiNeedToolCnvyHandlStatCd;
        if (ehchrcddiNeedToolCnvyHandlStatCd != null) {
            UpperCodeName ehchrcddiNeedToolCnvyHandlStatUpperCodeName = codeNameRdoMap.get(ehchrcddiNeedToolCnvyHandlStatCd);
            this.needToolCnvyHandlStatNm = ehchrcddiNeedToolCnvyHandlStatUpperCodeName != null ? ehchrcddiNeedToolCnvyHandlStatUpperCodeName.getName() : " ";
        }

        String ehchrcddiHarmRgnEmrcyHandlStatCd = ehchrcddi.getHarmRgnEmrcyHandlStatCd();
        this.harmRgnEmrcyHandlStatCd = ehchrcddiHarmRgnEmrcyHandlStatCd;
        if (ehchrcddiHarmRgnEmrcyHandlStatCd != null) {
            UpperCodeName ehchrcddiHarmRgnEmrcyHandlStatUpperCodeName = codeNameRdoMap.get(ehchrcddiHarmRgnEmrcyHandlStatCd);
            this.harmRgnEmrcyHandlStatNm = ehchrcddiHarmRgnEmrcyHandlStatUpperCodeName != null ? ehchrcddiHarmRgnEmrcyHandlStatUpperCodeName.getName() : " ";
        }

        String ehchrcddiOnstArngHandlStatCd = ehchrcddi.getOnstArngHandlStatCd();
        this.onstArngHandlStatCd = ehchrcddiOnstArngHandlStatCd;
        if (ehchrcddiOnstArngHandlStatCd != null) {
            UpperCodeName ehchrcddiOnstArngHandlStatUpperCodeName = codeNameRdoMap.get(ehchrcddiOnstArngHandlStatCd);
            this.onstArngHandlStatNm = ehchrcddiOnstArngHandlStatUpperCodeName != null ? ehchrcddiOnstArngHandlStatUpperCodeName.getName() : " ";
        }

        this.avrTotalSocr = summary.getAvrTotalSocr();
    }

    public static String genDscpYear(String planDt) {
        return planDt != null && planDt.length() >= 4 ? planDt.substring(0, 4) : null;
    }

    @Override
    public String toString() {
        return toJson();
    }

}
