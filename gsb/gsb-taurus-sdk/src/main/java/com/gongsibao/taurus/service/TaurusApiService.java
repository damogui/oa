package com.gongsibao.taurus.service;

import java.util.List;

import com.gongsibao.taurus.api.*;
import com.gongsibao.taurus.entity.AnnualReport;
import com.gongsibao.taurus.entity.Company;
import com.gongsibao.taurus.entity.CompanyAbnormal;
import com.gongsibao.taurus.entity.CompanyEquity;
import com.gongsibao.taurus.entity.CompanyMortgage;
import com.gongsibao.taurus.entity.Copyright;
import com.gongsibao.taurus.entity.CourtAnnouncement;
import com.gongsibao.taurus.entity.CourtExecutive;
import com.gongsibao.taurus.entity.DishonestInfo;
import com.gongsibao.taurus.entity.EntBranch;
import com.gongsibao.taurus.entity.EntChangeRecord;
import com.gongsibao.taurus.entity.EntInvest;
import com.gongsibao.taurus.entity.EntMember;
import com.gongsibao.taurus.entity.EntRegistry;
import com.gongsibao.taurus.entity.EntShareholder;
import com.gongsibao.taurus.entity.IcpInfo;
import com.gongsibao.taurus.entity.Judgment;
import com.gongsibao.taurus.entity.PatentDesc;
import com.gongsibao.taurus.entity.Patents;
import com.gongsibao.taurus.entity.ReportOutboundInvestment;
import com.gongsibao.taurus.entity.ReportShareholder;
import com.gongsibao.taurus.entity.ReportWebInfo;
import com.gongsibao.taurus.entity.Tm;
import com.gongsibao.taurus.entity.Tmdesc;
import com.gongsibao.taurus.entity.Tmflow;
import com.gongsibao.taurus.entity.WorksCopyright;
import com.gongsibao.taurus.message.ResponseMessage;

public class TaurusApiService {

	/**
	 * @Title: getEntRegistry
	 * @Description: 获取企业注册信息
	 * @param: @param companyName
	 * @param: @return
	 * @return: List<DishonestInfo>
	 * @throws
	 */
	public static EntRegistry getEntRegistry(String companyName) {

		EntRegistryApi api = ApiFactory.create(EntRegistryApi.class);
		api.setCompanyName(companyName);
		ResponseMessage<EntRegistry> response = api.getResponse();
		if (response == null) {

			return null;
		}

		if (response.getList().size() == 0) {

			return null;
		}

		return response.getList().get(0);
	}

	/**
	 * @Title: getAnnualReport
	 * @Description: 获取年报汇总
	 * @param: @param companyName
	 * @param: @return
	 * @return: List<AnnualReport>
	 * @throws
	 */
	public static ResponseMessage<AnnualReport> getAnnualReportList(String companyName, int currentPage, int pageSize) {

		AnnualReportApi api = ApiFactory.create(AnnualReportApi.class);
		api.setCompanyName(companyName);
		api.setCurrentPage(currentPage);
		api.setPageSize(pageSize);
		ResponseMessage<AnnualReport> response = api.getResponse();
		return response;
	}

	/**
	 * @Title: getCopyright
	 * @Description: 获取软件著作权
	 * @param: @param companyName
	 * @param: @return
	 * @return: List<Copyright>
	 * @throws
	 */
	public static ResponseMessage<Copyright> getCopyrightList(String companyName, int currentPage, int pageSize) {

		CopyrightApi api = ApiFactory.create(CopyrightApi.class);
		api.setCompanyName(companyName);
		api.setCurrentPage(currentPage);
		api.setPageSize(pageSize);
		ResponseMessage<Copyright> response = api.getResponse();
		return response;
	}

	/**
	 * @Title: getCourtExecutive
	 * @Description: 获取被执行人信息
	 * @param: @param companyName
	 * @param: @return
	 * @return: List<CourtExecutive>
	 * @throws
	 */
	public static ResponseMessage<CourtExecutive> getCourtExecutiveList(String companyName, int currentPage, int pageSize) {

		CourtExecutiveApi api = ApiFactory.create(CourtExecutiveApi.class);
		api.setCompanyName(companyName);
		api.setCurrentPage(currentPage);
		api.setPageSize(pageSize);
		ResponseMessage<CourtExecutive> response = api.getResponse();
		return response;
	}

