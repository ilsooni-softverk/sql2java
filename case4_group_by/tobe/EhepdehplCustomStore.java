package kr.amc.amis.ehs.aggregate.plan.custom.store;

import com.querydsl.core.types.Ops;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.amc.amis.ehs.aggregate.plan.custom.sdo.EhepdehplPlan;
import kr.amc.amis.ehs.aggregate.plan.custom.sdo.EhepdehplUniqueKey;
import kr.amc.amis.ehs.aggregate.plan.domain.entity.Ehepdehpl;
import kr.amc.amis.ehs.aggregate.plan.store.jpa.jpo.EhepdehplJpo;
import kr.amc.amis.ehs.aggregate.plan.store.jpa.jpo.QEhepdehplJpo;
import kr.amc.amis.library.store.querydsl.MultiIn;
import kr.amc.amis.library.store.querydsl.MultiPath;
import kr.amc.amis.library.store.querydsl.QuerydslUtil;
import kr.amc.amis.library.store.querydsl.WildcardPosition;
import kr.amc.amis.library.store.util.collection.ListUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class EhepdehplCustomStore {

    private final JPAQueryFactory jpaQueryFactory;
    private QEhepdehplJpo qEhepdehplJpo = QEhepdehplJpo.ehepdehplJpo;

    private List<Ehepdehpl> retrieveListBy(Predicate... predicates) {
        List<EhepdehplJpo> ehepdehplJpos = jpaQueryFactory.selectFrom(this.qEhepdehplJpo)
                .where(predicates)
                .fetch();
        return EhepdehplJpo.toDomains(ehepdehplJpos);
    }

    public List<Ehepdehpl> retrieveByEhepdehplPlanIn(Collection<EhepdehplPlan> ehepdehplPlans) {
        if (CollectionUtils.isEmpty(ehepdehplPlans)) {
            return new ArrayList<>();
        }

        List<List<EhepdehplPlan>> partitionList = ListUtils.fillPartition(ehepdehplPlans.stream().toList(), 300);
        List<Ehepdehpl> results = new ArrayList<>();
        for (List<EhepdehplPlan> partition : partitionList) {
            List<Ehepdehpl> ehepmpltg = this.retrieveListBy(
                    QuerydslUtil.genMultiIn(partition, new MultiIn<>(EhepdehplPlan.class, Arrays.asList(
                            new MultiPath(this.qEhepdehplJpo.ehsPlanManageSn),
                            new MultiPath(this.qEhepdehplJpo.ehsDtlPlanManageSn))))
            );
            results.addAll(ehepmpltg);
        }

        return results;
    }

    public List<Ehepdehpl> retrieveByEhsPlanManageSnaAndEhsDtlPlanManageSnAndEhsManageClausenoAndSbcmClsfCdAndSbcmTypCd(Long ehsPlanManageSn, Long ehsDtlPlanManageSn, String ehsManageClauseno, String sbcmClsfCd, String sbcmTypCd) {
        return this.retrieveListBy(
                QuerydslUtil.dynamicZeroGreaterThanOperation(this.qEhepdehplJpo.ehsPlanManageSn, Ops.EQ, ehsPlanManageSn),
                QuerydslUtil.dynamicZeroGreaterThanOperation(this.qEhepdehplJpo.ehsDtlPlanManageSn, Ops.EQ, ehsDtlPlanManageSn),
                QuerydslUtil.dynamicNotBlankOperation(this.qEhepdehplJpo.ehsManageClauseno, Ops.EQ, ehsManageClauseno),
                QuerydslUtil.dynamicNotBlankOperation(this.qEhepdehplJpo.sbcmClsfCd, Ops.EQ, sbcmClsfCd),
                QuerydslUtil.dynamicNotBlankOperation(this.qEhepdehplJpo.sbcmTypCd, Ops.EQ, sbcmTypCd)
        );
    }

    public List<Ehepdehpl> retrieveByEhsPlanManageSnaAndEhsDtlPlanManageSnAndEhsHeTypCd(Long ehsPlanManageSn, Long ehsDtlPlanManageSn, String ehsHeTypCd) {
        return this.retrieveListBy(
                QuerydslUtil.dynamicZeroGreaterThanOperation(this.qEhepdehplJpo.ehsPlanManageSn, Ops.EQ, ehsPlanManageSn),
                QuerydslUtil.dynamicZeroGreaterThanOperation(this.qEhepdehplJpo.ehsDtlPlanManageSn, Ops.EQ, ehsDtlPlanManageSn),
                QuerydslUtil.dynamicNotBlankOperation(this.qEhepdehplJpo.ehsHeTypCd, Ops.EQ, ehsHeTypCd)
        );
    }

    public List<Ehepdehpl> retrieveByEhsPlanManageSnaAndEhsDtlPlanManageSn(Long ehsPlanManageSn, Long ehsDtlPlanManageSn) {
        return this.retrieveListBy(
                QuerydslUtil.dynamicZeroGreaterThanOperation(this.qEhepdehplJpo.ehsPlanManageSn, Ops.EQ, ehsPlanManageSn),
                QuerydslUtil.dynamicZeroGreaterThanOperation(this.qEhepdehplJpo.ehsDtlPlanManageSn, Ops.EQ, ehsDtlPlanManageSn)
        );
    }

    public List<Ehepdehpl> retrieveByEhsPlanManageSnAndEhsDtlPlanManageSnAndEhsDtlPlanManageNmAndPlanYmAndEhsManageClausenoAndOthers(Long ehsPlanManageSn, Long ehsDtlPlanManageSn, String ehsDtlPlanManageNm, String planYm, String ehsManageClauseno, String evalKndCd, String planStDt, String planEndDt, String dscpTypCd, String safeHlcrInspectClsfCd, String ehsHeTypCd, String vaccinImplTypCd) {
        return this.retrieveListBy(
                QuerydslUtil.dynamicZeroGreaterThanOperation(this.qEhepdehplJpo.ehsPlanManageSn, Ops.EQ, ehsPlanManageSn),
                QuerydslUtil.dynamicZeroGreaterThanOperation(this.qEhepdehplJpo.ehsDtlPlanManageSn, Ops.EQ, ehsDtlPlanManageSn),
                QuerydslUtil.dynamicNotBlankOperation(this.qEhepdehplJpo.ehsDtlPlanManageNm, Ops.LIKE, ehsDtlPlanManageNm, WildcardPosition.BOTH),
                QuerydslUtil.dynamicNotBlankOperation(this.qEhepdehplJpo.planYm, Ops.EQ, planYm),

                QuerydslUtil.dynamicNotBlankOperation(this.qEhepdehplJpo.ehsManageClauseno, Ops.EQ, ehsManageClauseno),
                QuerydslUtil.dynamicNotBlankOperation(this.qEhepdehplJpo.evalKndCd, Ops.EQ, evalKndCd),

                QuerydslUtil.dynamicNotBlankOperation(this.qEhepdehplJpo.planEndDt, Ops.GOE, planStDt),
                QuerydslUtil.dynamicNotBlankOperation(this.qEhepdehplJpo.planStDt, Ops.LOE, planEndDt),

                QuerydslUtil.dynamicNotBlankOperation(this.qEhepdehplJpo.dscpTypCd, Ops.EQ, dscpTypCd),
                QuerydslUtil.dynamicNotBlankOperation(this.qEhepdehplJpo.safeHlcrInspectClsfCd, Ops.EQ, safeHlcrInspectClsfCd),
                QuerydslUtil.dynamicNotBlankOperation(this.qEhepdehplJpo.ehsHeTypCd, Ops.EQ, ehsHeTypCd),

                QuerydslUtil.dynamicNotBlankOperation(this.qEhepdehplJpo.vaccinImplTypCd, Ops.EQ, vaccinImplTypCd)
        );
    }

    public List<Ehepdehpl> retrieveByEhsManageClausenoAndEhsHeTypCdInAndPlanEndDtAndPlanStDtAndEhsDtlPlanManageNmLike(String ehsManageClauseno, List<String> ehsHeTypCds, String planEndDt, String planStDt, String ehsDtlPlanManageNm) {
        List<List<String>> partitionList = ListUtils.fillPartition(ehsHeTypCds, 300);
        List<Ehepdehpl> ehepdehpls = new ArrayList<>();
        for (List<String> partition : partitionList) {
            List<Ehepdehpl> resultEhepdehpl = this.retrieveListBy(
                    this.qEhepdehplJpo.ehsManageClauseno.eq(ehsManageClauseno),
                    this.qEhepdehplJpo.ehsHeTypCd.in(partition),
                    QuerydslUtil.dynamicNotBlankOperation(this.qEhepdehplJpo.planStDt, Ops.LOE, planEndDt),
                    QuerydslUtil.dynamicNotBlankOperation(this.qEhepdehplJpo.planEndDt, Ops.GOE, planStDt),
                    QuerydslUtil.dynamicNotBlankOperation(this.qEhepdehplJpo.ehsDtlPlanManageNm, Ops.LIKE, ehsDtlPlanManageNm, WildcardPosition.BOTH)
            );
            ehepdehpls.addAll(resultEhepdehpl);
        }
        return ehepdehpls;
    }


    public List<Ehepdehpl> retrieveByEhepdehplPlanIn(Collection<EhepdehplPlan> ehepdehplPlans, String searchPlanStDt, String searchPlanEndDt, String searchEhsHeTypCd, String ehsDtlPlanManageNm) {
        if (CollectionUtils.isEmpty(ehepdehplPlans)) {
            return new ArrayList<>();
        }

        List<List<EhepdehplPlan>> partitionList = ListUtils.fillPartition(ehepdehplPlans.stream().toList(), 300);
        List<Ehepdehpl> results = new ArrayList<>();
        for (List<EhepdehplPlan> partition : partitionList) {
            List<Ehepdehpl> ehepmpltg = this.retrieveListBy(
                    QuerydslUtil.genMultiIn(partition, new MultiIn<>(EhepdehplPlan.class, Arrays.asList(
                            new MultiPath(this.qEhepdehplJpo.ehsPlanManageSn),
                            new MultiPath(this.qEhepdehplJpo.ehsDtlPlanManageSn)))),
                    this.qEhepdehplJpo.ehsHeTypCd.ne("B123"),
                    QuerydslUtil.dynamicNotBlankOperation(this.qEhepdehplJpo.planStDt, Ops.LOE, searchPlanEndDt),
                    QuerydslUtil.dynamicNotBlankOperation(this.qEhepdehplJpo.planEndDt, Ops.GOE, searchPlanStDt),
                    QuerydslUtil.dynamicNotBlankOperation(this.qEhepdehplJpo.ehsHeTypCd, Ops.EQ, searchEhsHeTypCd),
                    QuerydslUtil.dynamicNotBlankOperation(this.qEhepdehplJpo.ehsDtlPlanManageNm, Ops.LIKE, ehsDtlPlanManageNm, WildcardPosition.BOTH)
            );
            results.addAll(ehepmpltg);
        }

        return results;
    }

    public List<Ehepdehpl> retrieveByPlanDt(String searchPlanStDt, String searchPlanEndDt) {
        return this.retrieveListBy(
                QuerydslUtil.dynamicNotBlankOperation(this.qEhepdehplJpo.planStDt, Ops.LOE, searchPlanEndDt),
                QuerydslUtil.dynamicNotBlankOperation(this.qEhepdehplJpo.planEndDt, Ops.GOE, searchPlanStDt)
        );
    }

    public List<Ehepdehpl> retrieveByEhepdehplPlanInEhsHeTypCd(Collection<EhepdehplPlan> ehepdehplPlans, String ehsHeTypCd) {
        if (CollectionUtils.isEmpty(ehepdehplPlans)) {
            return new ArrayList<>();
        }

        List<List<EhepdehplPlan>> partitionList = ListUtils.fillPartition(ehepdehplPlans.stream().toList(), 300);
        List<Ehepdehpl> results = new ArrayList<>();
        for (List<EhepdehplPlan> partition : partitionList) {
            List<Ehepdehpl> ehchrccdp = this.retrieveListBy(
                    QuerydslUtil.genMultiIn(partition, new MultiIn<>(EhepdehplPlan.class, Arrays.asList(
                            new MultiPath(this.qEhepdehplJpo.ehsPlanManageSn),
                            new MultiPath(this.qEhepdehplJpo.ehsDtlPlanManageSn)))),
                    QuerydslUtil.dynamicNotBlankOperation(this.qEhepdehplJpo.ehsHeTypCd, Ops.EQ, ehsHeTypCd)
            );
            results.addAll(ehchrccdp);
        }

        return results;
    }

    public List<Ehepdehpl> retrieveByPlanStDtPlanEndDtEhsDtlPlanManageNmEvalKndCd(String planStDt, String planEndDt, String ehsDtlPlanManageNm, String evalKndCd) {
        return retrieveListBy(
                qEhepdehplJpo.ehsManageClauseno.eq("00004"),
                QuerydslUtil.dynamicNotBlankOperation(qEhepdehplJpo.planStDt, Ops.LOE, planEndDt),
                QuerydslUtil.dynamicNotBlankOperation(qEhepdehplJpo.ehsDtlPlanManageNm, Ops.LIKE, ehsDtlPlanManageNm, WildcardPosition.BOTH),
                QuerydslUtil.dynamicNotBlankOperation(qEhepdehplJpo.planEndDt, Ops.GOE, planStDt),
                QuerydslUtil.dynamicNotBlankOperation(qEhepdehplJpo.evalKndCd, Ops.EQ, evalKndCd)
        );
    }

    public List<Ehepdehpl> retrieveByEhsManageClausenoAndEhsAprvalProgrsStatCd(
            String ehsManageClauseno, String ehsAprvalProgrsStatCd,
            Long ehsPlanManageSn, Long ehsDtlPlanManageSn,
            String ehsDtlPlanManageNm, String searchDscpTypCd,
            String searchPlanStDt, String searchPlanEndDt
    ) {
        return retrieveListBy(
                qEhepdehplJpo.ehsManageClauseno.eq(ehsManageClauseno),
                qEhepdehplJpo.ehsAprvalProgrsStatCd.eq(ehsAprvalProgrsStatCd),
                QuerydslUtil.dynamicZeroGreaterThanOperation(qEhepdehplJpo.ehsPlanManageSn, Ops.EQ, ehsPlanManageSn),
                QuerydslUtil.dynamicZeroGreaterThanOperation(qEhepdehplJpo.ehsDtlPlanManageSn, Ops.EQ, ehsDtlPlanManageSn),
                QuerydslUtil.dynamicNotBlankOperation(qEhepdehplJpo.ehsDtlPlanManageNm, Ops.LIKE, ehsDtlPlanManageNm, WildcardPosition.BOTH),
                QuerydslUtil.dynamicNotBlankOperation(qEhepdehplJpo.planDt, Ops.GOE, searchPlanStDt),
                QuerydslUtil.dynamicNotBlankOperation(qEhepdehplJpo.planDt, Ops.LOE, searchPlanEndDt),
                QuerydslUtil.dynamicNotBlankOperation(qEhepdehplJpo.dscpTypCd, Ops.EQ, searchDscpTypCd)
        );
    }

    public List<Ehepdehpl> retrieveByEhsManageClausenoAndPlanDtGoeAndPlanDtLoe(
            String ehsManageClauseno, String searchPlanStDt, String searchPlanEndDt,
            String searchDscpTypCd, String searchChrgDeptCd, String ehsDtlPlanManageNm
    ) {
        return this.retrieveListBy(
                qEhepdehplJpo.ehsManageClauseno.eq(ehsManageClauseno),
                qEhepdehplJpo.planDt.goe(searchPlanStDt),
                qEhepdehplJpo.planDt.loe(searchPlanEndDt),
                QuerydslUtil.dynamicNotBlankOperation(qEhepdehplJpo.dscpTypCd, Ops.EQ, searchDscpTypCd),
                QuerydslUtil.dynamicNotBlankOperation(qEhepdehplJpo.chrgDeptCd, Ops.EQ, searchChrgDeptCd),
                QuerydslUtil.dynamicNotBlankOperation(qEhepdehplJpo.ehsDtlPlanManageNm, Ops.LIKE, ehsDtlPlanManageNm, WildcardPosition.BOTH)
        );
    }

    public List<Ehepdehpl> retrieveByPlanDtGoeAndPlanDtLoeAndDscpTypCd(
            String searchPlanStDt, String searchPlanEndDt, String dscpTypCd
    ) {
        return this.retrieveListBy(
                qEhepdehplJpo.planDt.goe(searchPlanStDt),
                qEhepdehplJpo.planDt.loe(searchPlanEndDt),
                QuerydslUtil.dynamicNotBlankOperation(qEhepdehplJpo.dscpTypCd, Ops.EQ, dscpTypCd)
        );
    }

    public List<Ehepdehpl> retrieveByEhsPlanManageSnIn(
            List<Long> ehsPlanManageSnList, String ehsAprvalProgrsStatCd,
            String searchPlanStDt, String searchPlanEndDt, String dscpTypCd, String ehsDtlPlanManageNm
    ) {
        if (CollectionUtils.isEmpty(ehsPlanManageSnList)) {
            return new ArrayList<>();
        }

        List<List<Long>> partitionList = ListUtils.fillPartition(ehsPlanManageSnList, 300);
        List<Ehepdehpl> results = new ArrayList<>();
        for (List<Long> partition : partitionList) {

            results.addAll(this.retrieveListBy(
                    qEhepdehplJpo.ehsPlanManageSn.in(partition),
                    QuerydslUtil.dynamicNotBlankOperation(qEhepdehplJpo.ehsAprvalProgrsStatCd, Ops.EQ, ehsAprvalProgrsStatCd),
                    QuerydslUtil.dynamicNotBlankOperation(qEhepdehplJpo.planDt, Ops.GOE, searchPlanStDt),
                    QuerydslUtil.dynamicNotBlankOperation(qEhepdehplJpo.planDt, Ops.LOE, searchPlanEndDt),
                    QuerydslUtil.dynamicNotBlankOperation(qEhepdehplJpo.dscpTypCd, Ops.EQ, dscpTypCd),
                    QuerydslUtil.dynamicNotBlankOperation(qEhepdehplJpo.ehsDtlPlanManageNm, Ops.LIKE, ehsDtlPlanManageNm, WildcardPosition.BOTH)
            ));
        }
        return results;
    }

    public List<Ehepdehpl> retrieveByUniqueKeyInAndSearchPlanEndDtAndSearchPlanStDt(String searchPlanEndDt, String searchPlanStDt, List<EhepdehplUniqueKey> uniqueKeys) {
        if (uniqueKeys == null || uniqueKeys.isEmpty()) {
            return new ArrayList<>();
        }
        List<List<EhepdehplUniqueKey>> partitionList = ListUtils.fillPartition(uniqueKeys, 300);
        List<Ehepdehpl> results = new ArrayList<>();
        for (List<EhepdehplUniqueKey> partition : partitionList) {
            List<Ehepdehpl> ehepdehpls = this.retrieveListBy(
                    QuerydslUtil.genMultiIn(partition, new MultiIn<>(EhepdehplUniqueKey.class, Arrays.asList(
                            new MultiPath(this.qEhepdehplJpo.ehsPlanManageSn),
                            new MultiPath(this.qEhepdehplJpo.ehsDtlPlanManageSn)))),
                    QuerydslUtil.dynamicNotBlankOperation(qEhepdehplJpo.planEndDt, Ops.GOE, searchPlanStDt),
                    QuerydslUtil.dynamicNotBlankOperation(qEhepdehplJpo.planStDt, Ops.LOE, searchPlanEndDt)
            );
            results.addAll(ehepdehpls);
        }
        return results;
    }

    public List<Ehepdehpl> retrieveByUniqueKeyIn(List<EhepdehplUniqueKey> uniqueKeys) {
        if (uniqueKeys == null || uniqueKeys.isEmpty()) {
            return new ArrayList<>();
        }
        List<List<EhepdehplUniqueKey>> partitionList = ListUtils.fillPartition(uniqueKeys, 300);
        List<Ehepdehpl> results = new ArrayList<>();
        for (List<EhepdehplUniqueKey> partition : partitionList) {
            List<Ehepdehpl> ehepdehpls = this.retrieveListBy(
                    QuerydslUtil.genMultiIn(partition, new MultiIn<>(EhepdehplUniqueKey.class, Arrays.asList(
                            new MultiPath(this.qEhepdehplJpo.ehsPlanManageSn),
                            new MultiPath(this.qEhepdehplJpo.ehsDtlPlanManageSn))))
            );
            results.addAll(ehepdehpls);
        }
        return results;
    }
}
