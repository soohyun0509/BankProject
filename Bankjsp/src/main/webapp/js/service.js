loancontent() //페이지 들어가자마자 대출상품 불러오기
let selectLno=0; // 사용자가 선택한 대출번호 담아줄거임
let selectLimit=0; // 선택한 대출상품의 대출한도
let Camount=false; // 유효성 체크시 사용
let Caccount=false;
/*-------- 대출상품 불러오기 10/18 ------------------ */
function loancontent(){
	
	let loancontent = document.querySelector(".loancontent");
	let html='<div>대출명<br>대출내용<br>최대대출금액<br></div>'
	//let html='<tr><th>대출명</th><th>대출내용</th><th>최대대출금액</th></tr>';
	
	$.ajax({
		url:"/Bankjsp/page/loancontent",
		success: function(result){
			let list=JSON.parse(result);
			for(let i=0; i<list.length; i++){
				let c=list[i]
				html+='<div class="borderClass"></div><div>'+c.lname+'<br>'+c.lcontent+'<br>'+c.limitmoney+'<br><button class="minibtn" onclick="loanForm('+c.lno+','+c.limitmoney+')">대출신청하기</button></div>';
				//html+='<tr><td>'+c.lname+'</td><td>'+c.lcontent+'</td><td>'+c.lmoney+'</td></tr>';		//대출상품번호 전달하기
			}
			
			
			loancontent.innerHTML=html;
			
			
		}
	})
}

/*---------------- 대출신청폼 생성하기 10/19  ---------------------------- */
function loanForm(lno, limitmoney){
	let loanform=document.querySelector(".loanform");
	
	loanform.style.display='block';
	
	selectLno=lno;
	selectLimit=limitmoney;
	
}

/*----------- 계좌번호 유효성 체크 10/23 ---------------------- */
function accountCheck(){
	let account=document.querySelector("#account").value;
	let $account=/^(21)-([0-9]{3,4})-([0-9]{3,4})$/
	if($account.test(account)){check[2].innerHTML=icon; Caccount=true;}
	else{check[2].innerHTML="21-xxxx-xxxx 형식으로 입력해주세요"; Caccount=false;}
}

/*-------대출신청금액 유효성 검사 10/23--------------- */
function amountCheck(){
	let writeAmount=document.querySelector("#amount").value;
	let $amount=/^[0-9]+$/
	if(!($amount.test(writeAmount))){check[4].innerHTML="숫자만 입력가능합니다."; Camount=false;}
	else if(writeAmount>selectLimit){check[4].innerHTML="이 상품의 대출한도는 "+selectLimit +" 입니다."; Camount=false;}
	else{check[4].innerHTML=icon; Camount=true;}
	
}

/*------------ 대출신청폼 DB전송 10/19  ---------------- */
/*------ 유효성 검사 포함 수정중  10/23 -----------*/
function loanSignup(){
	if(!(Cname&&Caddress&&Caccount&&Cphone&&Camount)){alert("모든 정보를 정확히 입력해주세요"); return;}
	let html="";
	let today= new Date(); // 대출받은 날짜를 납입날짜로 해줄거니까!
	let month=today.getMonth()+1
	let date=today.getDate();
	// 아 다 변경해서 해주려그랬는데...약간 힘들어...
	//let ldate= date.getDate(); // 날짜만 빼오기

	console.log(date);
	// 폼 입력내용 한번에 전송
	let data={
		name : document.querySelector("#name").value,
		phone : document.querySelector("#phone").value,
		account : document.querySelector("#account").value,
		amount : document.querySelector("#amount").value,
		maddress1 : document.querySelector("#sample4_postcode").value,
		maddress2 :document.querySelector("#sample4_roadAddress").value,
		maddress3 :document.querySelector("#sample4_jibunAddress").value,
		maddress4 :document.querySelector("#sample4_detailAddress").value,
		lno: selectLno
	}
	
	$.ajax({
		url:"/Bankjsp/page/loanSignup",
		data : data,
		success: function(result){
		   if(result=='true'){ // result가 true면 입금까지 완료
		   	//SweetAlert2 테마 이용
				Swal.fire({
			      icon: 'success',
			      title: '대출신청이 정상적으로 완료됐습니다.',
			      text: `납부날짜는 매월 ${date}일 입니다.`
				});
				setTimeout(()=>{location.href="/Bankjsp/index.jsp"},2000)
				
			}else if(result==2){ // 개인정보 입력 틀렸을때
				Swal.fire({
			      icon: 'error',
			      title: '대출신청이 실패했습니다.',
			      text: '계좌생성시 기입한 정보를 입력하셔야 합니다.'
				});
			}else if(result==0){ // 로그인한 계좌랑 다를때
				Swal.fire({
			      icon: 'error',
			      title: '대출신청이 실패했습니다.',
			      text: '로그인한 계좌로만 대출이 가능합니다.'
				});
			}else{alert("DB오류")}
		}
		
	})
	
	
	
}