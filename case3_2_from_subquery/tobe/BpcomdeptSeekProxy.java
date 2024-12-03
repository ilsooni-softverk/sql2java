package kr.amc.amis.ehs.proxy.query.bpbpquery.co.bpcomdept;

import io.vizend.accent.domain.type.CodeName;
import kr.amc.amis.ehs.proxy.query.bpbpquery.co.bpcomdept.client.BpcomdeptSeekClient;
import kr.amc.amis.ehs.proxy.query.bpbpquery.co.bpcomdept.domain.entity.Bpcomdept;
import kr.amc.amis.ehs.proxy.query.bpbpquery.co.bpcomdept.domain.sdo.DepartmentNameRdo;
import kr.amc.amis.ehs.proxy.query.bpbpquery.co.bpcomdept.query.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BpcomdeptSeekProxy {
    private final BpcomdeptSeekClient bpcomdeptSeekClient;

    public List<Bpcomdept> retrieveListBpcomdeptByPcostDeptCdIn(Set<String> pcostDeptCdSet) {
        if (CollectionUtils.isEmpty(pcostDeptCdSet)) {
            return Collections.emptyList();
        }
        RetrieveListBpcomdeptByPcostDeptCdInQuery query = new RetrieveListBpcomdeptByPcostDeptCdInQuery(pcostDeptCdSet);
        return bpcomdeptSeekClient.retrieveListBpcomdeptByPcostDeptCdIn(query).getQueryResult();
    }

    public Bpcomdept retrieveListBpcomdeptByPcostDeptCd(String pcostDeptCd) {
        if (pcostDeptCd == null || pcostDeptCd.isEmpty()) {
            return null;
        }
        Set<String> pcostDeptCdSet = new HashSet<>();
        pcostDeptCdSet.add(pcostDeptCd);
        List<Bpcomdept> bpcomdepts = this.retrieveListBpcomdeptByPcostDeptCdIn(pcostDeptCdSet);
        return bpcomdepts.stream().findFirst().orElse(null);
    }

    public List<Bpcomdept> retrieveListBpcomdeptByUprDeptCdIn(Set<String> uprDeptCds) {
        if (CollectionUtils.isEmpty(uprDeptCds)) {
            return Collections.emptyList();
        }
        RetrieveListBpcomdeptByUprDeptCdInQuery query = new RetrieveListBpcomdeptByUprDeptCdInQuery(uprDeptCds);
        return bpcomdeptSeekClient.retrieveListBpcomdeptByUprDeptCdIn(query).getQueryResult();
    }

    public List<Bpcomdept> retrieveListBpcomdeptByConnectByPcostDeptCd(String pcostDeptCd) {
        if (pcostDeptCd == null || pcostDeptCd.isEmpty()) {
            return Collections.emptyList();
        }
        RetrieveListBpcomdeptByConnectByPcostDeptCdQuery query = new RetrieveListBpcomdeptByConnectByPcostDeptCdQuery(pcostDeptCd);
        return bpcomdeptSeekClient.retrieveListBpcomdeptByConnectByPcostDeptCd(query).getQueryResult();
    }

    public List<Bpcomdept> retrieveListBpcomdeptByPcostDeptNmContaining(String pcostDeptNm) {
        if (pcostDeptNm == null || pcostDeptNm.isEmpty()) {
            return Collections.emptyList();
        }
        RetrieveListBpcomdeptByPcostDeptNmContainingQuery query = new RetrieveListBpcomdeptByPcostDeptNmContainingQuery(pcostDeptNm);
        return bpcomdeptSeekClient.retrieveListBpcomdeptByPcostDeptNmContaining(query).getQueryResult();
    }

    public List<Bpcomdept> retrieveDeptAndUnitList() {
        return bpcomdeptSeekClient.retrieveDeptAndUnitList().getQueryResult();
    }

    public List<Bpcomdept> retrieveBpcomdeptListByApplyStDtApplyEndDtBetween(String date) {
        if (date == null || date.isEmpty()) {
            return Collections.emptyList();
        }
        RetrieveBpcomdeptListByApplyStDtApplyEndDtBetweenQuery query = new RetrieveBpcomdeptListByApplyStDtApplyEndDtBetweenQuery(date);
        return bpcomdeptSeekClient.retrieveBpcomdeptListByApplyStDtApplyEndDtBetween(query).getQueryResult();
    }

    public List<Bpcomdept> retrieveListBpcomdeptByDeptCdAndPcostDeptNmContainingAndUprDeptCdQuery(String deptCd, String pcostDeptNm, String uprDeptCd) {
        RetrieveListBpcomdeptByDeptCdAndPcostDeptNmContainingAndUprDeptCdQuery query = new RetrieveListBpcomdeptByDeptCdAndPcostDeptNmContainingAndUprDeptCdQuery(deptCd, pcostDeptNm, uprDeptCd);
        return bpcomdeptSeekClient.retrieveListBpcomdeptByDeptCdAndPcostDeptNmContainingAndUprDeptCdQuery(query).getQueryResult();
    }

    public List<CodeName> retrieveAllBpcomdeptCodeName() {
        RetrieveAllBpcomdeptCodeNameByPcostDeptCdInQuery query = new RetrieveAllBpcomdeptCodeNameByPcostDeptCdInQuery(null);
        return bpcomdeptSeekClient.retrieveAllBpcomdeptCodeNameByPcostDeptCdIn(query).getQueryResult();
    }

    public List<CodeName> retrieveAllBpcomdeptCodeNameByPcostDeptCdIn(Set<String> pcostDeptCdSet) {
        if (CollectionUtils.isEmpty(pcostDeptCdSet)) {
            return Collections.emptyList();
        }
        RetrieveAllBpcomdeptCodeNameByPcostDeptCdInQuery query = new RetrieveAllBpcomdeptCodeNameByPcostDeptCdInQuery(pcostDeptCdSet);
        return bpcomdeptSeekClient.retrieveAllBpcomdeptCodeNameByPcostDeptCdIn(query).getQueryResult();
    }

    public List<Bpcomdept> retrieveBpcomdeptListByHrUseYnAndHrDeptYnAndOrgzLvlCdAndUprDeptCd(String searchDeptCd) {
        RetrieveBpcomdeptListByHrUseYnAndHrDeptYnAndOrgzLvlCdAndUprDeptCdQuery query = new RetrieveBpcomdeptListByHrUseYnAndHrDeptYnAndOrgzLvlCdAndUprDeptCdQuery("Y", "Y", "5_", searchDeptCd);
        return bpcomdeptSeekClient.retrieveBpcomdeptListByHrUseYnAndHrDeptYnAndOrgzLvlCdAndUprDeptCd(query).getQueryResult();
    }

    public List<Bpcomdept> retrieveBpcomdeptListByHrUseYnAndHrDeptYnAndOrgzLvlCd() {
        RetrieveBpcomdeptListByHrUseYnAndHrDeptYnAndOrgzLvlCdQuery query = new RetrieveBpcomdeptListByHrUseYnAndHrDeptYnAndOrgzLvlCdQuery("Y", "Y", "5_");
        return bpcomdeptSeekClient.retrieveBpcomdeptListByHrUseYnAndHrDeptYnAndOrgzLvlCd(query).getQueryResult();
    }


    /**
     * 부서코드목록으로 부서명목록 조회 : FN_ZZ_GET_DEPT_NM LIST
     *
     * @param deptCds : 부서코드
     * @return List<DepartmentNameRdo> : 부서영문명, 부서약어명, 원가부서명, 부서명, 부서코드, 부서구분
     */
    public List<DepartmentNameRdo> fnZzGetDeptNmByPcostDeptCdIn(Collection<String> deptCds, String nameTp) {
        if (CollectionUtils.isEmpty(deptCds)) {
            return Collections.emptyList();
        }
        FnZzGetDeptNmByPcostDepCdInQuery query = new FnZzGetDeptNmByPcostDepCdInQuery(deptCds, nameTp);
        return bpcomdeptSeekClient.fnZzGetDeptNmByPcostDeptCdIn(query).getQueryResult();
    }

    /**
     * 부서코드로 부서명 조회 : FN_ZZ_GET_DEPT_NM
     *
     * @param deptCd : 부서코드
     * @param nameTp : 부서구분 (K:한글, E:영문)
     * @return DepartmentNameRdo : 부서영문명, 부서약어명, 원가부서명, 부서명, 부서코드, 부서구분
     */
    public DepartmentNameRdo fnZzGetDeptNm(String deptCd, String nameTp) {
        FnZzGetDeptNmQuery query = new FnZzGetDeptNmQuery(deptCd, nameTp);
        return bpcomdeptSeekClient.fnZzGetDeptNm(query).getQueryResult();
    }

    public List<DepartmentNameRdo> fnZzGetDeptKorNmByPcostDeptCdIn(Collection<String> deptCds) {
        if (CollectionUtils.isEmpty(deptCds)) {
            return Collections.emptyList();
        }
        FnZzGetDeptKorNmByPcostDeptCdInQuery query = new FnZzGetDeptKorNmByPcostDeptCdInQuery(deptCds);
        return bpcomdeptSeekClient.fnZzGetDeptKorNmByPcostDeptCdIn(query).getQueryResult();
    }

    public DepartmentNameRdo fnZzGetDeptKorNm(String deptCd) {
        FnZzGetDeptKorNmQuery query = new FnZzGetDeptKorNmQuery(deptCd);
        return bpcomdeptSeekClient.fnZzGetDeptKorNm(query).getQueryResult();
    }
}
