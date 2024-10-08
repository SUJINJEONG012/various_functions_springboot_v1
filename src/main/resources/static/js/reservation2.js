document.addEventListener('DOMContentLoaded', function() {
    let currentDate = new Date();
    let nextMonthDate = new Date(currentDate.getFullYear(), currentDate.getMonth() + 1, 1);
    let selectedCheckinDate = null;
    let selectedCheckoutDate = null;
    let roomPeak = 0; // 방 정보를 저장할 변수
   

 	// room_peak 값을 HTML에서 추출하는 함수
    function fetchRoomPeakFromHtml() {
        const roomPeakElements = document.querySelectorAll('.room-peak');
        if (roomPeakElements.length > 0) {
            // 첫 번째 room_peak 값을 사용 (모든 방이 동일하다고 가정)
            const roomPeakText = roomPeakElements[0].textContent;
            roomPeak = parseInt(roomPeakText.replace('원', '').trim());
            
           
        }
    }
    
    fetchRoomPeakFromHtml(); // HTML에서 room_peak 값을 가져옴


    // URL에서 accommodationId 추출
    function getAccommodationIdFromUrl() {
        const urlParams = new URLSearchParams(window.location.search);
        return urlParams.get('accommodationId');
    }
    
    const accommodationId = getAccommodationIdFromUrl();

    // 방 정보를 가져오는 함수
   function fetchRooms(accommodationId) {
	    // 숙소아이디를 제대로 가져오는 확인
	    if(!accommodationId){
			console.error("No accommodationId prived");
			return;
		}else{
			console.log(" 숙소아이디를 제대로 들고옴", accommodationId );
		}
        fetch(`/accommodation/view?accommodationId=${accommodationId}`)
            .then(response => {
				const contentType= response.headers.get('content-type');
				console.log("response content-type :", contentType);
				
				if(contentType && contentType.includes('application/json')){
					return response.json();// json 형식으로 응답을 처리
				}else{
					console.log('expected json, but received:', contentType);
					return response.text();// json이 아닌 응답텍스트를 반환
				}
			})
            .then(data => {
				
				if(typeof data === 'string'){
					console.log('not json response:', data);
				}else{
                rooms = data.rooms || [];
                console.log('Rooms Data:', rooms);
                updateCalendar(currentDate, 'current');
                updateCalendar(nextMonthDate, 'next');
                }
            })
            .catch(error => console.error('Error fetching room data:', error));
    }




    function updateCalendar(date, calendarType) {
        let monthYearElement, daysElement, today = new Date();
        today.setHours(0, 0, 0, 0);

        if (calendarType === 'current') {
            monthYearElement = document.getElementById('currentMonthYear');
            daysElement = document.getElementById('currentMonthDays');
        } else {
            monthYearElement = document.getElementById('nextMonthYear');
            daysElement = document.getElementById('nextMonthDays');
        }

        const year = date.getFullYear();
        const month = date.getMonth();
        const firstDay = new Date(year, month, 1).getDay();
        const lastDate = new Date(year, month + 1, 0).getDate();

        monthYearElement.textContent = date.toLocaleDateString('ko-KR', { month: 'long', year: 'numeric' });
        daysElement.innerHTML = '';

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

            if (currentDay < today) {
                dayElement.classList.add('disabled');
            } else {
                dayElement.onclick = function () {
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

                    updateSelectedDates();
                    updateCalendar(currentDate, 'current');
                    updateCalendar(nextMonthDate, 'next');
                };
            }

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
        return date1.getFullYear() === date2.getFullYear() &&
            date1.getMonth() === date2.getMonth() &&
            date1.getDate() === date2.getDate();
    }

    function updateSelectedDates() {
    const checkinElement = document.getElementById('selectedCheckinDate');
    const checkoutElement = document.getElementById('selectedCheckoutDate');
    const durationElement = document.getElementById('duration');
    const totalAmountElement = document.getElementById('totalAmount');

    checkinElement.textContent = selectedCheckinDate ? selectedCheckinDate.toLocaleDateString('ko-KR') : '없음';
    checkoutElement.textContent = selectedCheckoutDate ? selectedCheckoutDate.toLocaleDateString('ko-KR') : '없음';

    console.log("Selected Check-in Date:", selectedCheckinDate); 
    console.log("Selected Check-out Date:", selectedCheckoutDate);

    if (selectedCheckinDate && selectedCheckoutDate) {
        const differenceInTime = selectedCheckoutDate.getTime() - selectedCheckinDate.getTime();
        const differenceInDays = differenceInTime / (1000 * 3600 * 24);

        const nights =differenceInDays;
      	durationElement.textContent = `${nights} 박 ${nights + 1} 일`;

        const totalAmount = calculateTotalAmount(selectedCheckinDate, selectedCheckoutDate);
        totalAmountElement.textContent = totalAmount + '원';
    } else {
        durationElement.textContent = '없음';
        totalAmountElement.textContent = '없음';
    }
}

     function calculateTotalAmount(checkinDate,checkoutDate ) {
		let totalAmount = 0;
		let currentDate = new Date(checkinDate);
		
		//체크아웃 날짜 전날까지 반복하면서 금액 합산
		while(currentDate < checkoutDate){
			totalAmount += roomPeak;
			currentDate.setDate(currentDate.getDate()+1); // 다음 날로 이동
		}
		return totalAmount;
    }


    function changeMonth(offset, calendarType) {
        if (calendarType === 'current') {
            currentDate.setMonth(currentDate.getMonth() + offset);
            updateCalendar(currentDate, 'current');
        } else {
            nextMonthDate.setMonth(nextMonthDate.getMonth() + offset);
            updateCalendar(nextMonthDate, 'next');
        }
    }

    fetchRooms(accommodationId); // 방 정보를 가져오는 함수 호출
    
    
    //예약하기 버튼과 모달창 엘리먼트 선택
    const reserveButton = document.getElementById('reserveButton');
    const reservationModal = document.getElementById('reservationModal');
    const closeModal = document.getElementById('closeModal');
    const reservationDetails = document.getElementById('reservationDetails');
    const confirmReservationButton = document.getElementById('confirmReservationButton');
    
    //예약하기 버튼 클릭시 모달 창 열기
    reserveButton.addEventListener('click', function(){
		const checkinDate = selectedCheckinDate ? selectedCheckinDate.toLocaleDateString('ko-KR') : '없음';
        const checkoutDate = selectedCheckoutDate ? selectedCheckoutDate.toLocaleDateString('ko-KR') : '없음';
        const totalAmount = document.getElementById('totalAmount').textContent;
        
		reservationDetails.innerHTML = `체크인 날짜 : ${checkinDate}<br> 체크아웃날짜 : ${checkoutDate}<br> 총 금액: ${totalAmount}`;
		reservationModal.style.display = "block";
	});
	//a 모달창닫기버튼 클릭시 모달창 닫기
	closeModal.addEventListener('click', function(){
		reservationModal.style.display = "none";
	});
	//모달창 외부를 클릭하면 모달창 닫기
	window.addEventListener('click', function(event){
		if(event.target === reservationModal){
			reservationModal.style.display = "none";
		}
	});
	
	// 예약 확인 버튼 클릭 시 예약 로직 추가
    confirmReservationButton.addEventListener('click', function() {
        
        const checkInDate = selectedCheckinDate ? selectedCheckinDate.toLocaleDateString('ko-KR') : '';
        const checkOutDate = selectedCheckoutDate ? selectedCheckoutDate.toLocaleDateString('ko-KR') : '';
        
         // 추가로, 객실 ID와 사용자 ID를 추가합니다
        const reservationData = {
            accommodationId: accommodationId,  // 객실 ID
            roomId:roomId,
            checkInDate: checkInDate,
            checkOutDate: checkOutDate
        };
        
       // 서버로 데이터를 POST 요청합니다.
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
                alert('예약이 성공적으로 완료되었습니다.');
                reservationModal.style.display = 'none';  // 예약 완료 후 모달 닫기
            } else {
                 // 서버의 실패 메시지를 로그에 남기고 사용자에게 피드백을 제공
            	console.error('예약 실패:', data.message);
            	alert('예약에 실패했습니다: ' + data.message);
            }
        })
        .catch((error) => {
            console.error('예약 실패:', error);
        });    
    });
    
    
    
    updateCalendar(currentDate, 'current');
    updateCalendar(nextMonthDate, 'next');
});




