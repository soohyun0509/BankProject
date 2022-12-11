

slist()
 
 // 적금 상품 목록
 function slist(){
	$.ajax({
		url:"/Bankjsp/admin/adminsaving",
		success : function(re){
			let boardlist = JSON.parse(re)
			let html = ""
			for(let i = 0 ; i<boardlist.length; i++){
				let s = boardlist[i]
				
				html += '<tr>' +
				      '<td>'+s.s_no+'</td>'+
					  '<td onclick="viewload('+s.s_no+')">'+s.s_name+'</td>'+
		               '<td>'+s.s_content+'</td>'+
					   '<td>'+s.s_rate+'</td>'+
					   '<td>'+s.s_limit+'</td>'+
					   '<td>'+s.s_month+'</td>'+
					   '<td><button class="delBtn" type="button" onclick="savingdelete('+s.s_no+')"> 삭제 </button></td>'+
		               '</tr>';
			}
			document.querySelector(".addBtnBox").innerHTML
					= '<button class="delBtn" type="button" onclick="savingadd()"> 적금 추가 </button>'
			document.querySelector('.stable').innerHTML += html
		}
	})
}

// 적금 상품 추가하기
function savingadd(){
	
	html = '<div class="savingAddBox">'
	 		+'<input type="text" id="s_name"  placeholder="상품명">'
			+ '<input type="text" id="s_content"  placeholder="상품내용 설명">'
			+ '<input type="text" id="s_limit"  placeholder="적금한도">'// db에서 계산해야함
			+ '<input type="text" id="s_rate"  placeholder="이자율">'
			+ '<input type="text" id="s_month"  placeholder="적금기한(월 단위)">'
			+'<button type="button" class="savingaddbtn"> 등록하기 </button>'
			+'</div>'
	document.querySelector('.savingaddinput').innerHTML = html

	let savingaddbtn = document.querySelector(".savingaddbtn");
	
	savingaddbtn.addEventListener('click', ()=>{
		let info={ 
			s_name: document.querySelector('#s_name').value,
			s_content: document.querySelector('#s_content').value,
			s_limit: document.querySelector('#s_limit').value,
			s_rate: document.querySelector('#s_rate').value,
			s_month: document.querySelector('#s_month').value
		}
		$.ajax({
			url:"/Bankjsp/admin/admingsavingadd",
			data: info,
			success : function(result){
				if(result=='true') {
					 alert('추가 성공')
					 location.href = '/Bankjsp/admin/admin_saving.jsp'
				}else{alert('동일하지 않은 정보 입니다.')}
			}
		})
	})

	
	
}


// 대출 상품 삭제
function savingdelete(s_no){
	if(confirm(s_no+'번 항목을 삭제하시겠습니까?')){
		$.ajax({
			url:"/Bankjsp/admin/admingsavingdelete",
			data:{"s_no" : s_no},
			success : function(result){
				if(result=='true') {
					alert('삭제 성공')
					 location.href = '/Bankjsp/admin/admin_saving.jsp'
				}else{
					alert('동일하지 않은 정보 입니다.')}
				}
		})
	}
}















