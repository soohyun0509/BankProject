
let selectSno=null;
let selects_month=null; // 적금 기간
savingcontent()



/*---------- 적금 목록 호출 --------------------- */
function savingcontent(){
	
	let savingcontent = document.querySelector(".savingcontent");
	console.log(savingcontent);
	let html='<div>적금명<br>적금내용<br>적금 기간<br>월 최대 이체 한도<br></div>'
	$.ajax({
		url:"/Bankjsp/page/savingSingup",
		type: "get",
		success: function(result){
			let list=JSON.parse(result)
			for(let i=0; i<list.length; i++){
				let c=list[i]
			
				
				html+='<div class="borderClass"></div><div>'+c.s_name+'<br>'+c.s_content+'<br>'+c.s_month+'개월 <br>'+c.s_limit.toLocaleString()+'원 <br><button class="minibtn" onclick="savingForm('+c.s_no+','+c.s_month+')">적금신청하기</button></div>';
			
			}
			
			
			
			savingcontent.innerHTML=html;	
		}
	})
}


// 적금 목록 출력




/*---------------- 적금신청폼 생성하기  ---------------------------- */
function savingForm(s_no, s_month){
	let savingform=document.querySelector(".savingform");
	
	savingform.style.display='block';
	selects_month=s_month
	selectSno=s_no;
	console.log(selectSno);
	
	console.log(selects_month+"월");
	
}


const date = new Date()
//Mon Aug 01 2022 00:00:00 GMT+0900 (한국 표준시)
const currentYear = date.getFullYear()
const currentMonth = date.getMonth()+1
const currentDate = date.getDate()
let toDay = new Date( currentYear , currentMonth-1 , 1 )
const monthDays = document.querySelector('.days')

let s_sdate = currentYear+'-'+currentMonth+'-'+currentDate



//******* 적금신청폼- */
function savingSignup(){
	
	let html="";
	let today= new Date(); 
	let month=today.getMonth()+1
	let date=today.getDate();

	// 폼 입력내용 한번에 전송
	let data={
		name : document.querySelector("#name").value,
		s_pay : document.querySelector("#s_pay").value,
		s_no: selectSno,
		s_sdate : s_sdate,
		s_month : selects_month
	}
	
	$.ajax({
		url:"/Bankjsp/page/savingSingup",
		data : data,
		type : "post",
		success: function(result){
		   if(result!='null'){ // result가 true면 입금까지 완료
		   	//SweetAlert2 테마 이용
				Swal.fire({
			      icon: 'success',
			      title: '적금신청이 정상적으로 완료됐습니다.',
			      text: `적금계좌는 ${result}이며, 자동이체날짜는 매월 ${date}일 입니다.`  // 이거 정해야할텐데...
				});
				
			}else if(result==0){ // 로그인한 계좌랑 다를때
				Swal.fire({
			      icon: 'error',
			      title: '적금신청이 실패했습니다.',
			      text: '로그인한 계좌로만 적금이체가 가능합니다.'
				});
			}else{alert("DB오류")}
		}
		
	})
}

function cal(){
	$.ajax({
		url:"/Bankjsp/savingcal",
		type : "get",
		success : function(re){
			alert(re)
		}
	})
}