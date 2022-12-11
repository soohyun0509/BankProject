
list()
 
 // 대출 상품 목록
 function list(){
	
	$.ajax({
		url:"/Bankjsp/admin/adminloan",
		success : function(re){
			let boardlist = JSON.parse(re)
			let html = ""
			for(let i = 0 ; i<boardlist.length; i++){
				let b = boardlist[i]
				
				html += '<tr>' +
				      '<td>'+b.l_no+'</td>'+
					  '<td onclick="viewload('+b.bno+')">'+b.lname+'</td>'+
		               '<td>'+b.lcontent+'</td>'+
					   '<td>'+b.limitmoney+'</td>'+
					   '<td>'+b.rate+'</td>'+
					   '<td>'+b.ltime+'</td>'+
					   '<td><button class="delBtn" type="button" onclick="loandelete('+b.l_no+')"> 삭제 </button></td>'+
		               '</tr>';
			}
			
			document.querySelector(".addBtnBox").innerHTML= '<button class="addBtn" type="button" onclick="loanadd()"> 대출 추가 </button>'
			document.querySelector('.btable').innerHTML += html
		}
	})
}


// 대출 상품 삭제
function loandelete(l_no){
	if(confirm(l_no+'번 항목을 삭제하시겠습니까?')){
		$.ajax({
			url:"/Bankjsp/admin/adminloandelete",
			data:{"l_no" : l_no},
			success : function(result){
				if(result=='true') {
					alert('삭제 성공')
					 location.href = '/Bankjsp/admin/admin_loan.jsp'
				}else{
					alert('동일하지 않은 정보 입니다.')}
				}
		})
	}
}
// 대출 상품 추가하기
function loanadd(){
	
	html =  '<div class="loadAddBox">'
			+'<input type="text" id="lname" placeholder="상품명">'
			+ '<input type="text" id="lcontent" placeholder="상품내용설명">'
			+ '<input type="text" id="limitmoney" placeholder="대출한도">'// db에서 계산해야함
			+ '<input type="text" id="rate" placeholder="이자율">'
			+ '<input type="text" id="ltime" placeholder="대출기한(년 단위)">'
			+'<div><button type="button" class="loanaddbtn"> 등록하기 </button></div>'
			+'</div>'
	document.querySelector('.laonaddinput').innerHTML = html
	
	
	
	
	
	let loanaddbtn=document.querySelector(".loanaddbtn");
	loanaddbtn.addEventListener('click', ()=>{
		let info={ 
		lname: document.querySelector('#lname').value,
		lcontent: document.querySelector('#lcontent').value,
		limitmoney: document.querySelector('#limitmoney').value,
		rate: document.querySelector('#rate').value,
		ltime: document.querySelector('#ltime').value
		
		}
	console.log(info);
	
		$.ajax({
			url:"/Bankjsp/admin/adminloanadd",
			data: info,
			success : function(result){
				if(result=='true') {
					 alert('추가 성공')
					 location.href = '/Bankjsp/admin/admin_loan.jsp'
				}else{alert('동일하지 않은 정보 입니다.')}
				}
		})
	})

	
	
}


