/**
 * 
 */

document.addEventListener('DOMContentLoaded', function() {
	const wardSelect = document.getElementById('ward');
	const townSelect = document.getElementById('town');
	
	wardSelect.addEventListener('change', function() {
		const wardId = this.value;
		
		if(!wardId) {
			townSelect.innerHTML = '<option value="">-- 選択してください --</option>';
			return;
		}
		
		fetch(`/api/towns/by-ward?wardId=${wardId}`)
			.then(response => {
				if (!response.ok) {
					throw new Error("通信エラー");
				}
				return response.json()
			})
			.then(data => {
				townSelect.innerHTML = '<option value="">-- 選択してください --</option>';
				
				data.forEach(town => {
					const option = document.createElement('option');
					option.value = town.id;
					option.textContent = town.name;
					townSelect.appendChild(option);
				});
			})
			.catch(error => {
				console.error('町丁の取得に失敗しました：', error);
			});
	});
});