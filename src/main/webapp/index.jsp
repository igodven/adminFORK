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

<!-- 员工添加的模态框 -->
<div class="modal fade" id="empAddModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">添加员工</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
		<form class="form-horizontal">
		  <div class="form-group">
		    <label class="col-sm-2 control-label">empName</label>
		    <div class="col-sm-10">
		      	<input type="text" name="empName" class="form-control" id="empName_update_input" placeholder="empNamp">
		      <span class="help-block"></span>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">email</label>
		    <div class="col-sm-10">
		      <input type="text" name="email" class="form-control" id="email_update_input" placeholder="email@m520it.com">
		      <span class="help-block"></span>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">gender</label>
		    <div class="col-sm-10">
		      <label class="radio-inline">
				  <input type="radio" name="gender" id="gender1_update_input" value="M" checked="checked"> 男
				</label>
				<label class="radio-inline">
				  <input type="radio" name="gender" id="gender2_update_input" value="F"> 女
				</label>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">deptName</label>
		    <div class="col-sm-4">
		    	<!-- 部门提交部门id即可 -->
		      <select class="form-control" name="deptId">
		      
		      </select>
		    </div>
		  </div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="add_emp_save">保存</button>
      </div>
    </div>
  </div>
</div>

<!-- 员工更新的模态框 -->
<div class="modal fade" id="empUpdateModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">更新员工</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
		<form class="form-horizontal">
		  <div class="form-group">
		    <label class="col-sm-2 control-label">empName</label>
		    <div class="col-sm-10">
		      	<input type="text" name="empName" class="form-control" id="empName_update_input1" placeholder="empNamp">
		      <span class="help-block"></span>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">email</label>
		    <div class="col-sm-10">
		      <input type="text" name="email" class="form-control" id="email_update_input1" placeholder="email@m520it.com">
		      <span class="help-block"></span>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">gender</label>
		    <div class="col-sm-10">
		      <label class="radio-inline">
				  <input type="radio" name="gender" id="gender1_update_input1" value="M" checked="checked"> 男
				</label>
				<label class="radio-inline">
				  <input type="radio" name="gender" id="gender2_update_input1" value="F"> 女
				</label>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">deptName</label>
		    <div class="col-sm-4">
		    	<!-- 部门提交部门id即可 -->
		      <select class="form-control" name="deptId">
		      
		      </select>
		    </div>
		  </div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="upDate_emp_edit">更新</button>
      </div>
    </div>
  </div>
