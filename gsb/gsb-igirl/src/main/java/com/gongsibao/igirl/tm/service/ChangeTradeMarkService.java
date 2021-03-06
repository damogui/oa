package com.gongsibao.igirl.tm.service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.core.Oql;
import org.netsharp.organization.base.IEmployeeService;
import org.netsharp.organization.entity.Employee;
import org.netsharp.persistence.session.SessionManager;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.igirl.tm.ChangeTradeMark;
import com.gongsibao.entity.igirl.tm.dict.ChangeTradeMarkState;
import com.gongsibao.igirl.tm.base.IChangeTradeMarkService;
import com.gongsibao.igirl.tm.dto.ChangeTradeMarkDto;
import com.gongsibao.taurus.util.StringManager;
import com.gongsibao.utils.SupplierSessionManager;

@Service
public class ChangeTradeMarkService extends GsbPersistableService<ChangeTradeMark> implements IChangeTradeMarkService {

    public ChangeTradeMarkService() {
        super();
        this.type = ChangeTradeMark.class;
    }

    @Override
    public List<ChangeTradeMarkDto> ctmToRobot() {
        Oql oql = new Oql();
        oql.setType(ChangeTradeMark.class);
        oql.setSelects("ChangeTradeMark.*");
        oql.setFilter("changeTradeMarkState=?");
        oql.getParameters().add("changeTradeMarkState", ChangeTradeMarkState.WAIT_CONFIRM.getValue(), Types.INTEGER);
        List<ChangeTradeMark> ctms = this.queryList(oql);
        ChangeTradeMarkDto changeTradeMarkDto;
        List<ChangeTradeMarkDto> ctmDtos = new ArrayList<>();
        for (ChangeTradeMark ctm:ctms){
            changeTradeMarkDto = new ChangeTradeMarkDto();
            changeTradeMarkDto.setAppGjdq(ctm.getWriteType().getText());
            changeTradeMarkDto.setCertCode(ctm.getCertCode());
            changeTradeMarkDto.setChangeType(ctm.getChangeType().getContent());
            changeTradeMarkDto.setTxt_sqrmyzw(ctm.getTxt_sqrmyzw());
            changeTradeMarkDto.setTxt_sqrmyyw(ctm.getTxt_sqrmyyw());
            changeTradeMarkDto.setTxt_sqrdzzw(ctm.getTxt_sqrdzzw());
            changeTradeMarkDto.setTxt_sqrdzyw(ctm.getTxt_sqrdzyw());
            changeTradeMarkDto.setTxt_yzbm(ctm.getTxt_yzbm());
            changeTradeMarkDto.setTxt_lxr(ctm.getTxt_lxr());
            changeTradeMarkDto.setTxt_dh(ctm.getTxt_dh());
            changeTradeMarkDto.setTxt_dlrmc(ctm.getTxt_dlrmc());
            changeTradeMarkDto.setAgentBookPath(ctm.getAgentBookPath());
            changeTradeMarkDto.setAgentBookName(getFileName(ctm.getAgentBookPath()));
            changeTradeMarkDto.setAppTypeId(ctm.getApplierType().getText());
            changeTradeMarkDto.setScwjId(ctm.getLanguageType().getText());
            changeTradeMarkDto.setAgentFileNum(ctm.getAgentFileNum());
            changeTradeMarkDto.setCertFilePath(ctm.getCertFilePath());
            changeTradeMarkDto.setCertFileName(getFileName(ctm.getCertFilePath()));
            changeTradeMarkDto.setCertFileENPath(ctm.getCertFileENPath());
            changeTradeMarkDto.setCertFileENName(getFileName(ctm.getCertFileENPath()));
            if (ctm.getCertificateType()!=null){
                changeTradeMarkDto.setCertType(ctm.getCertificateType().getText());
            }else{
                changeTradeMarkDto.setCertType("");
            }
            changeTradeMarkDto.setAppCertificateNum(ctm.getAppCertificateNum());
            changeTradeMarkDto.setAppCertFilePath(ctm.getAppCertFilePath());
            changeTradeMarkDto.setAppCertFileName(getFileName(ctm.getAppCertFilePath()));
            changeTradeMarkDto.setAppCertFileENPath(ctm.getAppCertFileENPath());
            changeTradeMarkDto.setAppCertFileENName(getFileName(ctm.getAppCertFileENPath()));
            changeTradeMarkDto.setIfShareTm(ctm.getIfShareTm());
            changeTradeMarkDto.setTxt_bgqmyzw(ctm.getTxt_bgqmyzw());
            changeTradeMarkDto.setTxt_bgqmyyw(ctm.getTxt_bgqmyyw());
            changeTradeMarkDto.setTxt_bgqdzzw(ctm.getTxt_bgqdzzw());
            changeTradeMarkDto.setTxt_bgqdzyw(ctm.getTxt_bgqdzyw());
            changeTradeMarkDto.setIfBgzmEn(ctm.getProveLanguageType().getContent());
            changeTradeMarkDto.setBgzmFilePath(ctm.getBgzmFilePath());
            changeTradeMarkDto.setBgzmFileName(getFileName(ctm.getBgzmFilePath()));
            changeTradeMarkDto.setBgzmFileENPath(ctm.getBgzmFileENPath());
            changeTradeMarkDto.setBgzmFileENName(getFileName(ctm.getBgzmFileENPath()));
            changeTradeMarkDto.setSblx(ctm.getChangeTradeMarkType().getContent());
            changeTradeMarkDto.setTxt_sbsqh(ctm.getTxt_sbsqh());
            changeTradeMarkDto.setCommentPath(ctm.getCommentPath());
            changeTradeMarkDto.setCommentName(getFileName(ctm.getCommentPath()));
            ctmDtos.add(changeTradeMarkDto);
        }
        return ctmDtos;
    }

