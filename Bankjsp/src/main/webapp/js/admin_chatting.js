

chattinglist()



let account=document.querySelector(".account").value

let ClientSocket=new WebSocket('ws://localhost:8080/Bankjsp/chatting/'+account);
let content=null;
let date=null;
let room=0;
let name=null;

// 왜 밖에 놔두면 되고 함수 안에 넣어놓으면 안되냐고 왜 니맘대로냐고
ClientSocket.onopen=function(e){onOpen(e)}
ClientSocket.onclose=function(e){onClose(e)}
ClientSocket.onmessage=function(e){onMessage(e)}


// 채팅목록 중에 하나 누르면 채팅창 열리면서 관리자소켓 접속
function openAdminChat(c_room){

	// 방번호와 같은 값가진 애 데이터 다 불러오기
	$.ajax({
		url: "/Bankjsp/adminchatting",
		type : "POST",
		data : {"roomnum" :c_room},
		success : (re)=>{
			let json= JSON.parse(re)
			let html=''
			json.forEach(p=>{
				html+='<div class="msgcontent"><span class="c_msg">'+p.c_content+'</span></div>'
				
			
			})
			document.querySelector(".textsendbox").innerHTML='<div class="sendmsg">'+
					'<textarea rows="" cols="" class="adminmsg"></textarea>'+
					'<button class="sendBtn" onclick="adminBtn('+c_room+')">보내기</button>'+
				'</div>'
			document.querySelector(".msgbox").innerHTML=html
		}
		
	})	
		
	
}
/////// 관리자 메시지 웹소켓으로 보내기
function adminBtn(roomnum){
   let adminmsg = document.querySelector(".adminmsg").value
   $.ajax({
      url:"/Bankjsp/chattingDB",
      data:{"msg" : adminmsg , "account" : account , "roomnum" : roomnum , "type" : 2},
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
   ClientSocket.send(JSON.stringify(message));
   document.querySelector(".msgbox").value=null;
}

function onOpen(e){
	console.log(e + "관리자 접속")
}
// 나가기
function onClose(e){console.log(e +"나감")}
// 메시지 받으면
function onMessage(e){
   let msg=JSON.parse(e.data);
   console.log("메시지받음"+msg) //msg 아직 object!! 객체안에 값으로 빼오기
   if(msg.c_room==room){
      if(msg.type=="msg"){ // 타입이 메시지면
         if(msg.name==name){ // 세션에 있는게 관리자면
         document.querySelector(".msgbox").innerHTML
               +='<div class="a_msg"><span class="amsg">'+msg.content+'</span></div>'
         }else {
            document.querySelector(".msgbox").innerHTML
                  +='<div class="msgcontent"><span class="c_msg">'+msg.content+'</span></div>'
         
         }
      }
   
   }
   /*===========================스크롤 하단으로 자동 내리기==================*/

	document.querySelector(".msgbox").scrollTop=
		document.querySelector(".msgbox").scrollHeight
   
   
} // 메시지 받았을때



function chattinglist(){
   $.ajax({
      url:"/Bankjsp/adminchatting",
      success : function(re){
         let json = JSON.parse(re)
         html =''
         json.forEach(j =>{
            html += '<tr onclick="openAdminChat('+j.c_room+')"> <td> '+j.c_room+'</td> <td> '+j.name+'</td> </tr> </br>'
         })
         document.querySelector(".table").innerHTML+=html
      }
   }) 

}