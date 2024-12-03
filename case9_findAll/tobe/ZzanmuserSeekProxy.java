package kr.amc.amis.ehs.proxy.query.zzanquery.an.zzanmuser;

import kr.amc.amis.ehs.proxy.query.zzanquery.an.zzanmuser.domain.sdo.UserNameRdo;
import kr.amc.amis.ehs.proxy.query.zzanquery.an.zzanmuser.client.ZzanmuserSeekClient;
import kr.amc.amis.ehs.proxy.query.zzanquery.an.zzanmuser.domain.entity.Zzanmuser;
import kr.amc.amis.ehs.proxy.query.zzanquery.an.zzanmuser.domain.sdo.RiskEvaluationTargetDepartmentBasicRdo;
import kr.amc.amis.ehs.proxy.query.zzanquery.an.zzanmuser.query.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ZzanmuserSeekProxy {
    private final ZzanmuserSeekClient zzanmuserSeekClient;

    public List<Zzanmuser> retrieveZzanmuserList(Collection<String> usrIds) {
        if (usrIds == null || usrIds.isEmpty()) return new ArrayList<>();
        RetrieveZzanmuserListQuery query = new RetrieveZzanmuserListQuery(usrIds);
        return zzanmuserSeekClient.retrieveZzanmuserList(query).getQueryResult();
    }

    public Zzanmuser retrieveZzanmuser(String usrId) {
        if (usrId == null || usrId.isEmpty()) return null;
        RetrieveZzanmuserQuery query = new RetrieveZzanmuserQuery(usrId);
        return zzanmuserSeekClient.retrieveZzanmuser(query).getQueryResult();
    }

    /**
     * mapperId : retrieveRiskEvalTrgetDeptBasic2
     */
    public RiskEvaluationTargetDepartmentBasicRdo retrieveRiskEvaluationTargetDepartmentBasic(String blngUnitCd, String hlogDivCd) {
        RetrieveRiskEvaluationTargetDepartmentBasicQuery query = new RetrieveRiskEvaluationTargetDepartmentBasicQuery(blngUnitCd, hlogDivCd);
        return zzanmuserSeekClient.retrieveRiskEvaluationTargetDepartmentBasic(query).getQueryResult();
    }

    public List<Zzanmuser> retrieveZzanmuserListByRealWorkUnitCdAndHlogDivCdAndUseYn(String realWorkUnitCd, String hlogDivCd, String useYn) {
        RetrieveZzanmuserListByRealWorkUnitCdAndHlogDivCdAndUseYnQuery query = new RetrieveZzanmuserListByRealWorkUnitCdAndHlogDivCdAndUseYnQuery(realWorkUnitCd, hlogDivCd, useYn);
        return zzanmuserSeekClient.retrieveZzanmuserListByRealWorkUnitCdAndHlogDivCdAndUseYn(query).getQueryResult();
    }

    public List<Zzanmuser> retrieveZzanmuserListByUsrIdInAndKorNmLike(Collection<String> usrIds, String korNm) {
        RetrieveZzanmuserListByUsrIdInAndKorNmLikeQuery query = new RetrieveZzanmuserListByUsrIdInAndKorNmLikeQuery(usrIds, korNm);
        return zzanmuserSeekClient.retrieveZzanmuserListByUsrIdInAndKorNmLike(query).getQueryResult();
    }

    public List<Zzanmuser> retrieveZzanmuserListByUseYnAndRealWorkInitCdAndRealWorkDeptCdUserInfo(String useYn, String usrId, String korNm, String realWorkUnitCd, String realWorkDeptCd) {
        RetrieveZzanmuserListByUseYnAndRealWorkInitCdAndRealWorkDeptCdUserInfoQuery query = new RetrieveZzanmuserListByUseYnAndRealWorkInitCdAndRealWorkDeptCdUserInfoQuery(useYn, usrId, korNm, realWorkUnitCd, realWorkDeptCd);
        return zzanmuserSeekClient.retrieveZzanmuserListByUseYnAndRealWorkInitCdAndRealWorkDeptCdUserInfo(query).getQueryResult();
    }

    public List<Zzanmuser> retrieveVzzanmuserListByUseYnAndUsrIdAndKorNm(String useYn, String usrId, String korNm) {
        RetrieveVzzanmuserListByUseYnAndUsrIdAndKorNmQuery query = new RetrieveVzzanmuserListByUseYnAndUsrIdAndKorNmQuery(useYn, usrId, korNm);
        return zzanmuserSeekClient.retrieveVzzanmuserListByUseYnAndUsrIdAndKorNm(query).getQueryResult();
    }

    public List<Zzanmuser> retrieveVzzanmuserListByUseYnAndUsrId(String useYn, String usrId) {
        RetrieveVzzanmuserListByUseYnAndUsrIdQuery query = new RetrieveVzzanmuserListByUseYnAndUsrIdQuery(useYn, usrId);
        return zzanmuserSeekClient.retrieveVzzanmuserListByUseYnAndUsrId(query).getQueryResult();
    }

    public List<Zzanmuser> retrieveVzzanmuserListByUseYnAndKorNm(String useYn, String usrId) {
        RetrieveVzzanmuserListByUseYnAndKorNmQuery query = new RetrieveVzzanmuserListByUseYnAndKorNmQuery(useYn, usrId);
        return zzanmuserSeekClient.retrieveVzzanmuserListByUseYnAndKorNm(query).getQueryResult();
    }

    public List<Zzanmuser> retrieveZzanmuserListByUseYn(String useYn) {
        RetrieveZzanmuserListByUseYnQuery query = new RetrieveZzanmuserListByUseYnQuery(useYn);
        return zzanmuserSeekClient.retrieveZzanmuserListByUseYn(query).getQueryResult();
    }

    public List<Zzanmuser> retrieveZzanmuserListByUsrIdAndLoginUsrTypCdAndBlngDeptCdAndHlogDivCdAndUseYn(String usrId, String loginUsrTypCd, String blngDeptCd, String hlogDivCd, String useYn) {
        RetrieveZzanmuserListByUsrIdAndLoginUsrTypCdAndBlngDeptCdAndHlogDivCdAndUseYnQuery query = new RetrieveZzanmuserListByUsrIdAndLoginUsrTypCdAndBlngDeptCdAndHlogDivCdAndUseYnQuery(usrId, loginUsrTypCd, blngDeptCd, hlogDivCd, useYn);
        return zzanmuserSeekClient.retrieveZzanmuserListByUsrIdAndLoginUsrTypCdAndBlngDeptCdAndHlogDivCdAndUseYn(query).getQueryResult();
    }

    /**
     * 사용자아이디로 사용자명 목록 조회 (한글) : FN_ZZ_GET_USER_NM LIST
     *
     * @param empNos : 사용자ID
     * @return List<UserNameRdo> : 사용자ID, 사용자명(한글)
     */

    public List<UserNameRdo> fnZzGetKorUserNmByUsrIdIn(Collection<String> empNos) {
        if (empNos == null || empNos.isEmpty()) {
            return new ArrayList<>();
        }
        FnZzGetKorUserNmByUsrIdInQuery query = new FnZzGetKorUserNmByUsrIdInQuery(empNos);
        return zzanmuserSeekClient.fnZzGetKorUserNmByUsrIdIn(query).getQueryResult();
    }

    /**
     * 사용자아이디로 사용자명 조회 (한글): FN_ZZ_GET_USER_NM
     *
     * @param empno : 사용자ID
     * @return UserNameRdo : 사용자ID, 사용자명 (한글)
     */
    public UserNameRdo fnZzGetKorUserNm(String empno) {
        FnZzGetKorUserNmQuery query = new FnZzGetKorUserNmQuery(empno);
        return zzanmuserSeekClient.fnZzGetKorUserNm(query).getQueryResult();
    }


}
