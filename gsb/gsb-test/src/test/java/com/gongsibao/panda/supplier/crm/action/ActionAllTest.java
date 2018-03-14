package com.gongsibao.panda.supplier.crm.action;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.supplier.crm.action.customer.OpenMemberCustomerActionTest;
import com.gongsibao.panda.supplier.crm.action.customer.SaveCustomerActionTest;
import com.gongsibao.panda.supplier.crm.action.task.AbnormalActionTest;
import com.gongsibao.panda.supplier.crm.action.task.AllocationAutoActionTest;
import com.gongsibao.panda.supplier.crm.action.task.AllocationManualActionTest;
import com.gongsibao.panda.supplier.crm.action.task.FollowActionTest;
import com.gongsibao.panda.supplier.crm.action.task.RegainActionTest;
import com.gongsibao.panda.supplier.crm.action.task.ReleaseActionTest;
import com.gongsibao.panda.supplier.crm.action.task.RollbackActionTest;
import com.gongsibao.panda.supplier.crm.action.task.SaveActionTest;
import com.gongsibao.panda.supplier.crm.action.task.TransferActionTest;

@RunWith(Suite.class)
@SuiteClasses({ 
	
	AllocationManualActionTest.class, 
	AllocationAutoActionTest.class,
	AbnormalActionTest.class, 
	FollowActionTest.class, 
	RegainActionTest.class, 
	ReleaseActionTest.class, 
	RollbackActionTest.class, 
	TransferActionTest.class,
	
	SaveActionTest.class,
	SaveCustomerActionTest.class,
	OpenMemberCustomerActionTest.class
})
		
public class ActionAllTest {

}