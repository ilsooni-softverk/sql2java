package kr.amc.amis.ehs.feature.base.management.domain.sdo;

import io.vizend.accent.util.json.JsonSerializable;
import kr.amc.amis.ehs.aggregate.plan.domain.entity.Ehebmmncd;
import kr.amc.amis.ehs.proxy.query.zzanquery.an.zzanmuser.domain.sdo.UserNameRdo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class EhsManageCodeBasicRdo implements JsonSerializable {

    /**
     * EHS관리코드
     */
    private String ehsManageCd;

    /**
     * EHS관리코드명
     */
    private String ehsManageCdNm;

    /**
     * EHS관리상위코드
     */
    private String ehsManageUprCd;

    /**
     * 변경전 EHS관리코드
     */
    private String prvEhsManageCd;

    /**
     * 정렬순서
     */
    private Long sortSeq;

    /**
     * 특기사항
     */
    private String rmrk;

    /**
     * 사용여부
     */
    private String useYn;

    /**
     * 칼럼명
     */
    private String comnNm;

    /**
     * 검색용 searchDivCd
     */
    //private String searchDivCd;

    /**
     * 등록일시
     */
     private String frstEntrDt;

    /**
     * 수정일시
     */
     private String finlUpdtDt;

    // 1번째추가코드값
    private String frstAddCdVal;

    // 1번째추가코드설명
    private String frstAddCdDesc;

    // 2번째추가코드값
    private String sconAddCdVal;

    // 2번째추가코드설명
    private String sconAddCdDesc;

    // 3번째추가코드값
    private String thirAddCdVal;

    // 3번째추가코드설명
    private String thirAddCdDesc;

    // 4번째추가코드값
    private String frthAddCdVal;

    // 4번째추가코드설명
    private String frthAddCdDesc;

    // 타프로그램 검색전용 코드
    //private String searchEhsManageCd;

    //공통코드 및 분류정보사용 구분값
    //private String divNm;

    /**
     * 상위코드명
     */
    private String ehsManageUprNm;

    private String registeredNo;
    private LocalDateTime registeredDateTime;

    private String modifiedNo;
    private LocalDateTime modifiedDateTime;

    public EhsManageCodeBasicRdo(Ehebmmncd ehebmmncd,
                                 Map<String, Ehebmmncd> ehsManageCdNmMap,
                                 Map<String, UserNameRdo> usrNmMap) {
        //
        this.ehsManageCd = ehebmmncd.getEhsManageCd();
        this.prvEhsManageCd = ehebmmncd.getEhsManageCd();
        this.ehsManageCdNm = ehebmmncd.getEhsManageCdNm();

        String manageUprCd = ehebmmncd.getEhsManageUprCd();
        String ehsManageCdNm = ehsManageCdNmMap.get(manageUprCd) != null ? ehsManageCdNmMap.get(manageUprCd).getEhsManageCdNm() : null;
        this.ehsManageUprCd = manageUprCd;
        this.ehsManageUprNm = ehsManageCdNm != null ? ehsManageCdNm : manageUprCd;

        this.sortSeq = ehebmmncd.getSortSeq();
        this.rmrk = ehebmmncd.getRmrk();
        this.useYn = ehebmmncd.getUseYn();
        this.comnNm = ehebmmncd.getComnNm();
        this.frstAddCdVal = ehebmmncd.getFrstAddCdVal();
        this.frstAddCdDesc = ehebmmncd.getFrstAddCdDesc();
        this.sconAddCdVal = ehebmmncd.getSconAddCdVal();
        this.sconAddCdDesc = ehebmmncd.getSconAddCdDesc();
        this.thirAddCdVal = ehebmmncd.getThirAddCdVal();
        this.thirAddCdDesc = ehebmmncd.getThirAddCdDesc();
        this.frthAddCdVal = ehebmmncd.getFrthAddCdVal();
        this.frthAddCdDesc = ehebmmncd.getFrthAddCdDesc();

        String registeredBy = ehebmmncd.getRegisteredBy();
        String registeredUserNm = Optional.ofNullable(usrNmMap.get(registeredBy)).map(UserNameRdo::getUsrNm).orElse("");
        LocalDateTime registeredDate = Instant.ofEpochMilli(ehebmmncd.getRegisteredOn()).atZone(ZoneId.systemDefault()).toLocalDateTime();
        this.registeredNo = registeredUserNm + "(" + registeredBy + ")";
        this.registeredDateTime = registeredDate;
        this.frstEntrDt = registeredDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        String modifiedBy = ehebmmncd.getModifiedBy();
        String modifiedUserNm = Optional.ofNullable(usrNmMap.get(modifiedBy)).map(UserNameRdo::getUsrNm).orElse("");
        LocalDateTime modifiedDate = Instant.ofEpochMilli(ehebmmncd.getModifiedOn()).atZone(ZoneId.systemDefault()).toLocalDateTime();
        this.modifiedNo = modifiedUserNm + "(" + modifiedBy + ")";
        this.modifiedDateTime = modifiedDate;
        this.finlUpdtDt = modifiedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public String toString() {
        return toJson();
    }
}
