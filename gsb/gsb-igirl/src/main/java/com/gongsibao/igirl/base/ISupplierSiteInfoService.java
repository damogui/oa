package com.gongsibao.igirl.base;
import com.gongsibao.entity.igirl.baseinfo.SupplierSiteInfo;
import com.gongsibao.igirl.dto.SiteInfoDto;
import org.netsharp.base.IPersistableService;
public interface ISupplierSiteInfoService extends IPersistableService<SupplierSiteInfo> {
	public SiteInfoDto fetchSiteInfo(Integer supplierId);

}