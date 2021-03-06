<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ page import="org.netsharp.authorization.UserPermissionManager"%>
<%@ page import="org.netsharp.authorization.UserPermission"%>
<%@ page import="org.netsharp.organization.entity.Employee"%>
<%@ page import="org.netsharp.panda.core.HttpContext"%>
<%@ page import="org.netsharp.panda.core.comunication.ServletRequest"%>
<%@ page import="org.netsharp.panda.core.comunication.ServletResponse"%>
<%@ page import="org.netsharp.panda.core.comunication.HtmlWriter"%>
<%
	HttpContext ctx = new HttpContext();
	{
		ctx.setRequest(new ServletRequest(request, response));
		ctx.setResponse(new ServletResponse(response));
		ctx.setContext(request.getServletContext());
		ctx.setWriter(new HtmlWriter(response.getWriter()));
	}
	HttpContext.setCurrent(ctx);
	UserPermission permission = UserPermissionManager.getUserPermission();
	Employee employee = permission.getEmployee();
%>
<style>
	.cell .title{
		color:#76838f;
		text-align: center;
	}
	.cell .num{
		    text-align: center;
	}	
	.cell .num span{
		color:#000;
		font-size:36px;
	}
</style>
<div>
		 <div class="row" style="height:150px;">
        	<div class="cell cell-12">
	        	<div id="sbyctj" class="easyui-panel" title="我的异常商标统计"  style="padding:0px 10px !important;" data-options="fit:true,border:false,tools:'#refreshtool'" >
				<div id="refreshtool">
					<a href="#" class="fa fa-refresh" onclick="getAbnorvalNotice()"></a>
				</div> 
				<div class="row" >
	        			<div class="cell" style="width:12.5%;">
	        				<div class="title"><span>部分驳回</span></div>
	        				<div class="num" id="bfbh" name="abnormal" onclick="jumpPage(6);"><span id="new_count">0</span></div>
	        			</div>
	        			<div class="cell" style="width:12.5%;">
	        				<div class="title"><span>全部驳回</span></div>
	        				<div class="num" id="qbbh" name="abnormal" onclick="jumpPage(7);"><span id="un_start_count">0</span></div>
	        			</div>
	        			<div class="cell" style="width:12.5%;">
	        				<div class="title"><span>不予受理</span></div>
	        				<div class="num" id="bysl" name="abnormal" onclick="jumpPage(13);')"><span id="stay_foolow_count">0</span></div>
	        			</div>
	        			<div class="cell" style="width:12.5%;">
	        				<div class="title"><span>补证通知</span></div>
	        				<div class="num" id="bztz" name="abnormal" onclick="jumpPage(15);"><span id="timeout_count">0</span></div>
	        			</div>
	        			<div class="cell" style="width:12.5%;">
	        				<div class="title"><span>裁定通知</span></div>
	        				<div class="num" id="cdtz" name="abnormal" onclick="jumpPage(17);"><span id="abnormal_count" >0</span></div>
	        			</div>
	        			<div class="cell" style="width:12.5%;">
	        				<div class="title"><span>不予核准</span></div>
	        				<div class="num" id="byhz" name="abnormal" onclick="jumpPage(18);"><span id="public_count">0</span></div>
	        			</div>
	        			<div class="cell" style="width:12.5%;">
	        				<div class="title"><span>同日申请协商</span></div>
	        				<div class="num" id="trsqxs" name="abnormal" onclick="jumpPage(20);"><span id="abnormal_count" ">0</span></div>
	        			</div>
	        			<div class="cell" style="width:12.5%;">
	        				<div class="title"><span>同日申请补送证据</span></div>
	        				<div class="num" id="trsqbszj" name="abnormal" onclick="jumpPage(21);"><span id="public_count" ">0</span></div>
	        			</div>
	        		</div>
			   </div> 
        	</div>	
        </div>
		<div class="row" style="height:150px;">
        	<div class="cell cell-12">
	        	<div id="briefing" class="easyui-panel" title="销售简报" style="padding:0px 10px !important;" data-options="fit:true,border:false">
	        	
	        		<div class="row">
	        			<!-- <div class="cell cell-2">
	        				<div class="title"><span>新增商机</span></div>
	        				<div class="num"><span id="crm_new_count">0</span></div>
	        			</div> -->
	        			<div class="cell cell-2">
	        				<div class="title"><span>未启动</span></div>
	        				<div class="num"><span id="crm_un_start_count">0</span></div>
	        			</div>
	        			<div class="cell cell-2">
	        				<div class="title"><span>待跟进</span></div>
	        				<div class="num"><span id="crm_stay_foolow_count">0</span></div>
	        			</div>
	        			<div class="cell cell-2">
	        				<div class="title"><span>超时未跟进</span></div>
	        				<div class="num"><span id="crm_timeout_count">0</span></div>
	        			</div>
	        			<div class="cell cell-2">
	        				<div class="title"><span>异常未处理</span></div>
	        				<div class="num"><span id="crm_abnormal_count" style="color:red;">0</span></div>
	        			</div>
	        			<div class="cell cell-2">
	        				<div class="title"><span>公海</span></div>
	        				<div class="num"><span id="crm_public_count">0</span></div>
	        			</div>
	        		</div>
			    </div>
        	</div>	
        </div>
       
        <div class="row" style="height:200px;">
        	<div class="cell cell-6">
	        	<div id="foolow" class="easyui-panel" title="跟进统计（今日）" style="padding:10 20px;" data-options="fit:true,border:false">
			    </div>
        	</div>
        	<div class="cell cell-6">
	        	<div id="forecast" class="easyui-panel" title="预估业绩" data-options="fit:true,border:false">
	        		
			    </div>
        	</div>
        </div>
		<div class="row" style="height:200px;">
        	<div class="cell cell-12">
	        	<div class="easyui-panel" title="漏斗统计" data-options="fit:true,border:false">
	        		<p>
	        			<span id="allTasks">全部商机：0个</span>
	        		</p>
	        		<p>
	        			<span id="xClass">X类：0个</span>
	        			<span id="sClass" style="padding-left: 50px;">S类：0个</span>
	        		</p>
	        		<p>
	        			<span>A类：&nbsp;</span>
	        			<span id ="A0">A0:0个</span>
	        			<span id ="A1" class="padding-15">A1:0个</span>
	        			<span id ="A2" class="padding-15">A2:0个</span>
	        			<span id ="A3" class="padding-15">A3:0个</span>
	        			<span id ="A4" class="padding-15">A4:0个</span>
	        			<span class="padding-15">B类：&nbsp;</span>
	        			<span id ="B1">B1:0个</span>
	        			<span id ="B2" class="padding-15">B2:0个</span>
	        		</p>
	        		<p>
	        			<span>C类：&nbsp;</span>
	        			<span id ="C1">C1:0个</span>
	        			<span id ="C2" class="padding-15">C2:0个</span>
	        			<span id ="C3" class="padding-15">C3:0个</span>
	        			<span id ="C4" class="padding-15">C4:0个</span>
	        			<span style="padding-left: 67px;">D类：&nbsp;</span>
	        			<span id ="D1">D1:0个</span>
	        			<span id ="D2" class="padding-15">D2:0个</span>
	        		</p>
			    </div>
			    
			    <div class="easyui-panel" title="漏斗统计" data-options="fit:true,border:false">
	        		<p>
	        			<span id="allTasks">全部商机：0个</span>
	        		</p>
	        		<p>
	        			<span id="xClass">X类：0个</span>
	        			<span id="sClass" style="padding-left: 50px;">S类：0个</span>
	        		</p>
	        		<p>
	        			<span>A类：&nbsp;</span>
	        			<span id ="A0">A0:0个</span>
	        			<span id ="A1" class="padding-15">A1:0个</span>
	        			<span id ="A2" class="padding-15">A2:0个</span>
	        			<span id ="A3" class="padding-15">A3:0个</span>
	        			<span id ="A4" class="padding-15">A4:0个</span>
	        			<span class="padding-15">B类：&nbsp;</span>
	        			<span id ="B1">B1:0个</span>
	        			<span id ="B2" class="padding-15">B2:0个</span>
	        		</p>
	        		<p>
	        			<span>C类：&nbsp;</span>
	        			<span id ="C1">C1:0个</span>
	        			<span id ="C2" class="padding-15">C2:0个</span>
	        			<span id ="C3" class="padding-15">C3:0个</span>
	        			<span id ="C4" class="padding-15">C4:0个</span>
	        			<span style="padding-left: 67px;">D类：&nbsp;</span>
	        			<span id ="D1">D1:0个</span>
	        			<span id ="D2" class="padding-15">D2:0个</span>
	        		</p>
			    </div>
        	</div>
        </div>
