/**
 * 
 */


// 지도불러오는 스크립트
let map;
let marker;

function initializeMap() {
	const mapContainer = document.getElementById('map');
	const options = {
		center: new kakao.maps.LatLng(33.450701, 126.570667),
		level: 3
	};
	map = new kakao.maps.Map(mapContainer, options);
	marker = new kakao.maps.Marker({
		map: map
	});
}

function execution_daum_address() {
	new daum.Postcode(
		{
			oncomplete: function(data) {
				const geocoder = new kakao.maps.services.Geocoder();
				geocoder
					.addressSearch(
						data.address,
						function(results, status) {
							if (status === kakao.maps.services.Status.OK) {
								const coords = new kakao.maps.LatLng(
									results[0].y,
									results[0].x);
								/*우편번호를 input요소에 전달	
								document
									.getElementById('aaddress1').value = data.zonecode; */
								document
									.getElementById('aadress').value = data.address;
								map.panTo(coords);
								marker.setPosition(coords);
							} else {
								alert('주소를 찾을 수 없습니다.');
							}
						});
			}
		}).open();
}

initializeMap();

// 별 모양 라디오 버튼 처리
document.addEventListener('click', function() {
	const stars = document.querySelectorAll('.star');
	const agradeInput = document.getElementById('agrade');

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
});


// 숙소등록 저장        
document.getElementById("saveBtn").addEventListener("click", function(){
  	alert("클릭!!!");
  	// 저장할 데이터를 가져오거나 직접 구성
  	var accommodationAndRoomInfoDto  = {
		
			accommodationDto :{
			acate: document.querySelector("select[name='acate']").value,
            aname: document.querySelector("input[name='aname']").value,
            aadress: document.querySelector("input[name='aadress']").value  || null ,
            aphone: document.querySelector("input[name='aphone']").value || null ,
            atotalroom: document.querySelector("input[name='atotalroom']").value || null ,
            agrade: document.getElementById("agrade").value  || null ,
            adetail: document.querySelector("input[name='adetail']").value || null ,
            amainimg: document.querySelector("input[name='amainimg']").value || null,
			},
		  roomInfoDto: {
			riroomtype: document.querySelector("input[name='riroomtype']").value || null ,
            riroom: document.querySelector("input[name='riroom']").value || null ,
            riservice: document.querySelector("input[name='riservice']").value || null ,
            risize: document.querySelector("input[name='risize']").value || null ,
            riminper: document.querySelector("input[name='riminper']").value || null ,
            rimaxper: document.querySelector("input[name='rimaxper']").value || null ,
            ripeak: document.querySelector("input[name='ripeak']").value || null ,
            risemipeak: document.querySelector("input[name='risemipeak']").value || null ,
            rioff: document.querySelector("input[name='rioff']").value || null ,
             
            rimainimg: document.querySelector("input[name='rimainimg']").value || null,
            riextraimg1: document.querySelector("input[name='riextraimg1']").value || null,
            riextraimg2: document.querySelector("input[name='riextraimg2']").value || null,
            
			}
		};
		console.log("formData : " , accommodationAndRoomInfoDto);
		
		// AJAX 요청을 생성하고 데이터를 서버로 전송
		var xhr = new XMLHttpRequest();
		
		// 요청 초기화,(POST요청, 엔드포인트 url 설정)
		xhr.open('POST', '/admin/accommodation/save', true);
		
		// 요청헤더 설정(json 데이터 전송)
		xhr.setRequestHeader("Content-Type", "application/json");
		
		// 요청 완료 시 동작할 콜백 함수 정의
		xhr.onload = function(){
			if(xhr.status >= 200 && xhr.status < 300){
				//요청이 성공적으로 처리
				console.log("저장 완료 !");
				alert("저장완료 !");
				window.location.href="/admin/accommodation/list";
			}else{
				// 요청이 실패했을 때 실행되는 코드
				console.log("저장실패!", xhr.statusText);
				alert("저장실패! 오류발생!");
			}
		};
		
		// 요청 실패시 동작할 콜백함수정의
		xhr.onerror = function(){
			console.log("요청실패 : ", xhr.statusText);
			alert("요청 실패 ! 서버와의 통신에 문제가 발생했습니다 !");
		}
		
		// 데이터를 json문자열로 변환하여 전송
		xhr.send(JSON.stringify(accommodationAndRoomInfoDto));
		
});
