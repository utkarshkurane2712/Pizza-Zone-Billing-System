package com.shop.PizzaZone.Repos;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.PizzaZone.Entites.Category;
import com.shop.PizzaZone.Entites.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
	
	List<Product> findByCategory(Category category);
}
