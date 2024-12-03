/* 
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package kr.amc.amis.ehs.feature.disasterpreparedness.training.extract;

import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.custom.logic.EhchrcdevCustomLogic;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.entity.Ehchmcdeq;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.entity.Ehchrcdev;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.logic.EhchmcdeqLogic;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.logic.EhchrcdevLogic;
import kr.amc.amis.ehs.feature.disasterpreparedness.training.domain.sdo.DisciplineEvaluationDetailRdo;
import kr.amc.amis.ehs.proxy.query.bpbpquery.co.bpcomdept.BpcomdeptSeekProxy;
import kr.amc.amis.ehs.proxy.query.bpbpquery.co.bpcomdept.domain.entity.Bpcomdept;
import kr.amc.amis.ehs.proxy.query.zzanquery.an.zzandcdvl.ZzandcdvlSeekProxy;
import kr.amc.amis.ehs.proxy.query.zzanquery.an.zzanmuser.ZzanmuserSeekProxy;
import kr.amc.amis.ehs.proxy.query.zzanquery.an.zzanmuser.domain.entity.Zzanmuser;
import kr.amc.amis.ehs.proxy.query.zzanquery.an.zzanmuser.domain.sdo.UserNameRdo;
import kr.amc.amis.library.core.domain.entity.vo.CodeValue;
import kr.amc.amis.library.core.domain.entity.vo.UpperCodeName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RetrieveDscpEvalDtlBrkdwnExtract {
    private final EhchmcdeqLogic ehchmcdeqLogic;
    private final EhchrcdevLogic ehchrcdevLogic;
    private final EhchrcdevCustomLogic ehchrcdevCustomLogic;


    private final ZzanmuserSeekProxy zzanmuserSeekProxy;
    private final BpcomdeptSeekProxy bpcomdeptSeekProxy;
    private final ZzandcdvlSeekProxy zzandcdvlSeekProxy;

    public List<DisciplineEvaluationDetailRdo> retrieveDscpEvalDtlBrkdwn(String dscpTypCd, Long ehsPlanManageSn, Long ehsDtlPlanManageSn, Long dscpEvalQuestSn) {

        //Ehchmcdeq
        List<Ehchmcdeq> ehchmcdeqs = ehchmcdeqLogic.findByDscpTypCd(dscpTypCd);
        if (ehchmcdeqs == null || ehchmcdeqs.isEmpty()) {
            return new ArrayList<>();
        }
        List<Long> ehchmcdeqDscpEvalQuestSns = new ArrayList<>();
        List<String> ehchmcdeqDscpEvalQuestClauCds = new ArrayList<>();
        for (Ehchmcdeq ehchmcdeq : ehchmcdeqs) {
            ehchmcdeqDscpEvalQuestSns.add(ehchmcdeq.getDscpEvalQuestSn());
            ehchmcdeqDscpEvalQuestClauCds.add(ehchmcdeq.getDscpEvalQuestClauCd());
        }

        //FnZzGetCdNm
        List<UpperCodeName> fnZzGetCdNmRdos = zzandcdvlSeekProxy.fnZzGetCdNmIn(
                ehchmcdeqDscpEvalQuestClauCds.stream().map(ehchmcdeqDscpEvalQuestClauCd -> new CodeValue("EH0054", ehchmcdeqDscpEvalQuestClauCd)).toList());
        Map<String, UpperCodeName> fnZzGetCdNmRdoMap = fnZzGetCdNmRdos.stream().collect(Collectors.toMap(UpperCodeName::getCode, Function.identity()));

        //Ehchrcdev
        List<Ehchrcdev> ehchrcdevs = new ArrayList<>();
        if (dscpEvalQuestSn == null) {
            ehchrcdevs.addAll(
                    ehchrcdevCustomLogic.findByEhsPlanManageSnAndEhsDtlPlanManageSnAndDscpEvalQuestSnIn(ehsPlanManageSn, ehsDtlPlanManageSn, ehchmcdeqDscpEvalQuestSns));
        } else if (ehchmcdeqDscpEvalQuestSns.stream().anyMatch(ehchmcdeqDscpEvalQuestSn -> ehchmcdeqDscpEvalQuestSn.equals(dscpEvalQuestSn))){
            ehchrcdevs.add(
                    ehchrcdevLogic.findByEhsPlanManageSnAndEhsDtlPlanManageSnAndDscpEvalQuestSn(ehsPlanManageSn, ehsDtlPlanManageSn, dscpEvalQuestSn));
        }

        //return ------------------
        if (ehchrcdevs.isEmpty()) {
            return ehchmcdeqs.stream().map(ehchmcdeq -> new DisciplineEvaluationDetailRdo(ehchmcdeq, fnZzGetCdNmRdoMap))
                    .sorted(Comparator.comparing(DisciplineEvaluationDetailRdo::getSortSeq)
                            .thenComparing(DisciplineEvaluationDetailRdo::getDscpEvalQuestSn))
                    .toList();
        }

        //collect Ehchrcdev
        Map<Long, Ehchrcdev> ehchrcdevMap = new HashMap<>();
        List<String> registeredEmpnos = new ArrayList<>();
        List<String> empnos = new ArrayList<>();
        for (Ehchrcdev ehchrcdev : ehchrcdevs) {
            ehchrcdevMap.put(ehchrcdev.getDscpEvalQuestSn(), ehchrcdev);
            registeredEmpnos.add(ehchrcdev.getRegisteredBy());
            empnos.add(ehchrcdev.getRegisteredBy());
            empnos.add(ehchrcdev.getModifiedBy());
        }

        //Zzanmuser
        List<String> deptCds = new ArrayList<>();
        List<Zzanmuser> zzanmusers = zzanmuserSeekProxy.retrieveZzanmuserList(
                registeredEmpnos.stream().filter(Objects::nonNull).distinct().toList());
        Map<String, Zzanmuser> zzanmuserMap = new HashMap<>();
        for (Zzanmuser zzanmuser : zzanmusers) {
            zzanmuserMap.put(zzanmuser.getUsrId(),zzanmuser);
            deptCds.add(zzanmuser.getRealWorkDeptCd());
            deptCds.add(zzanmuser.getRealWorkUnitCd());
        }

        //bpcomdept
        List<Bpcomdept> bpcomdepts = bpcomdeptSeekProxy.retrieveListBpcomdeptByPcostDeptCdIn(
                deptCds.stream().filter(Objects::nonNull).collect(Collectors.toSet()));
        Map<String, Bpcomdept> bpcomdeptMap = bpcomdepts.stream().collect(Collectors.toMap(Bpcomdept::getPcostDeptCd, Function.identity()));

        //FnZzGetUserNm
        List<UserNameRdo> userNameRdos = zzanmuserSeekProxy.fnZzGetKorUserNmByUsrIdIn(
                empnos.stream().filter(Objects::nonNull).distinct().toList());
        Map<String, UserNameRdo> userNmRdoMap = userNameRdos.stream().collect(Collectors.toMap(UserNameRdo::getUsrId, Function.identity()));


        return ehchmcdeqs.stream().map(ehchmcdeq -> {
                    Long ehchmcdeqDscpEvalQuestSn = ehchmcdeq.getDscpEvalQuestSn();
                    Ehchrcdev ehchrcdev = ehchrcdevMap.get(ehchmcdeqDscpEvalQuestSn);
                    if (ehchrcdev == null) {
                        return new DisciplineEvaluationDetailRdo(ehchmcdeq, fnZzGetCdNmRdoMap);
                    }

                    String registeredBy = ehchrcdev.getRegisteredBy();
                    Zzanmuser zzanmuser = zzanmuserMap.get(registeredBy);
                    if (zzanmuser == null) {
                        return new DisciplineEvaluationDetailRdo(ehchmcdeq, fnZzGetCdNmRdoMap);
                    }

                    return new DisciplineEvaluationDetailRdo(ehchmcdeq, ehchrcdev, zzanmuser,
                            fnZzGetCdNmRdoMap, bpcomdeptMap, userNmRdoMap);
                })
                .sorted(Comparator.comparing(DisciplineEvaluationDetailRdo::getSortSeq, Comparator.nullsLast(Comparator.naturalOrder()))
                        .thenComparing(DisciplineEvaluationDetailRdo::getDscpEvalQuestSn))
                .toList();
    }

}
