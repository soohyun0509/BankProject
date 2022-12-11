// sendbtn() 온클릭 넣어줘야함

let account=null;            
let ClientSocket=null;
let content=null;
let date=null;
let room=0;
let name=null;

////////소비자 채팅창 열기
function openModal(){
      account=document.querySelector(".account").value
      
      console.log(account+" 계좌")
      if(account==1){
		alert("관리자 채팅방을 이용해주세요"); return;
			
	  }else if(account!='null'){
         ClientSocket=new WebSocket('ws://localhost:8080/Bankjsp/chatting/'+account);
         
         ClientSocket.onclose=function(e){onClose(e)}
         ClientSocket.onopen=function(e){onOpen(e)}
         ClientSocket.onmessage=function(e){onMessage(e)}
      
      }else{
         alert("로그인이 필요합니다.");	
         location.href="/Bankjsp/bankpage/login.jsp"
         return; // 이거 안해주면 모달창 뜬게 보이면서 페이지 넘어가길래 일단 넣어놓음
      }
      let html= 
            '<div class="popup">'+
               '<div class="popup-head">'+
                  '<span class="head-title">땡땡은행</span>'+
               '</div>'+
               '<div class="popup-body">'+
                  '<div class="body-content">'+
                     '<div class="body-titlebox">'+
                        '<h1></h1>'+
                     '</div>'+
                     '<div class="body-contentbox">'+
                     '</div>'+
                  '</div>'+
                  '<div class="sendbox">'+
                     '<textarea class="msgbox" rows="" cols="" onkeyup="enterkey()"></textarea>'+
                     '<button class="sendBtn" onclick="sendBtn()">보내기</button>'+
                  '</div>'+
               '</div>'+
               '<div class="popup-foot">'+
                  '<span class="pop-btn close" id="close" onclick="closeModal()">창 닫기</span>'+
               '</div>'+
            '</div>';
      document.querySelector(".i_modal").style.display="flex"
   document.querySelector(".i_modal").innerHTML=html         

}         

///// 소비자 메시지 웹소켓으로보내기
function sendBtn(){
   let msg = document.querySelector(".msgbox").value
   $.ajax({
      url:"/Bankjsp/chattingDB",
      data:{"msg" : msg , "account" : account ,"type" : 2}, // 2면 방
      success : function(re){
         let json = JSON.parse(re)
         json.forEach(j =>{
            content=j.c_content;
            name=j.name;
            room=j.c_room;
            date=j.c_date
         })
         send()
      }
   })
   
}




// 메시지 보냈을때
function send(){
   let message={
      content : content,
      name : name,
      c_room : room,
      type: "msg",
      c_date : date
   }
   
   console.log(message)
   ClientSocket.send(JSON.stringify(message));
   document.querySelector(".msgbox").value=null;
}

console.log(account)
// 접속
function onOpen(e){
   console.log(e + "접속")
	$.ajax({
		url : "/Bankjsp/chattingDB",
		data : {"account" : account , "type" : 1}, // 1이면 채팅방 열자마자 방생성
		async : false,
		success : re=>{
			room=JSON.parse(re)
			
		}	
	})



   if(account!=null){
	   document.querySelector(".body-contentbox").innerHTML
      +='<div class="alarm">'+
            '<span class="msg_content">안녕하세요 땡땡은행 대출상담입니다. 문의하실 사항을 남겨주세요</span>'+
                 
         '</div>'
	}

}

// 나가기
function onClose(e){console.log(e +"나감")}
// 메시지
function onMessage(e){
   let msg=JSON.parse(e.data);
   
   if(msg.c_room==room){
      if(msg.type=="msg"){ // 타입이 메시지면
         if(msg.name=="admin"){ // 방번호가 같고 문자를 보낸사람이 관리자면
         document.querySelector(".body-contentbox").innerHTML
               +='<div class="adminmsg">'+
               	'<span class="msg_content">'+msg.content+'</span>'+
                  '<span class="msg_date">'+msg.c_date+'</span>'+
                     
                  '</div>'
         }else {
            document.querySelector(".body-contentbox").innerHTML
                  +='<div class="c_msg">'+
                  	'<span class="msg_date">'+msg.c_date+'</span>'+
                     '<span class="msg_content">'+msg.content+'</span>'+
                   '</div>'
             console.log(msg.date)
         
         }
      }else if(msg.type="alarm"){ // 타입이 알람이면
         document.querySelector(".body-titlebox").innerHTML='<h3>'+msg.content+'</h3>'
      }

   }
    /*===========================스크롤 하단으로 자동 내리기==================*/

	document.querySelector(".body-contentbox").scrollTop=
	document.querySelector(".body-contentbox").scrollHeight
   
   
} // 메시지 받았을때


// 모달창 닫기
function closeModal(){
   document.querySelector(".i_modal").style.display="none";
   ClientSocket.close()
         
   
}