	/**
	 * @Title: getCourtExecutive
	 * @Description: 获取失信人信息
	 * @param: @param companyName
	 * @param: @return
	 * @return: List<DishonestInfo>
	 * @throws
	 */
	public static ResponseMessage<DishonestInfo> getDishonestInfoList(String companyName, int currentPage, int pageSize) {

		DishonestInfoApi api = ApiFactory.create(DishonestInfoApi.class);
		api.setCompanyName(companyName);
		api.setCurrentPage(currentPage);
		api.setPageSize(pageSize);
		ResponseMessage<DishonestInfo> response = api.getResponse();
		return response;
	}

	/**
	 * @Title: getEntBranch
	 * @Description: 获取分支机构
	 * @param: @param companyName
	 * @param: @return
	 * @return: List<DishonestInfo>
	 * @throws
	 */
	public static ResponseMessage<EntBranch> getEntBranchList(String companyName, int currentPage, int pageSize) {

		EntBranchApi api = ApiFactory.create(EntBranchApi.class);
		api.setCompanyName(companyName);
		api.setCurrentPage(currentPage);
		api.setPageSize(pageSize);
		ResponseMessage<EntBranch> response = api.getResponse();
		return response;
	}

	/**
	 * @Title: getEntChangeRecord
	 * @Description: 获取企业变更记录
	 * @param: @param companyName
	 * @param: @return
	 * @return: List<DishonestInfo>
	 * @throws
	 */
	public static ResponseMessage<EntChangeRecord> getEntChangeRecordList(String companyName, int currentPage, int pageSize) {

		EntChangeRecordApi api = ApiFactory.create(EntChangeRecordApi.class);
		api.setCompanyName(companyName);
		api.setCurrentPage(currentPage);
		api.setPageSize(pageSize);
		ResponseMessage<EntChangeRecord> response = api.getResponse();
		return response;
	}

	/**
	 * @Title: getEntInvest
	 * @Description: 获取对外投资
	 * @param: @param companyName
	 * @param: @return
	 * @return: List<DishonestInfo>
	 * @throws
	 */
	public static ResponseMessage<EntInvest> getEntInvestList(String companyName, int currentPage, int pageSize) {

		EntInvestApi api = ApiFactory.create(EntInvestApi.class);
		api.setCompanyName(companyName);
		api.setCurrentPage(currentPage);
		api.setPageSize(pageSize);
		ResponseMessage<EntInvest> response = api.getResponse();
		return response;
	}

	/**
	 * @Title: getEntMember
	 * @Description: 获取主要人员信息
	 * @param: @param companyName
	 * @param: @return
	 * @return: List<DishonestInfo>
	 * @throws
	 */
	public static ResponseMessage<EntMember> getEntMemberList(String companyName, int currentPage, int pageSize) {

		EntMemberApi api = ApiFactory.create(EntMemberApi.class);
		api.setCompanyName(companyName);
		api.setCurrentPage(currentPage);
		api.setPageSize(pageSize);
		ResponseMessage<EntMember> response = api.getResponse();
		return response;
	}

	/**
	 * @Title: getEntShareholder
	 * @Description: 获取股东信息信息
	 * @param: @param companyName
	 * @param: @return
	 * @return: List<DishonestInfo>
	 * @throws
	 */
	public static ResponseMessage<EntShareholder> getEntShareholderList(String companyName, int currentPage, int pageSize) {

		EntShareholderApi api = ApiFactory.create(EntShareholderApi.class);
		api.setCompanyName(companyName);
		api.setCurrentPage(currentPage);
		api.setPageSize(pageSize);
		ResponseMessage<EntShareholder> response = api.getResponse();
		return response;
	}

	/**
	 * @Title: getEntRegistry
	 * @Description: 获取ICP 信息
	 * @param: @param companyName
	 * @param: @return
	 * @return: List<DishonestInfo>
	 * @throws
	 */
	public static ResponseMessage<IcpInfo> getIcpInfoList(String companyName, int currentPage, int pageSize) {

		IcpInfoApi api = ApiFactory.create(IcpInfoApi.class);
		api.setCompanyName(companyName);
		api.setCurrentPage(currentPage);
		api.setPageSize(pageSize);
		ResponseMessage<IcpInfo> response = api.getResponse();
		return response;
	}

