


// 유효성 결과 표시할 부분 가져오기
let check= document.querySelectorAll(".check");

//유효성 통과하면 true로 바꿔주려고
let Cname=false;
let Cphone=false;
let Caddress=false;
let Cpassword=false;
let icon='<i class="fas fa-check"></i>';
/*---------- 이름 ---------------- */
// 10글자 이내로 한글 & 영어
function nameCheck(){
	let name=document.querySelector("#name").value;
	let $name=/^[a-zA-Z가-힣]{2,10}$/
	if($name.test(name)){check[0].innerHTML= icon; Cname=true;}
	else {check[0].innerHTML="이름에는 한글 또는 영어로 10글자 이내만 적을 수 있습니다."; Cname=false;}
}

/*---------- 전화번호 ------------- */
// 13글자   - 꼭 포함
function phoneCheck(){
	let phone= document.querySelector("#phone").value;
	let $phone=/^([0-9]{2,3})-([0-9]{3,4})-([0-9]{3,4})$/
	if($phone.test(phone)){check[1].innerHTML= icon; Cphone=true;}
	else {check[1].innerHTML="지역번호-xxxx-xxxx 형식의 번호를 적어주세요"; Cphone=false;}
}


/*---------- 비밀번호 ------------- */
// 숫자 4자리
function passwordCheck(){
	let password=document.querySelector("#password").value;
	let $password=/^[0-9]{4}$/
	
	if($password.test(password)){check[2].innerHTML=icon; Cpassword=true;}// 규정에 안맞는다고 안내
	else{check[2].innerHTML="비밀번호는 숫자 4자리만 가능합니다."; Cpassword=false;}
}
/*---------- 주소 -----------------*/
// 이벤트 걸어줘야되니까 밖에다 선언
let sample4_postcode=document.querySelector("#sample4_postcode")
let sample4_roadAddress=document.querySelector("#sample4_roadAddress")
let sample4_jibunAddress=document.querySelector("#sample4_jibunAddress")
let sample4_detailAddress=document.querySelector("#sample4_detailAddress")
// 쉼표 사용 못하게
function addressCheck(e){
			// 약간 e.currentTarget 요거가 이벤트가 일어난 대상(객체?) 이런건가...
	let address=e.currentTarget.value;
	//영문자, 소문자, 숫자, "-","_" 로만 구성된 길이 2~10자리 사이의 문자열
	// 으음 그냥 주소는 쉼표만 하는거로 할까..
	//let $address= /^[a-zA-Z가-힣0-9_-]{2,10}$/
	if(address.indexOf(",")!=-1){check[3].innerHTML="주소에는 쉼표(,)를 포함할 수 없습니다."; Caddress=false;}
	else{check[3].innerHTML=icon; Caddress=true; }

}
// change 이벤트 일어나면 addressCheck함수 발생되도록
sample4_postcode.addEventListener('change',addressCheck );
sample4_roadAddress.addEventListener('change',addressCheck );
sample4_jibunAddress.addEventListener('change',addressCheck );
sample4_detailAddress.addEventListener('change',addressCheck );


/*--------------------계좌생성----------------------------- */
// 저 위에 유효성 다 통과하면 계좌생성하도록 해야함
function signup(){
	
	if(!(Cname&&Caddress&&Cpassword&&Cphone)){alert("모든 정보를 정확히 입력해주세요"); return;}
	let data={
		name : document.querySelector("#name").value,
		phone : document.querySelector("#phone").value,
		password : document.querySelector("#password").value,
		maddress1 : document.querySelector("#sample4_postcode").value,
		maddress2 :document.querySelector("#sample4_roadAddress").value,
		maddress3 :document.querySelector("#sample4_jibunAddress").value,
		maddress4 :document.querySelector("#sample4_detailAddress").value
	}
	$.ajax({
		url:"/Bankjsp/page/signup",
		data:data,
		success: function(result){
			$("#signup_refresh").load( location.href+' #signup_refresh')
			document.querySelector(".resultAccount").innerHTML=	'<h3>계좌 생성 완료 </h3>'+
								'<span>고객님의 계좌는 : '+result+'입니다. </span>'
			mekeSecureCard(result)
			
			
		}
	})
	
}



/*-------------------- 보안카드 발급----------------------------- */
function mekeSecureCard(account){
	$.ajax({
		url:"/Bankjsp/page/secureCard",
		data:{"account" : account},
		success: function(result){
			let array = [];
			// 배열 선언하며 4자리씩 인덱스에 넣기
			for(let i = 0 , a=0 ; i<=116; i+=4 , a++){
				array[a] = result.substring(i,i+4)
				//console.log(array[a])
			}
			// jsp class="account" 불러오기
			let securecard =  document.querySelector('.securecard')
			// jsp class="account" 로 보낼 html 양식 tag 선언
			securecard.style.display="block"
			let tag = '<h3>보안카드</h3>';	
			for(let i = 0; i<30; i++){
				let m = array[i]
				let num = i+1
				if(i%3==0){tag+="<br>"}
				tag +='<span>'+num+'번     :  '+ m.substring(0,2) + m.substring(2,4)+'</span>'
			}
			
			// tag jsp로 보내기
			securecard.innerHTML=tag;
			
			
		}
	})
}

/*---------------------------- 카카오우편 API  ---------------------------------- */
//본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
function sample4_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 참고 항목 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample4_postcode').value = data.zonecode;
            document.getElementById("sample4_roadAddress").value = roadAddr;
            document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
            
            // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
            if(roadAddr !== ''){
                document.getElementById("sample4_extraAddress").value = extraRoadAddr;
            } else {
                document.getElementById("sample4_extraAddress").value = '';
            }

            var guideTextBox = document.getElementById("guide");
            // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
            if(data.autoRoadAddress) {
                var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                guideTextBox.style.display = 'block';

            } else if(data.autoJibunAddress) {
                var expJibunAddr = data.autoJibunAddress;
                guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                guideTextBox.style.display = 'block';
            } else {
                guideTextBox.innerHTML = '';
                guideTextBox.style.display = 'none';
            }
        }
    }).open();
}
/* 카카오 우편 API 종료 */