    @Override
    public ChangeTradeMark updateCtmState(String agentFileNum, Integer state) {
        Oql oql = new Oql();
        oql.setSelects("ChangeTradeMark.*");
        oql.setType(ChangeTradeMark.class);
        oql.setFilter("agentFileNum = ?");
        oql.getParameters().add("agentFileNum", agentFileNum, Types.INTEGER);
        ChangeTradeMark ctm = this.queryFirst(oql);
        if (ctm!=null){
            ctm.setChangeTradeMarkState(ChangeTradeMarkState.getItem(state));
            ctm.toPersist();
            ctm = super.save(ctm);
        }
        return ctm;
    }

    @Override
    public ChangeTradeMark updateOwner(Integer ctmId, Integer ownerId) {
        IEmployeeService employeeService = ServiceFactory.create(IEmployeeService.class);
        Oql oql = new Oql();
        oql.setSelects("ChangeTradeMark.*");
        oql.setType(ChangeTradeMark.class);
        oql.setFilter("id=?");
        oql.getParameters().add("id",ctmId,Types.INTEGER);
        ChangeTradeMark ctm = this.queryFirst(oql);
        if (ctm!=null){
            ctm.setOwnerId(ownerId);
            Employee employee = employeeService.byId(ownerId);
            ctm.setOwnerName(employee.getName());
            ctm.toPersist();
            ctm = super.save(ctm);
        }
        return ctm;
    }

    public String getFileName(String url){
        if (!StringManager.isNullOrEmpty(url)){
            return url.substring(url.lastIndexOf("/")+1);
        }
        return "";
    }

    @Override
    public ChangeTradeMark save(ChangeTradeMark entity) {
        ChangeTradeMark entity1=entity;
        if(entity1.getEntityState()== EntityState.New) {
            Integer departmentId = SupplierSessionManager.getDepartmentId();
            Integer supplierId = SupplierSessionManager.getSupplierId();
            entity1.setDepartmentId(departmentId);
            entity1.setSupplierId(supplierId);
            entity1.setOwnerId(SessionManager.getUserId());
            entity1.setOwnerName(SessionManager.getUserName());
            entity1.setAgentFileNum(String.valueOf(System.currentTimeMillis()));
        }
        return super.save(entity1);
    }
}
