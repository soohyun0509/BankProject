

/*----------- 계좌찾기 --------------- */
function searchAccount(){
	
	let name=document.querySelector("#name").value;
	let phone=document.querySelector("#phone").value;
	let account=document.querySelector("#account");
	
	
	$.ajax({
		url:"/Bankjsp/page/searchaccount",
		data: {"name" : name , "phone" : phone},
		success: function(result){
			account.innerHTML=result;
		}
		
		
	})
}


