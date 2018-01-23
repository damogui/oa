package com.gongsibao.crm.service;

import java.sql.Types;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.bd.service.SupplierPersistableService;
import com.gongsibao.crm.base.INCustomerTaskService;
import com.gongsibao.entity.crm.NCustomerTask;

@Service
public class NCustomerTaskService extends SupplierPersistableService<NCustomerTask> implements INCustomerTaskService {
	public NCustomerTaskService() {
		super();
		this.type = NCustomerTask.class;
	}

	@Override
	public int updateInspectionState(Integer taskId, Integer selectValue,
			String getNote) {
		UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("n_crm_customer_task");
			updateSql.set("inspection_state", selectValue);
			updateSql.set("memoto", getNote);
			updateSql.where("id=" + taskId);
		}
		String cmdText = updateSql.toSQL();
		return this.pm.executeNonQuery(cmdText, null);
	}

	@Override
	public int insertHighSeas(Integer taskId) {
		String cmdText = "UPDATE n_crm_customer_task SET salesman_id = NULL where id="+taskId;
		return this.pm.executeNonQuery(cmdText, null);
	}
	

	@Override
	public NCustomerTask byId(Object id){
		
		String selectFields = getSelectFullFields();
		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects(selectFields);
			oql.setFilter("id=?");
			oql.getParameters().add("id", id, Types.INTEGER);
		}

		NCustomerTask entity = this.queryFirst(oql);
		return entity;
	}
	
	private String getSelectFullFields() {

		StringBuilder builder = new StringBuilder();
		builder.append("NCustomerTask.*,");
		builder.append("NCustomerTask.products.*,");
		builder.append("NCustomerTask.products.productCategory1.{id,name},");
		builder.append("NCustomerTask.products.productCategory2.{id,name},");
		builder.append("NCustomerTask.products.product.{id,name},");
		builder.append("NCustomerTask.products.province.{id,name},");
		builder.append("NCustomerTask.products.city.{id,name},");
		builder.append("NCustomerTask.products.county.{id,name},");
		builder.append("NCustomerTask.follows.*,");
		builder.append("NCustomerTask.notifys.*,");
		builder.append("NCustomerTask.changes.*,"); 
		
		return builder.toString();
	}

	
}