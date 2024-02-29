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
document.getElementById("saveBtn").addEventListener("click", function(){
  	
  	alert("클릭!!!");
  	
  	//FormData 객체 생성
  	var formData = new FormData();
  	
  	//파일업로드 위한 추가이미지 파일
  	var mainImgFile =    document.querySeleoctor("input[name='amainimg']").files[0];
    var extraImg1File  = document.querySeleoctor("input[name='riextraimg1']").files[0];
    var extraImg2File  = document.querySeleoctor("input[name='riextraimg2']").files[0];
  	
  	formData.append("amainimg", mainImgFile);
  	formData.append("riextraimg1", extraImg1File);
  	formData.append("riextraimg2", extraImg2File);
  	
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
          
            riextraimg1 : document.querySelector("input[name='riextraimg1']").value || null,
    		    riextraimg2 : document.querySelector("input[name='riextraimg2']").value || null
  	
			}
		};
	

		
		// AJAX 요청을 생성하고 데이터를 서버로 전송
		var xhr = new XMLHttpRequest();
		
		// 요청 초기화,(POST요청, 엔드포인트 url 설정)
		xhr.open('POST', '/admin/accommodation/save', true);
		
		// 요청헤더 설정(json 데이터 전송) => formData를 이용하면 헤더는 포함되어서 설정하지않아도 됨.
		//xhr.setRequestHeader("Content-Type", "application/json");
		
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
		//xhr.send(formData);


});


