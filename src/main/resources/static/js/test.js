document.addEventListener('DOMContentLoaded', function() {
    let currentDate = new Date();
    let nextMonthDate = new Date(currentDate.getFullYear(), currentDate.getMonth() + 1, 1);
    let selectedCheckinDate = null;
    let selectedCheckoutDate = null;
    let rooms = []; // 방 정보를 저장할 변수

    // accommodationId를 적절히 설정하세요.
    const accommodationId = 1; // 예시값, 실제로는 URL에서 동적으로 가져와야 합니다.

    // 방 정보를 가져오는 함수
    function fetchRooms(accommodationId) {
        fetch(`/accommodation/view?accommodationId=${accommodationId}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                // 방 정보를 포함한 숙소 정보에서 rooms 배열을 추출합니다.
                rooms = data.rooms || [];
                console.log('Rooms Data:', rooms);
                updateCalendar(currentDate, 'current');
                updateCalendar(nextMonthDate, 'next');
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

        if (selectedCheckinDate && selectedCheckoutDate) {
            const differenceInTime = selectedCheckoutDate.getTime() - selectedCheckinDate.getTime();
            const differenceInDays = differenceInTime / (1000 * 3600 * 24);

            const nights = Math.floor(differenceInDays);
            const days = nights + 1;

            durationElement.textContent = `${nights}박 ${days}일`;

            const totalAmount = calculateTotalAmount(selectedCheckinDate, selectedCheckoutDate);
            totalAmountElement.textContent = totalAmount + '원';
        } else {
            durationElement.textContent = '없음';
            totalAmountElement.textContent = '없음';
        }
    }

    function calculateTotalAmount(checkinDate, checkoutDate) {
        if (!rooms || rooms.length === 0) {
            return 0; // 객실 정보가 없을 때 예외 처리
        }

        let totalAmount = 0;
        let currentDate = new Date(checkinDate);

        while (currentDate <= checkoutDate) {
            const rate = rooms[0].pricePerNight;
            totalAmount += rate;
            currentDate.setDate(currentDate.getDate() + 1);
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

console.log("Selected Check-in Date:", selectedCheckinDate); 
console.log("Selected Check-out Date:", selectedCheckoutDate);

    updateCalendar(currentDate, 'current');
    updateCalendar(nextMonthDate, 'next');
});
