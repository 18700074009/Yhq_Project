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
	
	
	<script type="text/javascript">
	
	$.validator.setDefaults({
	    submitHandler: function() {
	     addajax();
	    }
	});
	$().ready(function() {
		  $("#ff1").validate({
			    rules: {
			      cname: "required",
			    },
			    messages: {
			      cname: "请输入菜系名称",
			    }
			});
		});
	</script>
	<script type="text/javascript">
	function addajax(){
		$.ajax({
			type:"get",
			url:"${pageContext.request.contextPath}/cuisine?method=add",
			data:$("form").serialize(),
			dataType:"json",
			success:function(backData){
				if(backData.code==0){
					window.location.href="cuisineList.jsp";
				}
			},
			error:function(backData){
				alert(backData);
			}

		})
	}
	</script>
	<!-- 给出表单验证的提示颜色 -->
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
			
				
				
					<img border="0" width="13" height="13" src="style/images/title_arrow.gif"/>  添加菜系
				
			
		</div>
    </div>
	<div id="TitleArea_End"></div>
</div>


<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
	<!-- 表单内容 -->
	<form action="/My9_4_HotelSys_Ajax/addcuisine" method="get" onsubmit="return false;" id="ff1">
	
		<!-- 本段标题（分段标题） -->
		<div class="ItemBlock_Title">
        	<img width="4" height="7" border="0" src="style/images/item_point.gif"> 菜系信息&nbsp;
        </div>
		<!-- 本段表单字段 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
				<div class="ItemBlock2">
					<table cellpadding="0" cellspacing="0" class="mainForm">
						<tr>
							<td width="80px">菜系名称</td>
							<td>
								<input type="text" name="cname" class="InputStyle" id="addcuisinename"/>* <span id="addcuisinespan"></span>
								<!-- <input type="hidden" name="cid" value="" /> -->
							</td>
						</tr>
					</table>
				</div>
            </div>
        </div>
		
		<!-- 表单操作 -->
		<div id="InputDetailBar">
			
				
				
					 <input type="submit" value="添加" class="FunctionButtonInput">
				
			
            <a href="javascript:history.go(-1);" class="FunctionButton">返回</a>
        </div>
	</form>
	
</div>




</body>
</html>
