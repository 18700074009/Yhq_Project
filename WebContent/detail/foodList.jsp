<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="insrc.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<!-- 包含公共的JSP代码片段 -->
	
<title>无线点餐平台</title>



<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script type="text/javascript" src="style/js/jquery-1.12.2.js"></script>
	<script type="text/javascript" src="style/js/jquery.validate.js"></script>
	<style type="text/css">
	.error{
		color: red;
	}
	</style>
</head>
<body>
<!-- 页面标题 -->
<div id="TitleArea">
	<div id="TitleArea_Head"></div>
	<div id="TitleArea_Title">
		<div id="TitleArea_Title_Content">
			<img border="0" width="13" height="13" src="/My9_4_HotelSys_Ajax/detail/style/images/title_arrow.gif"/> 菜品列表
		</div>
    </div>
	<div id="TitleArea_End"></div>
</div>


	<!-- 过滤条件 -->
	<div id="QueryArea">
		<form action="/My9_4_HotelSys_Ajax/findfoodbyfname" method="get" id="fff2" onsubmit="return false;">
			<input type="hidden" name="method" value="search">
			<input type="text" name="fname" title="请输入菜品名称">
			<input type="submit" value="搜索">
		</form>
	</div>
<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <table class="MainArea_Content" align="center" cellspacing="0" cellpadding="0">
        <!-- 表头-->
        <thead>
            <tr align="center" valign="middle" id="TableTitle">
				<td>菜编号</td>
				<td>菜名</td>
				<td>所属菜系</td>
				<td>价格</td>
                <td>会员价格</td>
				<td>操作</td>
			</tr>
		</thead>	
		<!--显示数据列表 -->
        <tbody id="TableData">
			
        </tbody>
    </table>
	
   <!-- 其他功能超链接 -->
	<div id="TableTail" align="center">
		<div class="FunctionButton"><a href="saveFood.jsp">添加</a></div>
    </div> 
</div>

	<script type="text/javascript">
			$(function(){ajax()});
		</script>
	
		<script type="text/javascript">
			function ajax(){
				$.ajax({
					type:"get",
					url:"${pageContext.request.contextPath}/food?method=list",
					dataType:"json",
					success:function(backData){
						$("#TableData").empty();
						if(backData.code==0){
							$(backData.message).each(function(){
								var html = "";
								html += "<tr class='TableDetail1'>";
								html += "	<td>"+ this.fid +"&nbsp;</td>";
								html += "	<td>"+ this.fname +"&nbsp;</td>";
								html += "	<td>"+ this.cid.cname +"&nbsp;</td>";
								html += "	<td>"+ this.price +"&nbsp;</td>";
							    html += "   <td>"+ this.vipprice +"&nbsp;</td>";
								html += "	<td>";
								html += "		<a href='updateFood.jsp?fid="+ this.fid +"&cid="+ this.cid.cid +"'  class='FunctionButton'>更新</a>	";			
								html += "		<a href='javascript:del("+ this.fid +")' class='FunctionButton'>删除</a>";				
								html += "	</td>";
								html += "</tr>";
										
								$("#TableData").append($(html));
							});
						}else{
							alert(backData.message);
						}
					},
					error:function(backData){
						alert(backData);
					}
	
				});
			}
		
		</script>
		
		<script type="text/javascript">
			function del(fid){
				$.ajax({
					type:"get",
					url:"${pageContext.request.contextPath}/food?method=del&fid="+fid,
					dataType:"json",
					success:function(backData){
						if(backData.code==0){
							ajax();
						}else{
							alert(backData.message);
						}
					}
				})
			}
		
		</script>
		
		
		<script type="text/javascript">
		$.validator.setDefaults({
		    submitHandler: function() {
		     findfoodajax();
		    }
		});
		$().ready(function() {
			// 在键盘按下并释放及提交后验证提交表单
			  $("#fff2").validate({
				    rules: {
				      fname: "required",
				    },
				    messages: {
				      fname: "请输入菜品名称",
				    }
				});
			});
	
	</script>
	
	<script type="text/javascript">
	function findfoodajax(){
		$.ajax({
			type:"get",
			url:"${pageContext.request.contextPath}/food?method=findByfname",
			data:$("#fff2").serialize(),
			dataType:"json",
			success:function(backData){
				if(backData.code==0){
					$("#TableData").empty();
					if(backData.code==0){
						$(backData.message).each(function(){
							var html = "";
							html += "<tr class='TableDetail1'>";
							html += "	<td>"+ this.fid +"&nbsp;</td>";
							html += "	<td>"+ this.fname +"&nbsp;</td>";
							html += "	<td>"+ this.cid.cname +"&nbsp;</td>";
							html += "	<td>"+ this.price +"&nbsp;</td>";
						    html += "   <td>"+ this.vipprice +"&nbsp;</td>";
							html += "	<td>";
							html += "		<a href='updateFood.jsp?fid="+ this.fid +"&cid="+ this.cid.cid +"' class='FunctionButton'>更新</a>	";			
							html += "		<a href='javascript:del("+ this.fid +")' class='FunctionButton'>删除</a>";				
							html += "	</td>";
							html += "</tr>";
									
							$("#TableData").append($(html));
						});
					}
				}
			},
			error:function(backData){
				alert(backData);
			}

		})
	}
	</script>
</body>
</html>
