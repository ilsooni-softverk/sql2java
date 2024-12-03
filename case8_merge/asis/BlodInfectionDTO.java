/**
 * @desc 혈액노출사고 DTO
 *
 * @author 조용석
 * @version 1.0, 2018/04/09
 * @see
 */
package kr.amc.amis.ca.eh.sh.entity;

import kr.amc.amil.message.dto.AbstractDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BlodInfectionDTO extends AbstractDTO {

    // 사고신고번호
    private String accidDclarNo;

    // 사고신고접수구분코드
    private String accidDclarRcepDivCd;

    // 검사대상결과유형코드
    private String examTrgetRsltTypCd;

    // 사고명
    private String accidNm;

    // 사고발생일시
    private String accidOccurDtm;

    // 혈액노출사고장소사용코드
    private String blodExpoAccidLocUseCd;

    // 혈액노출사고장소사용코드
    private String blodExpoAccidLocUseNm;

    // 혈액노출사고부위코드
    private String blodExpoAccidSiteCd;

    // 혈액노출사고부위코드
    private String blodExpoAccidSiteNm;

    // 혈액노출사고유형코드
    private String blodExpoAccidTypCd;

    // 혈액노출사고유형코드
    private String blodExpoAccidTypNm;

    // 혈액노출사고경위코드
    private String blodExpoAccidCrcsCd;

    // 혈액노출사고경위코드
    private String blodExpoAccidCrcsNm;

    // 직원노출경위사유
    private String empExpoCrcsRsn;

    // 사고손상정도코드
    private String accidDamgDgreeCd;

    // 사고손상정도코드
    private String accidDamgDgreeNm;

    // 노출사용기구코드
    private String expoUseUtenslCd;

    // 노출사용기구코드
    private String expoUseUtenslNm;

    // 원인체정보유무
    private String csbdInfoPa;

    // 원인체명
    private String csbdNm;

    // 병원등록번호
    private String patno;

    // 원인체성별코드
    private String csbdSexCd;

    // 원인체나이
    private Long csbdAge;

    // 원인체진료과코드
    private String csbdMddpCd;

    // 원인체진료과코드
    private String csbdMddpNm;

    // 원인체병동코드
    private String csbdWardCd;

    // 원인체병동코드
    private String csbdWardNm;

    // 원인체진단명
    private String csbdDiagNm;

    // 검사조치필요여부
    private String examTkactNeedYn;

    // 투약필요여부
    private String mdctNeedYn;

    // 추후조치필요여부
    private String ltrTkactNeedYn;

    // 조치완료여부
    private String tkactFinshYn;

    // 특기사항
    private String rmrk;

    // 조치장소코드
    private String tkactLocCd;

    // 조치일시
    private String tkactFinshDtm;

    // 조치자사번
    private String tkactFinshEmpno;
    
    // 조치자성명
    private String tkactFinshEmpNm;
    
    // 외래진료여부
    private String opadMdexYn;

    // HIV노출여부
    private String hivExpoYn;

    // 매독노출여부
    private String syphExpoYn;

    // 기타질환노출여부
    private String etcDssExpoYn;
    
    // 기타질환노출내용
    private String etcDssExpoCnte;
    
    // 진행
    private String progrs;
}
