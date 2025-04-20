/**
 * 
 */

document.addEventListener('DOMContentLoaded', function () {
	const searchButton = document.getElementById('searchButton');
	const searchInput = document.getElementById('searchInput');
	const searchResults = document.getElementById('results');
	
	const typeClassMap = {
		'可燃ごみ': 'burnable',
		'資源物（びん・缶・ペットボトル）': 'petbottle',
		'資源物（古紙・布類）': 'paper',
		'資源物（木の枝・刈り草・葉）': 'leaf',
		'不燃ごみ': 'nonburnable',
		'有害ごみ': 'hazardous',
		'粗大ごみ': 'bulky',
		'その他': 'other'
	}
	
	searchButton.addEventListener('click', function () {
		const keyword = searchInput.value.trim();
		if (!keyword) {
			searchResults.innerHTML = '<p>検索ワードを入力してください</p>';
			return;
		}
		
		fetch(`/api/items/search?keyword=${encodeURIComponent(keyword)}`)
			.then(response => response.json())
			.then(data => {
				if (data.length === 0) {
					searchResults.innerHTML = '<p>該当する品目がありません</p>';
					return;
				}
				
				let html = '<ul>';
				data.forEach(item => {
					const type1 = item.trashType.name;
					const type2 = item.trashType2 ? item.trashType2.name : null;
					
					const main = typeClassMap[type1] || 'unknown';
					const sub = type2 ? (typeClassMap[type2] || 'unknown') : null;
					
					let typeText = `種別：<span class="${main}">${type1}</span>`;
					if (type2) {
						typeText += `/<span class="${sub}">${type2}</span>`;
					}
					html += `<li>${item.name}【${typeText}】</li>`;
				});
				html += '</ul>';
				
				searchResults.innerHTML = html;
			})
			.catch(error => {
				console.error('検索エラー：', error);
				searchResults.innerHTML = '<p>検索中にエラーが発生しました</p>';
			});
	});
});