package com.gongsibao.entity.yj;

import org.netsharp.core.annotations.Auto;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Id;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Persistable;

@Table(name="yj_company_player_employe_map")
public class CompanyPlayerEmployeMap extends Persistable {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 4967414383392078951L;
	@Id
	@Auto
	@Column(name="pkid",header="id")
	private Integer id;
	@Column(name="yj_company_id",header="YjCompanyId")
    private Integer yjCompanyId;
    @Column(name="yj_company_player_id",header="YjCompanyPlayerId")
    private Integer yjCompanyPlayerId;
    @Column(header="job")
    private String job;

    public Integer getYjCompanyId() {
        return yjCompanyId;
    }
    public void setYjCompanyId(Integer yjCompanyId) {
        this.yjCompanyId = yjCompanyId;
    }
    public Integer getYjCompanyPlayerId() {
        return yjCompanyPlayerId;
    }
    public void setYjCompanyPlayerId(Integer yjCompanyPlayerId) {
        this.yjCompanyPlayerId = yjCompanyPlayerId;
    }
    public String getJob() {
        return job;
    }
    public void setJob(String job) {
        this.job = job;
    }
}