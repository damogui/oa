<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<title>待跟进客户</title>
	<%@include file="/wx/qy/bd/include/header.jsp" %>
</head>
<body>
	<div class="weui-search-bar" id="searchBar">
	  <form class="weui-search-bar__form"  action="" onsubmit="return false;">
	    <div class="weui-search-bar__box">
	      <i class="weui-icon-search"></i>
	      <input type="search" class="weui-search-bar__input" id="searchKeyWord" placeholder="搜索" onsearch="ctrl.filter()">
	      <a href="javascript:" class="weui-icon-clear" id="searchClear"></a>
	    </div>
	    <label class="weui-search-bar__label" id="searchText">
	      <i class="weui-icon-search"></i>
	      <span>搜索</span>
	    </label>
	  </form>
	  <a href="javascript:ctrl.cancel();" class="weui-search-bar__cancel-btn" id="searchCancel">取消</a>
	</div>
	<div class="weui_body">
	
	  <div class="weui-pull-to-refresh__layer">
	    <div class='weui-pull-to-refresh__arrow'></div>
	    <div class='weui-pull-to-refresh__preloader'></div>
	    <div class="down">下拉刷新</div>
	    <div class="up">释放刷新</div>
	    <div class="refresh">正在刷新</div>
	  </div>
	  <div id="list" class="weui-cells"></div>
	  <div id="loadmore" class="weui-loadmore" style="display:none;">
	    <i class="weui-loading"></i>
	    <span class="weui-loadmore__tips">正在加载</span>
	  </div>
	  
		<div id="nodata"  class="weui-loadmore weui-loadmore_line" style="display:none;">
		  <span class="weui-loadmore__tips">暂无数据</span>
		</div>
	</div>
	<%@include file="/wx/qy/bd/include/footer.jsp" %>
	<script src="/wx/qy/bd/js/franchisee.list.ctrl.js"></script>
	<script src="/wx/qy/bd/js/franchisee.untrack.list.ctrl.js"></script>
	<script>
	  var ctrl = new org.netsharp.we.core.franchiseeListCtrl();
	  $(function(){
		  ctrl.init();
	  });
	</script>
</body>
</html>