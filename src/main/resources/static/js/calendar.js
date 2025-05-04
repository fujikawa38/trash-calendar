/**
 * 
 */

document.addEventListener('DOMContentLoaded', function () {
	const calendarEl = document.getElementById('calendar');
	const townSelect = document.getElementById('town');
	const showCalendar = document.getElementById('showCalendar');

	let currentTownId = null;

	const calendar = new FullCalendar.Calendar(calendarEl, {
		initialView: 'dayGridMonth',
		locale: 'ja',
		timeZone: 'local',
		height: 'auto',
		events: [],
		datesSet: function () {
			if (currentTownId) {
				const currentDate = calendar.getDate();
				const year = currentDate.getFullYear();
				const month = currentDate.getMonth();

				const startOfMonth = new Date(year, month, 1);
				const endOfMonth = new Date(year, month + 1, 0);

				loadScheduleEvents(currentTownId, startOfMonth, endOfMonth);
			}
		}
	});

	calendar.render();
	
	function getQueryParam(name) {
		const params = new URLSearchParams(window.location.search);
		return params.get(name);
	}

	const initialTownId = getQueryParam('town');
	if (initialTownId) {
		currentTownId = initialTownId;
		townSelect.value = initialTownId;

		const currentDate = calendar.getDate();
		const year = currentDate.getFullYear();
		const month = currentDate.getMonth();
		const startOfMonth = new Date(year, month, 1);
		const endOfMonth = new Date(year, month + 1, 0);

		loadScheduleEvents(currentTownId, startOfMonth, endOfMonth);
	}

		showCalendar.addEventListener('click', function () {
		const townId = townSelect.value;
		currentTownId = townId;

		if (!townId) {
			calendar.removeAllEvents();
			return;
		}

		const currentDate = calendar.getDate();
		const year = currentDate.getFullYear();
		const month = currentDate.getMonth();
		const startOfMonth = new Date(year, month, 1);
		const endOfMonth = new Date(year, month + 1, 0);

		loadScheduleEvents(townId, startOfMonth, endOfMonth);
	});

	function loadScheduleEvents(townId, startDate, endDate) {
		fetch(`/api/trash-schedules/by-town?townId=${townId}`)
			.then(response => response.json())
			.then(data => {
				calendar.removeAllEvents();

				const events = [];

				const weekdays = {
					'Sunday': 0,
					'Monday': 1,
					'Tuesday': 2,
					'Wednesday': 3,
					'Thursday': 4,
					'Friday': 5,
					'Saturday': 6
				};
				
				const trashTypeNames = {
					1: '可燃ごみ',
					2: '資源物（びん・缶・ペットボトル）',
					3: '資源物（古紙・布類）',
					4: '資源物（木の枝・刈り草・葉',
					5: '不燃ごみ/有害ごみ',
					6: '有害ごみ'
				};

				const trashTypeColors = {
					1: '#FF4C4C',
					2: '#87CEFA',
					3: '#FFA500',
					4: '#ADFF2F',
					5: '#1E90FF',
					6: '#1E90FF'
				};

				const year = startDate.getFullYear();
				const month = startDate.getMonth();

				data.forEach(schedule => {
					const { weekOfMonth, collectionDay, trashTypeId } = schedule;
					const weekday = weekdays[collectionDay.trim()];
					if (weekday === undefined) return;

					const color = trashTypeColors[trashTypeId] || '#808080';
					const name = trashTypeNames[trashTypeId] || '不明';

					if (weekOfMonth === 'Every') {
						for (let day = 1; day <= 31; day++) {
							const date = new Date(year, month, day);
							if (date.getMonth() !== month) break;
							if (date.getDay() === weekday) {
								events.push({
									title: name,
									start: formatDate(date),
									color: color
								});
							}
						}
					} else {
						const nth = parseInt(weekOfMonth, 10);
						const date = getNthWeekdayDate(year, month, nth, weekday);
						if (date) {
							events.push({
								title: name,
								start: formatDate(date),
								color: color
							});
						}
					}
				});

				calendar.addEventSource(events);
			})
			.catch(error => {
				console.error('ごみ収集日の取得に失敗：', error);
			});
	}

	function getNthWeekdayDate(year, month, nth, weekday) {
		let count = 0;
		for (let day = 1; day <= 31; day++) {
			const date = new Date(year, month, day);
			if (date.getMonth() !== month) break;
			if (date.getDay() === weekday) {
				count++;
				if (count === nth) return date;
			}
		}
		return null;
	}

	function formatDate(date) {
		const y = date.getFullYear();
		const m = String(date.getMonth() + 1).padStart(2, '0');
		const d = String(date.getDate()).padStart(2, '0');
		return `${y}-${m}-${d}`;
	}
});