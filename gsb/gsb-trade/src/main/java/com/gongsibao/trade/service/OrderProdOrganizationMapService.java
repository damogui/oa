package com.gongsibao.trade.service;

import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.IOrderProdUserMapService;
import com.gongsibao.trade.base.IOrderService;
import com.gongsibao.utils.NumberUtils;
import org.apache.commons.collections.CollectionUtils;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;
import org.netsharp.util.StringManager;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.trade.OrderProdOrganizationMap;
import com.gongsibao.trade.base.IOrderProdOrganizationMapService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderProdOrganizationMapService extends PersistableService<OrderProdOrganizationMap> implements IOrderProdOrganizationMapService {

    IOrderService orderService = ServiceFactory.create(IOrderService.class);

    IOrderProdUserMapService orderProdUserMapService = ServiceFactory.create(IOrderProdUserMapService.class);

    public OrderProdOrganizationMapService() {
        super();
        this.type = OrderProdOrganizationMap.class;
    }

    @Override
    public Boolean updateOrganizationMap(Integer orderProdId, Integer supplierId) {
        UpdateBuilder updateBuilder = UpdateBuilder.getInstance();
        {
            updateBuilder.update("so_order_prod_organization_map");
            updateBuilder.set("supplier_id", supplierId);
            updateBuilder.where("order_prod_id =" + orderProdId);
        }
        String sql = updateBuilder.toSQL();
        return this.pm.executeNonQuery(sql, null) > 0;
    }

    @Override
    public List<OrderProdOrganizationMap> getListByOrderNo(String orderNo) {
        List<OrderProdOrganizationMap> resList = new ArrayList<>();
        if (StringManager.isNullOrEmpty(orderNo)) {
            return resList;
        }
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT DISTINCT od.pkid 'orderProdId',sp.name 'supplierName', ");
        sql.append("GROUP_CONCAT( DISTINCT em.name ORDER BY opum.order_prod_id DESC) 'operator' FROM so_order_prod od ");
        sql.append("JOIN so_order oi ON oi.pkid = od.order_id ");
        sql.append("LEFT JOIN so_order_prod_organization_map opom ON opom.order_prod_id=od.pkid ");
        sql.append("LEFT JOIN sp_supplier sp ON sp.id = opom.supplier_id ");
        sql.append("LEFT JOIN so_order_prod_user_map opum ON opum.order_prod_id = od.pkid AND opum.type_id = 3063 ");
        sql.append("LEFT JOIN sp_salesman sm ON sm.employee_id = opum.user_id ");
        sql.append("LEFT JOIN sys_permission_employee em ON em.id = sm.employee_id ");
        sql.append("WHERE oi.no='" + orderNo + "' ");
        sql.append("GROUP BY opom.supplier_id ");
        DataTable rows = this.pm.executeTable(sql.toString(), null);
        for (IRow row : rows) {
            Integer orderProdId = NumberUtils.toInt(row.getInteger("orderProdId"));
            String supplierName = row.getString("supplierName");
            String operator = row.getString("operator");
            OrderProdOrganizationMap map = new OrderProdOrganizationMap();
            map.setOrderProdId(orderProdId);
            map.setSupplierName(StringManager.isNullOrEmpty(supplierName) ? "无" : supplierName);
            map.setOperator(operator);
            resList.add(map);
        }
        return resList;
    }

}