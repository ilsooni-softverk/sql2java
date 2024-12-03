/*
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package kr.amc.amis.ehs.aggregate.accident.domain.entity.sdo;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.AllArgsConstructor;
import io.vizend.accent.domain.entity.CreationDataObject;
import io.vizend.accent.util.json.JsonUtil;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class EhshrbarrCdo extends CreationDataObject {
    //사고신고번호
    private String accidDclarNo;

    //사고신고접수구분코드
    private String accidDclarRcepDivCd;

    //사고명
    private String accidNm;

    //사고발생일시
    private LocalDateTime accidOccurDtm;

    //혈액노출사고장소사용코드
    private String blodExpoAccidLocUseCd;

    //혈액노출사고부위코드
    private String blodExpoAccidSiteCd;

    //혈액노출사고유형코드
    private String blodExpoAccidTypCd;

    //혈액노출사고경위코드
    private String blodExpoAccidCrcsCd;

    //직원노출경위사유
    private String empExpoCrcsRsn;

    //사고손상정도코드
    private String accidDamgDgreeCd;

    //노출사용기구코드
    private String expoUseUtenslCd;

    //원인체정보유무
    private String csbdInfoPa;

    //원인체명
    private String csbdNm;

    //병원등록번호
    private String patno;

    //원인체성별코드
    private String csbdSexCd;

    //원인체나이
    private Long csbdAge;

    //원인체진료과코드
    private String csbdMddpCd;

    //원인체병동코드
    private String csbdWardCd;

    //원인체진단명
    private String csbdDiagNm;

    //HIV노출여부
    private String hivExpoYn;

    //매독노출여부
    private String syphExpoYn;

    //기타질환노출여부
    private String etcDssExpoYn;

    //기타질환노출내용
    private String etcDssExpoCnte;

    //검사조치필요여부
    private String examTkactNeedYn;

    //외래진료여부
    private String opadMdexYn;

    //투약필요여부
    private String mdctNeedYn;

    //추후조치필요여부
    private String ltrTkactNeedYn;

    //조치완료여부
    private String tkactFinshYn;

    //조치장소코드
    private String tkactLocCd;

    //조치완료일시
    private LocalDateTime tkactFinshDtm;

    //조치완료사번
    private String tkactFinshEmpno;

    //특기사항
    private String rmrk;


    public EhshrbarrCdo() {
        this.csbdInfoPa = "N";
        this.hivExpoYn = "N";
        this.syphExpoYn = "N";
        this.etcDssExpoYn = "N";
        this.etcDssExpoCnte = null;
        this.examTkactNeedYn = "N";
        this.opadMdexYn = "N";
        this.mdctNeedYn = "N";
        this.ltrTkactNeedYn = "N";
        this.tkactFinshYn = "N";
    }

    @Override
    public String genId() {
        // 
        return super.genId();
    }

    @Override
    public String toString() {
        // 
        return toJson();
    }

    public static EhshrbarrCdo fromJson(String json) {
        // 
        return JsonUtil.fromJson(json, EhshrbarrCdo.class);
    }

    public static EhshrbarrCdo sample() {
        // 
        return new EhshrbarrCdo();
    }

    public static void main(String[] args) {
        // 
        System.out.println(sample().toPrettyJson());
    }
}
