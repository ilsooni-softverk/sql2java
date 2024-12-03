/* 
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package kr.amc.amis.ehs.feature.base.management.extract;

import io.vizend.accent.domain.type.NameValue;
import io.vizend.accent.domain.type.NameValueList;
import kr.amc.amis.ehs.aggregate.plan.domain.entity.Ehebrnmnt;
import kr.amc.amis.ehs.aggregate.plan.domain.logic.EhebrnmntLogic;
import kr.amc.amis.ehs.feature.base.management.domain.sdo.NominationDismissalManagementUdo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @legacy-mapper : PreDsmsManageMapper
 */
@Service
@RequiredArgsConstructor
public class UpdatePreDsmsManageExtract {
    /* Gen by NARA Studio v4.1.0 */
    private final EhebrnmntLogic ehebrnmntLogic;

    /**
     * SqlID - kr.amc.amis.ca.eh.eb.store.mapper.PreDsmsManageMapper.updatePreDsmsManage
     * 선해임 수정
     */
    public List<String> updatePreDsmsManage(List<NominationDismissalManagementUdo> nominationDismissalManagementUdos) {
        //
        List<String> entityIds = new ArrayList<>();
        // retrieve : EHEBRNMNT MINT
        nominationDismissalManagementUdos.forEach(udo -> {
            Ehebrnmnt ehebrnmnt = ehebrnmntLogic.findByNmntDsmsSn(udo.getNmntDsmsSn());
            if (ehebrnmnt == null) {
                return;
            }
            String entityId = ehebrnmnt.getId();
            ehebrnmntLogic.modifyEhebrnmnt(entityId, genNameValueList(udo));
            entityIds.add(entityId);
        });
        return entityIds;
    }

    private NameValueList genNameValueList(NominationDismissalManagementUdo udo) {
        //
        String nmntDsmsProgrsStatCd = StringUtils.defaultIfEmpty(udo.getNmntDsmsProgrsStatCd(), null);
        NameValueList nameValueList = NameValueList.of(
                NameValue.of(Ehebrnmnt.EhebrnmntField.empno.name(), StringUtils.defaultIfEmpty(udo.getEmpno(), null)),
                NameValue.of(Ehebrnmnt.EhebrnmntField.compNm.name(), StringUtils.defaultIfEmpty(udo.getCompNm(), null)),
                NameValue.of(Ehebrnmnt.EhebrnmntField.nmntDt.name(), StringUtils.defaultIfEmpty(udo.getNmntDt(), null)),
                NameValue.of(Ehebrnmnt.EhebrnmntField.dsmsDt.name(), StringUtils.defaultIfEmpty(udo.getDsmsDt(), null)),
                NameValue.of(Ehebrnmnt.EhebrnmntField.qlcrNo.name(), StringUtils.defaultIfEmpty(udo.getQlcrNo(), null)),
                NameValue.of(Ehebrnmnt.EhebrnmntField.qlcrVlprDt.name(), StringUtils.defaultIfEmpty(udo.getQlcrVlprDt(), null)),
                NameValue.of(Ehebrnmnt.EhebrnmntField.nmntChrgTypCd.name(), StringUtils.defaultIfEmpty(udo.getNmntChrgTypCd(), null)),
                NameValue.of(Ehebrnmnt.EhebrnmntField.nmntDsmsProgrsStatCd.name(), nmntDsmsProgrsStatCd),
                NameValue.of(Ehebrnmnt.EhebrnmntField.rmrk.name(),StringUtils.defaultIfEmpty(udo.getRmrk(), null)),
                NameValue.of(Ehebrnmnt.EhebrnmntField.actBasisCnte.name(), StringUtils.defaultIfEmpty(udo.getActBasisCnte(), null)),
                NameValue.of(Ehebrnmnt.EhebrnmntField.relInsttNm.name(), StringUtils.defaultIfEmpty(udo.getRelInsttNm(), null))
        );
        if ("A324".equals(nmntDsmsProgrsStatCd)) {
            // 선임(반려)에서 저장했을 경우
            nameValueList.add(NameValue.of(Ehebrnmnt.EhebrnmntField.nmntConfmDt.name(), null));
            nameValueList.add(NameValue.of(Ehebrnmnt.EhebrnmntField.nmntConfmrNm.name(), null));
        } else if ("A328".equals(nmntDsmsProgrsStatCd)) {
            // 해임(반려)에서 저장했을 경우
            nameValueList.add(NameValue.of(Ehebrnmnt.EhebrnmntField.dsmsConfmDt.name(), null));
            nameValueList.add(NameValue.of(Ehebrnmnt.EhebrnmntField.dsmsConfmrNm.name(), null));
        }
        return nameValueList;
    }
}