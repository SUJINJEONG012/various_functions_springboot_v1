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
								document
									.getElementById('aaddress1').value = data.zonecode; //우편번호를 input요소에 전달
								document
									.getElementById('aaddress2').value = data.address;
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
function saveAcc(){
	
}