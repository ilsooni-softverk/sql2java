/* 
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package kr.amc.amis.ehs.feature.base.management.extract;

import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.entity.Ehebmcdsc;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.entity.sdo.EhebmcdscCdo;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.logic.EhebmcdscLogic;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

/**
 * @legacy-mapper : CrisisCtmsScenCircumBasicMapper
 */
@Service
@RequiredArgsConstructor
public class RegisterCrisisCtmsScenCircumBasicExtract {
    /* Gen by NARA Studio v4.1.0 */
    private final EhebmcdscLogic ehebmcdscLogic; // 위기대응시나리오상황기본

    /**
     * 위기대응훈련 시나리오상황 등록
     * SqlID - kr.amc.amis.ca.eh.eb.store.mapper.CrisisCtmsScenCircumBasicMapper.registerCrisisCtmsScenCircumBasic
     */
    public List<String> registerCrisisCtmsScenCircumBasic(List<EhebmcdscCdo> ehebmcdscCdos) {
        /* Gen by NARA Studio v4.1.0 */
        List<String> entityIds = new ArrayList<>();

        ehebmcdscCdos.forEach(ehebmcdscCdo -> {
            // retrieve selectKey Before
            Ehebmcdsc topScenCircumCdEhebmcdsc = ehebmcdscLogic.findTopByDscpTypCdOrderByScenCircumCdDesc(ehebmcdscCdo.getDscpTypCd());
            String topScenCircumCd = topScenCircumCdEhebmcdsc != null ? topScenCircumCdEhebmcdsc.getScenCircumCd() : null;
            int nextNumber = StringUtils.isNotEmpty(topScenCircumCd) ? Integer.parseInt(topScenCircumCd) + 1 : 1;
            String nextScenCircumCd = format("%03d", nextNumber).trim();
            // set values to InsertObject
            ehebmcdscCdo.setScenCircumCd(nextScenCircumCd);
            // insert
            String entityId = ehebmcdscLogic.registerEhebmcdsc(ehebmcdscCdo);
            entityIds.add(entityId);
        });
        return entityIds;
    }
}