</div>



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
				<button class="btn btn-success" id="emp_add_button">增加</button>
				<button class="btn btn-danger" id="emp_deleteAll_button">删除</button>
			</div>
		</div>
		<!--表格数据  -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-striped" id="table_edit">
					<thead>
						<tr>
						    <th>
						    <input type="checkbox" id="check_all"/>
						    </th>
							<th>#</th>
							<th>empName</th>
							<th>gender</th>
							<th>email</th>
							<th>deptName</th>
							<th>操作</th>
						</tr>
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
		
			</div>
			<!-- 分页条信息 -->
			<div class="col-md-6" id="page_nav_area">
			
			</div>
		</div>
	</div>

	<script type="text/javascript">
	//定义一个总记录数的全局的变量,方便保存后的跳转
	var pageTotal;
	var page_Num;
	$(function(){
		to_page(1)
	})
	function to_page(pn){
		$.ajax({
			url : "${APP_PATH}/emps",
			data : "pn="+pn,
			type : "GET",
			success : function(result) {
				//调用生成table的方法
				//解析显示表格数据
				build_emp_table(result);
				//解析显示分页信息
				build_page_info(result);
				//解析显示分页条数据
				build_page_nav(result);
			}
		});
	}
		function build_emp_table(result){
			$("#table_edit tbody").empty();
			//找到员工的数据
			var emps=result.extend.pageInfo.list;
			//对员工的数据进行迭代:index表示索引:item表示具体的数据
			$.each(emps,function(index,item){
				var checkId=$("<td><input type='checkbox' class='checkitem'/></td>")
				var empId=$("<td></td>").append(item.empId);
				var empName=$("<td></td>").append(item.empName);
				var gender=$("<td></td>").append(item.gender=="M"?"男":"女");
				var email=$("<td></td>").append(item.email);
				var deptName=$("<td></td>").append(item.department.deptName);
				var editButton=$("<button></button>").addClass("btn btn-primary btn-sm edit_btn").append($("<span></span>"))
				                                    .addClass("glyphicon glyphicon-pencil").append("编辑").attr("edit_id",item.empId);
				   
				var deleteButton=$("<button></button>").addClass("btn btn-danger btn-sm delete_btn").append($("<span></span>"))
				                                    .addClass("glyphicon glyphicon-trash").append("删除").attr("delete_id",item.empId);
				var buttonTd=$("<td></td>").append(editButton).append(" ").append(deleteButton);
			    var tr=$("<tr></tr>").append(checkId)
			                         .append(empId)
			                         .append(empName)
			                         .append(gender)
			                         .append(email)
			                         .append(deptName)
			                         .append(buttonTd)
			                         //表示吧数据添加到哪里去
			                         .appendTo("#table_edit tbody");
			    
			});
		}
		function build_page_info(result){
			//把之前的数据清空
			$("#page_info_area").empty();
			var page_num=result.extend.pageInfo;
			console.log(page_num);
			var pageNum="当前"+page_num.pageNum+"页"+"总"+page_num.pages+"页"+"总"+page_num.total+"记录";
			$("#page_info_area").append(pageNum);
			//赋值给定义的全局总数变量
			pageTotal=page_num.total;
			page_Num=result.extend.pageInfo.pageNum;
		}
		
		function build_page_nav(result){
			$("#page_nav_area").empty();
			var ul = $("<ul></ul>").addClass("pagination");
			var firstPages=$("<li></li>").append($("<a></a>").append("首页").attr("href","#"));
			var prePages=$("<li></li>").append($("<a></a>").append("&laquo;"));//前一页
			if(result.extend.pageInfo.hasPreviousPage==false){
				firstPages.addClass("disabled");
				prePages.addClass("disabled");
			}else{
				firstPages.click(function(){
					to_page(1)
				})
				prePages.click(function(){
					to_page(result.extend.pageInfo.pageNum-1)
				})
			}
			var nextPages=$("<li></li>").append($("<a></a>").append("&raquo;"));//后一页
			var lastPages=$("<li></li>").append($("<a></a>").append("末页").attr("href","#"));
			if(result.extend.pageInfo.hasNextPage==false){
				lastPages.addClass("disabled");
				nextPages.addClass("disabled");
			}else{
				    nextPages.click(function(){
				    	to_page(result.extend.pageInfo.pageNum+1)
				    })
				    lastPages.click(function(){
					    to_page(result.extend.pageInfo.total)
				})
			}
			ul.append(firstPages).append(prePages);
			
			var page_item=result.extend.pageInfo.navigatepageNums;
			$.each(page_item,function(index,item){
				var numLi=$("<li></li>").append($("<a></a>").append(item));
				if(result.extend.pageInfo.pageNum==item){
					numLi.addClass("active")
				}
				numLi.click(function(){
					to_page(item);
				})
				ul.append(numLi);
			})
			ul.append(nextPages).append(lastPages);
			console.log(ul);
			var navEle = $("<nav></nav>").append(ul);
			navEle.appendTo("#page_nav_area");
			
		}
		
		//方法清空表单的数据和表单的样式
		function form_reset(ele){
			//清空表单中的数据
			$(ele)[0].reset();
			//清空表单的样式
			$(ele).find("*").removeClass("has-error has-success")
			$(ele).find(".help-block").text("");
		}
		
		  //触发一个新增按钮的模态框的点击事件
		$("#emp_add_button").click(function(){
			//清除表单中的数据,防止反复提交数据
			form_reset("#empAddModal form")
			//$("#empAddModal form")[0].reset();
			 $("#empAddModal").modal({
				//点击模态框后.背景不会消失
				backdrop:"static"
			})  
		}) 
		
		$.ajax({
			  url:"${APP_PATH}/depts",
			  type:"GET",
			  success:function(result){
				  //调用方法把返回的数据传达模态框的下拉列表中
				  build_dept_select(result);
			  }
		});
		function build_dept_select(result){
			//{code: 100, extend: {depts: [{deptId: 1, deptName: "开发部"}]}, succes: "处理成功"}
			var deptResult=result.extend.depts
			$.each(deptResult,function(index,dept){
			    var option=$("<option></option>").append(dept.deptName).attr("value",dept.deptId);
			    option.appendTo($("#empAddModal select"));
			
			})
		}
		//对表单的数据进行校验
		function validate_add_form(){
		var empName=$("#empName_update_input").val();
	    var regName = /(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})/;
	    if(!regName.test(empName)){
	    	show_validate_msg("#empName_update_input","error","请输入正确的姓名!他可以包括2-5个汉字或者6-16个任意字符")
	    	//alert("请输入正确的姓名!他可以包括2-5个汉字或者6-16个任意字符")
	    	/* $("#empName_update_input").parent().addClass("has-error");
	    	$("#empName_update_input").next("span").text("请输入正确的姓名!他可以包括2-5个汉字或者6-16个任意字符") */
	    	return false;
	       }else{
	    	   show_validate_msg("#empName_update_input","success","")
	    /* 	$("#empName_update_input").parent().addClass("has-success");
	    	$("#empName_update_input").next("span").text(""); */
	       }
	    
	    var email=$("#email_update_input").val();
	    var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
	    if(!regEmail.test(email)){
	    	//抽取校验的方法
	    	   show_validate_msg("#email_update_input","error","请输入正确的邮箱格式")
	    	//alert("请输入正确的邮箱格式")
	    	/* $("#email_update_input").parent().addClass("has-error");
	    	$("#email_update_input").next("span").text("请输入正确的邮箱格式"); */
	    	return false;
	       }else{
	    	   show_validate_msg("#email_update_input","success","")
	    	/* $("#email_update_input").parent().addClass("has-success");
	    	$("#email_update_input").next("span").text(""); */
	    	   
	       }
	    return true;
		}
		//抽取出的校验方法//显示校验的信息
		function show_validate_msg(ele,status,msg){
			//移除绑定的提示信息
		    $(ele).parent().removeClass("has-success has-error");
		    $(ele).next("span").text("");
			if(status=="success"){
		    	$(ele).parent().addClass("has-success");
		    	$(ele).next("span").text("");
			}else if(status=="error"){
		    	$(ele).parent().addClass("has-error");
		    	$(ele).next("span").text(msg);
			}
		}
		
		//校验用户名是否可用,点击后就可知道相关信息
		$("#empName_update_input").change(function(){
			var empName=this.value;
			$.ajax({
				url:"${APP_PATH}/checkEmpName",
				type:"post",
				data:"empName="+empName,
				success:function(result){
					if(result.code==200){
					 show_validate_msg("#empName_update_input","error",result.extend.va_msg)
					 //检验用户存在后再保存按钮上注册一个属性error:方便保存时停止操作
					 $("#add_emp_save").attr("ajax-va","error")
					}else if(result.code==100){
					 show_validate_msg("#empName_update_input","success","用户名可用!")
					 //检验用户存在后再保存按钮上注册一个属性success:方便进行判断,是否继续保存
					 $("#add_emp_save").attr("ajax-va","success")
					}
				}
			})
		})
		$("#add_emp_save").click(function(){
			
		//保存数据前,调用方法,对表单的数据进行校验,如果不符合直接return
		if(!validate_add_form()){
			return ;} 
		
		//在执行之前保存之前,进行对重复的用户进行拦截
		if($(this).attr("ajax-va")=="error"){
			return false;
		}
		
			$.ajax({
				  url:"${APP_PATH}/saveEmps",
				  type:"post",
				  //快速的获取表单中的数据
				  data:$("#empAddModal form").serialize(),
				  success:function(result){
					  //关闭模态框
					  if(result.code==100){
						  
					  $("#empAddModal").modal('hide');
					  //保存数据完毕后,直接跳转到保存后打的最后一页
					  to_page(pageTotal);
					  }else if(result.code==200){
						  if(undefined != result.extend.error.email){
								//显示邮箱错误信息
								show_validate_msg("#email_add_input", "error", result.extend.error.email);
							}
							if(undefined != result.extend.error.empName){
								//显示员工名字的错误信息
								show_validate_msg("#empName_add_input", "error", result.extend.error.empName);
							}
					  }
					  
				  }
			});
		});
		$(document).on("click",".edit_btn",function(){
			$("#empUpdateModal select").empty();
			  //回显员工数据
			  edit_emp_show($(this).attr("edit_id"));
			  //在模态框的更新操作上绑定id,有利于根据id对员工进行更新
			  $("#upDate_emp_edit").attr("edit_id",$(this).attr("edit_id"))
			  //编辑员工
			  //启用模态框
			  $("#empUpdateModal").modal({
				//点击模态框后.背景不会消失
			     backdrop:"static"
		});
		$.ajax({
			  url:"${APP_PATH}/depts",
			  type:"GET",
			  success:function(result){
				  //调用方法把返回的数据传达模态框的下拉列表中
				  build_dept_select1(result);
			  }
		});
		})
		
		function build_dept_select1(result){
			//{code: 100, extend: {depts: [{deptId: 1, deptName: "开发部"}]}, succes: "处理成功"}
			var deptResult=result.extend.depts
			$.each(deptResult,function(index,dept){
			    var option=$("<option></option>").append(dept.deptName).attr("value",dept.deptId).addClass("select_value");
			    option.appendTo($("#empUpdateModal select"));
		})
		}
		function edit_emp_show(id){
			
			$.ajax({
				url:"${APP_PATH}/getEmp/"+id,
				type:"GET",
				success:function(result){
					$("#empUpdateModal input[name=empName]").val(result.extend.emp.empName);
					$("#empUpdateModal input[name=email]").val(result.extend.emp.email);
					$("#empUpdateModal input[name=gender]").val([result.extend.emp.gender]);
					$("#empUpdateModal select_value").val([result.extend.emp.deptId]);
					//$("#empUpdateModal select[name=deptId]").val([result.extend.emp.deptId]);
				}
			})
		}
		$("#upDate_emp_edit").click(function(){
			$("#empName_update_input1").removeClass("has-success has-error");
			$("#empName_update_input1").next("span").next("");
			//对员工的姓名进行校验
			var empName=$("#empName_update_input1").val();
		    var regName = /(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})/;
		    if(!regName.test(empName)){
		    	show_validate_msg("#empName_update_input1","error","请输入正确的姓名!他可以包括2-5个汉字或者6-16个任意字符")
		    	return false;
		       }else{
		    	   show_validate_msg("#empName_update_input1","success","");
		    	   
		       }
		    //对员工的邮箱进行校验
		    var email=$("#email_update_input1").val();
		    var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
		    if(!regEmail.test(email)){
		    	//抽取校验的方法
		    	   show_validate_msg("#email_update_input1","error","请输入正确的邮箱格式")
		    	return false;
		       }else{
		    	   show_validate_msg("#email_update_input1","success","")
		    	  
		       }
			
			var edit_id=$("#upDate_emp_edit").attr("edit_id");
			//更新员工的操作
			$.ajax({
				url:"${APP_PATH}/updateSaveEmp/"+edit_id,
				type:"put",
				data:$("#empUpdateModal form").serialize(),
				success:function(result){
					//alert(result.succes);
					$("#empUpdateModal").modal("hide");
					to_page(page_Num);
				}
			})
		})
		$(document).on("click",".delete_btn",function(){
			//1、弹出是否确认删除对话框
			var empName = $(this).parents("tr").find("td:eq(2)").text();
			var empId = $(this).attr("delete_id");
			//alert($(this).parents("tr").find("td:eq(1)").text());
			//弹出是否确认删除的按钮
			if(confirm("确认删除【"+empName+"】吗？")){
				//确认，发送ajax请求删除即可
				$.ajax({
					url:"${APP_PATH}/emp/"+empId,
					type:"DELETE",
					success:function(result){
						alert(result.succes);
						//回到本页
						to_page(page_Num);
					}
				});
			}
		})
			
		//完成全选/全不选功能
		$("#check_all").click(function(){
			//判断全选框是否被选择了,如果被选择了,单选框全选
			$(".checkitem").prop("checked",$(this).prop("checked"));
		})
		$(document).on("click",".checkitem",function(){
			//判断单选框是否为全部选择,如果是则全选框也被选择
			//进行判断,单选框被选择地数量是否等于页面显示的数据条数
			var flag=$(".checkitem:checked").length==$(".checkitem").length;
			$("#check_all").prop("checked",flag);
		})
		
		$("#emp_deleteAll_button").click(function(){
			var empNames="";
			var empIds="";
			$.each($(".checkitem:checked"),function(){
			   empNames+=$(this).parents("tr").find("td:eq(2)").text()+",";
			   empIds+=$(this).parents("tr").find("td:eq(1)").text()+"-";
			})
			//去掉字符串最后的一个逗号
			empNames=empNames.substring(0,empNames.length-1);
			//去掉字符串最后的一个"-"字符
			empIds=empIds.substring(0,empIds.length-1)
			if(confirm("确认删除【"+empNames+"】吗？")){
				$.ajax({
					url:"${APP_PATH}/emp/"+empIds,
					type:"delete",
					success:function(result){
						alert(result.succes)
						to_page(page_Num);
					}
				})
			}
		})
	</script>
</body>
</html>