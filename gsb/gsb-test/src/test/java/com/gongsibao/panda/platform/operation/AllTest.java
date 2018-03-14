package com.gongsibao.panda.platform.operation;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.platform.operation.reference.FunctionModuleReferenceTest;
import com.gongsibao.panda.platform.operation.workspace.crm.CustomerALLWorkspaceTest;
import com.gongsibao.panda.platform.operation.workspace.crm.CustomerAllocatedWorkspaceTest;
import com.gongsibao.panda.platform.operation.workspace.crm.CustomerNotTaskWorksapceTest;
import com.gongsibao.panda.platform.operation.workspace.crm.CustomerUndistributedWorkspaceTest;
import com.gongsibao.panda.platform.operation.workspace.crm.CustomerVerifyWorkspaceTest;
import com.gongsibao.panda.platform.operation.workspace.crm.TaskALLWorkspaceTest;
import com.gongsibao.panda.platform.operation.workspace.crm.TaskAllocatedWorkspaceTest;
import com.gongsibao.panda.platform.operation.workspace.crm.TaskCheckAbnormalWorkspaceTest;
import com.gongsibao.panda.platform.operation.workspace.crm.TaskDefeatedWorkspaceTest;
import com.gongsibao.panda.platform.operation.workspace.crm.TaskOpenSeaWorkspaceTest;
import com.gongsibao.panda.platform.operation.workspace.crm.TaskUndistributedWorkspaceTest;
import com.gongsibao.panda.platform.operation.workspace.crm.TaskUnstartWorkspaceTest;
import com.gongsibao.panda.platform.operation.workspace.crm.form.CustomerAddWorkspaceTest;
import com.gongsibao.panda.platform.operation.workspace.crm.form.CustomerEditWorkspaceTest;
import com.gongsibao.panda.platform.operation.workspace.crm.form.TaskAddWorkspaceTest;
import com.gongsibao.panda.platform.operation.workspace.crm.form.TaskEditWorkspaceTest;
import com.gongsibao.panda.platform.operation.workspace.crm.form.TaskFollowFormWorkspaceTest;
import com.gongsibao.panda.platform.operation.workspace.order.CarryoverWorkspaceTest;
import com.gongsibao.panda.platform.operation.workspace.order.ContractWorkspaceTest;
import com.gongsibao.panda.platform.operation.workspace.order.InvoiceWorkspaceTest;
import com.gongsibao.panda.platform.operation.workspace.order.OrderALLWorkspaceTest;
import com.gongsibao.panda.platform.operation.workspace.order.OrderPoolWorkspaceTest;
import com.gongsibao.panda.platform.operation.workspace.order.PerformanceWorkspaceTest;
import com.gongsibao.panda.platform.operation.workspace.order.ReceivedWorkspaceTest;
import com.gongsibao.panda.platform.operation.workspace.order.RefundWorkspaceTest;
import com.gongsibao.panda.platform.operation.workspace.order.ReportDayWorkspaceTest;
import com.gongsibao.panda.platform.operation.workspace.order.ReportMonthWorkspaceTest;
import com.gongsibao.panda.platform.operation.workspace.order.ReportWeekWorkspaceTest;
import com.gongsibao.panda.platform.operation.workspace.order.StagingWorkspaceTest;
import com.gongsibao.panda.platform.operation.workspace.reference.QualityRefeerenceTest;
import com.gongsibao.panda.platform.operation.workspace.reference.SupplierDepartmentReferenceTest;
import com.gongsibao.panda.platform.operation.workspace.reference.SupplierReferenceTest;
import com.gongsibao.panda.platform.operation.workspace.reference.SupplierSalesmanReferenceTest;
import com.gongsibao.panda.platform.operation.workspace.supplier.FunctionModuleWorkspaceTest;
import com.gongsibao.panda.platform.operation.workspace.supplier.QualityWorkspaceTest;
import com.gongsibao.panda.platform.operation.workspace.supplier.SupplierActionTest;
import com.gongsibao.panda.platform.operation.workspace.supplier.SupplierCategoryWorkspaceTest;
import com.gongsibao.panda.platform.operation.workspace.supplier.SupplierDepartmentWorkspaceTest;
import com.gongsibao.panda.platform.operation.workspace.supplier.SupplierSalesmanWorkspaceTest;
import com.gongsibao.panda.platform.operation.workspace.supplier.SupplierWorkspaceTest;
import com.gongsibao.panda.platform.operation.workspace.supplier.data.ImportTaskQualityDataTest;
import com.gongsibao.panda.platform.operation.workspace.supplier.data.ImportTaskRoleAndFunctionTest;
import com.gongsibao.panda.platform.operation.workspace.taurus.ActiveUserWorkspaceTest;
import com.gongsibao.panda.platform.operation.workspace.taurus.DayStatisticWorkspaceTest;
import com.gongsibao.panda.platform.operation.workspace.taurus.JnzUserBalanceWorkSpaceTest;
import com.gongsibao.panda.platform.operation.workspace.taurus.NewUserPerDayWorkspaceTest;
import com.gongsibao.panda.platform.operation.workspace.taurus.UcOrginazationWorkTest;
import com.gongsibao.panda.platform.operation.workspace.taurus.UserConsumptionWorkspaceTest;
import com.gongsibao.panda.platform.operation.workspace.taurus.UserWorkspaceTest;
import com.gongsibao.panda.platform.operation.workspace.wanda.WanDaProductWorkspaceTest;
import com.gongsibao.panda.platform.operation.workspace.wanda.WanDaSoOrderWorkspaceTest;
import com.gongsibao.panda.platform.operation.workspace.igirl.TradeMarkCaseAllWorkspaceTest;
import com.gongsibao.panda.platform.operation.workspace.igirl.TradeMarkFollowWorkspaceTest;
@RunWith(Suite.class)
@SuiteClasses({

        ResourceTest.class,

        //参照
        //功能模块
        FunctionModuleReferenceTest.class,
        SupplierReferenceTest.class,
        SupplierDepartmentReferenceTest.class,
        SupplierSalesmanReferenceTest.class,
        QualityRefeerenceTest.class,

        //金牛座
        UserWorkspaceTest.class,
        //UserInfoWorkspaceTest.class,
        NewUserPerDayWorkspaceTest.class,
//	UserConsStatisticWorkspaceTest.class,
//	UserRenewalStatisticWorkspaceTest.class,
        ActiveUserWorkspaceTest.class,
        DayStatisticWorkspaceTest.class,
        JnzUserBalanceWorkSpaceTest.class,
        UserConsumptionWorkspaceTest.class,
        UcOrginazationWorkTest.class,

        //万达
        WanDaProductWorkspaceTest.class,
        WanDaSoOrderWorkspaceTest.class,

        //CMS
//	ProductPackageWorkspaceTest.class,
//	ProductWorkspaceTest.class,

        //钉钉

        //供应商
//	CPAuditWorkspaceTest.class,
//	SPAuditWorkspaceTest.class,

        FunctionModuleWorkspaceTest.class,
        SupplierWorkspaceTest.class,
        SupplierCategoryWorkspaceTest.class,
        SupplierSalesmanWorkspaceTest.class,
        SupplierDepartmentWorkspaceTest.class,


        CustomerVerifyWorkspaceTest.class,
        CustomerAddWorkspaceTest.class,
        CustomerEditWorkspaceTest.class,
        TaskAddWorkspaceTest.class,
        TaskEditWorkspaceTest.class,
        TaskFollowFormWorkspaceTest.class,

        TaskOpenSeaWorkspaceTest.class,
        CustomerALLWorkspaceTest.class,
        CustomerAllocatedWorkspaceTest.class,
        CustomerUndistributedWorkspaceTest.class,
        CustomerNotTaskWorksapceTest.class,

        TaskAllocatedWorkspaceTest.class,
        TaskALLWorkspaceTest.class,
        TaskUndistributedWorkspaceTest.class,
        TaskUnstartWorkspaceTest.class,

        TaskDefeatedWorkspaceTest.class,
        TaskCheckAbnormalWorkspaceTest.class,
        QualityWorkspaceTest.class,

        //订单操作
        OrderALLWorkspaceTest.class,
        OrderPoolWorkspaceTest.class,
        CarryoverWorkspaceTest.class,
        ContractWorkspaceTest.class,
        InvoiceWorkspaceTest.class,
        PerformanceWorkspaceTest.class,
        ReceivedWorkspaceTest.class,
        RefundWorkspaceTest.class,
        StagingWorkspaceTest.class,
        ReportDayWorkspaceTest.class,
        ReportWeekWorkspaceTest.class,
        ReportMonthWorkspaceTest.class,
        

        ImportTaskQualityDataTest.class,
        ImportTaskRoleAndFunctionTest.class,
        // ImportOldDataToNewData.class,
        SupplierActionTest.class,
        //智能商标在运营下面的列表
        TradeMarkCaseAllWorkspaceTest.class,
        TradeMarkFollowWorkspaceTest.class,

        NavigationTest.class

})

public class AllTest {

}
