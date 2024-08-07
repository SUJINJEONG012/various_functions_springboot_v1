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
		const lastDate = new Date(year, month + 1,0).getDate();
		
		monthYearElement.textContent = date.toLocaleDateString('ko-KR', {month:'long', year: 'numeric'});
		daysElement.innerHTML = '';
		
		for(let i =0; i < firstDay; i++){
			const emptyDay = document.createElement('div');
			emptyDay.classList.add('day', 'empty');
			console.log("daysElement.appendChild(emptyDay) : " + daysElement.appendChild(emptyDay));
			daysElement.appendChild(emptyDay);
			
		}
		
		
	}
	
})