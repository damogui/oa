package com.gongsibao.crm.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.DetailPart;

import com.gongsibao.bd.base.IDictService;
import com.gongsibao.entity.bd.Dict;

public class TaskProductDetailPart extends DetailPart {

	/**
	 * @Title: queryByProducCategoryId1
	 * @Description: TODO(根据一级分类查询二级分类)
	 * @param: @param producCategoryId1
	 * @param: @return
	 * @return: List<Dict>
	 * @throws
	 */
	public List<Dict> queryByProductCategoryId1(Integer productCategoryId1) {

		IDictService service = ServiceFactory.create(IDictService.class);
		List<Dict> list = service.byParentId(productCategoryId1);
		return list;
	}

	/**
	 * @Title: queryByProducCategoryId1
	 * @Description: TODO(根据二级分类查询产品)
	 * @param: @param producCategoryId1
	 * @param: @return
	 * @return: List<Dict>
	 * @throws
	 */
	public Map<Integer, String> queryByProductCategoryId2(Integer productCategoryId1) {

		Map<Integer, String> map = new HashMap<Integer, String>();

		return map;
	}

	/**
	 * @Title: queryByProducId
	 * @Description: TODO(根据产品获取一级分类Id,二级分类Id)
	 * @param: @param productId
	 * @param: @return
	 * @return: Map<Integer,String>
	 * @throws
	 */
	public Map<Integer, String> queryByProductId(Integer productId) {

		Map<Integer, String> map = new HashMap<Integer, String>();

		return map;
	}
}