	/**
	 * @Title: getJudgment
	 * @Description: 获取法院判决信息
	 * @param: @param companyName
	 * @param: @return
	 * @return: List<DishonestInfo>
	 * @throws
	 */
	public static ResponseMessage<Judgment> getJudgmentList(String companyName, int currentPage, int pageSize) {

		JudgmentApi api = ApiFactory.create(JudgmentApi.class);
		api.setCompanyName(companyName);
		api.setCurrentPage(currentPage);
		api.setPageSize(pageSize);
		ResponseMessage<Judgment> response = api.getResponse();
		return response;
	}

	/**
	 * @Title: getPatents
	 * @Description: 获取专利信息
	 * @param: @param companyName
	 * @param: @return
	 * @return: List<DishonestInfo>
	 * @throws
	 */
	public static ResponseMessage<Patents> getPatentsList(String companyName, int currentPage, int pageSize) {

		PatentsApi api = ApiFactory.create(PatentsApi.class);
		api.setCompanyName(companyName);
		api.setCurrentPage(currentPage);
		api.setPageSize(pageSize);
		ResponseMessage<Patents> response = api.getResponse();
		return response;
	}

	/**
	 * @Title: getTm
	 * @Description: 获取商标
	 * @param: @param companyName
	 * @param: @return
	 * @return: List<DishonestInfo>
	 * @throws
	 */
	public static ResponseMessage<Tm> getTmList(String companyName, int currentPage, int pageSize) {

		TmApi api = ApiFactory.create(TmApi.class);
		api.setCompanyName(companyName);
		api.setCurrentPage(currentPage);
		api.setPageSize(pageSize);
		ResponseMessage<Tm> response = api.getResponse();
		return response;
	}


	/**
	 * @Title: getTm
	 * @Description: 通过名称获取商标列表
	 * @param: @param tmName
	 * @param: @return
	 * @return: List<DishonestInfo>
	 * @throws
	 */
	public static ResponseMessage<Tm> getTmByName(String tmName, int currentPage, int pageSize) {
		TmByNameApi api = ApiFactory.create(TmByNameApi.class);
		api.setTmName(tmName);
		api.setCurrentPage(currentPage);
		api.setPageSize(pageSize);
		ResponseMessage<Tm> response = api.getResponse();
		return response;
	}

	/**
	 * @Title: getWorksCopyright
	 * @Description: 获取作品著作权
	 * @param: @param companyName
	 * @param: @return
	 * @return: List<DishonestInfo>
	 * @throws
	 */
	public static ResponseMessage<WorksCopyright> getWorksCopyrightList(String companyName, int currentPage, int pageSize) {

		WorksCopyrightApi api = ApiFactory.create(WorksCopyrightApi.class);
		api.setCompanyName(companyName);
		api.setCurrentPage(currentPage);
		api.setPageSize(pageSize);
		ResponseMessage<WorksCopyright> response = api.getResponse();
		return response;
	}

	/**
	 * @Title: getPatentDesc
	 * @Description: 获取专利详情
	 * @param: @param companyName
	 * @param: @return
	 * @return: List<DishonestInfo>
	 * @throws
	 */
	public static PatentDesc getPatentDesc(String patentId) {

		PatentDescApi api = ApiFactory.create(PatentDescApi.class);
		api.setPatentId(patentId);
		ResponseMessage<PatentDesc> response = api.getResponse();
		if (response == null) {

			return null;
		}

		List<PatentDesc> list = response.getList();
		if (list.size() == 0) {

			return null;
		}
		return list.get(0);
	}

	/**
	 * @Title: getTmdesc
	 * @Description: 获取商标详情
	 * @param: @param companyName
	 * @param: @return
	 * @return: List<DishonestInfo>
	 * @throws
	 */
	public static Tmdesc getTmdesc(int tmId) {

		TmdescApi api = ApiFactory.create(TmdescApi.class);
		api.setTmId(tmId);
		ResponseMessage<Tmdesc> response = api.getResponse();
		if (response == null) {

			return null;
		}

		List<Tmdesc> list = response.getList();
		if (list.size() == 0) {

			return null;
		}
		return list.get(0);
	}

