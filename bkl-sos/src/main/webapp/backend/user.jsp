<%@page import="org.omg.CORBA.MARSHAL"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="com.km.common.vo.*"%>
<%@page import="com.km.common.utils.*"%>
<%@page import="com.bkl.sos.service.*"%>
<%@page import="com.bkl.sos.service.impl.*"%>
<%@page import="com.bkl.sos.utils.*"%>
<%@page import="com.bkl.sos.entity.*"%>
<%@page import="com.bkl.sos.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	long id = Long.parseLong(StringUtils.defaultIfEmpty(request.getParameter("id"), "0"));
	UserService userSrv = new UserServiceImpl();
	User user = userSrv.get(id);
	
	String statusString = request.getParameter("recordType");
	int status = 0;
	if (statusString != null) {
		status = Integer.parseInt(statusString);
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="htmlhead.jsp"></jsp:include>
</head>
<body>
<jsp:include page="topbar.jsp"></jsp:include>
<div class="main-container-inner">
	<a class="menu-toggler" id="menu-toggler" href="#">
		<span class="menu-text"></span>
	</a>
	<jsp:include page="menu.jsp"></jsp:include>
	<!-- content -->
	<div class="main-content">
		<div class="breadcrumbs" id="breadcrumbs">
			<script type="text/javascript">
				try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
			</script>
			<ul class="breadcrumb">
				<li>
					<i class="icon-home home-icon"></i>
					<a href="#">首页</a>
				</li>
				<li class="active"></li>
			</ul><!-- .breadcrumb -->
		</div>
		<div class="page-content">
			<div class="widget-box transparent">
				<div class="widget-header widget-header-small">
					<h4 class="blue smaller">
						<i class="icon-rss orange"></i>
						基本信息
					</h4>
				</div>
			</div>
	
			<div class="profile-user-info profile-user-info-striped">
				<div class="profile-info-row">
					<div class="profile-info-name">邮箱 </div>
					<div class="profile-info-value">
						<span><%=StringUtils.defaultIfEmpty(user.getEmail(),"-") %></span>
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name">呢称 </div>
					<div class="profile-info-value">
						<span><%=StringUtils.defaultIfEmpty(user.getNick_name(),"-") %></span>
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name">注册时间 </div>
					<div class="profile-info-value">
						<span><%=user.getCtimeString() %></span>
					</div>
				</div>
			</div>
			<p/>
			<div class="col-xs-12">
				<div class="btn-toolbar">
					<%if(user.getFrozen() == 0) { %>	
						<button class="btn btn-warning" onclick="enableUser(<%=user.getId()%>, 1)">禁用</button>
					<%} else { %>
						<button class="btn btn-success" onclick="enableUser(<%=user.getId()%>, 0)">启用</button>
					<%} %>
				</div>
				<p />
			</div>
		</div>
	</div>
</div>
<!-- content -->
<jsp:include page="htmlfoot.jsp"></jsp:include>
<script type="text/javascript" src="common/js/user.js"></script>
</body>
</html>