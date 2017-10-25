//package org.netsharp.persistence;
//
//import java.util.ArrayList;
//
//import org.junit.Test;
//import org.netsharp.core.Mtable;
//import org.netsharp.core.MtableManager;
//import org.netsharp.core.NetsharpException;
//import org.netsharp.db.DbFactory;
//import org.netsharp.db.IDb;
//import org.netsharp.entity.Customer;
//import org.netsharp.entity.Inventory;
//import org.netsharp.entity.SalesOrder;
//import org.netsharp.entity.SalesOrderDetail;
//
//public class TestCreateTable {
//
//	@Test
//	public void createTable() throws NetsharpException{
//		   
//	   ArrayList<Mtable> clss=new ArrayList<Mtable>(); 
//	   
//	   clss.add(MtableManager.getMtable(SalesOrder.class));
//	   clss.add(MtableManager.getMtable(SalesOrderDetail.class));
//	   clss.add(MtableManager.getMtable(Customer.class));
//	   clss.add(MtableManager.getMtable(Inventory.class));
//	   
//	   IDb db= DbFactory.create();
//	   for(Mtable meta : clss){
//		   db.createTable(meta);
//	   }
//    }
//
//}
