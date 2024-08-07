
document.addEventListener('DOMContentLoaded', function() {
	document.getElementById('add-category-form').addEventListener('submit', function(event) {
		event.preventDefault();
		const form = this;
		fetch(form.action, {
			method: 'POST',
			body: new FormData(form)
		}).then(response => {
			if (response.ok) {
				window.location.href = '/category-list';
			} else {
				console.error('Failed to add category');
			}
		}).catch(error => console.error('Error:', error));
	});
});
