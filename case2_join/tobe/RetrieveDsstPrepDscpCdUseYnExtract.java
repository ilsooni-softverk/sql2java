/*
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package kr.amc.amis.ehs.feature.base.management.extract;

import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.custom.logic.EhchdcdscCustomLogic;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.custom.store.EhebmbhvrCustomStore;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.entity.Ehchdcdsc;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.entity.Ehebmbhvr;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.logic.EhebmbhvrLogic;
import kr.amc.amis.ehs.feature.base.management.domain.sdo.DsstPrepDscpCdUseYnRdo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @legacy-mapper : CrisisCtmsDscpTypBasicMapper
 */
@Service
@RequiredArgsConstructor
public class RetrieveDsstPrepDscpCdUseYnExtract {
    /* Gen by NARA Studio v4.1.0 */
    private final EhebmbhvrLogic ehebmbhvrLogic; // 위기대응행동요령기본
    private final EhebmbhvrCustomStore ehebmbhvrCustomStore; // 위기대응행동요령기본
    private final EhchdcdscCustomLogic ehchdcdscCustomLogic; // 재난대비시나리오상세

    /**
     * SqlID - kr.amc.amis.ca.eh.eb.store.mapper.CrisisCtmsDscpTypBasicMapper.retrieveDsstPrepDscpCdUseYn
     * 재난대비훈련 훈련유형, 시나리오상황, 상황별행동요령 코드 사용여부
     */
    public List<DsstPrepDscpCdUseYnRdo> retrieveDsstPrepDscpCdUseYn(String searchDivNm,
                                                                    String searchDscpTypCd,
                                                                    String searchScenCd,
                                                                    Long searchCircumCd) {
        /* Gen by NARA Studio v4.1.0 */
        // retrieve : EHEBMBHVR BHVR
        List<Ehebmbhvr> bhvrList;
        if ("dscpTyp".equals(searchDivNm)) {
            bhvrList = ehebmbhvrLogic.findByDscpTypCd(searchDscpTypCd);
        } else if ("scen".equals(searchDivNm)) {
            bhvrList = ehebmbhvrLogic.findByScenCircumCdAndDscpTypCd(searchScenCd, searchDscpTypCd);
        } else if ("circum".equals(searchDivNm)) {
            bhvrList = ehebmbhvrLogic.findByCircumBhvrKnackSnAndScenCircumCdAndDscpTypCd(searchCircumCd, searchScenCd, searchDscpTypCd);
        } else {
            bhvrList = ehebmbhvrCustomStore.retrieveAll();
        }

        List<Long> circumBhvrKnackSns = bhvrList.stream().map(Ehebmbhvr::getCircumBhvrKnackSn).toList();
        // retrieve : EHCHDCDSC CDSC
        Map<Long, List<Ehchdcdsc>> cdscMap = ehchdcdscCustomLogic.findByCircumBhvrKnackSnIn(circumBhvrKnackSns).stream()
                .collect(Collectors.groupingBy(Ehchdcdsc::getCircumBhvrKnackSn));

        return bhvrList.stream()
                .map(bhvr -> {
                    List<Ehchdcdsc> ehchdcdscs = cdscMap.get(bhvr.getCircumBhvrKnackSn());
                    if (CollectionUtils.isEmpty(ehchdcdscs)) {
                        return null;
                    }
                    return new DsstPrepDscpCdUseYnRdo(bhvr.getDscpTypCd(), bhvr.getScenCircumCd(), bhvr.getCircumBhvrKnackSn());
                })
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(DsstPrepDscpCdUseYnRdo::getDscpTypCd)
                        .thenComparing(DsstPrepDscpCdUseYnRdo::getScenCircumCd)
                        .thenComparing(DsstPrepDscpCdUseYnRdo::getCircumBhvrKnackSn))
                .toList();
    }
}
