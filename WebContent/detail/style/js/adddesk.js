function addform(){
	var dname = document.getElementById("addname").value;
		if(dname == null||dname == ""){
			var s1Ele = document.getElementById("addspan");
			s1Ele.innerHTML = "<font color='red'>桌名不能为空</font>";
			return false;
		}
		return true;
}