<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String page_url = request.getServletPath();%>
<%
boolean home=false;
boolean arctype=false;
boolean otherpart=false;
boolean media=false;
boolean handout=false;
boolean video=false;
boolean otherpartdata=false;
boolean otherpartdataHandout=false;
boolean otherpartdataVideo=false;
boolean user=false;
boolean adver=false;
boolean version=false;
if (page_url.indexOf("/index.jsp") != -1) {
	home = true;
} else if (page_url.indexOf("/arctype.jsp") != -1){
	arctype = true;
} else if (page_url.indexOf("/otherpart.jsp") != -1) {
	otherpart = true;
} else if (page_url.indexOf("/media.jsp") != -1) {
	media = true;
} else if (page_url.indexOf("/handout.jsp")!=-1||page_url.indexOf("/addHandout.jsp") != -1) {
	handout = true;
} else if (page_url.indexOf("/video.jsp") != -1||page_url.indexOf("/addVideo.jsp")!=-1 ) {
	video = true;
} else if (page_url.indexOf("/otherpartdataHandout.jsp") != -1||page_url.indexOf("/addOtherPartHandout.jsp")!=-1) {
	otherpartdataHandout = true;
}else if (page_url.indexOf("/otherpartdataVideo.jsp") != -1||page_url.indexOf("/addOtherPartVideo.jsp")!=-1) {
	otherpartdataVideo = true;
} else if (page_url.indexOf("/users.jsp") != -1 ) {
	user = true;
} else if (page_url.indexOf("/adver.jsp") != -1||page_url.indexOf("/addBanner.jsp")!=-1 ) {
	adver = true;
} else if(page_url.indexOf("/version.jsp")!=-1){
	version=true;
}
if(otherpartdataHandout||otherpartdataVideo){
	otherpartdata=true;
}
%>
<div class="sidebar" id="sidebar">
	<script type="text/javascript">
		try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
	</script>
	<ul class="nav nav-list">
		<li class="<%=home?"active":""%>"><a href="index.jsp"><i class="icon-folder-close"></i><span class="menu-text"> 欢迎页 </span></a></li>
		<li class="<%=arctype?"active":""%>"><a href="arctype.jsp?reid=0"><i class="icon-folder-close"></i><span class="menu-text"> 科目管理</span></a></li>
		<li class="<%=otherpart?"active":""%>"><a href="otherpart.jsp"><i class="icon-folder-close"></i><span class="menu-text"> 机构管理 </span></a></li>
		<li class="<%=media?"active":""%>"><a href="media.jsp"><i class="icon-folder-close"></i><span class="menu-text"> 媒体库管理 </span></a></li>
		<li class="<%=handout?"active":""%>"><a href="handout.jsp?reid=0"><i class="icon-folder-close"></i><span class="menu-text"> 讲义管理 </span></a></li>
		<li class="<%=video?"active":""%>"><a href="video.jsp?reid=0"><i class="icon-folder-close"></i><span class="menu-text"> 视频管理 </span></a></li>
		<li class="<%=otherpartdata?"active":""%>"><a href="#" class="dropdown-toggle"><i class="icon-list"></i><span class="menu-text"> 其他机构内容管理 </span>
		<b class="arrow icon-angle-down"></b></a>
		<ul class="submenu">
			<li class="<%=otherpartdataHandout?"active":""%>"><a href="otherpartdataHandout.jsp" ><i class="icon-double-angle-right"></i>讲义管理</a></li>
			<li class="<%=otherpartdataVideo?"active":""%>"><a href="otherpartdataVideo.jsp" ><i class="icon-double-angle-right"></i>视频管理</a></li>
		</ul>
		</li>
		<li class="<%=user?"active":""%>"><a href="users.jsp"><i class="icon-folder-close"></i><span class="menu-text"> 用户管理</span></a></li>
		<li class="<%=adver?"active":""%>"><a href="adver.jsp"><i class="icon-folder-close"></i><span class="menu-text"> 广告管理 </span></a></li>
		<li class="<%=version?"active":""%>"><a href="version.jsp"><i class="icon-folder-close"></i><span class="menu-text"> 版本管理 </span></a></li>
	</ul>
	<div class="sidebar-collapse" id="sidebar-collapse">
		<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
	</div>
	<script type="text/javascript">
		try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
	</script>
</div>
