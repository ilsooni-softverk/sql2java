package kr.amc.amis.ehs.feature.accidentsafety.accident.extract;

import io.vizend.accent.domain.type.NameValue;
import io.vizend.accent.domain.type.NameValueList;
import kr.amc.amis.ehs.aggregate.accident.domain.entity.Ehshrbarr;
import kr.amc.amis.ehs.aggregate.accident.domain.entity.sdo.EhshrbarrCdo;
import kr.amc.amis.ehs.aggregate.accident.domain.logic.EhshrbarrLogic;
import kr.amc.amis.cloud.sqlight.util.SqlightUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MergeBlodInfectionAccidExtract {
    private final EhshrbarrLogic ehshrbarrLogic;

    public String mergeBlodInfectionAccid(EhshrbarrCdo ehshrbarrCdo) {
        String accidDclarNo = ehshrbarrCdo.getAccidDclarNo();
        String accidDclarRcepDivCd = ehshrbarrCdo.getAccidDclarRcepDivCd();
        String accidNm = ehshrbarrCdo.getAccidNm();
        LocalDateTime accidOccurDtm = ehshrbarrCdo.getAccidOccurDtm();
        String blodExpoAccidLocUseCd = ehshrbarrCdo.getBlodExpoAccidLocUseCd();
        String blodExpoAccidSiteCd = ehshrbarrCdo.getBlodExpoAccidSiteCd();
        String blodExpoAccidTypCd = ehshrbarrCdo.getBlodExpoAccidTypCd();
        String blodExpoAccidCrcsCd = ehshrbarrCdo.getBlodExpoAccidCrcsCd();
        String empExpoCrcsRsn = ehshrbarrCdo.getEmpExpoCrcsRsn();
        String accidDamgDgreeCd = ehshrbarrCdo.getAccidDamgDgreeCd();
        String expoUseUtenslCd = ehshrbarrCdo.getExpoUseUtenslCd();
        String csbdInfoPa = ehshrbarrCdo.getCsbdInfoPa();
        String csbdNm = StringUtils.isEmpty(ehshrbarrCdo.getCsbdNm()) ? null : ehshrbarrCdo.getCsbdNm();
        String patno = StringUtils.isEmpty(ehshrbarrCdo.getPatno()) ? null : ehshrbarrCdo.getPatno();
        String csbdSexCd = StringUtils.isEmpty(ehshrbarrCdo.getCsbdSexCd()) ? null : ehshrbarrCdo.getCsbdSexCd();
        Long csbdAge = ehshrbarrCdo.getCsbdAge();
        String csbdMddpCd = StringUtils.isEmpty(ehshrbarrCdo.getCsbdMddpCd()) ? null : ehshrbarrCdo.getCsbdMddpCd();
        String csbdWardCd = StringUtils.isEmpty(ehshrbarrCdo.getCsbdWardCd()) ? null : ehshrbarrCdo.getCsbdWardCd();
        String csbdDiagNm = StringUtils.isEmpty(ehshrbarrCdo.getCsbdDiagNm()) ? null : ehshrbarrCdo.getCsbdDiagNm();
        String examTkactNeedYn = ehshrbarrCdo.getExamTkactNeedYn();
        String mdctNeedYn = ehshrbarrCdo.getMdctNeedYn();
        String ltrTkactNeedYn = ehshrbarrCdo.getLtrTkactNeedYn();
        String tkactFinshYn = ehshrbarrCdo.getTkactFinshYn();
        String rmrk = StringUtils.isEmpty(ehshrbarrCdo.getRmrk()) ? null : ehshrbarrCdo.getRmrk();
        String tkactLocCd = StringUtils.isEmpty(ehshrbarrCdo.getTkactLocCd()) ? null : ehshrbarrCdo.getTkactLocCd();
        LocalDateTime tkactFinshDtm = ehshrbarrCdo.getTkactFinshDtm();
        String tkactFinshEmpno = StringUtils.isEmpty(ehshrbarrCdo.getTkactFinshEmpno()) ? null : ehshrbarrCdo.getTkactFinshEmpno();
        String opadMdexYn = ehshrbarrCdo.getOpadMdexYn();
        String hivExpoYn = ehshrbarrCdo.getHivExpoYn();
        String syphExpoYn = ehshrbarrCdo.getSyphExpoYn();
        String etcDssExpoYn = ehshrbarrCdo.getEtcDssExpoYn();
        String etcDssExpoCnte = StringUtils.isEmpty(ehshrbarrCdo.getEtcDssExpoCnte()) ? null : ehshrbarrCdo.getEtcDssExpoCnte();

        Ehshrbarr ehshrbarr = accidDclarNo == null ? null : ehshrbarrLogic.findByAccidDclarRcepDivCdAndAccidDclarNo(accidDclarRcepDivCd, accidDclarNo);
        if (ehshrbarr == null) {
            LocalDateTime accidOccurDtmLocalDateTime = ehshrbarrCdo.getAccidOccurDtm();
            ehshrbarrCdo.setAccidDclarNo(accidDclarNo);
            ehshrbarrCdo.setAccidDclarRcepDivCd(accidDclarRcepDivCd);
            ehshrbarrCdo.setAccidNm(accidNm);
            ehshrbarrCdo.setBlodExpoAccidLocUseCd(blodExpoAccidLocUseCd);
            ehshrbarrCdo.setBlodExpoAccidSiteCd(blodExpoAccidSiteCd);
            ehshrbarrCdo.setBlodExpoAccidTypCd(blodExpoAccidTypCd);
            ehshrbarrCdo.setBlodExpoAccidCrcsCd(blodExpoAccidCrcsCd);
            ehshrbarrCdo.setEmpExpoCrcsRsn(empExpoCrcsRsn);
            ehshrbarrCdo.setAccidDamgDgreeCd(accidDamgDgreeCd);
            ehshrbarrCdo.setExpoUseUtenslCd(expoUseUtenslCd);
            ehshrbarrCdo.setCsbdInfoPa(csbdInfoPa);
            ehshrbarrCdo.setCsbdNm(csbdNm);
            ehshrbarrCdo.setPatno(patno);
            ehshrbarrCdo.setCsbdSexCd(csbdSexCd);
            ehshrbarrCdo.setCsbdAge(csbdAge);
            ehshrbarrCdo.setCsbdMddpCd(csbdMddpCd);
            ehshrbarrCdo.setCsbdWardCd(csbdWardCd);
            ehshrbarrCdo.setCsbdDiagNm(csbdDiagNm);
            ehshrbarrCdo.setExamTkactNeedYn(SqlightUtil.nvl(examTkactNeedYn, "N")); //
            ehshrbarrCdo.setMdctNeedYn(SqlightUtil.nvl(mdctNeedYn, "N")); //
            ehshrbarrCdo.setLtrTkactNeedYn(SqlightUtil.nvl(ltrTkactNeedYn, "N")); //
            ehshrbarrCdo.setTkactFinshYn(SqlightUtil.nvl(tkactFinshYn, "N")); //
            ehshrbarrCdo.setRmrk(rmrk);
            ehshrbarrCdo.setTkactLocCd(tkactLocCd);
            LocalDateTime tkactFinshDtmLocalDateTime = ehshrbarrCdo.getTkactFinshDtm();
            ehshrbarrCdo.setTkactFinshDtm(convertToLocalDateTime(tkactFinshDtmLocalDateTime));
            ehshrbarrCdo.setTkactFinshEmpno(tkactFinshEmpno);
            ehshrbarrCdo.setOpadMdexYn(SqlightUtil.nvl(opadMdexYn, "N")); //
            ehshrbarrCdo.setHivExpoYn(SqlightUtil.nvl(hivExpoYn, "N")); //
            ehshrbarrCdo.setSyphExpoYn(SqlightUtil.nvl(syphExpoYn, "N")); //
            ehshrbarrCdo.setEtcDssExpoYn(SqlightUtil.nvl(etcDssExpoYn, "N")); //
            ehshrbarrCdo.setEtcDssExpoCnte(SqlightUtil.nvl(etcDssExpoCnte, " ")); //
            ehshrbarrCdo.setExamTkactNeedYn(SqlightUtil.nvl(examTkactNeedYn, "N")); //
            return ehshrbarrLogic.registerEhshrbarr(ehshrbarrCdo);
        } else {
            NameValueList nameValueList = NameValueList.of(
                    NameValue.of(Ehshrbarr.EhshrbarrField.accidNm.name(), SqlightUtil.nvl(accidNm, ehshrbarr.getAccidNm())),
                    NameValue.of(Ehshrbarr.EhshrbarrField.accidOccurDtm.name(), convertToLocalDateTime(accidOccurDtm)),
                    NameValue.of(Ehshrbarr.EhshrbarrField.blodExpoAccidLocUseCd.name(), blodExpoAccidLocUseCd),
                    NameValue.of(Ehshrbarr.EhshrbarrField.blodExpoAccidSiteCd.name(), blodExpoAccidSiteCd),
                    NameValue.of(Ehshrbarr.EhshrbarrField.blodExpoAccidTypCd.name(), blodExpoAccidTypCd),
                    NameValue.of(Ehshrbarr.EhshrbarrField.blodExpoAccidCrcsCd.name(), blodExpoAccidCrcsCd),
                    NameValue.of(Ehshrbarr.EhshrbarrField.empExpoCrcsRsn.name(), empExpoCrcsRsn),
                    NameValue.of(Ehshrbarr.EhshrbarrField.accidDamgDgreeCd.name(), accidDamgDgreeCd),
                    NameValue.of(Ehshrbarr.EhshrbarrField.expoUseUtenslCd.name(), expoUseUtenslCd),
                    NameValue.of(Ehshrbarr.EhshrbarrField.csbdInfoPa.name(), csbdInfoPa),
                    NameValue.of(Ehshrbarr.EhshrbarrField.csbdNm.name(), csbdNm),
                    NameValue.of(Ehshrbarr.EhshrbarrField.patno.name(), patno),
                    NameValue.of(Ehshrbarr.EhshrbarrField.csbdSexCd.name(), csbdSexCd),
                    NameValue.of(Ehshrbarr.EhshrbarrField.csbdAge.name(), csbdAge),
                    NameValue.of(Ehshrbarr.EhshrbarrField.csbdMddpCd.name(), csbdMddpCd),
                    NameValue.of(Ehshrbarr.EhshrbarrField.csbdWardCd.name(), csbdWardCd),
                    NameValue.of(Ehshrbarr.EhshrbarrField.csbdDiagNm.name(), csbdDiagNm),
                    NameValue.of(Ehshrbarr.EhshrbarrField.examTkactNeedYn.name(), SqlightUtil.nvl(examTkactNeedYn, "N")),
                    NameValue.of(Ehshrbarr.EhshrbarrField.mdctNeedYn.name(), SqlightUtil.nvl(mdctNeedYn, "N")),
                    NameValue.of(Ehshrbarr.EhshrbarrField.ltrTkactNeedYn.name(), SqlightUtil.nvl(ltrTkactNeedYn, "N")),
                    NameValue.of(Ehshrbarr.EhshrbarrField.tkactFinshYn.name(), SqlightUtil.nvl(tkactFinshYn, "N")),
                    NameValue.of(Ehshrbarr.EhshrbarrField.rmrk.name(), rmrk),
                    NameValue.of(Ehshrbarr.EhshrbarrField.tkactLocCd.name(), tkactLocCd),
                    NameValue.of(Ehshrbarr.EhshrbarrField.tkactFinshDtm.name(), convertToLocalDateTime(tkactFinshDtm)),
                    NameValue.of(Ehshrbarr.EhshrbarrField.tkactFinshEmpno.name(), tkactFinshEmpno),
                    NameValue.of(Ehshrbarr.EhshrbarrField.opadMdexYn.name(), SqlightUtil.nvl(opadMdexYn, "N")),
                    NameValue.of(Ehshrbarr.EhshrbarrField.hivExpoYn.name(), SqlightUtil.nvl(hivExpoYn, "N")),
                    NameValue.of(Ehshrbarr.EhshrbarrField.syphExpoYn.name(), SqlightUtil.nvl(syphExpoYn, "N")),
                    NameValue.of(Ehshrbarr.EhshrbarrField.etcDssExpoYn.name(), SqlightUtil.nvl(etcDssExpoYn, "N")),
                    NameValue.of(Ehshrbarr.EhshrbarrField.etcDssExpoCnte.name(), SqlightUtil.nvl(etcDssExpoCnte, " "))
            );
            ehshrbarrLogic.modifyEhshrbarr(ehshrbarr.getId(), nameValueList);
            return ehshrbarr.getId();
        }
    }

    private LocalDateTime convertToLocalDateTime(LocalDateTime dtm) {
        return dtm == null ? null : dtm.withSecond(0).withNano(0);
    }
}
