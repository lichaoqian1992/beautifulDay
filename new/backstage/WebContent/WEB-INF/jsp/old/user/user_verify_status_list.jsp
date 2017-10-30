<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="base" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="renderer" content="webkit">

<title>H+ 后台主题UI框架 - 基础表格</title>


<link href="css/bootstrap.min.css?v=3.4.0" rel="stylesheet">
<link href="font-awesome/css/font-awesome.css?v=4.3.0" rel="stylesheet">
<link href="css/plugins/iCheck/custom.css" rel="stylesheet">
<link href="css/animate.css" rel="stylesheet">
<link href="css/style.css?v=2.2.0" rel="stylesheet">

</head>


<body>

	<div class="row wrapper border-bottom white-bg page-heading">
		<div class="col-lg-10">
			<h2>用户验证记录</h2>
			<ol class="breadcrumb">
				<li><a>主页</a></li>
				<li><a>用户安全</a></li>
				<li><strong>用户验证记录</strong></li>
			</ol>
		</div>
		<div class="col-lg-2"></div>
	</div>


	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-lg-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>用户组别</h5>

					</div>
					<div class="ibox-content">

						<div class="row">
							<div class="col-sm-5 m-b-xs">
								<select class="input-sm form-control input-s-sm inline">
									<option value="0">请选择</option>
									<option value="1">选项1</option>
									<option value="2">选项2</option>
									<option value="3">选项3</option>
								</select>
							</div>
							<div class="col-sm-4 m-b-xs">
								<div data-toggle="buttons" class="btn-group">
									<label class="btn btn-sm btn-white"> <input
										type="radio" id="option1" name="options">天
									</label> <label class="btn btn-sm btn-white active"> <input
										type="radio" id="option2" name="options">周
									</label> <label class="btn btn-sm btn-white"> <input
										type="radio" id="option3" name="options">月
									</label>
								</div>
							</div>
							<div class="col-sm-3">
								<div class="input-group">
									<input type="text" placeholder="请输入关键词"
										class="input-sm form-control"> <span
										class="input-group-btn">
										<button type="button" class="btn btn-sm btn-primary">
											搜索</button>
									</span>
								</div>
							</div>
						</div>

						<table class="table  table-bordered table-hover">
							<thead>
								<tr>
									<th>编号</th>
									<th>数据</th>
									<th>用户</th>
									<th>值</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>1</td>
									<td><span class="line" style="display: none;">5,3,2,-1,-3,-2,2,3,5,2</span>
									<svg class="peity" height="16" width="32">
										<polygon fill="#1ab394"
											points="0 9.375 0 0.5 3.5555555555555554 4.25 7.111111111111111 6.125 10.666666666666666 11.75 14.222222222222221 15.5 17.77777777777778 13.625 21.333333333333332 6.125 24.888888888888886 4.25 28.444444444444443 0.5 32 6.125 32 9.375"></polygon>
										<polyline fill="transparent"
											points="0 0.5 3.5555555555555554 4.25 7.111111111111111 6.125 10.666666666666666 11.75 14.222222222222221 15.5 17.77777777777778 13.625 21.333333333333332 6.125 24.888888888888886 4.25 28.444444444444443 0.5 32 6.125"
											stroke="#169c81" stroke-width="1" stroke-linecap="square"></polyline></svg>
									</td>
									<td>张三</td>
									<td class="text-navy"><i class="fa fa-level-up"></i> 40%</td>
								</tr>
								<tr>
									<td>2</td>
									<td><span class="line" style="display: none;">5,3,9,6,5,9,7,3,5,2</span>
									<svg class="peity" height="16" width="32">
										<polygon fill="#1ab394"
											points="0 15 0 7.166666666666666 3.5555555555555554 10.5 7.111111111111111 0.5 10.666666666666666 5.5 14.222222222222221 7.166666666666666 17.77777777777778 0.5 21.333333333333332 3.833333333333332 24.888888888888886 10.5 28.444444444444443 7.166666666666666 32 12.166666666666666 32 15"></polygon>
										<polyline fill="transparent"
											points="0 7.166666666666666 3.5555555555555554 10.5 7.111111111111111 0.5 10.666666666666666 5.5 14.222222222222221 7.166666666666666 17.77777777777778 0.5 21.333333333333332 3.833333333333332 24.888888888888886 10.5 28.444444444444443 7.166666666666666 32 12.166666666666666"
											stroke="#169c81" stroke-width="1" stroke-linecap="square"></polyline></svg>
									</td>
									<td>李四</td>
									<td class="text-warning"><i class="fa fa-level-down"></i>
										-20%</td>
								</tr>
								<tr>
									<td>3</td>
									<td><span class="line" style="display: none;">1,6,3,9,5,9,5,3,9,6,4</span>
									<svg class="peity" height="16" width="32">
										<polygon fill="#1ab394"
											points="0 15 0 13.833333333333334 3.2 5.5 6.4 10.5 9.600000000000001 0.5 12.8 7.166666666666666 16 0.5 19.200000000000003 7.166666666666666 22.400000000000002 10.5 25.6 0.5 28.8 5.5 32 8.833333333333332 32 15"></polygon>
										<polyline fill="transparent"
											points="0 13.833333333333334 3.2 5.5 6.4 10.5 9.600000000000001 0.5 12.8 7.166666666666666 16 0.5 19.200000000000003 7.166666666666666 22.400000000000002 10.5 25.6 0.5 28.8 5.5 32 8.833333333333332"
											stroke="#169c81" stroke-width="1" stroke-linecap="square"></polyline></svg>
									</td>
									<td>王麻子</td>
									<td class="text-navy"><i class="fa fa-level-up"></i> 26%</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>



	</div>





	<!-- Mainly scripts -->
	<script src="js/jquery-2.1.1.min.js"></script>
	<script src="js/bootstrap.min.js?v=3.4.0"></script>
	<script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<script src="js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

	<!-- Peity -->
	<script src="js/plugins/peity/jquery.peity.min.js"></script>

	<!-- Custom and plugin javascript -->
	<script src="js/hplus.js?v=2.2.0"></script>
	<script src="js/plugins/pace/pace.min.js"></script>

	<!-- iCheck -->
	<script src="js/plugins/iCheck/icheck.min.js"></script>

	<!-- Peity -->
	<script src="js/demo/peity-demo.js"></script>

	<script>
        $(document).ready(function () {
            $('.i-checks').iCheck({
                checkboxClass: 'icheckbox_square-green',
                radioClass: 'iradio_square-green',
            });
        });
    </script>
<body>
</html>