package com.example.enotes_api.Repository;



import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.enotes_api.Entity.Category;
 


@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer>{


   //List<Category> findByActiveTrue();
}
