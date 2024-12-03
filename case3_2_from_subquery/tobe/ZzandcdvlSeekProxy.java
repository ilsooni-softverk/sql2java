package kr.amc.amis.ehs.proxy.query.zzanquery.an.zzandcdvl;

import kr.amc.amis.ehs.proxy.query.zzanquery.an.zzandcdvl.query.RetrieveZzandcdvlListByCdNoAndCdValInQuery;
import kr.amc.amis.ehs.proxy.query.zzanquery.an.zzandcdvl.query.RetrieveZzandcdvlListByCdNoQuery;
import kr.amc.amis.ehs.proxy.query.zzanquery.an.zzanmuser.query.FnZzGetCdNmInQuery;
import kr.amc.amis.ehs.proxy.query.zzanquery.an.zzanmuser.query.FnZzGetCdNmQuery;
import kr.amc.amis.ehs.proxy.query.zzanquery.an.zzandcdvl.domain.sdo.MdexJobtyCdRdo;
import kr.amc.amis.ehs.proxy.query.zzanquery.an.zzandcdvl.domain.sdo.MrJobtyCdRdo;
import kr.amc.amis.ehs.proxy.query.zzanquery.an.zzanmuser.query.FnZzGetMdexJobtyCdQuery;
import kr.amc.amis.ehs.proxy.query.zzanquery.an.zzanmuser.query.FnZzGetMrJobtyCdQuery;
import kr.amc.amis.ehs.proxy.query.zzanquery.an.zzandcdvl.client.ZzandcdvlSeekClient;
import kr.amc.amis.ehs.proxy.query.zzanquery.an.zzandcdvl.domain.entity.Zzandcdvl;
import kr.amc.amis.ehs.proxy.query.zzanquery.an.zzandcdvl.query.RetrieveZzandcdvlListByCdNoAndCdValInAndN10thAddCdValQuery;
import kr.amc.amis.ehs.proxy.query.zzanquery.an.zzandcdvl.query.RetrieveZzandcdvlListByCodeValueInQuery;
import kr.amc.amis.library.core.domain.entity.vo.CodeValue;
import kr.amc.amis.library.core.domain.entity.vo.UpperCodeName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ZzandcdvlSeekProxy {
    private final ZzandcdvlSeekClient zzandcdvlSeekClient;

    public List<Zzandcdvl> retrieveZzandcdvlListByCdNoAndCdValInAndN10thAddCdVal(String cdNo, Collection<String> cdVals, String n10thAddCdVal) {
        if (cdNo == null || cdVals == null || cdVals.isEmpty()) {
            return new ArrayList<>();
        }
        RetrieveZzandcdvlListByCdNoAndCdValInAndN10thAddCdValQuery query = new RetrieveZzandcdvlListByCdNoAndCdValInAndN10thAddCdValQuery(cdNo, cdVals, n10thAddCdVal);
        return zzandcdvlSeekClient.retrieveZzandcdvlListByCdNoAndCdValInAndN10thAddCdVal(query).getQueryResult();
    }

    public List<Zzandcdvl> retrieveZzandcdvlListByCdNoAndCdValIn(String cdNo, Collection<String> cdVals) {
        if (cdNo == null || cdVals == null || cdVals.isEmpty()) {
            return new ArrayList<>();
        }
        RetrieveZzandcdvlListByCdNoAndCdValInQuery query = new RetrieveZzandcdvlListByCdNoAndCdValInQuery(cdNo, cdVals);
        return zzandcdvlSeekClient.retrieveZzandcdvlListByCdNoAndCdValIn(query).getQueryResult();
    }

    public List<Zzandcdvl> retrieveZzandcdvlListByCodeValueIn(List<CodeValue> codeValueList) {
        if (CollectionUtils.isEmpty(codeValueList)) {
            return Collections.emptyList();
        }
        RetrieveZzandcdvlListByCodeValueInQuery query = new RetrieveZzandcdvlListByCodeValueInQuery(codeValueList);
        return zzandcdvlSeekClient.retrieveZzandcdvlListByCodeValueIn(query).getQueryResult();
    }

    public MrJobtyCdRdo retrieveMrJoptyCd(String locpCd, String socpCd, String jobgpCd, String drJobtyCd) {
        FnZzGetMrJobtyCdQuery query = new FnZzGetMrJobtyCdQuery(locpCd, socpCd, jobgpCd, drJobtyCd);
        return zzandcdvlSeekClient.retrieveMrJoptyCd(query).getQueryResult();
    }

    public MdexJobtyCdRdo retrieveMdexJoptyCd(String locpCd, String socpCd) {
        FnZzGetMdexJobtyCdQuery query = new FnZzGetMdexJobtyCdQuery(locpCd, socpCd);
        return zzandcdvlSeekClient.retrieveMdexJoptyCd(query).getQueryResult();
    }

    /**
     * 코드번호(CD_NO),코드값(CD_VAL)으로 코드값명 조회 : FN_ZZ_GET_CD_NM
     *
     * @param codeValue : 코드번호, 코드값
     * @return UpperCodeName : 코드번호, 코드값, 코드값명
     */
    public UpperCodeName fnZzGetCdNm(CodeValue codeValue) {
        FnZzGetCdNmQuery query = new FnZzGetCdNmQuery(codeValue);
        return zzandcdvlSeekClient.retrieveCdNm(query).getQueryResult();
    }


    /**
     * 코드번호(CD_NO),코드값(CD_VAL)으로 코드값명 조회 : FN_ZZ_GET_CD_NM
     *
     * @param codeValueList : 코드번호, 코드값
     * @return UpperCodeName : 코드번호, 코드값, 코드값명
     */
    public List<UpperCodeName> fnZzGetCdNmIn(Collection<CodeValue> codeValueList) {
        if (codeValueList == null || codeValueList.isEmpty()) {
            return new ArrayList<>();
        }
        FnZzGetCdNmInQuery query = new FnZzGetCdNmInQuery(codeValueList);
        return zzandcdvlSeekClient.fnZzGetCdNmIn(query).getQueryResult();
    }

    //retrieveZzandcdvlListByCdNo
    public List<Zzandcdvl> retrieveZzandcdvlListByCdNo(String cdNo) {
        if (cdNo == null) {
            return null;
        }
        RetrieveZzandcdvlListByCdNoQuery query = new RetrieveZzandcdvlListByCdNoQuery(cdNo);
        return zzandcdvlSeekClient.retrieveZzandcdvlListByCdNo(query).getQueryResult();
    }

}
