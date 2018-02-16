function addcuisine(){
	var cname = document.getElementById("addcuisinename").value;
		if(cname == null||cname == ""){
			var s1Ele = document.getElementById("addcuisinespan");
			s1Ele.innerHTML = "<font color='red'>菜系名不能为空</font>";
			return false;
		}
		return true;
}

function updatecuisine(){
	var cname = document.getElementById("updatecuisinename").value;
		if(cname == null||cname == ""){
			var s1Ele = document.getElementById("updatecuisinespan");
			s1Ele.innerHTML = "<font color='red'>菜系名不能为空</font>";
			return false;
		}
		return true;
}