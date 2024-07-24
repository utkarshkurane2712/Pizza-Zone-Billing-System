package com.shop.PizzaZone.Services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.PizzaZone.Entites.Category;
import com.shop.PizzaZone.Entites.Product;
import com.shop.PizzaZone.Repos.CategoryRepo;
import com.shop.PizzaZone.Repos.ProductRepo;

@Service
public class CategoryServices {
	
	
		@Autowired
		CategoryRepo categoryRepo;
		
		@Autowired
		private ProductRepo productRepo;
		
		
		public List<Category> findAllCategories(){
			return categoryRepo.findAll();
		}
		
		
		public Category findCategoryById(Long id) {
			return categoryRepo.findById(id).orElse(null);
		}
		
		
		public Category saveCategory(Category category) {
			return categoryRepo.save(category);
		}
		
		public void deleteCategoryById(Long id) {
			categoryRepo.deleteById(id);
		}
		
		public List<Product> findByCategory(Category category){
			return productRepo.findByCategory(category);
		}

}