</div>
<script src='/gsb/supplier/home/js/portal-statistic.js'></script>

	<script>
		//销售简报
		var brief = new com.gongsibao.crm.web.home.BriefingCtrl();
		//跟进统计
		var foolow = new com.gongsibao.crm.web.home.FoolowCtrl();
		//预估业绩
		var forecast = new com.gongsibao.crm.web.home.ForecastCtrl();
		//漏斗统计
		var funnel = new com.gongsibao.crm.web.home.FunnelCtrl();
		$(function() {
			
 			//这几个数据可以用一个DTO一次性返回，这样调用次数太多
 			brief.briefingCountPars2('salesPresentation',0,1,function(entity){
				//$("#crm_new_count").text(entity.newTasksCount);
				$("#crm_un_start_count").text(entity.unStartTasksCount);
				$("#crm_stay_foolow_count").text(entity.unfoolowTasksCount);
				$("#crm_timeout_count").text(entity.timeOutTasksCount);
				$("#crm_abnormal_count").text(entity.exceptUntreatedTasksCount);				
				$("#crm_public_count").text(entity.highSeasCount);
			});
			
			foolow.foolowCountPars0('getFoolowSatatistic');
			
			forecast.forecastAmountPars1('getForecastAmount',1);
			forecast.forecastAmountPars1('getForecastAmount',2);
			forecast.forecastAmountPars1('getForecastAmount',3);
			
			funnel.funnelXSCountPars0('getXSCount'); 
			funnel.funnelCodeCountPars0('getCodeTaskCount'); 
			
			
			getAbnorvalNotice();
			//setInterval("getAbnorvalNotice()",10000);
		});
		
		function getAbnorvalNotice(){
			<%-- var userId=<%=employee.getId()%>; --%>
			var siteCtl=new org.netsharp.core.JServiceLocator();
			siteCtl.invoke("com.gongsibao.igirl.tm.web.TradeMarkCasePart","getAbnormalNotice",[],function(d){
				d=d.data;
				var len = d.length;
				for(var i=0;i<len;i++){
					switch(d[i].tmState)
					{
					case "6":
						changeAbnormalCount("bfbh",d[i].count);
						break;
					case "7":
						changeAbnormalCount("qbbh",d[i].count);
						break;
					case "13":
						changeAbnormalCount("bysl",d[i].count);
						break;
					case "15":
						changeAbnormalCount("bztz",d[i].count);
						break;
					case "17":
						changeAbnormalCount("cdtz",d[i].count);
						break;
					case "18":
						changeAbnormalCount("byhz",d[i].count);
						break;
					case "20":
						changeAbnormalCount("trsqxs",d[i].count);
						break;
					case "21":
						changeAbnormalCount("trsqbszj",d[i].count);
						break;
					default:
						break;
					}
				}
			})
		};
		
		function changeAbnormalCount(id,count){
			id="#"+id;
			$(id).empty();
			if(count>0){
				$(id).append("<span style='color:red;'>"+count+"</span>");
			}else{
				$(id).append("<span>"+count+"</span>");
			}
			
		};
		function jumpPage(markState){
			var userRole=<%=employee.getRoles()%>;
			console.log(userRole);
			var userId=<%=employee.getId()%>;
			var url ="/panda/igirl/notice/follow/list?markState=";
			/* var siteCtl=new org.netsharp.core.JServiceLocator();
			siteCtl.invoke("com.gongsibao.igirl.tm.web.TradeMarkCasePart","getRoleListByUserId",[userId],function(d){
				d=d.data;
				if(d==)
				window.open(url+markState);
			}); */
			
			window.open(url+markState);
				
		};
	</script>