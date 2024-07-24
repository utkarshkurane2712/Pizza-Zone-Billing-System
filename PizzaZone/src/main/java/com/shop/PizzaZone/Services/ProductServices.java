package com.shop.PizzaZone.Services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.PizzaZone.Entites.Product;
import com.shop.PizzaZone.Repos.ProductRepo;

@Service
public class ProductServices {
	
	@Autowired
	private ProductRepo productRepo;
	
	
	public List<Product> findAllProduct(){
		return productRepo.findAll();
	}
	
	
	public Product findProductById(Long id) {
		return  productRepo.findById(id).orElse(null);
	}
	
	
	public Product saveProduct(Product product) {
		return productRepo.save(product);
	}

	
	public void deleteProductById(Long id) {
		productRepo.deleteById(id);
	}
	
	public Product getProductById(Long id) {
		Optional<Product> product = productRepo.findById(id);
		return product.orElse(null);
	}
	
	
}
