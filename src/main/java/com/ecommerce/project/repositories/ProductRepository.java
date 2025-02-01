package com.ecommerce.project.repositories;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // X_NOTE: JPA is smart enough to run queries base on method names: Query Derivation
    // X_NOTE: IMPORTANT: Make sure to use same naming convention in DTOs in method name or app won't run
    Page<Product> findByCategoryOrderByProductPriceAsc(Category category, Pageable pageDetails);

    Page<Product> findByProductNameLikeIgnoreCase(String keyword, Pageable pageDetails);
}
