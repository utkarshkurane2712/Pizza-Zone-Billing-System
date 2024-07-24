
document.addEventListener('DOMContentLoaded', function() {
	document.getElementById('add-product-form').addEventListener('submit', function(event) {
		event.preventDefault();
		const form = this;
		fetch(form.action, {
			method: 'POST',
			body: new FormData(form)
		}).then(response => {
			if (response.ok) {
				window.location.href = '/product-list';
			} else {
				console.error('Failed to add product');
			}
		}).catch(error => console.error('Error:', error));
	});
});
