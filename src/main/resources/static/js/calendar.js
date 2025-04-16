/**
 * 
 */

//document.addEventListener('DOMContentLoaded', function() {
//	const calendarEl = document.getElementById('calendar');
//	const calendar = new FullCalendar.Calendar(calendarEl, {
//		initialView: 'dayGridMonth',
//		locale: 'ja'
//	});
//	calendar.render();
//})
//
//function loadCalendar(townId) {
//	fetch(`/api/trash-schedules/by-town?townId=${townId}`)
//		.then(response => response.json())
//		.then(data => {
//			const events = data.map(schedule => {
//				return {
//					title: schedule.trashType.name,
//					start: schedule.date,
//					allDay: true
//				};
//			});
//			
//			const calendarEl = document.getElementById('calendar');
//			const calendar = new FullCalendar.Calendar(calendarEl, {
//				initialView: 'dayGridMonth',
//				events: events,
//				locale: 'ja'
//			});
//			
//			calendar.render();
//		})
//		.catch(err => {
//			console.error("カレンダー読み込みエラー：", err);
//		});
//}
//
//document.querySelector('form').addEventListener('submit', function(e) {
//	e.preventDefault();
//	const townId = document.getElementById('town').value;
//	loadCalendar(townId);
//});

//document.addEventListener('DOMContentLoaded', function() {
//	const calendarEl = document.getElementById('calendar');
//	const townSelect = document.getElementById('town');
//	
//	const calendar = new FullCalendar.Calendar(calendarEl, {
//		initialView: 'dayGridMonth',
//		locale: 'ja',
//		height: 'auto',
//		events: [],
//		datesSet: function (info) {
//			if (townSelect.value) {
//				loadTrashEvents(townSelect.value, info.start);
//			}
//		}
//	});
//	
//	calendar.render();
//	
//	townSelect.addEventListener('change', function() {
//		const townId = this.value;
//		if (!townId) {
//			calendar.removeAllEvents();
//			return;
//		}
//		
//		const currentStart = calendar.view.currentStart;
//		loadTrashEvents(townId, currentStart);
//	});
//		
//	function loadTrashEvents(townId, monthStartDate) {
//		fetch(`/api/trash-schedules/by-town?townId=${townId}`)
//			.then(response => response.json())
//			.then(data => {
//				calendar.removeAllEvents();
//				
//				const year = monthStartDate.getFullYear();
//				const month = monthStartDate.getMonth();
//				const events = [];
//				
//					
//				const weekdays = {
//					'Saturday': 0,
//					'Sunday': 1,
//					'Monday': 2,
//					'Tuesday': 3,
//					'Wednesday': 4,
//					'Thursday': 5,
//					'Friday': 6
//				};
//					
//				data.forEach(schedule => {
//					const { weekOfMonth, collectionDay, trashType } = schedule;
//					const weekday = weekdays[collectionDay];
//					
//					if (weekOfMonth === 'Every') {
//						for (let i = 1; i <= 5; i++) {
//							const date = getNthWeekdayDate(year, month, weekday, i);
//							if (date) {
//								events.push({
//									title: trashType.name,
//									start: date.toISOString().split('T')[0]
//								});
//							}
//						}
//					} else {
//						const nth = parseInt(weekOfMonth, 10);
//						const date = getNthWeekdayDate(year, month, weekday, nth);
//						if (date) {
//							events.push({
//								title: trashType.name,
//								start: date.toISOString().split('T')[0]
//							});
//						}
//					}
//				});
////					const weekday = weekdays[collectionDay];
////					if (weekOfMonth === 'Every') {
////						for (let i = 0; i < 5; i++) {
////							const date = getNthWeekdayDate(today, i + 1, weekday)
////							if (date.getMonth() === today.getMonth()) {
////								events.push({
////									title: trashType.name,
////									start: date.toISOString().split('T')[0]
////								});
////							}
////						}
////					} else {
////						const nth = parseInt(weekOfMonth, 10);
////						const date = getNthWeekdayDate(today, nth, weekday);
////						if (date.getMonth() === today.getMonth()) {
////							events.push({
////								title: trashType.name,
////								start: date.toISOString().split('T')[0]
////							});
////						}
////					}
////				});
//				
//				calendar.addEventSource(events);
//			})
//			.catch(error => {
//				console.error('ごみ収集日の取得に失敗：', error);
//			});
//	}
//	
//	function getNthWeekdayDate(year, month, weekday, nth) {
//		const firstDay = new Date(year, month, 1);
//		let count = 0;
//		for (let day = 1; day <= 31; day++) {
//			const date = new Date(year, month, day);
//			if (date.getMonth() !== month) break;
//			if (date.getDay() === weekday) {
//				count++;
//				if (count === nth) {
//					return date;
//				}
//			}
//		}
//		return null;
//	}
//	
////	function getNthWeekdayDate(baseDate, nth, weekday) {
////		const year = baseDate.getFullYear();
////		const month = baseDate.getMonth();
////		const firstDay = new Date(year, month, 1);
////		
////		let count = 0;
////		for (let day = 1; day <= 31; day++) {
////			const date = new Date(year, month, day);
////			if (date.getMonth() !== month) break;
////			if (date.getDay() === weekday) {
////				count++;
////				if (count === nth) return date;
////			}
////		}
////		return new Date();
////	}
//});

document.addEventListener('DOMContentLoaded', function() {
	const calendarEl = document.getElementById('calendar');
	const townSelect = document.getElementById('town');
	
	let currentTownId = null;
	
	const calendar = new FullCalendar.Calendar(calendarEl, {
		initialView: 'dayGridMonth',
		locale: 'ja',
		timeZone: 'local',
		height: 'auto',
		events: [],
		datesSet: function (info) {
			if (currentTownId) {
				loadScheduleEvents(currentTownId, info.start, info.end);
			}
		}
	});
	
	calendar.render();
	
	townSelect.addEventListener('change', function() {
		const townId = this.value;
		currentTownId = townId;
		
		if (!townId) {
			calendar.removeAllEvents();
			return;
		}
		
		const currentStart = calendar.view.currentStart;
		const currentEnd = calendar.view.currentEnd
		loadScheduleEvents(townId, currentStart, currentEnd);
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
				
				const trashTypeColors = {
					1: '#FF4C4C',
					2: '#87CEFA',
					3: '#FFA500',
					4: '#ADFF2F',
					5: '#1E90FF'
				};
				
				const current = new Date(startDate);
				while (current < endDate) {
					const year = current.getFullYear();
					const month = current.getMonth();
				
					data.forEach(schedule => {
						const { weekOfMonth, collectionDay, trashType } = schedule;
						const weekday = weekdays[collectionDay.trim()];
					
						if (weekday === undefined) return;
					
						const color = trashTypeColors[trashType.id] || '#808080';
					
						if (weekOfMonth === 'Every') {
							for (let day = 1; day <= 31; day++) {
								const date = new Date(year, month, day);
								if (date.getMonth() !== month) break;
								if (date.getDay() === weekday) {
									events.push({
										title: trashType.name,
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
									title: trashType.name,
									start: formatDate(date),
									color: color
								});
							}
						}
					});
				
					current.setMonth(current.getMonth() + 1);
					current.setDate(1);
				}
				
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