	/**
	 * @Title: getReportWebInfo
	 * @Description: 获取商标流程
	 * @param: @param companyName
	 * @param: @return
	 * @return: List<DishonestInfo>
	 * @throws
	 */
	public static ResponseMessage<Tmflow> getTmflowList(int tmId, int currentPage, int pageSize) {

		TmflowApi api = ApiFactory.create(TmflowApi.class);
		api.setTmId(tmId);
		api.setCurrentPage(currentPage);
		api.setPageSize(pageSize);
		ResponseMessage<Tmflow> response = api.getResponse();
		if (response == null) {

			return null;
		}
		return response;
	}

	/**
	 * @Title: getReportShareholderList
	 * @Description: TODO(获取年报股东出资信息)
	 * @param: @param annualReportId
	 * @param: @param currentPage
	 * @param: @param pageSize
	 * @param: @return
	 * @return: ResponseMessage<ReportShareholder>
	 * @throws
	 */
	public static ResponseMessage<ReportShareholder> getReportShareholderList(int annualReportId, int currentPage, int pageSize) {

		ReportShareholderApi api = ApiFactory.create(ReportShareholderApi.class);
		api.setAnnualReportId(annualReportId);
		api.setCurrentPage(currentPage);
		api.setPageSize(pageSize);
		ResponseMessage<ReportShareholder> response = api.getResponse();
		if (response == null) {

			return null;
		}
		return response;
	}

	/**
	 * @Title: getReportOutboundInvestmentList
	 * @Description: TODO(获取年报对外投资)
	 * @param: @param annualReportId
	 * @param: @param currentPage
	 * @param: @param pageSize
	 * @param: @return
	 * @return: ResponseMessage<ReportOutboundInvestment>
	 * @throws
	 */
	public static ResponseMessage<ReportOutboundInvestment> getReportOutboundInvestmentList(int annualReportId, int currentPage, int pageSize) {

		ReportOutboundInvestmentApi api = ApiFactory.create(ReportOutboundInvestmentApi.class);
		api.setAnnualReportId(annualReportId);
		api.setCurrentPage(currentPage);
		api.setPageSize(pageSize);
		ResponseMessage<ReportOutboundInvestment> response = api.getResponse();
		if (response == null) {

			return null;
		}
		return response;
	}

	/**
	 * @Title: getReportWebInfoList
	 * @Description: TODO(获取年报网站信息)
	 * @param: @param annualReportId
	 * @param: @param currentPage
	 * @param: @param pageSize
	 * @param: @return
	 * @return: ResponseMessage<ReportWebInfo>
	 * @throws
	 */
	public static ResponseMessage<ReportWebInfo> getReportWebInfoList(int annualReportId, int currentPage, int pageSize) {

		ReportWebInfoApi api = ApiFactory.create(ReportWebInfoApi.class);
		api.setAnnualReportId(annualReportId);
		api.setCurrentPage(currentPage);
		api.setPageSize(pageSize);
		ResponseMessage<ReportWebInfo> response = api.getResponse();
		if (response == null) {

			return null;
		}
		return response;
	}

	/**
	 * @Title: getEntList
	 * @Description: TODO(模糊查询公司信息)
	 * @param: @param companyName
	 * @param: @param currentPage
	 * @param: @param pageSize
	 * @param: @return
	 * @return: ResponseMessage<Company>
	 * @throws
	 */
	public static ResponseMessage<Company> getEntList(String companyName, int currentPage, int pageSize) {

		EntSearchApi api = ApiFactory.create(EntSearchApi.class);
		api.setCompanyName(companyName);
		api.setCurrentPage(currentPage);
		api.setPageSize(pageSize);
		ResponseMessage<Company> response = api.getResponse();
		if (response == null) {

			return null;
		}
		return response;
	}

