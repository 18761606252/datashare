<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="itcast" uri="http://itcast.cn/common/"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>数据查询-BootCRM</title>

<!-- Bootstrap Core CSS -->
<link href="<%=basePath%>res/css/bootstrap.min.css" rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="<%=basePath%>res/css/metisMenu.min.css" rel="stylesheet">

<!-- DataTables CSS -->
<link href="<%=basePath%>res/css/dataTables.bootstrap.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="<%=basePath%>res/css/sb-admin-2.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="<%=basePath%>res/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link href="<%=basePath%>res/css/boot-crm.css" rel="stylesheet"
	type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>


<div class="panel panel-default">
	<div class="panel-body">
		<form class="form-inline" action="${pageContext.request.contextPath }/dataManage/list.do" method="get">
			<div class="form-group">
				<label for="dataStation">数据站点</label> 
				<input type="text" class="form-control" id="dataStation" value="${dataStation}" name="dataStation">
			</div>
			<div class="form-group">
				<label for="dataCity">数据城市</label> 
				<select	class="form-control" id="dataCity"  name="dataCity">
					<option value="">--请选择--</option>
					<c:forEach items="${cityType}" var="item">
						<option value="${item.dict_id}"<c:if test="${item.dict_id == dataCity}"> selected</c:if>>${item.dict_item_name }</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label for="dataElement">数据要素</label> 
				<select	class="form-control" id="dataElement"  name="dataElement">
					<option value="">--请选择--</option>
					<c:forEach items="${elementType}" var="item">
						<option value="${item.dict_id}"<c:if test="${item.dict_id == dataElement}"> selected</c:if>>${item.dict_item_name }</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label for="dataYear">数据年份</label> 
				<select	class="form-control" id="dataYear"  name="dataYear">
					<option value="">--请选择--</option>
					<c:forEach items="${yearType}" var="item">
						<option value="${item.dict_id}"<c:if test="${item.dict_id == dataYear}"> selected</c:if>>${item.dict_item_name }</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label for="dataMonth">数据月份</label>
				<select	class="form-control" id="dataMonth" name="dataMonth">
					<option value="">--请选择--</option>
					<c:forEach items="${monthType}" var="item">
						<option value="${item.dict_id}"<c:if test="${item.dict_id == dataMonth}"> selected</c:if>>${item.dict_item_name }</option>
					</c:forEach>
				</select>
			</div>
			<button type="submit" class="btn btn-primary">查询</button>
			
				
			</form>
			
			
	</div>
</div>


			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">数据信息列表</div>
						<!-- /.panel-heading -->
						<form id="jvForm">
						<table class="table table-bordered table-striped">
							<thead>
								<tr>
								<th width="20"><input type="checkbox" onclick="checkBox('ids',this.checked)"/></th>
									<th>ID</th>
									<th>数据站点</th>
									<th>数据城市</th>
									<th>数据年份</th>
									<th>数据月份</th>
									<th>数据日期</th>
									<th>数据要素类型</th>
									<th>数据来源</th>
									<th>数据操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${page.rows}" var="row">
									<tr>
									<td><input type="checkbox" value="${row.t_id }" name="ids"/></td>
										<td>${row.t_id}</td>
										<td>${row.station_id}</td>
										<td>${row.data_city}</td>
										<td>${row.year_data}</td>
										<td>${row.mon_data}</td>
										<td>${row.day_data}</td>
										<td>${row.data_element}</td>
										<td>${row.data_source}</td>
										<td>
											<a href="#" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#dataEditDialog" onclick="editData(${row.t_id})">修改</a>
											<a href="#" class="btn btn-danger btn-xs" onclick="deleteData(${row.t_id})">删除</a>
											
										</td>
									</tr>
								</c:forEach>
								
							</tbody>
								
						</table>
						</form>
						
						
						
						 <div class="col-md-12 text-left" style="margin-top:30px;">
						
									<a href="#" class="btn btn-danger btn-xs" onclick="optDelete(${name})">批量删除</a>
								</div> 
							
					
						
						<div class="col-md-12 text-right">
							<itcast:page url="${pageContext.request.contextPath }/dataManage/list.do" />
						</div>
						
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
				</div>
				<!-- /.col-lg-12 -->
			</div>
		</div>
		<!-- /#page-wrapper -->

	
	<!-- 数据编辑对话框 -->
