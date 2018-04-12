package com.gongsibao.entity.igirl.ic.baseinfo;


import com.gongsibao.entity.igirl.ic.dict.CorpInvestment;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name = "ic_cost",header = "出资情况")
public class IcCost extends Entity{
    @Column(name = "money",header = "出资金额,万元")
    private Double money;

    @Column(name = "way",header = "出资方式")
    private CorpInvestment way;

    @Column(name = "time",header = "出资时间")
    private String time;

    @Column(name = "person_id",header = "股东id")
    private Integer personId;

    @Reference(foreignKey = "personId",header = "股东")
    private IcPerson icPerson;

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public CorpInvestment getWay() {
        return way;
    }

    public void setWay(CorpInvestment way) {
        this.way = way;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public IcPerson getIcPerson() {
        return icPerson;
    }

    public void setIcPerson(IcPerson icPerson) {
        this.icPerson = icPerson;
    }
}
