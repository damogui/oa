package com.gongsibao.panda.platform.operation.workspace.crm;

import com.gongsibao.crm.web.TaskAllListPart;
import com.gongsibao.entity.crm.dic.CustomerFollowStatus;
import org.junit.Before;
import org.netsharp.panda.plugin.entity.PToolbar;

/**
 * Created by zhangchao on 2018/3/3.
 */
public class TaskUnstartWorkspaceTest extends TaskOpenSeaWorkspaceTest {

    @Override
    @Before
    public void setup() {

        super.setup();
        urlList = "/operation/customer/task/unstart/list";
        listPartName = formPartName = "未启动任务";
        resourceNodeCode = "Operation_CRM_Task_UNstart";
        listPartJsController = TaskAllListPart.class.getName();
        listPartServiceController = TaskAllListPart.class.getName();
        listPartImportJs = "/gsb/supplier/crm/base/js/task-base-list.part.js|/gsb/platform/operation/crm/js/task-all-list.part.js|/gsb/panda-extend/gsb.custom.query.controls.js";
        listToolbarPath = "task/unstart/toolbar";
        //未分配条件
        listFilter = " foolow_status = " + CustomerFollowStatus.UNSTART.getValue() + " ";//未启动
    }

}