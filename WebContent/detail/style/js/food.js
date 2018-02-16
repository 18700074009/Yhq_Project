function addfood(){
	var fname = document.getElementById("addfoodfname").value;
		if(fname == null||fname == ""){
			var s1Ele = document.getElementById("addfnamespan");
			s1Ele.innerHTML = "<font color='red'>菜名不能为空</font>";
			return false;
		}
	var price = document.getElementById("addfoodprice").value;
		if(price == null||price == ""){
			var s2Ele = document.getElementById("addpricespan");
			s2Ele.innerHTML = "<font color='red'>价格不能为空</font>";
			return false;
		}
	var re = /^\d+(\.\d{1,2})?$/;
	if(!re.test(price)){
		var s2Ele = document.getElementById("addpricespan");
		s2Ele.innerHTML = "<font color='red'>请输入正确价格</font>";
		return false;
	}
	
	var vipprice = document.getElementById("addfoodvipprice").value;
		if(vipprice == null||vipprice == ""){
			var s3Ele = document.getElementById("addvippricespan");
			s3Ele.innerHTML = "<font color='red'>vip价格不能为空</font>";
			return false;
		}
		if(!re.test(vipprice)){
			var s3Ele = document.getElementById("addvippricespan");
			s3Ele.innerHTML = "<font color='red'>请输入正确的会员价格</font>";
			return false;
		}
		
		
		return true;
}


function updatefood(){
	var fname = document.getElementById("updatefoodfname").value;
		if(fname == null||fname == ""){
			var s1Ele = document.getElementById("updatefnamespan");
			s1Ele.innerHTML = "<font color='red'>菜名不能为空</font>";
			return false;
		}
	var price = document.getElementById("updatefoodprice").value;
		if(price == null||price == ""){
			var s2Ele = document.getElementById("updatepricespan");
			s2Ele.innerHTML = "<font color='red'>价格不能为空</font>";
			return false;
		}
	var re = /^\d+(\.\d{1,2})?$/;
	if(!re.test(price)){
		var s2Ele = document.getElementById("updatepricespan");
		s2Ele.innerHTML = "<font color='red'>请输入正确价格</font>";
		return false;
	}
	
	var vipprice = document.getElementById("updatefoodvipprice").value;
		if(vipprice == null||vipprice == ""){
			var s3Ele = document.getElementById("updatevippricespan");
			s3Ele.innerHTML = "<font color='red'>vip价格不能为空</font>";
			return false;
		}
		if(!re.test(vipprice)){
			var s3Ele = document.getElementById("updatevippricespan");
			s3Ele.innerHTML = "<font color='red'>请输入正确的会员价格</font>";
			return false;
		}
		
		
		return true;
}
