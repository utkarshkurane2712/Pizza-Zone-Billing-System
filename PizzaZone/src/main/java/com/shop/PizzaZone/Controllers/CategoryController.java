package com.shop.PizzaZone.Controllers;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.shop.PizzaZone.Entites.Category;
import com.shop.PizzaZone.Services.CategoryServices;


@Controller
public class CategoryController {
	
	@Autowired
	CategoryServices categoryServices;

	
	@GetMapping("/category-list")
	public String listCategory(Model model) {
		List<Category> categories = categoryServices.findAllCategories();
		model.addAttribute("categories", categories);
		return "category-list";
	}
	
	
	@GetMapping("/add-category")
	public String addCategory(Model model) {
		model.addAttribute("categories", new Category());
		return "add-category";
	}
	
	@PostMapping("/addC")
	public String addedCategory(@ModelAttribute Category category) {
		categoryServices.saveCategory(category);
		return "category-list";
		
	}
	
	@GetMapping("/edit-category/{id}")
	public String editCategory(@PathVariable("id") Long id,Model model) {
		Category category = categoryServices.findCategoryById(id);
		model.addAttribute("category", category);
		return "edit-category";
	}
	
	
	@PostMapping("/editC/{id}")
	public String editedCategory(@PathVariable("id") Long id, @ModelAttribute Category category) {
		category.setId(id);
		categoryServices.saveCategory(category);
		return "category-list";
	}
	
	@GetMapping("/deleteC/{id}")
	public String deleteCategory(@PathVariable("id") Long id) {
		categoryServices.deleteCategoryById(id);
		return "category-list";
	}
	

//	@GetMapping("/findByCategory")
//	public String findByCategoryId(Model model) {
//		List<Category> categories = categoryServices.findByCategory();
//		model.addAttribute("categories", categories);
//		return "/home";
//	}

}
