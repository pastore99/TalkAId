let currentDate = new Date();
let currentMonth = currentDate.getMonth();
let currentYear = currentDate.getFullYear();

document.getElementById('calendarTitle').textContent = `${new Date().toLocaleString('default', { month: 'long' })} ${currentYear}`;





function createCalendar() {
    const monthNames = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
    document.getElementById('calendarTitle').textContent = `${monthNames[currentMonth]} ${currentYear}`;

    let firstDayOfMonth = new Date(currentYear, currentMonth, 1).getDay();
    let daysInMonth = 32 - new Date(currentYear, currentMonth, 32).getDate();

    let calendarBody = '';

    // Fill in the days of the month
    for(let x = 1; x <= daysInMonth; x++) {
        calendarBody += `<td>${x}</td>`;
    }

    // Append the calendar to the DOM
    document.getElementById('calendarTable').innerHTML = `<tr>${calendarBody}</tr>`;

    // Add click event listeners to each date cell
    document.querySelectorAll('#calendarTable td').forEach((cell, index) => {
        cell.addEventListener('click', () => {
            // Remove the 'selected' class from all cells
            document.querySelectorAll('#calendarTable td').forEach(cell => cell.classList.remove('selected'));

            // Add the 'selected' class to the clicked cell
            cell.classList.add('selected');

            // Get the selected day from the clicked cell
            let selectedDay = Number(cell.textContent);
             selectedDay++;
            // Calculate the selected date and time
            let selectedDate = new Date(currentYear, currentMonth, selectedDay);

            // Update the hidden input fields
            document.getElementById('selectedDate').value = selectedDate.toISOString().split('T')[0];
        });
    });
}





function createTimeTable() {
    let timeTableBody = '';

    // Fill in the hours of the day
    for(let x = 8; x < 21; x++) {
        timeTableBody += `<td>${x}:00-${x+1}:00</td>`;
    }

    // Append the time table to the DOM
    document.getElementById('timeTable').innerHTML = `<tr>${timeTableBody}</tr>`;

    // Add click event listeners to each date cell
    document.querySelectorAll('#timeTable td').forEach((cell, index) => {
        cell.addEventListener('click', () => {
            // Remove the 'selected' class from all cells
            document.querySelectorAll('#timeTable td').forEach(cell => cell.classList.remove('selected'));

            // Add the 'selected' class to the clicked cell
            cell.classList.add('selected');

            // Get the selected time
            let selectedTime = document.querySelectorAll('#timeTable td')[index].textContent;

            // Update the hidden input field
            document.getElementById('selectedTime').value = selectedTime;
        });
    });

}





createTimeTable();

createCalendar();

document.getElementById('prevMonth').addEventListener('click', () => {
    currentMonth--;
    if(currentMonth < 0) {
        currentMonth = 11;
        currentYear--;
    }
    createCalendar();
});

document.getElementById('nextMonth').addEventListener('click', () => {
    currentMonth++;
    if(currentMonth > 11) {
        currentMonth = 0;
        currentYear++;
    }
    createCalendar();
});
