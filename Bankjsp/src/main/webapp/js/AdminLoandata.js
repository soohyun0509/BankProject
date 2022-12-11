getLoanData()
// 대출 상환 내역 호출
function getLoanData(){
	let html="";
	$.ajax({
		url:"/Bankjsp/admin/loandata",
		success : function(re){
			let json=JSON.parse(re);
			json.forEach(list=>{
				// 이자 계산해서 소수점 버리기
				let rate=Math.floor(((list.lbalance * list.rate ) * list.ltime) / (list.ltime*12));
				// 천단위 소수점 찍기
				let balance=list.lbalance.toLocaleString('ko-KR');
				rate=rate.toLocaleString('ko-KR');
				
				// 미납횟수 검사해서 납부상태 표시하기
				let repayResult="정상"
				if(list.repay!=0){repayResult=list.repay+"개월 미납"}
				
				
				
			// c.depositmoney-(((p.lbalance * l.rate) * l.ltime) / (l.ltime*12))
				html+='<tr>'+
				'<td>'+list.p_no+'</td><td>'+list.name+'</td>'+
				'<td>'+list.lname+'</td><td>'+balance+'원 </td>'+
				'<td>'+rate+'원 </td><td>'+list.ldate+'</td>'+
				'<td>'+list.account+'</td>'+
				'<td>'+repayResult+'</td>'+
				'</tr>'
				// 대출기한 , 대출시작날짜 둘다 가져오긴 함
			})
			document.querySelector(".loanData_table").innerHTML+=html;
			
	
		}
	})
	
}
