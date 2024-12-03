package kr.amc.amis.ehs.feature.disasterpreparedness.training.domain.sdo;

import io.vizend.accent.util.json.JsonSerializable;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.entity.Ehchmcdbs;
import kr.amc.amis.ehs.proxy.query.zzanquery.an.zzanmuser.domain.entity.Zzanmuser;
import kr.amc.amis.ehs.proxy.query.bpbpquery.co.bpcomdept.domain.entity.Bpcomdept;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Setter
@Getter
@NoArgsConstructor
public class DisciplineBasicRdo implements JsonSerializable {

    // EHS계획관리일련번호
    private Long ehsPlanManageSn;
    // EHS세부계획관리일련번호
    private Long ehsDtlPlanManageSn;

    // 모니터링결과내용
    private String mntgRsltCnte;
    // 종합의견
    private String synthOpin;
    // 특기사항1
    private String rmrk1;
    // 특기사항2
    private String rmrk2;
    // 특기사항3
    private String rmrk3;

    // 등록자정보
    private String registeredDeptName;
    private String registeredUnitName;
    private String registeredName;
    private LocalDateTime registeredDateTime;

    // 수정자정보
    private String modifiedName;
    private LocalDateTime modifiedDateTime;


    public DisciplineBasicRdo(Ehchmcdbs ehchmcdbs, Zzanmuser registeredZzanmuser, Bpcomdept realWorkDeptBpcomdept, Bpcomdept realWorkUnitBpcomdept) {

        this.ehsPlanManageSn = ehchmcdbs.getEhsPlanManageSn();
        this.ehsDtlPlanManageSn  = ehchmcdbs.getEhsDtlPlanManageSn();
        this.mntgRsltCnte = ehchmcdbs.getMntgRsltCnte();
        this.synthOpin = ehchmcdbs.getSynthOpin();
        this.rmrk1 = ehchmcdbs.getRmrk1();
        this.rmrk2 = ehchmcdbs.getRmrk2();
        this.rmrk3 = ehchmcdbs.getRmrk3();

        this.registeredDeptName = realWorkDeptBpcomdept != null ? realWorkDeptBpcomdept.getPcostDeptNm() : null;
        this.registeredUnitName = realWorkUnitBpcomdept != null ? realWorkUnitBpcomdept.getPcostDeptNm() : null;

        String registeredBy = ehchmcdbs.getRegisteredBy();
        String alias = registeredZzanmuser.getAlias();
        String registeredEmpName = alias != null ? registeredZzanmuser.getKorNm() + "(" + alias + ")" : registeredZzanmuser.getKorNm() ;
        registeredEmpName = registeredEmpName + "("+registeredBy+ ")";
        this.registeredName = registeredEmpName;
        this.registeredDateTime = Instant.ofEpochMilli(ehchmcdbs.getRegisteredOn()).atZone(ZoneId.systemDefault()).toLocalDateTime();

        this.modifiedName = registeredEmpName;
        this.modifiedDateTime = Instant.ofEpochMilli(ehchmcdbs.getModifiedOn()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    @Override
    public String toString() {
        return toJson();
    }
}
