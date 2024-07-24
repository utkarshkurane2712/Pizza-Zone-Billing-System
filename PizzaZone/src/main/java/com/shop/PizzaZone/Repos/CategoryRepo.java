package com.shop.PizzaZone.Repos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.PizzaZone.Entites.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

}
