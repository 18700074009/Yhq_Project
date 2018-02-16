<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="insrc.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
 	<!-- 包含公共的JSP代码片段 -->
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>无线点餐平台</title>



<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script type="text/javascript" src="style/js/jquery-1.12.2.js"></script>
	<script type="text/javascript" src="style/js/jquery.validate.js"></script>
	<style type="text/css">
	.error{
		color:red;
	}
	</style>
</head>
<body>
<!-- 页面标题 -->
<div id="TitleArea">
	<div id="TitleArea_Head"></div>
	<div id="TitleArea_Title">
		<div id="TitleArea_Title_Content">
			<img border="0" width="13" height="13" src="${pageContext.request.contextPath}/detail/style/images/title_arrow.gif"/> 餐桌列表
		</div>
    </div>
	<div id="TitleArea_End"></div>
</div>


<!-- 过滤条件 -->
<div id="QueryArea">
	<form action="/My9_4_HotelSys_Ajax/finddeskbydname" method="get" onsubmit="return false" id="f2">
		<!-- <input type="hidden" name="method" value="search"> -->
		<input type="text" name="dname" title="请输入餐桌名称">
		<input type="submit" value="搜索">
	</form>
</div>


<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <table class="MainArea_Content" cellspacing="0" cellpadding="0">
        <!-- 表头-->
        <thead>
            <tr align="center" valign="middle" id="TableTitle">
				<td>编号</td>
				<td>桌名</td>
				<td>状态</td>
				<td>预定时间</td>
				<td>操作</td>
			</tr>
		</thead>	
		<!--显示数据列表 -->
        <tbody id="TableData">
        
        </tbody>
    </table>
	
   <!-- 其他功能超链接 -->
	<div id="TableTail" align="center">
		<div class="FunctionButton"><a href="saveBoard.jsp">添加</a></div>
    </div> 
</div>
	<script type="text/javascript">
		$(function(){ajax()});
	</script>

	<script type="text/javascript">
		function ajax(){
			$.ajax({
				type:"get",
				url:"${pageContext.request.contextPath}/desk?method=list",
				dataType:"json",
				success:function(backData){
					$("#TableData").empty();
					if(backData.code==0){
						$(backData.message).each(function(){
							var html = "";
								html += "		<tr class='TableDetail1'>";
								html += "		<td align='center'>"+ this.did +"&nbsp;</td>";
								html += "		<td align='center'>"+ this.dname +"&nbsp;</td>";
								html += "		<td align='center'> ";
								if(this.status==1){
								html += "预定";
									}else{
								html +=	"空闲";
										} 
								html += "</td>";
								html += "		<td align='center'>"+ this.presettime +"</td>";
								html += "		<td>";
								html += "			<a href='javascript:update("+this.did+","+this.status+")' class='FunctionButton'>";
								if(this.status==1){
									html += "退桌";
										}else{
									html +=	"订桌";
											} 
								html += "</a>";			
								html += "			<a href='javascript:del("+this.did+")' class='FunctionButton' >删除</a>";			
								html += "		</td>";
								html += "	</tr>";					
							$("#TableData").append($(html));
						});
					}
				},
				error:function(backData){
					alert(backData);
				}

			});
		}
	
	</script>

	<script type="text/javascript">
		function update(did,status){
			$.ajax({
				type:"get",
				url:"${pageContext.request.contextPath}/desk?method=update&did="+did+"&status="+status,
				dataType:"json",
				success:function(backData){
					if(backData.code==0){
						ajax()
					}else{
						alert(backData.Message);
					}
				}
			})
		}
	</script>
	
	<script type="text/javascript">
		function del(did){
			$.ajax({
				type:"get",
				url:"${pageContext.request.contextPath}/tologin",
				dataType:"json",
				success:function(backData){
					alert(backData);
				}
			})
		}
	</script>
	
	
	
	<script type="text/javascript">
		$.validator.setDefaults({
		    submitHandler: function() {
		     findajax();
		    }
		});
		$().ready(function() {
			// 在键盘按下并释放及提交后验证提交表单
			  $("#f2").validate({
				    rules: {
				      dname: "required",
				    },
				    messages: {
				      dname: "请输入餐桌名称",
				    }
				});
			});
	
	</script>
	
	
	<script type="text/javascript">
	function findajax(){
		$.ajax({
			type:"get",
			url:"${pageContext.request.contextPath}/desk?method=find",
			data:$("#f2").serialize(),
			dataType:"json",
			success:function(backData){
				if(backData.code==0){
					$("#TableData").empty();
					$(backData.message).each(function(){
						var html = "";
							html += "		<tr class='TableDetail1'>";
							html += "		<td align='center'>"+ this.did +"&nbsp;</td>";
							html += "		<td align='center'>"+ this.dname +"&nbsp;</td>";
							html += "		<td align='center'> ";
							if(this.status==1){
							html += "预定";
								}else{
							html +=	"空闲";
									} 
							html += "</td>";
							html += "		<td align='center'>"+ this.presettime +"</td>";
							html += "		<td>";
							html += "			<a href='javascript:update("+this.did+","+this.status+")' class='FunctionButton'>";
							if(this.status==1){
								html += "退桌";
									}else{
								html +=	"订桌";
										} 
							html += "</a>";			
							html += "			<a href='javascript:del("+this.did+")' class='FunctionButton' >删除</a>";			
							html += "		</td>";
							html += "	</tr>";					
						$("#TableData").append($(html));
					});
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
