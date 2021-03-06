package com.gongsibao.panda;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.action.ActionAllTest;
import com.gongsibao.panda.auth.AuthAllTest;
import com.gongsibao.panda.supplier.SupplierAllTest;

@RunWith(Suite.class)
@SuiteClasses({

        //平台相关（不可删除）
        org.netsharp.meta.framework.AllTests.class,
        org.netsharp.meta.platform.AllTests.class,
        org.netsharp.meta.basebiz.AllTests.class,


	//org.netsharp.wx.meta.AllTests.class,
//  org.netsharp.cache.plugin.AllTests.class,
//    org.netsharp.scrum.meta.AllTests.class,

//	//1.基础信息 
        com.gongsibao.panda.platform.basic.AllTest.class,

        //2.用户中心
//	com.gongsibao.panda.user.AllTest.class,

        //3.招商CRM
        com.gongsibao.panda.platform.franchisee.FranchiseeAllTest.class,

        //4.股转中心
        //com.gongsibao.panda.ma.AllTest.class,

        //5.运营管理
        com.gongsibao.panda.platform.operation.AllTest.class,

        //2.服务商系统
        SupplierAllTest.class,

        //6.商品管理
        //com.gongsibao.panda.platform.product.AllTest.class,


        //8.交易中心
        com.gongsibao.panda.platform.trade.AllTest.class,

        //9.报表中心
//	 com.gongsibao.panda.platform.report.AllTest.class,

        //10.igri
        //com.gongsibao.panda.igirl.AllTest.class,


        //11.gardian
// 	com.gongsibao.panda.gardian.AllTest.class,

        //12.财务报销
        //com.gongsibao.panda.platform.cw.AllTest.class,

		// 13Rest接口
		//com.gongsibao.panda.rest.RestAllTest.class,

        //处理资源Id
        org.netsharp.meta.end.AllTests.class,


        //所有Action入口
        ActionAllTest.class,
        
        //放在最后处理
        AuthAllTest.class
})
public class DevAllTest {

}
