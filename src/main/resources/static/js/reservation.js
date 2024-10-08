
document.addEventListener('DOMContentLoaded', function() {

	let currentDate = new Date(); //현재날
	let nextMonthDate = new Date(currentDate.getFullYear(), currentDate.getMonth() + 1, 1);

	//널값을 사용한 이유는 값비교시,변수가 제대로 적용되었는지 확인할 때 ==null 로 사용할 수 있어서.
	let selectedCheckinDate = null;
	let selectedCheckoutDate = null;

	let roomPeak = 0; // 방 요금
	let ramount = 1; // 기본 인원 수


	// 인원 수 변경 버튼 엘리먼트 가져오기
	const decreaseGuestCount = document.getElementById('decreaseGuestCount');
	const increaseGuestCount = document.getElementById('increaseGuestCount');
	const guestCountElement = document.getElementById('ramount');

	// 총 금액을 표시하는 엘리먼트
	const totalAmountElement = document.getElementById('totalAmount');

	// 방 정보를 가져오는 함수
	function fetchRoomPeakFromHtml() {
		const roomPeakElements = document.querySelectorAll('.room-peak');
		if (roomPeakElements.length > 0) {
			const roomPeakText = roomPeakElements[0].textContent;
			roomPeak = parseInt(roomPeakText.replace('원', '').trim());
		}
	}


	// 초기 달력 렌더링
	updateCalendar(currentDate, 'current');
	updateCalendar(nextMonthDate, 'next');



	fetchRoomPeakFromHtml(); // HTML에서 방 요금을 가져옴

	// URL에서 accommodationId 추출
	function getAccommodationIdFromUrl() {
		const urlParams = new URLSearchParams(window.location.search);
		return urlParams.get('accommodationId');
	}

	const accommodationId = getAccommodationIdFromUrl();

	// 방 정보를 가져오는 함수
	function fetchRooms(accommodationId) {
		if (!accommodationId) {
			console.error("No accommodationId provided");
			return;
		}
		console.log("숙소 아이디를 제대로 들고옴", accommodationId);
		fetch(`/accommodation/view?accommodationId=${accommodationId}`)
			.then(response => {
				const contentType = response.headers.get('content-type');
				console.log("response content-type :", contentType);

				if (contentType && contentType.includes('application/json')) {
					return response.json(); // json 형식으로 응답을 처리
				} else {
					console.log('Expected JSON, but received:', contentType);
					return response.text(); // json이 아닌 응답텍스트를 반환
				}
			})
			.then(data => {
				if (typeof data === 'string') {
					console.log('Not JSON response:', data);
				} else {
					roomId = data.roomId || null; // 데이터에서 roomId 추출
					rooms = data.rooms || [];
					console.log('Rooms Data:', rooms);
					updateCalendar(currentDate, 'current');
					updateCalendar(nextMonthDate, 'next');
				}
			})
			.catch(error => console.error('Error fetching room data:', error));
	}



	// 달력 업데이트 함수
	function updateCalendar(date, calendarType) {
		let monthYearElement, daysElement, today = new Date();
		today.setHours(0, 0, 0, 0);

		if (calendarType === 'current') {
			monthYearElement = document.getElementById('currentMonthYear');
			daysElement = document.getElementById('currentMonthDays');
		} else if (calendarType === 'next')
			if (calendarType === 'next') {
				monthYearElement = document.getElementById('nextMonthYear');
				daysElement = document.getElementById('nextMonthDays');
			}

		if (!monthYearElement || !daysElement) {
			console.error('Calendar elements are missing.');
			return;
		}

		const year = date.getFullYear();
		const month = date.getMonth();
		const firstDay = new Date(year, month, 1).getDay();
		const lastDate = new Date(year, month + 1, 0).getDate();

		// 년과 월을 화면에 표시
		monthYearElement.textContent = date.toLocaleDateString('ko-KR', { month: 'long', year: 'numeric' });
		daysElement.innerHTML = '';

		// 첫번째날 앞의 빈 날짜를 채우기
		for (let i = 0; i < firstDay; i++) {
			const emptyDay = document.createElement('div');
			emptyDay.classList.add('day', 'empty');
			daysElement.appendChild(emptyDay);
		}


		for (let day = 1; day <= lastDate; day++) {
			const dayElement = document.createElement('div');
			dayElement.classList.add('day');
			dayElement.textContent = day;

			const currentDay = new Date(year, month, day);

			// 지난 날짜는 비활성화
			if (currentDay < today) {
				dayElement.classList.add('disabled');
			} else {
				// 날짜클릭 이벤트 함수
				dayElement.onclick = function() {
					//날짜선택로직
					const selectedDate = new Date(year, month, day);
					if (!selectedCheckinDate || (selectedCheckinDate && selectedCheckoutDate)) {
						selectedCheckinDate = selectedDate;
						selectedCheckoutDate = null;
					} else if (selectedCheckinDate && !selectedCheckoutDate) {
						if (selectedDate < selectedCheckinDate) {
							selectedCheckoutDate = selectedCheckinDate;
							selectedCheckinDate = selectedDate;
						} else {
							selectedCheckoutDate = selectedDate;
						}
					}
					// 선택된 날짜 업데이트 및 달력 새로고침
					updateSelectedDates();
					updateCalendar(currentDate, 'current');
					updateCalendar(nextMonthDate, 'next');
				};
			}

			// 선댁된 날짜 표시
			if (selectedCheckinDate && isSameDay(selectedCheckinDate, currentDay)) {
				dayElement.classList.add('selected');
			}

			if (selectedCheckoutDate && isSameDay(selectedCheckoutDate, currentDay)) {
				dayElement.classList.add('selected');
			}

			if (selectedCheckinDate && selectedCheckoutDate && currentDay > selectedCheckinDate && currentDay < selectedCheckoutDate) {
				dayElement.classList.add('range');
			}

			daysElement.appendChild(dayElement);
		}
	}



	function isSameDay(date1, date2) {
		return date1.getFullYear() === date2.getFullYear() && date1.getMonth() === date2.getMonth() && date1.getDate() === date2.getDate();
	}

	function updateSelectedDates() {
		const checkinElement = document.getElementById('selectedCheckinDate');
		const checkoutElement = document.getElementById('selectedCheckoutDate');
		const durationElement = document.getElementById('duration');
		const totalAmountElement = document.getElementById('totalAmount');

		//시간을 한국식으로 변경조건
		checkinElement.textContent = selectedCheckinDate ? selectedCheckinDate.toLocaleDateString('ko-KR') : '없음';
		checkoutElement.textContent = selectedCheckoutDate ? selectedCheckoutDate.toLocaleDateString('ko-KR') : '없음';

		/* 기간계산( 체크인날짜와,체크아웃날짜 모두 선택된경우,두 날짜간의 차이를 계산)
		getTime()메서드는날짜객체의 시간을 밀리초단위로반환하므로, 두날짜간의 차이를 계산하고, 
		1000* 3600 * 24로 나누어 일수
		*/
		if (selectedCheckinDate && selectedCheckoutDate) {
			const differenceInTime = selectedCheckoutDate.getTime() - selectedCheckinDate.getTime();
			const differenceInDays = differenceInTime / (1000 * 3600 * 24);

			const nights = differenceInDays;
			durationElement.textContent = `${nights} 박 ${nights + 1} 일`;

			updateTotalAmount();
		} else {
			durationElement.textContent = '없음';
			totalAmountElement.textContent = '없음';
		}
	}

	function updateTotalAmount() {
		if (selectedCheckinDate && selectedCheckoutDate) {
			const totalAmount = calculateTotalAmount(selectedCheckinDate, selectedCheckoutDate);
			const finalAmount = calculateFinalAmount(totalAmount, ramount);
			totalAmountElement.textContent = finalAmount.toLocaleString() + '원';
		} else {
			totalAmountElement.textContent = '없음';
		}
	}

	function calculateTotalAmount(checkinDate, checkoutDate) {
		let totalAmount = 0;
		let currentDate = new Date(checkinDate);

		while (currentDate < checkoutDate) {
			totalAmount += roomPeak;
			currentDate.setDate(currentDate.getDate() + 1);
		}
		return totalAmount;
	}

	function calculateFinalAmount(baseAmount, ramount) {
		if (ramount <= 2) {
			return baseAmount; // 2명까지 추가 금액 없음
		} else {
			const additionalGuests = ramount - 2;
			const additionalCost = additionalGuests * 10000; // 1명당 10,000원
			return baseAmount + additionalCost;
		}
	}

	function changeMonth(offset, calendarType) {
		if (calendarType === 'current') {
			currentDate.setMonth(currentDate.getMonth() + offset);
			updateCalendar(currentDate, 'current');
		} else if (calendarType === 'next') {
			nextMonthDate.setMonth(nextMonthDate.getMonth() + offset);
			updateCalendar(nextMonthDate, 'next');
		}
	}

	function adjustGuestCount(change) {
		ramount += change;
		ramount = Math.max(1, ramount); // 최소 1명
		guestCountElement.textContent = ramount;
		updateTotalAmount();
	}

	// 인원 수 버튼 클릭 이벤트
	if (decreaseGuestCount) {
		decreaseGuestCount.addEventListener('click', function() {
			adjustGuestCount(-1);
		});
	}

	if (increaseGuestCount) {
		increaseGuestCount.addEventListener('click', function() {
			adjustGuestCount(1);
		});
	}

	// 예약하기 버튼과 모달창 엘리먼트 선택
	const reserveButton = document.getElementById('reserveButton');
	const reservationModal = document.getElementById('reservationModal');
	const closeModal = document.getElementById('closeModal');
	const reservationDetails = document.getElementById('reservationDetails');
	const confirmReservationButton = document.getElementById('confirmReservationButton');


	// 예약하기 버튼 클릭 시 모달 창 열기
	reserveButton.addEventListener('click', function() {
		// 서버에 로그인 상태 요청
		fetch('/api/check-login')
			.then(response => response.json())
			.then(data => {
				if (!data.isLoggedIn) {
					alert('로그인 후 예약하실 수 있습니다.');
					return; // 로그인하지 않은 경우 모달창을 열지 않고 종료
				}

				// 체크인 및 체크아웃 날짜 확인
				if (!selectedCheckinDate || !selectedCheckoutDate) {
					alert('체크인과 체크아웃 날짜를 모두 선택해주세요.');
					return; // 날짜가 선택되지 않은 경우 모달창을 열지 않고 종료
				}

				const checkinDate = selectedCheckinDate.toLocaleDateString('ko-KR');
				const checkoutDate = selectedCheckoutDate.toLocaleDateString('ko-KR');
				const totalAmount = document.getElementById('totalAmount').textContent;
				const ramount = document.getElementById('ramount').textContent;

				reservationDetails.innerHTML = `
                <div>
                <span lang="en">체크인 날짜 : ${checkinDate}</span>  <br> 체크아웃 날짜 : ${checkoutDate}<br> 
                총 금액: ${totalAmount} <br> 
                총인원수 : ${ramount}
                </div>
            `;

				reservationModal.style.display = "block"; // 모든 조건을 만족할 경우 모달창 열기
			})
			.catch((error) => {
				alert('로그인 상태 확인 중 오류 발생: ' + error.message);
			});
	});


	// 모달창 닫기 버튼 클릭 시 모달창 닫기
	closeModal.addEventListener('click', function() {
		reservationModal.style.display = "none";
	});

	// 모달창 외부 클릭 시 모달창 닫기
	window.addEventListener('click', function(event) {
		if (event.target === reservationModal) {
			reservationModal.style.display = "none";
		}
	});


	

	// 결제하기 버튼 클릭 시 예약 로직 추가
	confirmReservationButton.addEventListener('click', function() {
		const checkInDate = selectedCheckinDate ? selectedCheckinDate.toLocaleDateString('ko-KR') : '';
		const checkOutDate = selectedCheckoutDate ? selectedCheckoutDate.toLocaleDateString('ko-KR') : '';
		const roomId = document.getElementById('roomId') ? document.getElementById('roomId').value : '';

		const reservationData = {
			accommodationId: accommodationId,
			roomId: roomId,
			checkInDate: checkInDate,
			checkOutDate: checkOutDate,
			ramount: ramount
		};

		// 예약 요청을 보내고 응답을 확인
		fetch('/api/reserve', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify(reservationData),
			
		})
	
			.then(response => response.json())
			.then(data => {
				if (data.success) {
					const reservationId = data.reservationId; // 예약 ID 가져오기
        			reservationData.rid = reservationId; // 예약 데이터에 추가
					
					// 결제가 성공한 경우 예약 요청
					alert('예약이 성공적으로 완료되었습니다. 결제 처리를 진행합니다.');
					console.log('예약 ID 성공 :', reservationId); // 예약 ID를 출력
					// 예약이 성공적으로 완료된 경우 결제 진행
					mypayment(reservationData);
					
				} else {
					alert('예약 처리에 실패했습니다:' + data.message);
					console.log('예약 ID 실패 :', reservationData); // 예약 ID를 출력
				}
			})
			.catch((error) => {
				alert('예약 요청 중 오류 발생: ' + error.message);
			});

	});
	
	// 결제로직 추가 
	const mypayment = (reservationData) => {
		const IMP = window.IMP;
		IMP.init("imp25587836"); // 해당 키를 사용하세요
		const myAmount = Number(document.getElementById('totalAmount').textContent.replace(/[^0-9]/g, ''));

		IMP.request_pay({
			pg: "html5_inicis", // KG이니시스
			pay_method: "card",
			name: "예약결제",
			amount: myAmount, // 결제 금액
			buyer_email: "peekaboo32@naver.com", // 메일
			buyer_name: "홍길동", // 주문자
			buyer_tel: "010-8635-0291", // 전화번호
			buyer_addr: "부산광역시 수영구 광안동",
			buyer_postcode: "01181",
			m_redirect_url: "/", // 모바일 결제 후 리다이렉트될 주소
		},  (rsp) => {
			if (rsp.success) {
				// 결제 성공
				fetch('/api/reserve/confirm', {
					method: 'POST',
					headers: {
						'Content-Type': 'application/json',
					},
					body: JSON.stringify({...reservationData, rid: reservationData.rid, paymentId : rsp.imp_uid}),//결제 ID를 추가
				})
					.then(response => response.json())
					.then(data => {
						console.log('서버응답데이터:', data);
						if (data.success) {
							alert(data.message);
							reservationModal.style.display = 'none';
						} else {
							alert('결제는 성공했지만 예약처리에 실패했습니다: ' + data.message);
					
							console.log('예약실패이유: ', data.message || '서버오류');
						}
					})
					.catch((error) => {
						alert('예약 실패: ' + error.message);
					});
			} else {
				// 결제 실패
				alert('결제에 실패했습니다. 오류 메시지: ' + rsp.error_msg);
				console.error('결제 실패 응답:', rsp); // 로그에 전체 응답 정보 추가
			}
		});
	}


});