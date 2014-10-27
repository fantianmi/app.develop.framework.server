<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>系统设置</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<jsp:include page="common_css.jsp"></jsp:include>
</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>
				<jsp:include page="menu.jsp"></jsp:include>
				<div class="main-content">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>

						<ul class="breadcrumb">
							<li>
								<i class="icon-home home-icon"></i>
								<a href="index.jsp">Home</a>
							</li>
							<li class="active">系统设置</li>
						</ul><!-- .breadcrumb -->
					</div>
					<div class="page-content">
						<div class="alert alert-block alert-success">
							<i class="icon-ok red"></i>

							<strong class="red">
								暂未开放!
							</strong>
						</div>
					</div>
				</div>

			</div>
		</div>

	<jsp:include page="common_js.jsp"></jsp:include>
</body>
</html>

