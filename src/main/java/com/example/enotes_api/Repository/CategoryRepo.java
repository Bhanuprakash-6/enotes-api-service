package com.example.enotes_api.Repository;



import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.enotes_api.Entity.Category;
 


@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer>{

   @Query("SELECT c FROM Category c WHERE c.is_active = true")
   List<Category> findByActiveTrue();

   // @Query("SELECT c FROM Category c WHERE c.id = :id AND c.is_deleted = false")
   // Optional<Category> findByIdAndIsDeletedFalse(@Param("id") Integer id);

   //is_delete
   @Query("SELECT c FROM Category c WHERE c.id = :id AND c.is_delete = false")
    Optional<Category> findByIdAndIsDeletedFalse(@Param("id") Integer id);

   @Query("SELECT c FROM Category c WHERE c.is_active = true AND c.is_delete = false")
   List<Category> findByActiveTrueAndNotDeleted();


   Boolean existsByName(String name);


}
