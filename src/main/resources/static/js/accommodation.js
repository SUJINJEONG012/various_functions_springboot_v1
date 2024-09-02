//지도
var mapContainer = document.getElementById('map'), // 지도를 표시할 div
	mapOption = {
		center: new daum.maps.LatLng(37.537187, 127.005476), // 지도의 중심좌표
		level: 5 // 지도의 확대 레벨
	};

//지도를 미리 생성
var map = new daum.maps.Map(mapContainer, mapOption);
//주소-좌표 변환 객체를 생성
var geocoder = new daum.maps.services.Geocoder();
//마커를 미리 생성
var marker = new daum.maps.Marker({
	position: new daum.maps.LatLng(37.537187, 127.005476),
	map: map
});


function execution_daum_address() {
	new daum.Postcode({
		oncomplete: function(data) {
			var addr = data.address; // 최종 주소 변수

			// 주소 정보를 해당 필드에 넣는다.
			document.getElementById("accommodationAdress").value = addr;
			// 주소로 상세 정보를 검색
			geocoder.addressSearch(data.address, function(results, status) {
				// 정상적으로 검색이 완료됐으면
				if (status === daum.maps.services.Status.OK) {

					var result = results[0]; //첫번째 결과의 값을 활용

					// 해당 주소에 대한 좌표를 받아서
					var coords = new daum.maps.LatLng(result.y, result.x);
					// 지도를 보여준다.
					mapContainer.style.display = "block";
					map.relayout();
					// 지도 중심을 변경한다.
					map.setCenter(coords);
					// 마커를 결과값으로 받은 위치로 옮긴다.
					marker.setPosition(coords)
				}
			});
		}
	}).open();
}

// 별 모양 라디오 버튼 처리
/*document.addEventListener('click', function() {
	const stars = document.querySelectorAll('.star');
	const agradeInput = document.getElementById('accommodationGrade');
	const currentGrade = agradeInput.value;
	
	// 초기 성급을 설정
	setActiveStars(currentGrade);
	console.log()

	
	stars.forEach(function(star) {
		star.addEventListener('click', function() {
			const value = this.getAttribute('data-value');
			agradeInput.value = value; //선택된 값 설정 (선택된 별의 값)
			setActiveStars(value);
		});
	});

	function setActiveStars(value) {
		stars.forEach(function(star) {
			if (parseInt(star.getAttribute('data-value')) <= parseInt(value)) {
				star.classList.add('active');
			} else {
				star.classList.remove('active');
			}
		});
	}
});*/

document.addEventListener('DOMContentLoaded', function() {
            const stars = document.querySelectorAll('.star');
            const agradeInput = document.getElementById('accommodationGrade');
            const currentGrade = agradeInput.value;

            // 초기 성급을 설정
            setActiveStars(currentGrade);

            stars.forEach(function(star) {
                star.addEventListener('click', function() {
                    const value = this.getAttribute('data-value');
                    agradeInput.value = value; // 선택된 값 설정 (선택된 별의 값)
                    setActiveStars(value);
                });
            });

            function setActiveStars(value) {
                stars.forEach(function(star) {
                    if (parseInt(star.getAttribute('data-value')) <= parseInt(value)) {
                        star.classList.add('active');
                    } else {
                        star.classList.remove('active');
                    }
                });
            }
 });
 


// 숙소등록 저장        
document.addEventListener("DOMContentLoaded", function() {	
  
	//저장 버튼 핸들러
	let acsaveBtn = document.getElementById("acsaveBtn");
	
	acsaveBtn.addEventListener("click", function(e) {
	// 기본 동작인 폼 제출을 중지
	e.preventDefault();
	
	// 숙소 이름이 비어 있지 않으면 폼 제출
  	let formData = new FormData(document.getElementById("saveFormAccommodations"));
  
	// 숙소 이름이 비어 있는지 확인
	const inputs = document.querySelectorAll("#saveFormAccommodations.input");
	let isEmpty = false;

		for (let i = 0; i < inputs.length; i++) {
			if (inputs[i].value.trim() === "") { // 각 입력 요소의 값을 trim하여 비어 있는지 확인
				isEmpty = true;
				break;
			}
		}

		if (isEmpty) {
			// 하나 이상의 입력 값이 비어 있을 경우 사용자에게 알림
			alert("입력값을 제대로 입력해주세요.");
			return;
		}
		
   $.ajax({
		 url : "/admin/accommodation/save",
		 type : "POST",
		 data : formData,
		 processData : false,
		 contentType : false,
		 success:function(response){
			 console.log(response);
			 alert("요청성공!");
			 alert("응답데이터 : " + response);
			 
			 // ajax요청이 성공후 폼 데이터 초기화
			 document.getElementById("saveFormAccommodations").reset();
		 },
		 error:function(xhr, status, error){
			 console.error("요청 실패 : ", error);
		 }
		 
	 });
	});
	
	
		
});

// 파일선택
function selectFile(element){
	
	const file = element.files[0];
	const filename = element.closest('.file_input').firstElementChild;
	
	// 1. 파일 선택창에서 취소 버튼이 클릭된 경우
	if(!file){
		filename.value='';
		return false;
	}
	// 2. 파일 크기가 10MB를 초과하는 경우
	const fileSize= Math.floor(file.size/ 1024/1024);
	if(fileSize > 10){
		alert("10MB 이하의 파일로 업로드 해주세요.");
		filename.value='';
		element.value='';
		return false;
	}
	// 3. 파일명 지정
	filename.value= file.name;	
}


// 파일추가
function addFile(){
	const fileDiv = document.createElement('div');
	fileDiv.className = 'add_file';
	fileDiv.innerHTML = `
	<div class="file_input">
		                <input type="text" readonly />
		                <label> 첨부파일
		                    <input type="file" name="files" onchange="selectFile(this);" />
		                </label>
		            </div>
		            <button type="button" onclick="removeFile(this);" class="btns del_btn"><i class="material-symbols-outlined">cancel</i><span>삭제</span></button>
	`;
	document.querySelector('.file_list').appendChild(fileDiv);
}

// 파일 삭제
function removeFile(element){
	const fileAddBtn = element.nextElementSibling;
	if(fileAddBtn){
		const inputs = element.previousElementSibling.querySelectorAll('input');
		inputs.forEach(input => input.value = '')
		return false;
	}
	element.parentElement.remove();
}

