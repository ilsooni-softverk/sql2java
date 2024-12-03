/* 
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package kr.amc.amis.ehs.feature.base.management.extract;

import kr.amc.amis.ehs.aggregate.plan.custom.logic.EhebmmncdCustomLogic;
import kr.amc.amis.ehs.aggregate.plan.custom.store.EhebmmncdCustomStore;
import kr.amc.amis.ehs.aggregate.plan.domain.entity.Ehebmmncd;
import kr.amc.amis.ehs.feature.base.management.domain.sdo.EhsManageCodeBasicRdo;
import kr.amc.amis.ehs.feature.base.management.domain.sdo.EhsManageCodeBasicSdo;
import kr.amc.amis.ehs.proxy.query.zzanquery.an.zzanmuser.ZzanmuserSeekProxy;
import kr.amc.amis.ehs.proxy.query.zzanquery.an.zzanmuser.domain.sdo.UserNameRdo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @legacy-mapper : EhsManageCdBasicMapper
 */
@Service
@RequiredArgsConstructor
public class RetrieveEhsManageCdBasicListExtract {
    /* Gen by NARA Studio v4.1.0 */
    private final EhebmmncdCustomLogic ehebmmncdCustomLogic;
    private final EhebmmncdCustomStore ehebmmncdCustomStore;
    private final ZzanmuserSeekProxy zzanmuserSeekProxy;

    /**
     * EHS관리코드기본목록조회
     * EHS관리코드기본의 목록을 조회한다.
     * SqlID - kr.amc.amis.ca.eh.eb.store.mapper.EhsManageCdBasicMapper.retrieveEhsManageCdBasicList
     */
    public List<EhsManageCodeBasicRdo> retrieveEhsManageCdBasicList(EhsManageCodeBasicSdo ehsManageCodeBasicSdo) {
        /* Gen by NARA Studio v4.1.0 */
        String ehsManageCd = ehsManageCodeBasicSdo.getEhsManageCd();
        String ehsManageCdNm = ehsManageCodeBasicSdo.getEhsManageCdNm();
        String ehsManageUprCd = ehsManageCodeBasicSdo.getEhsManageUprCd();
        String useYn = ehsManageCodeBasicSdo.getUseYn();
        String searchEhsManageCd = ehsManageCodeBasicSdo.getSearchEhsManageCd();
        String divNm = ehsManageCodeBasicSdo.getDivNm();

        // retrieve : EHEBMMNCD BMD
        List<Ehebmmncd> bmdList = ehebmmncdCustomStore.retrieveByEhsManageUprCdAndEhsManageCdNmLikeAndUseYnAndEhsManageCd(
                ehsManageCd, ehsManageUprCd, ehsManageCdNm, useYn, searchEhsManageCd
        );
        if (CollectionUtils.isEmpty(bmdList)) {
            return new ArrayList<>();
        }
        Set<String> ehsManageUprCds = new HashSet<>();
        Set<String> empNos = new HashSet<>();
        for (Ehebmmncd bmd : bmdList) {
            CollectionUtils.addIgnoreNull(ehsManageUprCds, bmd.getEhsManageUprCd());
            CollectionUtils.addIgnoreNull(empNos, bmd.getRegisteredBy());
            CollectionUtils.addIgnoreNull(empNos, bmd.getModifiedBy());
        }

        // retrieve : EHEBMMNCD
        Map<String, Ehebmmncd> ehsManageUprNmMap = ehebmmncdCustomLogic.findByEhsManageCdIn(ehsManageUprCds.stream().toList()).stream()
                .collect(Collectors.toMap(Ehebmmncd::getEhsManageCd, Function.identity()));
        // retrieve: FN_ZZ_GET_USER_NM()
        Map<String, UserNameRdo> usrNmMap = zzanmuserSeekProxy.fnZzGetKorUserNmByUsrIdIn(empNos).stream()
                .collect(Collectors.toMap(UserNameRdo::getUsrId, Function.identity()));

        // set result
        List<EhsManageCodeBasicRdo> ehsManageCodeBasicRdos = bmdList.stream()
                .map(ehebmmncd -> new EhsManageCodeBasicRdo(ehebmmncd, ehsManageUprNmMap, usrNmMap))
                .toList();


        // set Order By
        if ("common".equals(divNm)) {
            return ehsManageCodeBasicRdos.stream()
                    .sorted(Comparator.comparing(EhsManageCodeBasicRdo::getSortSeq))
                    .toList();
        } else {
            return ehsManageCodeBasicRdos.stream()
                    .sorted(Comparator.comparing(EhsManageCodeBasicRdo::getEhsManageCd)
                            .thenComparing(EhsManageCodeBasicRdo::getSortSeq)
                            .thenComparing(EhsManageCodeBasicRdo::getEhsManageUprCd, Comparator.nullsLast(Comparator.naturalOrder()))
                            .thenComparing(EhsManageCodeBasicRdo::getEhsManageCdNm))
                    .toList();
        }
    }

}
