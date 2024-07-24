package com.shop.PizzaZone.Controllers;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.shop.PizzaZone.Entites.Admin;
import com.shop.PizzaZone.Entites.Product;
import com.shop.PizzaZone.Services.ProductServices;

@Controller
public class AdminController {
	
	@Autowired
	SessionFactory sf;
	
	@Autowired
	ProductServices productServices;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/loggedin")
	public String loggedin(Admin admin) {
		Session ss = sf.openSession();
		Admin DBAdmin = ss.get(Admin.class, admin.getEmail());
		if(admin.getPassword().equals(DBAdmin.getPassword())) {
			return "home";
		}
		else {
			return "login";
		}
	}
	
	@GetMapping("/home")
	public String home(Model model) {
		List<Product> products = productServices.findAllProduct();
		model.addAttribute("products", products);
		return "home";
	}
	
	

}
