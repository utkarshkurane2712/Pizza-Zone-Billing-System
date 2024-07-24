document.addEventListener('DOMContentLoaded', function() {
    const productCards = document.querySelectorAll('.product-card');
    const billingItemsContainer = document.getElementById('billing-items');
    const totalAmountContainer = document.getElementById('total-amount');
    const searchBar = document.getElementById('search-bar');
    const productsGrid = document.getElementById('products-grid');

    let orderItems = {};

    productCards.forEach(card => {
        card.addEventListener('click', function() {
            const productId = this.dataset.id;
            addToBilling(productId);
        });
    });

    searchBar.addEventListener('input', function() {
        const searchQuery = this.value.toLowerCase();
        const products = productsGrid.querySelectorAll('.product-card');
        products.forEach(product => {
            const productName = product.querySelector('.product-info h5').textContent.toLowerCase();
            if (productName.includes(searchQuery)) {
                product.style.display = '';
            } else {
                product.style.display = 'none';
            }
        });
    });

    function addToBilling(productId) {
        fetch(`/addToCart/${productId}`)
            .then(response => response.json())
            .then(product => {
                if (!product || !product.price) {
                    console.error('Invalid product data', product);
                    return;
                }

                if (!orderItems[productId]) {
                    orderItems[productId] = { ...product, quantity: 1 };
                } else {
                    orderItems[productId].quantity++;
                }

                renderBillingItems();
                updateTotalAmount();
            })
            .catch(error => console.error('Error:', error));
    }

    function renderBillingItems() {
        billingItemsContainer.innerHTML = '';
        for (const productId in orderItems) {
            const item = orderItems[productId];
            const discountPrice = (item.price * 0.8).toFixed(2);
            const totalPrice = (discountPrice * item.quantity).toFixed(2);

            const billingRow = document.createElement('tr');
            billingRow.innerHTML = `
                <td>${item.name}</td>
                <td>${item.price.toFixed(2)}</td>
                <td>${discountPrice}</td>
                <td>
                    <button class="decrease-qty" data-id="${productId}">-</button>
                    <span>${item.quantity}</span>
                    <button class="increase-qty" data-id="${productId}">+</button>
                </td>
                <td>${totalPrice}</td>
                <td><img src="${item.image}" alt="Product Image" style="max-width: 50px;"></td>
            `;

            billingRow.querySelector('.increase-qty').addEventListener('click', function() {
                orderItems[productId].quantity++;
                renderBillingItems();
                updateTotalAmount();
            });

            billingRow.querySelector('.decrease-qty').addEventListener('click', function() {
                if (orderItems[productId].quantity > 1) {
                    orderItems[productId].quantity--;
                } else {
                    delete orderItems[productId];
                }
                renderBillingItems();
                updateTotalAmount();
            });

            billingItemsContainer.appendChild(billingRow);
        }
    }

    function updateTotalAmount() {
        let totalAmount = 0;
        for (const productId in orderItems) {
            const item = orderItems[productId];
            const discountPrice = item.price * 0.8;
            totalAmount += discountPrice * item.quantity;
        }
        totalAmountContainer.textContent = totalAmount.toFixed(2);
    }
});
