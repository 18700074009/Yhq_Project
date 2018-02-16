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
<script type="text/javascript" src="style/js/uploadPreview.min.js"></script>
<script type="text/javascript" src="style/js/jquery.form.js"></script>
	
	<script type="text/javascript">
	
	$.validator.setDefaults({
	    submitHandler: function() {
	     $("#fff3").ajaxSubmit({
	    	 type:"post",
	    	 url:"${pageContext.request.contextPath}/food?method=updatefood&fid="+<%=request.getParameter("fid")%>,
	    	 dataType:"json",
	    	 success:function(backData){
	    		if(backData.code==0){
	    			alert(backData.message);
	    			window.location.href="foodList.jsp";
	    		}else{
	    			alert(backData.message);
	    		}
	    	 }
	     });
	     	return false;
	    }
	});
	$().ready(function() {
		  $("#fff3").validate({
			    rules: {
			      fname: "required",
			      price: {
			    	  required:true,
			    	  number:true,
			    	  min:0
			      },
			      vipprice:{
			    	  required:true,
			    	  number:true,
			    	  min:0
			      }
			    },
			    messages: {
			      fname: "请输入菜品名称",
			      price: "请输入菜品价格",
			      vipprice:"请输入菜品会员价格"
			    }
			});
		});
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
			
				
					<img border="0" width="13" height="13" src="/My9_4_HotelSys_Ajax/detail/style/images/title_arrow.gif"/> 更新新菜品
				
				
			
		</div>
    </div>
	<div id="TitleArea_End"></div>
</div>

<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
	<!-- 表单内容 -->	<!-- 当没有附件上传时，表单设置enctype="multipart/form-data"这一属性，则会使获取的表单数据为null，删除这一属性即可成功获取表单数据 -->
	<form action="/My9_4_HotelSys_Ajax/food" method="post" onsubmit="return false;" id="fff3" enctype="multipart/form-data">
		<!-- 本段标题（分段标题） -->
		<div class="ItemBlock_Title">
        	<img width="4" height="7" border="0" src="/My9_4_HotelSys_Ajax/detail/style/images/item_point.gif"> 菜品信息&nbsp;
        </div>
		<!-- 本段表单字段 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
				<div class="ItemBlock2">
					<table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
							<td width="80px">菜系</td>
							<td>
                            <select name="cid" style="width:80px" id="select">
                            	<!-- 用ajax添加数据 -->
                            </select>
                             *<input type="hidden" name="cid" value="1" /></td>
						</tr>
						<tr>
							<td width="80px">菜名</td>
							<td><input type="text" id="updatefoodfname" name="fname" class="InputStyle" value=""/> *<span id="updatefnamespan"></span></td>
						</tr>
						<tr>
							<td>价格</td>
							<td><input type="text" id="updatefoodprice" name="price" class="InputStyle" value=""/> *<span id="updatepricespan"></span></td>
						</tr>
                        <tr>
							<td>会员价格</td>
							<td><input type="text" id="updatefoodvipprice" name="vipprice" class="InputStyle" value=""/> *<span id="updatevippricespan"></span></td>
						</tr>
						
						<tr>
							<td>简介</td>
							<td><textarea name="intro" class="TextareaStyle" id="intro">暂无</textarea></td>
						</tr>
						<tr>
							<td width="80px">菜品图片</td>
							<td>
									<!-- <img style='max-width:68px;width:68px;width:expression(width>68?"68px":width "px");max-width: 68px;' 
									src="style/images/baizhuoxia.jpg">
									<input type="hidden" name="image" value="baizhuoxia.jpg"> -->
								<div id="imgdiv"><img id="imgShow"  width="100" height="100" /></div>
								<input type="file" name="imageUrl" id="up_img"/>*
							</td>
						</tr>
					</table>
				</div>
            </div>
        </div>
		
		
		<!-- 表单操作 -->
		<div id="InputDetailBar">
            
				
					 <input type="submit" value="修改" class="FunctionButtonInput">
				
				
			
            
            <a href="javascript:history.go(-1);" class="FunctionButton">返回</a>
        </div>
	</form>
</div>

	<script type="text/javascript">
		$(function(){
			$.ajax({
				type:"get",
				url:"${pageContext.request.contextPath}/cuisine?method=list",
				dataType:"json",
				success:function(backData){
					$("#select").empty();
					if(backData.code==0){
						$(backData.message).each(function(){
							var html = "";
							if(this.cid == <%=request.getParameter("cid")%>){
								html += "<option value='"+ this.cid +"' selected='selected'>"+ this.cname +"</option>";
								$("#select").append($(html));
							}else{
								html += "<option value='"+ this.cid +"'>"+ this.cname +"</option>";
								$("#select").append($(html));
							}
						});
					}else{
						alert(backData.message);
					}
				},
				error:function(backData){
					alert(backData);
				}
			});
			$.ajax({
				type:"get",
				url:"${pageContext.request.contextPath}/food?method=findByfid&fid="+<%=request.getParameter("fid")%>,
				dataType:"json",
				success:function(backData){
					if(backData.code==0){
						$("#updatefoodfname").val(backData.message.fname);
						$("#updatefoodprice").val(backData.message.price);
						$("#updatefoodvipprice").val(backData.message.vipprice);
						$("#intro").val(backData.message.intro);
						//alert(backData.message.photourl);
						$("#imgShow").attr("src",backData.message.photourl);
						//$("#up_img").val(backData.message.photourl);
					}else{
						alert(backData.message);
					}
				},
				error:function(backData){
					alert(backData);
				}
			});
		
		})
	
	</script>
	<script>
       window.onload = function () { 
            new uploadPreview({ UpBtn: "up_img", DivShow: "imgdiv", ImgShow: "imgShow" });
        }
    </script>
   <script type="text/javascript">
   		function deletesrc(){
   			var newimg = $("#up_img").val();
   			$("#imgShow").attr("src",newimg);
   		}
   
   </script>

</body>
</html>
