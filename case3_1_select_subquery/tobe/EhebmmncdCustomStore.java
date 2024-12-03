package kr.amc.amis.ehs.aggregate.plan.custom.store;

import com.querydsl.core.types.Ops;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.amc.amis.ehs.aggregate.plan.custom.sdo.EhebmmncdAddCodeValue;
import kr.amc.amis.ehs.aggregate.plan.custom.sdo.EhebmmncdCode;
import kr.amc.amis.ehs.aggregate.plan.domain.entity.Ehebmmncd;
import kr.amc.amis.ehs.aggregate.plan.store.jpa.jpo.EhebmmncdJpo;
import kr.amc.amis.ehs.aggregate.plan.store.jpa.jpo.QEhebmmncdJpo;
import kr.amc.amis.ehs.aggregate.plan.store.jpa.repository.EhebmmncdJpaRepository;
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
public class EhebmmncdCustomStore {
    private final JPAQueryFactory jpaQueryFactory;
    private final EhebmmncdJpaRepository ehebmmncdJpaRepository;
    private QEhebmmncdJpo qEhebmmncdJpo = QEhebmmncdJpo.ehebmmncdJpo;

    private List<Ehebmmncd> retrieveListBy(Predicate... predicates) {
        List<EhebmmncdJpo> ehebmmncdJpos = jpaQueryFactory.selectFrom(this.qEhebmmncdJpo)
                .where(predicates)
                .fetch();
        return EhebmmncdJpo.toDomains(ehebmmncdJpos);
    }

    public List<Ehebmmncd> retrieveByEhebmmncdCodeIn(List<EhebmmncdAddCodeValue> groupKeys) {

        if (CollectionUtils.isEmpty(groupKeys)) {
            return new ArrayList<>();
        }

        List<List<EhebmmncdAddCodeValue>> partitionList = ListUtils.fillPartition(groupKeys, 300);
        List<Ehebmmncd> ehebmmncds = new ArrayList<>();
        for (List<EhebmmncdAddCodeValue> partition : partitionList) {
            List<Ehebmmncd> resultEhebmmncd = this.retrieveListBy(
                    QuerydslUtil.genMultiIn(partition, new MultiIn<>(EhebmmncdAddCodeValue.class, Arrays.asList(
                            new MultiPath(this.qEhebmmncdJpo.frstAddCdVal),
                            new MultiPath(this.qEhebmmncdJpo.sconAddCdVal)
                    )))
            );
            ehebmmncds.addAll(resultEhebmmncd);
        }
        return ehebmmncds;
    }

    public List<Ehebmmncd> retrieveByEhebmncdCodeNameIn(Collection<EhebmmncdCode> ehebmmncdCodes) {
        if (CollectionUtils.isEmpty(ehebmmncdCodes)) {
            return new ArrayList<>();
        }
        List<List<EhebmmncdCode>> partitionList = ListUtils.fillPartition(ehebmmncdCodes.stream().toList(), 300);
        List<Ehebmmncd> results = new ArrayList<>();
        for (List<EhebmmncdCode> partition : partitionList) {
            List<Ehebmmncd> ehebmmncds = this.retrieveListBy(
                    QuerydslUtil.genMultiIn(partition, new MultiIn<>(EhebmmncdCode.class, Arrays.asList(
                            new MultiPath(this.qEhebmmncdJpo.ehsManageUprCd),
                            new MultiPath(this.qEhebmmncdJpo.ehsManageCd)
                    )))
            );
            results.addAll(ehebmmncds);
        }
        return results;
    }

    public List<Ehebmmncd> retrieveByEhsManageCdAndFrthAddCdValAndUseYnAndUsrEmpno(String ehsManageCd, String frthAddCdVal, String frstAddCdVal, String useYn) {
        return this.retrieveListBy(
                qEhebmmncdJpo.ehsManageCd.like(ehsManageCd),
                QuerydslUtil.dynamicNotBlankOperation(qEhebmmncdJpo.frthAddCdVal, Ops.EQ, frthAddCdVal),
                qEhebmmncdJpo.useYn.eq(useYn),
                QuerydslUtil.dynamicNotBlankOperation(qEhebmmncdJpo.frstAddCdVal, Ops.EQ, frstAddCdVal)
        );
    }

