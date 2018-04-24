package com.gongsibao.rest.web.controller.v1.order;

import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.rest.base.order.IOrderService;
import com.gongsibao.rest.web.common.apiversion.Api;
import com.gongsibao.rest.web.common.apiversion.LoginCheck;
import com.gongsibao.rest.web.common.security.SecurityUtils;
import com.gongsibao.rest.web.common.util.NumberUtils;
import com.gongsibao.rest.web.common.web.ResponseData;
import com.gongsibao.rest.web.controller.BaseController;
import com.gongsibao.rest.web.dto.order.OrderDTO;
import com.gongsibao.rest.web.dto.order.OrderMessageDTO;
import com.gongsibao.rest.web.dto.order.OrderProductDTO;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: OrderController
 *
 * @author wangkun <wangkun@gongsibao.com>
 * @Description: TODO
 * @date 2018/4/21 12:56
 */
@RestController
@RequestMapping("/wx/{v}/order")
@Api(1)
public class OrderController extends BaseController {

    @Autowired
    private IOrderService orderService;

    /* *
     * @Description: 获取订单进度详情
     * @param  []
     * @return com.gongsibao.rest.web.common.web.ResponseData
     * @author wangkun <wangkun@gongsibao.com>
     * @date 2018/4/21
     */
    @RequestMapping("/messageInfo")
    public ResponseData lstService(HttpServletRequest request) {
        int orderProdId = NumberUtils.toInt(request.getParameter("orderProdId"));

        try {
            OrderMessageDTO dto = orderService.getOrderMessage(orderProdId);
            if (null == dto) {
                return ResponseData.getError(ResponseData.FAIL, "订单不存在");
            }
            return ResponseData.getSuccess(dto, "");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.getError(ResponseData.EXCEPTION, "您的网络不稳定，请稍后再试。");
        }
    }

    @RequestMapping("/info")
    @LoginCheck
    public ResponseData info(HttpServletRequest request) {
        int orderId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("orderIdStr")));
        if (orderId == 0) {
            return ResponseData.getError(ResponseData.FAIL, "订单不存在");
        }

        try {
            SoOrder order = orderService.getById(orderId);
            if (null == order) {
                return ResponseData.getError(ResponseData.FAIL, "订单不存在");
            }

            OrderDTO orderDTO = new OrderDTO();
            {
                orderDTO.setPkid(order.getId());
                orderDTO.setNo(order.getNo());
                orderDTO.setAddTime(order.getCreateTime());
                orderDTO.setAdd_time(order.getCreateTime());
                orderDTO.setProdName(order.getProdName());
                orderDTO.setProcessStatusId(order.getProcessStatus().getValue());
                orderDTO.setPayStatusId(order.getPayStatus().getValue());
                orderDTO.setPayablePrice(order.getPayablePrice());
                orderDTO.setPaidPrice(order.getPaidPrice());
                orderDTO.setIsChangePrice(BooleanUtils.toInteger(order.getIsChangePrice(), 1, 0));
                orderDTO.setChangePriceAuditStatusId(order.getChangePriceAuditStatus().getValue());
                orderDTO.setType(order.getType().getValue());
                orderDTO.setIsInstallment(BooleanUtils.toInteger(order.getIsInstallment(), 1, 0));
                orderDTO.setInstallmentAuditStatusId(order.getInstallmentAuditStatusId().getValue());
            }
            return ResponseData.getSuccess(orderDTO, "");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.getError(ResponseData.EXCEPTION, "您的网络不稳定，请稍后再试。");
        }
    }
}