<div class="modal fade" id="dataEditDialog" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">修改数据信息</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" id="edit_data_form">
					<input type="hidden"  id="edit_t_id" name="t_id"/>
					<!-- <input type="hidden"  id="edit_dataYear" name="data_year"/> -->
					<div class="form-group">
						<label for="edit_stationId" class="col-sm-2 control-label">数据站点</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="edit_stationId" placeholder="数据站点" name="station_id">
						</div>
					</div>
					<div class="form-group">
						<label for="edit_dataCity" style="float:left;padding:7px 15px 0 27px;">数据城市</label> 
						<div class="col-sm-10">
							<select	class="form-control" id="edit_dataCity" placeholder="数据城市" name="data_city">
								<option value="">--请选择--</option>
								<c:forEach items="${cityType}" var="item">
									<option value="${item.dict_id}"<c:if test="${item.dict_id == dataCity}"> selected</c:if>>${item.dict_item_name }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="edit_dataElement" style="float:left;padding:7px 15px 0 27px;">数据要素</label>
						<div class="col-sm-10"> 
							<select	class="form-control" id="edit_dataElement"  name="data_element">
								<option value="">--请选择--</option>
								<c:forEach items="${elementType}" var="item">
									<option value="${item.dict_id}"<c:if test="${item.dict_id == dataElement}"> selected</c:if>>${item.dict_item_name }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="edit_dataYear" style="float:left;padding:7px 15px 0 27px;">数据年份</label>
						<div class="col-sm-10">
							<select	class="form-control" id="edit_dataYear" name="data_year">
								<option value="">--请选择--</option>
								<c:forEach items="${yearType}" var="item">
									<option value="${item.dict_id}"<c:if test="${item.dict_id == dataYear}"> selected</c:if>>${item.dict_item_name }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="edit_dataMon" style="float:left;padding:7px 15px 0 27px;">数据月份</label>
						<div class="col-sm-10">
							<select	class="form-control" id="edit_dataMon" name="data_mon">
								<option value="">--请选择--</option>
								<c:forEach items="${monthType}" var="item">
									<option value="${item.dict_id}"<c:if test="${item.dict_id == dataMonth}"> selected</c:if>>${item.dict_item_name }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
							<label for="edit_dataSource" class="col-sm-2 control-label">数据来源</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="edit_dataSource" placeholder="数据来源" name="data_source">
							</div>
						</div>
				</form>
				
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary" onclick="updateData()">保存修改</button>
				
			</div>
		</div>
	</div>
</div>
	<!-- /#wrapper -->

	<!-- jQuery -->
	<script src="<%=basePath%>res/js/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="<%=basePath%>res/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="<%=basePath%>res/js/metisMenu.min.js"></script>

	<!-- DataTables JavaScript -->
	<script src="<%=basePath%>res/js/jquery.dataTables.min.js"></script>
	<script src="<%=basePath%>res/js/dataTables.bootstrap.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="<%=basePath%>res/js/sb-admin-2.js"></script>
	
	<script type="text/javascript">
		function editData(id) {
			$.ajax({
				type:"get",
				url:"<%=basePath%>dataManage/detail.do",
				data:{"id":id},
				success:function(data) {
					$("#edit_t_id").val(data.t_id);
					$("#edit_stationId").val(data.station_id);
					$("#edit_dataCity").val(data.data_city);
					$("#edit_dataElement").val(data.data_element);
					//$("#edit_year_data").val(data.year_data);
					$("#edit_dataYear").val(data.data_year);
					
					$("#edit_dataMon").val(data.data_mon);
					$("#edit_dataSource").val(data.data_source); 
				}
			});
		}
		function updateData(){
			$.post("<%=basePath%>dataManage/update.do",$("#edit_data_form").serialize(),function(data){
				alert("数据信息更新成功！");
				window.location.reload();
			});
		}
		
		function deleteData(id) {
			if(confirm('确定要删除该条数据吗?')) {
				$.get("<%=basePath%>dataManage/delete.do",{"id":id},function(data){
					alert("数据删除更新成功！");
					window.location.reload();
				});
			}
		}
	
		//全选
		function checkBox(name,checked){
			$("input[name='" + name + "']").prop("checked",checked);
		}

		//批量删除
		function optDelete(name){
			//请选择一个
			var s = $("input[name='ids']:checked").size();
		 	if(s <= 0){
				alert("请至少选择一个!");
				return;
			}
		 	if(!confirm("确定要删除吗?")){
		 		return ;
		 	}
		 	//删除
		 	$("#jvForm").attr("action","/dataManage/deletes.do?name=" + name);
		 	$("#jvForm").attr("method","post").submit();
		 	window.location.reload();
		}
	
		
	</script>

</body>

</html>
