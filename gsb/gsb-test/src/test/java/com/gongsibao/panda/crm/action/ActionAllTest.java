package com.gongsibao.panda.crm.action;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.crm.action.customer.SaveCustomerActionTest;
import com.gongsibao.panda.crm.action.task.AbnormalActionTest;
import com.gongsibao.panda.crm.action.task.AddActionTest;
import com.gongsibao.panda.crm.action.task.AllocationActionTest;
import com.gongsibao.panda.crm.action.task.FollowActionTest;
import com.gongsibao.panda.crm.action.task.RegainActionTest;
import com.gongsibao.panda.crm.action.task.ReleaseActionTest;
import com.gongsibao.panda.crm.action.task.RollbackActionTest;
import com.gongsibao.panda.crm.action.task.TransferActionTest;
import com.gongsibao.panda.crm.action.task.UpdateActionTest;

@RunWith(Suite.class)
@SuiteClasses({ 
	AllocationActionTest.class, 
	AbnormalActionTest.class, 
	FollowActionTest.class, 
	RegainActionTest.class, 
	ReleaseActionTest.class, 
	RollbackActionTest.class, 
	TransferActionTest.class,
	
	AddActionTest.class,
	UpdateActionTest.class,
	
	SaveCustomerActionTest.class,
})
		
public class ActionAllTest {

}
