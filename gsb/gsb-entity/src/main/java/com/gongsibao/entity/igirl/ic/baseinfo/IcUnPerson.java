package com.gongsibao.entity.igirl.ic.baseinfo;

import com.gongsibao.entity.igirl.ic.IcRegisterCase;
import com.gongsibao.entity.igirl.ic.dict.CorpType;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

import java.util.List;

@Table(name = "ic_un_person",header = "非自然人股东")
public class IcUnPerson extends Entity {
    @Column(name = "name",header = "名称")
    private String name;

    @Column(name = "inv_type",header = "单位类型")
    private CorpType invType;

    @Column(name = "entLic_type_id",header = "证照类型Id")
    private Integer entLicTypeId;

    @Reference(foreignKey = "entLicTypeId",header = "证照类型")
    private EntLicType entLicType;

    @Column(name = "nationalityId",header = "国别和地区Id")
    private Integer nationalityId;

    @Reference(foreignKey = "nationalityId",header = "国别和地区")
    private Nationality nationality;

    @Column(name = "corp_rpt",header = "法定代表人")
    private String corpRpt;

    @Column(name = "prov_id",header = "身份证登记住址,省Id")
    private Integer provId;

    @Reference(foreignKey = "provId",header = "身份证登记住址,省")
    private AreaOne prov;

    @Column(name = "city_id",header = "身份证登记住址区域Id")
    private Integer cityId;

    @Reference(foreignKey = "cityId",header = "身份证登记住址区域")
    private AreaTwo city;

    @Column(name = "dom_other",header = "身份证登记住址街道")
    private String domOther;

    @Column(name = "ic_register_case_id",header = "工商注册案子Id")
    private Integer registerCaseId;

    @Reference(foreignKey = "registerCaseId",header = "工商注册案子")
    private IcRegisterCase registerCase;

    @Subs(foreignKey = "unPersonId",header = "非自然人股东",subType = IcCost.class)
    private List<IcCost> costs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CorpType getInvType() {
        return invType;
    }

    public void setInvType(CorpType invType) {
        this.invType = invType;
    }

    public EntLicType getEntLicType() {
        return entLicType;
    }

    public void setEntLicType(EntLicType entLicType) {
        this.entLicType = entLicType;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    public String getCorpRpt() {
        return corpRpt;
    }

    public void setCorpRpt(String corpRpt) {
        this.corpRpt = corpRpt;
    }

    public Integer getProvId() {
        return provId;
    }

    public void setProvId(Integer provId) {
        this.provId = provId;
    }

    public AreaOne getProv() {
        return prov;
    }

    public void setProv(AreaOne prov) {
        this.prov = prov;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public AreaTwo getCity() {
        return city;
    }

    public void setCity(AreaTwo city) {
        this.city = city;
    }

    public String getDomOther() {
        return domOther;
    }

    public void setDomOther(String domOther) {
        this.domOther = domOther;
    }

    public Integer getEntLicTypeId() {
        return entLicTypeId;
    }

    public void setEntLicTypeId(Integer entLicTypeId) {
        this.entLicTypeId = entLicTypeId;
    }

    public Integer getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(Integer nationalityId) {
        this.nationalityId = nationalityId;
    }

    public List<IcCost> getCosts() {
        return costs;
    }

    public void setCosts(List<IcCost> costs) {
        this.costs = costs;
    }

    public Integer getRegisterCaseId() {
        return registerCaseId;
    }

    public void setRegisterCaseId(Integer registerCaseId) {
        this.registerCaseId = registerCaseId;
    }

    public IcRegisterCase getRegisterCase() {
        return registerCase;
    }

    public void setRegisterCase(IcRegisterCase registerCase) {
        this.registerCase = registerCase;
    }
}
