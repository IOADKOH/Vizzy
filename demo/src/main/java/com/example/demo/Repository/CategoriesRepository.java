package com.example.demo.Repository;

import com.example.demo.Model.ModelCategories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository <ModelCategories, Long> {
}
