
document.addEventListener('DOMContentLoaded', function() {
	document.querySelectorAll('.delete-btn').forEach(button => {
		button.addEventListener('click', function(event) {
			event.preventDefault();
			if (confirm('Are you sure you want to delete this product?')) {
				fetch(this.href, {
					method: 'GET'
				}).then(response => {
					if (response.ok) {
						window.location.reload();
					} else {
						console.error('Failed to delete product');
					}
				}).catch(error => console.error('Error:', error));
			}
		});
	});
});
