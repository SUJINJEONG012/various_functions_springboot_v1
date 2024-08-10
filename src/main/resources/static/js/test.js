document.addEventListener('DOMContentLoaded', function(){

	// 현재 날짜와 다음 달 날짜
	let currentDate = new Date();
	let nextMonthDate = new Date(currentDate.getFullYear(), currentDate.getMonth() +1,1);
	let selectedCheckinDate = null;
	let selectedCheckoutDate = null;
	
	function updateCalendar(date, calendarType){
		
		let monthYearElement, daysElement, today = new Date();
		today.setHours(0,0,0,0);
		
		if(calendarType === 'current'){
			monthYearElement = document.getElementById('currentMonthYear');
			daysElement = document.getElementById('currentMonthDays'); 
		}else{
			monthYearElement = document.getElementById('nextMonthYear');
			daysElement = document.getElementById('nextMonthDays');
		}
		
		const year = date.getFullYear();
		const month = date.getMonth();
		const firstDay = new Date(year, month, 1).getDay();
		const lastDate = new Date(year, month + 1, 0).getDate();
		
		monthYearElement.textContent = date.toLocaleDateString('ko-KR', {month:'long', year: 'numeric'});
		daysElement.innerHTML = '';
		
		for(let i =0; i < firstDay; i++){
			const emptyDay = document.createElement('div');
			emptyDay.classList.add('day', 'empty');
			console.log("daysElement.appendChild(emptyDay) : " + daysElement.appendChild(emptyDay));
			daysElement.appendChild(emptyDay);
			
		}
		
		for(let day = 1; day <= lastDate; day++){
			const dayElement = document.createElement('div');
			dayElement.classList.add('day');
			dayElement.textContent = day;
			
			const currentDay = new Date(year, month, day);
			
			if(currentDay < today){
				dayElement.classList.add('disabled');
			}else{
				/*날짜 비활성화되지 않은경우, 즉 선택가능한 날짜일 때 실행 onclick함수를 할당하여 날짜를 클릭했을때 수행될 동작 정의*/ 
				dayElement.onclick = function(){
					// 클릭한 날짜를 Date객체로 생성
					const selectedDate = new Date(year, month, day);
					
					//체크인 날짜가 없거나, 체크인 날짜와 체크아웃 날짜가 이미 설정된 경우
					if(!selectedCheckinDate || (selectedCheckinDate && selectedCheckoutDate)){
						//새로 선택한 날짜를 체크인날짜로 설정
						selectedCheckinDate = selectedDate;
						//체크아웃날짜는 null로 초기화
						selectedCheckoutDate = null;
					
					// 체크인 날짜는 있지만 체크아웃 날짜가 없는 경우
					}else if(selectedCheckinDate && !selectedCheckoutDate){
						// 선택한 날짜가 현재 체크인 날짜보다 이전이면, 현재 날짜로 체크아웃 날짜로 설정하고, 새로 선택한 날짜를 체크인 날짜로 설정
						if(selectedDate  < selectedCheckinDate){
							selectedCheckoutDate = selectedCheckinDate;
							selectedCheckinDate = selectedDate;							
						}else{
						//선택한 날짜를 체크아웃으로 설정
						selectedCheckoutDate = selectedDate	
						}
					}
					
					updateSelectedDates();
					updateCalendar(currentDate,'current');
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
	
	function isSameDay(date1, date2){
		return date1.getFullYear() === date2.getFullYear() && 
			date1.getMonth() === date2.getMonth() && 
			date1.getDate() === date2.getDate();
	}
	
	function updateSelectedDates(){
		const checkinElement = document.getElementById('selectedCheckinDate');
		const checkoutElement = document.getElementById('selectedCheckoutDate');
		const durationElement = document.getElementById('duration');
		const totalAmountElement = document.getElementById('totalAmount');
		
		checkinElement.textContent = selectedCheckinDate ? selectedCheckinDate.toLocaleDateString('ko-KR') : '없음' ;
		checkoutElement.textContent = selectedCheckoutDate ? selectedCheckoutDate.toLocaleDateString('ko-KR') : '없음';
		
		if(selectedCheckinDate && selectedCheckoutDate){
			// 날짜간의 차이계산
			const differenceInTime = selectedCheckoutDate.getTime() - selectedCheckinDate.getTime();
			const differenceInDays = differenceInTime / (1000 * 3600 * 24);
			
			// 차이를 '박' 과 '일' 로 분리해서 계산
			const nights = Math.floor(differenceInDays);//숙박하는 '박'수
			const days = nights + 1; // 전체'일'수
			
			durationElement.textContent = `${nights}박 ${days}일`;
			
			const totalAmount = calculateTotalAmount(selectedCheckinDate, selectedCheckoutDate);
			totalAmount.textContent= totalAmount + '원';
		}else{
			durationElement.textContent = '없음';
			totalAmountElement.textContent = '없음';
		}
	}
	
	
	function calculateTotalAmount(checkinDate, checkoutDate){
		let totalAmount = 0;
		let currentDate = new Date(checkinDate);
		while (currentDate <= checkoutDate){
			//여기에서 룸 요금 
			const rate = 10000;
			totalAmount += rate;
			currentDate.setDate(currentDate.getDate()+1);
		}
		return totalAmount;
	}
	// 현재달력, 다음달력 offset 몇개월을이동했는지 기억하기 위해 선언,
	function changeMonth(offset, calendarType){
		if(calendarType === 'current'){
			currentDate.setMonth(currentDate.getMonth()+ offset);
			updateCalendar(currentDate, 'current');
		}else{
			nextMonthDate.setMonth(nextMonthDate.getMonth() + offset);
			updateCalendar(nextMonthDate, 'next');
		}
	}
	
	updateCalendar(currentDate, 'current');
	updateCalendar(nextMonthDate, 'next');
	
});