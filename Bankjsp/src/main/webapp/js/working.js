
const random1 = Math.floor(Math.random() * 30);
const random2 = Math.floor(Math.random() * 30);
let array = [];
// var p -> 안쓰는것같아서 주석처리함 확인받고 삭제하기

/*------------------ 입금 --------------------- */
function deposit(){
	let plusM=document.querySelector("#plusM").value;
	let depositResult=document.querySelector(".depositResult");
	let depositre="";

	$.ajax({
		url:"/Bankjsp/page/deposit",
		data:{"plusM":plusM},
		success:function(result){
			if(result==='true'){
				depositre="입금 완료됐습니다."
				document.querySelector("#plusM").value=null;
			}else(depositre=result)
		depositResult.innerHTML+=depositre;
		}
	})	
}

/*------------------ 출금 --------------------- */
function withdraw(){
	let withdrawM= document.querySelector("#withdrawM").value;
	let withdrawResult=document.querySelector(".withdrawResult");
	let resultText='';
	$.ajax({
		url:"/Bankjsp/page/withdraw",
		data: {"withdrawM" : withdrawM},
		success: function(result){
			if(result==='1'){
				resultText+="출금 정상적으로 완료됐습니다.";
				document.querySelector("#withdrawM").value=null;
				document.querySelector('.test1').innerHTML=null; // 보안카드 문제 제거
			}else if(result==='2'){
				resultText+="잔액이 부족합니다.";
				
			}else{resultText+="출금과정에 문제가 있습니다. 관리자에게 문의 바랍니다."}
			withdrawResult.innerHTML=resultText;
		}
		
	})
	
}

/*------------------ 이체 --------------------- */
function transfer(){
	let transferM=document.querySelector("#transferM").value;
	let otherAccount=document.querySelector("#otherAccount").value;
	let transferResult=document.querySelector(".transferResult");
	let resultText=''
	
	$.ajax({
		url:"/Bankjsp/page/transfer",
		data:{"transferM":transferM , "otherAccount":otherAccount},
		success:function(result){
			if(result==='0'){
				resultText+="출금과정에 문제가 있습니다. 관리자에게 문의 바랍니다.";
				
			}else if(result==='2'){
				resultText+="잔액이 부족합니다.";
				
			}else if(result==='3'){
				resultText+="존재하지 않는 계좌입니다.";
			}else{
				resultText+=result + "님께 "+transferM+"원이 정상적으로 이체됐습니다."
				document.querySelector("#transferM").value=null;
				document.querySelector("#otherAccount").value=null;
				document.querySelector('.test1').innerHTML=null; // 보안카드 문제 제거
				
			}
			transferResult.innerHTML=resultText;
		}
		
	})
	
	
	
}


/*-------------------- 보안카드 ★문제★ 발급----------------------------- */

function test(no){
	$.ajax({
		url:"/Bankjsp/page/test",
		success: function(result){
			
			// 배열 선언하며 4자리씩 인덱스에 넣기
			for(let i = 0 , a=0 ; i<=116; i+=4 , a++){
				array[a] = (result.substring(i,i+2) + (result.substring(i+2,i+4)) )
				// console.log(array[a])
			}
			
			// jsp class="account" 로 보낼 html 양식 tag 선언
			let test =  document.querySelector('.test1')
			let testtag = '<tr> '+(random1+1)+' 번 '     +array[random1].substring(0,2)+' <input type="password" id="password1">  <input type="password" id="password2"> </tr> <br>'+
						  '<tr> '+(random2+1)+' 번  <input type="password" id="password3">  <input type="password" id="password4"> '+array[random2].substring(2,4)+     '</tr> <br>'+
						  '<button onclick="confirm('+no+')"> 확인 </button>'
						  
						 
			console.log(array[random1].substring(2,3))
			console.log(array[random1].substring(3,4))
			console.log(array[random2].substring(0,1))
			console.log(array[random2].substring(1,2))

			// tag jsp로 보내기
			test.innerHTML = testtag
		}
	})
}


function confirm(no){// 출금이랑 이체 각각 함수실행해줘야해서 매개변수 넣어줌!
	let password1 = document.querySelector("#password1").value;
	let password2 = document.querySelector("#password2").value;
	let password3 = document.querySelector("#password3").value;
	let password4 = document.querySelector("#password4").value;	
		if( password1 == array[random1].substring(2,3) &&
			password2 == array[random1].substring(3,4) &&
			password3 == array[random2].substring(0,1) &&
			password4 == array[random2].substring(1,2) ){
				if(no==1){withdraw();}
				else if(no==2){transfer();}
				
			}
		else{ alert("보안카드 번호를 확인해주세요!") }
}
/*------------------  --------------------- */

