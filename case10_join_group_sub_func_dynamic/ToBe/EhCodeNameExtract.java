package kr.amc.amis.ehs.feature.shared.code.extract;

import kr.amc.amis.ehs.aggregate.plan.custom.store.EhebmmncdCustomStore;
import kr.amc.amis.ehs.aggregate.plan.domain.entity.Ehebmmncd;
import kr.amc.amis.ehs.aggregate.plan.domain.logic.EhebmmncdLogic;
import kr.amc.amis.library.core.domain.entity.vo.UpperCodeName;
import kr.amc.amis.ehs.aggregate.plan.custom.sdo.EhebmmncdCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class EhCodeNameExtract {
    //
    private final EhebmmncdLogic ehebmmncdLogic;
    private final EhebmmncdCustomStore ehebmmncdCustomStore;

    /**
     * EHS 상위코드(EHS_MANAGE_UPR_CD),EHS 코드(EHS_MANAGE_CD)으로 EHS 코드명 조회 : FN_EH_CDNM
     *
     * @param ehebmmncdCode : EHS 상위코드, EHS 코드
     * @return UpperCodeName : 상위코드값, 코드값, 코드값명
     */
    public UpperCodeName fnEhCdNm(EhebmmncdCode ehebmmncdCode) {
        //
        String rEhsManageCd = ehebmmncdCode.getEhsManageCd();
        String rEhsManageUprCd = ehebmmncdCode.getEhsManageUprCd();

        Ehebmmncd ehebmmncd = ehebmmncdLogic.findByEhsManageCd(rEhsManageCd);
        return new UpperCodeName(rEhsManageUprCd, rEhsManageCd,
                ehebmmncd != null && rEhsManageUprCd != null && rEhsManageUprCd.equals(ehebmmncd.getEhsManageUprCd()) ? ehebmmncd.getEhsManageCdNm() : " "
                );

    }

    /**
     * EHS 상위코드(EHS_MANAGE_UPR_CD),EHS 코드(EHS_MANAGE_CD)으로 EHS 코드명 조회 : FN_EH_CDNM LIST
     *
     * @param ehebmmncdCodes : EHS 상위코드, EHS 코드
     * @return UpperCodeName : 상위코드값, 코드값, 코드값명
     */
    public List<UpperCodeName> fnEhCdNmIn(List<EhebmmncdCode> ehebmmncdCodes) {
        //
        if(ehebmmncdCodes == null || ehebmmncdCodes.isEmpty()){
            return new ArrayList<>();
        }

        List<Ehebmmncd> fnEhCdNms = ehebmmncdCustomStore.retrieveByEhebmncdCodeNameIn(ehebmmncdCodes);
        Map<String, String> collect = fnEhCdNms.stream().collect(Collectors.toMap(ehebmmncd -> ehebmmncd.getEhsManageUprCd() + "_" + ehebmmncd.getEhsManageCd(), Ehebmmncd::getEhsManageCdNm));
        return ehebmmncdCodes.stream()
                .map(fnEhCdNmSdo -> {
                    String key = fnEhCdNmSdo.getEhsManageUprCd() + "_" + fnEhCdNmSdo.getEhsManageCd();
                    return new UpperCodeName(fnEhCdNmSdo.getEhsManageUprCd(),
                            fnEhCdNmSdo.getEhsManageCd(),
                            collect.getOrDefault(key, " ")
                            );
                }).toList();
    }
}