    public List<Ehebmmncd> retrieveByEhsManageUprCdAndFrstAddCdVal(String ehsManageUprCd, List<String> frstAddCdVals) {
        List<List<String>> partitionList = ListUtils.fillPartition(frstAddCdVals, 300);
        List<Ehebmmncd> ehebmmncds = new ArrayList<>();
        for (List<String> partition : partitionList) {
            List<Ehebmmncd> resultEhebmmncd = this.retrieveListBy(
                    qEhebmmncdJpo.ehsManageUprCd.eq(ehsManageUprCd),
                    QuerydslUtil.dynamicNotEmptyOperation(qEhebmmncdJpo.frstAddCdVal, Ops.IN, partition)
            );
            ehebmmncds.addAll(resultEhebmmncd);
        }
        return ehebmmncds;
    }

    public List<Ehebmmncd> retrieveByEhsManageUprCdAndEhsManageCdNmLikeAndUseYnAndEhsManageCd(String ehsManageCd, String ehsManageUprCd, String ehsManageCdNm, String useYn, String searchEhsManageCd) {
        //
        return this.retrieveListBy(
                QuerydslUtil.dynamicNotBlankOperation(qEhebmmncdJpo.ehsManageUprCd, Ops.EQ, ehsManageCd),
                QuerydslUtil.dynamicNotBlankOperation(qEhebmmncdJpo.ehsManageUprCd, Ops.EQ, ehsManageUprCd),
                QuerydslUtil.dynamicNotBlankOperation(qEhebmmncdJpo.ehsManageCdNm, Ops.LIKE, ehsManageCdNm, WildcardPosition.BOTH),
                QuerydslUtil.dynamicNotBlankOperation(qEhebmmncdJpo.useYn, Ops.EQ, useYn),
                QuerydslUtil.dynamicNotBlankOperation(qEhebmmncdJpo.ehsManageCd, Ops.EQ, searchEhsManageCd)
        );
    }

    public List<Ehebmmncd> retrieveByEhsManageUprCdAndUseYnAndEhsManageCd(String ehsManageCd, String ehsManageUprCd, String useYn, String searchEhsManageCd) {
        //
        return this.retrieveListBy(
                QuerydslUtil.dynamicNotBlankOperation(qEhebmmncdJpo.ehsManageUprCd, Ops.EQ, ehsManageCd),
                QuerydslUtil.dynamicNotBlankOperation(qEhebmmncdJpo.ehsManageUprCd, Ops.EQ, ehsManageUprCd),
                QuerydslUtil.dynamicNotBlankOperation(qEhebmmncdJpo.useYn, Ops.EQ, useYn),
                QuerydslUtil.dynamicNotBlankOperation(qEhebmmncdJpo.ehsManageCd, Ops.EQ, searchEhsManageCd)
        );
    }

    public List<Ehebmmncd> retrieveByEhsManageUprCdAndEhsManageCdAndAddCdValLike(String ehsManageUprCd, String ehsManageCd, String frstAddCdVal, String sconAddCdVal, String thirAddCdVal, String frthAddCdVal) {
        //
        return this.retrieveListBy(
                this.qEhebmmncdJpo.useYn.eq("Y"),
                QuerydslUtil.dynamicNotBlankOperation(this.qEhebmmncdJpo.ehsManageUprCd, Ops.EQ, ehsManageUprCd),
                QuerydslUtil.dynamicNotBlankOperation(this.qEhebmmncdJpo.ehsManageCd, Ops.EQ, ehsManageCd),
                QuerydslUtil.dynamicNotBlankOperation(this.qEhebmmncdJpo.frstAddCdVal, Ops.LIKE, frstAddCdVal, WildcardPosition.BOTH),
                QuerydslUtil.dynamicNotBlankOperation(this.qEhebmmncdJpo.sconAddCdVal, Ops.LIKE, sconAddCdVal, WildcardPosition.BOTH),
                QuerydslUtil.dynamicNotBlankOperation(this.qEhebmmncdJpo.thirAddCdVal, Ops.LIKE, thirAddCdVal, WildcardPosition.BOTH),
                QuerydslUtil.dynamicNotBlankOperation(this.qEhebmmncdJpo.frthAddCdVal, Ops.LIKE, frthAddCdVal, WildcardPosition.BOTH)
        );
    }

    public String update(Ehebmmncd ehebmmncd) {
        // 강제로 modifyBy를 바꾸기 위해서 Custom Store update 생성.
        EhebmmncdJpo ehebrnmntJpo = new EhebmmncdJpo(ehebmmncd);
        ehebmmncdJpaRepository.save(ehebrnmntJpo);
        return ehebmmncd.getId();
    }

}