	public static ResponseMessage<CompanyAbnormal> getAbnormalList(String companyName, int currentPage, int pageSize) {

		CompanyAbnormalApi api = ApiFactory.create(CompanyAbnormalApi.class);
		api.setCompanyName(companyName);
		api.setCurrentPage(currentPage);
		api.setPageSize(pageSize);
		ResponseMessage<CompanyAbnormal> response = api.getResponse();
		if (response == null) {

			return null;
		}

		// ResponseMessage<CompanyAbnormal> response = new
		// ResponseMessage<CompanyAbnormal>();
		// List<CompanyAbnormal> list = new ArrayList<CompanyAbnormal>();
		// {
		// CompanyAbnormal entity = new CompanyAbnormal();
		// {
		//
		// entity.setId(222078129L);
		// entity.setPutDate("2015-08-10 00:00:00");
		// entity.setRemoveDate("2016-07-29 00:00:00");
		// entity.setPutDepartment("梅河口市市场监督管理局");
		// entity.setRemoveDepartment("梅河口市市场监督管理局");
		// entity.setRemoveReason("列入经营异常名录3年内且依照《经营异常名录管理办法》第六条规定被列入经营异常名录的企业，可以在补报未报年份的年度报告并公示后，申请移出");
		// }
		// list.add(entity);
		//
		// entity = new CompanyAbnormal();
		// {
		//
		// entity.setId(222078130L);
		// entity.setPutDate("2015-08-10 00:00:00");
		// entity.setRemoveDate("2016-08-02 00:00:00");
		// entity.setPutDepartment("梅河口市市场监督管理局");
		// entity.setRemoveDepartment("梅河口市市场监督管理局");
		// entity.setRemoveReason("列入经营异常名录3年内且依照《经营异常名录管理办法》第六条规定被列入经营异常名录的企业，可以在补报未报年份的年度报告并公示后，申请移出");
		// }
		// list.add(entity);
		// }
		// response.setList(list);
		return response;
	}

	public static ResponseMessage<CompanyEquity> getEquityList(String companyName, int currentPage, int pageSize) {

		CompanyEquityApi api = ApiFactory.create(CompanyEquityApi.class);
		api.setCompanyName(companyName);
		api.setCurrentPage(currentPage);
		api.setPageSize(pageSize);
		ResponseMessage<CompanyEquity> response = api.getResponse();
		if (response == null) {

			return null;
		}

		// ResponseMessage<CompanyEquity> response = new
		// ResponseMessage<CompanyEquity>();
		// List<CompanyEquity> list = new ArrayList<CompanyEquity>();
		// {
		// CompanyEquity entity = new CompanyEquity();
		// {
		//
		// entity.setId(13890154L);
		// entity.setRegNumber("110108002734659_0002");
		// entity.setPledgor("李彦宏");
		// entity.setPledgee("百度在线网络技术（北京）有限公司");
		// entity.setEquityAmount("9950 万元");
		// entity.setRegDate("2011-12-22 00:00:00");
		// entity.setState("有效");
		// }
		// list.add(entity);
		//
		// entity = new CompanyEquity();
		// {
		//
		// entity.setId(13890155L);
		// entity.setRegNumber("110108002734659_0003");
		// entity.setPledgor("王湛");
		// entity.setPledgee("百度在线网络技术（北京）有限公司");
		// entity.setEquityAmount("50 万元");
		// entity.setRegDate("2017-05-09 00:00:00");
		// entity.setState("无效");
		// }
		// list.add(entity);
		// }
		// response.setList(list);
		return response;
	}

	public static ResponseMessage<CompanyMortgage> getMortgageList(String companyName, int currentPage, int pageSize) {

		CompanyMortgageApi api = ApiFactory.create(CompanyMortgageApi.class);
		api.setCompanyName(companyName);
		api.setCurrentPage(currentPage);
		api.setPageSize(pageSize);
		ResponseMessage<CompanyMortgage> response = api.getResponse();
		if (response == null) {

			return null;
		}

		// ResponseMessage<CompanyMortgage> response = new
		// ResponseMessage<CompanyMortgage>();
		// List<CompanyMortgage> list = new ArrayList<CompanyMortgage>();
		// {
		// CompanyMortgage entity = new CompanyMortgage();
		// {
		//
		// entity.setId(5662L);
		// entity.setRegNum("黑克山工商动抵登字（2015）第35号");
		// entity.setRegDate("2015-04-29 00:00:00");
		// entity.setRegDepartment("黑龙江省克山县工商行政管理局");
		// entity.setStatus("有效");
		// entity.setAmount("150万元");
		// }
		// list.add(entity);
		//
		// entity = new CompanyMortgage();
		// {
		// entity.setId(8640010L);
		// entity.setRegNum("黑克山工商动抵登字（2015）第35号");
		// entity.setRegDate("2015-04-29 00:00:00");
		// entity.setRegDepartment("克山县市场监督管理局");
		// entity.setStatus("有效");
		// entity.setAmount("150万元");
		// }
		// list.add(entity);
		// }
		// response.setList(list);
		return response;
	}

