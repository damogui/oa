package com.gongsibao.entity.igirl.ic.baseinfo;


import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

import java.util.List;

@Table(name = "ic_member",header = "主要成员信息")
public class IcMember extends Entity{
    @Column(name = "executive",header = "是否设立董事会")
    private String executive;

    @Subs(foreignKey = "memberId",header = "董事",subType = IcDirector.class)
    private List<IcDirector> directors;

    @Subs(foreignKey = "memberId",header = "经理",subType = IcManager.class)
    private List<IcManager> managers;

    @Column(name = "name",header = "法人名字")
    private String name;

    //supervisorNotSet:不设立监事会,监事人数1-2人     supervisorYesSet:设立监事会,监事人数至少为3人,其中设监事会主席1人
    @Column(name = "supervisor",header = "是否设立监事会")
    private String supervisor;

    @Subs(foreignKey = "memberId",header = "监事",subType = IcConductor.class)
    private List<IcConductor> conductors;

    public String getExecutive() {
        return executive;
    }

    public void setExecutive(String executive) {
        this.executive = executive;
    }

    public List<IcDirector> getDirectors() {
        return directors;
    }

    public void setDirectors(List<IcDirector> directors) {
        this.directors = directors;
    }

    public List<IcManager> getManagers() {
        return managers;
    }

    public void setManagers(List<IcManager> managers) {
        this.managers = managers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public List<IcConductor> getConductors() {
        return conductors;
    }

    public void setConductors(List<IcConductor> conductors) {
        this.conductors = conductors;
    }
}
