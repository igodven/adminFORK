<%@page
	import="org.apache.taglibs.standard.lang.jstl.test.PageContextImpl"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>员工列表</title>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<!-- 引入jquery  -->
<script type="text/javascript"
	src="${APP_PATH}/static/js/jquery-1.12.4.min.js"></script>
<!-- 引入bootStrap样式 -->
<link
	href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>
	<!--搭建显示页面  -->
	<div class="container">
		<!--标题  -->
		<div class="row">
			<div class="col-md-12">
				<h1>crud</h1>
			</div>
		</div>
		<!-- 两个按钮 -->
		<div class="row">
			<div class="col-md-4 col-md-offset-8">
				<button class="btn btn-success">增加</button>
				<button class="btn btn-danger">删除</button>
			</div>
		</div>
		<!--表格数据  -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>#</th>
							<th>empName</th>
							<th>gender</th>
							<th>email</th>
							<th>deptName</th>
							<th>操作</th>
						</tr>
						<c:forEach items="${pageInfo.list}" var="emp">
							<tr>
								<td>${emp.empId}</td>
								<td>${emp.empName}</td>
								<td>${emp.gender=="M"?"男":"nv" }</td>
								<td>${emp.email}</td>
								<td>${emp.department.deptName }</td>
								<td>
									<button class="btn btn-primary btn-sm">编辑</button>
									<button class="btn btn-outline-danger btn-sm">删除</button>
								</td>
							</tr>
						</c:forEach>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
		<!-- 显示分页信息 -->
		<div class="row">
			<!--分页文字信息  -->
			<div class="col-md-6" id="page_info_area">
				当前${pageInfo.pageNum}页 总${pageInfo.pages }页 共${pageInfo.total }记录</div>
			<!-- 分页条信息 -->
			<div class="col-md-6" id="page_nav_area">
				<nav aria-label="Page navigation example">
					<ul class="pagination">
						<li class="page-item"><a class="page-link"
							href="${APP_PATH }/emps?pn=1">首页</a></li>
						<c:if test="${pageInfo.hasPreviousPage }">
							<li><a href="${APP_PATH}/emps?pn=${pageInfo.pageNum-1}"
								aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
									<span class="sr-only">Previous</span>
							</a></li>
						</c:if>
						<c:forEach items="${pageInfo.navigatepageNums }" var="page_num">
							<c:if test="${pageInfo.pageNum==page_num }">
								<li class="active"><a
									href="${APP_PATH}/emps?pn=${page_num}">${page_num}</a></li>
							</c:if>
							<c:if test="${pageInfo.pageNum!=page_num }">
								<li><a href="${APP_PATH}/emps?pn=${page_num}">${page_num}</a></li>
							</c:if>

						</c:forEach>
						<c:if test="${pageInfo.hasNextPage}">
							<li class="page-item"><a class="page-link"
								href="${APP_PATH }/emps?pn=${pageInfo.pageNum+1}"
								aria-label="Next"> <span aria-hidden="true">&raquo;</span> <span
									class="sr-only">Next</span>
							</a></li>
						</c:if>
						<li class="page-item"><a class="page-link"
							href="${APP_PATH }/emps?pn=${pageInfo.pages}">末页</a></li>
					</ul>
				</nav>
			</div>
		</div>
	</div>
</body>
</html>