	public static ResponseMessage<CourtAnnouncement> getCourtAnnouncementList(String companyName, int currentPage, int pageSize) {

		 CourtAnnouncementApi api =
		 ApiFactory.create(CourtAnnouncementApi.class);
		 api.setCompanyName(companyName);
		 api.setCurrentPage(currentPage);
		 api.setPageSize(pageSize);
		 ResponseMessage<CourtAnnouncement> response = api.getResponse();
		 if (response == null) {
		
		 return null;
		 }

		// ResponseMessage<CourtAnnouncement> response = new
		// ResponseMessage<CourtAnnouncement>();
		// List<CourtAnnouncement> list = new ArrayList<CourtAnnouncement>();
		// {
		// CourtAnnouncement entity = new CourtAnnouncement();
		// {
		//
		// entity.setId(80L);
		// entity.setPublishdate("2016-01-06");
		// entity.setBltntype("62");
		// entity.setParty1("安得利（北京）食品贸易有限公司");
		// entity.setContent("北京臻香思餐饮管理有限公司：\n本院对原告安得利（北京）食品贸易有限公司诉你方买卖合同纠纷一案已审理终结。现依法向你方公告送达（2015）朝民（商）初字第29066号民事判决书。被告自公告之日起60日内来本院领取民事判决书，逾期则视为送达。如不服本判决，可在公告期满后15日内，向本院递交上诉状及副本，上诉于北京市第三中级人民法院。逾期未上诉的，本判决即发生法律效力。 \n");
		//
		// }
		// list.add(entity);
		//
		// entity = new CourtAnnouncement();
		// {
		// entity.setId(136753L);
		// entity.setPublishdate("2015-10-31");
		// entity.setBltntype("132");
		// entity.setParty1("北京麦冬仓储有限公司");
		// entity.setContent("北京臻香思餐饮管理有限公司：\n本院受理原告北京麦冬仓储有限公司诉被告北京臻香思餐饮管理有限公司仓储合同纠纷一案，现依法向你公告送达起诉状副本、应诉通知书、开庭传票。自本公告发出之日起经过60日即视为送达。提出答辩状的期限为公告送达期满后次日起15日内。并定于答辩期满后第1个工作日上午9时（遇法定节假日顺延）在本院第44法庭公开开庭审理。 \n");
		//
		// }
		// list.add(entity);
		//
		// entity = new CourtAnnouncement();
		// {
		// entity.setId(147330L);
		// entity.setPublishdate("2015-10-24");
		// entity.setBltntype("132");
		// entity.setParty1("于爱匣");
		// entity.setContent("北京臻香思餐饮管理有限公司：                   \n本院受理的（2015）朝民（商）初字第33814号原告于爱匣诉被告北京臻香思餐饮管理有限公司合伙协议纠纷一案，现依法向你公告送达民事起诉状副本、开庭传票、应诉通知书、举证通知书。原告的诉讼请求为：1、确认原被告于2014年5月16日签订的《合伙协议》已解除；2、被告返还原告出资款20万元；3、诉讼费被告承担。自公告发出之日起60日视为送达。提出答辩状和举证的时限为送达期满后的15日内。并定于举证期满后第1个工作日上午9时（如遇法定节假日顺延）在北京市朝阳区华威北里甲14号民四庭开庭审理，逾期将依法缺席判决。\n");
		//
		// }
		// list.add(entity);
		// }
		// response.setList(list);
		return response;
	}
}