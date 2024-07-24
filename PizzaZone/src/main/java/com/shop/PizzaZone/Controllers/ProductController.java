package com.shop.PizzaZone.Controllers;

import java.io.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.shop.PizzaZone.Entites.Category;
import com.shop.PizzaZone.Entites.Product;
import com.shop.PizzaZone.Services.CategoryServices;
import com.shop.PizzaZone.Services.ProductServices;

@Controller
public class ProductController {
	
	@Autowired
	ProductServices productServices;

	@Autowired
	CategoryServices categoryServices;
	
	private final String UPLOAD_DIR = "/Projects/PizzaZone/src/main/resources/static/images/";
	
	
	@GetMapping("/product-list")
	public String listProduct(Model model) {
		List<Product> products =productServices.findAllProduct();
		model.addAttribute("products", products);
		return "product-list";
	}
	
	
	@GetMapping("/add-product")
	public String addProduct(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("categories", categoryServices.findAllCategories());
		return "add-product";
	}
	
	private String saveImage(MultipartFile imageFile) throws IOException {
		String fileName = imageFile.getOriginalFilename();
		File file =new File(UPLOAD_DIR + fileName);
		Files.createDirectories(Paths.get(UPLOAD_DIR));
		imageFile.transferTo(file);
		return "/images/" +fileName;
	}
	
	@PostMapping("/addP")
	public String addedProduct(@RequestParam("name") String name,@RequestParam("price") double price,@RequestParam("image") MultipartFile imagefile,@RequestParam("category.id") Long id,Model model) throws IOException {
		String imagePath =saveImage(imagefile);
		
		Category category = categoryServices.findCategoryById(id);
		Product product = new Product();
		product.setName(name);
		product.setPrice(price);
		product.setImage(imagePath);
		product.setCategory(category);
		productServices.saveProduct(product);
		return "product-list";
	}
	
	
	@GetMapping("/edit-product/{id}")
	public String editProduct(@PathVariable Long id, Model model) {
		Product product = productServices.findProductById(id);
		model.addAttribute("product", product);
		model.addAttribute("categories", categoryServices.findAllCategories());
		return "edit-product";
	}
	
	
	@PostMapping("/editP/{id}")
	public String editedProduct(@PathVariable Long id, @ModelAttribute Product product) {
		product.setId(id);
		productServices.saveProduct(product);
		return "product-list";
	}
	
	
	@GetMapping("/deleteP/{id}")
	public String deleteProduct(@PathVariable Long id) {
		productServices.deleteProductById(id);
		return "product-list";
	}
	
	@GetMapping	("/addToCart/{id}")
	@ResponseBody
	public Product assToCart(@PathVariable Long id) {
		return productServices.findProductById(id);
	}
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
}
