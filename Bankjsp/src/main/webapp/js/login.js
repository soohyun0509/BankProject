

/*-------------- 로그인 ------------------ */
function login(){
	
	let account=document.querySelector("#account").value;
	let password=document.querySelector("#password").value;
	let loginresult=document.querySelector("#loginresult");
	
	$.ajax({
		url:"/Bankjsp/page/login",
		data: {"account" : account,"password" : password},
		success : function(result){
			if(result=='3'){
				loginresult.innerHTML="관리자.";
				location.href="/Bankjsp/index.jsp"
			}else{
				if(result==='1'){
				loginresult.innerHTML="로그인 성공했습니다.";
				location.href="/Bankjsp/index.jsp"
				
				}else if(result==='2'){alert("비밀번호가 틀렸습니다."); $("#logininput").load( location.href+' #logininput')}
				 else if(result==='4'){alert('비밀번호 오류 횟수가 초과되었습니다. 은행을 방문해주세요.')}
				 else if(result=='5'){alert("존재하지 않는 계좌입니다."); $("#logininput").load( location.href+' #logininput')}
				 }
			}
	})
}