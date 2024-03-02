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
                document.getElementById("aadress").value = addr;
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
/*document.getElementById("saveBtn").addEventListener("click", function(){
  	
  	// FormData 객체를 사용하여 폼 데이터를 가져옴
  	var formData = new FormData(document.querySelector('form'));
  	
  	// Fetch API를 사용하여 POST 요청 전송
  	fetch('/admin/accommodation/save', {
    method: 'POST',
    body: formData
  })
  .then(response => {
    if (!response.ok) {
      throw new Error('Network response was not ok');
    }
    return response.text();
  })
  .then(data => {
    // 서버 응답 처리
    console.log(data);
  })
  .catch(error => {
    console.error('There has been a problem with your fetch operation:', error);
  });
  	
});*/

function saveBtn(){
	
	// 폼 데이터 가져오기
  	var formData = new FormData(document.getElementById("saveFormAccommodations"));
  
  
	// AJAX 요청 설정
  var xhr = new XMLHttpRequest();
  xhr.open("POST", "/admin/accommodation/save"); // POST 방식으로 "/saveFormData" 엔드포인트에 요청 보내기
  xhr.onload = function () {
    if (xhr.status >= 200 && xhr.status < 300) {
      console.log("Success:", xhr.responseText);
    } else {
      console.error("Error:", xhr.responseText);
    }
  };
  xhr.onerror = function () {
    console.error("Request failed");
  };
  
  // 폼 데이터 전송
  console.log("formData : " , formData);
  xhr.send(formData);
  
}


